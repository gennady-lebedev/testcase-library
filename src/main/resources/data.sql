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

INSERT INTO items (id, book_id, status, user_id, place, due_date) VALUES
  (1, 3, 'ON_SHELF', null, '1-st shelf', null),
  (2, 2, 'ON_HANDS', 2, '2-nd shelf', '2017-06-11'),
  (3, 1, 'ON_HANDS', 4, '4-th shelf', '2017-06-11'),
  (4, 3, 'ON_HANDS', 3, '5-th shelf', '2017-06-11')
ON CONFLICT DO NOTHING;
ALTER SEQUENCE items_id_seq RESTART WITH 5;

INSERT INTO item_logs (id, item_id, item_status, item_holder, item_due_date, made_by, timestamp) VALUES
  (1, 1, '0', null, null, 1, '2017-06-01 15:19:17.014000'),
  (2, 1, '1', null, null, 1, '2017-06-01 15:19:28.157000'),
  (3, 1, '2', 3, '2017-06-11', 1, '2017-06-01 15:19:35.447000'),
  (4, 1, '3', null, '2017-06-02', 1, '2017-06-01 15:19:39.077000'),
  (5, 1, '1', null, null, 1, '2017-06-01 15:19:41.039000'),
  (6, 1, '2', 4, '2017-06-11', 1, '2017-06-01 15:19:44.827000'),
  (7, 1, '3', null, '2017-06-02', 1, '2017-06-01 15:19:50.784000'),
  (8, 1, '1', null, null, 1, '2017-06-01 15:19:56.046000'),
  (9, 2, '0', null, null, 1, '2017-06-01 15:20:24.306000'),
  (10, 2, '1', null, null, 1, '2017-06-01 15:20:31.502000'),
  (11, 2, '2', 2, '2017-06-11', 1, '2017-06-01 15:20:35.547000'),
  (12, 3, '0', null, null, 1, '2017-06-01 15:20:49.867000'),
  (13, 3, '1', null, null, 1, '2017-06-01 15:21:02.900000'),
  (14, 3, '2', 4, '2017-06-11', 1, '2017-06-01 15:21:10.501000'),
  (15, 3, '3', null, '2017-06-02', 1, '2017-06-01 15:21:11.901000'),
  (16, 3, '0', null, null, 1, '2017-06-01 15:21:15.417000'),
  (17, 3, '4', null, null, 1, '2017-06-01 15:21:18.910000'),
  (18, 3, '0', null, null, 1, '2017-06-01 15:21:20.881000'),
  (19, 3, '1', null, null, 1, '2017-06-01 15:21:29.898000'),
  (20, 3, '2', 4, '2017-06-11', 1, '2017-06-01 15:21:37.061000'),
  (21, 4, '0', null, null, 1, '2017-06-01 15:21:54.730000'),
  (22, 4, '1', null, null, 1, '2017-06-01 15:22:04.627000'),
  (23, 4, '2', 3, '2017-06-11', 1, '2017-06-01 15:22:08.362000')
ON CONFLICT DO NOTHING;
ALTER SEQUENCE item_logs_id_seq RESTART WITH 24;

COMMIT;