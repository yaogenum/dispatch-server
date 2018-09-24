package com.snapshot.person.dispatch.client.core.event;

import lombok.Data;

import java.util.List;

/**
 * description: 事件处理信息
 *
 * @author jiubao@wcai.com - 九宝  every day good lucky
 * @version 1.0.0
 * @Date 2018/6/4 下午4:08
 * @since 1.0.0
 */
@Data
public class ExecuteEvent {
    ExecuteEvent(EventData eventData) {
        setEventData(eventData);
    }

    /**
     * 事件属性
     */
    private EventData eventData;
    /**
     * 执行步骤的数据记录
     */
    private List<StepData> stepData;
}
