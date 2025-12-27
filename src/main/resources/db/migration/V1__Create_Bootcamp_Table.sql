CREATE SCHEMA IF NOT EXISTS bootcamp;

CREATE TABLE IF NOT EXISTS bootcamp (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    launchdate DATE NOT NULL,
    duration INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- TABLA: bootcamp_capability
-- Relación Bootcamp - Capability

CREATE TABLE IF NOT EXISTS bootcamp_capability (
    bootcamp_id BIGINT NOT NULL,
    capability_id BIGINT NOT NULL,

    CONSTRAINT pk_bootcamp_capability
        PRIMARY KEY (bootcamp_id, capability_id),

    CONSTRAINT fk_bootcamp
        FOREIGN KEY (bootcamp_id)
        REFERENCES bootcamp(id)
        ON DELETE CASCADE
);

-- ============================
-- ÍNDICES (performance)
-- ============================

CREATE INDEX IF NOT EXISTS idx_bootcamp_capability_bootcamp
    ON bootcamp_capability (bootcamp_id);

CREATE INDEX IF NOT EXISTS idx_bootcamp_capability_capability
    ON bootcamp_capability (capability_id);