package com.task.manager;

import java.time.LocalDateTime;

import org.hibernate.annotations.processing.SQL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.task.manager.entity.ErrorResponse;
import com.task.manager.entity.TaskEntity;
import com.task.manager.repository.TaskRepo;
import com.task.manager.service.TaskMnagerService;
import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ManagerApplicationTests {

	@Autowired
	TaskRepo repo;

	@Autowired
	TaskMnagerService mnagerService;


	/* test case to check Creat Task and Get Tast By ID */
	@Test
	@DisplayName("Creeate")
	@DirtiesContext
	public void testCreateAndGetTaskById() {
		TaskEntity task = new TaskEntity();
		task.setTaskName("Test Task");
		task.setDescription("Test Description");
		task.setDueDate(LocalDateTime.of(2024, 10, 15, 17, 0));
		task.setCreatedDate(LocalDateTime.now());
		task.setUpdatedDate(LocalDateTime.now());
		TaskEntity savedTask = repo.save(task);

		Optional<TaskEntity> taskGet = repo.findById(savedTask.getTaskId());
		assertThat(taskGet).isPresent();
		assertThat(savedTask.getTaskId()).isNotNull();
		assertThat(savedTask.getTaskName()).isEqualTo("Test Task");
	}

	/* Test case to check Get all tasks */
	@Test
	@DirtiesContext
	public void testGetAllTasks() {
		TaskEntity task1 = new TaskEntity(1, "Task 1", "Description 1", LocalDateTime.now(), LocalDateTime.now(),
				LocalDateTime.now());
		TaskEntity task2 = new TaskEntity(2, "Task 2", "Description 2", LocalDateTime.now(), LocalDateTime.now(),
				LocalDateTime.now());
		repo.save(task1);
		repo.save(task2);

		List<TaskEntity> tasks = mnagerService.getAllTask();

		assertThat(tasks.size()).isGreaterThanOrEqualTo(2);
	}

	@Test
	@DirtiesContext
	public void testUpdateTask() {
		TaskEntity task1 = new TaskEntity(1, "Task 1", "Description 1", LocalDateTime.now(), LocalDateTime.now(),
				LocalDateTime.now());
		TaskEntity savedTask = repo.save(task1);
		TaskEntity task2 = new TaskEntity(1, "Task Changed", "Description Changed", LocalDateTime.now(),
				LocalDateTime.now(), LocalDateTime.now());
		TaskEntity updatedTask = (TaskEntity) mnagerService.UpdateById(task2, savedTask.getTaskId());
		assertThat(savedTask.getTaskName()).isNotEqualTo(updatedTask.getTaskName());
	}

	@Test
	@DirtiesContext
	public void testDeletTask() {
		TaskEntity task = new TaskEntity();
		task.setTaskName("Test Task");
		task.setDescription("Test Description");
		task.setDueDate(LocalDateTime.of(2024, 10, 15, 17, 0));
		task.setCreatedDate(LocalDateTime.now());
		task.setUpdatedDate(LocalDateTime.now());
		TaskEntity savedTask = repo.save(task);

		ErrorResponse response = mnagerService.DeleteById(savedTask.getTaskId());

		// assertThat(response.contains("successfully deleted"));
	}

}
