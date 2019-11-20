package com.hussein.api;

/**
 * <p>Title: HelloService</p>
 * <p>Description: hello</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/11/18 3:08 PM
 */
public interface HelloService {

    /**
     * say hello
     *
     * @param name 名字
     * @return 你好, name
     */
    String sayHello(String name);
}
