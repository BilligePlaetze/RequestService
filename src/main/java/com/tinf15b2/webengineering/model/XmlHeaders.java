package com.tinf15b2.webengineering.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum XmlHeaders {
	TagCloudDtd("<?xml version=\"1.0\"?><!DOCTYPE TagCloudData PUBLIC \"-//Tinf15B2//Tagcloud DTD Version 1.0//EN\" "
			+ "\"/resources/static/tagcloud.dtd\">");

	private String header;
}
