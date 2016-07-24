package com.tinf15b2.webengineering.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TagCloudData {

	@JsonProperty
	@JacksonXmlProperty(localName = "EntriesByLabel")
	private Map<String, DatabaseEntries> entriesByLabel = new HashMap<>();

	public void addEntry(DatabaseEntry databaseEntryToAdd) {

		String label = databaseEntryToAdd.getLabel();

		if (entriesByLabel.containsKey(label)) {
			entriesByLabel.get(label).add(databaseEntryToAdd);

		} else {
			DatabaseEntries databaseEntries = new DatabaseEntries();
			databaseEntries.add(databaseEntryToAdd);
			entriesByLabel.put(databaseEntryToAdd.getLabel(), databaseEntries);
		}
	}

	public void sortEntries() {
		for (String key : entriesByLabel.keySet()) {
			entriesByLabel.get(key).sortEntries();
		}
	}
}
