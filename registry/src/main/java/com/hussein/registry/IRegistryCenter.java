package com.hussein.registry;

/**
 * <p>Title: IRegistryCenter</p>
 * <p>Description: 注册中心</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/11/18 1:55 PM
 */
public interface IRegistryCenter {

    /**
     * 注册服务地址
     *
     * @param serviceName    服务名称
     * @param serviceAddress 服务地址
     */
    void register(String serviceName, String serviceAddress);
}
