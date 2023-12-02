package com.ar.cac.homebanking.mappers;

        import com.ar.cac.homebanking.models.dtos.UserDTO;
        import com.ar.cac.homebanking.models.User;
        import lombok.Getter;
        import lombok.Setter;
        import lombok.experimental.UtilityClass;
@UtilityClass
@Getter
@Setter
public class UserMapper {

    // Metodos para transformar objetos

    public static User dtoToUser(UserDTO dto){
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setDni(dto.getDni());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFechaNacimiento(dto.getFechaNacimiento());
        user.setDireccion(dto.getDireccion());
        return user;
    }

    public static UserDTO userToDto(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setDni(user.getDni());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setFechaNacimiento(user.getFechaNacimiento());
        dto.setDireccion(user.getDireccion());
        return dto;
    }
}