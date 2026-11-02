package com.example.lostfound.controllers;

import com.example.lostfound.AppStrings;
import com.example.lostfound.LostAndFoundException;
import com.example.lostfound.db.UserDB;
import com.example.lostfound.dto.GeneralResponseDTO;
import com.example.lostfound.dto.UserDTO;
import com.example.lostfound.repository.UserRepository;
import com.example.lostfound.services.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/")
    ResponseEntity<GeneralResponseDTO<UserDTO>> addUser(@RequestBody UserDB userDB) {
        try {
            var newuser=userRepository.save(userDB);
            var addedNewUser=userRepository.existsById(newuser.getId());
            if (addedNewUser) {
                return ResponseEntity.status(HttpStatus.CREATED).body(new GeneralResponseDTO<>(HttpStatus.CREATED, AppStrings.userCreated,new UserDTO(newuser.getUsername(),newuser.getId())));

            }
        }
        catch (Exception e) {
            throw new LostAndFoundException(e.getMessage());

        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new GeneralResponseDTO<>(HttpStatus.CONFLICT, AppStrings.failedToCreate, null));
    }
@PutMapping("/{id}")
ResponseEntity<GeneralResponseDTO<UserDTO>> updateUser(@PathVariable Long id, @RequestBody UserDB userDB) {

            var user=userRepository.findById(id).map(existingProduct->{
                existingProduct.setUsername(userDB.getUsername());
                existingProduct.setPassword(userDB.getUsername());
                userRepository.save(existingProduct);
                return Utils.returnResponse(HttpStatus.OK,new UserDTO(existingProduct.getUsername(), existingProduct.getId()),AppStrings.userUpdated);
            }).orElse(Utils.returnResponse(HttpStatus.NOT_FOUND,  null,AppStrings.failedToUpdate));



        return Utils.returnResponse(HttpStatus.CONFLICT,null,AppStrings.failedToUpdate);
}
@DeleteMapping("/{id}")
ResponseEntity<GeneralResponseDTO<String>>  deleteUser(@PathVariable Long id){
        try {
            var  deletedUser=userRepository.existsById(id);
            if (deletedUser) {
                return Utils.returnResponse(HttpStatus.ACCEPTED,null,AppStrings.userDeleted);
            }
            else {
                return Utils.returnResponse(HttpStatus.CONFLICT,null,AppStrings.failedToDelete);
            }

        } catch (RuntimeException e) {
            throw new LostAndFoundException(AppStrings.failedToDelete);
        }
}
    @RequestMapping("/{id}")
    ResponseEntity<GeneralResponseDTO<UserDTO>> getUser(@PathVariable Long id){
        try {
            var user=userRepository.getOne(id);

            return Utils.returnResponse(HttpStatus.FOUND,new UserDTO(user.getUsername(),user.getId()
            ),AppStrings.userFound);
        }
        catch (Exception e) {
            throw new LostAndFoundException(AppStrings.userNotFound);

        }



    }
    }



