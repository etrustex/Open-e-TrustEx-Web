-- ==========================================================
-- Computes the MD5 checksum of a string
-- @param p_string a string
-- @return the md5 string representation (32 char)
-- ==========================================================
CREATE FUNCTION ETX_UTIL_MD5(p_string VARCHAR(256)) RETURNS VARCHAR(256)
BEGIN
    RETURN md5(p_string);
END;
/
-- Gather the statistics from a given table
-- @param table_name_in is the table name
-- ==========================================================
CREATE PROCEDURE ETX_UTIL_GATHER_STATISTICS(IN table_name_in VARCHAR(256))
BEGIN
  SET @t1 = CONCAT_WS(' ', 'OPTIMIZE TABLE', table_name_in);
  SET @t2 = CONCAT_WS(' ', 'SHOW INDEX FROM', table_name_in, 'FROM', DATABASE());

  SELECT (CONCAT('Table statistics will be gathered for schema: ', DATABASE(), ', table: ', table_name_in, ' Started at: ', CURRENT_TIMESTAMP)) AS 'MESSAGE 1';
  PREPARE stmt1 FROM @t1;
  EXECUTE stmt1;
  DEALLOCATE PREPARE stmt1;

  SELECT (CONCAT('Index statistics will be gathered for schema: ', DATABASE(), ', table: ', table_name_in, ' Started at: ', CURRENT_TIMESTAMP)) AS 'MESSAGE 2';
  PREPARE stmt2 FROM @t2;
  EXECUTE stmt2;
  DEALLOCATE PREPARE stmt2;

  SELECT (CONCAT('Statistics gathered completed. Completed at: ', CURRENT_TIMESTAMP)) AS 'END';
END;
/