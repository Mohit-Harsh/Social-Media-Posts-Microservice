package com.tweets.Posts.repository;

import com.tweets.Posts.model.Follows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follows,Integer>
{
    @Query("select f from Follows f where f.uid.uid = :uid")
    Follows findByUser(int uid);
}
