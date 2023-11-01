package com.msaproject.models;

import jakarta.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventory_id;

    @ManyToOne
    private Sneaker sneaker;

    @ManyToOne
    private Size size;

    @ManyToOne
    private Color color;

    private int quantity;
}
