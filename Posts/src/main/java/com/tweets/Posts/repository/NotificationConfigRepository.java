package com.tweets.Posts.repository;

import com.tweets.Posts.model.NotificationConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationConfigRepository extends JpaRepository<NotificationConfig,Integer>{
}
