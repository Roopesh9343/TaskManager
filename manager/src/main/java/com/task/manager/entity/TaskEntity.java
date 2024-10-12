package com.task.manager.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import jakarta.persistence.*;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)    
private Integer taskId;
private String taskName;
private String description;
private LocalDateTime dueDate;
private LocalDateTime createdDate;
private LocalDateTime updatedDate;
}
