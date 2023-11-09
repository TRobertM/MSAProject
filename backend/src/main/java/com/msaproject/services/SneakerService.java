package com.msaproject.services;

import com.msaproject.models.Sneaker;
import com.msaproject.repositories.SneakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SneakerService {
    private final SneakerRepository sneakerRepository;

    @Autowired
    public SneakerService(SneakerRepository sneakerRepository){
        this.sneakerRepository = sneakerRepository;
    }

    public List<Sneaker> getAllSneakers(){
        return sneakerRepository.findAll();
    }

    public Optional<Sneaker> getSneakerById(Long id){
        return sneakerRepository.findById(id);
    }

    public Sneaker createSneaker(Sneaker sneaker){
        return sneakerRepository.save(sneaker);
    }

    public void deleteSneaker(Long id){
        sneakerRepository.deleteById(id);
    }
}
