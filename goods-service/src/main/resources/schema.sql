DROP TABLE IF EXISTS goods;
DROP TABLE IF EXISTS category;

CREATE TABLE category (
  id INTEGER PRIMARY KEY,
  name VARCHAR(40)
);

CREATE TABLE goods (
  id INTEGER PRIMARY KEY,
  name VARCHAR(40),
  price INTEGER,
  category_id INTEGER REFERENCES category(id)
);
