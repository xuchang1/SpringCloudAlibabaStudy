package com.study.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.util.function.Supplier;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author changxu13
 * @date 2021/4/16 12:53
 */
public class GpSentinelGatewayBlockExceptionHandler implements WebExceptionHandler {
	private List<ViewResolver> viewResolvers;
	private List<HttpMessageWriter<?>> messageWriters;
	private final Supplier<ServerResponse.Context> contextSupplier = () -> {
		return new ServerResponse.Context() {
			@Override
			public List<HttpMessageWriter<?>> messageWriters() {
				return GpSentinelGatewayBlockExceptionHandler.this.messageWriters;
			}

			@Override
			public List<ViewResolver> viewResolvers() {
				return GpSentinelGatewayBlockExceptionHandler.this.viewResolvers;
			}
		};
	};

	public GpSentinelGatewayBlockExceptionHandler(List<ViewResolver> viewResolvers, ServerCodecConfigurer serverCodecConfigurer) {
		this.viewResolvers = viewResolvers;
		this.messageWriters = serverCodecConfigurer.getWriters();
	}

	/**
	 * 自定义限流异常返回结果
	 */
	private Mono<Void> writeResponse(ServerResponse response, ServerWebExchange exchange) {
		ServerHttpResponse serverHttpResponse = exchange.getResponse();
		serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
		byte[] datas = "{\"code\":999, \"msg\":\"访问人数过多\"}".getBytes(StandardCharsets.UTF_8);
		DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(datas);
		return serverHttpResponse.writeWith(Mono.just(buffer));
	}

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		if (exchange.getResponse().isCommitted()) {
			return Mono.error(ex);
		} else {
			return !BlockException.isBlockException(ex) ? Mono.error(ex) : this.handleBlockedRequest(exchange, ex).flatMap((response) -> {
				return this.writeResponse(response, exchange);
			});
		}
	}

	private Mono<ServerResponse> handleBlockedRequest(ServerWebExchange exchange, Throwable throwable) {
		return GatewayCallbackManager.getBlockHandler().handleRequest(exchange, throwable);
	}
}
