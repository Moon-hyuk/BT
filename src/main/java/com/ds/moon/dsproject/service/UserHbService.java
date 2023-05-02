package com.ds.moon.dsproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ds.moon.dsproject.dto.UserHbDto;
import com.ds.moon.dsproject.entity.UserHb;
import com.ds.moon.dsproject.repository.UserHbRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHbService {

    private final UserHbRepository userHbRepository;

    public List<UserHb> getList() {

        return userHbRepository.findAll();
    }

    public UserHb userHbdata(UserHbDto userHbDto) {
        // 잘라서 넣기

        userHbDto.setUserId(userHbDto.getUserId());
        userHbDto.setUserHbCd(userHbDto.getUserHbCd());
        UserHb userHb = UserHb.createUserHb(userHbDto);

        return userHb;
    }

    public void delete(UserHb userHb) {
        userHbRepository.deleteByUserUserId(userHb.getUser().getUserId());
    }

    public void saveUserHb(UserHbDto userHbDto) {
        UserHb userHb = userHbdata(userHbDto);
        delete(userHb);
        if (userHbDto.getUserHbCd() != null) {
            String[] hbList = userHbDto.getUserHbCd().split(",");

            for (int i = 0; i < hbList.length; i++) {
                userHb.getHb().setHbCd(hbList[i]);// 취미 코드 넣기
                userHb.getUser().setUserId(userHbDto.getUserId());// 유저아이디 넣기

                userHbRepository.save(userHb);
            }
        }

    }

    public List<UserHb> selectUserIdByHb(String userId) {
        return userHbRepository.findAllByUserUserId(userId);
    }

}
