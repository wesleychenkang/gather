package com.zhongdao.gather.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonMethod {

	/**
	 * 检查身份号码有效性
	 * 
	 * 参考 https://jingyan.baidu.com/article/7f41ececff944a593d095c8c.html?qq-pf-to=pcqq.group
	 * @param str
	 * @return
	 */
	public static boolean checkCardNumber(String str) {

		if (null == str) {
			return false;
		}
		if (str.length() != 18) {
			return false;
		}
		String[] chars = str.split("");

		int[] z = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

		int[] c = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };

		Long sum = 0L;
		// System.out.println("z"+z.length);
		for (int i = 0; i < 17; i++) {
			int a = Integer.valueOf(chars[i]);
			int b = z[i];
			sum = sum + a * b;
		}

		int r = Integer.valueOf(sum % 11 + "");
		String res = c[r] + "";

		if (res.equals(chars[17])) {
			return true;
		}

		return false;
	}

	/**
	 * 通过身份证号码获取年龄
	 * @param str
	 * @return
	 */
	public static long getAge(String str) {
		if (null == str || str.isEmpty())
			return 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date n = new Date();
		Date z = null;
		try {
			z = dateFormat.parse(str.substring(6, 14));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long y = n.getTime() - z.getTime();
		Long l = 365 * 24 * 60 * 60 * 1000L;
		long zt = y / l;
		return zt;
	}
	private static final String HEX_CHARS = "0123456789abcdef";
	private static final String STRING_MD5 = "MD5";
	private static final String STRING_UTF8 = "utf-8";
	public static String stringToMd5(String srcString) {
		String dstString = null;
		try {
			
			MessageDigest messageDigest = MessageDigest.getInstance(STRING_MD5);
			byte[] b = messageDigest.digest(srcString.getBytes(STRING_UTF8));

			StringBuffer tmpSb = new StringBuffer();
			for (byte aB : b) {
				tmpSb.append(HEX_CHARS.charAt((aB >> 4) & 0x0F));
				tmpSb.append(HEX_CHARS.charAt(aB & 0x0F));
			}
			dstString = tmpSb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			dstString = null;
		}
		return dstString;
	}

	private static final String getIpAddr_s1 = "x-forwarded-for";
	private static final String getIpAddr_s2 = "Proxy-Client-IP";
	private static final String getIpAddr_s3 = "WL-Proxy-Client-IP";
	private static final String getIpAddr_unknown = "unknown";
	private static final String getIpAddr_blank = "";

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(getIpAddr_s1);
		if(ip != null && ip.length() > 0){
			int index=ip.indexOf(",");
			if(index>0){
				ip=ip.substring(0, index).trim();
			}
			return ip;
		}
		if (ip == null || ip.length() == 0 || getIpAddr_unknown.equalsIgnoreCase(ip)) {
			ip = request.getHeader(getIpAddr_s2);
		}
		if (ip == null || ip.length() == 0 || getIpAddr_unknown.equalsIgnoreCase(ip)) {
			ip = request.getHeader(getIpAddr_s3);
		}
		if (ip == null || ip.length() == 0 || getIpAddr_unknown.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip != null) {
			return ip;
		}
		return getIpAddr_blank;
	}

	public static String getBase64(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }  
	
	public static String getFromBASE64(String s) {
		byte[] b = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				return new String(b);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new String(b);
	}

	private static final String STRING_regExp = "^1[34578]\\d{9}$";

	public static boolean isPhoneOk(String s) {
		return checkResp(s, STRING_regExp);
	}


	
	private static final String STRING_pwdExp = "^[0-9a-zA-Z_]{6,20}$";
	public static boolean isPasswordOk(String s) {
		return checkResp(s, STRING_pwdExp);
	}
	
	private static final String STRING_codeExp = "^[0-9]{4}$";

	public static boolean isCodeOk(String s) {
		return checkResp(s, STRING_codeExp);
	}

	private static final String STRING_idcardExp = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
	
	public static boolean isIdcardOk(String s){
		return checkResp(s,STRING_idcardExp);
	}
	
	private static final String STRING_nameExp = "^[\u4E00-\u9FA5]{2,5}(\\.[\u4E00-\u9FA5]{2,5})?$";
	
	public static boolean isNameOk(String s){
		return checkResp(s,STRING_nameExp);
	}
	
	public static String createToken(long id){
		String token=stringToMd5(id+""+System.currentTimeMillis());
		return token;
	}
	public static String mergeString(String ...strs){
		String result="";
		for(String str : strs){
			result+=str;
		}
		return result;
	}

	
	private static boolean checkResp(String s,String resp){
		if (s == null || s.length() <= 0) {
			return false;
		}
		Pattern p = Pattern.compile(resp);
		Matcher m = p.matcher(s);
		return m.find();
	}
	
	
	public static String replace(String source,String []args){
		String result=source;
		for(int i=1;i<=args.length;i++){
			result=result.replaceAll(createParam(i), args[i-1]);
		}
		return result;
	}
	private static final String STRING_SHARP="#";
	public static String createParam(int index){
		return STRING_SHARP+index;
	}

	
	public static String messagePassword(String password){
		StringBuilder sb=new StringBuilder(password);
		int start=(password.length()-2)/2;
		sb.replace(start, start+2, "**");
		return sb.toString();
	}


	public static boolean checkSignature(long timestamp,String signature,String params[]){
		if (timestamp == 0 || signature == null||params==null) {
			return false;
		}
		long nowTime = System.currentTimeMillis();
		// 接口请求的时间在600秒钟之前的直接抛弃
		if ((nowTime - timestamp) > 600000) {
			return false;
		}
		// 将方法的参数按字典排序,然后按字典排序将它们加起来的字符串+盐按SHA512加密，和签名作对比
		Arrays.sort(params);
		int length=params.length;
		String signStr="";
		if (params != null &&length>0) {
			StringBuilder sb=new StringBuilder(length);
			for (int i = 0; i < length; i++) {
				if(!StringUtils.isEmpty(params[i])){
					sb.append(params[i]);
				}
			}
			signStr=EncryptUtils.md5(sb.toString());
			//if (signature.equals(EncryptionUtil.SHA512((signStr+salt)))) { 暂时先不做这一步
			if (signature.equals(signStr)){
				return true;
			}
		}
		return false;
	}

	public static String createSalt() {
		String password = "";
		Random r = new Random();
		StringBuilder sb = new StringBuilder(16);
		sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
		int len = sb.length();
		if (len < 16) {
			for (int i = 0; i < 16 - len; i++) {
				sb.append("0");
			}
		}
		String salt = sb.toString();
		password = EncryptUtils.md5(password + salt);
		char[] cs = new char[48];
		for (int i = 0; i < 48; i += 3) {
			cs[i] = password.charAt(i / 3 * 2);
			char c = salt.charAt(i / 3);
			cs[i + 1] = c;
			cs[i + 2] = password.charAt(i / 3 * 2 + 1);
		}
		return new String(cs);
	}

	public static void main(String[] args) {

	}
	
}
