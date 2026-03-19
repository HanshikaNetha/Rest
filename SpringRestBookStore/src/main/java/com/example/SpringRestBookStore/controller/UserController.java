package com.example.SpringRestBookStore.controller;

import com.example.SpringRestBookStore.dto.LoginDto;
import com.example.SpringRestBookStore.dto.LoginResponse;
import com.example.SpringRestBookStore.dto.UserDto;
import com.example.SpringRestBookStore.dto.UserResponseDto;
import com.example.SpringRestBookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/RegisterUser")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){
        UserResponseDto registerdUser=userService.RegisterUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User "+registerdUser.getUsername()+" is registered");
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto){
        UserResponseDto updatedUser=userService.UpdateUser(id, userDto);
        return ResponseEntity.status(HttpStatus.OK).body("User "+updatedUser.getUsername()+" is updated");
    }



    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserResponseDto> getUserByid(@PathVariable("id") Long id){
        UserResponseDto user=userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> allUsers=userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        UserResponseDto deletedUser=userService.DeleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User "+deletedUser.getUsername()+" is deletd");
    }
}
