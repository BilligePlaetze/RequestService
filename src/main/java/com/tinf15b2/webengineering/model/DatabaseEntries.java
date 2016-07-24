package com.tinf15b2.webengineering.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DatabaseEntries {

	@JacksonXmlProperty(localName = "Entry")

	@JacksonXmlElementWrapper(localName = "Entries")
	@JsonProperty
	private List<DatabaseEntry> databaseEntries = new LinkedList<>();

	public int size() {
		return databaseEntries.size();
	}

	@JsonIgnore
	public List<DatabaseEntry> getAllEntries() {
		Collections.sort(databaseEntries);
		return databaseEntries;
	}

	public void add(DatabaseEntry databaseEntry) {
		databaseEntries.add(databaseEntry);
	}

	public void sortEntries() {
		Collections.sort(databaseEntries);
	}

}
