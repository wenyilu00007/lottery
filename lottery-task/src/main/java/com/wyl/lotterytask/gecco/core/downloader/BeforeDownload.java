package com.wyl.lotterytask.gecco.core.downloader;

import com.wyl.lotterytask.gecco.core.request.HttpRequest;

public interface BeforeDownload {
	
	public void process(HttpRequest request);

}
