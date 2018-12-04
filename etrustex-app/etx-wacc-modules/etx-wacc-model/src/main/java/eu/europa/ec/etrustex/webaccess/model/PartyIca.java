package eu.europa.ec.etrustex.webaccess.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ETX_WEB_PARTY_ICA")
@AttributeOverrides({
        @AttributeOverride(name = "createdOn", column = @Column(name = "PCA_CREATED_ON", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "PCA_UPDATED_ON")),
        @AttributeOverride(name = "activeState", column = @Column(name = "PCA_STATE", nullable = false))})
@AssociationOverrides({
        @AssociationOverride(name = "createdBy", joinColumns = @JoinColumn(name = "PCA_CREATED_BY", nullable = false, updatable = false)),
        @AssociationOverride(name = "updatedBy", joinColumns = @JoinColumn(name = "PCA_UPDATED_BY"))})
@NamedQueries({
        @NamedQuery(name = "findICAsByParty", query = "SELECT ica FROM PartyIca ica WHERE ica.party = :party AND ica.activeState = 1"),
        @NamedQuery(name = "findICAsByActiveParties", query = "SELECT ica FROM PartyIca ica WHERE ica.party.activeState = 1 AND ica.remoteParty.activeState = 1"),
        @NamedQuery(name = "findAllICAsSenderReceiver", query = "SELECT NEW eu.europa.ec.etrustex.webaccess.model.vo.PartyIcaVO (ica.id, ica.party.nodeName, ica.remoteParty.nodeName) FROM PartyIca ica WHERE ica.activeState = 1"),
        @NamedQuery(name = "findICAsBySenderReceiver", query = "SELECT ica FROM PartyIca ica WHERE ica.party.nodeName = :sender AND ica.remoteParty.nodeName = :receiver"),
        @NamedQuery(name = "findActiveICAsBySenderReceiver", query = "SELECT ica FROM PartyIca ica WHERE ica.party.nodeName = :sender AND ica.remoteParty.nodeName = :receiver AND ica.activeState = 1"),
        @NamedQuery(name = "countActiveICAsBySenderReceiver", query = "SELECT count(ica.id) FROM PartyIca ica WHERE ica.party.nodeName = :sender AND ica.remoteParty.nodeName = :receiver AND ica.activeState = 1")
})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyIca extends StateAndTrackingInformation<Long> {

    private static final long serialVersionUID = 5639179221002035180L;

    @SequenceGenerator(name = "ETX_WEB_PARTYICASEQ", sequenceName = "ETX_WEB_PARTYICASEQ", allocationSize = 1)
    @Id
    @Column(name = "PCA_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ETX_WEB_PARTYICASEQ")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PAR_ID", nullable = false)
    private Party party;

    @ManyToOne
    @JoinColumn(name = "REMOTE_PAR_ID", nullable = false)
    private Party remoteParty;

    @Column(name = "PCA_LOADED_ON", nullable = false)
    private Date loadedOn;

    @Column(name = "PCA_INTEGRITY")
    private Integer integrityCode;

    @Column(name = "PCA_CONFIDENTIALITY")
    private Integer confidentialityCode;

    @Column(name = "PCA_CERTIFICATE")
    @Lob
    private String certificate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public Party getRemoteParty() {
        return remoteParty;
    }

    public void setRemoteParty(Party remoteParty) {
        this.remoteParty = remoteParty;
    }

    public Date getLoadedOn() {
        return loadedOn;
    }

    public void setLoadedOn(Date loadedOn) {
        this.loadedOn = loadedOn;
    }

    public Integer getIntegrityCode() {
        return integrityCode;
    }

    public void setIntegrityCode(Integer integrityCode) {
        this.integrityCode = integrityCode;
    }

    public Integer getConfidentialityCode() {
        return confidentialityCode;
    }

    public void setConfidentialityCode(Integer confidentialityCode) {
        this.confidentialityCode = confidentialityCode;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

}
