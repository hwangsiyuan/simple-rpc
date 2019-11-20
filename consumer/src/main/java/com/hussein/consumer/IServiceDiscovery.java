package com.hussein.consumer;

/**
 * <p>Title: IServiceDiscovery</p>
 * <p>Description: 服务发现</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/11/18 7:15 PM
 */
public interface IServiceDiscovery {

    /**
     * 服务发现
     *
     * @param serviceName 服务名称
     * @return 服务地址
     */
    String discovery(String serviceName);
}
