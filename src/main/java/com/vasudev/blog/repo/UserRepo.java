package com.vasudev.blog.repo;

import com.vasudev.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <User,Integer>{
}
