INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadVies.dataCertDlg.title', 'Extragere date certificat',
            1, 1, 1, 3);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.certLbl.text', 'Date mesaj certificat',
            1, 1, 1, 3);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'commonView.trustOption.always', 'Întotdeauna de încredere',
            1, 1, 1, 3);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'commonView.trustOption.no', 'Nu',
            1, 1, 1, 3);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'commonView.trustOption.yes', 'Da',
            1, 1, 1, 3);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'commonView.optionPane.option.ok', 'Ok',
            1, 1, 1, 3);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.displayDataLbl.text', 'Arată certificatul',
            1, 1, 1, 3);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.signatureNotTrustedLbl.text', 'Semnătura digitală a mesajului nu a fost considerată de încredere de către utilizator',
            1, 1, 1, 3);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.signatureTrustedAndValidatedLbl.text', 'Semnătura digitală a mesajului a fost considerată de încredere și validată',
            1, 1, 1, 3);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.trustCertDlg.title', 'Fiabilitate certificat',
            1, 1, 1, 3);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.signatureLbl.text', 'Semnătura digitală a mesajului nu a putut fi verificată. Aveți încredere în următorul certificat?',
            1, 1, 1, 3);