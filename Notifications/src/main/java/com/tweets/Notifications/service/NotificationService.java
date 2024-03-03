package com.tweets.Notifications.service;

import com.tweets.Notifications.model.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationService
{
    @RabbitListener(queues = {"likequeue"})
    public void consumeLikeNotification(LikeNotification notification)
    {
        System.out.println(notification);
    }

    @RabbitListener(queues = {"commentqueue"})
    public void consumeCommentNotification(CommentNotification notification)
    {
        System.out.println(notification);
    }

    @RabbitListener(queues = {"postqueue"})
    public void consumePostNotification(PostNotification notification)
    {
        int uid = notification.getUid();
        RestTemplate restTemplate = new RestTemplate();
        Object[] objects = restTemplate.getForObject("http://localhost:8080/user/followers/"+uid,Object[].class);
        List<Object> list = Arrays.asList(objects);
        System.out.println(list);
    }
}
