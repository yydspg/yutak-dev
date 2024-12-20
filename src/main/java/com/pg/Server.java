package com.pg;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Server {

    public static void main(String[] args) {
      do1();
    }

    public static void do1(){
        VertxOptions options = new VertxOptions();
        options.setMaxEventLoopExecuteTime(1L)
                .setMaxEventLoopExecuteTimeUnit(TimeUnit.MILLISECONDS)
                .setEventLoopPoolSize(2);
        Vertx vertx = Vertx.vertx(options);
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.route(HttpMethod.POST,"/vertx").handler((context)->{
            JsonObject jsonObject = context.body().asJsonObject();
            System.out.println(jsonObject.getString("name"));
            context.response().end();
        });
       vertx.createHttpServer().requestHandler(router).listen(8080);
    }
    public static void do2(){
        VertxOptions options = new VertxOptions();
        options.setMaxEventLoopExecuteTime(1L)
                .setMaxEventLoopExecuteTimeUnit(TimeUnit.MILLISECONDS)
                .setEventLoopPoolSize(2);
        AtomicLong data = new AtomicLong(0);
        ConcurrentHashMap<Long, String> map = new ConcurrentHashMap<>();
        Vertx vertx = Vertx.vertx(options);
        Router router = Router.router(vertx);
        router.route(HttpMethod.GET,"/vertx").handler((dto)->{
            map.put(data.getAndIncrement(),"nihao");
            dto.response().setStatusCode(200)
                    .send("ok");
        });
        vertx.createHttpServer().requestHandler(router).listen(8080);
    }
}
