CREATE TABLE readers(
                        id SERIAL PRIMARY KEY,
                        username VARCHAR(50)    UNIQUE  NOT NULL,
                        hash_password VARCHAR(255) NOT NULL,
                        email VARCHAR(100)   UNIQUE NOT NULL,
                        role VARCHAR(10) NOT NULL CHECK ( role IN ('admin','reader'))
);

CREATE TABLE books(
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255)    UNIQUE NOT NULL,
                      writer VARCHAR(255)  UNIQUE NOT NULL,
                      price INT            NOT NULL,
                      image bytea,
                      quantity INT         NOT NULL
);


