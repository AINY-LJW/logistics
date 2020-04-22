package com.logistics.feign.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.logistics.common.domain.User;
import com.logistics.util.EasyUIDataGridResult;
import com.logistics.util.R;

/**
 * 
 * 简述部分: * Feign接口，并增加 @FeignClient(name="service-user") 注解用以适应Eureka和Ribbon，
 * 里面的方法是和服务端提供的接口对应的
 *
 * @author WK
 * @version 2020年1月23日
 */
//注解里面写你在  Eureka注册的提供服务者的名字
//增加 fallbackFactory配置   Hystrix容错处理
//@Component
@FeignClient(name="logistics-provider",fallbackFactory = CommentFeignClientFallback.class)
public interface UserFeignClient {
	/**=====================================路径规划==========================================*/
	/**
	 * 所有坐标
	 * TODO
	 * @param 
	 * @return R
	 */
	@PostMapping("/planRote/getall")
	public R getAll() ;
	/**=====================================以下农产品增删改查==========================================*/
	@PostMapping("product/getall")
	EasyUIDataGridResult getAllProducts(@RequestBody @RequestParam("page") int pageNum,
			@RequestParam("rows") int pageSize, @RequestParam(value = "asin", required = false) String name,
			@RequestParam(value = "reviewerName", required = false) String classtype,
			@RequestParam(value = "keyWord", required = false) String place, @RequestParam("identity") String other) ;
	/**
	 * 返回json数据做词云图
	 * 
	 * @return String
	 */
	@GetMapping("/companyCredit/getTextCloudJson")
	public String getTextCloudJsonForIndustry();
	
	@PostMapping("companyCredit/changeCompanyCrediState")
	public R changeCompanyCrediState(@RequestBody @RequestParam("id") int id,
			@RequestParam("state") boolean state) ;
	
	@PostMapping("companyCredit/getOwnCompanyCredit")
	public EasyUIDataGridResult getOwnCompanyCredit(@RequestBody @RequestParam("page") int pageNum,
			@RequestParam("rows") int pageSize, @RequestParam(value = "asin", required = false) String asin,
			@RequestParam(value = "reviewerName", required = false) String reviewerName,
			@RequestParam(value = "keyWord", required = false) String keyWord,@RequestParam("companyId") int companyId);
	/**=====================================以下最短路径规划==========================================*/
	
	/**=====================================以下登录==========================================*/
	/**
	 * 验证登陆
	 * 
	 * @param name
	 * @param pwd
	 * @return User
	 */
	@PostMapping(value = "/user/loginForm")
	public User ifUserExist(@RequestBody @RequestParam("form-username") String name,
			@RequestParam("form-password") String pwd,@RequestParam("form-identity") String identity);

	/**
	 * 返回json数据做词云图
	 * 
	 * @return String
	 */
	@GetMapping("/user/getTextCloudJson")
	public String getTextCloudJson();
}
