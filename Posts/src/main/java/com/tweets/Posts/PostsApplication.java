package com.tweets.Posts;

import com.tweets.Posts.model.Users;
import com.tweets.Posts.repository.PostRepository;
import com.tweets.Posts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PostsApplication {

	public static void main(String[] args)
	{
		ApplicationContext context = SpringApplication.run(PostsApplication.class, args);

		Users user = context.getBean(Users.class);

	}

}
