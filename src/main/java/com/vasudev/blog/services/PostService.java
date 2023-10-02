package com.vasudev.blog.services;

import com.vasudev.blog.entities.Post;
import com.vasudev.blog.payloads.PostDto;
import com.vasudev.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostByCategory(Integer categoryId);
    PostResponse getPostsByUser(Integer userId,Integer pageNumber, Integer pageSize);
    List<Post>searchPosts(String keyword);
}
