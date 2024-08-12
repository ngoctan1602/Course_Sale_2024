package com.tantan.SaleCourse.controller.testasync;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AnsyncService implements IAnsyncService{
    @Override
    @Async
    public CompletableFuture<Void> saveVideo(String video) {
        System.out.println("Saving video: " + video);
        // Giả lập độ trễ
        try {
            Thread.sleep(2000); // 2 giây
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    @Async
    public CompletableFuture<Void> saveImage(String image) {
        // Giả lập xử lý hình ảnh
        System.out.println("Saving image: " + image);
        // Giả lập độ trễ
        try {
            Thread.sleep(2000); // 2 giây
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return CompletableFuture.completedFuture(null);
    }
}
