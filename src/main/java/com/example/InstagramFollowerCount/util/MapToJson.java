package com.example.InstagramFollowerCount.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MapToJson {
    public JSONArray mapFileToJsonArray(String jsonContent) {
        JSONArray jsonArray = new JSONArray(jsonContent);
        System.out.println("Followers Completed!");
        return jsonArray;
    }

    public JSONArray mapFileWithKeyToJsonArray(String jsonContent) {
        JSONObject jsonObject = new JSONObject(jsonContent);
        if (jsonObject.has("relationships_following")) {
            JSONArray jsonArray = jsonObject.getJSONArray("relationships_following");
            System.out.println("Following Completed!");
            return jsonArray;
        } else {
            System.out.println("Key of the file is not correct!");
        }
        return null;
    }
}
