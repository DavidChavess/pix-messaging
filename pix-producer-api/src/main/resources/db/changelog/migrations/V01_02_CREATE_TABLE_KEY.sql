--liquibase formatted sql
--changeset david:1
CREATE TABLE IF NOT EXISTS KEY (
    id SERIAL NOT NULL PRIMARY KEY,
    key VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL
)