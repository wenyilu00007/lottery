package com.wyl.lotterytask.gecco.core.dynamic;

import org.reflections.adapters.JavaReflectionAdapter;
import org.reflections.vfs.Vfs.File;

public class GeccoJavaReflectionAdapter extends JavaReflectionAdapter {

	@Override
	public Class<?> getOfCreateClassObject(File file) throws Exception {
		return getOfCreateClassObject(file, GeccoClassLoader.get());
	}

}
