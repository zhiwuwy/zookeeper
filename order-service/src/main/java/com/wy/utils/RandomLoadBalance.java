package com.wy.utils;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Random;

/**
 * @author wangyang
 * @date 2020/9/3 13:24
 * @description:
 */
public class RandomLoadBalance extends LoadBalance{
    @Override
    public String chooseServiceHost() {
        String result = "";
        if(!CollectionUtils.isEmpty(SERVICE_LIST)){
            int index = new Random().nextInt(SERVICE_LIST.size());
            result = SERVICE_LIST.get(index);
        }
        return result;
    }
}
