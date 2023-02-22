package com.cg.api;


import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.LocationRegion;
import com.cg.model.dto.CustomerDTO;
import com.cg.model.dto.LocationRegionDTO;
import com.cg.service.customer.ICustomerService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private AppUtils appUtils;


    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {

        List<CustomerDTO> customerDTOS = customerService.findALlCustomerDTO();

        return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getById(@PathVariable Long customerId) {

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            throw new ResourceNotFoundException("Customer not found !");
        }

        Customer customer = customerOptional.get();

        return new ResponseEntity<>(customer.toCustomerDTO(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> doCreate(@RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {

        new CustomerDTO().validate(customerDTO, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        customerDTO.setId(null);
        customerDTO.getLocationRegion().setId(null);
        Customer customer = customerDTO.toCustomer();

        Optional<Customer> emailOptional = customerService.findByEmail(customer.getEmail());

        if (emailOptional.isPresent()) {
            throw new EmailExistsException("Email is already exist");
        }

        customer.getLocationRegion().setId(null);
        customer.setId(null);
        customer.setBalance(BigDecimal.ZERO);
        Customer newCustomer = customerService.save(customer);

        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PatchMapping ("/{customerId}")
    public ResponseEntity<?> doUpdate(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }


        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Optional<Customer> customerOptional = customerService.findById(customerId);
        Customer customer = customerDTO.toCustomer();
        if (!customerOptional.isPresent()) {
            throw new DataInputException("ID customer is not exist!");
        }

        Optional<Customer> emailOptional = customerService.findByEmailAndIdIsNot(customer.getEmail(), customerId);

        if (emailOptional.isPresent()) {
            throw new EmailExistsException("Email is already exist");
        }

        customer.setId(customerId);
        customer.setBalance(customerOptional.get().getBalance());
        Customer newCustomer = customerService.save(customer);

        return new ResponseEntity<>(newCustomer, HttpStatus.OK);
    }

    @PostMapping("/deposit/{customerId}")
    public ResponseEntity<?> doDeposit(@RequestBody Deposit deposit) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
