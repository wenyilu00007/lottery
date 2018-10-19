package com.wyl.lotterytask.gecco.core.spider.conversion;

public class StringTypeHandle implements TypeHandle<String> {

	@Override
	public String getValue(Object src) {
		return src.toString();
	}

}
