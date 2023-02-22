package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.LocationRegion;
import com.cg.model.Transfer;
import com.cg.model.dto.CustomerDTO;
import com.cg.repository.CustomerRepository;
import com.cg.repository.DepositRepository;
import com.cg.repository.LocationRegionRepository;
import com.cg.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Override
    public List<Customer> findALl() {
        return customerRepository.findAll();
    }

    @Override
    public List<CustomerDTO> findALlCustomerDTO() {
        return customerRepository.findAllCustomerDTO();
    }

    @Override
    public List<Customer> findAllByDeletedIsFalse() {
        return customerRepository.findAllByDeletedIsFalse();
    }

    @Override
    public List<Customer> findAllByIdNot(Long id) {
        return customerRepository.findAllByIdNot(id);
    }

    @Override
    public List<Customer> findAllByIdNotAndDeletedIsFalse(Long id) {
        return customerRepository.findAllByIdNotAndDeletedIsFalse(id);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {

        LocationRegion locationRegion = customer.getLocationRegion();
        locationRegionRepository.save(locationRegion);
        customer.setLocationRegion(locationRegion);

        return customerRepository.save(customer);
    }

    @Override
    public void incrementBalance(Long customerId, BigDecimal transactionAmount) {
        customerRepository.incrementBalance(customerId, transactionAmount);
    }

    @Override
    public Deposit deposit(Deposit deposit) {
        Customer customer = deposit.getCustomer();
//        BigDecimal currentBalance = customer.getBalance();
        BigDecimal transactionAmount = deposit.getTransactionAmount();
//        BigDecimal newBalance = currentBalance.add(transactionAmount);
//        customer.setBalance(newBalance);
        customerRepository.incrementBalance(customer.getId(), transactionAmount);

        deposit.setId(null);
        deposit.setCreatedAt(new Date());
        depositRepository.save(deposit);

        customer = customerRepository.findById(customer.getId()).get();
        deposit.setCustomer(customer);

        return deposit;
    }

    @Override
    public Transfer transfer(Transfer transfer) {

        BigDecimal transferAmount = transfer.getTransferAmount();
        BigDecimal transactionAmount = transfer.getTransactionAmount();

        customerRepository.decrementBalance(transfer.getSender().getId(), transactionAmount);

        customerRepository.incrementBalance(transfer.getRecipient().getId(), transferAmount);

        transferRepository.save(transfer);

        Customer sender = customerRepository.findById(transfer.getSender().getId()).get();
        transfer.setSender(sender);

        return transfer;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Optional<Customer> findByEmailAndIdIsNot(String email, Long id) {
        return customerRepository.findByEmailAndIdIsNot(email, id);
    }
}
