package com.night.cloud.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author 夜月
 * @description
 * @data 2020/6/23 17:55
 */
@Component
@Slf4j
public class MyGatewayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Welcome to MyGatewayFilter:" + new Date());
        String name = exchange.getRequest().getQueryParams().getFirst("name");
        if (StringUtils.isEmpty(name)) {
            log.info("name is null");
            exchange.getResponse().setStatusCode(HttpStatus.BAD_GATEWAY);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
