package com.tinf15b2.webengineering.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JacksonXmlRootElement
public class DatabaseEntry implements Comparable<DatabaseEntry> {

	@JsonIgnore
	private String label;

	@JacksonXmlProperty(localName = "HashTag")
	private String hashTag;
	@JacksonXmlProperty(localName = "Counts")
	private long counts;

	@Override
	public int compareTo(DatabaseEntry o) {
		if (counts > o.getCounts()) {
			return -1;
		}

		if (counts < o.getCounts()) {
			return 1;
		}

		return hashTag.compareToIgnoreCase(o.getHashTag());
	}
}
