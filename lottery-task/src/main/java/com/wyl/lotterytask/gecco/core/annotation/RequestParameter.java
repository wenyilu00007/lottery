package com.wyl.lotterytask.gecco.core.annotation;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParameter {

	String value() default "";
	
}
