package com.ar.cac.homebanking.services;

        import com.ar.cac.homebanking.exceptions.InsufficientFoundsException;
        import com.ar.cac.homebanking.exceptions.TransferNotFoundException;
        import com.ar.cac.homebanking.exceptions.UserNotExistsException;
        import com.ar.cac.homebanking.mappers.TransferMapper;
        import com.ar.cac.homebanking.models.Account;
        import com.ar.cac.homebanking.models.Transfer;
        import com.ar.cac.homebanking.models.dtos.TransferDTO;
        import com.ar.cac.homebanking.repositories.AccountRepository;
        import com.ar.cac.homebanking.repositories.TransferRepository;
        import jakarta.transaction.Transactional;
        import org.springframework.stereotype.Service;

        import javax.security.auth.login.AccountNotFoundException;
        import java.util.List;
        import java.util.stream.Collectors;
        import java.util.Date;

@Service
public class TransferService {

    private final TransferRepository repository;

    private final AccountRepository accountRepository;
    public TransferService(TransferRepository repository, AccountRepository accountRepository){
        this.repository = repository;
        this.accountRepository = accountRepository;
    }
    public List<TransferDTO> getTransfer() {
        List<Transfer> transfers = repository.findAll();
        return transfers.stream()
                .map(TransferMapper::transferToDTO)
                .collect(Collectors.toList());
    }

    public TransferDTO getTransferById(Long id){
        Transfer transfer = repository.findById(id).orElseThrow(() ->
                new TransferNotFoundException("Transfer not found with id: " + id));
        return TransferMapper.transferToDTO(transfer);
    }

    public TransferDTO updateTransfer(Long id, TransferDTO transferDto){
        Transfer transfer = repository.findById(id).orElseThrow(() -> new TransferNotFoundException("Transfer not found with id: " + id));
        Transfer updatedTransfer = TransferMapper.dtoToTransfer(transferDto);
        updatedTransfer.setId(transfer.getId());
        return TransferMapper.transferToDTO(repository.save(updatedTransfer));
    }
    public String deleteTransfer(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "Se ha eliminado la transferencia";
        } else {
            return "No se ha eliminado la transferencia";
        }
    }

    @Transactional
        public TransferDTO performTransfer(TransferDTO dto) throws AccountNotFoundException {
            // Comprobar si las cuentas de origen y destino existen
            Account originAccount = accountRepository.findById(dto.getTransferOrigin())
                    .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + dto.getTransferOrigin()));

            Account destinationAccount = accountRepository.findById(dto.getTransferDestination())
                    .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + dto.getTransferDestination()));

        // Comprobar si la cuenta de origen tiene fondos suficientes
        if (originAccount.getAmount().compareTo(dto.getAmount()) < 0) {
            throw new InsufficientFoundsException("Insufficient funds in the account with id: " + dto.getTransferOrigin());
        }

        // Realizar la transferencia
        originAccount.setAmount(originAccount.getAmount().subtract(dto.getAmount()));
        destinationAccount.setAmount(destinationAccount.getAmount().add(dto.getAmount()));

        // Guardar las cuentas actualizadas
        accountRepository.save(originAccount);
        accountRepository.save(destinationAccount);

        // Crear la transferencia y guardarla en la base de datos
        Transfer transfer = new Transfer();
        // Creamos un objeto del tipo Date para obtener la fecha actual
        Date date = new Date();
        // Seteamos el objeto fecha actual en el transferDto
        transfer.setDateTransf(date);   //setDate(date);
        transfer.setTransferOrigin(originAccount.getId());
        transfer.setTransferTarget(destinationAccount.getId());
        transfer.setAmount(dto.getAmount());
        transfer = repository.save(transfer);

        // Devolver el DTO de la transferencia realizada
        return TransferMapper.transferToDTO(transfer);
    }

}