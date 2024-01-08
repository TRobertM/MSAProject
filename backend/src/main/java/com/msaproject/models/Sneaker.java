package com.msaproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Sneaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String brand;
    @NotNull
    private String image;

    @NotNull
    @Min(1)
    private double price;
    private int discount;

    @NotNull
    @Pattern(regexp = "^(male|female|unisex)$", message = "Gender should be either 'male', 'female', or 'unisex'")
    private String gender;

    @OneToMany(mappedBy = "sneaker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Inventory> sneakerInventory = new ArrayList<>();

    public Sneaker(){}

    public Sneaker(String name, String brand, String image, double price, int discount, String gender){
        this.name = name;
        this.brand = brand;
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.gender = gender;
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sneaker sneaker = (Sneaker) o;
        return Double.compare(sneaker.price, price) == 0 && discount == sneaker.discount && Objects.equals(id, sneaker.id) && Objects.equals(name, sneaker.name) && Objects.equals(brand, sneaker.brand) && Objects.equals(image, sneaker.image) && Objects.equals(gender, sneaker.gender) && Objects.equals(sneakerInventory, sneaker.sneakerInventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, image, price, discount, gender, sneakerInventory);
    }
}
