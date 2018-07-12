package com.wislove.wechat.utils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 验证类
 * 
 * @author: 廖双龙
 * @date: 2018年7月12日下午3:53:02
 */
public class CheckUtils {
	// 和微信公众平台配置的token须保持一致
	private static final String token = "jby646277";

	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		System.out.println(signature);
		String[] arr = new String[] { token, timestamp, nonce };
		// 字典排序
		Arrays.sort(arr);
		// 生成字符串
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		// sha1加密
		String temp = getSha1(content.toString());
		return temp.equals(signature);
	}

	private static String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		MessageDigest mdTemp;
		try {
			mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));
			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b0 = md[i];
				buf[k++] = hexDigits[b0 >>> 4 & 0xf];
				buf[k++] = hexDigits[b0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}
}
