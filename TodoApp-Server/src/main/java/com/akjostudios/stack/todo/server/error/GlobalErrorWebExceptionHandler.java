package com.akjostudios.stack.todo.server.error;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.reactive.result.view.ViewResolver;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Order(-2)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {
	public GlobalErrorWebExceptionHandler(
			ObjectProvider<ViewResolver> viewResolvers,
			ServerCodecConfigurer configurer,
			ErrorAttributes errorAttributes,
			WebProperties webProperties,
			ApplicationContext applicationContext
	) {
		super(errorAttributes, webProperties.getResources(), applicationContext);
		this.setMessageReaders(configurer.getReaders());
		this.setMessageWriters(configurer.getWriters());
		this.setViewResolvers(viewResolvers.orderedStream().toList());
	}

	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
	}

	private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
		Map<String, Object> errorAttributesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());

		return ServerResponse.status(HttpStatus.valueOf((Integer) errorAttributesMap.get("status")))
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(errorAttributesMap));
	}
}