package io.frank.learn.netty.http;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.client.HttpClientResponse;

/**
 * @author jinjunliang
 */
public class HttpClientDemo {
    public static void main(String[] args) {
        HttpClient client = HttpClient.create();
        HttpClient.ResponseReceiver<?> receiver = client.get();
        HttpClient.RequestSender sender = (HttpClient.RequestSender) receiver.uri("http://baidu.com/");
        Mono<HttpClientResponse> m = sender.response();
        HttpClientResponse response = m.block();
        System.out.println(response.status().code());

//        HttpClientResponse response =
//                HttpClient.create()
//                        .get()
//                        .uri("http://baidu.com/")
//                        .response()
//                        .block();
    }
}
