package com.tinf15b2.webengineering.facade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;

import com.tagcloud.persistence.RequestResult;
import com.tagcloud.persistence.TagcloudRequest;
import com.tagcloud.persistence.exception.UnsupportedRequestException;
import com.tinf15b2.webengineering.gateway.DatabaseGateway;
import com.tinf15b2.webengineering.model.DatabaseEntry;
import com.tinf15b2.webengineering.model.ResourceFormat;
import com.tinf15b2.webengineering.model.TagCloudData;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Control {

	@Inject
	private DatabaseGateway databaseGateway;

	public TagCloudData getDataFor(String label, Long from, Long to, Integer limit) throws UnsupportedRequestException {

		TagcloudRequest request = TagcloudRequest.builder() //
				.tag(label) //
				.fromTimestamp(from) //
				.toTimestamp(to)//
				.limit(limit) //
				.build();

		List<RequestResult> results = databaseGateway.getTagCloudDataFor(request);
		return convertListInTagcloudData(results);
	}

	private TagCloudData convertListInTagcloudData(List<RequestResult> results) {

		TagCloudData tagCloudData = new TagCloudData();

		for (RequestResult result : results) {
			DatabaseEntry entry = DatabaseEntry.builder() //
					.label(result.getTag()) //
					.hashTag(result.getTagWord()) //
					.counts(result.getCounts()) //
					.build();//

			tagCloudData.addEntry(entry);
		}
		tagCloudData.sortEntries();
		return tagCloudData;

	}

	public FileInputStream getStreamTo(String nameOfRequestedResource, ResourceFormat reFormat)
			throws NullPointerException, URISyntaxException, FileNotFoundException {

		String pathToResource = buildPathToResource(nameOfRequestedResource, reFormat);

		URI uriToFile = this.getClass().getClassLoader().getResource(pathToResource).toURI();
		File file = new File(uriToFile);
		return new FileInputStream(file);
	}

	private String buildPathToResource(String resourceName, ResourceFormat format) {
		return format.getResourceDir() + resourceName + format.getEnding();
	}

	public void closeDatabaseConnection() {
		databaseGateway.closeConnection();
	}
}
