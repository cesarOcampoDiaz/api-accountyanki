package com.nttdata.document;

import lombok.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Transaction {

    //private static final long serialVersionUID = 9176873029745254542L;
    private String id;
    private Integer typeAccountId;
    private Integer clientId;
    private String phone;

    private String accountId;
    private Integer currencyId;
    private Integer typeOperationId;

    private String originAccount;

    private String destinationAccount;
    private Double amount;
    private LocalDateTime dateTransaction;


}
