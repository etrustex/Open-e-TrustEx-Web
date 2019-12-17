package eu.europa.ec.etrustex.webaccess.model.vo;

public class NodeIcaKey {
    private final String localParty;
    private final String remoteParty;

    public NodeIcaKey(String localParty, String remoteParty) {
        this.localParty = localParty;
        this.remoteParty = remoteParty;
    }

    public String getLocalParty() {
        return localParty;
    }

    public String getRemoteParty() {
        return remoteParty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeIcaKey that = (NodeIcaKey) o;

        if (localParty != null ? !localParty.equals(that.localParty) : that.localParty != null) return false;
        if (remoteParty != null ? !remoteParty.equals(that.remoteParty) : that.remoteParty != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = localParty != null ? localParty.hashCode() : 0;
        result = 31 * result + (remoteParty != null ? remoteParty.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NodeIcaKey{");
        sb.append("localParty='").append(localParty).append('\'');
        sb.append(", remoteParty='").append(remoteParty).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
