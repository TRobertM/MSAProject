package com.msaproject.services;

import com.msaproject.models.Inventory;
import com.msaproject.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    public Optional<Inventory> findInventoryById(Long id){
        return inventoryRepository.findInventoryById(id);
    }

    public void saveInventory(Inventory inventory){
        inventoryRepository.save(inventory);
    }
}
