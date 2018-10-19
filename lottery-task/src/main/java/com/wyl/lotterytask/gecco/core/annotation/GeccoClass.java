package com.wyl.lotterytask.gecco.core.annotation;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GeccoClass {

	Class<?>[] value();
}
