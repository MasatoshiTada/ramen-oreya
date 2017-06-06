-- tokyo shop
INSERT INTO order_summary(summary_id, order_timestamp, provided, shop_id) VALUES(nextval('seq_order_summary_id'), now(), TRUE, 'tokyo');
INSERT INTO order_summary(summary_id, order_timestamp, provided, shop_id) VALUES(nextval('seq_order_summary_id'), now(), FALSE, 'tokyo');
INSERT INTO order_summary(summary_id, order_timestamp, provided, shop_id) VALUES(nextval('seq_order_summary_id'), now(), FALSE, 'tokyo');

INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(nextval('seq_order_detail_id'), 101, 1, 1);
INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(nextval('seq_order_detail_id'), 302, 1, 1);

INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(nextval('seq_order_detail_id'), 103, 1, 2);
INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(nextval('seq_order_detail_id'), 201, 1, 2);
INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(nextval('seq_order_detail_id'), 203, 1, 2);

INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(nextval('seq_order_detail_id'), 102, 1, 3);
INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(nextval('seq_order_detail_id'), 204, 1, 3);

-- osaka shop
INSERT INTO order_summary(summary_id, order_timestamp, provided, shop_id) VALUES(nextval('seq_order_summary_id'), now(), TRUE, 'osaka');
INSERT INTO order_summary(summary_id, order_timestamp, provided, shop_id) VALUES(nextval('seq_order_summary_id'), now(), FALSE, 'osaka');
INSERT INTO order_summary(summary_id, order_timestamp, provided, shop_id) VALUES(nextval('seq_order_summary_id'), now(), FALSE, 'osaka');

INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(nextval('seq_order_detail_id'), 102, 1, 4);

INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(nextval('seq_order_detail_id'), 101, 1, 5);
INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(nextval('seq_order_detail_id'), 201, 1, 5);

INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(nextval('seq_order_detail_id'), 102, 1, 6);
INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(nextval('seq_order_detail_id'), 204, 1, 6);
INSERT INTO order_detail(detail_id, goods_id, amount, summary_id) VALUES(nextval('seq_order_detail_id'), 302, 1, 6);
