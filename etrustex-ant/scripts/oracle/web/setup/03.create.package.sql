CREATE OR REPLACE PACKAGE ETX_UTIL AS

  -- ==========================================================
  -- Computes the MD5 checksum of a string
  -- @param p_string a string
  -- @return the md5 string representation (32 char)
  -- ==========================================================
  FUNCTION MD5 (p_string IN VARCHAR2) RETURN VARCHAR2;

  -- ==========================================================
  -- Gather the statistics from a given table
  -- @param table_name_in is the table name
  -- ==========================================================
  PROCEDURE GATHER_STATISTICS(table_name_in VARCHAR2);
END ETX_UTIL;
/
