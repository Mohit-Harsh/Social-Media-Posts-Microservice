package com.tweets.Posts.controller;

import com.tweets.Posts.model.PostLikes;
import com.tweets.Posts.model.UserLikes;
import com.tweets.Posts.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class LikeController {

    @Autowired
    private LikeService service;

    @PostMapping("/like")
    public void addlikes(@RequestBody Map<String,Integer> body)
    {
        int uid = body.get("uid");
        int pid = body.get("pid");
        service.addlike(uid,pid);
    }

    @GetMapping("/user/{uid}/likes")
    public List<UserLikes> getuserlikes(@PathVariable int uid)
    {
        return service.userlikes(uid);
    }

    @GetMapping("/post/{pid}/likes")
    public List<PostLikes> getpostlikes(@PathVariable int pid)
    {
        return service.getpostlikes(pid);
    }

    @GetMapping("/unlike")
    public void unlike(int pid, int uid)
    {
        service.unlike(pid,uid);
    }
}
