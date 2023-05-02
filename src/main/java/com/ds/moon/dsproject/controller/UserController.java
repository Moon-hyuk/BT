package com.ds.moon.dsproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.ds.moon.dsproject.dto.UserDto;
import com.ds.moon.dsproject.dto.UserHbDto;
import com.ds.moon.dsproject.entity.Dept;
import com.ds.moon.dsproject.entity.Hb;
import com.ds.moon.dsproject.entity.User;
import com.ds.moon.dsproject.service.UserService;
import com.ds.moon.dsproject.service.DeptService;
import com.ds.moon.dsproject.service.HbService;
import com.ds.moon.dsproject.service.UserHbService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bt")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private HbService hbService;
	@Autowired
	private UserHbService userHbService;

	@GetMapping(value = "/dplist")
	public List<Dept> getDpList() {
		List<Dept> deptlist = deptService.getListDept();
		return deptlist;
	}

	@GetMapping(value = "/hblist")
	public List<Hb> getHbList() {
		List<Hb> Hbtlist = hbService.getListHb();
		return Hbtlist;
	}

	@GetMapping(value = "/index")
	public String index() {
		return "/index";
	}

	@PostMapping(value = "/sign/user")
	public ResponseEntity<User> user_sign_proc(@RequestBody UserDto userdto) {
		User user = User.createUser(userdto);
		userService.saveUser(user);

		return ResponseEntity.ok(user);
	}

	@PostMapping(value = "/sign/hb")
	public ResponseEntity<UserHbDto> hb_sign_proc(@RequestBody UserHbDto userHbDto) {

		userHbService.saveUserHb(userHbDto);

		return ResponseEntity.ok(userHbDto);
	}

	@GetMapping(value = "/list")
	public List<User> UserList(String searchKeyword) {
		System.out.println("검색어검색어검색어검색어검색어검색어검색어" + searchKeyword);
		return userService.getListUser(searchKeyword);
	}

	@GetMapping(value = "/info")
	public User UserInfo(String userId) {

		System.out.println("유저아이디유저아이디유저아이디유저아이디유저아이디유저아이디" + userId);

		return userService.getUserInfo(userId);
	}

	@GetMapping(value = "/userhblist")
	public String UserHbList(String userId) {
		String hbList = userHbService.selectUserIdByHb(userId);
		System.out.println("유저어어어허비선택목록!!!!!!!" + hbList);

		return hbList;
	}

	@PostMapping(value = "/hb/delete")
	public ResponseEntity<UserHbDto> hb_delete_proc(@RequestBody UserHbDto userHbDto) {
		userHbService.delete(userHbDto.getUserId());

		return ResponseEntity.ok(userHbDto);
	}

	@PostMapping(value = "/user/delete")
	public ResponseEntity<User> user_delete_proc(@RequestBody UserDto userDto) {
		User user = User.createUser(userDto);

		userService.deleteUserId(user);

		return ResponseEntity.ok(user);
	}

}