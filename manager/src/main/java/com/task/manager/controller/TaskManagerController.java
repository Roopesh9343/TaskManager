package com.task.manager.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.manager.entity.ErrorResponse;
import com.task.manager.entity.TaskEntity;
import com.task.manager.service.TaskMnagerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.ui.Model;
import java.util.Optional;

@RestController
@RequestMapping("/api/taskManger")
@Slf4j
public class TaskManagerController {

    @Autowired
    TaskMnagerService managerService;


    // @GetMapping("/")
    // public String viewHomePage(Model model) {
    //     return "index"; // Thymeleaf template name without extension
    // }

    @PostMapping("/create")
    @Operation(summary = "Create a new task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskEntity.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server ERROR", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

    })
    public ResponseEntity<?> createTask(@RequestBody TaskEntity taskEntity) {

        Object task = (managerService.CreateTask(taskEntity));
        if (task instanceof TaskEntity) {
            return new ResponseEntity<>((TaskEntity) task, HttpStatus.CREATED);
        }
        if (task instanceof ErrorResponse) {
            ErrorResponse resp = (ErrorResponse) task;
            switch (resp.getErrorCode()) {
                case "400":
                    return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
                default:
                    return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(new ErrorResponse("500", "Internal Server ERROR"),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping
    @Operation(summary = "Get All tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Tasks successfull", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskEntity.class))),
    })
    public List<TaskEntity> getAllTask() {
        return managerService.getAllTask();
    }

    @GetMapping("{id}")
    @Operation(summary = "Get a task by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskEntity.class))),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<?> getTaskById(@PathVariable("id") Integer taskId) {
        Optional<TaskEntity> task = managerService.GetTaskById(taskId);
        if (task.isPresent()) {
            return new ResponseEntity<TaskEntity>((TaskEntity) task.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("404", "Task not found"), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("update/{id}")
    @Operation(summary = "Update a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TaskEntity.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server ERROR", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))

    })
    public ResponseEntity<?> updateTask(@PathVariable("id") Integer taskId,
            @RequestBody TaskEntity taskEntity) {
        Object task = managerService.UpdateById(taskEntity, taskId);
        Boolean a = (task instanceof TaskEntity);
        log.atWarn().log("Bol {}", a);
        if (true) {
            log.atWarn().log("Task {}", task);
            System.out.println("task : " + task);
            return new ResponseEntity<>((TaskEntity) task, HttpStatus.OK);
        }
        if (task instanceof ErrorResponse) {
            ErrorResponse resp = (ErrorResponse) task;
            switch (resp.getErrorCode()) {
                case "400":
                    return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
                default:
                    return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(new ErrorResponse("500", "Internal Server ERROR"),
                HttpStatus.INTERNAL_SERVER_ERROR);

        // return new ResponseEntity<TaskEntity>(managerService.UpdateById(taskEntity,
        // taskId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task deleted successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<?> deleteTask(@PathVariable("id") Integer taskId) {
        ErrorResponse message = managerService.DeleteById(taskId);
        switch (message.getErrorCode()) {
            case "200":
                return new ResponseEntity<>(message, HttpStatus.OK);
            default:
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

}
