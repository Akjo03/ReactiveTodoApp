package com.akjostudios.stack.todo.server.service;

import com.akjostudios.stack.todo.server.data.model.TodoEntry;
import com.akjostudios.stack.todo.server.data.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TodoService {
	private final TodoRepository todoRepository;

	public Mono<TodoEntry> createTodoEntry(TodoEntry todoEntry) {
		return todoRepository.save(todoEntry)
				.map(savedTodoEntry -> {
					savedTodoEntry.setCreatedAt(Instant.now());
					return savedTodoEntry;
				});
	}

	public Flux<TodoEntry> getAllTodoEntries() {
		return todoRepository.findAllByOrderByCreatedAt();
	}

	public Mono<TodoEntry> toggleTodoEntry(String id) {
		return todoRepository.findById(id)
				.map(todoEntry -> {
					todoEntry.setCompleted(!todoEntry.isCompleted());
					return todoEntry;
				}).flatMap(todoRepository::save);
	}

	public Mono<Void> deleteTodoEntry(String id) {
		return todoRepository.deleteById(id);
	}
}