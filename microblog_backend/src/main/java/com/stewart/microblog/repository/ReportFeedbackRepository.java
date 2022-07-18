package com.stewart.microblog.repository;

import com.stewart.microblog.entity.ReportFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportFeedbackRepository extends JpaRepository<ReportFeedback, Integer> {
}
