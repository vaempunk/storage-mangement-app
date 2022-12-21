-- Задание 3: Все материалы с ценой > 200, такие что: 
-- на каждом складе этот материал имеется в наличии в количестве > 100
SELECT *
FROM material m
WHERE m.unit_price > 200
AND (SELECT COUNT(*) FROM storage) = (
    SELECT COUNT(*)
    FROM storage_material st_m
    WHERE st_m.material_id = m.id
)
AND 100 < ALL(
    SELECT st_m.amount
    FROM storage_material st_m
    WHERE st_m.material_id = m.id
);
