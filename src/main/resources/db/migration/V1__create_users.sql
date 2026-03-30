CREATE TABLE users
(
    id            UUID    NOT NULL,
    full_name     VARCHAR(255),
    email         VARCHAR(255),
    user_name     VARCHAR(255),
    password_hash VARCHAR(255),
    role          VARCHAR(255),
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    is_active     BOOLEAN NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);