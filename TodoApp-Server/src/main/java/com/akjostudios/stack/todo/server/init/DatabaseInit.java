package com.akjostudios.stack.todo.server.init;

import com.akjostudios.stack.todo.server.data.repository.TodoRepository;
import io.github.akjo03.lib.init.Initializable;
import io.github.akjo03.lib.logging.Logger;
import io.github.akjo03.lib.logging.LoggerManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInit implements Initializable {
	private static final Logger LOGGER = LoggerManager.getLogger(DatabaseInit.class);

	private final TodoRepository todoRepository;

	@Override
	public void init() {
		todoRepository.deleteAll().block();
		LOGGER.success("Database successfully initialized!");
	}
}