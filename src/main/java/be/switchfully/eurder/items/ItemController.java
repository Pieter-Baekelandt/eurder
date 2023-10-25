package be.switchfully.eurder.items;

import be.switchfully.eurder.security.Feature;
import be.switchfully.eurder.security.SecurityService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestHeader;

import java.util.Collection;

@Path("/items")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ItemController {
    @Inject
    ItemService itemService;
    @Inject
    SecurityService securityService;
    @POST
    @Path("/add")
    public Response addItem(@RestHeader String authorization, Item item) {
        securityService.validateAuthorization(authorization, Feature.ADD_NEW_ITEM);
        try {
            itemService.addItem(item);
            return Response.status(Response.Status.CREATED).entity("Item added.").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public Collection<ItemDTO> getAllItems(@RestHeader String authorization) {
        securityService.validateAuthorization(authorization,Feature.CONSULT_ITEMS);
        return itemService.getAllItems();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateItem(@RestHeader String authorization, @PathParam("id") String id, Item item) {
        securityService.validateAuthorization(authorization,Feature.UPDATE_ITEM);
        try {
            itemService.updateItem(id, item);
            return Response.status(Response.Status.ACCEPTED).entity("Item updated.").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
