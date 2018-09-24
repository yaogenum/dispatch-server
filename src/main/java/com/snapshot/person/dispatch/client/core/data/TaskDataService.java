package com.snapshot.person.dispatch.client.core.data;

import java.util.Set;

/**
 * description: 收集任务执行所需要的数据
 *
 * @author jiubao@wcai.com - 九宝  every day good lucky
 * @version 1.0.0
 * @Date 2018/6/6 上午10:59
 * @since 1.0.0
 */

public interface TaskDataService {
    /**
     * 提供执行任务所有需要的任务信息
     *
     * @param input
     * @return
     */
    Set<TaskData> collectAllTaskData(Object input);

    /**
     * 任务开始调度时,提供接入方进行本地查询是否已经执行过
     *
     * @param taskData
     * @return default false 默认未被执行
     */
    boolean isExecuted(TaskData taskData);

    /**
     * 任务调度完成后，提供接入方校验本地是否运行成功
     *
     * @param taskData
     * @return default true 默认执行成功
     */
    boolean isExecuteSuccess(TaskData taskData);
}
