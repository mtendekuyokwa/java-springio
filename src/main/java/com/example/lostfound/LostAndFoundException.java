package com.example.lostfound;

import com.example.lostfound.dto.GeneralResponseDTO;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class LostAndFoundException extends RuntimeException {
    public LostAndFoundException(String message) {
        super(message);
    }
}

