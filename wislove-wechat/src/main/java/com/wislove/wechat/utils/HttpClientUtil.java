package com.wislove.wechat.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * http 链接工具类
* @author: 廖双龙
* @date: 2018年7月16日上午10:47:45
 */
public class HttpClientUtil {
	
    private static RequestConfig requestConfig = RequestConfig.custom()
    		.setSocketTimeout(15000)
    		.setConnectTimeout(15000)
    		.setConnectionRequestTimeout(15000)
            .build();

    /**
     * 发送 post请求
     * 
     * @param httpUrl 地址
     */
    public static String sendHttpPost(String httpUrl) {
    	HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 post请求
     * 
     * @param httpUrl 地址
     * @param params 参数
     */
    public static String sendHttpPostWithData(String httpUrl, Map<String, String> params) {
        return sendHttpPost(httpUrl, params, null);
    }

    /**
     * 发送 post请求
     * 
     * @param httpUrl 地址
     * @param headers Header
     */
    public static String sendHttpPostWithHeader(String httpUrl, Map<String, String> headers) {
        return sendHttpPost(httpUrl, null, headers);
    }

    /**
     * 发送 post请求
     * 
     * @param httpUrl 地址
     * @param stringEntity 提交的字符串数据，比如xml、json等
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
     * @param httpUrl 地址
     * @param stringEntity 提交的字符串数据，比如xml、json等
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
     * @param httpUrl 地址
     * @param params 参数
     * @param headers Header
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
                System.out.println("HttpClientUtil--post request failed,code=" + response.getStatusLine().getStatusCode());
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