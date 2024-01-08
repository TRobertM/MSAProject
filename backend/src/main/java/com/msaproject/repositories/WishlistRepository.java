package com.msaproject.repositories;

import com.msaproject.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    public boolean existsByCustomerUsername(String username);
    public Wishlist findWishlistByCustomer_Id(Long id);
}
