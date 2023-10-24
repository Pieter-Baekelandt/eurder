package be.switchfully.eurder.customers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class CustomerController {
    @Inject
    CustomerService customerService;

    @POST
    @Path("/register")
    public Response registerCustomer(Customer customer) {
        try {
            customerService.registerCustomer(customer);
            return Response.status(Response.Status.CREATED).entity("Account created").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/customers")
    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}

