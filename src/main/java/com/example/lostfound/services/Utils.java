package com.example.lostfound.services;

import com.example.lostfound.db.UserDB;
import com.example.lostfound.dto.GeneralResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utils {
    public static  <T> ResponseEntity<GeneralResponseDTO<T>> returnResponse(HttpStatus status, T data, String message) {
        return ResponseEntity.status(status).body(new GeneralResponseDTO<T>(status, message, data));
    }

}