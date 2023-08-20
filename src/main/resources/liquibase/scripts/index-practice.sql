-- liquibase formatted sql

-- changeset yanna:1
CREATE INDEX name_index ON student(name);

--changeset yanna:2
CREATE INDEX name_color_index ON faculty(name,color);