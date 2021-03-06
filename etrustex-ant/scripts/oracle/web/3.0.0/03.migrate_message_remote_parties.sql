-- Alter ETX_WEB_MESSAGE
ALTER TABLE ETX_WEB_MESSAGE ADD (MSG_REMOTE_PARTY_NAME_BACK VARCHAR2(64))
/
UPDATE ETX_WEB_MESSAGE SET MSG_REMOTE_PARTY_NAME_BACK = MSG_REMOTE_PARTY_NAME
/
ALTER TABLE ETX_WEB_MESSAGE ADD (MSG_REMOTE_PARTY_ID NUMBER(19))
/
ALTER TABLE ETX_WEB_MESSAGE ADD CONSTRAINT ETX_WEB_MESSAGE_FK_REM_PARTY FOREIGN KEY (MSG_REMOTE_PARTY_ID) REFERENCES ETX_WEB_PARTY(PAR_ID)
/

DECLARE
  CURSOR messages_cursor IS
  SELECT m.MSG_ID message_id, m.MSG_LOCAL_PARTY_ID local_party_id, m.MSG_REMOTE_PARTY_NAME_BACK remote_party_node_name, p.BUS_ID bus_id
  FROM ETX_WEB_MESSAGE m JOIN ETX_WEB_PARTY p
  ON m.MSG_LOCAL_PARTY_ID = p.PAR_ID;

  row_message messages_cursor%ROWTYPE;
  remote_party_id NUMBER(19);
BEGIN
  FOR row_message IN messages_cursor
  LOOP

    BEGIN
      SELECT PAR_ID into remote_party_id from ETX_WEB_PARTY where PAR_NODE_NAME = row_message.remote_party_node_name and BUS_ID = row_message.bus_id;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        -- Insert new remote party if it doesn't exist already
        remote_party_id := ETX_WEB_PRTSEQ.nextval;
        INSERT INTO ETX_WEB_PARTY (PAR_ID, PAR_NODE_NAME, PAR_DISPLAY_NAME, BUS_ID, PAR_CREATED_BY, PAR_ACTIVE_STATE, PAR_WEB_MANAGED)
              VALUES (remote_party_id, row_message.remote_party_node_name, row_message.remote_party_node_name, row_message.bus_id, 0, 1, 0);
      END;

    UPDATE ETX_WEB_MESSAGE SET MSG_REMOTE_PARTY_ID = remote_party_id WHERE MSG_ID = row_message.message_id;

  END LOOP;
END;
/

-- Alter ETX_WEB_MESSAGE
ALTER TABLE ETX_WEB_MESSAGE MODIFY (MSG_REMOTE_PARTY_ID NUMBER(19) NOT NULL)
/
ALTER TABLE ETX_WEB_MESSAGE DROP COLUMN MSG_REMOTE_PARTY_NAME
/
ALTER TABLE ETX_WEB_MESSAGE DROP COLUMN MSG_REMOTE_PARTY_NAME_BACK
/

