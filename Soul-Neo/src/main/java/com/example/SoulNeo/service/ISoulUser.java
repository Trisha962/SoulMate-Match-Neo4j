package com.example.SoulNeo.service;

import com.example.SoulNeo.domain.User;
import com.example.SoulNeo.exception.userNotFound;

import java.util.Collection;
import java.util.List;

public interface ISoulUser {

     List<User> getUsersByCity(String city);

    Collection<User> getAll();
    public  User saveUser(User user);
    List<User> findByGenderNot(String gender) ;
     void createLikeRelationship(String userEmail1, String userEmail2);

     void unlikeUser(User userFrom, User userTo);
//    User findMatch(String userEmail);
//
//    User getUserById(String userFromId);

  User getUserById(String userToId);
    List<User> findMatches(String userEmail);

}
