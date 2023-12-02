package com.ar.cac.homebanking.models.dtos;

        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String email;

    private String password;

    private String name;

    private String surname;

    private String dni;

    private Date fechaNacimiento;

    private String direccion;

}