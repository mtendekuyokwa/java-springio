package com.example.lostfound;

import com.example.lostfound.dto.GeneralResponseDTO;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LostAndFoundExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GeneralResponseDTO<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        var errorBody = new GeneralResponseDTO<>(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LostAndFoundException.class)
    public ResponseEntity<GeneralResponseDTO<Object>> handleLostAndFoundException(LostAndFoundException ex) {
        var errorBody = new GeneralResponseDTO<>(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponseDTO<Object>> handleGeneralException(Throwable ex) {
        var errorBody = new GeneralResponseDTO<>(
                HttpStatus.BAD_REQUEST,
                ex.getMessage().split(";")[ex.getMessage().split(";").length-1].split("\\[")[1].split("\\]")[0],
                null
        );
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }
}
