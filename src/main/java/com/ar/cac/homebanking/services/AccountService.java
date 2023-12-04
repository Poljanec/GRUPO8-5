package com.ar.cac.homebanking.services;

        import com.ar.cac.homebanking.exceptions.AccountNotFoundException;
        import com.ar.cac.homebanking.exceptions.UserNotExistsException;
        import com.ar.cac.homebanking.mappers.AccountMapper;
        import com.ar.cac.homebanking.models.Account;
        import com.ar.cac.homebanking.models.User;
        import com.ar.cac.homebanking.models.dtos.AccountDTO;
        import com.ar.cac.homebanking.models.enums.AccountType;
        import com.ar.cac.homebanking.repositories.AccountRepository;
        import com.ar.cac.homebanking.repositories.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.math.BigDecimal;
        import java.util.List;
        import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private final UserRepository userRepository;
    private final AccountRepository repository;

    public AccountService(UserRepository userRepository, AccountRepository repository){
        this.userRepository = userRepository;
        this.repository = repository;
    }
    public List<AccountDTO> getAccounts() {
        List<Account> accounts = repository.findAll();
        return accounts.stream()
                .map(AccountMapper::accountToDto)
                .collect(Collectors.toList());
    }

    public AccountDTO createAccount(AccountDTO dto) throws AccountNotFoundException{

        try {
            String email = dto.getTitular();
            User userValidated = validateUserByEmail(email);
            Account accountValidated = validateAccountByType(dto.getType());

            if (userValidated != null) {
                if (accountValidated == null) {
                    dto.setAmount(BigDecimal.ZERO);
                    Account newAccount = repository.save(AccountMapper.dtoToAccount(dto));
                    userValidated.addAccount(newAccount);
                    userRepository.save(userValidated);
                    return AccountMapper.accountToDto(newAccount);
                } else {
                    throw new AccountNotFoundException("Ya existe una cuenta de tipo: " + dto.getType());
                }
            } else {
                throw new AccountNotFoundException("No existe el usuario: " + dto.getTitular());
            }
        } catch (AccountNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error interno del servidor", e);
        }
    }

    public Account validateAccountByType(AccountType type){
        return repository.findByType(type);
        //return repository.findByType(type).orElse(null);
    }

    private User validateUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public AccountDTO getAccountById(Long id) {
        Account entity = repository.findById(id).get();
        return AccountMapper.accountToDto(entity);
    }

    public String deleteAccount(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "La cuenta con id: " + id + " ha sido eliminada";
        } else {
            // TODO: REFACTOR create new exception
            throw new UserNotExistsException("La cuenta a eliminar no existe");
        }

    }

    public AccountDTO updateAccount(Long id, AccountDTO dto) {
        if (repository.existsById(id)) {
            Account accountToModify = repository.findById(id).get();
            // Validar qué datos no vienen en null para setearlos al objeto ya creado

            // Logica del Patch
            if (dto.getAlias() != null) {
                accountToModify.setAlias(dto.getAlias());
            }

            if (dto.getType() != null) {
                accountToModify.setType(dto.getType());
            }

            if (dto.getCbu() != null) {
                accountToModify.setCbu(dto.getCbu());
            }

            if (dto.getAmount() != null) {
                accountToModify.setAmount(dto.getAmount());
            }

            Account accountModified = repository.save(accountToModify);

            return AccountMapper.accountToDto(accountModified);
        }
        return null;
    }
}