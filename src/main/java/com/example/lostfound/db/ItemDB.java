package com.example.lostfound.db;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class ItemDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    LOSTSTATUS itemStatus;
    String description;
    String location;
    String itemName;
    Category category;
    RecoveryStatus  recoveryStatus=RecoveryStatus.UNRECOVERED;

    public RecoveryStatus getRecoveryStatus() {
        return recoveryStatus;
    }

    public void setRecoveryStatus(RecoveryStatus recoveryStatus) {
        this.recoveryStatus = recoveryStatus;
    }

    public ItemDB(LOSTSTATUS itemStatus, String description, String location, String itemName, Long userid, Category category) {
        this.itemStatus = itemStatus;
        this.description = description;
        this.location = location;
        this.itemName = itemName;
        this.userid = userid;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public ItemDB() {

    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    private Long userid;
    public Long getId() {
        return id;
    }

    public LOSTSTATUS getItemStatus() {
        return itemStatus;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public Long getUserid() {
        return userid;
    }
}
