package com.msaproject.services;

import com.msaproject.models.Customer;
import com.msaproject.models.Wishlist;
import com.msaproject.repositories.WishlistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository){
        this.wishlistRepository = wishlistRepository;
    }

    public void updateWishlist(Wishlist wishlist){
        wishlistRepository.save(wishlist);
    }

    public boolean checkExists(String username){
        return wishlistRepository.existsByCustomerUsername(username);
    }

    public Wishlist findWishlistByUser(Long id){
        return wishlistRepository.findWishlistByCustomer_Id(id);
    }
}
