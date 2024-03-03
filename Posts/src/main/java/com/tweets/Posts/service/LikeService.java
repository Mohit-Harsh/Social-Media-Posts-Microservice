package com.tweets.Posts.service;

import com.tweets.Posts.model.*;
import com.tweets.Posts.repository.LikeRepository;
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

@Service
public class LikeService
{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private LikeRepository repo;

    @Autowired
    private UserRepository urepo;

    @Autowired
    private PostRepository prepo;

    @Autowired
    private NotificationConfigRepository nrepo;

    public void addlike(int uid, int pid)
    {
        Users user = urepo.findById(uid).orElse(null);
        Posts post = prepo.findById(pid).orElse(null);
        Likes like = new Likes();
        like.setPost(post);
        like.setUser(user);
        repo.save(like);

        NotificationConfig config = nrepo.findById(post.getUsers().getUid()).get();
        if(config.isLike_config())
        {
            LikeNotification notification = new LikeNotification(post.getUsers().getEmail(),user.getUsername());
            rabbitTemplate.convertAndSend("queue_exchange","like_routing_key",notification);
            System.out.println("Like notification sent to " + post.getUsers().getEmail());
        }
        else
        {
            System.out.println("Like not sent to queue");
        }
    }

    public List<UserLikes> userlikes(int uid)
    {
        List<Likes> likes = repo.findByUsers(uid);
        List<UserLikes> userlikes = new ArrayList<>();
        for(Likes like : likes)
        {
            UserLikes l = new UserLikes();
            l.setPost(like.getPost().getPost());
            l.setLid(like.getLid());
            l.setPid(like.getPost().getPid());
            userlikes.add(l);
        }

        return userlikes;
    }

    public List<PostLikes> getpostlikes(int pid)
    {
        List<Likes> likes = repo.getpostlikes(pid);
        List<PostLikes> postlikes = new ArrayList<>();
        for(Likes like : likes)
        {
            PostLikes l = new PostLikes();
            l.setUid(like.getUser().getUid());
            l.setUsername(like.getUser().getUsername());
            postlikes.add(l);
        }
        return postlikes;
    }

    public void unlike(int pid, int uid)
    {
        Likes like = repo.getlike(pid,uid);
        repo.delete(like);
    }
}
