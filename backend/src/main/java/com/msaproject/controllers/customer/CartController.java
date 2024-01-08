package com.msaproject.controllers.customer;

import com.msaproject.models.Cart;
import com.msaproject.models.Customer;
import com.msaproject.models.Inventory;
import com.msaproject.models.Wishlist;
import com.msaproject.services.CartService;
import com.msaproject.services.CustomerService;
import com.msaproject.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer/cart")
public class CartController {

    private final CartService cartService;
    private final CustomerService customerService;
    private final InventoryService inventoryService;

    @Autowired
    public CartController(CartService cartService, CustomerService customerService, InventoryService inventoryService){
        this.cartService = cartService;
        this.customerService = customerService;
        this.inventoryService = inventoryService;
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

    @Transactional
    @DeleteMapping("/remove-from-cart")
    public ResponseEntity<String> deleteFromCart(@RequestBody Inventory inventory){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<Customer> customerOptional = customerService.getCustomerByUsername(username);
            if(customerOptional.isPresent()){
                Customer customer = customerOptional.get();
                Cart cart = customer.getCart();
                cart.getItems().removeIf(s -> s.getId().equals(inventory.getId()));
                cartService.addCart(cart);
                return ResponseEntity.ok("Item removed successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wishlist not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error removing item from wishlist");
        }
    }

    @DeleteMapping("/empty-cart")
    public ResponseEntity<String> emptyCart(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<Customer> customerOptional = customerService.getCustomerByUsername(username);
            if(customerOptional.isPresent()){
                Customer customer = customerOptional.get();
                Cart cart = customer.getCart();
                cart.getItems().clear();
                cartService.addCart(cart);
                return ResponseEntity.ok("Cart successfully emptied");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error emptying cart");
        }
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<String> addToCart(@RequestBody Long inventoryId){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            boolean hasCart = cartService.checkUserHasCart(username);

            Optional<Customer> customerOptional = customerService.getCustomerByUsername(username);

            if (!hasCart) {
                if (customerOptional.isPresent()) {
                    Customer customer = customerOptional.get();
                    Cart cart = new Cart();
                    cart.setUser(customer);
                    cartService.addCart(cart);
                }
            }

            Cart cart = customerOptional.orElseThrow(() -> new IllegalStateException("User not found")).getCart();

            Optional<Inventory> inventoryOptional = inventoryService.findInventoryById(inventoryId);

            if (inventoryOptional.isPresent()) {
                Inventory inventory = inventoryOptional.get();

                if (cart.getItems().contains(inventory)) {
                    return ResponseEntity.badRequest().body("Item is already in the cart");
                } else {
                    cart.getItems().add(inventory);
                    cartService.addCart(cart);

                    inventory.setQuantity(inventory.getQuantity() - 1);
                    inventoryService.saveInventory(inventory);

                    return ResponseEntity.ok("Item added to cart successfully");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory not found");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding item to cart");
        }
    }

}
