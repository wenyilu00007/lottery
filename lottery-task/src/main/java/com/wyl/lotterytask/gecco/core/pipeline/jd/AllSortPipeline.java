package com.wyl.lotterytask.gecco.core.pipeline.jd;

import com.wyl.lotterytask.gecco.bean.jd.AllSort;
import com.wyl.lotterytask.gecco.bean.jd.Category;
import com.wyl.lotterytask.gecco.core.annotation.PipelineName;
import com.wyl.lotterytask.gecco.core.pipeline.Pipeline;
import com.wyl.lotterytask.gecco.core.request.HttpRequest;
import com.wyl.lotterytask.gecco.core.scheduler.SchedulerContext;
import com.wyl.lotterytask.gecco.core.spider.HrefBean;

import java.util.List;

@PipelineName("allSortPipeline")
public class AllSortPipeline implements Pipeline<AllSort> {

    @Override
    public void process(AllSort allSort) {
        List<Category> categorys = allSort.getMobile();
        for(Category category : categorys) {
            List<HrefBean> hrefs = category.getCategorys();
            for(HrefBean href : hrefs) {
                String url = href.getUrl()+"&delivery=1&page=1&JL=4_10_0&go=0";
                HttpRequest currRequest = allSort.getRequest();
                SchedulerContext.into(currRequest.subRequest(url));
            }
        }
    }

}






