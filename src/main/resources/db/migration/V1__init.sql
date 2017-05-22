-- auto-generated definition
CREATE TABLE role
(
  id   BIGINT NOT NULL
    CONSTRAINT role_pkey
    PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE users
(
  id       BIGINT NOT NULL
    CONSTRAINT users_pkey
    PRIMARY KEY,
  email    VARCHAR(255),
  password VARCHAR(255)
);
CREATE TABLE users_role
(
  users_id BIGINT NOT NULL
    CONSTRAINT fkiu0xsee0dmwa28nffgyf4bcvc
    REFERENCES users,
  role_id  BIGINT NOT NULL
    CONSTRAINT fk3qjq7qsiigxa82jgk0i0wuq3g
    REFERENCES role,
  CONSTRAINT users_role_pkey
  PRIMARY KEY (users_id, role_id)
);
CREATE SEQUENCE hibernate_sequence;

INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'USER');