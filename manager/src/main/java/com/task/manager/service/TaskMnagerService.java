package com.task.manager.service;

import java.util.List;
import java.util.Optional;

import com.task.manager.entity.ErrorResponse;
import com.task.manager.entity.TaskEntity;

public interface TaskMnagerService {
    Object  CreateTask (TaskEntity taskEntity);
    List<TaskEntity> getAllTask();
    Optional<TaskEntity> GetTaskById(Integer id);
    Object UpdateById(TaskEntity taskEntity,Integer id);
    ErrorResponse DeleteById(Integer id);
}
