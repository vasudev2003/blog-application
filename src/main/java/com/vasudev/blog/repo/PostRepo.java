package com.vasudev.blog.repo;

import com.vasudev.blog.entities.Category;
import com.vasudev.blog.entities.Post;
import com.vasudev.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
