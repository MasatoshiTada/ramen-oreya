-- INSERT INTO order_summary(summary_id, order_timestamp, provided) VALUES(1, '2017-02-02 10:01:02', TRUE);
-- INSERT INTO order_summary(summary_id, order_timestamp, provided) VALUES(2, '2017-02-02 10:03:11', FALSE);
-- INSERT INTO order_summary(summary_id, order_timestamp, provided) VALUES(3, '2017-02-02 10:04:23', FALSE);
INSERT INTO order_summary(summary_id, order_timestamp, provided) VALUES(1, now(), TRUE);
INSERT INTO order_summary(summary_id, order_timestamp, provided) VALUES(2, now(), FALSE);
INSERT INTO order_summary(summary_id, order_timestamp, provided) VALUES(3, now(), FALSE);

INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(1, 101, 1, 1);
INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(2, 302, 1, 1);

INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(3, 103, 1, 2);
INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(4, 201, 1, 2);
INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(5, 203, 1, 2);

INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(6, 102, 1, 3);
INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(7, 204, 1, 3);
