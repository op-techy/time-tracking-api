CREATE TABLE tasks
(
    id         UUID                        NOT NULL,
    name       VARCHAR(255)                NOT NULL,
    session_id UUID                        NOT NULL,
    start_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_time   TIMESTAMP WITHOUT TIME ZONE,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    status     VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_tasks PRIMARY KEY (id)
);

ALTER TABLE tasks
    ADD CONSTRAINT FK_TASKS_ON_SESSION FOREIGN KEY (session_id) REFERENCES sessions (id);