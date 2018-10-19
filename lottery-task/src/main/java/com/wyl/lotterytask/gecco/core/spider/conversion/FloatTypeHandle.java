package com.wyl.lotterytask.gecco.core.spider.conversion;


public class FloatTypeHandle implements TypeHandle<Float> {
	
	@Override
	public Float getValue(Object src) throws Exception {
		return Float.valueOf(src.toString());
	}

}
