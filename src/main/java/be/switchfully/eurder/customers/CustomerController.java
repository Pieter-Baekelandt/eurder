package be.switchfully.eurder.customers;

import be.switchfully.eurder.security.Feature;
import be.switchfully.eurder.security.SecurityService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestHeader;

import java.util.Collection;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class CustomerController {
    @Inject
    CustomerService customerService;
    @Inject
    SecurityService securityService;

    @POST
    @Path("/register")
    public Response registerCustomer(CreateCustomerDTO createCustomerDTO) {
        try {
            customerService.registerCustomer(createCustomerDTO);
            return Response.status(Response.Status.CREATED).entity("Account created").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/customers")
    public Collection<CustomerDTO> getAllCustomers(@RestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.CONSULT_CUSTOMERS);
        return customerService.getAllCustomers();
    }

    @GET
    @Path("/customers/{id}")
    public Response getCustomerById(@RestHeader String authorization, @PathParam("id") String id) {
        securityService.validateAuthorization(authorization, Feature.CONSULT_CUSTOMERS);
        return Response.status(Response.Status.ACCEPTED).entity(customerService.getCustomerById(id)).build();
    }
}

