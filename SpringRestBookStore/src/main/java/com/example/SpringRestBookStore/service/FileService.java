package com.example.SpringRestBookStore.service;

import com.example.SpringRestBookStore.dto.FileDto;
import com.example.SpringRestBookStore.entity.FileEntity;
import com.example.SpringRestBookStore.repository.FileRespository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRespository fileRespository;
    private  final ModelMapper modelMapper;
    public FileDto uploadFile(MultipartFile file)throws IOException{
        FileEntity fileEntity=new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileType(file.getContentType());
        fileEntity.setFileSize(file.getSize());
        fileEntity.setFileData(file.getBytes());
        FileEntity savedFile=fileRespository.save(fileEntity);
        return modelMapper.map(savedFile, FileDto.class);
    }
    public FileEntity downloadFile(Long id){
        return fileRespository.findById(id).orElseThrow(()->new RuntimeException("File not found with id: "+id));
    }
 }
