CREATE SCHEMA hostel;

CREATE TABLE categories
(
    id          LONG IDENTITY PRIMARY KEY,
    name        VARCHAR2(20) NOT NULL,
    description TEXT
);

INSERT INTO categories(name, description)
VALUES ('duplex', 'sea view'),
       ('standard', 'garden view');

CREATE TABLE apartments
(
    id          LONG IDENTITY PRIMARY KEY,
    number      INTEGER NOT NULL UNIQUE,
    rooms       INTEGER,
    cleaning    TIMESTAMP,
    category_id LONG REFERENCES categories (id)
);

ALTER TABLE apartments
    ADD CONSTRAINT NUMBER_UNIQUE UNIQUE (number);

INSERT INTO apartments(number, rooms, cleaning, category_id)
VALUES (100, 1, '2021-06-29', 2),
       (101, 2, '2021-06-25', 1);

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

INSERT INTO guests(name, surname, passport, foto, birth, check_in, check_out, apartment_id)
VALUES ('Max', 'Maxov', '1235 125469', 12, '2000-12-12', '2021-06-30', '2021-07-20', 5),
       ('Ivan', 'Ivanov', '3696 456582', 555, '1980-01-02', '2021-06-24', '2021-06-30', 6);


CREATE TABLE roles
(
    id   LONG IDENTITY PRIMARY KEY,
    name VARCHAR2(20) NOT NULL
);

INSERT INTO ROLES (name)
VALUES ('admin'),
       ('dispatcher');


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

INSERT INTO users(name, surname, login, password, role_id)
VALUES ('Alex', 'Petrov', 'apetrov', '$2y$12$6xPVMOWSajJly0QzC2fane/hP78iCWvtDcvdcd0rqSIcQw1ceDFum', 1),
       ('Elena', 'Ivanova', 'eivanova', '$2y$12$c7yLs0GnV1WeWf5ZK3.xrubAXf7NnteEgcA7yW0DiydyzAM5C8tXG', 2);