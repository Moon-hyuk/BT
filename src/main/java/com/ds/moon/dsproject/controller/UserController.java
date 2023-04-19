package com.ds.moon.dsproject.controller;

import java.lang.ProcessBuilder.Redirect;
import java.security.Provider.Service;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.ds.moon.dsproject.dto.HbDto;
import com.ds.moon.dsproject.dto.UserDto;
import com.ds.moon.dsproject.dto.UserHbDto;
import com.ds.moon.dsproject.entity.Dept;
import com.ds.moon.dsproject.entity.Hb;
import com.ds.moon.dsproject.entity.User;
import com.ds.moon.dsproject.entity.UserHb;
import com.ds.moon.dsproject.service.UserService;
import com.ds.moon.dsproject.service.DeptService;
import com.ds.moon.dsproject.service.HbService;
import com.ds.moon.dsproject.service.UserHbService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private HbService hbService;
	@Autowired
	private UserHbService userHbService;

	@GetMapping(value = "/index")
	public String index() {
		return "/index";
	}

	@GetMapping(value = "/test")
	public String test(Model model) {
		model.addAttribute("test", "테스트중이용");
		return "/test";
	}

	@GetMapping(value = "/list")
	public String UserList(Model model, String searchKeyword, String userId) {
		List<User> userlist = userService.getListUser();
		List<Dept> deptlist = deptService.getListDept();
		List<Hb> hblist = hbService.getListHb();
		List<UserHb> userHblist = userHbService.getList();

		User user = new User();
		// System.out.println("우ㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜ"+userId);
		// System.out.println("우ㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜ"+searchKeyword);
		//검색
		if (searchKeyword == null) {
			userlist = userService.getListUser();
			if (userId == null) {
				userId = "";
			} else {
				user = userService.getUserInfo(userId);
				System.out.println("컨유오컹커너언ㄹ머" + user);
			}
		} else {
			userlist = userService.getListUserNm(searchKeyword);
			if (userId == null) {
				userId = "";
			} else {
				user = userService.getUserInfo(userId);
				System.out.println("컨유오컹커너언ㄹ머" + user);
			}
		}
		//취미
		if(userId!=null){
			List<UserHb> searchUserHbList = userHbService.selectUserIdByHb(userId);
			model.addAttribute("serchuserhblist", searchUserHbList);
			System.out.println("검색된 유저허비리스트"+searchUserHbList);
		}
		

		model.addAttribute("userhb", userHblist);
		model.addAttribute("userinfo", user);
		model.addAttribute("deptlist", deptlist);
		model.addAttribute("hblist", hblist);
		model.addAttribute("userlist", userlist);

		return "userlist";
	}

	@GetMapping(value = "/sign")
	public String UserSign(Model model) {
		List<Dept> deptlist = deptService.getListDept();
		List<Hb> hblist = hbService.getListHb();
		model.addAttribute("deptlist", deptlist);
		model.addAttribute("hblist", hblist);
		return "usersign";
	}

	@PostMapping(value = "/user/sign")
	public String user_sign_proc(UserDto userDto, HbDto hbDto) {
		User user = User.createUser(userDto);
		Hb hb = Hb.createDept(hbDto);

		UserHbDto userHbDto = new UserHbDto();
		userHbDto.setUserId(userDto.getUserId());
		userHbDto.setUserHbCd(hbDto.getHbCd());

		UserHb userHb = UserHb.createUserHb(userHbDto);
		System.out.println("유저아이디"+userDto.getUserId());
		System.out.println("hbCd"+hbDto.getHbCd());
		System.out.println("여기에 들어옴?"+ user);
		System.out.println("여기에 들어옴?ss"+ hb);
		System.out.println("여기온 하비"+hb.getHbCd());
		System.out.println("userHb"+userHb);

		//자르기
		String[] hbList = hb.getHbCd().split(",");
		//유저 먼저 등록 (pk라 먼저해야됨)
		userService.saveUser(user);

		//취미 하나씩 잘라넣기
		for(int i=0; i<hbList.length; i++){
			userHb.getHb().setHbCd(hbList[i]);// 취미 코드 넣기
			userHb.getUser().setUserId(userDto.getUserId());//유저아이디 넣기

			System.out.println(userDto.getUserId() + hbList[i]);
			System.out.println("저장전 "+userHb);
			userHbService.saveUserHb(userHb);
		}
		
		

		return "redirect:/list";
	}
	
	@PostMapping(value ="/user/modify")
	public String user_modify_proc(UserDto userDto){
		User user = User.createUser(userDto);
		userService.saveUser(user);

		return "redirect:/list";
	}

}