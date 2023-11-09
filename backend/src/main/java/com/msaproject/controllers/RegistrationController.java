package com.msaproject.controllers;

import com.msaproject.models.Customer;
import com.msaproject.services.CustomerService;
import com.msaproject.services.PasswordHashService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    private final CustomerService customerService;
    private final PasswordHashService passwordHashService;

    @Autowired
    public RegistrationController(CustomerService customerService, PasswordHashService passwordHashService){
        this.customerService = customerService;
        this.passwordHashService = passwordHashService;
    }

    @PostMapping
    public ResponseEntity<String> registerCustomer(@Valid @RequestBody Customer customer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getFieldError("email").getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email format: " + errorMessage);
        }

        boolean usernameUsed = customerService.searchByName(customer.getUsername());
        boolean phoneUsed = customerService.searchByPhone(customer.getPhone());
        boolean emailUsed = customerService.searchByEmail(customer.getEmail());
        if(usernameUsed){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already used");
        } else if (phoneUsed){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Phone number already used");
        } else if (emailUsed){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already used");
        } else {
            customer.setPassword(passwordHashService.hashPassword(customer.getPassword()));
            customerService.registerCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful");
        }
    }
}
