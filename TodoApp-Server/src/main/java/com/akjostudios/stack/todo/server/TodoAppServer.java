package com.akjostudios.stack.todo.server;

import com.akjostudios.stack.todo.server.init.DatabaseInit;
import io.github.akjo03.lib.config.AkjoLibSpringAutoConfiguration;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
@RequiredArgsConstructor
@ImportAutoConfiguration(classes = {
		AkjoLibSpringAutoConfiguration.class
})
@SuppressWarnings("unused")
public class TodoAppServer implements ApplicationListener<ApplicationReadyEvent> {
	public static void main(String[] args) {
		SpringApplication.run(TodoAppServer.class, args);
	}

	@Override
	public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
		event.getApplicationContext()
				.getBean(DatabaseInit.class)
				.init();
	}
}