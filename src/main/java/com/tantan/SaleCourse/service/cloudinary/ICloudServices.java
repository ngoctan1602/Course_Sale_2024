package com.tantan.SaleCourse.service.cloudinary;

import org.springframework.web.multipart.MultipartFile;

public interface ICloudServices {
    public String uploadFile(MultipartFile multipartFile);
    public String uploadVideo(MultipartFile multipartFile,String folderName, String type);
}
