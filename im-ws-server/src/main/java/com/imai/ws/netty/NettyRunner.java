package com.imai.ws.netty;

import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author wei
 * @ClassName: NettyServerRunner
 * @Description: spring启动后启动netty服务, 使用spring容器管理netty的bean
 */
@Component
public class NettyRunner implements ApplicationRunner {

    @Resource
    private NettyServer nettyServer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        nettyServer.start();
    }

}
