-- remove all jobs
DECLARE
  CURSOR c_jobs IS
    SELECT * FROM user_jobs;
  r_jobs c_jobs%ROWTYPE;
BEGIN
  FOR r_jobs IN c_jobs LOOP
    dbms_job.REMOVE(r_jobs.job);
    dbms_output.put_line('Removed job # ' || r_jobs.job || ' - ' || r_jobs.what);
  END LOOP;
END;
/

-- drop all objects
BEGIN
  FOR cur_rec IN (SELECT object_name, object_type FROM user_objects WHERE object_type IN (
    'MATERIALIZED VIEW',
    'TABLE',
    'VIEW',
    'PACKAGE BODY',
    'PACKAGE',
    'PROCEDURE',
    'FUNCTION',
    'SEQUENCE',
    'DATABASE LINK',
	'SYNONYM'
    ) and object_name like '%ETX_WEB_%') LOOP

    BEGIN
      IF cur_rec.object_type = 'TABLE' THEN
        EXECUTE IMMEDIATE 'DROP ' || cur_rec.object_type || ' "' || cur_rec.object_name || '" CASCADE CONSTRAINTS';
      ELSE
        EXECUTE IMMEDIATE 'DROP ' || cur_rec.object_type || ' "' || cur_rec.object_name || '"';
      END IF;
    EXCEPTION
      WHEN OTHERS THEN
        DBMS_OUTPUT.put_line('FAILED: DROP ' || cur_rec.object_type || ' "' || cur_rec.object_name || '"');
    END;
  END LOOP;
END;
/

purge recyclebin
/