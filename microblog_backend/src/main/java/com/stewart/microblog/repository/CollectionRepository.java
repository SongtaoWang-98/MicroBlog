package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {
}
