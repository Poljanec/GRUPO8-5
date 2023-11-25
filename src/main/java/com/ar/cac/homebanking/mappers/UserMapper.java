package com.ar.cac.homebanking.mappers;

        import com.ar.cac.homebanking.models.UserDTO;
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
        user.setName(dto.getNombre());
        user.setSurname(dto.getApellido());
        user.setDni(dto.getDni());
        user.setEmail(dto.getMail());
        user.setPassword(dto.getContrasena());
        user.setFechanacimiento(dto.getFechanacimiento());
        user.setDireccion(dto.getDireccion());
        return user;
    }

    public static UserDTO userToDto(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setNombre(user.getName());
        dto.setApellido(user.getSurname());
        dto.setDni(user.getDni());
        dto.setMail(user.getEmail());
        dto.setContrasena(user.getPassword());
        dto.setFechanacimiento(user.getFechanacimiento());
        dto.setDireccion(user.getDireccion());
        return dto;
    }
}