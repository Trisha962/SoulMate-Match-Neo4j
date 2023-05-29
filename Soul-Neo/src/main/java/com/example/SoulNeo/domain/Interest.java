package com.example.SoulNeo.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.time.LocalDateTime;

//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Node(labels = {"Interested"})
//public class Interest {
//    @Id
//    @GeneratedValue
//    private Long Id;
//    @Relationship(type = "HAS_INTEREST", direction = Relationship.Direction.INCOMING)
//    private User userFrom;
//    @Relationship(type = "INTERESTED_IN", direction = Relationship.Direction.OUTGOING)
//    private User userTo;
//    private LocalDateTime localDateTime;
//}
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
//@Node(labels = {"Like"})
//@RelationshipProperties
@RelationshipEntity(type = "LIKE")
public class Interest {

    @Id
    @GeneratedValue
    private Long id;
  @Property(name = "user_from")

    @StartNode

    private User userFrom;

    @Property(name = "user_to")

    @EndNode
    private User userTo;

    private LocalDateTime timestamp;
}