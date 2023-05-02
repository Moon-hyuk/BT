package com.ds.moon.dsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.moon.dsproject.entity.Dept;

public interface DeptRepository extends JpaRepository<Dept,Long>{
    
} 