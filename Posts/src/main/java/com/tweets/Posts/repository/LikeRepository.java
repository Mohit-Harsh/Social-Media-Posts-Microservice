package com.tweets.Posts.repository;

import com.tweets.Posts.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Integer>{

    @Query("select l from Likes l where l.user.uid = :uid")
    List<Likes> findByUsers(int uid);

    @Query("select l from Likes l where l.post.pid = :pid")
    List<Likes> getpostlikes(int pid);

    @Query("select l from Likes l where l.user.uid = :uid and l.post.pid = :pid")
    Likes getlike(int pid, int uid);
}
