package com.gamesstorebe.controller;

import com.gamesstorebe.customHandleError.system.Result;
import com.gamesstorebe.service.impl.FileHandleService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileHandleController {
    private final FileHandleService fileHandleService;

    public FileHandleController(FileHandleService showImageService) {
        this.fileHandleService = showImageService;
    }

    @GetMapping("/image/{type}/{fileName}")
    public ResponseEntity<Resource> showImage(@PathVariable (name = "fileName") String fileName,
                                              @PathVariable (name = "type") String type) throws IOException {
        return fileHandleService.showImage(type, fileName);
    }

    @GetMapping("/upload")
    public Result uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        fileHandleService.uploadImage(file);
        return new Result(true, HttpStatus.OK, "Upload successfully", null);
    }
}
