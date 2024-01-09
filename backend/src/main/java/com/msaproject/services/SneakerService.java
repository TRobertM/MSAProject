package com.msaproject.services;

import com.msaproject.models.Inventory;
import com.msaproject.models.Sneaker;
import com.msaproject.repositories.InventoryRepository;
import com.msaproject.repositories.SneakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class SneakerService {
    private final SneakerRepository sneakerRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public SneakerService(SneakerRepository sneakerRepository, InventoryRepository inventoryRepository){
        this.sneakerRepository = sneakerRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public List<Sneaker> getAllSneakers(){
        return sneakerRepository.findAll();
    }

    public Optional<Sneaker> getSneakerById(Long id){
        return sneakerRepository.findById(id);
    }

    public void addSneaker(Sneaker sneaker){
        sneakerRepository.save(sneaker);
    }

    public void deleteSneaker(Long id){
        sneakerRepository.deleteById(id);
    }

    public List<Sneaker> getSneakersByGender(String gender){
        return sneakerRepository.findSneakersByGender(gender);
    }

    public List<Inventory> getSneakerVariants(Long id){
        Optional<Sneaker> sneaker = sneakerRepository.findById(id);
        Optional<List<Inventory>> inventory = Optional.empty();
        if(sneaker.isPresent()){
            inventory = inventoryRepository.findInventoriesBySneaker(sneaker.get());
        }
        return inventory.orElse(null);
    }
}
