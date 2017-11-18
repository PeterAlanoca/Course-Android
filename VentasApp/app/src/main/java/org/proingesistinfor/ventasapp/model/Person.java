package org.proingesistinfor.ventasapp.model;

import java.io.Serializable;

/**
 * Created by peter on 05-11-17.
 */

public abstract class Person implements Serializable {

    private String fullName;
    private String birthdate;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
