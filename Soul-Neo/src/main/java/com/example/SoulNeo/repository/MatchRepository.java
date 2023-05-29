package com.example.SoulNeo.repository;

import com.example.SoulNeo.domain.Match;
import com.example.SoulNeo.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends Neo4jRepository<Match, Long> {
//    @Query("MATCH (user1:User {email: $userEmail})-[:Like|Interest]-(user2:User)-[:Like|Interest]-(user1) RETURN user2")
    @Query("MATCH (user1:User {email: $userEmail})-[:Like|Interest]-(user2:User)-[:Like|Interest]-(user1) RETURN user2")
    List<User> findMatches(String userEmail);
}
