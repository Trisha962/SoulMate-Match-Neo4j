package com.example.ProductByUser.rabbit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;


@Data
@NoArgsConstructor
@AllArgsConstructor
//this class represents a ProductDTO that contains the product data as a JSONObject.
// It is used for transferring product information between different components of the system
public class UserDto {
    private JSONObject jsonObject;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
