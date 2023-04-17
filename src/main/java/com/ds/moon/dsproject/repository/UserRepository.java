package com.ds.moon.dsproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.moon.dsproject.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

    User findByuserId(String userId);

    User findByUserNm(String UserNm);
    List<User> findByUserNmContaining(String searchKeyword);

    
    
} 