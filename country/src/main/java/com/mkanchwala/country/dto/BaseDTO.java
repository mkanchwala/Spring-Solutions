package com.mkanchwala.country.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mkanchwala
 *
 */
public class BaseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Date dateCreated;
	private Date lastUpdated;
	private String createdBy;
	private String lastUpdatedBy;
	
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result	+ ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result	+ ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		result = prime * result	+ ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseDTO other = (BaseDTO) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (lastUpdated == null) {
			if (other.lastUpdated != null)
				return false;
		} else if (!lastUpdated.equals(other.lastUpdated))
			return false;
		if (lastUpdatedBy == null) {
			if (other.lastUpdatedBy != null)
				return false;
		} else if (!lastUpdatedBy.equals(other.lastUpdatedBy))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "BaseDTO [dateCreated=" + dateCreated + ", lastUpdated="	+ lastUpdated + ", createdBy=" + createdBy + ", lastUpdatedBy="	+ lastUpdatedBy + "]";
	}
}
