package com.tweets.Posts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Likes
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "SERIAL")
    private int lid;

    @ManyToOne
    @JoinColumn(name = "pid")
    private Posts post;

    @ManyToOne
    @JoinColumn(name = "uid")
    private Users user;
}
