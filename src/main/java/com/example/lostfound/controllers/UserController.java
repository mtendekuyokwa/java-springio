package com.example.lostfound.controllers;

import com.example.lostfound.AppStrings;
import com.example.lostfound.LostAndFoundException;
import com.example.lostfound.db.UserDB;
import com.example.lostfound.dto.GeneralResponseDTO;
import com.example.lostfound.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/")
    ResponseEntity<GeneralResponseDTO<UserDB>>  addUser(@RequestBody UserDB userDB) {
        try {
            var newuser=userRepository.save(userDB);
            var addedNewUser=userRepository.existsById(newuser.getId());
            if (addedNewUser) {
                return ResponseEntity.status(HttpStatus.CREATED).body(new GeneralResponseDTO<>(HttpStatus.CREATED, AppStrings.userCreated,newuser));

            }
        }
        catch (Exception e) {
            throw new LostAndFoundException(e.getMessage());

        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new GeneralResponseDTO<>(HttpStatus.CONFLICT, AppStrings.failedToCreate, null));
    }

    @RequestMapping("/{id}")
    ResponseEntity<GeneralResponseDTO<UserDB> getUser(@PathVariable Long id){

    }
    }



