package com.example.lostfound.controllers;

//import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import com.example.lostfound.AppStrings;
import com.example.lostfound.LostAndFoundException;
import com.example.lostfound.db.ItemDB;
import com.example.lostfound.dto.GeneralResponseDTO;
import com.example.lostfound.repository.ItemRepository;
import com.example.lostfound.services.Utils;
import org.apache.coyote.http2.HpackDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;
    
    @GetMapping("/")
    public ResponseEntity<String> getSomething(){
        return ResponseEntity.ok().body("I am ok");
    }
    
    @PostMapping("/")
    public ResponseEntity<GeneralResponseDTO<ItemDB>> addItem(@RequestBody ItemDB item) throws Throwable {
        try {
          var newItem=  itemRepository.save(item);
           var getItemFromDB=itemRepository.existsById(newItem.getId());
           if (getItemFromDB) {
               return Utils.returnResponse(HttpStatus.CREATED,newItem,AppStrings.itemCreated);

           }
           else {
               return Utils.returnResponse(HttpStatus.CONFLICT,null,AppStrings.failedToCreate);

           }
        }
        catch (Exception e) {

            throw new LostAndFoundException(AppStrings.failedToCreate);
        }

    }
}
