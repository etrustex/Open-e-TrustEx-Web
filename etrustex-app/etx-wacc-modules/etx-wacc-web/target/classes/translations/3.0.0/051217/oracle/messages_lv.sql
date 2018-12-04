INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadVies.dataCertDlg.title', 'Izvilktais sertifikāta datums',
            1, 1, 1, 17);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.certLbl.text', 'Sertifikāta vēstules dati',
            1, 1, 1, 17);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'commonView.trustOption.always', 'Vienmēr uzticēties',
            1, 1, 1, 17);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'commonView.trustOption.no', 'Nē',
            1, 1, 1, 17);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'commonView.trustOption.yes', 'Jā',
            1, 1, 1, 17);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'commonView.optionPane.option.ok', 'Labi',
            1, 1, 1, 17);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.displayDataLbl.text', 'Parādīt sertifikātu',
            1, 1, 1, 17);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.signatureNotTrustedLbl.text', 'Lietotājs neuzticas vēstules elektroniskajam parakstam.',
            1, 1, 1, 17);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.signatureTrustedAndValidatedLbl.text', 'Vēstules elektroniskajam parakstam neuzticējās un tas netika validēts.',
            1, 1, 1, 17);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.trustCertDlg.title', 'Uzticēšanās sertifikātam',
            1, 1, 1, 17);
INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'downloadView.signatureLbl.text', 'Nav iespējams pārbaudīt vēstules elektronisko parakstu. Vai uzticaties šādam sertifikātam?',
            1, 1, 1, 17);