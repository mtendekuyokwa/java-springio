package com.example.lostfound.repository;

import com.example.lostfound.db.ItemDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemDB, Long> {
}
