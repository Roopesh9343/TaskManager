package com.task.manager.service.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.manager.entity.ErrorResponse;
import com.task.manager.entity.TaskEntity;
import com.task.manager.repository.TaskRepo;
import com.task.manager.service.TaskMnagerService;

@Service
public class TaskManagerServiceImpl implements TaskMnagerService {

    @Autowired
    TaskRepo taskRepo;

    @Override
    public Object CreateTask(TaskEntity taskEntity) {
        if (taskEntity.getTaskName().isEmpty() || taskEntity.getTaskName().isBlank()) {
            return new ErrorResponse("400", "Invalid input");
        }
        TaskEntity task = null;
        try {
            task = taskRepo.save(taskEntity);
        } catch (Exception e) {
            return new ErrorResponse("500", "Internal server Error");
        }

        return taskRepo.save(taskEntity);
    }

    @Override
    public List<TaskEntity> getAllTask() {
        return (List<TaskEntity>) taskRepo.findAll();
    }

    @Override
    public Optional<TaskEntity> GetTaskById(Integer id) {
        return taskRepo.findById(id);
    }

    @Override
    public Object UpdateById(TaskEntity taskEntity, Integer id) {
        if (taskEntity.getTaskName().isEmpty() || taskEntity.getTaskName().isBlank()) {
            return new ErrorResponse("400", "Invalid input");
        }
        Optional<TaskEntity> existingTask = taskRepo.findById(id);
        if (existingTask.isPresent()
        ) {
            existingTask.get().setTaskName(taskEntity.getTaskName());
            existingTask.get().setDescription(taskEntity.getDescription());
            existingTask.get().setDueDate(taskEntity.getDueDate());
            existingTask.get().setUpdatedDate(LocalDateTime.now());
            taskRepo.save(existingTask.get());
            return existingTask.get();
        }
        return new ErrorResponse("404", "Task Not Found");
    }

    @Override
    public ErrorResponse DeleteById(Integer id) {
        Optional<TaskEntity> task = taskRepo.findById(id);
        if (task.isPresent()) {
            taskRepo.deleteById(id);
            return new ErrorResponse("200", "Deleted successfully");
        } else {
            // Task does not exist, return a message
            return new ErrorResponse("404", "Task with ID " + id + " not found. No deletion performed.");
        }
    }
}
