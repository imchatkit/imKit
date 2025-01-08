package com.imai.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * 网关启动程序
 *
 * @author wei
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ApiGatewayApplication {
    public static void main(String[] args) {
        // 标记 sentinel 类型为 网关
        System.setProperty("csp.sentinel.app.type", "1");
        SpringApplication application = new SpringApplication(ApiGatewayApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  api网关启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}