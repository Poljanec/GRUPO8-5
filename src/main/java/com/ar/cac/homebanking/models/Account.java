package com.ar.cac.homebanking.models;


        import com.ar.cac.homebanking.models.enums.AccountType;
        import jakarta.persistence.*;
        import lombok.Getter;
        import lombok.Setter;

        import java.math.BigDecimal;

@Entity
@Table(name = "cuentas")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_type")
    private AccountType type;

    @Column(name = "cbu")
    private String cbu;

    @Column(name = "alias")
    private String alias;

    @Column(name = "amount")
    private BigDecimal amount;

    //@
    //private User owner;

}