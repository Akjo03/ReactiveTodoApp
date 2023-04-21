package com.akjostudios.stack.todo.server.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("todos")
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class TodoEntry {
	@Id private String id;

	private String title;
	private boolean completed;
	private Instant createdAt;
}