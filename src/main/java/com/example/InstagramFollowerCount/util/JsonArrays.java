package com.example.InstagramFollowerCount.util;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Component
public class JsonArrays {
    private JSONArray jsonArrayFollowers;
    private JSONArray jsonArrayFollowing;

    public JSONArray getFollowersArray() {
        return jsonArrayFollowers;
    }

    public JSONArray getFollowingArray() {
        return jsonArrayFollowing;
    }

    public int getFollowersArraySize() {
        return jsonArrayFollowers.length();
    }

    public int getFollowingArraySize() {
        return jsonArrayFollowing.length();
    }

    public void setArrayFollowers(JSONArray jsonArrayFollowers) {
        System.out.println("setArrayFollowers is working!");
        this.jsonArrayFollowers = jsonArrayFollowers;
    }

    public void setArrayFollowing(JSONArray jsonArrayFollowing) {
        System.out.println("setArrayFollowing is working!");
        this.jsonArrayFollowing = jsonArrayFollowing;
    }
}
