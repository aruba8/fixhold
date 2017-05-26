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
    CONSTRAINT on_users REFERENCES users,
  role_id  BIGINT NOT NULL
    CONSTRAINT on_roles REFERENCES role,
  CONSTRAINT users_role_pkey
  PRIMARY KEY (users_id, role_id)
);
CREATE SEQUENCE hibernate_sequence;

INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'USER');