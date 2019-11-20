package com.hussein.provider;

import com.hussein.api.HelloService;

/**
 * <p>Title: HelloServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/11/18 3:14 PM
 */
@RpcAnnotation(HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "您好，" + name;
    }
}
