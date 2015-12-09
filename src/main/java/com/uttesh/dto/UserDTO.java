package com.uttesh.dto;


import com.uttesh.model.User;
/**
 *
 * @author Uttesh Kumar T.H.
 */
public class UserDTO {

    private final String username;
    private final String id;
    private final String mobile;
    private final String firmname;
    private final String firmcode;
    private final String aboutme;

    public String getId() {
        return id;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.mobile = user.getMobile();
        this.firmcode = user.getFirmcode();
        this.firmname = user.getFirmname();
        this.aboutme = user.getAboutme();
    }


    public String getUsername() {
        return username;
    } 

    public String getMobile() {
        return mobile;
    }

    public String getFirmname() {
        return firmname;
    }

    public String getFirmcode() {
        return firmcode;
    }

    public String getAboutme() {
        return aboutme;
    }
    
    

}
