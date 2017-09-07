package org.tensorflow.demo.model;

import com.google.firebase.database.Exclude;

public class User {

    // ------------------------- ATTRIBUTES --------------------------

    private String id;
    private String name;
    private String email;

    // ------------------------- CONSTRUCTOR -------------------------

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // ---------------------- GETTERS & SETTERS ----------------------

    @Exclude    // The Id is the key of the table, no need to store it again
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
