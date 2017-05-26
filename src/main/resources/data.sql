INSERT INTO publishers (id, title) VALUES
  (1, 'Manning Publications Co.'),
  (2, 'Packt Publishing Ltd.'),
  (3, 'O’Reilly Media, Inc.'),
  (4, 'The Pragmatic Programmers, LLC.'),
  (5, 'Prentice Hall')
ON CONFLICT DO NOTHING;
ALTER SEQUENCE publishers_id_seq RESTART WITH 6;

INSERT INTO authors (id, name) VALUES
  (1, 'Craig Walls'),
  (2, 'Ron Jeffries'),
  (3, 'Cay S. Horstmann')
ON CONFLICT DO NOTHING;
ALTER SEQUENCE authors_id_seq RESTART WITH 4;

INSERT INTO books (id, title, isbn, publisher_id) VALUES
  (1, 'Core Java Volume I–Fundamentals, 10th Edition', '978-0-13-417730-4', 3),
  (2, 'The Nature of Software Development', '1-94122-237-4', 4),
  (3, 'Spring in Action, 4th Edition', '978-1-61729-120-3', 1),
  (4, 'Spring Boot in Action', '1-61729-254-0', 1)
ON CONFLICT DO NOTHING;
ALTER SEQUENCE books_id_seq RESTART WITH 5;

INSERT INTO books_authors (book_id, author_id) VALUES
  (1, 3),
  (2, 2),
  (3, 1),
  (4, 1)
ON CONFLICT DO NOTHING;

INSERT INTO users (id, name, password, role) VALUES
  (1, 'Admin', '$2a$10$7BdS5724jFiHcMn77jYLte5cOHE9hGPvqiIH7bKJo9kOHSmyAixTO', 'ADMIN'), -- 123
  (2, 'The Great Old Librarian', '$2a$10$.mkfXM5eDAYeMYfGOO5LGeGQYvk41Byty7hUaJ1r9dzjUTkAZTXum', 'LIBRARIAN'), -- 123
  (3, 'Dummy Reader', '$2a$10$TiQe.YKwu8EX5roArIY3mewqSUihj6hYAs22Oj4W1SUY.LtiLYp1a', 'READER'), -- 123
  (4, 'Regular Reader', '$2a$10$nALiN6cco3SftvENnV7VO.63vcF20yaQYIuIdb7Q7GnJL/MuosGsG', 'READER') --345
ON CONFLICT DO NOTHING;
ALTER SEQUENCE users_id_seq RESTART WITH 5;

INSERT INTO items (id, book_id, status, user_id, place) VALUES
  (1, 1, 'DRAFT', NULL, NULL)
ON CONFLICT DO NOTHING;
COMMIT;
ALTER SEQUENCE items_id_seq RESTART WITH 2;