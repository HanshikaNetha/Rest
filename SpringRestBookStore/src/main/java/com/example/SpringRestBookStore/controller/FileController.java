package com.example.SpringRestBookStore.controller;

import com.example.SpringRestBookStore.dto.FileDto;
import com.example.SpringRestBookStore.entity.FileEntity;
import com.example.SpringRestBookStore.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/files")
public class FileController {
    private final FileService fileService;
    @PostMapping("/upload")
    public ResponseEntity<FileDto> uploadFile(@RequestParam("file")MultipartFile file) throws IOException{
        FileDto uploadedFile=fileService.uploadFile(file);
        return ResponseEntity.ok(uploadedFile);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id){
        FileEntity fileEntity=fileService.downloadFile(id);
        ByteArrayResource resource=new ByteArrayResource(fileEntity.getFileData());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileEntity.getFileType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\""+fileEntity.getFileName()+"\"").contentLength(fileEntity.getFileSize()).body(resource);
    }
}
