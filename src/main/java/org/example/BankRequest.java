package org.example;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankRequest {

    private String accountId;
    private BigDecimal amount;
    private int term;
    private BigDecimal rate;

}
