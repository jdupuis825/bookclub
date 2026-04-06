/*
    Walls, C. (2019). Spring In Action (5th ed.). Shelter Island, NY: Manning Publications.
        Modified by Terrence Galamison (2023)
    Dupuis, J. (2025). CSI 505 Intermediate Java Programming. Bellevue University. Modified by A. Student 2025.
*/

package com.example.wishlist.model;

public class WishListItem {

    private int id;
    private String name;
    private String description;
    private double price;
    private boolean purchased;
    private int ownerId;   // ID of the user who owns this wishlist item

    public WishListItem() {
    }

    public WishListItem(int id, String name, String description, double price, boolean purchased, int ownerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.purchased = purchased;
        this.ownerId = ownerId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public int getOwnerId() {
        return ownerId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "WishListItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", purchased=" + purchased +
                ", ownerId=" + ownerId +
                '}';
    }
}
