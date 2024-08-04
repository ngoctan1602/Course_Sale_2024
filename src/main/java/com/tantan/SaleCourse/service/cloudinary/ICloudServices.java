package com.tantan.SaleCourse.service.cloudinary;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

public interface ICloudServices {
    public String uploadFile(MultipartFile multipartFile);
    public String uploadVideo(MultipartFile multipartFile,String folderName, String type);
}
