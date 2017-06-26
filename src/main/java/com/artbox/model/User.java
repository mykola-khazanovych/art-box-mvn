package com.artbox.model;

import com.artbox.builder.UserBuilder;

/**
 * Created by mykola.khazanovych on 6/26/2017.
 */
public class User {
    private String email;
    private String password;

    public User( UserBuilder builder ) {
        this.email = builder.getEmail();
        this.password = builder.getPassword();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User's email: \"" + email;
    }
}
