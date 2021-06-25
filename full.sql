CREATE SCHEMA hostel;

CREATE TABLE guests
(
    id IDENTITY PRIMARY KEY,
    full_name VARCHAR2(50) NOT NULL,
    passport VARCHAR2(50) UNIQUE,
    foto int,
    birth DATE,
    check_in DATE,
    check_out DATE,
    apartment INTEGER REFERENCES apartments(number)
);

CREATE TABLE apartments (
    number INTEGER PRIMARY KEY,
    rooms INTEGER,
    cleaning_date TIMESTAMP,
    category VARCHAR2(20) REFERENCES categories(category)
);

CREATE TABLE categories (
     category VARCHAR2(20) PRIMARY KEY,
     description TEXT
);

CREATE TABLE users (
    id IDENTITY PRIMARY KEY,
    name VARCHAR2(20) NOT NULL,
    role VARCHAR2(20) NOT NULL REFERENCES roles(role)
);

CREATE TABLE roles (
    role VARCHAR2(20) PRIMARY KEY
);

