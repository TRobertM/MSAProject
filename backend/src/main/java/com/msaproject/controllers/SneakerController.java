package com.msaproject.controllers;

import com.msaproject.models.Sneaker;
import com.msaproject.services.SneakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sneakers")
public class SneakerController {

    private final SneakerService sneakerService;

    @Autowired
    public SneakerController(SneakerService sneakerService){
        this.sneakerService = sneakerService;
    }

    @GetMapping
    public List<Sneaker> getAllSneakers() {
        return sneakerService.getAllSneakers();
    }

    @GetMapping("/{id}")
    public Optional<Sneaker> getSneakerById(@PathVariable Long id){
        return sneakerService.getSneakerById(id);
    }

    @PostMapping
    public Sneaker createSneaker(@RequestBody Sneaker sneaker) {
        return sneakerService.createSneaker(sneaker);
    }

    @DeleteMapping("/{id}")
    public void deleteSneaker(@PathVariable Long id) {
        sneakerService.deleteSneaker(id);
    }
}
