package com.msaproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Digits(integer = 16, fraction = 0)
    @NotNull
    private Long number;

    @Digits(integer = 3, fraction = 0)
    @NotNull
    private int cvv;

    @Future
    private LocalDate expiration;

    @OneToOne
    @NotNull
    private Customer cardHolder;

    public Card(Long id, Long number, int cvv, LocalDate expiration, Customer cardHolder) {
        this.id = id;
        this.number = number;
        this.cvv = cvv;
        this.expiration = expiration;
        this.cardHolder = cardHolder;
    }

    public Card() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public Customer getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(Customer cardHolder) {
        this.cardHolder = cardHolder;
    }
}
