package com.logistics.feign.consumer.feign;

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
 * @author lijiawen
 * @version 2019年1月21日
 */
@Component
public class CommentFeignClientFallback implements FallbackFactory<UserFeignClient>{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public UserFeignClient create(Throwable cause) {
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
		};
	}

}
