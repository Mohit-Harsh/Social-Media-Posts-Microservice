package com.tweets.Posts.controller;

import com.tweets.Posts.model.NotificationConfig;
import com.tweets.Posts.repository.NotificationConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationConfigController
{
    @Autowired
    private NotificationConfigRepository nrepo;

    @PutMapping("/config")
    public void updateConfig(@RequestBody NotificationConfig config)
    {
        nrepo.save(config);
    }
}
