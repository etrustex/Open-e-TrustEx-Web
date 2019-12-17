INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadVies.dataCertDlg.title', 'Sertifikaadi andmete ekstraheerimine',
            1, 1, 1, 12);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.certLbl.text', 'Sertifikaadi sõnumi andmed',
            1, 1, 1, 12);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'commonView.trustOption.always', 'Usalda alati',
            1, 1, 1, 12);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'commonView.trustOption.no', 'Ei',
            1, 1, 1, 12);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'commonView.trustOption.yes', 'Jah',
            1, 1, 1, 12);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'commonView.optionPane.option.ok', 'OK',
            1, 1, 1, 12);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.displayDataLbl.text', 'Näita sertifikaati',
            1, 1, 1, 12);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.signatureNotTrustedLbl.text', 'Kasutaja ei usaldanud sõnumi digitaalallkirja',
            1, 1, 1, 12);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.signatureTrustedAndValidatedLbl.text', 'Sõnumi digitaalallkirja usaldati ja see kinnitati',
            1, 1, 1, 12);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.trustCertDlg.title', 'Usaldatav sertifikaat',
            1, 1, 1, 12);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.signatureLbl.text', 'Sõnumi digitaalallkirja ei saa kontrollida. Kas usaldate seda sertifikaati?',
            1, 1, 1, 12);
