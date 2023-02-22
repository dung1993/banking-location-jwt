package com.cg.model.dto;


import com.cg.model.Customer;
import com.cg.model.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Valid;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDTO implements Validator {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private BigDecimal balance;

    @Valid
    private LocationRegionDTO locationRegion;

    public CustomerDTO(Long id, String fullName, String email, String phone, BigDecimal balance, LocationRegion locationRegion) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
        this.locationRegion = locationRegion.toLocationRegionDTO();
    }

    public Customer toCustomer() {
        return new Customer()
                .setId(id)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setBalance(balance)
                .setLocationRegion(locationRegion.toLocationRegion())
                ;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDTO customerDTO = (CustomerDTO) target;

        String fullName = customerDTO.getFullName();
        String email = customerDTO.getEmail();

        if (fullName.length() == 0) {
            errors.rejectValue("fullName", "fullName.null", "Full name is required");
        }
        else {
            if (fullName.length() < 4 || fullName.length() > 25) {
                errors.rejectValue("fullName", "fullName.length", "Full name is accept between 4 and 25 characters");
            }
        }

        if (email.length() == 0) {
            errors.rejectValue("email", "email.null", "Email is required");
        }
        else {
            if (!email.matches("^[\\w]+@([\\w-]+\\.)+[\\w-]{2,6}$")) {
                errors.rejectValue("email", "email.matches", "Email not valid");
            }
        }
    }
}
