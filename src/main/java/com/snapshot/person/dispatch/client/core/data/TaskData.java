package com.snapshot.person.dispatch.client.core.data;

import lombok.Data;

import java.util.Date;

/**
 * description: 定时任务的任务数据列表
 *
 * @author jiubao@wcai.com - 九宝  every day good lucky
 * @version 1.0.0
 * @Date 2018/6/6 上午10:55
 * @since 1.0.0
 */
@Data
public class TaskData {
    /**
     * 任务ID,用于分片所用
     */
    private long id;
    /**
     * 执行的SQL
     */
    private String sql;
    /**
     * 执行的时间
     */
    private Date time;
}
