package com.wy.utils;

import java.util.List;

/**
 * @author wangyang
 * @date 2020/9/3 13:19
 * @description:
 */
public abstract class LoadBalance {
    public volatile static List<String> SERVICE_LIST;

    public abstract String chooseServiceHost();
}
