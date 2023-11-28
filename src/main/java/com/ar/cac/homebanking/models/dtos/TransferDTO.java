package com.ar.cac.homebanking.models.dtos;

        import com.ar.cac.homebanking.models.enums.AccountType;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

        import java.math.BigDecimal;
        import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TransferDTO {

    private Long id;

    private BigDecimal amount;

    private Long account_origin;

    private Long account_destination;

    private Date date_transf;

}