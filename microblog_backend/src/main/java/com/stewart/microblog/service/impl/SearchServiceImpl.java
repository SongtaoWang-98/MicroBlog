package com.stewart.microblog.service.impl;

import com.stewart.microblog.entity.Blog;
import com.stewart.microblog.entity.BlogTopic;
import com.stewart.microblog.entity.Topic;
import com.stewart.microblog.entity.UserInfo;
import com.stewart.microblog.esrepository.ESBlogRepository;
import com.stewart.microblog.repository.BlogRepository;
import com.stewart.microblog.repository.BlogTopicRepository;
import com.stewart.microblog.repository.PictureRepository;
import com.stewart.microblog.service.SearchService;
import com.stewart.microblog.vo.SearchResultVO;
import com.stewart.microblog.vo.UserListItemVO;
import com.stewart.microblog.vo.UserListVO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    private BlogTopicRepository blogTopicRepository;
    @Resource
    private PictureRepository pictureRepository;
    @Resource
    private HomeServiceImpl homeService;
    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public SearchResultVO searchByTopic(String str) {
        //需要查询的字段
        BoolQueryBuilder boolQueryBuilder= QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("name",str));
        //构建高亮查询
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withHighlightFields(new HighlightBuilder.Field("name"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>"))
                .build();
        //查询
        SearchHits<Topic> search = elasticsearchRestTemplate.search(searchQuery, Topic.class);
        //得到查询返回的内容
        List<SearchHit<Topic>> searchHits = search.getSearchHits();
        //设置一个最后需要返回的实体类集合
        List<Blog> blogList = new ArrayList<>();
        //遍历返回的内容进行处理
        for(SearchHit<Topic> searchHit:searchHits){
            //高亮的内容
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            //将高亮的内容填充
            searchHit.getContent().setName(highlightFields.get("name")==null ?
                    searchHit.getContent().getName():highlightFields.get("name").get(0));
            //放到实体类中
            List<BlogTopic> blogTopicList = blogTopicRepository.findBlogTopicsByTopicIdAndDeleted(
                    searchHit.getContent().getId(), false);
            Set<Integer> blogIdSet = new HashSet<>();
            for(BlogTopic blogTopic: blogTopicList) {
                if(blogIdSet.add(blogTopic.getBlogId())){
                    blogList.add(blogRepository.findBlogByIdAndScopeAndStateAndDeleted(
                            blogTopic.getBlogId(), "PUBLIC", "NORMAL", false));
                }
            }
        }
        return new SearchResultVO(homeService.convertBlogsToVO(blogList));
    }
    @Override
    public SearchResultVO searchByContent(String str) {
        //根据一个值查询多个字段  并高亮显示
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
        //需要查询的字段
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("username", str))
                .should(QueryBuilders.matchQuery("nickname", str));
        //构建高亮查询
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withHighlightFields(new HighlightBuilder.Field("username")
                ,new HighlightBuilder.Field("nickname"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>"))
                .build();
        //查询
        SearchHits<UserInfo> search = elasticsearchRestTemplate.search(searchQuery, UserInfo.class);
        //得到查询返回的内容
        List<SearchHit<UserInfo>> searchHits = search.getSearchHits();
        //设置一个最后需要返回的实体类集合
        List<UserListItemVO> userListItemVOList = new ArrayList<>();
        //遍历返回的内容进行处理
        for(SearchHit<UserInfo> searchHit:searchHits){
            //高亮的内容
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            //将高亮的内容填充
            searchHit.getContent().setUsername(highlightFields.get("username")==null ?
                    searchHit.getContent().getUsername():highlightFields.get("username").get(0));
            searchHit.getContent().setNickname(highlightFields.get("nickname")==null ?
                    searchHit.getContent().getNickname():highlightFields.get("nickname").get(0));
            //放到实体类中
            UserInfo userInfo = searchHit.getContent();
            userListItemVOList.add(new UserListItemVO(userInfo.getId(),
                    pictureRepository.findPictureByIdAndDeleted(userInfo.getPhotoId(), false)
                            .getUrl(), userInfo.getNickname(), userInfo.getUsername()));
        }
        return new UserListVO(userListItemVOList);
    }
}
