package com.stewart.microblog.service.impl;

import com.stewart.microblog.entity.Blog;
import com.stewart.microblog.entity.ConcernGroup;
import com.stewart.microblog.entity.Topic;
import com.stewart.microblog.entity.User;
import com.stewart.microblog.repository.*;
import com.stewart.microblog.service.HomeService;
import com.stewart.microblog.vo.BlogVO;
import com.stewart.microblog.vo.HomeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public String testFindNickName() {
        String userName = "songtao_wang@qq.com";
        return userRepository.findByUsername(userName).getNickname();
    }
    @Override
    public HomeVO showHotBlogsHome() {
        String userName = "songtao_wang@qq.com";
        User user = userRepository.findByUsername(userName);
        Integer userId = user.getId();
        List<String> groupNameList = new ArrayList<>();
        List<ConcernGroup> concernGroupList = groupRepository.findByUserId(userId);
        for(ConcernGroup concernGroup : concernGroupList) {
            groupNameList.add(concernGroup.getName());
        }
        List<BlogVO> blogVOList = new ArrayList<>();
        List<Blog> blogList = blogRepository.findAllByOrderByHeatDesc();
        for(int i = 0; i < (Math.min(blogList.size(), 10)); i++) {
            Integer blogId = blogList.get(i).getId();
            Blog blog = blogRepository.findById(blogId).orElse(null);
            assert blog != null;
            BlogVO blogVO = new BlogVO(
                    blog.getTime().toString(),
                    blog.getContent(),
                    null,
                    null,
                    -1, -1, -1, -1, null
            );
            blogVOList.add(blogVO);
        }
        List<String> topicNameList = new ArrayList<>();
        List<Topic> topicList = topicRepository.findAllByOrderByHeatDesc();
        for(int i = 0; i < Math.min(3, topicList.size()); i++) {
            topicNameList.add(topicList.get(i).getName());
        }
        return new HomeVO(
                pictureRepository.findPictureById(user.getPhotoId()).getUrl(),
                user.getNickname(),
                user.getUsername(),
                followRepository.countByUserId(userId),
                followRepository.countByFollowingId(userId),
                groupNameList,
                blogVOList,
                topicNameList
        );
    }
}
