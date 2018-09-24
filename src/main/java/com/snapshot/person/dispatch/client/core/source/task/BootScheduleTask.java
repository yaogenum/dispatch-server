package com.snapshot.person.dispatch.client.core.source.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * description: 定时任务扫描
 *
 * @author jiubao@wcai.com - 九宝  every day good lucky
 * @version 1.0.0
 * @Date 2018/6/2 下午3:29
 * @since 1.0.0
 */

@Component
public class BootScheduleTask {
    /**
     * 7~17点 之间 每间隔30分钟开始重试
     */
    @Scheduled(cron = "0 */30 7-17 * * ?")
    public void sendDisptachTask() {
    System.out.print("cycle output , healthy check");
    }

}
