package com.msaproject.controllers;

import com.msaproject.DTOs.CustomerDTO;
import com.msaproject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }
}
