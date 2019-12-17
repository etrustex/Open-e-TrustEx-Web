-- Alter ETX_WEB_MESSAGE
ALTER TABLE ETX_WEB_MESSAGE ADD MSG_REMOTE_PARTY_NAME_BACK VARCHAR(64) NULL;
UPDATE ETX_WEB_MESSAGE SET MSG_REMOTE_PARTY_NAME_BACK = MSG_REMOTE_PARTY_NAME;
ALTER TABLE ETX_WEB_MESSAGE ADD MSG_REMOTE_PARTY_ID DECIMAL(19, 0) NULL;
ALTER TABLE ETX_WEB_MESSAGE ADD CONSTRAINT ETX_WEB_MESSAGE_FK_REM_PARTY FOREIGN KEY (MSG_REMOTE_PARTY_ID) REFERENCES ETX_WEB_PARTY (PAR_ID);

-- Data Migration
DROP PROCEDURE IF EXISTS MIGRATE_MESSAGE_REMOTE_PARTIES;

CREATE PROCEDURE MIGRATE_MESSAGE_REMOTE_PARTIES()
BEGIN
    DECLARE remote_party_id DECIMAL(19, 0);
    DECLARE remote_party_ica DECIMAL(19, 0);
    DECLARE message_id DECIMAL(19, 0);
    DECLARE message_local_party_id DECIMAL(19, 0);
    DECLARE message_remote_party_node_name VARCHAR(64);
    DECLARE message_bus_id DECIMAL(19, 0);

    DECLARE messages_cursor CURSOR FOR
      SELECT m.MSG_ID message_id, m.MSG_LOCAL_PARTY_ID local_party_id, m.MSG_REMOTE_PARTY_NAME_BACK remote_party_node_name, p.BUS_ID bus_id
      FROM ETX_WEB_MESSAGE m JOIN ETX_WEB_PARTY p
      ON m.MSG_LOCAL_PARTY_ID = p.PAR_ID;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET end_of_messages = 1;

    OPEN messages_cursor;
        messages_loop: LOOP
            FETCH messages_cursor INTO message_id, message_local_party_id, message_remote_party_node_name, message_bus_id;

            IF end_of_messages = 1 THEN
                close messages_cursor;
                LEAVE messages_loop;
            END IF;

            SET remote_party_id = (SELECT PAR_ID from ETX_WEB_PARTY where PAR_NODE_NAME = message_remote_party_node_name and BUS_ID = message_bus_id);

            IF remote_party_id IS NULL THEN
                -- Insert new remote party if it doesn't exist already
                SET remote_party_id = (SELECT MAX(PAR_ID) + 1 FROM ETX_WEB_PARTY);
                INSERT INTO ETX_WEB_PARTY (PAR_ID, PAR_NODE_NAME, PAR_DISPLAY_NAME, BUS_ID, PAR_CREATED_BY, PAR_ACTIVE_STATE, PAR_WEB_MANAGED)
                    VALUES (remote_party_id, message_remote_party_node_name, message_remote_party_node_name, message_bus_id, 0, 1, 0);
            END IF;

            UPDATE ETX_WEB_MESSAGE SET MSG_REMOTE_PARTY_ID = remote_party_id WHERE MSG_ID = message_id;
    END LOOP messages_loop;
END;

CALL MIGRATE_MESSAGE_REMOTE_PARTIES();
DROP PROCEDURE MIGRATE_MESSAGE_REMOTE_PARTIES;

-- Alter ETX_WEB_MESSAGE
ALTER TABLE ETX_WEB_MESSAGE CHANGE MSG_REMOTE_PARTY_ID MSG_REMOTE_PARTY_ID DECIMAL(19, 0) NOT NULL;
ALTER TABLE ETX_WEB_MESSAGE DROP COLUMN MSG_REMOTE_PARTY_NAME;
ALTER TABLE ETX_WEB_MESSAGE DROP COLUMN MSG_REMOTE_PARTY_NAME_BACK;