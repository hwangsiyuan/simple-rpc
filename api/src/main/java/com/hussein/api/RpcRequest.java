package com.hussein.api;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: RpcRequest</p>
 * <p>Description: rpc请求参数</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/11/18 3:21 PM
 */
@Data
public class RpcRequest implements Serializable {

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数列表
     */
    private Object[] parameters;
}
