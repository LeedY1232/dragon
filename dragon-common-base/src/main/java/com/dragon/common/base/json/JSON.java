package com.dragon.common.base.json;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JavaType;

public class JSON {

	private static final JsonMapper mapper = JsonMapper.nonNullMapper();
	
	public static String toJson(Object object) {
		return mapper.toJson(object);
	}

	public static <T> T parseObject(String jsonString, Class<T> clazz) {
		return mapper.fromJson(jsonString, clazz);
	}

	/**
	 * 解析 JSONString，如果 JSONString 为空串，返回 null
	 */
	public static <T> T parseNotBlank(String jsonString, Class<T> clazz) {
		return StringUtils.isNotBlank(jsonString) ? mapper.fromJson(jsonString, clazz) : null;
	}
	
	public static <T> List<T> parseArray(String jsonString, Class<T> clazz) {
		JavaType jt = mapper.createCollectionType(List.class, clazz);
		return mapper.fromJson(jsonString, jt);
	}
	
	public static <K, V> HashMap<K, V> parseMap(String jsonString, Class<K> kClazz, Class<V> vClazz) {
		JavaType jt = mapper.createCollectionType(HashMap.class, kClazz, vClazz);
		return mapper.fromJson(jsonString, jt);
	}

}
