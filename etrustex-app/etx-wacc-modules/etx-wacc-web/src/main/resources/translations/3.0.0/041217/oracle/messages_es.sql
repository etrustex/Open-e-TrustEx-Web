 INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'message.recipientErrorMessage', 'Indique un destinatario',
            1, 1, 1, 11);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'label.admin.ica.inactive', 'Inactivo',
            1, 1, 1, 11);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'label.ica.signature.not.required', 'firma facultativa',
            1, 1, 1, 11);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'label.ica.signature.required', 'firma obligatoria',
            1, 1, 1, 11);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'label.ica.encryption.not.required', 'sin cifrado',
            1, 1, 1, 11);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'label.ica.encryption.required', 'transmisión cifrada',
            1, 1, 1, 11);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'error.ica.not.loaded', 'El acuerdo de intercambio no se ha cargado. Contacte con el administrador del sistema',
            1, 1, 1, 11);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'label.webstart.openDownloadWebstart', 'Descargar archivos',
            1, 1, 1, 11);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'label.webstart.openUploadWebstart', 'Cargar archivos',
            1, 1, 1, 11);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'message.attachmentListEmpty', 'Elija al menos un archivo para cargar',
            1, 1, 1, 11);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'label.message.send.error', 'Se ha producido un error al intentar enviar el mensaje. Vuelva a intentarlo o guarde un borrador.',
            1, 1, 1, 11);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'uploadPanel.confidentialToolTip.text', 'Confidencial',
            1, 1, 1, 11);
            INSERT INTO ETX_WEB_LABEL_TRANSLATION (LTR_ID, LTR_KEY, LTR_MESSAGE, LTR_SCREEN_ID, LTR_SCREEN_POSITION_X,
            LTR_SCREEN_POSITION_Y, LNG_ID)
            VALUES ((SELECT MAX(LTR_ID) + 1
            FROM ETX_WEB_LABEL_TRANSLATION), 'uploadPanel.nonConfidentToolTip.text', 'No confidencial',
            1, 1, 1, 11);