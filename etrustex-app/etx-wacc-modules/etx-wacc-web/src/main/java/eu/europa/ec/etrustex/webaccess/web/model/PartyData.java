package eu.europa.ec.etrustex.webaccess.web.model;


import java.util.List;

public class PartyData implements Comparable<PartyData> {
    private Long partyId;
    private String displayName;
    private String nodeName;
    private boolean canSend;
    private List<DisplayPartyIca> partyIcas;

    // don't delete
    public PartyData() {
    }

    public PartyData(Long partyId, String displayName, String nodeName, List<DisplayPartyIca> partyIcas) {
        this.partyId = partyId;
        this.displayName = displayName;
        this.nodeName = nodeName;
        this.partyIcas = partyIcas;
    }

    public PartyData(Long partyId, String displayName, String nodeName, boolean canSend) {
        this.partyId = partyId;
        this.displayName = displayName;
        this.nodeName = nodeName;
        this.canSend = canSend;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public boolean getCanSend() {
        return canSend;
    }

    public void setCanSend(boolean canSend) {
        this.canSend = canSend;
    }

    public void setPartyIcas(List<DisplayPartyIca> partyIcas) {
        this.partyIcas = partyIcas;
    }

    public List<DisplayPartyIca> getPartyIcas() {
        return partyIcas;
    }

    @Override
    public int compareTo(PartyData o) {
        int c = displayName.compareToIgnoreCase(o.getDisplayName());
        return c == 0 && nodeName != null ? nodeName.compareToIgnoreCase(o.getNodeName()) : c;
    }
}
