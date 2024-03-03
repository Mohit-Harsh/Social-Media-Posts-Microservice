package com.tweets.Notifications.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationConfig
{
    @Id
    private int uid;
    private boolean like_config;
    private boolean comment_config;
    private boolean post_config;

}
