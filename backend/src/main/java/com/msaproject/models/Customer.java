package com.msaproject.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @Email
    @NotNull
    private String email;
    private String address;
    private int phone;

    @OneToOne(mappedBy = "cardHolder")
    private Card card;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToOne(mappedBy = "customer")
    private Wishlist wishlist;

    public Customer() {
    }

    public Customer(Long id, String username, String password, String email, String address, int phone, Card card, Cart cart, Wishlist wishlist) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.card = card;
        this.cart = cart;
        this.wishlist = wishlist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return phone == customer.phone && Objects.equals(id, customer.id) && Objects.equals(username, customer.username) && Objects.equals(password, customer.password) && Objects.equals(email, customer.email) && Objects.equals(address, customer.address) && Objects.equals(card, customer.card) && Objects.equals(cart, customer.cart) && Objects.equals(wishlist, customer.wishlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, address, phone, card, cart, wishlist);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", card=" + card +
                ", cart=" + cart +
                ", wishlist=" + wishlist +
                '}';
    }
}
