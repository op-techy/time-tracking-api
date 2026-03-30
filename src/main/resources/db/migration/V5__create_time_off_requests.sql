CREATE TABLE time_off_requests
(
    id              UUID         NOT NULL,
    reason          VARCHAR(255) NOT NULL,
    start_date      date         NOT NULL,
    end_date        date         NOT NULL,
    employee_id     UUID         NOT NULL,
    approved_by_id  UUID,
    approval_status VARCHAR(255) NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_time_off_requests PRIMARY KEY (id)
);

ALTER TABLE time_off_requests
    ADD CONSTRAINT FK_TIME_OFF_REQUESTS_ON_APPROVED_BY FOREIGN KEY (approved_by_id) REFERENCES users (id);

ALTER TABLE time_off_requests
    ADD CONSTRAINT FK_TIME_OFF_REQUESTS_ON_EMPLOYEE FOREIGN KEY (employee_id) REFERENCES users (id);