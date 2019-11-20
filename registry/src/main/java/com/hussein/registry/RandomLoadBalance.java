package com.hussein.registry;

import java.util.List;
import java.util.Random;

/**
 * <p>Title: RandomLoadBalance</p>
 * <p>Description: 随机负载均衡</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/11/20 11:06 AM
 */
public class RandomLoadBalance extends AbstractLoadBalance {

    @Override
    String doSelect(List<String> addressList) {
        return addressList.get(new Random().nextInt(addressList.size()));
    }
}
