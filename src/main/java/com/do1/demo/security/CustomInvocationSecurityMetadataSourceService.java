package com.do1.demo.security;


import com.do1.demo.entity.Resource;
import com.do1.demo.entity.Role;
import com.do1.demo.repository.ResourceRepository;
import com.do1.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;


/**
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 此类在初始化时，应该取到所有资源及其对应角色的定义。
 */
@Service
public class CustomInvocationSecurityMetadataSourceService implements
        FilterInvocationSecurityMetadataSource {


    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private RoleRepository roleRepository;


    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    /**
     * 加载角色对应的资源<br/>
     * 资源通常为url， 一个资源可以由多个角色来访问。
     */
    public void loadResourceDefine() {
        resourceMap = new HashMap<>();
        List<Role> roleList = roleRepository.findAll();
        for (Role role : roleList) {
            ConfigAttribute securityConfig = new SecurityConfig(role.getName());
            List<Resource> resourceList = resourceRepository.findResourceByRoleId(role.getId());
            for (Resource resource : resourceList) {
                String url = resource.getPath();
                Collection<ConfigAttribute> securityConfigs = resourceMap.computeIfAbsent(url, s -> new ArrayList<>());
                securityConfigs.add(securityConfig);
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<>();
    }

    // 根据URL，找到相关的权限(role)配置。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        System.out.println("CustomInvocationSecurityMetadataSourceService.getAttributes");
        // object 是一个URL，被用户请求的url。
        FilterInvocation filterInvocation = (FilterInvocation) object;
        if (resourceMap == null) {
            loadResourceDefine();
        }
        for (String resURL : resourceMap.keySet()) {
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if (requestMatcher.matches(filterInvocation.getHttpRequest())) {
                return resourceMap.get(resURL);
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}
