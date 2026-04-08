package com.macro.mall.tiny.config;

import com.macro.mall.tiny.modules.ums.model.UmsResource;
import com.macro.mall.tiny.modules.ums.service.UmsAdminService;
import com.macro.mall.tiny.modules.ums.service.UmsResourceService;
import com.macro.mall.tiny.security.component.DynamicSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mall-security模块相关配置
 * 自定义配置，用于配置如何获取用户信息及动态权限
 * Created by macro on 2019/11/9.
 */
@Configuration
public class MallSecurityConfig {

    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsResourceService resourceService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> adminService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            private Map<String, String> dataSource;

            @Override
            public Map<String, String> loadDataSource() {
                dataSource = new ConcurrentHashMap<>();
                List<UmsResource> resourceList = resourceService.list();
                for (UmsResource resource : resourceList) {
                    dataSource.put(resource.getUrl(), resource.getId() + ":" + resource.getName());
                }
                return dataSource;
            }

            @Override
            public Map<String, String> getDataSource() {
                if (dataSource == null) {
                    loadDataSource();
                }
                return dataSource;
            }

            @Override
            public void clearDataSource() {
                dataSource = null;
            }
        };
    }
}
