package eu.europa.ec.etrustex.webaccess.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "ETX_WEB_COMPATIBLE_BROWSER")
@NamedQueries({
        @NamedQuery(name = "findAllCompatibleBrowsers", query = "SELECT cb FROM CompatibleBrowser cb",
                hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class CompatibleBrowser extends AbstractEntity<Long> {

    @Id
    @Column(name = "CBR_ID")
    @NotFound(action = NotFoundAction.IGNORE)
    private Long id;

    @Column(name="CBR_NAME")
    private String name;

    @Column(name="CBR_VERSION")
    private int version;

    @Column(name="CBR_ICON_PATH")
    private String path;

    @Column(name="CBR_COMMENT")
    private String comment;

    @Column(name="CBR_IS_COMPATIBLE")
    private Boolean isCompatible;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public Boolean getCompatible() {
        return isCompatible;
    }

    public void setCompatible(Boolean compatible) {
        isCompatible = compatible;
    }
}
