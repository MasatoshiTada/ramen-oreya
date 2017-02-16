DROP TABLE IF EXISTS order_detail;
DROP TABLE IF EXISTS order_summary;

DROP SEQUENCE IF EXISTS seq_order_summary_id;
DROP SEQUENCE IF EXISTS seq_order_detail_id;

CREATE SEQUENCE seq_order_summary_id START WITH 4 INCREMENT BY 1;
CREATE SEQUENCE seq_order_detail_id START WITH 8 INCREMENT BY 1;

CREATE TABLE order_summary(
  summary_id INTEGER PRIMARY KEY,
  order_timestamp TIMESTAMP,
  provided BOOLEAN
);

CREATE TABLE order_detail (
  detail_id INTEGER PRIMARY KEY,
  goods_id INTEGER,
  amount INTEGER,
  summary_id INTEGER REFERENCES order_summary(summary_id)
);

