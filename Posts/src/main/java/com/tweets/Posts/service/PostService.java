package com.tweets.Posts.service;

import com.tweets.Posts.model.*;
import com.tweets.Posts.repository.NotificationConfigRepository;
import com.tweets.Posts.repository.PostRepository;
import com.tweets.Posts.repository.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService
{

    @Autowired
    private PostRepository repo;

    @Autowired
    private UserRepository urepo;

    @Autowired
    private NotificationConfigRepository nrepo;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void addpost(int uid, String post)
    {
        Users user = urepo.findById(uid).stream().findFirst().orElse(null);
        Posts new_post = new Posts();
        new_post.setUsers(user);
        new_post.setPost(post);
        repo.save(new_post);

        PostNotification notification = new PostNotification(uid,user.getUsername(),post);
        rabbitTemplate.convertAndSend("queue_exchange","post_routing_key",notification);
        System.out.println("Post sent to queue");

    }
    public Optional<Posts> getPost(int pid)
    {
        return repo.findById(pid);
    }

    public List<PostsDTO> getUserPosts(int uid)
    {
        List<Posts> postlist = repo.findByUsers(uid);
        List<PostsDTO> posts = new ArrayList<>();
        for(Posts p : postlist)
        {
            PostsDTO pd = new PostsDTO();
            pd.setPid(p.getPid());
            pd.setPost(p.getPost());
            posts.add(pd);
        }
        return posts;
    }

    public void updatepost(int pid, String new_post)
    {
        Posts post = repo.findById(pid).get();
        if(post != null)
        {
            post.setPost(new_post);
            repo.save(post);
        }
    }

    public void deletepost(int pid)
    {
        repo.deleteById(pid);
    }

    public List<PostsDTO> getAllPosts()
    {
        List<Posts> posts = repo.findAll();
        List<PostsDTO> allposts = new ArrayList<>();
        for(Posts post : posts)
        {
            PostsDTO new_post = new PostsDTO(post.getPid(),post.getPost());
            allposts.add(new_post);
        }
        return allposts;
    }
}
