
package com.ar.cac.homebanking.models;


        import com.ar.cac.homebanking.models.enums.TransferType;
        import jakarta.persistence.*;
        import lombok.*;

        import java.math.BigDecimal;
        import java.util.Date;

@Entity
@Table(name = "transferencias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "account_origin")
    private Long accountOrigin;

    @Column(name = "account_destination")
    private Long accountTarget;

    @Column(name = "date_transf")
    private Date dateTransf;

}