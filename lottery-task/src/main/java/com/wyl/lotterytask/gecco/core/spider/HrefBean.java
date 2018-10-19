package com.wyl.lotterytask.gecco.core.spider;

import com.wyl.lotterytask.gecco.core.annotation.Href;
import com.wyl.lotterytask.gecco.core.annotation.HtmlField;
import com.wyl.lotterytask.gecco.core.annotation.Text;

public class HrefBean implements SpiderBean {

	private static final long serialVersionUID = -3770871271092767593L;

	@Href
	@HtmlField(cssPath="a")
	private String url;
	
	@Text
	@HtmlField(cssPath="a")
	private String title;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
