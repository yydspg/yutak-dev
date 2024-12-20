package com.pg;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) {
        VertxOptions options = new VertxOptions();
        options.setMaxEventLoopExecuteTime(1L)
                .setMaxEventLoopExecuteTimeUnit(TimeUnit.MILLISECONDS)
                .setEventLoopPoolSize(2);

        Vertx vertx = Vertx.vertx(options);
        HttpClientOptions httpOptions = new HttpClientOptions().setDefaultPort(9001);
        WebClient client = WebClient.create(vertx);
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(()->{
            client.post(8080, "localhost", "/vertx")
                    .sendJsonObject(new JsonObject()
                            .put("name","wokankan"))
                    .onSuccess(response -> System.out
                            .println("Received response with status code" + response.statusCode()))
                    .onFailure(err ->
                            System.out.println("Something went wrong " + err.getMessage()));
        },1,5,TimeUnit.SECONDS);
    }
}
