修改的 2 个文件

RestTemplateConfig.java：

@Configuration                          // 原来是 @Component
public class RestTemplateConfig {
    @Bean
    @LoadBalanced                       // ← 新增，赋予 RestTemplate 负载均衡能力
    public RestTemplate restTemplate() { ... }
}

GetMessageController.java：

// 原来：写死 localhost:8081，只能打到一个实例
"http://localhost:8081/getMessage?secret={id}"

// 现在：用 Nacos 里的服务名，自动在多个实例间负载均衡
"http://connected/getMessage?secret={id}"

工作原理

Connect 发起请求
      │
      ▼
"http://connected/getMessage"  ← 不认识这个主机名
      │
      ▼
@LoadBalanced 拦截 → 去 Nacos 查 "connected" 的实例列表
      │
      ├── 192.168.x.x:8081  ← 空闲
      ├── 192.168.x.x:1898  ← 空闲
      └── 192.168.x.x:8082  ← 繁忙
      │
      ▼
挑一个空闲的 → 实际请求 http://192.168.x.x:8081/getMessage

---