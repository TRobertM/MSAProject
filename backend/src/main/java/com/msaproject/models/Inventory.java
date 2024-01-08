package com.msaproject.models;

import jakarta.persistence.*;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sneaker sneaker;

    @ManyToOne
    private Size size;

    @ManyToOne
    private Color color;

    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sneaker getSneaker() {
        return sneaker;
    }

    public Size getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setSneaker(Sneaker sneaker) {
        this.sneaker = sneaker;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(int amount){
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount){
        if(amount > 0 && this.quantity > 0 && this.quantity >= amount){
            quantity -= amount;
        }
    }

    public boolean checkAvailable(){
        if(quantity > 0){
            return true;
        } else return false;
    }
}
