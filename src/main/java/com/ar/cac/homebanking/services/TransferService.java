package com.ar.cac.homebanking.services;

        import com.ar.cac.homebanking.exceptions.UserNotExistsException;
        import com.ar.cac.homebanking.mappers.TransferMapper;
        import com.ar.cac.homebanking.mappers.UserMapper;
        import com.ar.cac.homebanking.models.Transfer;
        import com.ar.cac.homebanking.models.User;
        import com.ar.cac.homebanking.models.TransferDTO;
        import com.ar.cac.homebanking.models.UserDTO;
        import com.ar.cac.homebanking.models.enums.TransferType;
        import com.ar.cac.homebanking.repositories.TransferRepository;
        import org.springframework.stereotype.Service;

        import java.math.BigDecimal;
        import java.util.List;
        import java.util.stream.Collectors;

@Service
public class TransferService {

    private final TransferRepository repository;

    public TransferService(TransferRepository repository){
        this.repository = repository;
    }
    public List<TransferDTO> getTransfer() {
        List<Transfer> transfers = repository.findAll();
        return transfers.stream()
                .map(TransferMapper::transferToDto)
                .collect(Collectors.toList());
    }

    public TransferDTO createTransfer(TransferDTO dto) {
/*        dto.setType(AccountType.SAVINGS_BANK);
        dto.setAmount(BigDecimal.ZERO);  */
        Transfer newTransfer = repository.save(TransferMapper.dtoToTransfer(dto));
        return TransferMapper.transferToDto(newTransfer);
    }

    public TransferDTO getTransferById(Long id) {
        Transfer entity = repository.findById(id).get();
        return TransferMapper.transferToDto(entity);
    }

    public String deleteTransfer(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "La transferencia con id: " + id + " ha sido eliminada";
        } else {
            // TODO: REFACTOR create new exception
            throw new UserNotExistsException("La transferencia a eliminar no existe");
        }

    }

    public TransferDTO updateTransfer(Long id, TransferDTO dto) {
        if (repository.existsById(id)) {
            Transfer transferToModify = repository.findById(id).get();
            // Validar qué datos no vienen en null para setearlos al objeto ya creado

            // Logica del Patch
            if (dto.getAmount() != null) {
                transferToModify.setAmount(dto.getAmount());
            }

            if (dto.getAccount_origin() != null) {
                transferToModify.setAccount_origin(dto.getAccount_origin());
            }

            if (dto.getAccount_destination() != null) {
                transferToModify.setAccount_destination(dto.getAccount_destination());
            }

            if (dto.getDate_transf() != null) {
                transferToModify.setDate_transf(dto.getDate_transf());
            }

            Transfer transferModified = repository.save(transferToModify);

            return TransferMapper.transferToDto(transferModified);
        }
        return null;
    }
}