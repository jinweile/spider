package com.etaoshi.spider.comm;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * DESede对称加密算法
 * 
 * @author kongqz
 * */
public class DESedeCoder {
	/**
	 * 密钥算法
	 * */
	public static final String KEY_ALGORITHM = "DESede";

	/**
	 * 加密/解密算法/工作模式/填充方式
	 * */
	public static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";

	/**
	 * 公钥
	 */
	private static final String hexString = "0123456789ABCDEF";

	/**
	 * 
	 * 生成密钥
	 * 
	 * @return byte[] 二进制密钥
	 * */
	private static String initkey() throws Exception {
		// 实例化密钥生成器
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		// 初始化密钥生成器
		kg.init(168);
		// 生成密钥
		SecretKey secretKey = kg.generateKey();
		// 获取二进制密钥编码形式
		return Base64.encodeBase64String(secretKey.getEncoded());
	}

	/**
	 * 转换密钥
	 * 
	 * @param key
	 *            二进制密钥
	 * @return Key 密钥
	 * */
	private static Key toKey(byte[] key) throws Exception {
		// 实例化Des密钥
		DESedeKeySpec dks = new DESedeKeySpec(key);
		// 实例化密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory
				.getInstance(KEY_ALGORITHM);
		// 生成密钥
		SecretKey secretKey = keyFactory.generateSecret(dks);
		return secretKey;
	}

	/**
	 * 加密数据
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @return byte[] 加密后的数据
	 * */
	public static String encrypt(String data, String key) throws Exception {
		// 还原密钥
		Key k = toKey(key.getBytes());
		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		// 执行操作
		return Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
	}

	/**
	 * 解密数据
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @return byte[] 解密后的数据
	 * */
	public static String decrypt(String data, String key) throws Exception {
		// 欢迎密钥
		Key k = toKey(key.getBytes());
		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		// 执行操作
		return new String(cipher.doFinal(Base64.decodeBase64(data)));
	}

	/**
	 * 进行加解密的测试
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String str = "DESede";
		System.out.println("原文：\t" + str);
		// 初始化密钥
		String key = DESedeCoder.initkey();
		System.out.println("密钥：\t" + key);
		// 加密数据
		String data = DESedeCoder.encrypt(str, key);
		System.out.println("加密后：\t" + data);
		// 解密数据
		data = DESedeCoder.decrypt(data, key);
		System.out.println("解密后：\t" + data);
	}

}
