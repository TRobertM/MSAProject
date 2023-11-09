package com.msaproject.repositories;

import com.msaproject.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByUsername(String name);
    boolean existsByPhone(int phone);
    boolean existsByEmail(String email);
}
