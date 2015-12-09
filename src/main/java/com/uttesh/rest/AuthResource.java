package com.uttesh.rest;

import com.uttesh.model.User;
import com.uttesh.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 *
 * @author Uttesh Kumar T.H.
 */
@Component
@Path("/public/auth/")
public class AuthResource {

    private final Logger logger = Logger.getLogger(AuthResource.class);

    @Autowired
    private UserService userService;

    protected UserService getService() {
        return userService;
    }

    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(
            User user,
            @Context HttpServletRequest request) {
        try {
            user = userService.create(user);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        try {
            return Response.status(Response.Status.OK).entity(user).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

}
