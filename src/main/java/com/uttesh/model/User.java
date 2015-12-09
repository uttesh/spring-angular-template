package com.uttesh.model;

import org.springframework.data.mongodb.core.mapping.Document;
/**
 *
 * @author Uttesh Kumar T.H.
 */
@Document
public class User extends BaseEntity {

    private String username;
    private String mobile;
    private String firmname;
    private String firmcode;
    private String aboutme;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFirmname() {
        return firmname;
    }

    public void setFirmname(String firmname) {
        this.firmname = firmname;
    }

    public String getFirmcode() {
        return firmcode;
    }

    public void setFirmcode(String firmcode) {
        this.firmcode = firmcode;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAboutme() {
        return aboutme;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme;
    }
    

}
