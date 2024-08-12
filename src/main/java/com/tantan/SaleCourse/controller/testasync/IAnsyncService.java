package com.tantan.SaleCourse.controller.testasync;

import java.util.concurrent.CompletableFuture;

public interface IAnsyncService {
    CompletableFuture<Void> saveVideo(String video);
    CompletableFuture<Void> saveImage(String image);
}
