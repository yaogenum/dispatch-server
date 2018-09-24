package com.snapshot.person.dispatch.client.core.node;

import com.snapshot.person.dispatch.client.core.event.ExecuteEvent;

/**
 * description: 执行节点基本定义接口
 *
 * @author jiubao@wcai.com - 九宝  every day good lucky
 * @version 1.0.0
 * @Date 2018/6/4 下午3:42
 * @since 1.0.0
 */

public interface Node {

    /**
     * 执行前预处理
     *
     * @param executeEvent
     */
    void preExecute(ExecuteEvent executeEvent);

    /**
     * 执行完成后善后处理
     *
     * @param executeResult
     * @param executeEvent
     */
    void afterExecute(boolean executeResult, ExecuteEvent executeEvent);

    /**
     * 执行+执行+执行
     *
     * @param executeEvent
     * @return
     */
    Boolean execute(ExecuteEvent executeEvent);

    /**
     * 执行节点的名称
     *
     * @return
     */
    String getNodeName();
}
