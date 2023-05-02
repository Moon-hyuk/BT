package com.ds.moon.dsproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.moon.dsproject.entity.Hb;

public interface HbRepository extends JpaRepository<Hb, Long>{
    
    List<Hb> findAllByOrderByHbCdAsc();
}
