DELETE FROM mysql.proc WHERE name like 'ETX_UTIL%';
/
DROP PROCEDURE IF EXISTS CHECK_USER_ROLE;
/
DROP PROCEDURE IF EXISTS ETX_UTIL_GATHER_STATISTICS;
/
DROP PROCEDURE IF EXISTS DROP_ALL_WEB_TABLES;
/
DROP FUNCTION IF EXISTS ETX_UTIL_MD5;
/
CREATE PROCEDURE DROP_ALL_WEB_TABLES()
BEGIN
    DECLARE table_name VARCHAR(255);

    DECLARE end_of_tables INT DEFAULT 0;

    DECLARE tables_cursor CURSOR FOR
        SELECT t.table_name
        FROM information_schema.tables t
        WHERE t.table_schema = DATABASE() AND t.table_type='BASE TABLE' AND t.table_name like '%ETX_WEB_%';
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET end_of_tables = 1;

    SET FOREIGN_KEY_CHECKS = 0;
    OPEN tables_cursor;

    tables_loop: LOOP
        FETCH tables_cursor INTO table_name;

        IF end_of_tables = 1 THEN
            LEAVE tables_loop;
        END IF;

        SET @drop_tables = CONCAT('DROP TABLE IF EXISTS ' , table_name, ' CASCADE');
        PREPARE stmt FROM @drop_tables;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;

    END LOOP;

    CLOSE tables_cursor;
    SET FOREIGN_KEY_CHECKS = 1;
END;
/

CALL DROP_ALL_WEB_TABLES();
/

DROP PROCEDURE DROP_ALL_WEB_TABLES;
/