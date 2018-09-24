package com.snapshot.person.dispatch.client.core.event;

import lombok.Data;

/**
 * description: 执行步骤的数据记录
 *
 * @author jiubao@wcai.com - 九宝  every day good lucky
 * @version 1.0.0
 * @Date 2018/6/4 下午3:49
 * @since 1.0.0
 */
@Data
public class StepData {
    StepData(long eventId, String name, Integer status, String input) {
        this.eventId = eventId;
        this.name = name;
    }

    private String name;

    /**
     * 步骤ID
     */
    private long id = System.currentTimeMillis();
    /**
     * 关联的事件ID
     */
    private long eventId;
    /**
     * 执行状态
     */
    private Integer status = 1;
    /**
     * 错误编码
     */
    private Integer code = 0;
    /**
     * 输入参数
     */
    private String input;
    /**
     * 输出结果
     */
    private String output;
}
