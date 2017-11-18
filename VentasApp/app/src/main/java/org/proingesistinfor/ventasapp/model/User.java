package org.proingesistinfor.ventasapp.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by peter on 05-11-17.
 */

public class User extends Person implements Serializable {

    private int id;
    private String email;

    private String username;
    private String password;
    private String photo;
    private String cover;

    public User(String fullName, String birthdate, String email, String username, String password, String photo, String cover) {
        setFullName(fullName);
        setBirthdate(birthdate);
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.cover = cover;
        this.email = email;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public String getCover() {
        return cover;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setCover(String cover) {
        this.cover = cover;
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
}
