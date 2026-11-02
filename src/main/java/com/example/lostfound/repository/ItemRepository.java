package com.example.lostfound.repository;

import com.example.lostfound.db.Category;
import com.example.lostfound.db.ItemDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemDB, Long> {
    @Query(value = "SELECT i FROM ItemDB i WHERE i.itemName LIKE :itemname " +
            "AND i.location LIKE :location " +
            "AND (:category IS NULL OR i.category = :category)")

    List<ItemDB> getByNameLocationAndCategory(
            @Param("itemname") String itemname,
            @Param("location") String location,
            @Param("category") Category category
    );
}
