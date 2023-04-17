package com.ds.moon.dsproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ds.moon.dsproject.entity.Dept;
import com.ds.moon.dsproject.entity.User;
import com.ds.moon.dsproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;

    public User saveUser(User user){
        
        validateDuplicateMember(user);
        return userRepository.save(user);
    }

    private void validateDuplicateMember(User user){
        User findUser = userRepository.findByuserId(user.getUserId());
        if(findUser != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
    //사원 리스트
    public List<User> getListUser(){
        return userRepository.findAll();
    }

    //사원 
    public User getUserInfo(String userNm){
        System.out.println("유유유우우우우우우우"+userNm);
        return userRepository.findByUserNm(userNm);

        
    }
    
    //검색
    public List<User> getListUserNm(String searchKeyword){
        return userRepository.findByUserNmContaining(searchKeyword);
    }
}