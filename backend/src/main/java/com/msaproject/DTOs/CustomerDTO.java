package com.msaproject.DTOs;

public class CustomerDTO {
    private String username;
    private String email;
    private String address;
    private int phone;

    public CustomerDTO() {
    }

    public CustomerDTO(String username, String email, String address, int phone) {
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
