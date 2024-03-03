package com.tweets.Posts.controller;

import com.tweets.Posts.model.Follows;
import com.tweets.Posts.model.NotificationConfig;
import com.tweets.Posts.model.Users;
import com.tweets.Posts.repository.FollowRepository;
import com.tweets.Posts.repository.NotificationConfigRepository;
import com.tweets.Posts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController
{
    @Autowired
    private UserRepository repo;

    @Autowired
    private FollowRepository frepo;

    @Autowired
    private NotificationConfigRepository nrepo;

    @PostMapping("/user/add")
    public void adduser(@RequestBody Users user)
    {
        repo.save(user);

        List<Users> followers = new ArrayList<>();
        Follows follow = new Follows();
        follow.setUid(user);
        follow.setFollowerlist(followers);
        frepo.save(follow);

        NotificationConfig config = new NotificationConfig(user.getUid(), false, false, false);
        nrepo.save(config);
    }

    @GetMapping("/user/{uid}")
    public Optional<Users> getUser(@PathVariable int uid)
    {
        return repo.findById(uid);
    }

}
