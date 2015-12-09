package com.uttesh.model;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class Role {

    String label;
    String value;
    String status = "Active";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
