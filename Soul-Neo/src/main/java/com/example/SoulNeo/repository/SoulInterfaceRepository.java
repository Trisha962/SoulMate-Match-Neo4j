package com.example.SoulNeo.repository;

import com.example.SoulNeo.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.List;


@EnableNeo4jRepositories
public interface SoulInterfaceRepository extends Neo4jRepository<User, String> {
    List<User> findByGender(String gender);
    List<User> findByAgeGreaterThan(int age);
}
