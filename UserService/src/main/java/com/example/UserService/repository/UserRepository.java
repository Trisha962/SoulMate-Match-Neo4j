package com.example.UserService.repository;

import com.example.UserService.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//extended jpa repository
//datatype of id is string
@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findByEmailAndPassword(String email,String password);
}
