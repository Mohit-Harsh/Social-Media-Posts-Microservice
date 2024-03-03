package com.tweets.Posts.repository;

import com.tweets.Posts.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Posts, Integer>
{
    @Query("select p from Posts p where p.users.uid = :uid")
    List<Posts> findByUsers(int uid);
}
