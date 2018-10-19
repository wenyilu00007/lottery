package com.wyl.lotterytask.gecco.core.spider.conversion;

public interface TypeHandle<T> {
	
	public T getValue(Object src) throws Exception;

}
