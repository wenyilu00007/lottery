package com.wyl.lotterytask.gecco.core.pipeline.jd;

import com.wyl.lotterytask.gecco.bean.jd.AllSort;
import com.wyl.lotterytask.gecco.bean.jd.Category;
import com.wyl.lotterytask.gecco.bean.jd.ProductList;
import com.wyl.lotterytask.gecco.core.annotation.PipelineName;
import com.wyl.lotterytask.gecco.core.pipeline.Pipeline;
import com.wyl.lotterytask.gecco.core.request.HttpRequest;
import com.wyl.lotterytask.gecco.core.scheduler.SchedulerContext;
import com.wyl.lotterytask.gecco.core.spider.HrefBean;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
@PipelineName("productListPipeline")
public class ProductListPipeline implements Pipeline<ProductList> {

    @Override
    public void process(ProductList productList) {
        HttpRequest currRequest = productList.getRequest();
        //下一页继续抓取
        int currPage = productList.getCurrPage();
        int nextPage = currPage + 1;
        int totalPage = productList.getTotalPage();
        if(nextPage <= totalPage) {
            String nextUrl = "";
            String currUrl = currRequest.getUrl();
            if(currUrl.indexOf("page=") != -1) {
                nextUrl = StringUtils.replaceOnce(currUrl, "page=" + currPage, "page=" + nextPage);
            } else {
                nextUrl = currUrl + "&" + "page=" + nextPage;
            }
            SchedulerContext.into(currRequest.subRequest(nextUrl));
        }
    }

}






