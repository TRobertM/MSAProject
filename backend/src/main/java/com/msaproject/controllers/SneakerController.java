package com.msaproject.controllers;

import com.msaproject.models.Sneaker;
import com.msaproject.services.SneakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<Sneaker> getSneakerById(@PathVariable Long id){
        return sneakerService.getSneakerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
