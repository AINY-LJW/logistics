package com.logistics.feign.consumer.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.common.domain.User;
import com.logistics.feign.consumer.feign.UserFeignClient;
import com.logistics.util.R;

@RestController
public class CommentController {
//	@Autowired 
//	private RestTemplate restTemplate;
	// 使用 DiscoveryClient 寻找服务提供者
//	@Autowired
//	 private DiscoveryClient discoveryClient;
	@Autowired
	private UserFeignClient userFeignClient;
	@Autowired
	private HttpServletRequest request;
//	@GetMapping("/getUser")
//	public String getUser() {
//		return restTemplate.getForObject("http://127.0.0.1:8801/user/getName", String.class);
//		 List<ServiceInstance> list = discoveryClient.getInstances("porvider-user");
//		   if (list.size() > 0) {
//		      String url = list.get(0).getUri().toString();
//		      return restTemplate.getForObject(url + "/user/getName", String.class);
//		   } else {
//		      return null;
//		   }
		// RestTemplate 甚至可以直接通过服务注册名称去调用API，这是利用Ribbon后开启了虚拟主机的能力。
//		return restTemplate.getForObject("http://porvider-user/user/getName", String.class);
//		return userFeignClient.getUser();
//	}

	@PostMapping(value = "changePwd")
	public R changePwd(@RequestBody @RequestParam("phone") String name,@RequestParam("pwd") String pwd) {
		return userFeignClient.changePwd(name, pwd);
	}

	/**
	 * 登陆验证   session添加
	 * 
	 * @param name
	 * @param pwd
	 * @param request
	 * @return User
	 */
	@PostMapping(value = "loginForm")
	 public User ifUserExist(@RequestBody @RequestParam("form-username") String name,@RequestParam("form-password") String pwd,@RequestParam("form-identity") String identity) {
		User ifUserExist = userFeignClient.ifUserExist(name, pwd,identity);
		request.getSession().setAttribute("loginUser", ifUserExist);
		// 身份		
		request.getSession().setAttribute("identity", identity);
		return ifUserExist;
	}
	@PostMapping(value = "exitLogin")
	 public void exitLogin() {
		request.getSession().removeAttribute("loginUser");
	}
	/**
	 * 获取json数据生成云图
	 * 
	 * @return String
	 */
	@GetMapping("getTextCloudJson")
	public String getTextCloudJson() {
		return userFeignClient.getTextCloudJson();
	}
	
}
