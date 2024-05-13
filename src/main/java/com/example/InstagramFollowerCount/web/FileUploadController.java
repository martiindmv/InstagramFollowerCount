package com.example.InstagramFollowerCount.web;

import com.example.InstagramFollowerCount.util.Comparator;
import com.example.InstagramFollowerCount.util.JsonArrays;
import com.example.InstagramFollowerCount.util.MapToJson;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Controller
public class FileUploadController {
    @Autowired
    private JsonArrays jsonArrays;
    @Autowired
    private MapToJson mapToJson;
    @Autowired
    private Comparator comparator;

    @PostMapping("/uploadFollowers")
    public String handleFollowersList(@RequestParam("followers") MultipartFile file) throws IOException {
        String contentFollowers = new String(file.getInputStream().readAllBytes());

        //Mapping the file to a JSON object
        JSONArray followersArray = mapToJson.mapFileToJsonArray(contentFollowers);

        //Assign an object in JSONArrays file in order to compare after
        jsonArrays.setArrayFollowers(followersArray);

        return "redirect:/";
    }

    @PostMapping("/uploadFollowing")
    public String handleFollowingList(@RequestParam("following") MultipartFile file) throws IOException {
        String contentFollowing = new String(file.getInputStream().readAllBytes());

        //Mapping the file to a JSON object
        JSONArray followingArray = mapToJson.mapFileWithKeyToJsonArray(contentFollowing);

        //Assign an object in JSONArrays file in order to compare after
        jsonArrays.setArrayFollowing(followingArray);

        comparator.createCommonMap(jsonArrays);

        return "redirect:/";
    }

    @GetMapping("/")
    public String displayTable(Model model) {
        Set<String> difference = comparator.getDifference();
        model.addAttribute("difference", difference);
        return "index";
    }

    @GetMapping("/getUsername")
    public String handleFileUpload(@RequestParam("username") String username) {

        System.out.println("File username post: " + username);

        return "index";
    }

}