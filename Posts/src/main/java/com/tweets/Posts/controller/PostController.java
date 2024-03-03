package com.tweets.Posts.controller;

import com.tweets.Posts.model.Posts;
import com.tweets.Posts.model.PostsDTO;
import com.tweets.Posts.model.Users;
import com.tweets.Posts.repository.PostRepository;
import com.tweets.Posts.repository.UserRepository;
import com.tweets.Posts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    private PostService service;

    @PostMapping("/posts")
    public void addposts(@RequestBody List<Map<String,String>> posts)
    {
        for(Map<String,String> post : posts)
        {
            int uid = Integer.parseInt(post.get("uid"));
            String p = post.get("post");
            service.addpost(uid,p);
        }
    }

    @PostMapping("/post")
    public void addpost(@RequestBody Map<String,String> body)
    {
        int uid = Integer.parseInt(body.get("uid"));
        String post = body.get("post");

        service.addpost(uid,post);
    }

    @PutMapping("/post/{pid}")
    public void updatepost(@PathVariable int pid, @RequestBody Map<String, String> body)
    {
        String post = body.get("post");

        service.updatepost(pid,post);
    }

    @DeleteMapping("/post/{pid}")
    public void deletepost(@PathVariable int pid)
    {
        service.deletepost(pid);
    }

    @GetMapping("/post/{pid}")
    public Optional<Posts> getPost(@PathVariable int pid)
    {
        return service.getPost(pid);
    }

    @GetMapping("/user/{uid}/posts")
    public List<PostsDTO> getUserPosts(@PathVariable int uid)
    {
        return service.getUserPosts(uid);
    }

    @GetMapping("/posts")
    public List<PostsDTO> getAllPosts()
    {
        return service.getAllPosts();
    }

}
