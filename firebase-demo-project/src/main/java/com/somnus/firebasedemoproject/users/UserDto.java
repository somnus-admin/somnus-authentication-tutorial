package com.somnus.firebasedemoproject.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserDto implements Serializable {

    private int id;
    private String username;
    private String displayName;
    private String email;

    private UserDto() {}

    public UserDto(Integer id, String username, String email, String displayName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }
}
