package com.msaproject.services;

import com.msaproject.models.Cart;
import com.msaproject.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public boolean checkUserHasCart(String username){
        return cartRepository.existsByUserUsername(username);
    }

    public Optional<Cart> findCartByCustomer(Long id){
        return cartRepository.findByUser_Id(id);
    }

    public void addCart(Cart cart){
        cartRepository.save(cart);
    }
}
