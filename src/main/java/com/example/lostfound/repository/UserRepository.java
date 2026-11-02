package com.example.lostfound.repository;

import com.example.lostfound.db.ItemDB;
import com.example.lostfound.db.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDB, Long> {
}
