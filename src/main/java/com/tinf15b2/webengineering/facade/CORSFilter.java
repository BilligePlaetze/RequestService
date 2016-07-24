package com.tinf15b2.webengineering.facade;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.tinf15b2.webengineering.model.IgnoreCORSFilter;

@Provider
public class CORSFilter implements ContainerResponseFilter {

	@Context
	private ResourceInfo info;

	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {

		//Cors-Filter  will only added when everything is ok
		if (response.getStatus() == Status.OK.getStatusCode()) {
			IgnoreCORSFilter ignoreCorsFilter = info.getResourceMethod().getAnnotation(IgnoreCORSFilter.class);
			if (ignoreCorsFilter == null) {
				response.getHeaders().add("Access-Control-Allow-Origin", "*");
				response.getHeaders().add("Access-Control-Allow-Headers",
						"origin, content-type, accept, authorization");
				response.getHeaders().add("Access-Control-Allow-Credentials", "true");
				response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
			}
		}
	}

}
