package com.msaproject.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;
    private int cvv;
    private LocalDate expiration;

    @OneToOne
    private Customer cardHolder;

    public Card(Long id, int number, int cvv, LocalDate expiration, Customer cardHolder) {
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number && cvv == card.cvv && Objects.equals(id, card.id) && Objects.equals(expiration, card.expiration) && Objects.equals(cardHolder, card.cardHolder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, cvv, expiration, cardHolder);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number=" + number +
                ", cvv=" + cvv +
                ", expiration=" + expiration +
                ", cardHolder=" + cardHolder +
                '}';
    }
}
