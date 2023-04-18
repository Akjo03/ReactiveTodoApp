package com.akjostudios.stack.todo.server.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;

@EnableReactiveMongoRepositories
public class ReactiveMongoConfig extends AbstractReactiveMongoConfiguration {
	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create();
	}

	@Override
	protected @NotNull String getDatabaseName() {
		return "todo";
	}
}