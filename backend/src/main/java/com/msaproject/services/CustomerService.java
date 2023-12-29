package com.msaproject.services;

import com.msaproject.models.Customer;
import com.msaproject.models.CustomerDetails;
import com.msaproject.models.RegisterRequest;
import com.msaproject.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements UserDetailsService {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> userDetail = repository.getCustomerByUsername(username);
        return userDetail.map(CustomerDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public Optional<Customer> getCustomerByUsername(String username){
        return repository.getCustomerByUsername(username);
    }

    public boolean checkEmail(String email){
        return repository.existsByEmail(email);
    }

    public boolean checkUsername(String username){
        return repository.existsByUsername(username);
    }

    private Customer requestToCustomer(RegisterRequest request){
        Customer customer = new Customer();
        String encodedPass = encoder.encode(request.getPassword());
        customer.setEmail(request.getEmail());
        customer.setUsername(request.getUsername());
        customer.setRole(request.getRole());
        customer.setPassword(encodedPass);
        return customer;
    }

    public boolean updateUser(Customer customer){
        repository.save(customer);
        return true;
    }

    public boolean checkPhoneNumber(int phone){
        return repository.existsByPhone(phone);
    }

    public void addUser(RegisterRequest customer) {
        repository.save(requestToCustomer(customer));
    }
}
