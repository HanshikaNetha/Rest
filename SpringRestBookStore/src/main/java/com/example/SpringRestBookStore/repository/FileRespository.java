package com.example.SpringRestBookStore.repository;

import com.example.SpringRestBookStore.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;

public interface FileRespository extends JpaRepository<FileEntity, Long> {
    FileEntity findByFileName(String fileName);
}
