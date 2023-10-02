package com.vasudev.blog.services.Impl;

import com.vasudev.blog.entities.Category;
import com.vasudev.blog.entities.Post;
import com.vasudev.blog.entities.User;
import com.vasudev.blog.exceptions.ResourceNotFoundException;
import com.vasudev.blog.payloads.PostDto;
import com.vasudev.blog.payloads.PostResponse;
import com.vasudev.blog.repo.CategoryRepo;
import com.vasudev.blog.repo.PostRepo;
import com.vasudev.blog.repo.UserRepo;
import com.vasudev.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));

        Post post=this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost=this.postRepo.save(post);


        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id",postId));
      post.setTitle(postDto.getTitle());
      post.setContent(postDto.getContent());
      post.setImageName(post.getImageName());
      Post updatedPost=this.postRepo.save(post);

        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        this.postRepo.delete(post);
        this.modelMapper.map(post,PostDto.class);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {

        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        Page<Post>pagePost=this.postRepo.findAll(pageable);
        List<Post>posts=pagePost.getContent();

        List<PostDto>postDto=posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse= new PostResponse();
        postResponse.setContent(postDto);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getNumberOfElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse ;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id",postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id",categoryId));
         List<Post> posts = this.postRepo.findByCategory(category);
         List<PostDto> postDto = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDto;
    }

    @Override
    public PostResponse getPostsByUser(Integer userId,Integer pageNumber,Integer pageSize) {
        User user= userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id",userId));
         List<Post> posts = this.postRepo.findByUser(user);
         Pageable pageable=PageRequest.of(pageNumber,pageSize);
         Page<Post>postpage= (Page<Post>) this.postRepo.findByUser(user);
         List<Post>posts1=postpage.getContent();
         List<PostDto> postDto=posts1.stream().map(post -> modelMapper.map(postpage,PostDto.class)).collect(Collectors.toList());
         PostResponse postResponse= new PostResponse();
         postResponse.setContent(postDto);
         postResponse.setPageNumber(postpage.getNumber());
         postResponse.setPageSize(postpage.getSize());
         postResponse.setTotalElements(postpage.getNumberOfElements());
         postResponse.setTotalPages(postResponse.getTotalPages());
         postResponse.setLastPage(postpage.isLast());
        return postResponse;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
