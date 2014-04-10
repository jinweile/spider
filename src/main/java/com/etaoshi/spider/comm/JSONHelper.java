package com.etaoshi.spider.comm;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class JSONHelper {

	/**
	 * 默认编码格式
	 */
	private static final String DEFAULT_CHARSET_NAME = "UTF-8";

	/**
	 * 序列化
	 * 
	 * @param object
	 * @return
	 */
	public static <T> String serialize(T object) {
		return JSON.toJSONString(object);
	}

	/**
	 * 反序列化
	 * 
	 * @param string
	 * @param clz
	 * @return
	 */
	public static <T> T deserialize(String string, Class<T> clz) {
		return JSON.parseObject(string, clz);
	}

	/**
	 * 加载json文件
	 * 
	 * @param path
	 * @param clz
	 * @return
	 * @throws IOException
	 */
	public static <T> T load(Path path, Class<T> clz) throws IOException {
		return deserialize(new String(Files.readAllBytes(path),
				DEFAULT_CHARSET_NAME), clz);
	}

	/**
	 * 保存为json文件
	 * 
	 * @param path
	 * @param object
	 * @throws IOException
	 */
	public static <T> void save(Path path, T object) throws IOException {
		if (Files.notExists(path.getParent())) {
			Files.createDirectories(path.getParent());
		}
		Files.write(path, serialize(object).getBytes(DEFAULT_CHARSET_NAME),
				StandardOpenOption.WRITE, StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING);
	}

}
