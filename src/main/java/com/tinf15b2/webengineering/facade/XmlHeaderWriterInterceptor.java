package com.tinf15b2.webengineering.facade;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import com.tinf15b2.webengineering.model.XmlHeaderAnnotation;

@Provider
public class XmlHeaderWriterInterceptor implements WriterInterceptor {

	@Context
	private ResourceInfo info;

	@Override
	public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
		final OutputStream outputStream = context.getOutputStream();

		if (context.getMediaType().equals(MediaType.APPLICATION_XML_TYPE)) {
			XmlHeaderAnnotation anno = info.getResourceMethod().getAnnotation(XmlHeaderAnnotation.class);
			if (anno != null) {
				String value = anno.value().getHeader();
				outputStream.write(value.getBytes());
			}
		}

		context.proceed();
	}
}