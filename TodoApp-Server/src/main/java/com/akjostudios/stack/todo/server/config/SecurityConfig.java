package com.akjostudios.stack.todo.server.config;

import lombok.RequiredArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@RequiredArgsConstructor
@Getter
@Setter
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
	private final ServicesConfig servicesConfig;

	@Bean
	public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
		return http
				.authorizeExchange().anyExchange().permitAll().and()
				.httpBasic().disable()
				.formLogin().disable()
				.cors().configurationSource(request -> {
					CorsConfiguration corsConfig = new CorsConfiguration();
					corsConfig.addAllowedOrigin(servicesConfig.getClientUrl());
					corsConfig.addAllowedHeader("*");
					corsConfig.addAllowedMethod("*");
					corsConfig.setAllowCredentials(true);
					return corsConfig;
				}).and()
				.exceptionHandling().and()
				.csrf().disable()
				.build();
	}
}