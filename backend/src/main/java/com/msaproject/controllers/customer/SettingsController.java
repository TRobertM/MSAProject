package com.msaproject.controllers.customer;

import com.msaproject.DTOs.CustomerDTO;
import com.msaproject.models.Customer;
import com.msaproject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/customer/settings")
public class SettingsController {

    private final CustomerService customerService;

    @Autowired
    public SettingsController(CustomerService customerService){
        this.customerService = customerService;
    }

    private CustomerDTO customerToDTO (Customer customer){
        CustomerDTO newCustomer = new CustomerDTO();
        newCustomer.setUsername(customer.getUsername());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setAddress(customer.getAddress());
        newCustomer.setPhone(customer.getPhone());
        return newCustomer;
    }

    @GetMapping
    public ResponseEntity<CustomerDTO> getSettings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Customer> customerOptional = customerService.getCustomerByUsername(username);
        CustomerDTO newCustomer = new CustomerDTO();
        if(customerOptional.isPresent()){
            newCustomer = customerToDTO(customerOptional.get());
        }
        return ResponseEntity.ok(newCustomer);
    }

    @PutMapping("/change_address")
    public ResponseEntity<Void> changeAddress(@RequestBody String address){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Customer> customerOptional = customerService.getCustomerByUsername(username);
        customerOptional.get().setAddress(address);
        customerService.updateUser(customerOptional.get());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/change_phone")
    public ResponseEntity<String> changePhone(@RequestBody int phoneNumber){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Customer> customerOptional = customerService.getCustomerByUsername(username);
        if(customerService.checkPhoneNumber(phoneNumber) == true){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Phone number is already in use");
        } else {
            customerOptional.get().setPhone(phoneNumber);
            customerService.updateUser(customerOptional.get());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Phone number changed successfully!");
        }

    }
}
