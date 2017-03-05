DROP TABLE IF EXISTS books_authors;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS publishers;
DROP TABLE IF EXISTS authors;

CREATE TABLE IF NOT EXISTS publishers (
  id INTEGER PRIMARY KEY,
  title VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS authors (
  id INTEGER PRIMARY KEY,
  name VARCHAR(50),
  full_name VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS books (
  id INTEGER PRIMARY KEY,
  title VARCHAR(100),
  isbn VARCHAR(20),
  publisher_id INTEGER,
  FOREIGN KEY (publisher_id) REFERENCES publishers,
  UNIQUE (isbn)
);

CREATE TABLE IF NOT EXISTS books_authors (
  book_id INTEGER,
  author_id INTEGER,
  FOREIGN KEY (book_id) REFERENCES books,
  FOREIGN KEY (author_id) REFERENCES authors
)