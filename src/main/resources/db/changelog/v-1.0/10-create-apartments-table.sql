CREATE TABLE apartments
(
    id          LONG IDENTITY PRIMARY KEY,
    number      INTEGER NOT NULL UNIQUE,
    rooms       INTEGER,
    cleaning    TIMESTAMP,
    category_id LONG REFERENCES categories (id)
);