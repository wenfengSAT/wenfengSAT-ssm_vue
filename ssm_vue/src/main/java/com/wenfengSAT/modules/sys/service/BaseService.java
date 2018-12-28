package com.wenfengSAT.modules.sys.service;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {

	/**
	 * 查询列表
	 */
	List<T> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存用户
	 */
	boolean save(T t);
	
	/**
	 * 修改用户
	 */
	boolean update(T t);
}
