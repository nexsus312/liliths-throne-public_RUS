package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.86
 * @version 0.4
 * @author Innoxia
 */
public enum Height {
	
	// Need to standardise to 1.5 each category

	/**6" to 2'*/
	NEGATIVE_THREE_MINIMUM("размером с фею", 15, 61, PresetColour.GENERIC_SIZE_ONE),
	
	/**2' to 3'6"*/
	NEGATIVE_TWO_MINIMUM("очень маленьк(ий,ая)", 61, 106, PresetColour.GENERIC_SIZE_ONE),
	
	/**3'6" to 4'*/
	NEGATIVE_ONE_TINY("крохотн(ый,ая)", 106, 122, PresetColour.GENERIC_SIZE_ONE),
	
	/**4' to 5'*/
	ZERO_TINY("маленьк(ий,ая)", 122, 152, PresetColour.GENERIC_SIZE_ONE),
	
	/**5' to 5'6"*/
	ONE_SHORT("коротк(ий,ая)", 152, 166, PresetColour.GENERIC_SIZE_TWO),

	/**5'6" to 6'*/
	TWO_AVERAGE("средний рост", 166, 183, PresetColour.GENERIC_SIZE_THREE),

	/**6' to 6'6"*/
	THREE_TALL("высок(ий,ая)", 183, 198, PresetColour.GENERIC_SIZE_FOUR),

	/**6'6" to 7'*/
	FOUR_VERY_TALL("очень высок(ий,ая)", 198, 214, PresetColour.GENERIC_SIZE_FIVE),

	/**7' to 7'6"*/
	FIVE_ENORMOUS("возвышается", 214, 228, PresetColour.GENERIC_SIZE_SIX),

	/**7'6" to 9'"*/
	SIX_GIANT("гигантск(ий,ая)", 228, 274, PresetColour.GENERIC_SIZE_SEVEN),

	/**9' to 12'*/
	SEVEN_COLOSSAL("колосальн(ая,ый)", 274, 366, PresetColour.GENERIC_SIZE_EIGHT);
	
	private int minimumValue;
	private int maximumValue;
	private String descriptor;
	private Colour colour;

	private Height(String descriptor, int minimumValue, int maximumValue, Colour colour) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}

	public int getMedianValue() {
		return (minimumValue + maximumValue) / 2;
	}

	public int getRandomValue() {
		return minimumValue + Util.random.nextInt(maximumValue - minimumValue);
	}
	
	/**
	 * If height is less than this value, then that height is short stature.
	 */
	public static int getShortStatureCutOff() {
		return ZERO_TINY.getMinimumValue();
	}
	
	public boolean isShortStature() {
		return this.getMinimumValue()<getShortStatureCutOff();
	}
	
	public static Height getHeightFromInt(int centimeters) {
		for(Height cs : Height.values()) {
			if(centimeters >= cs.getMinimumValue() && centimeters < cs.getMaximumValue()) {
				return cs;
			}
		}
		return SEVEN_COLOSSAL;
	}
	
	public String getDescriptor() {
		return descriptor;
	}

	public Colour getColour() {
		return colour;
	}
}
