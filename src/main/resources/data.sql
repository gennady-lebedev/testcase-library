TRUNCATE TABLE publishers;
INSERT INTO publishers (id, title) VALUES (1, 'Manning Publications Co.');
INSERT INTO publishers (id, title) VALUES (2, 'Packt Publishing Ltd.');
INSERT INTO publishers (id, title) VALUES (3, 'O’Reilly Media, Inc.');
INSERT INTO publishers (id, title) VALUES (4, 'The Pragmatic Programmers, LLC.');
INSERT INTO publishers (id, title) VALUES (5, 'Prentice Hall');
COMMIT;

TRUNCATE TABLE authors;
INSERT INTO authors (id, name, full_name) VALUES (1, 'Craig Walls', 'Craig Walls');
INSERT INTO authors (id, name, full_name) VALUES (2, 'Ron Jeffries', 'Ron Jeffries');
INSERT INTO authors (id, name, full_name) VALUES (3, 'Cay S. Horstmann', 'Cay S. Horstmann');
COMMIT;