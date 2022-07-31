package com.stewart.microblog.esrepository;

import com.stewart.microblog.entity.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author stewartwang
 */
public interface ESBlogRepository extends ElasticsearchRepository<Blog, Integer> {
    /**
     * ceshik
     * @return
     */
    Blog findBlogById(Integer id);
    List<Blog> findAllByContentContaining(String str);
}
