package com.msaproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Sneaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String image;
    private double price;

    @OneToMany(mappedBy = "sneaker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Inventory> sneakerInventory = new ArrayList<>();

    public Sneaker(){}

    public Sneaker(String name, String brand, String image, double price){
        this.name = name;
        this.brand = brand;
        this.image = image;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Inventory> getSneakerInventory() {
        return sneakerInventory;
    }

    public void setSneakerInventory(List<Inventory> sneakerInventory) {
        this.sneakerInventory = sneakerInventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sneaker sneaker = (Sneaker) o;
        return Double.compare(sneaker.price, price) == 0 && id.equals(sneaker.id) && name.equals(sneaker.name) && Objects.equals(brand, sneaker.brand) && Objects.equals(image, sneaker.image) && Objects.equals(sneakerInventory, sneaker.sneakerInventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, image, price, sneakerInventory);
    }

    @Override
    public String toString() {
        return "Sneaker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", sneakerInventory=" + sneakerInventory +
                '}';
    }
}
