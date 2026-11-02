package com.example.lostfound;

import com.example.lostfound.dto.GeneralResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LostAndFoundException extends RuntimeException {
    public LostAndFoundException(String message) {
        super(message);
        printStackTrace();
    }
    public ResponseEntity<GeneralResponseDTO> LostAndFoundException(String message) {
        this.LostAndFoundException(message);
        var dto=new GeneralResponseDTO(HttpStatus.CONFLICT,AppStrings.genericExcpection,"");
       var response= ResponseEntity.status(dto.getHttpStatusCode()).body(dto);
       return response;
    }
}
