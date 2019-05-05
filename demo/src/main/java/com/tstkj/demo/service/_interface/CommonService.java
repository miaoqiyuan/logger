package com.tstkj.demo.service._interface;


import java.util.List;

public interface CommonService<V> {

	void add(V v);

	void deleteByPrimary(String id);

	void delete(V v);

	void update(V v);

	V getByPrimary(String id);

	List<V> getAll();

	List<V> getAll(V v);

	Integer getCount();

	boolean queryRepeat(String colum, String value, String id, Class<V> tClass);

}
