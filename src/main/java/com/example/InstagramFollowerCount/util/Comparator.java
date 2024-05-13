package com.example.InstagramFollowerCount.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Component
public class Comparator {
    private HashMap<String, String> followersMap;
    private HashMap<String, String> followingMap;

    public void createCommonMap(JsonArrays jsonArray) {
        followingMap = valueHrefExtractor(jsonArray.getFollowingArray(), " follows you");
        followersMap = valueHrefExtractor(jsonArray.getFollowersArray(), " you follow");

        Set<String> followingSet = new HashSet<>(followingMap.keySet());
        Set<String> followersSet = new HashSet<>(followersMap.keySet());

        // Find the difference (elements in followingSet but not in followersSet)
        Set<String> difference = new HashSet<>(followingSet);
        difference.removeAll(followersSet);

        System.out.println("Users you follow who don't follow you: " + difference);
    }

    private HashMap<String, String> valueHrefExtractor(JSONArray jsonArray, String follow) {
        HashMap<String, String> arrayToMap = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject stringListData = jsonArray.getJSONObject(i).getJSONArray("string_list_data").getJSONObject(0);
            String href = stringListData.getString("href");
            String value = stringListData.getString("value");
            arrayToMap.put(value, href);
        }
        return arrayToMap;
    }
}
