package com.tinf15b2.webengineering.boundary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.tinf15b2.webengineering.facade.Control;
import com.tinf15b2.webengineering.model.ResourceFormat;

import lombok.extern.slf4j.Slf4j;

@Path("/resources/static/")
@Slf4j
public class StaticBoundary {

	@Inject
	private Control control;

	@GET
	@Path("{fileName}.dtd")
	public Response getDTDFiles(@PathParam("fileName") String requestedResourceName) {
		log.info("DTD resource was requested");
		return getResponseFor(requestedResourceName, ResourceFormat.DTD);
	}

	private Response getResponseFor(String requestedResourceName, ResourceFormat format) {

		try {
			FileInputStream fileInputStream = control.getStreamTo(requestedResourceName, format);
			log.info("Returning static file");
			return Response.ok().entity(fileInputStream).build();

		} catch (FileNotFoundException | URISyntaxException | NullPointerException e) {

			log.info("Error with: " + requestedResourceName + " --> " + e.getMessage());
			return Response.status(Status.NOT_FOUND).build();

		}
	}
}
