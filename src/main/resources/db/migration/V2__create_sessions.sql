CREATE TABLE sessions
(
    id         UUID                        NOT NULL,
    user_id    UUID                        NOT NULL,
    clock_in   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    clock_out  TIMESTAMP WITHOUT TIME ZONE,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    is_active  BOOLEAN                     NOT NULL,
    CONSTRAINT pk_sessions PRIMARY KEY (id)
);

ALTER TABLE sessions
    ADD CONSTRAINT FK_SESSIONS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);