CREATE TABLE users
(
    id       LONG IDENTITY PRIMARY KEY,
    name     VARCHAR2(20)  NOT NULL,
    surname  VARCHAR2(50)  NOT NULL,
    login    VARCHAR2(50)  NOT NULL,
    password VARCHAR2(255) NOT NULL,
    role_id  LONG          NOT NULL REFERENCES roles (id),
    status   VARCHAR2(20)  NOT NULL DEFAULT 'active'
);