package com.msaproject.controllers.customer;


import com.msaproject.models.*;
import com.msaproject.services.CustomerService;
import com.msaproject.services.InventoryService;
import com.msaproject.services.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/customer/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final CustomerService customerService;
    private final InventoryService inventoryService;

    public OrdersController(OrdersService ordersService, CustomerService customerService, InventoryService inventoryService){
        this.ordersService = ordersService;
        this.customerService = customerService;
        this.inventoryService = inventoryService;
    }

    @PostMapping("/add-to-orders")
    public ResponseEntity<String> addToWishlist(@RequestBody List<Long> inventoryIds){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<Customer> customerOptional = customerService.getCustomerByUsername(username);
            if(customerOptional.isPresent()){
                Customer customer = customerOptional.get();
                double totalCost = 0;
                Orders orders = new Orders();
                for(int i = 0; i < inventoryIds.size(); i++){
                    Optional<Inventory> inventory = inventoryService.findInventoryById(inventoryIds.get(i));
                    totalCost += inventory.get().getSneaker().getPrice();
                    orders.getItems().add(inventory.get());
                }
                orders.setCost(totalCost);
                orders.setCustomer(customer);
                ordersService.updateOrder(orders);
                return ResponseEntity.ok("Item added to wishlist successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wishlist not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error placing order");        }
    }
}
