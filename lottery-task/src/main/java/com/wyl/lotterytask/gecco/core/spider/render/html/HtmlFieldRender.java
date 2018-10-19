package com.wyl.lotterytask.gecco.core.spider.render.html;

import com.wyl.lotterytask.gecco.core.annotation.HtmlField;
import com.wyl.lotterytask.gecco.core.request.HttpRequest;
import com.wyl.lotterytask.gecco.core.response.HttpResponse;
import com.wyl.lotterytask.gecco.core.spider.SpiderBean;
import com.wyl.lotterytask.gecco.core.spider.render.FieldRender;
import com.wyl.lotterytask.gecco.core.spider.render.FieldRenderException;
import com.wyl.lotterytask.gecco.core.utils.ReflectUtils;
import net.sf.cglib.beans.BeanMap;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class HtmlFieldRender implements FieldRender {

	@Override
	public void render(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		Set<Field> htmlFields = ReflectionUtils.getAllFields(bean.getClass(), ReflectionUtils.withAnnotation(HtmlField.class));
		for (Field htmlField : htmlFields) {
			Object value = injectHtmlField(request, response, htmlField, bean.getClass());
			if(value != null) {
				fieldMap.put(htmlField.getName(), value);
			}
		}
		beanMap.putAll(fieldMap);
	}

	private Object injectHtmlField(HttpRequest request, HttpResponse response, Field field, Class<? extends SpiderBean> clazz) {
		HtmlField htmlField = field.getAnnotation(HtmlField.class);
		String content = response.getContent();
		HtmlParser parser = new HtmlParser(request.getUrl(), content);
		// parser.setLogClass(clazz);
		String cssPath = htmlField.cssPath();
		Class<?> type = field.getType();// 属性的类
		boolean isArray = type.isArray();// 是否是数组类型
		boolean isList = ReflectUtils.haveSuperType(type, List.class);// 是List类型
		if (isList) {
			Type genericType = field.getGenericType();// 获得包含泛型的类型
			Class genericClass = ReflectUtils.getGenericClass(genericType, 0);// 泛型类
			if (ReflectUtils.haveSuperType(genericClass, SpiderBean.class)) {
				// List<spiderBean>
				return parser.$beanList(cssPath, request, genericClass);
			} else {
				// List<Object>
				try {
					return parser.$basicList(cssPath, field);
				} catch (Exception ex) {
					//throw new FieldRenderException(field, content, ex);
					FieldRenderException.log(field, content, ex);
				}
			}
		} else if (isArray) {
			Class genericClass = type.getComponentType();
			if (ReflectUtils.haveSuperType(genericClass, SpiderBean.class)) {
				List<SpiderBean> list = parser.$beanList(cssPath, request, genericClass);
				SpiderBean[] a = (SpiderBean[]) Array.newInstance(genericClass, list.size());
				return list.toArray(a);
			} else {
				// List<Object>
				try {
					return parser.$basicList(cssPath, field).toArray();
				} catch (Exception ex) {
					//throw new FieldRenderException(field, content, ex);
					FieldRenderException.log(field, content, ex);
				}
			}
		} else {
			if (ReflectUtils.haveSuperType(type, SpiderBean.class)) {
				// SpiderBean
				return parser.$bean(cssPath, request, (Class<? extends SpiderBean>) type);
			} else {
				// Object
				try {
					return parser.$basic(cssPath, field);
				} catch (Exception ex) {
					//throw new FieldRenderException(field, content, ex);
					FieldRenderException.log(field, content, ex);
				}
			}
		}
		return null;
	}

}
