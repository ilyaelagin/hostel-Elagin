CREATE SCHEMA hostel;

DROP SCHEMA hostel;

CREATE TABLE categories
(
    category    VARCHAR2(20) PRIMARY KEY,
    description TEXT
);

CREATE TABLE apartments
(
    number        INTEGER PRIMARY KEY,
    rooms         INTEGER,
    cleaning_date TIMESTAMP,
    category      VARCHAR2(20) REFERENCES categories (category)
);

CREATE TABLE guests
(
    id        IDENTITY PRIMARY KEY,
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
    role VARCHAR2(20) PRIMARY KEY
);

CREATE TABLE users
(
    id   IDENTITY PRIMARY KEY,
    name VARCHAR2(20) NOT NULL,
    role VARCHAR2(20) NOT NULL REFERENCES roles (role)

);
