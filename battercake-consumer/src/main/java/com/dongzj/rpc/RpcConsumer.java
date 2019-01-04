package com.dongzj.rpc;


import java.lang.reflect.Proxy;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/12
 * Time: 17:58
 */
public class RpcConsumer {

    public static <T> T getService(Class<T> clazz, String ip, int port) {
        ProxyHandler proxyHandler = new ProxyHandler(ip, port);
        return (T) Proxy.newProxyInstance(RpcConsumer.class.getClassLoader(), new Class<?>[]{clazz}, proxyHandler);
    }
}
