package com.stewart.microblog.service.impl;

import com.stewart.microblog.entity.Blog;
import com.stewart.microblog.esrepository.ESBlogRepository;
import com.stewart.microblog.repository.BlogRepository;
import com.stewart.microblog.service.SearchService;
import com.stewart.microblog.vo.SearchResultVO;
import com.stewart.microblog.vo.UserListVO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 搜索功能接口实现类
 * @author 王松涛
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Resource
    private ESBlogRepository esBlogRepository;
    @Resource
    private BlogRepository blogRepository;
    @Resource
    private HomeServiceImpl homeService;
    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public SearchResultVO searchByTopic(String str) {
        return null;
    }
    @Override
    public SearchResultVO searchByContent(String str) {
        //根据一个值查询多个字段  并高亮显示  这里的查询是取并集，即多个字段只需要有一个字段满足即可
        //需要查询的字段
        BoolQueryBuilder boolQueryBuilder= QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("content",str));
        //构建高亮查询
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withHighlightFields(new HighlightBuilder.Field("content"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>"))
                .build();
        //查询
        SearchHits<Blog> search = elasticsearchRestTemplate.search(searchQuery, Blog.class);
        //得到查询返回的内容
        List<SearchHit<Blog>> searchHits = search.getSearchHits();
        //设置一个最后需要返回的实体类集合
        List<Blog> blogList = new ArrayList<>();
        //遍历返回的内容进行处理
        for(SearchHit<Blog> searchHit:searchHits){
            //高亮的内容
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            //将高亮的内容填充到content中
            searchHit.getContent().setContent(highlightFields.get("content")==null ?
                    searchHit.getContent().getContent():highlightFields.get("content").get(0));
            //放到实体类中
            Blog blog = blogRepository.findBlogByIdAndScopeAndStateAndDeleted(
                    searchHit.getContent().getId(), "PUBLIC", "NORMAL", false);
            blog.setContent(searchHit.getContent().getContent());
            blogList.add(blog);
        }
        return new SearchResultVO(homeService.convertBlogsToVO(blogList));
    }
    @Override
    public UserListVO searchByUsername(String str) {
        return null;
    }
}
