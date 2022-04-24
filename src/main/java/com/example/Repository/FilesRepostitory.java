package com.example.Repository;

import com.example.Entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilesRepostitory  extends JpaRepository<Files,String> {
    public List<Files> findByfileName(String name);
}
