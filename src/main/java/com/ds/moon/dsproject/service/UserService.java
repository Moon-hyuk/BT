package com.ds.moon.dsproject.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ds.moon.dsproject.entity.User;
import com.ds.moon.dsproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 저장.수정
    public void saveUser(User user) {

        userRepository.save(user);

    }

    public User modifyUser(User user) {
        return userRepository.save(user);
    }

    private void validateDuplicateMember(User user) {
        User findUser = userRepository.findByuserId(user.getUserId());
        if (findUser != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    // 사원 리스트
    public List<User> getListUser(String searchKeyword) {
        if(searchKeyword != null){
            return userRepository.findByUserNmContaining(searchKeyword);
        }
        return userRepository.findAll();
    }

    // 사원
    public User getUserInfo(String userId) {
        return userRepository.findByuserId(userId);

    }

    // 검색
    public List<User> getListUserNm(String searchKeyword) {
        return userRepository.findByUserNmContaining(searchKeyword);
    }

    // 삭제
    public void deleteUserId(User user) {

        userRepository.delete(user);
    }
}