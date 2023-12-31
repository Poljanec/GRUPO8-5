package com.ar.cac.homebanking.models;

        import jakarta.persistence.*;
        import lombok.*;

        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mail")
    private String email;

    @Column(name = "contrasena")
    private String password;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String surname;

    @Column(name = "dni")
    private String dni;

    @Column(name = "fechaNacimiento")
    private Date fechaNacimiento;

    @Column(name = "direccion")
    private String direccion;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
        account.setOwner(this);
    }


}

