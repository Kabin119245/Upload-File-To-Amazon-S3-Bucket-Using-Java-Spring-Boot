package com.kabin.awss3.controller;

import com.kabin.awss3.model.User;
import com.kabin.awss3.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller

public class UserController {

    @Autowired
    private S3Service s3Service;


    @GetMapping("/file-upload")
    public String fileUploadPage() {
        return "form";
    }



    @PostMapping("/file-upload")
    public String saveFiles(Model model, @RequestParam("description") String description,
                            @RequestParam("file")MultipartFile multipartFile) {
        String filename = multipartFile.getOriginalFilename();
        User user = new User();
        user.setFileName(filename);
        user.setDescription(description);


        try {
            s3Service.uploadToS3(multipartFile.getInputStream(), filename);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "form";

    }



}
