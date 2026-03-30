CREATE TABLE notifications
(
    id         UUID         NOT NULL,
    user_id    UUID         NOT NULL,
    message    VARCHAR(255) NOT NULL,
    severity   VARCHAR(255) NOT NULL,
    is_read    BOOLEAN      NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_notifications PRIMARY KEY (id)
);

ALTER TABLE notifications
    ADD CONSTRAINT FK_NOTIFICATIONS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);