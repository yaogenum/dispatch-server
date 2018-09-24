package com.snapshot.person.dispatch.client.core.event;

import lombok.Data;

import java.util.Set;

/**
 * description: 构造的事件属性定义
 *
 * @author jiubao@wcai.com - 九宝  every day good lucky
 * @version 1.0.0
 * @Date 2018/6/4 下午3:45
 * @since 1.0.0
 */
@Data
public class EventData {

    EventData(String sql, String source) {
        setSql(sql);
        setSource(source);
        /**
         * parse sql fetch table list
         */
        setTable(null);
    }

    /**
     * 事件ID
     */
    private long id = Thread.currentThread().getId() + System.currentTimeMillis();
    /**
     * 画像SQL
     */
    private String sql;
    /**
     * 画像依赖的 TABLE
     */
    private Set<String> table;
    /**
     * 事件来源 (mc,task)
     */
    private String source;
    /**
     * 创建时间
     */
    private long createTime = System.currentTimeMillis();

}
