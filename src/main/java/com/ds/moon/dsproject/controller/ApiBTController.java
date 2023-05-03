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
public class ApiBTController {

	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private HbService hbService;
	@Autowired
	private UserHbService userHbService;

	//부서 목록표
	@GetMapping(value = "/dplist")
	public List<Dept> getDpList() {
		List<Dept> deptlist = deptService.getListDept();
		return deptlist;
	}

	//취미 목록표
	@GetMapping(value = "/hblist")
	public List<Hb> getHbList() {
		List<Hb> Hbtlist = hbService.getListHb();
		return Hbtlist;
	}

	//회원 저장
	@PostMapping(value = "/sign/user")
	public ResponseEntity<User> user_sign_proc(@RequestBody UserDto userdto) {
		User user = User.createUser(userdto);
		userService.saveUser(user);

		return ResponseEntity.ok(user);
	}

	//회원별 취미저장
	@PostMapping(value = "/sign/hb")
	public ResponseEntity<UserHbDto> hb_sign_proc(@RequestBody UserHbDto userHbDto) {

		userHbService.saveUserHb(userHbDto);

		return ResponseEntity.ok(userHbDto);
	}

	//회원목록
	@GetMapping(value = "/list")
	public List<User> UserList(String searchKeyword) {
		return userService.getListUser(searchKeyword);
	}

	//회원별 정보 출력
	@GetMapping(value = "/info")
	public User UserInfo(String userId) {


		return userService.getUserInfo(userId);
	}
	//회원별 취미 출력
	@GetMapping(value = "/userhblist")
	public String UserHbList(String userId) {
		String hbList = userHbService.selectUserIdByHb(userId);

		return hbList;
	}

	//취미 삭제
	@PostMapping(value = "/hb/delete")
	public ResponseEntity<UserHbDto> hb_delete_proc(@RequestBody UserHbDto userHbDto) {
		userHbService.delete(userHbDto.getUserId());

		return ResponseEntity.ok(userHbDto);
	}

	//회원 삭제
	@PostMapping(value = "/user/delete")
	public ResponseEntity<User> user_delete_proc(@RequestBody UserDto userDto) {
		User user = User.createUser(userDto);

		userService.deleteUserId(user);

		return ResponseEntity.ok(user);
	}

}