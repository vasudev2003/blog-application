package com.vasudev.blog.controllers;

import com.vasudev.blog.entities.User;
import com.vasudev.blog.payloads.ApiResponse;
import com.vasudev.blog.payloads.UserDto;
import com.vasudev.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
       UserDto createUser= this.userService.createUser(userDto);
       return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId)
    {
            UserDto updatedUser=this.userService.updateUser(userDto, userId);
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId)
    {
            this.userService.deleteUser(userId);
            return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
      return ResponseEntity.ok( this.userService.getAllUsers());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)
    {

        return ResponseEntity.ok(this.userService.getUserById(userId));
    }




}
