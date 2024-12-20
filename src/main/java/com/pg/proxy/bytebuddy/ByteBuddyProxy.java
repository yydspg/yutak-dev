package com.pg.proxy.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;

public class ByteBuddyProxy {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {
        DynamicType.Loaded<Object> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .name("com.yutak.bytebuddy.proxy.ByteBuddyProxy")
                .defineMethod("getProxy",String.class, Modifier.STATIC+Modifier.PUBLIC)
                .withParameter(String[].class,"args")
                .intercept(FixedValue.value("Hello World"))
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("hello world"))
                .make()
                .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.WRAPPER);
        dynamicType.saveIn(new File("/home/paul/opensource/yutak-dev/out"));
        System.out.println(dynamicType.getLoaded().newInstance().toString());
    }
}
