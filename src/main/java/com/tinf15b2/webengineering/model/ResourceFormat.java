package com.tinf15b2.webengineering.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResourceFormat {
	DTD("staticContent/DTDFiles/", ".dtd");

	private String resourceDir;
	private String ending;
}
