package com.wyl.lottery.weixin.client.enums;


import com.wyl.lotterycommon.utils.bean.enums.BaseEnum;

/**
 * 模板通知类型  模板id
 *
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/10/31
 */
public enum TemplateNoticeTypeEnum implements BaseEnum {


    //待揽件
    WAIT_PACKAGE(2, "1v0yVxUbLxRR4CjSQgjhm9ZOqb2r9r6ZkZ1eCaciQIM"),
    //审核
    AUDIT(3, "NRSoAjCvtAtolmDSxDOIQr__jwCrbONYfeFoxL-LQ1Y"),
    //已揽件（寄出）
    PACKAGED(2, "9c7Z7Z8nSJI8TW63lJChsgGkeT-qykLR23XzVUxch7I"),
    //签收
    SIGN(1, "9c7Z7Z8nSJI8TW63lJChsgGkeT-qykLR23XzVUxch7I");

    /**
     * The Type.
     */
    public final int type;
    /**
     * The Name.
     */
    public final String name;

    TemplateNoticeTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getType() {
        return this.type;
    }
}
