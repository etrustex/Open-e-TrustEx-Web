ALTER TABLE ETX_WEB_MESSAGE
ADD CONSTRAINT FK_MESSAGE_STATUS
FOREIGN KEY (LAST_MST_ID) REFERENCES ETX_WEB_MESSAGE_STATUS(MST_ID)
/
