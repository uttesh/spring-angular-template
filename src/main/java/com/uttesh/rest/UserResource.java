package com.uttesh.rest;

import com.uttesh.dto.Person;
import com.uttesh.model.User;
import com.uttesh.service.AbstractEntityService;
import com.uttesh.service.UserService;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 *
 * @author Uttesh Kumar T.H.
 */
@Component
@Path("/secure/users/")
public class UserResource extends AbstractEntityResource<User> {

    private final Logger logger = Logger.getLogger(UserResource.class);

    @Autowired
    private UserService userService;

    @GET
    @Path("me")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getMe(@Context HttpServletRequest request) throws UnsupportedEncodingException {
        Person person = new Person();
        person.setFirstName("Uttesh");
        person.setLastName("Kumar T.H.");
        return person;
    }

    @Override
    AbstractEntityService<User> getService() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
