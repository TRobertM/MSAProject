package com.msaproject.controllers;

import com.msaproject.models.RegisterRequest;
import com.msaproject.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private CustomerService customerService;

    @Autowired
    public RegisterController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest newUser){
        if (customerService.checkUsername(newUser.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
        if (customerService.checkEmail(newUser.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }
        customerService.addUser(newUser);
        return ResponseEntity.status(HttpStatus.OK).body("Registration successful");
    }
}
