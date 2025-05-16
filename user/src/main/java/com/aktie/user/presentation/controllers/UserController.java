package com.aktie.user.presentation.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import com.aktie.user.domain.entities.dto.UserDTO;
import com.aktie.user.services.UserService;

/**
 *
 * @author SRamos
 */
@Path("/api/v1/user")
public class UserController {

    @Inject
    UserService userService;

    @POST
    public Response create(UserDTO dto) {
        var createdUser = userService.create(dto);

        return Response.ok(createdUser).build();
    }

    @GET
    public Response findBy(@HeaderParam("userId") String userId) {
        var userFound = userService.findBy(userId);

        return Response.ok(userFound).build();
    }

    @GET
    @Path("/all")
    public Response findAllBy(@HeaderParam("document") String document) {
        var usersFound = userService.listBy(document);

        return Response.ok(usersFound).build();
    }

    @PUT
    public Response update(UserDTO dto, @HeaderParam("userId") String userId) {
        var updatedUser = userService.updateInfo(dto, userId);

        return Response.ok(updatedUser).build();
    }

    @DELETE
    public Response disable(@HeaderParam("userId") String userId) {
        var desibledUser = userService.disable(userId);

        return Response.ok(desibledUser).build();
    }

}