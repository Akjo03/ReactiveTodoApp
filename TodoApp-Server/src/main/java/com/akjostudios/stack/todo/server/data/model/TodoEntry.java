package com.akjostudios.stack.todo.server.data.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("todos")
@RequiredArgsConstructor
@Getter
@Setter
public class TodoEntry {
	@Id private String id;

	private String title;
	private boolean completed;
	private Instant createdAt;
}