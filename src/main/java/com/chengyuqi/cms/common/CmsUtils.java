package com.chengyuqi.cms.common;

import org.apache.commons.codec.digest.DigestUtils;

public class CmsUtils {
	/**
	 * 加盐加密
	 * @param str	明文
	 * @param salt   盐（自定义）
	 * @return
	 */
	public static String encryption(String str,String salt) {
		return  DigestUtils.md5Hex(salt+str+salt);
		
	}
}
