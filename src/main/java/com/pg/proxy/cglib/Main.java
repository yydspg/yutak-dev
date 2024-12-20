package com.pg.proxy.cglib;

import com.pg.proxy.pojo.ProxyInterfaceImpl;

public class Main {
    public static void main(String[] args) {
        CglibMethodInterceptor interceptor = new CglibMethodInterceptor(new ProxyInterfaceImpl());
        ProxyInterfaceImpl proxy = (ProxyInterfaceImpl) interceptor.getProxy();
        proxy.do_proxy();
    }
}
