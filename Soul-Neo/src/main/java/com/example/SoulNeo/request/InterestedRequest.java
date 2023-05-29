package com.example.SoulNeo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class InterestedRequest {
    private String userFromId;
    private String userToId;
}
