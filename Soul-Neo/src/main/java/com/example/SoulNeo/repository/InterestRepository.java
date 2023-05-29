package com.example.SoulNeo.repository;

import com.example.SoulNeo.domain.Interest;
import com.example.SoulNeo.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InterestRepository extends Neo4jRepository<Interest, Long> {

    @Query("MATCH (user1:User {email: $userEmail1}), (user2:User {email: $userEmail2}) " +
            "CREATE (user1)-[:Interest]->(user2)")
    List<Interest> createLikeRelationship(String userEmail1, String userEmail2);
//    Optional<Interest> findByUserFromAndUserToNotInterested(User userFrom, User userTo);

    @Query("MATCH (user1:User {email: $userEmail1})-[like:Interest]->(user2:User {email: $userEmail2}) DELETE like")
    List<Interest> deleteLikeRelationship(@Param("userEmail1") String userEmail1, @Param("userEmail2")String userEmail2);

//    void deleteByUserFromAndUserTo(User userFrom, User userTo);
}
