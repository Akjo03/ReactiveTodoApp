package com.akjostudios.stack.todo.server.controller;

import com.akjostudios.stack.todo.server.data.dto.TodoEntryDto;
import com.akjostudios.stack.todo.server.data.mapping.TodoEntryMapper;
import com.akjostudios.stack.todo.server.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
	private final TodoService todoService;
	private final TodoEntryMapper todoEntryMapper;

	@PostMapping("/create")
	public ResponseEntity<Mono<TodoEntryDto>> createTodoEntry(@RequestBody TodoEntryDto todoEntryDto) {
		Mono<TodoEntryDto> todoEntryDtoMono = todoService.createTodoEntry(
				todoEntryMapper.convertBackward(todoEntryDto)
		).map(todoEntryMapper::convertForward);

		return ResponseEntity.ok(todoEntryDtoMono);
	}

	@GetMapping("/all")
	public ResponseEntity<Flux<TodoEntryDto>> getAllTodoEntries() {
		Flux<TodoEntryDto> todoEntryDtoFlux = todoService.getAllTodoEntries()
				.map(todoEntryMapper::convertForward);

		return ResponseEntity.ok(todoEntryDtoFlux);
	}

	@PatchMapping("/toggle/{id}")
	public ResponseEntity<Mono<TodoEntryDto>> toggleTodoEntry(@PathVariable String id) {
		Mono<TodoEntryDto> todoEntryDtoMono = todoService.toggleTodoEntry(id)
				.map(todoEntryMapper::convertForward);

		return ResponseEntity.ok(todoEntryDtoMono);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Mono<Void>> deleteTodoEntry(@PathVariable String id) {
		Mono<Void> voidMono = todoService.deleteTodoEntry(id);

		return ResponseEntity.ok(voidMono);
	}
}