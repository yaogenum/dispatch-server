package com.snapshot.person.dispatch.client.core.execute;

import com.snapshot.person.dispatch.client.core.event.ExecuteEvent;
import com.snapshot.person.dispatch.client.core.node.*;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * description: 执行链路
 *
 * @author jiubao@wcai.com - 九宝  every day good lucky
 * @version 1.0.0
 * @Date 2018/6/4 下午5:38
 * @since 1.0.0
 */
@Slf4j
public class ExecuteChain {
    public List<Node> nodes = new LinkedList<Node>() {{
        add(new RecordStartLogNode());
        add(new CheckTableReadyNode());
        add(new CheckTaskLocalIsRunNode());
        add(new ExecuteNode());
        add(new RecordExecuteStatusNode());
        add(new CheckTaskLocalIsEndNode());
        add(new RecordEndLogNode());
    }};

    /**
     * 核心执行流程
     *
     * @param executeEvent
     * @return
     */
    public boolean execute(ExecuteEvent executeEvent) {
        log.info("start execute, eventEvemt:{}", executeEvent);
        boolean executeResult = true;
        Integer step = 1;
        for (Node node : nodes) {
            log.info("index:{},step:{} pre execute, eventEvemt:{}", step, node.getNodeName(), executeEvent);
            node.preExecute(executeEvent);
            executeResult = node.execute(executeEvent);
            node.afterExecute(executeResult, executeEvent);
            log.info("index:{},step:{} after execute,isSuccess:{}, eventEvemt:{}", step, node.getNodeName(), executeResult, executeEvent);
            if (!executeResult) {
                //此处可以添加报警
                return false;
            }
        }

        log.info("end execute, eventEvemt:{}", executeEvent);
        return executeResult;
    }
}
