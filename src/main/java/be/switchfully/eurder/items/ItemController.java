package be.switchfully.eurder.items;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;

@Path("/items")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ItemController {
    @Inject
    ItemService itemService;

    @POST
    @Path("/add")
    public Response addItem(Item item) {
        try {
            itemService.addItem(item);
            return Response.status(Response.Status.CREATED).entity("Item added.").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public Collection<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateItem(@PathParam("id") String id, Item item) {
        try {
            itemService.updateItem(id, item);
            return Response.status(Response.Status.ACCEPTED).entity("Item updated.").build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
