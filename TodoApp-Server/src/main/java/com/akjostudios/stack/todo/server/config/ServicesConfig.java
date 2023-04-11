package com.akjostudios.stack.todo.server.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("services")
@Getter
@Setter
public class ServicesConfig {
	private String clientUrl;
	private String serverUrl;
}