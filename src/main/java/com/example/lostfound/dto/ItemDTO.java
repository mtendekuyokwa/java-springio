package com.example.lostfound.dto;

import com.example.lostfound.db.LOSTSTATUS;
import com.example.lostfound.dto.validation.OnCreate;
import com.example.lostfound.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ItemDTO {
    @NotBlank(message = "name cannot be blank",groups =  {OnCreate.class})
            @Size(min = 3, max = 30, message = "name is too short or too long",groups = {OnCreate.class, OnUpdate.class})
    String name;

    Long id;

    @NotBlank(message = "Description cannot be blank",groups = {OnCreate.class})
            @Size(min = 8, max = 150,message = "Description too short or too long",groups = {OnCreate.class, OnUpdate.class})
    String description;

    @NotNull(message = "item status not set",groups = {OnCreate.class})
    LOSTSTATUS itemStatus;

    @NotBlank(message = "location not set",groups = {OnCreate.class})
    @Size(min = 2,max=150,groups =  {OnCreate.class,OnUpdate.class})
    String location;

    Long userid;

    public Long getUserid() {
        return userid;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;

    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LOSTSTATUS getItemStatus() {
        return itemStatus;
    }
}
