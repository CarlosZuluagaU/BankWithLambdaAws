package org.example;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class BankResponse {

    private BigDecimal quota;
    private BigDecimal rate;
    private int term;


    private BigDecimal quotaWithAccount;
    private BigDecimal rateWithAccount;
    private int termWithAccount;


}
