package com.hussein.consumer;

import com.hussein.registry.LoadBalance;
import com.hussein.registry.RandomLoadBalance;
import com.hussein.registry.constant.RegistryCenterConfig;
import com.hussein.registry.utils.ZkUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;

import java.util.List;

/**
 * <p>Title: ZkServiceDiscovery</p>
 * <p>Description: zk 服务发现</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/11/18 7:20 PM
 */
@Slf4j
public class ZkServiceDiscovery implements IServiceDiscovery {

    /**
     * 服务地址列表
     * /rpc/com.hussein.api.HelloService 可能对应多个地址
     * 127.0.0.1:1010,127.0.0.1:1020
     */
    private List<String> serviceAddressList;

    @Override
    public String discovery(String serviceName) {
        //服务节点 /rpc/com.hussein.api.HelloService
        String nodePath = RegistryCenterConfig.RPC_NAMESPACE + "/" + serviceName;
        ZkUtils zkUtils = new ZkUtils();
        CuratorFramework client = zkUtils.getClient();
        try {
            serviceAddressList = client.getChildren().forPath(nodePath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("服务发现子节点异常");
        }
        registerWatcher(client, nodePath);
        LoadBalance loadBalance = new RandomLoadBalance();
        String address = loadBalance.selectHost(serviceAddressList);
        log.info("获取服务地址,{}", address);
        return address;
    }

    private void registerWatcher(CuratorFramework client, String path) {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);
        pathChildrenCache.getListenable().addListener((curatorFramework, pathChildrenCacheEvent) -> serviceAddressList = client.getChildren().forPath(path));
    }
}
