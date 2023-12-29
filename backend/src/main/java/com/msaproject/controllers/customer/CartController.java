package com.msaproject.controllers.customer;

import com.msaproject.models.Cart;
import com.msaproject.models.Customer;
import com.msaproject.models.Inventory;
import com.msaproject.services.CartService;
import com.msaproject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/customer/cart")
public class CartController {

    private final CartService cartService;
    private final CustomerService customerService;

    @Autowired
    public CartController(CartService cartService, CustomerService customerService){
        this.cartService = cartService;
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getCart(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean exists = cartService.checkUserHasCart(username);
        Optional<Customer> customerOptional = customerService.getCustomerByUsername(username);
        if(!exists){
            if(customerOptional.isPresent()){
                Customer customer = customerOptional.get();
                Cart cart = new Cart();
                cart.setUser(customer);
                cartService.addCart(cart);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        else {
            if(customerOptional.isPresent()){
                Cart cart = cartService.findCartByCustomer(customerOptional.get().getId()).orElse(null);
                List<Inventory> inventories = cart.getItems();
                return ResponseEntity.ok(inventories);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
    }
}
