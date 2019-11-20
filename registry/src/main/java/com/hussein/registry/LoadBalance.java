package com.hussein.registry;

import java.util.List;

/**
 * <p>Title: LoadBalance</p>
 * <p>Description: 负载均衡</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/11/18 7:39 PM
 */
public interface LoadBalance {

    /**
     * 选择服务地址
     *
     * @param addressList 服务地址列表
     * @return 服务地址
     */
    String selectHost(List<String> addressList);
}
