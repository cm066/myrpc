package com.rpc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cm
 * @create 2021/11/27-11:26 下午
 */
public class AplicationMain {
    private static volatile boolean running = true;
    public static void main(String[] args) {
        try {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.rpc");

            //添加一个钩子函数，在关闭的时候执行
            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                    try {
                        context.stop();
                    }catch (Throwable throwable){

                    }
                    synchronized (AplicationMain.class){
                        running = false;
                        AplicationMain.class.notify();
                    }
                }

            });
            context.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("服务器已经启动了=====");
        synchronized (AplicationMain.class){
            while (running){
                try {
                    AplicationMain.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
