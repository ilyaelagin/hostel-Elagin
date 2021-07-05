CREATE TABLE categories
(
    id          LONG IDENTITY PRIMARY KEY,
    name        VARCHAR2(20) NOT NULL,
    description TEXT
);

CREATE TABLE apartments
(
    id          LONG IDENTITY PRIMARY KEY,
    number      INTEGER NOT NULL UNIQUE,
    rooms       INTEGER,
    cleaning    TIMESTAMP,
    category_id LONG REFERENCES categories (id)
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

INSERT INTO categories(name, description)
VALUES ('duplex', 'sea view'),
       ('standard', 'garden view');

INSERT INTO apartments(number, rooms, cleaning, category_id)
VALUES (100, 1, '2021-06-29', 2),
       (101, 2, '2021-06-25', 1);

INSERT INTO guests(name, surname, passport, foto, birth, check_in, check_out, apartment_id)
VALUES ('Max', 'Maxov', '1235 125469', 12, '2000-12-12', '2021-06-30',
        '2021-07-20', 1),
       ('Ivan', 'Ivanov', '3696 456582', 555, '1980-01-02', '2021-06-24',
        '2021-06-30', 2);
