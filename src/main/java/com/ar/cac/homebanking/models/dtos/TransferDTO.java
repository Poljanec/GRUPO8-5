package com.ar.cac.homebanking.models.dtos;

import com.ar.cac.homebanking.models.enums.AccountType;
import lombok.*;
import java.math.BigDecimal;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransferDTO {

    private Long id;

    private BigDecimal amount;

    private Long transferOrigin;

    private Long transferDestination;

    private Date dateTransf;

}