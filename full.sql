CREATE SCHEMA hostel;

CREATE TABLE categories
(
    id          LONG IDENTITY PRIMARY KEY,
    name    VARCHAR2(20) NOT NULL,
    description TEXT
);

CREATE TABLE apartments
(
    id          LONG IDENTITY PRIMARY KEY,
    number      INTEGER NOT NULL,
    rooms       INTEGER ,
    cleaning    TIMESTAMP,
    category_id LONG    NOT NULL REFERENCES categories (id)
);

CREATE TABLE guests
(
    id           LONG IDENTITY PRIMARY KEY,
    name         VARCHAR2(50) NOT NULL,
    surname      VARCHAR2(50) NOT NULL,
    passport     VARCHAR2(50) UNIQUE,
    foto         BYTEA,
    birth        DATE,
    check_in     DATE,
    check_out    DATE,
    apartment_id LONG REFERENCES apartments (id)
);

CREATE TABLE roles
(
    id   LONG IDENTITY PRIMARY KEY,
    role VARCHAR2(20) NOT NULL
);

CREATE TABLE users
(
    id      LONG IDENTITY PRIMARY KEY,
    name    VARCHAR2(20) NOT NULL,
    surname VARCHAR2(50) NOT NULL,
    role_id LONG         NOT NULL REFERENCES roles (id)
);
