DROP TABLE IF EXISTS task_entity;

CREATE TABLE task_entity (
    task_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    task_name VARCHAR(255),
    description TEXT,
    created_date TIMESTAMP,
    updated_date TIMESTAMP,
    due_date TIMESTAMP
);
