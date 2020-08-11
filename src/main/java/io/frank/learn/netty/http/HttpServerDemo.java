package io.frank.learn.netty.http;

import reactor.netty.http.server.HttpServer;

/**
 * @author jinjunliang
 */
public class HttpServerDemo {
    public static void main(String[] args) {
        HttpServer server = HttpServer.create();
        server.bindNow();
    }
}
