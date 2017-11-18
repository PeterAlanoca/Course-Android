package org.proingesistinfor.ventasapp.model;

import java.io.Serializable;

/**
 * Created by peter on 05-11-17.
 */

public class Ad implements Serializable {
    private int id;
    private int idUser;
    private String name;
    private String description;
    private double price;
    private String image;

    public Ad() {
    }

    public Ad(int id, int idUser, String name, String description, double price, String image) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Ad(int idUser, String name, String description, double price, String image) {
        this.idUser = idUser;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
