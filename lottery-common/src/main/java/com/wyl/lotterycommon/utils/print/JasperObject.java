package com.wyl.lotterycommon.utils.print;

import java.util.List;
import java.util.Map;

/**
 * jsper渲染对象
 * 
 * @author Administrator
 *
 * @param <T>
 *            数据泛型
 */
public class JasperObject<T> {

	// 数据源
	private List<T> dataSource;

	// 参数
	private Map<String, Object> parameter;

	// 模板名
	private String templateName;

	public JasperObject(List<T> dataSource, String templateName) {
		super();
		this.dataSource = dataSource;
		this.templateName = templateName;
	}

	public JasperObject(List<T> dataSource, Map<String, Object> parameter, String templateName) {
		super();
		this.dataSource = dataSource;
		this.parameter = parameter;
		this.templateName = templateName;
	}

	public List<T> getDataSource() {
		return dataSource;
	}

	public void setDataSource(List<T> dataSource) {
		this.dataSource = dataSource;
	}

	public Map<String, Object> getParameter() {
		return parameter;
	}

	public void setParameter(Map<String, Object> parameter) {
		this.parameter = parameter;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

}
