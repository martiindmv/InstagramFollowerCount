package com.example.InstagramFollowerCount.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONOperations {
    public String getJsonObjectKey(String jsonContent) {
        JSONObject jsonObject = new JSONObject(jsonContent);
        if (jsonObject.has("relationships_following")) {
            return "relationships_following";
        }
        return null;

    }

    public String readFileArray(String jsonContent) {
        JSONArray jsonArray = new JSONArray(jsonContent);
        valueHrefExtractor(jsonArray);
        System.out.println("Followers Completed!");
        return "test";
    }

    public String readFileWithKey(String jsonContent, String jsonKey) {
        if (jsonKey.equals("relationships_following")) {
            JSONObject jsonObject = new JSONObject(jsonContent);
            JSONArray jsonArray = jsonObject.getJSONArray(jsonKey);
            valueHrefExtractor(jsonArray);
        } else {
            System.out.println("Key of the file is not correct!");
        }
        System.out.println("Following Completed!");
        return "test";
    }

    private static void valueHrefExtractor(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject stringListData = jsonArray.getJSONObject(i).getJSONArray("string_list_data").getJSONObject(0);
            String href = stringListData.getString("href");
            String value = stringListData.getString("value");

            System.out.println("Href: " + href + ", Value: " + value);
        }
    }
}
