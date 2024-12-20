package com.pg.spi;

public class MySpiImpl implements MySpi{
    @Override
    public void hi() {
        String a = "12";
        String b = "12";
        System.out.println(a == b);
    }
}
