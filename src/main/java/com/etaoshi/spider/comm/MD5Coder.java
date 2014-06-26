package com.etaoshi.spider.comm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Coder {

	/**
	 * MD5 32位
	 * 
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encrypt32(String data) throws NoSuchAlgorithmException {
		// 用于加密的字符
		char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		// 使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
		byte[] btInput = data.getBytes();
		// 获得指定摘要算法的 MessageDigest对象，此处为MD5
		// MessageDigest类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
		// 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
		MessageDigest mdInst = MessageDigest.getInstance("MD5");
		// MD5 Message Digest from SUN, <initialized>
		// MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
		mdInst.update(btInput);
		// MD5 Message Digest from SUN, <in progress>
		// 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
		byte[] md = mdInst.digest();
		// 把密文转换成十六进制的字符串形式
		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[k++] = md5String[byte0 >>> 4 & 0xf];
			str[k++] = md5String[byte0 & 0xf];
		}
		// 返回经过加密后的字符串
		return new String(str);
	}

	/**
	 * MD5 16位
	 * 
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encrypt16(String data) throws NoSuchAlgorithmException {
		return encrypt32(data).substring(8, 24);
	}

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		System.out.println(MD5Coder.encrypt16("adsfdsf"));
	}

}
