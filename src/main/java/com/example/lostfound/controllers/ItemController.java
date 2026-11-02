package com.example.lostfound.controllers;

//import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import com.example.lostfound.AppStrings;
import com.example.lostfound.LostAndFoundException;
import com.example.lostfound.db.Category;
import com.example.lostfound.db.ItemDB;
import com.example.lostfound.db.RecoveryStatus;
import com.example.lostfound.dto.GeneralResponseDTO;
import com.example.lostfound.dto.ItemDTO;
import com.example.lostfound.dto.RecoveryDTO;
import com.example.lostfound.dto.validation.OnCreate;
import com.example.lostfound.repository.ItemRepository;
import com.example.lostfound.repository.UserRepository;
import com.example.lostfound.services.Utils;
import com.sun.net.httpserver.HttpsConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<GeneralResponseDTO<List<ItemDTO>>> getItems(){
        try {
            var allItems = itemRepository.findAll().stream().map(item -> new ItemDTO(item.getItemName(),item.getId(),item.getDescription(),item.getItemStatus(),item.getLocation(),item.getUserid(),item.getCategory())).toList();
            return Utils.returnResponse(HttpStatus.OK,allItems,AppStrings.allItemsRetrieved);

        }
        catch (Exception e) {
            throw  new LostAndFoundException(AppStrings.failedToGetAll);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponseDTO<List<ItemDTO>>> getItem(@PathVariable Long id){
        try {
            itemRepository.deleteById(id);
            return Utils.returnResponse(HttpStatus.OK,null,AppStrings.deleteConfirmed);

        }
        catch (Exception e) {
            throw  new LostAndFoundException(AppStrings.failedToDelete);
        }

    }
    @PutMapping("/{id}/recover")
    public ResponseEntity<GeneralResponseDTO<ItemDTO>> modifyItem(@PathVariable Long id, @RequestBody RecoveryDTO recoveryStatus){
        try {
          var item=  itemRepository.findById(id).map(itemDB -> {
                itemDB.setRecoveryStatus(recoveryStatus.getRecoveryStatus());
            itemRepository.save(itemDB);
            return Utils.returnResponse(HttpStatus.OK,new ItemDTO(itemDB.getItemName(),itemDB.getId(),itemDB.getDescription(),itemDB.getItemStatus(),itemDB.getLocation(),itemDB.getUserid(),itemDB.getCategory()),AppStrings.dataUpated);
            }).orElse(Utils.returnResponse(HttpStatus.CONFLICT,null,AppStrings.failedToUpdate));
return item;
        }
        catch (Exception e) {
            throw new LostAndFoundException(AppStrings.failetoPatch);
        }
    }

    @GetMapping("search")
    public ResponseEntity<GeneralResponseDTO<List<ItemDB>>> searchItem(
            @RequestParam(defaultValue = "%") String location,
            @RequestParam(defaultValue = "ALL") Category category,
            @RequestParam (defaultValue = "%") String name
    ){
try {
    //TODO: convert the itemdb to itemdto
var items=itemRepository.getByNameLocationAndCategory(name,location, category==Category.ALL?null:category);
return Utils.returnResponse(HttpStatus.OK,items,AppStrings.allItemsRetrieved);
}
catch (Exception e) {
    throw new LostAndFoundException(AppStrings.failedToSearch);
}
    }
    @PostMapping("/")
    public ResponseEntity<GeneralResponseDTO<ItemDB>> addItem(@Validated(OnCreate.class) @RequestBody ItemDTO item) {
        if (!userRepository.existsById(item.getUserid())){
            throw new LostAndFoundException(AppStrings.userNotFound);
        }
        try {
          var newItem=  itemRepository.save(new ItemDB(item.getItemStatus(),item.getDescription(),item.getLocation(),item.getName(),item.getUserid(),item.getCategory()));
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
