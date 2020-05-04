package com.logistics.feign.provider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.logistics.feign.provider.dao.UserMapper;
import com.logistics.feign.provider.domain.User;
import com.logistics.feign.provider.domain.UserExample;
import com.logistics.feign.provider.domain.UserExample.Criteria;
import com.logistics.util.R;

/**
 * 
 * 简述部分:用户
 *
 * @author helantian
 * @version 2020年1月17日
 */
@RestController
public class UserController {
	@Autowired
	private UserMapper mapper;
	
//	@Autowired
//	private CompanyUserMapper companyUser;
	@SuppressWarnings("unused")
	@PostMapping(value = "/user/loginForm")
	 public User ifUserExist(@RequestBody @RequestParam("form-username") String name,@RequestParam("form-password") String pwd,@RequestParam("form-identity") String identity) {
		if ("company".equals(identity)) {
//			return getCompanyUser(name, pwd);
			return null;
		} else if ("user".equals(identity)){
			return getUser(name, pwd) ;
		}else {
			return null;
		}
	}
	@PostMapping(value = "/user/changePwd")
	public R changePwd(@RequestBody @RequestParam("phone") String name,@RequestParam("pwd") String pwd) {
		
		User record = new User();
		try {
			record.setPhoneNumber(name);
			record.setPassword(pwd);
			if(mapper.updateUserPwd(record ) != 0) {
				return R.ok();
			}
			return R.error("数据发生变化");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return R.error("发生异常");
		}
		
	}
	/**
	 * 普通用户
	 * TODO
	 * @param 
	 * @return User
	 */
	private User getUser(String name, String pwd) {
		UserExample example=new UserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andPhoneNumberEqualTo(name);
		createCriteria.andPasswordEqualTo(pwd);
		List<User> selectByExample = mapper.selectByExample(example);
		if(selectByExample.size()!=0) {
			return selectByExample.get(0);
		}else {
			// 简历		
			return null;
		}
	}


}
