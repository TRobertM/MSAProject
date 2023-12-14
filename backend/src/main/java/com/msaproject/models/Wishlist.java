package com.msaproject.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "wishlist_sneaker",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "sneaker_id")
    )
    private List<Sneaker> sneakers = new ArrayList<>();

    public Wishlist() {
    }

    public Wishlist(Long id, Customer customer, List<Sneaker> sneakers) {
        this.id = id;
        this.customer = customer;
        this.sneakers = sneakers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Sneaker> getSneakers() {
        return sneakers;
    }

    public void setSneakers(List<Sneaker> sneakers) {
        this.sneakers = sneakers;
    }
}
