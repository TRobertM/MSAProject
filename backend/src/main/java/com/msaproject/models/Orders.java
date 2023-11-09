package com.msaproject.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double cost;

    @ManyToMany
    @JoinTable(
            name = "customer_order",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_inventory",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "inventory_id")
    )
    private List<Inventory> items = new ArrayList<>();

    public Orders() {
    }

    public Orders(Long id, double cost, List<Customer> customers, List<Inventory> items) {
        this.id = id;
        this.cost = cost;
        this.customers = customers;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Inventory> getItems() {
        return items;
    }

    public void setItems(List<Inventory> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", cost=" + cost +
                ", customers=" + customers +
                ", items=" + items +
                '}';
    }
}
