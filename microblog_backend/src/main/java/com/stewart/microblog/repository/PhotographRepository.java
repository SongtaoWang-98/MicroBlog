package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Photograph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotographRepository extends JpaRepository<Photograph, Integer> {
}
