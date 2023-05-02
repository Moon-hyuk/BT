package com.ds.moon.dsproject.entity;

import javax.persistence.Entity;


import com.ds.moon.dsproject.dto.UserDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user_tb")
@Getter
@Setter
@ToString
public class User {

    @Id
    @Column(name = "user_id",unique=true )
    private String userId;
    @Column(name = "user_nm")
    private String userNm;
    @Column(name = "user_eml_addr")
    private String userEmlAddr;
    @Column(name = "user_telno")
    private String userTelno;
    @Column(name = "user_addr")
    private String userAddr;
    
    @Column(name = "user_aprv_yn")
    private String userAprvYn;
   
   
    @ManyToOne
    @JoinColumn(name = "dept_cd")
    private Dept dept;

    
    

    public static User createUser(UserDto userDto) {
        User user = new User();
        Dept dept = new Dept();
        dept.setDeptCd(userDto.getDeptCd());
        user.setUserId(userDto.getUserId());
        user.setUserNm(userDto.getUserNm());
        user.setUserEmlAddr(userDto.getUserEmlAddr());
        user.setUserTelno(userDto.getUserTelno());
        user.setUserAddr(userDto.getUserAddr());
        user.setUserAprvYn(userDto.getUserAprvYn());
        user.setDept(dept);

        return user;
    }
}
