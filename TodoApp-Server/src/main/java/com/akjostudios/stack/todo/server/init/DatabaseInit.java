package com.akjostudios.stack.todo.server.init;

import io.github.akjo03.lib.init.Initializable;
import io.github.akjo03.lib.logging.Logger;
import io.github.akjo03.lib.logging.LoggerManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInit implements Initializable {
	private static final Logger LOGGER = LoggerManager.getLogger(DatabaseInit.class);

	@Override
	public void init() {
		// ...

		LOGGER.success("Database successfully initialized!");
	}
}