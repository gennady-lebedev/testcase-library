DROP TABLE IF EXISTS item_logs;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS users;
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
  name VARCHAR(50)
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
  book_id INTEGER NOT NULL,
  author_id INTEGER NOT NULL,
  FOREIGN KEY (book_id) REFERENCES books,
  FOREIGN KEY (author_id) REFERENCES authors
);

CREATE TABLE IF NOT EXISTS users (
  id INTEGER PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS items (
  id INTEGER PRIMARY KEY,
  book_id INTEGER NOT NULL,
  status VARCHAR(20) NOT NULL,
  user_id INTEGER,
  place VARCHAR(50),
  FOREIGN KEY (book_id) REFERENCES books,
  FOREIGN KEY (user_id) REFERENCES users
);

CREATE TABLE IF NOT EXISTS item_logs (
  id INTEGER PRIMARY KEY,
  item_id INTEGER NOT NULL,
  user_id INTEGER,
  "when" TIMESTAMP,
  what VARCHAR(200),
  FOREIGN KEY (item_id) REFERENCES items,
  FOREIGN KEY (user_id) REFERENCES users
);