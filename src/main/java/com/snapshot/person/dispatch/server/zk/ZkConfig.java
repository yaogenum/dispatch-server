package com.snapshot.person.dispatch.server.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * zookeeper配置
 */
@Slf4j
@Component
public class ZkConfig {

    private static RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

    private static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183")
            .sessionTimeoutMs(3000)
            .connectionTimeoutMs(5000)
            .retryPolicy(retryPolicy)
            .build();


    @PostConstruct
    public void initZkClient() {
        client.start();

        try {
            client.setData().forPath("/test/node", (new String("init ok").getBytes()));
            log.info("zk init success");
        } catch (Exception e) {
            log.error("zk inint exception", e);
        }

    }

    public CuratorFramework getClient() {
        return client;
    }

}
