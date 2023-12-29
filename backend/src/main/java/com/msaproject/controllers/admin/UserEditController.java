package com.msaproject.controllers.admin;

import com.msaproject.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/private/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserEditController {

    @Autowired
    CustomerRepository customerRepository;

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestBody Long id){
        customerRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
