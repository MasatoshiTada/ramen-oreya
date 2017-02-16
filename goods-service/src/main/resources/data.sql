INSERT INTO category(id, name) VALUES(1, 'ラーメン');
INSERT INTO category(id, name) VALUES(2, 'トッピング');
INSERT INTO category(id, name) VALUES(3, 'サイドメニュー');

INSERT INTO goods(id, name, price, category_id) VALUES(101, 'ラーメン並', 700, 1);
INSERT INTO goods(id, name, price, category_id) VALUES(102, 'ラーメン中', 800, 1);
INSERT INTO goods(id, name, price, category_id) VALUES(103, 'ラーメン大', 900, 1);

INSERT INTO goods(id, name, price, category_id) VALUES(201, 'チャーシュー', 200, 2);
INSERT INTO goods(id, name, price, category_id) VALUES(202, '海苔', 100, 2);
INSERT INTO goods(id, name, price, category_id) VALUES(203, 'ほうれん草', 100, 2);
INSERT INTO goods(id, name, price, category_id) VALUES(204, '玉子', 100, 2);
INSERT INTO goods(id, name, price, category_id) VALUES(205, 'ネギ', 100, 2);

INSERT INTO goods(id, name, price, category_id) VALUES(301, '小ライス', 100, 3);
INSERT INTO goods(id, name, price, category_id) VALUES(302, '餃子', 300, 3);
INSERT INTO goods(id, name, price, category_id) VALUES(303, 'チャーシュー丼', 300, 3);
