package com.wyl.lottery.weixin.client.service;

import com.wyl.lottery.weixin.client.bo.AuditNoticeBO;
import com.wyl.lottery.weixin.client.bo.PackagedAndSignNoticeBO;
import com.wyl.lottery.weixin.client.bo.WaitPackageNoticeBO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/10/31
 */
public interface UserNoticeClient {

    @PostMapping("/ms/waitPackageNotice/v1")
    void waitPackageNotice(@RequestBody WaitPackageNoticeBO waitPackageNoticeBO);

    void auditNotice(@RequestBody AuditNoticeBO auditNoticeBO);

    void packagedAndSignNotice(@RequestBody PackagedAndSignNoticeBO packagedAndSignNoticeBO);

}
