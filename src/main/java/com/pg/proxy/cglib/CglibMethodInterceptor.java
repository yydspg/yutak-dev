package com.pg.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibMethodInterceptor implements MethodInterceptor {

    Object object;
    public CglibMethodInterceptor(Object object) {
        this.object = object;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // before
        int temp = 0;
        for (int i = 0; i < 1000; i++) {
            temp += i;
            temp = temp % 10;
        }
        Object result = null;
        try {
            result = methodProxy.invoke(object, objects);
            //end
            for (int i = 0; i < 1000; i++) {
                temp += i;
                temp = temp % 10;
            }
        }catch (Exception ignored){

        }
        return result;
    }

    public Object getProxy() {
        Enhancer eh  = new Enhancer();
        eh.setSuperclass(object.getClass());
        eh.setCallback(this);
        return eh.create();
    }
}
