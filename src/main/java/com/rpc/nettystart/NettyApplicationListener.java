package com.rpc.nettystart;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author cm
 * @create 2021/11/27-11:40 下午
 */
public class NettyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(){
             @Override
            public void run() {
                //启动netty
            }
        }.start();
    }
}
