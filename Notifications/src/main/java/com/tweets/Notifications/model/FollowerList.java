package com.tweets.Notifications.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class FollowerList
{
    private List<UserDTO> followersList;

    public FollowerList() {

        followersList = new ArrayList<>();
    }

    public List<UserDTO> getFollowersList() {
        return followersList;
    }

    public void setFollowersList(List<UserDTO> followersList) {
        this.followersList = followersList;
    }
}
