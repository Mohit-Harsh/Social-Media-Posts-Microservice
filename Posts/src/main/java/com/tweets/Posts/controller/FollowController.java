package com.tweets.Posts.controller;

import com.tweets.Posts.model.UserDTO;
import com.tweets.Posts.model.Users;
import com.tweets.Posts.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class FollowController
{
    @Autowired
    private FollowService service;

    @PostMapping("/user/follow/{uid}")
    public String followuser(@PathVariable int uid, @RequestBody Map<String,Integer> map)
    {
        int uid_follower = map.get("uid_follower");
        return service.followuser(uid, uid_follower);

    }

    @GetMapping("/user/followers/{uid}")
    public List<UserDTO> getFollowers(@PathVariable int uid)
    {
        return service.getFollowers(uid);
    }

}
