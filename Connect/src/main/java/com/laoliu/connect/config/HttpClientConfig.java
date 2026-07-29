package com.laoliu.connect.config;

import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Apache HttpClient 5 连接池配置
 * <p>
 * 这个配置会替换 Spring Boot 的自动配置，
 * Feign 检测到容器中的 CloseableHttpClient Bean 后会直接使用它。
 *
 * @author 25516
 */
@Configuration
public class HttpClientConfig {

    // ============================================================
    // 连接池参数 —— 根据你的服务规模调整
    // ============================================================

    /** 连接池最大连接数：同一时刻最多保持多少个 TCP 连接（所有路由合计） */
    static final int MAX_TOTAL_CONNECTIONS = 200;

    /** 每个路由最大连接数：对同一目标服务最多同时维持多少连接 */
    static final int MAX_PER_ROUTE = 20;

    // ============================================================
    // 超时参数
    // ============================================================

    /** 连接超时：TCP 三次握手最多等多久 */
    static final Timeout CONNECT_TIMEOUT = Timeout.ofSeconds(5);

    /** 从连接池中获取连接的最大等待时间（池满了排队等多久） */
    static final Timeout CONNECTION_REQUEST_TIMEOUT = Timeout.ofSeconds(3);

    // ============================================================
    // 连接存活参数
    // ============================================================

    /** 连接空闲多久后，下次使用前先做 liveness check（发个探测包确认连接还活着） */
    static final TimeValue VALIDATE_AFTER_INACTIVITY = TimeValue.ofSeconds(10);

    /** 空闲连接最大存活时间：60 秒没被用过就清理掉 */
    static final TimeValue MAX_IDLE_TIME = TimeValue.ofSeconds(60);

    /**
     * 创建带连接池的 HttpClient
     * <p>
     * Feign 的 HttpClient5FeignLoadBalancer 会自动注入这个 Bean，
     * 不需要额外配置即可生效。
     */
    @Bean
    public CloseableHttpClient httpClient() {
        // ---- 1. 连接管理器：负责连接池的创建、借用、归还 ----
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);

        // 空闲超过 10 秒的连接，借用时会先验证是否仍然可用
        // 避免拿到 "已经被服务端关闭但客户端还不知道" 的死连接
        connectionManager.setValidateAfterInactivity(VALIDATE_AFTER_INACTIVITY);

        // ---- 2. 启动后台清理线程：定期驱逐过期/空闲连接 ----
        startConnectionEvictionThread(connectionManager);

        // ---- 3. 请求级配置：超时 ----
        RequestConfig requestConfig = RequestConfig.custom()
                // 从池取连接等多久
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                .build();

        // ---- 4. 连接级配置 ----
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                // TCP 握手等多久
                .setConnectTimeout(CONNECT_TIMEOUT)
                .build();

        // ---- 5. 组装 ----
        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                // false = Client 关闭时关闭连接池
                .setConnectionManagerShared(false)
                // 每个连接空闲超过 60s 逐出
                .evictIdleConnections(MAX_IDLE_TIME)
                .build();
    }

    /**
     * 后台线程定期清理过期和空闲连接
     * <p>
     * 连接池不会自动清理死连接——如果你不做这个，
     * 闲置一段时间后可能拿到已断开但没被回收的连接，导致请求失败。
     */
    private void startConnectionEvictionThread(PoolingHttpClientConnectionManager cm) {
        Thread evictionThread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    TimeUnit.SECONDS.sleep(30);
                    // 关闭过期的连接
                    cm.closeExpired();
                    // 关闭空闲超过 MAX_IDLE_TIME 的连接
                    cm.closeIdle(MAX_IDLE_TIME);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "httpclient-eviction-thread");
        evictionThread.setDaemon(true);
        evictionThread.start();
    }
}
