package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public enum Wetness {

	ZERO_DRY("сухо", 0, 101, 101, PresetColour.GENERIC_WETNESS_ONE),
	
	ONE_SLIGHTLY_MOIST("слегка влажно", 1, 100, 100, PresetColour.GENERIC_WETNESS_TWO),
	
	TWO_MOIST("влажно", 2, 50, 50, PresetColour.GENERIC_WETNESS_THREE),
	
	THREE_WET("мокро", 3, 25, 25, PresetColour.GENERIC_WETNESS_FOUR),
	
	FOUR_SLIMY("склизко", 4, 0, 0, PresetColour.GENERIC_WETNESS_FIVE),
	
	FIVE_SLOPPY("очень мокро", 5, 0, 0, PresetColour.GENERIC_WETNESS_SIX),
	
	SIX_SOPPING_WET("промокло насквозь", 6, 0, 0, PresetColour.GENERIC_WETNESS_SEVEN),
	
	SEVEN_DROOLING("истекает соками", 7, 0, 0, PresetColour.GENERIC_WETNESS_EIGHT);
	

	private int wetness;
	private int arousalNeededToGetVaginaWet;
	private int arousalNeededToGetAssWet;
	private String descriptor;
	private Colour colour;

	private Wetness(String descriptor, int wetness, int arousalNeededToGetVaginaWet, int arousalNeededToGetAssWet, Colour colour) {
		this.descriptor = descriptor;
		this.wetness = wetness;
		this.arousalNeededToGetVaginaWet = arousalNeededToGetVaginaWet;
		this.arousalNeededToGetAssWet = arousalNeededToGetAssWet;
		this.colour = colour;
	}

	public static Wetness valueOf(int wetness) {
		for(Wetness w : Wetness.values()) {
			if(wetness == w.getValue()) {
				return w;
			}
		}
		return SEVEN_DROOLING;
	}

	/**
	 * To fit into a sentence such as:<br/>
	 * "Your vagina is "+getDescriptor()+"."<br/>
	 * "Your "+getDescriptor()+" asshole is stretched wide open."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public int getValue() {
		return wetness;
	}

	public int getArousalNeededToGetVaginaWet() {
		return arousalNeededToGetVaginaWet;
	}

	public int getArousalNeededToGetAssWet() {
		return arousalNeededToGetAssWet;
	}

	public Colour getColour() {
		return colour;
	}
}
