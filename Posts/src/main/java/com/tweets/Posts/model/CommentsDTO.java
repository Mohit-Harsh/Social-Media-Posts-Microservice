package com.tweets.Posts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CommentsDTO
{
    private int cid;
    private int uid;
    private int pid;
    private String comment;
}
