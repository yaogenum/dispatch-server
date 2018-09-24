package com.snapshot.person.dispatch.client.core.node;

import com.snapshot.person.dispatch.client.core.event.ExecuteEvent;

/**
 * description: 调用数据平台 table是否ready
 * <p>
 * ready 的标记为
 * <p>
 * 1. 表存在
 * <p>
 * 2. 表若存在日任务,则需要运行成功
 *
 * @author jiubao@wcai.com - 九宝  every day good lucky
 * @version 1.0.0
 * @Date 2018/6/4 下午5:23
 * @since 1.0.0
 */

public class CheckTableReadyNode implements Node {

    @Override
    public void preExecute(ExecuteEvent executeEvent) {

    }

    @Override
    public void afterExecute(boolean executeResult,ExecuteEvent executeEvent) {

    }

    @Override
    public Boolean execute(ExecuteEvent executeEvent) {
        return null;
    }

    @Override
    public String getNodeName() {
        return "任务调度-检测BI表是否准备完成";
    }
}
