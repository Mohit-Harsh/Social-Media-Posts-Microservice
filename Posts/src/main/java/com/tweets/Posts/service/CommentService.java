package com.tweets.Posts.service;

import com.tweets.Posts.model.*;
import com.tweets.Posts.repository.CommentRepository;
import com.tweets.Posts.repository.NotificationConfigRepository;
import com.tweets.Posts.repository.PostRepository;
import com.tweets.Posts.repository.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService
{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CommentRepository repo;

    @Autowired
    private UserRepository urepo;

    @Autowired
    private PostRepository prepo;

    @Autowired
    private NotificationConfigRepository nrepo;

    public List<CommentsDTO> getAllComments()
    {
        List<Comments> comlist = repo.findAll();
        List<CommentsDTO> cdlist = new ArrayList<>();

        for(Comments c : comlist)
        {
            CommentsDTO cd = new CommentsDTO();
            cd.setCid(c.getCid());
            cd.setUid(c.getUser().getUid());
            cd.setPid(c.getPost().getPid());
            cd.setComment(c.getComment());
            cdlist.add(cd);
        }

        return cdlist;
    }

    public CommentsDTO getById(int cid)
    {
        Comments com = repo.findById(cid).get();
        CommentsDTO cd = new CommentsDTO(com.getCid(),com.getUser().getUid(),com.getPost().getPid(),com.getComment());
        return cd;
    }


    public void updateComment(int cid, String comment) {
        Comments com = repo.findById(cid).get();
        com.setComment(comment);
        repo.save(com);
    }

    public void deleteComment(int cid) {
        repo.deleteById(cid);
    }

    public List<Map<String,String>> getUserComments(int uid)
    {
        List<Comments> comlist = repo.findByUid(uid);
        List<Map<String,String>> mplist = new ArrayList<>();
        for(Comments com : comlist)
        {
            Map<String,String> mp = new HashMap<>();
            mp.put("post",com.getPost().getPost());
            mp.put("comment",com.getComment());
            mplist.add(mp);
        }
        return mplist;
    }

    public List<Map<String,String>> getPostComments(int pid)
    {
        List<Comments> comlist = repo.findByPid(pid);
        List<Map<String,String>> mplist = new ArrayList<>();
        for(Comments com : comlist)
        {
            Map<String,String> mp = new HashMap<>();
            mp.put("post",com.getPost().getPost());
            mp.put("comment",com.getComment());
            mplist.add(mp);
        }
        return mplist;
    }

    public void addComment(int uid, int pid, String comment)
    {
        Comments com = new Comments();
        Users user = urepo.findById(uid).get();
        Posts post = prepo.findById(pid).get();
        com.setComment(comment);
        com.setUser(user);
        com.setPost(post);
        repo.save(com);

        NotificationConfig config = nrepo.findById(post.getUsers().getUid()).get();
        if(config.isComment_config())
        {
            CommentNotification notification = new CommentNotification(post.getUsers().getEmail(),user.getUsername(),comment);
            rabbitTemplate.convertAndSend("queue_exchange","comment_routing_key",notification);
            System.out.println("Comment notification sent to " + post.getUsers().getEmail());
        }
        else
        {
            System.out.println("Comment not sent to queue");
        }
    }
}
