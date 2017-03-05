INSERT INTO publishers (id, title) VALUES
  (1, 'Manning Publications Co.'),
  (2, 'Packt Publishing Ltd.'),
  (3, 'O’Reilly Media, Inc.'),
  (4, 'The Pragmatic Programmers, LLC.'),
  (5, 'Prentice Hall');
COMMIT;

INSERT INTO authors (id, name, full_name) VALUES
  (1, 'Craig Walls', 'Craig Walls'),
  (2, 'Ron Jeffries', 'Ron Jeffries'),
  (3, 'Cay S. Horstmann', 'Cay S. Horstmann');
COMMIT;


INSERT INTO books (id, title, isbn, publisher_id) VALUES
  (1, 'Core Java Volume I–Fundamentals, 10th Edition', '978-0-13-417730-4', 3),
  (2, 'The Nature of Software Development', '1-94122-237-4', 4),
  (3, 'Spring in Action, 4th Edition', '978-1-61729-120-3', 1),
  (4, 'Spring Boot in Action', '1-61729-254-0', 1);
COMMIT;

INSERT INTO books_authors (book_id, author_id) VALUES
  (1, 3),
  (2, 2),
  (3, 1),
  (4, 1);
COMMIT;