package com.cg.model.dto;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepositDTO {

    private Long id;

    private BigDecimal transactionAmount;

    public Deposit toDeposit(Customer customer) {
        return new Deposit()
                .setId(id)
                .setCustomer(customer)
                .setTransactionAmount(transactionAmount)
                ;
    }
}
