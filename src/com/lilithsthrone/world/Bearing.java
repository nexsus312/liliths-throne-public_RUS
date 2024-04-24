package com.lilithsthrone.world;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public enum Bearing {
	NORTH("север"),
	NORTH_EAST("северо-восток"),
	EAST("восток"),
	SOUTH_EAST("юго-восток"),
	SOUTH("юг"),
	SOUTH_WEST("юго-запад"),
	WEST("запад"),
	NORTH_WEST("северо-запад"),
	RANDOM("случайно");
	
	private String name;
	
	private Bearing(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	
}
