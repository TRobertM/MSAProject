package com.msaproject.services;

import com.msaproject.DTOs.CustomerDTO;
import com.msaproject.models.Customer;
import com.msaproject.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    private CustomerDTO mapToDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setPhone(customer.getPhone());
        return customerDTO;
    }

    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<Customer> getCustomerById(Long id){
        return customerRepository.findById(id);
    }

    public boolean searchByName(String name){
        return customerRepository.existsByUsername(name);
    }

    public boolean searchByPhone(int phone){
        return customerRepository.existsByPhone(phone);
    }

    public boolean searchByEmail(String email){
        return customerRepository.existsByEmail(email);
    }

    public void registerCustomer(Customer customer){
        customerRepository.save(customer);
    }
}
