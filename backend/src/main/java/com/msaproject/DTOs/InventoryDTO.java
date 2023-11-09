package com.msaproject.DTOs;

import com.msaproject.models.Color;
import com.msaproject.models.Size;
import com.msaproject.models.Sneaker;

public class InventoryDTO {
    Sneaker sneaker;
    Color color;
    Size size;

    public InventoryDTO(Sneaker sneaker, Color color, Size size) {
        this.sneaker = sneaker;
        this.color = color;
        this.size = size;
    }

    public InventoryDTO() {
    }

    public Sneaker getSneaker() {
        return sneaker;
    }

    public void setSneaker(Sneaker sneaker) {
        this.sneaker = sneaker;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
