package com.macro.mall.tiny.security.component;

import java.util.Map;

/**
 * 动态权限相关业务类
 * Created by macro on 2020/2/7.
 */
public interface DynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, String> loadDataSource();

    /**
     * 获取已加载的资源MAP
     */
    Map<String, String> getDataSource();

    /**
     * 清空已加载的资源MAP
     */
    void clearDataSource();
}
