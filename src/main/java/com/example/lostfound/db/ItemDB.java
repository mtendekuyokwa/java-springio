package com.example.lostfound.db;

import jakarta.persistence.*;
enum LOSTSTATUS {
    LOST,STATUS
}
@Entity
public class ItemDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    LOSTSTATUS itemStatus;
    String description;
    String locationFound;
    String itemName;

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    private String userid;
    public Long getId() {
        return id;
    }

    public LOSTSTATUS getItemStatus() {
        return itemStatus;
    }

    public String getDescription() {
        return description;
    }

    public String getLocationFound() {
        return locationFound;
    }

    public String getUserid() {
        return userid;
    }
}
