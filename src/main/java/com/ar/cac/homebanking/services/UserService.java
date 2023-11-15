package com.ar.cac.homebanking.services;
        import com.ar.cac.homebanking.models.UserDTO;
        import com.ar.cac.homebanking.mappers.UserMapper;
        import com.ar.cac.homebanking.models.User;
        import com.ar.cac.homebanking.repositories.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.stream.Collectors;

@Service
public class UserService {
    // Inyectar una instancia del Repositorio
    @Autowired
    private UserRepository repository;

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

    public UserDTO createUser(UserDTO userDto){
        User user = repository.save(UserMapper.dtoToUser(userDto));
        return UserMapper.userToDto(user);
    }


}