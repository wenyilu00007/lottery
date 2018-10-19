package com.wyl.lotterycommon.privoder;

import com.wyl.lottery.service.id.SnowflakeIdWorker;
import com.wyl.lotterycommon.domain.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertDbPrivoder {
    @Autowired
    SnowflakeIdWorker snowflakeIdWorker;

    public Long nextId() {
        return snowflakeIdWorker.nextId();
    }

    public void saveBuild(Base base){
        long now = System.currentTimeMillis();
        base.setId(nextId());
        base.setCreatetime(now);
        base.setUpdatetime(now);
    }
}
