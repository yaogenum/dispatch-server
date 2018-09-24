package com.snapshot.person.dispatch.server.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * zookeeper配置
 */
@Slf4j
@Component
public class ZkConfig {

    @Value("${zk.config.ips}")
    private String ips;

    @Value("${zk.config.session.timeout}")
    private Integer sessionTimeOut = 3000;

    @Value("${zk.config.connect.timeout}")
    private Integer connectTimeout = 5000;

    @Value("${zk.config.retry.base.sleep.time}")
    private Integer baseSleepTimeMs = 1000;

    @Value("${zk.config.retry.max.times}")
    private Integer maxRetry = 3;

    private CuratorFramework client = null;

    @PostConstruct
    public void initZkClient() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, maxRetry);
        client = CuratorFrameworkFactory.builder()
                .connectString(ips)
                .sessionTimeoutMs(sessionTimeOut)
                .connectionTimeoutMs(connectTimeout)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        try {
            client.setData().forPath("/test/node", (new String("init ok").getBytes()));
        } catch (Exception e) {
            log.error("zk inint exception", e);
            throw new Exception("zk init exception", e);
        } finally {
            if (null == client) {
                throw new Exception("zk init fail,client null");
            } else {
                log.info("zk init success");
            }
        }

    }

    public CuratorFramework getClient() {
        return client;
    }

}
