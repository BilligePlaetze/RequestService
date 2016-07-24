package com.tinf15b2.webengineering.boundary;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tagcloud.persistence.exception.UnsupportedRequestException;
import com.tinf15b2.webengineering.facade.Control;
import com.tinf15b2.webengineering.model.TagCloudData;
import com.tinf15b2.webengineering.model.XmlHeaderAnnotation;
import com.tinf15b2.webengineering.model.XmlHeaders;

import lombok.extern.slf4j.Slf4j;

@Path("/resources/tagcloud")
@Slf4j
public class TagCloudBoundary {

	@Inject
	private Control control;

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@XmlHeaderAnnotation(XmlHeaders.TagCloudDtd)
	public Response getTagCloudDataAsXml( //
			@QueryParam("label") String label, //
			@QueryParam("from") Long from, //
			@QueryParam("to") Long to, //
			@QueryParam("limit") Integer limit) { //

		log.info(String.format("/resources/tagcloud was called with label= %s, from=%d, to=%d, limit=%d", label, from,
				to, limit));
		try {

			TagCloudData data = control.getDataFor(label, from, to, limit);
			Response response = Response//
					.ok() //
					.entity(data) //
					.build();
			log.info("Returning data");
			return response;

		} catch (UnsupportedRequestException e1) {

			log.error("" + e1);
			return Response //
					.status(400) //
					.entity("UnsupportedRequest: " + e1.getMessage()) //
					.type(MediaType.TEXT_HTML) //
					.build();
		}
	}
}
