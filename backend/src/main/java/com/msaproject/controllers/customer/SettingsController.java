package com.msaproject.controllers;

import com.msaproject.DTOs.CustomerDTO;
import com.msaproject.models.Customer;
import com.msaproject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/p/settings")
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

    @PostMapping
    public ResponseEntity<Void> changeAddress(@RequestBody String address){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Customer> customerOptional = customerService.getCustomerByUsername(username);
        customerOptional.get().setAddress(address);
        return ResponseEntity.ok().build();
    }


}
