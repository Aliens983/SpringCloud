在 Spring Boot 中获取当前端口有几种方式，最简单的是 @Value：

方式一：@Value（最常用）

@Value("${server.port}")
private int port;

方式二：@LocalServerPort（测试常用）

@LocalServerPort
private int port;

方式三：从 Environment 获取

private final Environment env;

// 用的时候
env.getProperty("server.port");
