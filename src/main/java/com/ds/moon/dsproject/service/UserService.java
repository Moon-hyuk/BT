package com.ds.moon.dsproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ds.moon.dsproject.entity.Dept;
import com.ds.moon.dsproject.entity.User;
import com.ds.moon.dsproject.repository.DeptRepository;
import com.ds.moon.dsproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getListUser(){
        return userRepository.findAll();
        
    }
}