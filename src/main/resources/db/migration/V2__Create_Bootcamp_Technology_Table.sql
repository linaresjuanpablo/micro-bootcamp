CREATE SCHEMA IF NOT EXISTS bootcamp;

CREATE TABLE bootcamp_technology (
    bootcamp_id BIGINT NOT NULL,
    technology_id BIGINT NOT NULL,
    PRIMARY KEY (bootcamp_id, technology_id),
    FOREIGN KEY (bootcamp_id) REFERENCES bootcamp(id)
    -- OJO: no pongas FK a technology porque está en otra base de datos
);