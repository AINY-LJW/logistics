package com.logistics.feign.consumer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PageController {
	@Autowired
	private HttpServletRequest request;
	/**=======================================以下公司信誉档案相关=================================*/
	/**
	 * 物流配送最优路径
	 * TODO
	 * @param 
	 * @return String
	 */
	@GetMapping(value = "logistics")
	public String showOwnCompanyCreditPage(Model model) {
		return "logistics.html";
	}
	/**
	 * 所有农产品
	 * TODO
	 * @param 
	 * @return String
	 */
	@RequestMapping(value = "allCompanyCredit", method = RequestMethod.GET)
	public String showAllCompanyCreditPage(Model model) {
		return "allCompanyCredit.html";
	}
	/**=======================================以下简历相关=================================*/
	/**
	 * 上传json数据保存简历页面
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "uploadResume", method = RequestMethod.GET)
	public String showuploadResumePage(Model model) {
		return "uploadResume.html";
	}
	
	/**
	 * 上传json数据保存评论页面
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "allResume", method = RequestMethod.GET)
	public String showAllResume(Model model) {
		return "allResume.html";
	}
	/**=======================================分割线=================================*/
	/**
	 * 进入评论列表页
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "allComment", method = RequestMethod.GET)
	public String getAllComment(Model model) {
//		 List<Comment> comments=restTemplate.getForObject("http://porvider-user/user/getList", List.class);
//		 List<Comment> comments = userFeignClient.getCommentList();
//	     model.addAttribute("comments", comments);
		return "allComment.html";
	}

	/**
	 * 进入登陆页面
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showindex(Model model) {
		return "login.html";
	}

	/**
	 * 进入首页
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "homePage", method = RequestMethod.GET)
	public String showHomePage(Model model) {
		model.addAttribute("identity", request.getSession().getAttribute("identity"));
		return "homePage.html";
	}
	
	/**
	 * 进入首页
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHomePage1(Model model) {
		model.addAttribute("identity", request.getSession().getAttribute("identity"));
		return "homePage.html";
	}

	/**
	 * 首页里的首页。。
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "homePageIn", method = RequestMethod.GET)
	public String showHomePageIn(Model model) {
		return "homePageIn.html";
	}

	/**
	 * 上传json数据保存评论页面
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "uploadJson", method = RequestMethod.GET)
	public String showUploadJson(Model model) {
		return "uploadJson.html";
	}

	/**
	 * 产品平均分页面
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "average", method = RequestMethod.GET)
	public String showAverage(Model model) {
		return "average.html";
	}

	/**
	 * 云标签页面
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "textCloud", method = RequestMethod.GET)
	public String showTextCloud(Model model) {
		return "textCloud.html";
	}

	/**
	 * 评论分类统计页面
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "commentStatistics", method = RequestMethod.GET)
	public String showcommentStatistics(Model model) {
		return "commentStatistics.html";
	}
	
	@RequestMapping(value = "exit", method = RequestMethod.GET)
	public String exit(Model model) {
		try {
			request.getSession().removeAttribute("identity");
			request.getSession().removeAttribute("identity");
			return "login.html";
		} catch (Exception e) {
			return "homePage.html";
		}
	}
}
