package com.stewart.microblog.repository;

import com.stewart.microblog.entity.ReportFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 举报反馈信息持久层接口
 * @author 王松涛
 */
public interface ReportFeedbackRepository extends JpaRepository<ReportFeedback, Integer> {

}
