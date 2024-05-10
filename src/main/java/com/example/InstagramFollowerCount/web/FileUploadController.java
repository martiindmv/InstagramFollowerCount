package com.example.InstagramFollowerCount.web;

import com.example.InstagramFollowerCount.util.JSONOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileUploadController {

    private JSONOperations jsonOperations = new JSONOperations();

    @PostMapping("/uploadFollowers")
    public String handleFollowersList(@RequestParam("followers") MultipartFile file) throws IOException {

        String contentFollwers = new String(file.getInputStream().readAllBytes());
        jsonOperations.readFileArray(contentFollwers);

        String fileName = file.getOriginalFilename();

        return "redirect:/";
    }

    @PostMapping("/uploadFollowing")
    public String handleFollowingList(@RequestParam("following") MultipartFile file) throws IOException {
        String contentFollowing = new String(file.getInputStream().readAllBytes());
        String jsonKey = jsonOperations.getJsonObjectKey(contentFollowing);
        jsonOperations.readFileWithKey(contentFollowing, jsonKey);


        return "redirect:/";
    }

    @GetMapping("/getUsername")
    public String handleFileUpload(@RequestParam("username") String username) {

        System.out.println("File username post: " + username);

        return "redirect:/";
    }

}