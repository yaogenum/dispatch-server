package com.snapshot.person.dispatch.client.core.node;

import com.snapshot.person.dispatch.client.core.event.ExecuteEvent;

/**
 * description: description
 *
 * @author jiubao@wcai.com - 九宝  every day good lucky
 * @version 1.0.0
 * @Date 2018/6/4 下午5:23
 * @since 1.0.0
 */

public class CheckTaskLocalIsEndNode implements Node{
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
        return "任务调度-检测本地任务状态,判断是否调度成功";
    }
}
