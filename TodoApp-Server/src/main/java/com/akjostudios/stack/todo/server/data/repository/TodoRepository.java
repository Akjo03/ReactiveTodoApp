package com.akjostudios.stack.todo.server.data.repository;

import com.akjostudios.stack.todo.server.data.model.TodoEntry;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SuppressWarnings("unused")
public interface TodoRepository extends ReactiveMongoRepository<TodoEntry, String> {
	@NotNull Mono<TodoEntry> findById(@NotNull String id);
	@NotNull Flux<TodoEntry> findAllByOrderByCreatedAt();
}