package com.msaproject.controllers.customer;

import com.msaproject.models.*;
import com.msaproject.services.CustomerService;
import com.msaproject.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;
    private final CustomerService customerService;

    @Autowired
    public WishlistController(WishlistService wishlistService, CustomerService customerService){
        this.wishlistService = wishlistService;
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Sneaker>> getWishlist(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean exists = wishlistService.checkExists(username);
        Optional<Customer> customerOptional = customerService.getCustomerByUsername(username);
        if(!exists){
            if(customerOptional.isPresent()){
                Customer customer = customerOptional.get();
                Wishlist wishlist = new Wishlist();
                wishlist.setCustomer(customer);
                wishlistService.updateWishlist(wishlist);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        else {
            if(customerOptional.isPresent()){
                Wishlist wishlist = wishlistService.findWishlistByUser(customerOptional.get().getId());
                List<Sneaker> sneakers = wishlist.getSneakers();
                return ResponseEntity.ok(sneakers);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
    }

    @PostMapping("/add-to-wishlist")
    public ResponseEntity<String> addToWishlist(@RequestBody Sneaker sneaker){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            boolean exists = wishlistService.checkExists(username);

            Optional<Customer> customerOptional = customerService.getCustomerByUsername(username);

            if(!exists){
                if(customerOptional.isPresent()){
                    Customer customer = customerOptional.get();
                    Wishlist wishlist = new Wishlist();
                    wishlist.setCustomer(customer);
                    wishlistService.updateWishlist(wishlist);
                }
            }

            if(customerOptional.isPresent()){
                Customer customer = customerOptional.get();
                Wishlist wishlist = customer.getWishlist();
                wishlist.getSneakers().add(sneaker);
                wishlistService.updateWishlist(wishlist);
                return ResponseEntity.ok("Item added to wishlist successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wishlist not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error placing order");        }
    }

    @DeleteMapping("/remove-from-wishlist")
    public ResponseEntity<String> removeFromWishlist(@RequestBody Sneaker sneaker){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            Optional<Customer> customerOptional = customerService.getCustomerByUsername(username);
            if(customerOptional.isPresent()){
                Customer customer = customerOptional.get();
                Wishlist wishlist = customer.getWishlist();
                wishlist.getSneakers().removeIf(s -> s.getId().equals(sneaker.getId()));
                wishlistService.updateWishlist(wishlist);
                return ResponseEntity.ok("Item removed successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wishlist not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error removing item from wishlist");
        }
    }
}
