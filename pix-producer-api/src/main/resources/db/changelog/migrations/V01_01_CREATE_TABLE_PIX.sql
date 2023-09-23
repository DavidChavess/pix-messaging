--liquibase formatted sql
--changeset david:1
CREATE TABLE IF NOT EXISTS PIX (
    id SERIAL NOT NULL PRIMARY KEY,
    identifier VARCHAR(255) NOT NULL UNIQUE,
    target_key VARCHAR(255) NOT NULL,
    source_key VARCHAR(255) NOT NULL,
    transfer_date TIMESTAMP NOT NULL DEFAULT NOW(),
    status VARCHAR(255) NOT NULL,
    value DECIMAL(7,2) NOT NULL
)