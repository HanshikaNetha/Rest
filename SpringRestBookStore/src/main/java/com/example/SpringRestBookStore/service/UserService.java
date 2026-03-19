package com.example.SpringRestBookStore.service;

import com.example.SpringRestBookStore.dto.LoginDto;
import com.example.SpringRestBookStore.dto.LoginResponse;
import com.example.SpringRestBookStore.dto.UserDto;
import com.example.SpringRestBookStore.dto.UserResponseDto;
import com.example.SpringRestBookStore.entity.User;
import com.example.SpringRestBookStore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserResponseDto RegisterUser(UserDto userDto){
        User user=modelMapper.map(userDto, User.class);
        User saveduser=userRepository.save(user);
        UserResponseDto userResponseDto=modelMapper.map(userDto, UserResponseDto.class);
        return userResponseDto;
    }

    public UserResponseDto UpdateUser(Long id, UserDto userDto){
        User existingUser=userRepository.findById(id).orElseThrow(()->new RuntimeException("User id not found"));
        existingUser.setUsername(userDto.getUsername());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(userDto.getPassword());
        existingUser.setBooks(userDto.getBooks());
        User savingUser=userRepository.save(existingUser);
        UserResponseDto userResponseDto=modelMapper.map(existingUser, UserResponseDto.class);
        return userResponseDto;
    }



    public UserResponseDto getUserById(Long id){
        User getUser=userRepository.findById(id).orElseThrow(()->new RuntimeException("User id not found"));
        UserResponseDto userResponseDto=modelMapper.map(getUser, UserResponseDto.class);
        return userResponseDto;
    }

    public List<UserResponseDto> getAllUser(){
        List<User> allUsers=userRepository.findAll();
        List<UserResponseDto> AllUserResponseDto=allUsers.stream().map(i->modelMapper.map(i, UserResponseDto.class)).toList();
        return AllUserResponseDto;
    }

    public UserResponseDto DeleteUser(Long id){
        User user=userRepository.findById(id).orElseThrow(()->new RuntimeException("user id not found"));
        userRepository.deleteById(user.getId());
        UserResponseDto userResponseDto=modelMapper.map(user, UserResponseDto.class);
        return userResponseDto;
    }

}
