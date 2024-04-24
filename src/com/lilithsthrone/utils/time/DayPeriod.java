package com.lilithsthrone.utils.time;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * https://en.wikipedia.org/wiki/Twilight
 * 
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public enum DayPeriod {
	
	DAY("дневное время", PresetColour.BASE_BLUE_LIGHT),
	
	CIVIL_TWILIGHT("гражданские сумерки", PresetColour.BASE_PURPLE_LIGHT),
	
	NAUTICAL_TWILIGHT("морские сумерки", PresetColour.BASE_PURPLE),
	
	ASTRONOMICAL_TWILIGHT("астрономические сумерки", PresetColour.BASE_BLUE_STEEL),
	
	NIGHT("ночное время", PresetColour.BASE_BLUE_DARK);
	
	
	private String name;
	private Colour colour;
	
	private DayPeriod(String name, Colour colour) {
		this.name = name;
		this.colour = colour;
	}
	
	public String getName() {
		return name;
	}
	
	public Colour getColour() {
		return colour;
	}
}
