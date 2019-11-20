package com.hussein.registry;

import java.util.List;

/**
 * <p>Title: AbstractLoadBalance</p>
 * <p>Description: 抽象负载均衡</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/11/20 10:51 AM
 */
public abstract class AbstractLoadBalance implements LoadBalance {

    @Override
    public String selectHost(List<String> addressList) {
        if (addressList == null || addressList.isEmpty()) {
            return null;
        }
        if (addressList.size() == 1) {
            return addressList.get(0);
        }
        return doSelect(addressList);
    }

    /**
     * 选择服务地址
     *
     * @param addressList
     * @return
     */
    abstract String doSelect(List<String> addressList);
}
