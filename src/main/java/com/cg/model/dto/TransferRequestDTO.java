package com.cg.model.dto;

import com.cg.model.Customer;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

public class TransferRequestDTO implements Validator {

    private Customer sender;
    private Customer recipient;

    private String transferAmount;
    private String fees;
    private String feesAmount;
    private String transactionAmount;

    public TransferRequestDTO() {
    }

    public TransferRequestDTO(Customer sender, Customer recipient, String transferAmount, String fees, String feesAmount, String transactionAmount) {
        this.sender = sender;
        this.recipient = recipient;
        this.transferAmount = transferAmount;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public Customer getRecipient() {
        return recipient;
    }

    public void setRecipient(Customer recipient) {
        this.recipient = recipient;
    }

    public String getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(String transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getFeesAmount() {
        return feesAmount;
    }

    public void setFeesAmount(String feesAmount) {
        this.feesAmount = feesAmount;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TransferRequestDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TransferRequestDTO transferRequestDTO = (TransferRequestDTO) target;

        String transferAmountStr = transferRequestDTO.getTransferAmount();

        if (transferAmountStr.length() == 0 || transferAmountStr.length() > 7) {
            errors.rejectValue("transferAmount", "transferAmount.length");
        }
        else {
            if (!transferAmountStr.matches("(^$|[0-9]*$)")){
                errors.rejectValue("transferAmount", "transferAmount.matches");
            }
            else {
                BigDecimal transferAmount = BigDecimal.valueOf(Long.parseLong(transferAmountStr));

                BigDecimal minValue = BigDecimal.valueOf(100);
                BigDecimal maxValue = BigDecimal.valueOf(1000000);

                if (transferAmount.compareTo(minValue) < 0) {
                    errors.rejectValue("transferAmount", "transferAmount.min");
                }

                if (transferAmount.compareTo(maxValue) > 0) {
                    errors.rejectValue("transferAmount", "transferAmount.max");
                }
            }
        }
    }
}
