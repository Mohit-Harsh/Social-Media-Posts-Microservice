package com.tweets.Notifications.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentNotification
{
    private String email; //recipient's email
    private String username; //username of person who commented
    private String comment; //comment
}
