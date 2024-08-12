package com.tantan.SaleCourse.service.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class CloudServices implements ICloudServices {
    @Resource
    private Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        try {
            return (String) cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap()).get("secure_url");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String uploadVideo(MultipartFile multipartFile, String folderName, String type) {
        try {
            Map<String, Object> uploadParams = ObjectUtils.asMap(
                    "folder", folderName,
                    "resource_type", "video" // Đặt resource_type là 'video' cho tệp video
            );

            if (multipartFile != null) {
                Map<?, ?> uploadedFile = cloudinary.uploader().upload(multipartFile.getBytes(), uploadParams);
                return (String) uploadedFile.get("secure_url");
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
