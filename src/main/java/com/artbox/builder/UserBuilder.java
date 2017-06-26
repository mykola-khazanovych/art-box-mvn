package com.artbox.builder;

import com.artbox.model.User;

/**
 * Created by mykola.khazanovych on 6/26/2017.
 */
public class UserBuilder {
    private String email;
    private String password;

    public UserBuilder() {
        super();
    }

    public UserBuilder email (String val) {
        this.email = val;
        return this;
    }

    public UserBuilder password (String val) {
        this.password = val;
        return this;
    }


    public User build() {
        return new User(this);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
