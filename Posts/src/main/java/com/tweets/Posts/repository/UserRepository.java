package com.tweets.Posts.repository;

import com.tweets.Posts.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

    Optional<Users> findByUsername(String username);

    @Query("select u.username from Users u where uid=?1")
    Optional<Users> getById(int uid);
}
