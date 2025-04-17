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

select *
from books;
select c.id,c.name FROM category c join book_category bc on c.id = bc.category_id where bc.book_id=?;



CREATE TABLE category(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE book_category(
    book_id INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (book_id,category_id),
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);
CREATE TABLE selected (
                            id SERIAL PRIMARY KEY,
                            reader_id INT NOT NULL,
                            book_id INT NOT NULL,
                            FOREIGN KEY (reader_id) REFERENCES readers(id) ON DELETE CASCADE,
                            FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

select c.id, c.name FROM category c join book_category bc on c.id = bc.category_id where bc.book_id = ?;

CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        reader_id INT NOT NULL REFERENCES readers(id),
                        book_id INT NOT NULL REFERENCES books(id),
                        order_date TIMESTAMP NOT NULL,
                        status_code VARCHAR(50) NOT NULL
);