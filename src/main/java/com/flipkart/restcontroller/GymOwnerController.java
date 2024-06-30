package com.flipkart.restcontroller;

import com.flipkart.model.*;
import com.flipkart.service.*;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.validation.Validator;

import java.util.List;

@Path("/gymowner")
@Produces(MediaType.APPLICATION_JSON)
public class GymOwnerController {
    Validator validator;
    FlipFitGymOwnerService gymOwnerService;

    public GymOwnerController() {
        this.validator = validator;
        this.gymOwnerService = gymOwnerService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(Credentials credentials){
        if(gymOwnerService.validateLogin(credentials.getUser(), credentials.getPassword())){
            return Response.ok().build();
        }
        else return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addgymwithslots")
    public Response addGymWithSlots(FlipFitGym gym){
        gymOwnerService.addGymWithSlots(gym);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response createGymOwner(FlipFitGymOwner gymOwner){
        gymOwnerService.createGymOwner(gymOwner);
        return Response.ok().build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/viewMyGyms/{id}")
    public Response viewMyGyms(@PathParam("id") String id){
        List<FlipFitGym> gymList = gymOwnerService.viewMyGyms(id);
        return Response.ok(gymList).build();
    }
}
