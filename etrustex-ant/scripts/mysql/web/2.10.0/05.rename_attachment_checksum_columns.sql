ALTER TABLE `ETX_WEB_ATTACHMENT` CHANGE COLUMN `ATT_CHECKSUM` `ATT_TRANSMISSION_CHK` tinyblob;
ALTER TABLE `ETX_WEB_ATTACHMENT` CHANGE COLUMN `ATT_CHECKSUM_METHOD` `ATT_TRANSMISSION_CHK_METHOD` varchar(10);

