package com.msaproject.controllers.admin;

import com.msaproject.models.Sneaker;
import com.msaproject.services.SneakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/private/sneakers")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SneakerAdminController {

    private final SneakerService sneakerService;

    @Autowired
    public SneakerAdminController(SneakerService sneakerService){
        this.sneakerService = sneakerService;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSneaker(@PathVariable Long id){
        sneakerService.deleteSneaker(id);
        return ResponseEntity.ok("Deletion successful");
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSneaker(@RequestBody Sneaker sneaker){
        try {
            sneakerService.addSneaker(sneaker);
            return ResponseEntity.ok("Sneaker created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create sneaker: " + e.getMessage());
        }
    }
}
