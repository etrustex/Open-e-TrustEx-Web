 INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'message.recipientErrorMessage', 'Palun sisestage saaja',
            1, 1, 1, 12);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'label.admin.ica.inactive', 'Mitteaktiivne',
            1, 1, 1, 12);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'label.ica.signature.not.required', 'valikuline allkiri',
            1, 1, 1, 12);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'label.ica.signature.required', 'kohustuslik allkiri',
            1, 1, 1, 12);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'label.ica.encryption.not.required', 'krüptimata',
            1, 1, 1, 12);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'label.ica.encryption.required', 'krüptitud edastus',
            1, 1, 1, 12);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'error.ica.not.loaded', 'Andmevahetuslepingu laadimine ebaõnnestus. Palun võtke ühendust süsteemi haldajaga.',
            1, 1, 1, 12);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'label.webstart.openDownloadWebstart', 'Laadi failid alla',
            1, 1, 1, 12);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'label.webstart.openUploadWebstart', 'Laadi failid üles',
            1, 1, 1, 12);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'message.attachmentListEmpty', 'Valige palun üles laadimiseks vähemalt üks fail',
            1, 1, 1, 12);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'label.message.send.error', 'Sõnumi saatmisel tekkis viga. Palun proovige uuesti või salvestage mustand.',
            1, 1, 1, 12);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'uploadPanel.confidentialToolTip.text', 'Konfidentsiaalne',
            1, 1, 1, 12);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'uploadPanel.nonConfidentToolTip.text', 'Mittekonfidentsiaalne',
            1, 1, 1, 12);