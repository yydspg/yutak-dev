package com.pg.proxy.jdkproxy;

import com.pg.proxy.pojo.ProxyInterfaceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyInterceptor  {

    public static ProxyInterfaceImpl get(){
        ProxyInterfaceImpl proxyInterface = new ProxyInterfaceImpl();
        return (ProxyInterfaceImpl) Proxy.newProxyInstance(
               ClassLoader.getSystemClassLoader(),
                ProxyInterfaceImpl.class.getInterfaces(),
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                        return method.invoke(proxyInterface, objects);
                    }
                });
    }

}
