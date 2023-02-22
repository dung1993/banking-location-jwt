package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Transfer;
import com.cg.model.dto.CustomerDTO;
import com.cg.service.IGeneralService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {

    List<CustomerDTO> findALlCustomerDTO();

    List<Customer> findAllByDeletedIsFalse();

    List<Customer> findAllByIdNot(Long id);

    List<Customer> findAllByIdNotAndDeletedIsFalse(Long id);

    void incrementBalance(Long customerId, BigDecimal transactionAmount);

    Deposit deposit(Deposit deposit);

    Transfer transfer(Transfer transfer);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByEmailAndIdIsNot(String email, Long id);
}
