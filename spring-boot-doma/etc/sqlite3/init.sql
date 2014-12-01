DROP TABLE IF EXISTS words;

CREATE TABLE words (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  content VARCHAR(1024) NOT NULL,
  type INTEGER NOT NULL
);

INSERT INTO words(id, content, type) VALUES (1, 'ようこそ', 1);
INSERT INTO words(id, content, type) VALUES (2, 'Enjoy', 1);
INSERT INTO words(id, content, type) VALUES (3, 'Happy', 2);
