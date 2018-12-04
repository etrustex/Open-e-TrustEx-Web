package eu.europa.ec.etrustex.webaccess.model.vo;

public class PartyIcaVO {

    private final Long id;
    private final String localParty;
    private final String remoteParty;

    public PartyIcaVO(Long id, String localParty, String remoteParty) {
        this.id = id;
        this.localParty = localParty;
        this.remoteParty = remoteParty;
    }

    public Long getId() {
        return id;
    }

    public String getLocalParty() {
        return localParty;
    }

    public String getRemoteParty() {
        return remoteParty;
    }
}
