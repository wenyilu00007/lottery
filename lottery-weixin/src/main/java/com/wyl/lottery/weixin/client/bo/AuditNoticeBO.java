package com.wyl.lottery.weixin.client.bo;

import lombok.Data;

@Data
public class AuditNoticeBO {

    private Long userId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
