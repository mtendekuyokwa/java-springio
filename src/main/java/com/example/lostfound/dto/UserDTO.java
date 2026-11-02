package com.example.lostfound.dto;

public class UserDTO {
    String username;
    Long id;

    public UserDTO(String username, Long id) {
        this.username = username;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }
}
