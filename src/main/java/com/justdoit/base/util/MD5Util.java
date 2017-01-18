package com.justdoit.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class MD5Util {

	private final static Logger logger = Logger.getLogger(MD5Util.class);
	
	 /**
     * 判断字符串是否为空
     * @param s
     * @return 
     */
    public static boolean isEmpty(String s){
        return s == null || s.trim().length() == 0;
    }

    public static boolean isNotEmpty(String s){
        return !isEmpty(s);
    }

	public static String encode(String s){
        if(s == null || s.trim().length() == 0){
            return null;
        }
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("MD5");
        }catch (NoSuchAlgorithmException ex) {
        	logger.error("MD5Util Can't encode to md5 string", ex);
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4',
                             '5', '6', '7', '8', '9',
                             'A', 'B', 'C', 'D', 'E', 'F' };
        md.update(s.getBytes());
        byte[] datas = md.digest();
        int len = datas.length;
        char str[] = new char[len * 2];
        int k = 0;
        for (int i = 0; i < len; i++) {
            byte byte0 = datas[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }
	
	/**
     * 获取文件的后缀名
     * @param filename
     * @return 
     */
    public static String getExt(String filename){
        String ext = null;
        int index = filename.lastIndexOf(".");
        if(index > 0){
            ext = filename.substring(index + 1).toLowerCase();
        }
        return ext;
    }
	
	public static void main(String[] args) {
		String str = encode("123justdoit0:0:0:0:0:0:0:1");
		System.out.println(str);
	}
}
