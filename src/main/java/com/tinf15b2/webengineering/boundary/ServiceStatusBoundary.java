package com.tinf15b2.webengineering.boundary;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/")
public class ServiceStatusBoundary {
	@GET
	@Produces("text/html")
	public Response getStartingPage() {
		String output = "<h1>Tagcloudservice is running<h1>" + "<p>Ping @ " + new Date().toString() + "</p";
		log.info("Startpage was called");
		return Response.status(200).entity(output).build();
	}
}
