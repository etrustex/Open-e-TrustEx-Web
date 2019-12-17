CREATE OR REPLACE PACKAGE BODY ETX_UTIL AS

  -- ==========================================================
  -- Computes the MD5 checksum of a string
  -- @param p_string a string
  -- @return the md5 string representation (32 char)
  -- ==========================================================
  FUNCTION MD5 (p_string IN VARCHAR2) RETURN VARCHAR2 IS
  BEGIN
    RETURN rawtohex(dbms_obfuscation_toolkit.md5(
  	  input => utl_raw.cast_to_raw(p_string)));
  END MD5;

  -- ==========================================================
  -- Gather the statistics from a given table
  -- @param table_name_in is the table name
  -- ==========================================================
  PROCEDURE GATHER_STATISTICS(table_name_in VARCHAR2)
  IS
      CURSOR c_index_name IS
        SELECT index_name FROM user_indexes
        WHERE table_name = table_name_in;

      r_index_name c_index_name%ROWTYPE;
      schema_name VARCHAR2(500);
  BEGIN
    SELECT sys_context('USERENV', 'CURRENT_SCHEMA') INTO schema_name FROM dual;
    dbms_output.put_line('Statistics will be gathered for schema: ' || schema_name || ', table: ' || table_name_in || ' Started at: '|| SYSTIMESTAMP);
    DBMS_STATS.GATHER_TABLE_STATS (
        ownname => schema_name,
        tabname => table_name_in,
        estimate_percent => 10);

    FOR r_index_name IN c_index_name
      LOOP
          dbms_output.put_line('Statistics will be gathered for schema: ' || schema_name || ', index: ' || r_index_name.index_name || ' Started at: '|| SYSTIMESTAMP);
          DBMS_STATS.GATHER_INDEX_STATS(
            ownname => schema_name,
            indname => r_index_name.index_name,
            estimate_percent => 10);
      END LOOP;
      dbms_output.put_line('Statistics gathered completed. Completed at: '|| SYSTIMESTAMP);
  END;

END ETX_UTIL;
/
