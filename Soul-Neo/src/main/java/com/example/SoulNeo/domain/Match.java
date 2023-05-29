package com.example.SoulNeo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Node(labels = {"Match"})
@RelationshipProperties

public class Match {
    @Id
    @GeneratedValue
    private Long id;
    @Relationship(type = "Matched_with")

    private User userEmail1;
    @Relationship(type = "Matched_with")

    private User UserEmail2;
    private LocalDateTime localDateTime;


}
