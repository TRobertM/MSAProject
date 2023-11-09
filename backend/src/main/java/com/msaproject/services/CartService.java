package com.msaproject.services;

import com.msaproject.DTOs.InventoryDTO;
import com.msaproject.models.Cart;
import com.msaproject.models.Inventory;
import com.msaproject.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    private InventoryDTO mapToDTO(Inventory inventory){
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setColor(inventory.getColor());
        inventoryDTO.setSize(inventory.getSize());
        inventoryDTO.setSneaker(inventory.getSneaker());
        return inventoryDTO;
    }

    public List<InventoryDTO> getCartInventory(String userMail){
        Cart userCart = cartRepository.findCartByUser_Email(userMail);
        List<InventoryDTO> inventoryDTOs = userCart
                .getItems()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return inventoryDTOs;
    }
}
