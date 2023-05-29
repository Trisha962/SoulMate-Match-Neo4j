package com.example.SoulNeo.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("User")
public class User {
    @Id
    private String email;
    private String name;
    private String gender;
    private String city;
    private Integer age;
    private String password;
    private String role;
    private String image;
    private List<Interest> interestList;

    @Relationship(type = "INTEREST", direction = Relationship.Direction.OUTGOING)
    private List<Interest> likes;



}
