DROP TABLE IF EXISTS item_logs;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS books_authors;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS publishers;
DROP TABLE IF EXISTS authors;

CREATE TABLE IF NOT EXISTS publishers (
  id SERIAL PRIMARY KEY,
  title VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS authors (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS books (
  id SERIAL PRIMARY KEY,
  title VARCHAR(100),
  isbn VARCHAR(20),
  publisher_id INTEGER,
  FOREIGN KEY (publisher_id) REFERENCES publishers,
  UNIQUE (isbn)
);

CREATE TABLE IF NOT EXISTS books_authors (
  book_id SERIAL NOT NULL,
  author_id INTEGER NOT NULL,
  FOREIGN KEY (book_id) REFERENCES books,
  FOREIGN KEY (author_id) REFERENCES authors
);

CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  password VARCHAR(60) NOT NULL,
  role VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS items (
  id SERIAL PRIMARY KEY,
  book_id INTEGER NOT NULL,
  status VARCHAR(20) NOT NULL,
  user_id INTEGER,
  place VARCHAR(50),
  due_date DATE,
  FOREIGN KEY (book_id) REFERENCES books,
  FOREIGN KEY (user_id) REFERENCES users
);

CREATE TABLE IF NOT EXISTS item_logs (
  id SERIAL PRIMARY KEY,
  item_id INTEGER NOT NULL,
  item_status VARCHAR(20) NOT NULL,
  item_holder INTEGER,
  item_due_date DATE,
  made_by INTEGER,
  timestamp TIMESTAMP,
  FOREIGN KEY (item_id) REFERENCES items,
  FOREIGN KEY (made_by) REFERENCES users
);