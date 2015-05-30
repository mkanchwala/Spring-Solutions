package com.jellybelly.user.beans;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * @author mkanchwala
 */
@MappedSuperclass
public abstract class BaseEntity<ID> {
    @Column(name = "date_created", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateCreated;

    @Column(name = "last_updated", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastUpdated;

    @Version
    private long version;

    public abstract ID getId();

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public DateTime getLastUpdated() {
        return lastUpdated;
    }

    public long getVersion() {
        return version;
    }

    @PrePersist
    public void prePersist() {
        DateTime now = DateTime.now();
        this.dateCreated = now;
        this.lastUpdated = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdated = DateTime.now();
    }
}