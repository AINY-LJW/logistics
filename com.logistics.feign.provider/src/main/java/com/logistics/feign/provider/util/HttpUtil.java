package com.logistics.feign.provider.util;

import java.io.ByteArrayOutputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.StreamUtils;

import com.alibaba.fastjson.JSONObject;
/**
 * 
 * 简述部分:HTTP请求调用
 * 
 *
 * @author helantian
 * @version 2020年4月28日
 */
public class HttpUtil {
	/** POST请求 */
	public static JSONObject doPost(String url, JSONObject params) {

		JSONObject result = new JSONObject();
		result.put("success", true);
		result.put("data", null);
		result.put("code", 200);
		result.put("msg", null);

		OutputStream out = null;
		DataOutputStream dataOutputStream = null;
		InputStream in = null;
		ByteArrayOutputStream baos = null;
		try {
			URLConnection urlConnection = new URL(url).openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
			// 设置是否向httpUrlConnection输出，post请求，参数要放在http正文内，因此需要设为true,
			// 默认情况下是false;
			httpUrlConnection.setDoOutput(true);
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			httpUrlConnection.setDoInput(true);
			// 忽略缓存
			httpUrlConnection.setUseCaches(false);
			// 设定请求的方法为"POST"，默认是GET
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.connect();

			// 建立输入流，向指向的URL传入参数

			String queryString = "";

			if (params != null) {
				for (Entry<String, Object> entry : params.entrySet()) {
					queryString += entry.getKey() + "=" + URLEncoder.encode(entry.getValue().toString(), "UTF-8") + "&";
				}
			}
			if (queryString.length() > 0) {
				queryString = queryString.substring(0, queryString.length() - 1);
				out = httpUrlConnection.getOutputStream();
				dataOutputStream = new DataOutputStream(out);
				dataOutputStream.writeBytes(queryString);

				dataOutputStream.flush();
				out.flush();
			}
			// 获得响应状态
			int responseCode = httpUrlConnection.getResponseCode();
			if (HttpURLConnection.HTTP_OK == responseCode) {
				baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				in = httpUrlConnection.getInputStream();
				while ((len = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
					baos.flush();
				}
				result.put("success", true);
				result.put("data", baos.toString("UTF-8"));
				result.put("code", 200);
				result.put("msg", "请求成功");
			} else {
				result.put("success", false);
				result.put("code", responseCode);
				result.put("msg", "请求异常");
			}
		} catch (Exception e) {
			result.put("success", false);
			result.put("code", 500);
			result.put("msg", "请求异常，异常信息：" + e.getClass() + "->" + e.getMessage());
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					result.put("success", false);
					result.put("code", 500);
					result.put("msg", "请求异常，异常信息：" + e.getClass() + "->" + e.getMessage());
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					result.put("success", false);
					result.put("code", 500);
					result.put("msg", "请求异常，异常信息：" + e.getClass() + "->" + e.getMessage());
				}
			}
			if (dataOutputStream != null) {
				try {
					dataOutputStream.close();
				} catch (IOException e) {
					result.put("success", false);
					result.put("code", 500);
					result.put("msg", "请求异常，异常信息：" + e.getClass() + "->" + e.getMessage());
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					result.put("success", false);
					result.put("code", 500);
					result.put("msg", "请求异常，异常信息：" + e.getClass() + "->" + e.getMessage());
				}
			}
		}
		return result;
	}

	/** GET请求 */
	public static JSONObject doGet(String url, JSONObject params) {

		JSONObject result = new JSONObject();
		result.put("success", true);
		result.put("data", null);
		result.put("code", 200);
		result.put("msg", null);
 
		InputStream in = null;
		ByteArrayOutputStream baos = null;
 
		try {
			// URL传入参数
			String queryString = "";
 
			if (params != null) {
				for (Entry<String, Object> entry : params.entrySet()) {
					queryString += entry.getKey()
							+ "="
							+ URLEncoder.encode(entry.getValue().toString(),
									"UTF-8") + "&";
				}
			}
 
			if (queryString.length() > 0) {
				queryString = queryString
						.substring(0, queryString.length() - 1);
 
				url = url + "?" + queryString;
			}
 
			URLConnection urlConnection = new URL(url).openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
			// 设置是否向httpUrlConnection输出，post请求，参数要放在http正文内，因此需要设为true,
			// 默认情况下是false;
			httpUrlConnection.setDoOutput(false);
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			httpUrlConnection.setDoInput(true);
			// 忽略缓存
			httpUrlConnection.setUseCaches(false);
			// 设定请求的方法为"POST"，默认是GET
			httpUrlConnection.setRequestMethod("GET");
			httpUrlConnection.connect();
 
			// 获得响应状态
			int responseCode = httpUrlConnection.getResponseCode();
 
			if (HttpURLConnection.HTTP_OK == responseCode) {
				baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				in = httpUrlConnection.getInputStream();
				while ((len = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
					baos.flush();
				}
 
				result.put("success", true);
				result.put("data", baos.toString("UTF-8"));
				result.put("code", 200);
				result.put("msg", "请求成功");
			} else {
				result.put("success", false);
				result.put("code", responseCode);
				result.put("msg", "请求异常");
			}
		} catch (Exception e) {
			result.put("success", false);
			result.put("code", 500);
			result.put("msg",
					"请求异常，异常信息：" + e.getClass() + "->" + e.getMessage());
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					result.put("success", false);
					result.put("code", 500);
					result.put("msg",
							"请求异常，异常信息：" + e.getClass() + "->" + e.getMessage());
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					result.put("success", false);
					result.put("code", 500);
					result.put("msg",
							"请求异常，异常信息：" + e.getClass() + "->" + e.getMessage());
				}
			}
		}
		return result;
	}
	/*public static void main(String[] args) {
		String url = "http://tyff.test.ngrok.xiaomiqiu.cn/services/businessData/listener/wuhansmzj";
		JSONObject object = doPost(url, null);
		System.out.println(object.toJSONString());
	}*/
	public static String sendHttpRequest(String httpURL,Map<String,String> params) throws Exception{
        //建立URL连接对象
        URL url = new URL(httpURL);
        //创建连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置请求的方式(需要是大写的)
        conn.setRequestMethod("GET");
        //设置需要输出
        conn.setDoOutput(true);
        //判断是否有参数.
        if(params!=null&&params.size()>0){
            StringBuilder sb = new StringBuilder();
            for(Entry<String,String> entry:params.entrySet()){
                sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
            //sb.substring(1)去除最前面的&
            conn.getOutputStream().write(sb.substring(1).toString().getBytes("utf-8"));
        }
        //发送请求到服务器
        conn.connect();
        //获取远程响应的内容.
        String responseContent = StreamUtils.copyToString(conn.getInputStream(),Charset.forName("utf-8"));
        conn.disconnect();
        return responseContent;
    }
}