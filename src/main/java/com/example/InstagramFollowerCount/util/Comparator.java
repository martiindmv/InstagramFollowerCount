package com.example.InstagramFollowerCount.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Comparator {
    Set<String> difference;

    public Set<String> createCommonMap(JsonArrays jsonArray) {
        Set<String> followersSet = valueHrefExtractor(jsonArray.getWhoFollowsYouArray());
        Set<String> followingSet = valueHrefExtractor(jsonArray.getWhoYouAreFollowingArray());

        // Find the difference (elements in followingSet but not in followersSet)
        difference = new HashSet<>(followingSet);
        difference.removeAll(followersSet);

        System.out.println("Users you follow who don't follow you: " + difference);
        return difference;
    }

    private Set<String> valueHrefExtractor(JSONArray jsonArray) {
        Set<String> arrayToSet = new HashSet<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject stringListData = jsonArray.getJSONObject(i).getJSONArray("string_list_data").getJSONObject(0);
            String value = stringListData.getString("value");
            arrayToSet.add(value);
        }
        return arrayToSet;
    }

    public Set<String> getDifference() {
        return difference;
    }
}
