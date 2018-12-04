ALTER TABLE ETX_WEB_MESSAGE
ADD LAST_MST_ID NUMBER(19, 0)
/

-- mst_id is auto-incremented so max created on is guaranteed per status
update etx_web_message msg
set msg.last_mst_id =
(select max(ms.mst_id)
  from etx_web_message_status ms
  where ms.msg_id = msg.msg_id
  group by ms.msg_id)
/