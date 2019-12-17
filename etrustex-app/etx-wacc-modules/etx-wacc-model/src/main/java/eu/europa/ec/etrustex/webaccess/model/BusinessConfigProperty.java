package eu.europa.ec.etrustex.webaccess.model;

public enum BusinessConfigProperty {
    BUS_SENDING_ENABLED("etx.business.sending.enabled"),
    BUS_CUSTOM_VIEW_NAME("etx.business.custom.view.name"),
    BUS_WARN_EMAIL_NOTIF_ENABLED("etx.business.warn.email.notif.enabled"),
    BUS_ENFORCE_EMAIL_NOTIF_ENABLED("etx.business.enforce.one.email.notif.enabled"),
    BUS_FOLDER_STRUCTURE_ENABLED("etx.business.folder.structure.enabled"),
    BUS_EDMA_SPLASH_SCREEN("etx.business.edma.splash.screen"),
    BUS_ANNOUNCEMENT_CONTENT("etx.business.announcements.content"),
    BUS_FORBID_ARCHIVE_UPLOAD("etx.web.forbid.archive.upload");

    private String code;

    public static BusinessConfigProperty fromCode(String code) {
        if (code != null) {
            for (BusinessConfigProperty property : BusinessConfigProperty.values()) {
                if (property.getCode().equals(code)) {
                    return property;
                }
            }
        }

        throw new IllegalArgumentException("No business configuration found for the input code: " + code);
    }

    BusinessConfigProperty(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
