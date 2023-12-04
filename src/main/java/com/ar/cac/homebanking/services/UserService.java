package com.ar.cac.homebanking.services;

        import com.ar.cac.homebanking.exceptions.UserNotExistsException;
        import com.ar.cac.homebanking.mappers.AccountMapper;
        import com.ar.cac.homebanking.mappers.UserMapper;
        import com.ar.cac.homebanking.models.Account;
        import com.ar.cac.homebanking.models.User;
        import com.ar.cac.homebanking.models.dtos.AccountDTO;
        import com.ar.cac.homebanking.models.dtos.UserDTO;
        import com.ar.cac.homebanking.models.enums.AccountType;
        import com.ar.cac.homebanking.repositories.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.math.BigDecimal;
        import java.util.List;
        import java.util.Random;
        import java.util.stream.Collectors;

@Service
public class UserService {
    // Inyectar una instancia del Repositorio
    @Autowired
    private UserRepository repository;
    @Autowired AccountDTO accountDTO;
    // Metodos

    public List<UserDTO> getUsers(){

        // Obtengo la lista de la entidad usuario de la db
        List<User> users = repository.findAll();
        // Mapear cada usuario de la lista hacia un dto
        List<UserDTO> usersDtos = users.stream()
                .map(UserMapper::userToDto)
                .collect(Collectors.toList());
        return usersDtos;
    }
    //Consigna grupal. Item 8
    public UserDTO createUser(UserDTO userDto) {


        User userValidated = validateUserByEmail(userDto);
        if (userValidated == null) {
            accountDTO.setTitular(userDto.getEmail());
            User userSaved = repository.save(UserMapper.dtoToUser(userDto));
            /* Antes de usar inversion de control y añadir el @Autowired de AccountDto
            //AccountDTO account = new AccountDTO();
            //account.setType(AccountType.SAVING_ACCOUNT);
            //account.setCbu(generarCbu());
            //System.out.print("Id vale: " + valueId);
            //account.setAlias(generarAlias(valueId));
            //account.setAmount(BigDecimal.ZERO);
            */

            accountDTO.setType(AccountType.SAVING_ACCOUNT);
            accountDTO.setCbu(generarCbu());
            long valueId = userSaved.getId();
            accountDTO.setAlias(generarAlias(valueId));
            accountDTO.setAmount(BigDecimal.ZERO);

            Account accountEntity = AccountMapper.dtoToAccount(accountDTO); //convierto a entidad

            // Asignar la cuenta al usuario del tipo entity
            userSaved.addAccount(accountEntity);
            repository.save(userSaved);
            return UserMapper.userToDto(userSaved);
        } else {
            throw new UserNotExistsException("Usuario con mail: " + userDto.getEmail() + " ya existe");
        }
    }
    //metodo que genera un alias compuesto por una palabra un punto y el id usuario
    private String generarAlias(long num) {

        String[] PALABRAS = {"azul", "verde", "pasto", "rojo", "blanco", "negro", "gris", "nieve", "marron", "rosa", "tierra", "iris", "pastel", "metal", "amar", "plata", "oro", "cristal"};

        Random random = new Random();
        String palabraSeleccionada = PALABRAS[random.nextInt(PALABRAS.length)];

        String aliasGenerado = palabraSeleccionada + "." + num;
        System.out.print("el alias generado es: " + aliasGenerado);
        return aliasGenerado;
    }

//metodo que genera un cbu de 18 decimales. Puede mejorarse con un patron que respete el formato x-xxx-xxxx-xxxxx de un cbu
    public static String generarCbu() {
        Random random = new Random();
        StringBuilder cbu = new StringBuilder();

        cbu.append(1 + random.nextInt(9));

        for (int i = 0; i < 17; i++) {
            cbu.append(random.nextInt(10));
        }
    System.out.print("el cbu es: " + cbu);
        return cbu.toString();
    }


    public UserDTO getUserById(Long id) {
        User entity = repository.findById(id).get();
        return UserMapper.userToDto(entity);
    }

    public String deleteUser(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "El usuario con id: " + id + " ha sido eliminado";
        } else {
            throw new UserNotExistsException("El usuario a eliminar elegido no existe");
        }

    }

    public UserDTO updateUser(Long id, UserDTO dto) {
        if (repository.existsById(id)){
            User userToModify = repository.findById(id).get();
            // Validar qué datos no vienen en null para setearlos al objeto ya creado

            // Logica del Patch
            if (dto.getName() != null){
                userToModify.setName(dto.getName());
            }

            if (dto.getSurname() != null){
                userToModify.setSurname(dto.getSurname());
            }

            if (dto.getEmail() != null){
                userToModify.setEmail(dto.getEmail());
            }

            if (dto.getPassword() != null){
                userToModify.setPassword(dto.getPassword());
            }

            if (dto.getDni() != null){
                userToModify.setDni(dto.getDni());
            }

            User userModified = repository.save(userToModify);

            return UserMapper.userToDto(userModified);
        }

        return null;
    }
    // Validar que existan usuarios unicos por mail
    public User validateUserByEmail(UserDTO dto){
        return repository.findByEmail(dto.getEmail());
    }
}