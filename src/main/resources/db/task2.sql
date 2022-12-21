-- Задание 2: Для каждого материала: наименование;
-- сколько складов, на которых этот материал имеется в количестве > 100

SELECT m.name, COUNT(IF(st_m.amount > 100, 1, NULL)) 'Количество складов'
FROM material m
    LEFT JOIN storage_material st_m
    ON m.id = st_m.storage_id
GROUP BY m.name