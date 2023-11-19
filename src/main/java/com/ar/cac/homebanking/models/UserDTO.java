package com.ar.cac.homebanking.models;

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

    private String mail;

    private String contrasena;

    private String nombre;

    private String apellido;

    private String dni;

    private Date fechanacimiento;

    private String direccion;

}