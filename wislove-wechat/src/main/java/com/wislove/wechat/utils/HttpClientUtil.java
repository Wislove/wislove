package com.wislove.wechat.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;


/**
 * http 链接工具类
 * 
 * @author: 廖双龙
 * @date: 2018年7月16日上午10:47:45
 */
public class HttpClientUtil {

	private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000)
			.setConnectionRequestTimeout(15000).build();

	/**
	 * 发送http请求
	 * 
	 * @param url
	 * @param method
	 *            目前支持Get和Post请求
	 * @return String
	 */
	public static String sendHttpRequest(String url, Map<String, String> headers, Map<String, String> params,
			String method) {
		if (method == null || "".equals(method)) {
			method = "GET";
		}

		return "";
	}
	
	
	public static void main(String[] args) {
		String url = "https://api.weixin.qq.com/cgi-bin/token";
		Map<String, String> params = new HashMap<>();
		params.put("grant_type", "client_credential");
		params.put("appid", "wxc7ca1dfd1ff1d99f");
		params.put("secret", "7b80c66a1c7bb308dd3de189c2b57335");
		
		System.out.println(sendGetRequest(url, null, params));
	}
	
	/**
	 * 发送get请求
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @return String
	 */
	public static String sendGetRequest(String url, Map<String, String> headers, Map<String, String> params) {
		
		// 设置参数
		StringBuilder httpUrl = new StringBuilder(url);
		if (url.contains("?")){
			httpUrl.append("&");		
		}else{
			httpUrl.append("?");
		}
		
		// 将参数以?k1=v1&k2=v2链接起来
		Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, String> entry = iterator.next();
            Object key = entry.getKey();
            httpUrl.append(key);
            httpUrl.append('=');
            String value = entry.getValue();
            httpUrl.append(value);
            if (iterator.hasNext())
            {
            	httpUrl.append("&");
            }
        }
		
        HttpGet httpGet = new HttpGet(httpUrl.toString());
		if (!CollectionUtils.isEmpty(headers)) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}
		}

		return sendGetMethod(httpGet);
	}

	/**
	 * 发送get请求
	 * 
	 * @param httpGet
	 * @return String
	 * @author 廖双龙
	 */
	private static String sendGetMethod(HttpGet httpGet) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpGet.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				System.out
						.println("HttpClientUtil--Get request failed,code=" + response.getStatusLine().getStatusCode());
			}
			responseContent = EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			HttpClientUtils.closeQuietly(response);
			HttpClientUtils.closeQuietly(httpClient);
		}
		return responseContent;
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 */
	public static String sendHttpPost(String httpUrl) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param params
	 *            参数
	 */
	public static String sendHttpPostWithData(String httpUrl, Map<String, String> params) {
		return sendHttpPost(httpUrl, params, null);
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param headers
	 *            Header
	 */
	public static String sendHttpPostWithHeader(String httpUrl, Map<String, String> headers) {
		return sendHttpPost(httpUrl, null, headers);
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param stringEntity
	 *            提交的字符串数据，比如xml、json等
	 * @return
	 */
	public static String sendHttpPost(String httpUrl, String stringEntity) {
		HttpPost httpPost = new HttpPost(httpUrl);
		if (stringEntity != null) {
			try {
				httpPost.setEntity(new StringEntity(stringEntity));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param stringEntity
	 *            提交的字符串数据，比如xml、json等
	 * @return
	 */
	public static String sendHttpPost(String httpUrl, String stringEntity, String charset) {
		HttpPost httpPost = new HttpPost(httpUrl);
		if (stringEntity != null) {
			httpPost.setEntity(new StringEntity(stringEntity, charset));
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param params
	 *            参数
	 * @param headers
	 *            Header
	 * @return
	 */
	public static String sendHttpPost(String httpUrl, Map<String, String> params, Map<String, String> headers) {
		HttpPost httpPost = new HttpPost(httpUrl);
		if (params != null) {
			List<NameValuePair> valuePairList = new ArrayList<NameValuePair>();
			params.forEach((k, v) -> {
				if (v != null) {
					valuePairList.add(new BasicNameValuePair(k, v));
				}
			});
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(valuePairList, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (headers != null) {
			headers.forEach((k, v) -> {
				if (v != null) {
					httpPost.addHeader(k, v);
				}
			});
		}
		return sendHttpPost(httpPost);
	}

	/**
	 * 发送Post请求
	 * 
	 * @param httpPost
	 * @return
	 */
	private static String sendHttpPost(HttpPost httpPost) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				System.out.println(
						"HttpClientUtil--post request failed,code=" + response.getStatusLine().getStatusCode());
			}
			responseContent = EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			HttpClientUtils.closeQuietly(response);
			HttpClientUtils.closeQuietly(httpClient);
		}
		return responseContent;
	}
}