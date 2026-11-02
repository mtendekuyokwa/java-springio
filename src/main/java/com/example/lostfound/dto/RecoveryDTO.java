package com.example.lostfound.dto;

import com.example.lostfound.db.RecoveryStatus;

public class RecoveryDTO {
    private RecoveryStatus recoveryStatus;

    public RecoveryDTO() {}

    public RecoveryStatus getRecoveryStatus() {
        return recoveryStatus;
    }

    public void setRecoveryStatus(RecoveryStatus recoveryStatus) {
        this.recoveryStatus = recoveryStatus;
    }
}
