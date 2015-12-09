package com.uttesh.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
/**
 *
 * @author Uttesh Kumar T.H.
 */
public class BaseEntity {

    @Id
    protected String id;
    
    protected Date createdOn;
    protected Date updatedOn;

    @Override
    public String toString() {
        return "BaseEntity{" + "id=" + id + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "}";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

}
