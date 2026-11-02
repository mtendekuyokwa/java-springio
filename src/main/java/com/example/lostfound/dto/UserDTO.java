package com.example.lostfound.dto;

import com.example.lostfound.dto.validation.OnCreate;
import com.example.lostfound.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class UserDTO {

    @NotBlank(message = "username cannot be blank",groups = {OnCreate.class})
    @Size(min = 2, max = 100,message = "username too short",groups = {OnCreate.class, OnUpdate.class})
    String username;


    Long id;
    @JsonIgnore
    @NotBlank(message = "password cannot be blank",groups = {OnCreate.class})
            @Size(min = 8,max = 100,message = "password too short",groups =  {OnCreate.class, OnUpdate.class})
     String password;

    public UserDTO(String username, Long id) {
        this.username = username;
        this.id = id;
    }
    public UserDTO() {}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }
}
