package com.msaproject.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Sneaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String image;

    @OneToMany(mappedBy = "sneaker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inventory> sneakerInventory;

}
