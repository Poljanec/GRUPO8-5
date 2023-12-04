package com.ar.cac.homebanking.models.dtos;

        import com.ar.cac.homebanking.models.enums.AccountType;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        import org.springframework.stereotype.Component;

        import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Component //necesario para que reconozca la clase como un beans
public class AccountDTO {

    private Long id;

    private AccountType type;

    private String cbu;

    private String alias;

    private BigDecimal amount;

    private String titular;

}