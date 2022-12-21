DELETE FROM storage_material;
DELETE FROM material;
DELETE FROM storage;

ALTER TABLE material AUTO_INCREMENT=1;
ALTER TABLE storage AUTO_INCREMENT=1;

INSERT INTO material (name, measure_unit, unit_price) VALUES
('Брус еловый', 'm^3', 124.99),
('Лист фанеры', 'cm^2', 44.99),
('Цемент', 'm^3', 79.99),
('Бревно березовое', DEFAULT, 209.99),
('Золото', 'mg', 1049.99);

INSERT INTO storage (owner_surname) VALUES
('Романов'),
('Годунов'),
('Донской'),
('Калита'),
('Невский');

INSERT INTO storage_material (storage_id, material_id, measure_unit, amount, last_operation_date) VALUES
(1, 1, 'm^3', 333, '2022-07-15'),
(1, 2, 'mm^2', 5, NOW()),
(1, 4, DEFAULT, 155, '2021-01-02'),
(2, 4, DEFAULT, 155, '2021-01-03'),
(3, 4, DEFAULT, 155, '2021-01-05'),
(4, 4, DEFAULT, 155, '2021-01-06'),
(5, 4, DEFAULT, 99, '2021-01-10'),
(1, 5, 'mg', 3000, '2010-05-15'),
(2, 5, 'mg', 3000, '2010-05-16'),
(3, 5, 'mg', 3000, '2010-05-17'),
(4, 5, 'mg', 3000, '2010-05-18'),
(5, 5, 'mg', 221, '2022-12-05');
