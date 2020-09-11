package com.wy.utils;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * @author wangyang
 * @date 2020/9/3 12:18
 * @description:
 */
public class ServiceRegister {
    private static final String BASE_SERVICE = "/services";
    private static final String SERVICE_NAME = "/products";

    public static void register(String address, int port){
        try {
            String path = BASE_SERVICE + SERVICE_NAME;
            ZooKeeper zooKeeper = new ZooKeeper("192.168.198.100:2181",5000,(watchedEvent)->{});
            Stat exists = zooKeeper.exists(path, false);
            if(exists == null){
                zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            String servicePath = address + ":" + port;
            //注册临时有序好的节点，临时是为了客户端挂了就删除节点，有序是为了对服务进行区分
            zooKeeper.create(path + "/child",servicePath.getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("产品服务注册成功，注册的ip和端口为：" + servicePath);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
