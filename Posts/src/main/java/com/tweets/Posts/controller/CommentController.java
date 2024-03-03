package com.tweets.Posts.controller;
import com.tweets.Posts.model.CommentsDTO;
import com.tweets.Posts.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CommentController{

    @Autowired
    private CommentService service;

    @PostMapping("/comments")
    public void addComment(@RequestBody Map<String,String> mp)
    {
        int uid = Integer.parseInt(mp.get("uid"));
        int pid = Integer.parseInt(mp.get("pid"));
        String comment = mp.get("comment");

        service.addComment(uid,pid,comment);
    }

    @GetMapping("/comments")
    public List<CommentsDTO> getAllComments()
    {
        return service.getAllComments();
    }

    @GetMapping("/comment/{pid}")
    public CommentsDTO getById(@PathVariable int cid)
    {
        return service.getById(cid);
    }

    @PutMapping("/comment/{pid}")
    public void updateById(@PathVariable int cid, @RequestBody Map<String,String> mp)
    {
        service.updateComment(cid,mp.get("comment"));
    }

    @DeleteMapping("/comment/{cid}")
    public void deleteById(@PathVariable int cid)
    {
        service.deleteComment(cid);
    }

    @GetMapping("/user/{uid}/comments")
    public List<Map<String,String>> getUserComments(@PathVariable int uid)
    {
        return service.getUserComments(uid);
    }

    @GetMapping("/post/{pid}/comments")
    public List<Map<String,String>> getPostComments(@PathVariable int pid)
    {
        return service.getPostComments(pid);
    }

}
