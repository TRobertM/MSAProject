package com.msaproject.controllers;

import com.msaproject.models.Inventory;
import com.msaproject.models.Sneaker;
import com.msaproject.services.SneakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/p/sneakers")
public class SneakerController {

    private final SneakerService sneakerService;

    @Autowired
    public SneakerController(SneakerService sneakerService){
        this.sneakerService = sneakerService;
    }

    @GetMapping
    public ResponseEntity<List<Sneaker>> getSneakers(){
        return ResponseEntity.ok(sneakerService.getAllSneakers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Inventory>> getSneakerVariants(@PathVariable Long id){
        return ResponseEntity.ok(sneakerService.getSneakerVariants(id));
    }

    @GetMapping("/i/{id}")
    public ResponseEntity<Sneaker> getSneakerById(@PathVariable Long id){
        Optional<Sneaker> sneaker = sneakerService.getSneakerById(id);
        if(sneaker.isPresent()){
            Sneaker returnedSneaker = sneaker.get();
            return ResponseEntity.ok(returnedSneaker);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/g/{gender}")
    public ResponseEntity<List<Sneaker>> getSneakersByGender(@PathVariable String gender){
        return ResponseEntity.ok(sneakerService.getSneakersByGender(gender));
    }
}
