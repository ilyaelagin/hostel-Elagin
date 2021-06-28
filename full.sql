CREATE SCHEMA hostel;

CREATE TABLE categories
(
    id          LONG IDENTITY PRIMARY KEY,
    category    VARCHAR2(20)  NOT NULL,
    description TEXT
);

CREATE TABLE apartments
(
    id       LONG IDENTITY PRIMARY KEY,
    number   INTEGER      NOT NULL,
    rooms    INTEGER      NOT NULL,
    cleaning TIMESTAMP    NOT NULL,
    category VARCHAR2(20) NOT NULL REFERENCES categories (category)
);

CREATE TABLE guests
(
    id        LONG IDENTITY PRIMARY KEY,
    name      VARCHAR2(50) NOT NULL,
    surname   VARCHAR2(50) NOT NULL,
    passport  VARCHAR2(50) UNIQUE,
    foto      BYTEA,
    birth     DATE,
    check_in  DATE,
    check_out DATE,
    apartment INTEGER REFERENCES apartments (number)
);

CREATE TABLE roles
(
    id   LONG IDENTITY PRIMARY KEY,
    role VARCHAR2(20) NOT NULL
);

CREATE TABLE users
(
    id   LONG IDENTITY PRIMARY KEY,
    name VARCHAR2(20) NOT NULL,
    role VARCHAR2(20) NOT NULL REFERENCES roles (role)
);
