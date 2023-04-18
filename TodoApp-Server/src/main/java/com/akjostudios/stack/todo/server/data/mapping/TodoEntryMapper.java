package com.akjostudios.stack.todo.server.data.mapping;

import com.akjostudios.stack.todo.server.data.dto.TodoEntryDto;
import com.akjostudios.stack.todo.server.data.model.TodoEntry;
import io.github.akjo03.lib.converter.Converter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class TodoEntryMapper extends Converter<TodoEntry, TodoEntryDto> {
	public TodoEntryMapper() {
		super(TodoEntryMapper::toDTO, TodoEntryMapper::toEntity);
	}

	private static @NotNull TodoEntryDto toDTO(@NotNull TodoEntry entity) {
		TodoEntryDto dto = new TodoEntryDto();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setCompleted(entity.isCompleted());
		dto.setCreatedAt(entity.getCreatedAt());

		return dto;
	}

	private static @NotNull TodoEntry toEntity(@NotNull TodoEntryDto dto) {
		TodoEntry entity = new TodoEntry();
		entity.setId(dto.getId());
		entity.setTitle(dto.getTitle());
		entity.setCompleted(dto.isCompleted());
		entity.setCreatedAt(dto.getCreatedAt());

		return entity;
	}
}