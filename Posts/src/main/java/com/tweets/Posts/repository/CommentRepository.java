package com.tweets.Posts.repository;

import com.tweets.Posts.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer>{
    @Query("select c from Comments c where c.user.uid = :uid")
    List<Comments> findByUid(int uid);

    @Query("select c from Comments c where c.post.pid = :pid")
    List<Comments> findByPid(int pid);
}
