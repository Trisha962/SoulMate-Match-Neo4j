package com.example.SoulNeo.repository;
import com.example.SoulNeo.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.List;


@Repository
public interface SoulInterfaceRepository extends Neo4jRepository<User, String> {

    List<User> findByGenderNot(String gender);
    @Query("MATCH (u:User)<-[s:Matched_with]-(m:Match)-[r:Matched_with]->(u2:User) RETURN u, m, u2")
    Collection<User> getAllUsers();
    User findByCity(String city);





//    List<User> findByAgeGreaterThan(int age);
//    @Query("MATCH (u:User)<- [s:soulUsers]-(i:Interest) RETURN u,s,i")
//    @Query("MATCH (u:User) WHERE u.age > $age RETURN u")
//    List<User> findByAge(@Param("age") int age);
}
