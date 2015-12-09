package com.uttesh.rest;


import com.uttesh.model.BaseEntity;
import com.uttesh.service.AbstractEntityService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
/**
 *
 * @author Uttesh Kumar T.H.
 */
@Component
abstract public class  AbstractEntityResource<T extends BaseEntity> extends AbstractResource {
 
    abstract AbstractEntityService<T> getService();
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String id) {
        T t = getService().findOne(id);
        if (t != null) {
            return Response.status(Response.Status.OK).entity(t).build();
        } 
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        List<T> list = getService().findAll(new Sort(Sort.Direction.DESC, "createdOn"));
        return Response.status(Response.Status.OK).entity(list).build();
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("id") String id) {
        getService().delete(id);
        return Response.status(Response.Status.OK).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(T t) {
        t = getService().save(t);
        return Response.status(Response.Status.OK).entity(t).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(T t) {
        t = getService().save(t);
        return Response.status(Response.Status.OK).entity(t).build();
    }

    
}
