package com.ds.moon.dsproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.moon.dsproject.entity.UserHb;
import com.ds.moon.dsproject.entity.UserHbBridge;

public interface UserHbRepository extends JpaRepository<UserHb, UserHbBridge>{
    
    List<UserHb> findAllByUserUserId(String userId);

    
    void deleteByUserUserId(String userId);


}
