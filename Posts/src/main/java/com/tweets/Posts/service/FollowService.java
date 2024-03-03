package com.tweets.Posts.service;

import com.tweets.Posts.model.Follows;
import com.tweets.Posts.model.UserDTO;
import com.tweets.Posts.model.Users;
import com.tweets.Posts.repository.FollowRepository;
import com.tweets.Posts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowService
{
    @Autowired
    private FollowRepository repo;

    @Autowired
    private UserRepository urepo;

    public String followuser(int uid, int uid_follower)
    {
        Users user = urepo.findById(uid).get();
        Users follower = urepo.findById(uid_follower).get();
        Follows follow = repo.findByUser(uid);

        follow.getFollowerlist().add(follower);
        repo.save(follow);

        return "You have Followed " + user.getUsername();
    }

    public List<UserDTO> getFollowers(int uid)
    {
        Follows follows = repo.findByUser(uid);
        List<Users> users = follows.getFollowerlist();
        List<UserDTO> userlist = new ArrayList<>();
        for(Users u : users)
        {
            UserDTO ud = new UserDTO(u.getUid(),u.getUsername(),u.getEmail());
            userlist.add(ud);
        }
        return userlist;
    }
}
