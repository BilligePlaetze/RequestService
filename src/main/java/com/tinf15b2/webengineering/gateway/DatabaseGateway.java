package com.tinf15b2.webengineering.gateway;

import java.util.List;

import com.tagcloud.persistence.RequestHandler;
import com.tagcloud.persistence.RequestResult;
import com.tagcloud.persistence.TagcloudRequest;
import com.tagcloud.persistence.exception.UnsupportedRequestException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseGateway {

	private RequestHandler requestHandler;

	public DatabaseGateway() {
		requestHandler = RequestHandler.getInstance();
	}

	public List<RequestResult> getTagCloudDataFor(TagcloudRequest request) throws UnsupportedRequestException {
		log.info("Request database");
		return requestHandler.requestRepository(request);
	}

	public void closeConnection() {
		requestHandler.close();
	}
}
