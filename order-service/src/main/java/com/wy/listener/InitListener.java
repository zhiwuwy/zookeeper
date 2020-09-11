package com.wy.listener;

import com.wy.utils.LoadBalance;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyang
 * @date 2020/9/3 12:56
 * @description:
 */
public class InitListener implements ServletContextListener {
    private static final String BASE_SERVICE = "/services";
    private static final String SERVICE_NAME = "/products";
    private ZooKeeper zooKeeper;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            zooKeeper = new ZooKeeper("192.168.198.100:2181",5000,(watchedEvent)->{
                //当有节点变更的时候通知到消费者
                if(watchedEvent.getType() == Watcher.Event.EventType.NodeChildrenChanged && watchedEvent.getPath().equals(BASE_SERVICE + SERVICE_NAME)){
                    updateServerList();
                }
            });
            updateServerList();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void updateServerList() {
        List<String> newServerList = new ArrayList<>();
        try {
            List<String> zooKeeperChildren = zooKeeper.getChildren(BASE_SERVICE + SERVICE_NAME, true);
            for (String subNode : zooKeeperChildren) {
                System.out.println(subNode);
                byte[] data = zooKeeper.getData(BASE_SERVICE + SERVICE_NAME + "/" + subNode,false,null);
                String host = new String(data , "UTF-8");
                System.out.println("host:" + host);
                newServerList.add(host);
            }
            LoadBalance.SERVICE_LIST = newServerList;
        }catch (Exception e){
            e.printStackTrace();
        }
     }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
