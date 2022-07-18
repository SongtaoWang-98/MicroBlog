package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, Integer> {
}
