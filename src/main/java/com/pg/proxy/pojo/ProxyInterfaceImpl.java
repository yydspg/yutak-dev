package com.pg.proxy.pojo;

public class ProxyInterfaceImpl  implements ProxyInterface {

    @Override
    public void do_proxy() {
        int temp = 1;
        for (int i = 0; i < 1000; i++) {
            temp = temp + i;
            temp = temp % 9;
        }
    }

    @Override
    public void do_args_poxy(String[] args) {

    }
}
