package com.aktie.sale.presentation.controllers;

import com.aktie.sale.domain.entities.dto.SaleDTO;
import com.aktie.sale.services.SaleService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author SRamos
 */
@Path("/api/v1/sale")
public class SaleController {

    @Inject
    SaleService userService;

    @POST
    public Response create(SaleDTO dto) {
        var createdUser = userService.create(dto);

        return Response.ok(createdUser).build();
    }

    @GET
    public Response findBy(@HeaderParam("saleId") String userId) {
        var userFound = userService.findBy(userId);

        return Response.ok(userFound).build();
    }

}