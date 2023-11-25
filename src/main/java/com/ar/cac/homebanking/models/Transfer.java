
package com.ar.cac.homebanking.models;


        import com.ar.cac.homebanking.models.enums.TransferType;
        import jakarta.persistence.*;
        import lombok.Getter;
        import lombok.Setter;

        import java.math.BigDecimal;
        import java.util.Date;

@Entity
@Table(name = "transferencias")
@Getter
@Setter
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "account_origin")
    private Long account_origin;

    @Column(name = "account_destination")
    private Long account_destination;

    @Column(name = "date_transf")
    private Date date_transf;

}