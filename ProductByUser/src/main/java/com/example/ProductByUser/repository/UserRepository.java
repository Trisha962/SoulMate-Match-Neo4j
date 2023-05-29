package com.example.ProductByUser.repository;

import com.example.ProductByUser.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

 public Optional<User> findByName(String name);
 List<User> findByNameContainingIgnoreCase(String name);
}
