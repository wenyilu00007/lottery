package com.wyl.lottery.weixin.client.bo;

import lombok.Data;

@Data
public class PackagedAndSignNoticeBO {

    private Long userId;
    private Long ordersId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }
}
