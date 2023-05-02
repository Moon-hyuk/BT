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

    //취미 저장 변환
    public UserHb userHbdata(UserHbDto userHbDto) {
        userHbDto.setUserId(userHbDto.getUserId());
        userHbDto.setUserHbCd(userHbDto.getUserHbCd());
        UserHb userHb = UserHb.createUserHb(userHbDto);

        return userHb;
    }

    //취미 삭제
    public void delete(String userId) {
        userHbRepository.deleteByUserUserId(userId);
    }

    //취미 저장
    public void saveUserHb(UserHbDto userHbDto) {
        UserHb userHb = userHbdata(userHbDto);
        delete(userHb.getUser().getUserId());
        if (userHbDto.getUserHbCd() != null) {
            String[] hbList = userHbDto.getUserHbCd().split(",");

            for (int i = 0; i < hbList.length; i++) {
                userHb.getHb().setHbCd(hbList[i]);// 취미 코드 넣기
                userHb.getUser().setUserId(userHbDto.getUserId());// 유저아이디 넣기

                userHbRepository.save(userHb);
            }
        }

    }

    //회원별 취미목록
    public String selectUserIdByHb(String userId) {
        String hbList = "";
        List<UserHb> userHbList =  userHbRepository.findAllByUserUserId(userId);
		if (userId != null) {
			for (int i = 0; i < userHbList.size(); i++) {
				hbList += userHbList.get(i).getHb().getHbCd();
			}
		}
        return hbList;
    }

}
