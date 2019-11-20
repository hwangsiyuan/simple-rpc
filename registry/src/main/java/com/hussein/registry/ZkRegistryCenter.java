package com.hussein.registry;

import com.hussein.registry.constant.RegistryCenterConfig;
import com.hussein.registry.utils.ZkUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;

/**
 * <p>Title: ZkRegistryCenter</p>
 * <p>Description: zookeeper注册中心</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/11/18 2:07 PM
 */
@Slf4j
public class ZkRegistryCenter implements IRegistryCenter {

    @Override
    public void register(String serviceName, String serviceAddress) {
        String serviceNodePath = RegistryCenterConfig.RPC_NAMESPACE + "/" + serviceName;
        ZkUtils zkUtils = new ZkUtils();
        try {
            if (!zkUtils.isExistNode(serviceNodePath)) {
                zkUtils.crateNode(serviceNodePath, CreateMode.PERSISTENT, "");
            }
            String addressPath = serviceNodePath + "/" + serviceAddress;
            String node = zkUtils.crateNode(addressPath, CreateMode.EPHEMERAL, "");
            log.info("服务注册成功:{}", node);
        } catch (Exception e) {
            log.error("服务注册异常", e);
        }
    }
}
