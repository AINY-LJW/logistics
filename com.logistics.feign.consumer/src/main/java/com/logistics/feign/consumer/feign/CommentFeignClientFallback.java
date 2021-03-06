package com.logistics.feign.consumer.feign;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.logistics.common.domain.User;
import com.logistics.util.EasyUIDataGridResult;
import com.logistics.util.R;

import feign.hystrix.FallbackFactory;
/** 
 * <p>TODO 依赖服务失效之后的处理
 * 	微服务的容错处理Hystrix </p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>
 *
 * @author helantian
 * @version 2019年1月21日
 */
@Component
public class CommentFeignClientFallback implements FallbackFactory<UserFeignClient>{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public UserFeignClient create(Throwable cause) {
		logger.error("调用失败"+cause.getMessage().toString());
		return new UserFeignClient() {

			@Override
			public String getTextCloudJsonForIndustry() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public R changeCompanyCrediState(int id, boolean state) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public EasyUIDataGridResult getOwnCompanyCredit(int pageNum, int pageSize, String asin, String reviewerName,
					String keyWord, int companyId) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public User ifUserExist(String name, String pwd, String identity) {
				logger.error("登录异常");
				return null;
			}

			@Override
			public String getTextCloudJson() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public R getAll() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public EasyUIDataGridResult getAllProducts(int pageNum, int pageSize, String name, String classtype,
					String place, String other) {
				logger.error("获取农产品异常");
				return null;
			}

			@Override
			public EasyUIDataGridResult getAllOrder(int pageNum, int pageSize, String name, String issend, String place,
					String isend) {
				logger.error("获取订单异常");
				return null;
			}

			@Override
			public R getPlanList(Map<String, Object> datas) {
				logger.error("规划路线失败");
				return null;
			}

			@Override
			public R changePwd(String name, String pwd) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

}
