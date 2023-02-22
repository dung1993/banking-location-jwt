package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transfers")
public class Transfer extends BaseEntity implements Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
    private Customer sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "id", nullable = false)
    private Customer recipient;

    @Column(name = "transfer_amount", precision = 10, scale = 0, nullable = false)
    private BigDecimal transferAmount;

    private Long fees;

    @Column(name = "fees_amount", precision = 10, scale = 0, nullable = false)
    private BigDecimal feesAmount;

    @Column(name = "transaction_amount", precision = 10, scale = 0, nullable = false)
    private BigDecimal transactionAmount;


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Transfer transfer = (Transfer) target;

        BigDecimal transferAmount = transfer.getTransferAmount();

    }
}
