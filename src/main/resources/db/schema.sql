-- schema: 16а Склады <->> 16б Наличие материалов <<-> 01 Материалы

DROP TRIGGER IF EXISTS before_delete_material;
DROP TRIGGER IF EXISTS after_update_storage_material;
DROP PROCEDURE IF EXISTS count_all_materials;

DROP TABLE IF EXISTS storage_material;
DROP TABLE IF EXISTS storage;
DROP TABLE IF EXISTS material;

-- 01 Материалы
-- Содержит сведения о материалах, которые используются при изготовлении деталей или в электромонтажных работах при сборке изделий.
-- •	код материала;
-- •	наименование;
-- •	единица измерения;
-- •	плановая цена за единицу.
CREATE TABLE material (
    id              INT NOT NULL AUTO_INCREMENT,
    name            VARCHAR(50) NOT NULL,
    -- Максимальная длина текстового представления единицы измерения - 5, соответсвующая dam^3 (декаметр в кубе)
    -- https://en.wikipedia.org/wiki/Unified_Code_for_Units_of_Measure
    measure_unit    VARCHAR(5) DEFAULT('UNIT'),
    unit_price      NUMERIC(8, 2) CHECK (unit_price > 0),
    PRIMARY KEY (id)
);

-- 16а Склады
-- •	номер склада;
-- •	фамилия материально ответственного лица.
CREATE TABLE storage (
    id              INT NOT NULL AUTO_INCREMENT,
    -- Максимальная длина фамилии соответствует UK Government Data Standards Catalogue (Vol. 2)
    owner_surname   VARCHAR(35) NOT NULL,
    PRIMARY KEY (id)
);

-- 16б Наличие Материалов на складах
-- •	номер склада;
-- •	код материала;
-- •	единица измерения;
-- •	количество, имеющееся на складе;
-- •	дата последней операции.
CREATE TABLE storage_material (
    storage_id          INT NOT NULL,
    material_id         INT NOT NULL,
    measure_unit        VARCHAR(5) NOT NULL DEFAULT('UNIT'),
    amount              INT NOT NULL CHECK (amount >= 0),
    last_operation_date TIMESTAMP,
    PRIMARY KEY (storage_id, material_id),
    FOREIGN KEY (storage_id) REFERENCES storage (id)
            ON UPDATE CASCADE
            ON DELETE CASCADE,
    FOREIGN KEY (material_id) REFERENCES material (id)
);

-- Триггер на каскадное удаление в таблице storage_material
CREATE TRIGGER before_delete_material
BEFORE DELETE ON material
FOR EACH ROW
DELETE FROM storage_material st_m
WHERE st_m.material_id = OLD.id;

DELIMITER $$

-- Триггер на обновление в таблице storage_material
-- Если после обновления количество стало равным нулю, запись удаляется из таблицы storage_material
CREATE TRIGGER after_update_storage_material
AFTER UPDATE ON storage_material
FOR EACH ROW
BEGIN
    IF NEW.amount = 0 THEN
        DELETE FROM storage_material st_m
        WHERE st_m.storage_id = NEW.storage_id AND st_m.material = NEW.material_id;
    END IF;
END $$

-- Хранимая процедура, возвращающая количество материала на всех складах
CREATE PROCEDURE count_all_materials (
    IN material_id INT,
    OUT material_amount INT
)
BEGIN

    SELECT SUM(amount)
    INTO material_amount
    FROM storage_material st_m
    WHERE st_m.material_id = material_id;

END $$

DELIMITER ;
