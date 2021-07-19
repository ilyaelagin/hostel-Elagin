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