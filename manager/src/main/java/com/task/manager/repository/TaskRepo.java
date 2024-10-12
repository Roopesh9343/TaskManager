package com.task.manager.repository;

import org.springframework.data.repository.CrudRepository;

import com.task.manager.entity.TaskEntity;

public interface TaskRepo extends CrudRepository<TaskEntity,Integer>{

}
