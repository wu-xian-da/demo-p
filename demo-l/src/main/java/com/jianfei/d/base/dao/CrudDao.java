/**
  *project demo-l
  *@author changchun.wu
  *2017年6月22日下午5:49:38
  */
package com.jianfei.d.base.dao;

import java.util.List;

import com.jianfei.d.entity.common.Page;

public interface CrudDao<T> extends BaseDao {
	
	/***
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(Long id);
	
	/****
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T getEntity(T entity);
	
	/******
	 * 获取多条数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);
	
	/***
	 * 分页查询
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(T entity);
	
	/*****
	 * 查询所有数据列表
	 * @param entity
	 * @return
	 */
	public List<T> findAllCond(T entity);
	
	/***
	 * 查询所有数据
	 * @return
	 */
	public List<T> findAll();
	
	/***
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	
	/***
	 * 批量插入数据
	 * @param entitys
	 * @return
	 */
	public int insertBatch(List<T> entitys);
	
	/***
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	/****
	 * 批量更新数据
	 * @param entitys
	 * @return
	 */
	public int updateBatch(List<T> entitys);
	
	/***
	 * 删除数据
	 * @param entity
	 * @return
	 */
	public int deleteEntity(T entity);
	
	/***
	 * 删除数据
	 * @param id
	 * @return
	 */
	public int delete(Long id);
	
	/****
	 * 删除所有
	 * @return
	 */
	public int deleteAll();
}
