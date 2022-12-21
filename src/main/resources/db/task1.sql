-- Задание 1: Сведения о наличии материалов в малом (<10) количестве:
-- наименование материала; номер склада; количество

SELECT m.name 'Наименование материала', st_m.storage_id 'Номер склада', st_m.amount 'Количество'
FROM material m
    INNER JOIN storage_material st_m
    ON m.id = st_m.material_id
WHERE st_m.amount < 10;