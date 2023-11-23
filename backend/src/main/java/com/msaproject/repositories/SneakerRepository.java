package com.msaproject.repositories;

import com.msaproject.models.Sneaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SneakerRepository extends JpaRepository<Sneaker, Long> {
    List<Sneaker> findAll();
    Optional<Sneaker> findById(Long id);
}
