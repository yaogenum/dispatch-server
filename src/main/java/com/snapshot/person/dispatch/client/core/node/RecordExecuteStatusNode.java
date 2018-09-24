package com.snapshot.person.dispatch.client.core.node;

import com.snapshot.person.dispatch.client.core.event.ExecuteEvent;

/**
 * description:执行调度后，内部 将任务调度情况回写到zk
 *
 * @author jiubao@wcai.com - 九宝  every day good lucky
 * @version 1.0.0
 * @Date 2018/6/4 下午5:25
 * @since 1.0.0
 */

public class RecordExecuteStatusNode implements Node {
    @Override
    public void preExecute(ExecuteEvent executeEvent) {

    }

    @Override
    public void afterExecute(boolean executeResult, ExecuteEvent executeEvent) {

    }

    @Override
    public Boolean execute(ExecuteEvent executeEvent) {
        return null;
    }

    @Override
    public String getNodeName() {
        return "任务调度-记录任务状态至注册中心";
    }
}
