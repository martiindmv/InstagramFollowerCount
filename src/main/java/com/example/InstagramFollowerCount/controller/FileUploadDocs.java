package com.example.InstagramFollowerCount.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadDocs {

    @PostMapping("/uploadFollowers")
    @Operation(summary = "Upload followers", description = "This method accepts a file containing followers and processes it.")
    @ApiResponse(responseCode = "405", description = "A GET request was sent, while this method supports only POST requests.")
    @ApiResponse(responseCode = "500", description = "File with incorrect format was uploaded.")
    Object fileWithFollowers(MultipartFile file) throws IOException;

    @PostMapping("/uploadFollowing")
    @Operation(summary = "Upload following", description = "This method accepts a file containing the following list and processes it.")
    @ApiResponse(responseCode = "405", description = "A GET request was sent, while this method supports only POST requests.")
    @ApiResponse(responseCode = "500", description = "File with incorrect format was uploaded.")
    String fileWithFollowing(MultipartFile file) throws IOException;
}