package com.wy.listener;


import com.wy.utils.ServiceRegister;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.InetAddress;
import java.util.Properties;

/**
 * @author wangyang
 * @date 2020/9/3 11:56
 * @description:
 */
public class InitListener implements ServletContextListener {
    @Override
    /**
     * 容器初始化调用
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Properties properties = new Properties();
            properties.load(InitListener.class.getClassLoader().getResourceAsStream("application.properties"));
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            int port = Integer.valueOf(properties.getProperty("server.port"));
            ServiceRegister.register(hostAddress,port);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
