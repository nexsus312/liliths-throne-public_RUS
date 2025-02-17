package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Measured in cm.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum HairLength {
	/** Bald */
	ZERO_BALD("лысина", 0, 1, PresetColour.GENERIC_SIZE_ONE, false),
	/** Very short */
	ONE_VERY_SHORT("очень коротко", 1, 8, PresetColour.GENERIC_SIZE_TWO, false),
	/** Short */
	TWO_SHORT("коротко", 8, 15, PresetColour.GENERIC_SIZE_THREE, true),
	/** above the shoulders */
	THREE_SHOULDER_LENGTH("по плечи", 15, 30, PresetColour.GENERIC_SIZE_FOUR, true),
	/** Reaching down to mid-back */
	FOUR_MID_BACK("длинно", 30, 60, PresetColour.GENERIC_SIZE_FIVE, true),
	/** Reaching down to just above the ass */
	FIVE_ABOVE_ASS("очень длинно", 60, 100, PresetColour.GENERIC_SIZE_SIX, true),
	/** Reaching down to below the ass */
	SIX_BELOW_ASS("невероятно длинно", 100, 180, PresetColour.GENERIC_SIZE_SEVEN, true),
	/** Hair so long that it reaches the floor */
	SEVEN_TO_FLOOR("по-пол", 180, 350, PresetColour.GENERIC_SIZE_EIGHT, true);

	private int minimumValue, maximumValue;
	private String descriptor;
	private Colour colour;
	private boolean suitableForPulling;

	private HairLength(String descriptor, int minimumValue, int maximumValue, Colour colour, boolean suitableForPulling) {
		this.descriptor = descriptor;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
		this.suitableForPulling = suitableForPulling;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}

	public int getMedianValue() {
		return minimumValue + (maximumValue - minimumValue) / 2;
	}
	
	public static HairLength getShorter(int currentHairLength){
		if (currentHairLength <= ZERO_BALD.maximumValue)
			return ZERO_BALD;
		else if (currentHairLength <= ONE_VERY_SHORT.maximumValue)
			return ZERO_BALD;
		else if (currentHairLength <= TWO_SHORT.maximumValue)
			return ONE_VERY_SHORT;
		else if (currentHairLength <= THREE_SHOULDER_LENGTH.maximumValue)
			return TWO_SHORT;
		else if (currentHairLength <= FOUR_MID_BACK.maximumValue)
			return THREE_SHOULDER_LENGTH;
		else if (currentHairLength <= FIVE_ABOVE_ASS.maximumValue)
			return FOUR_MID_BACK;
		else if (currentHairLength <= SIX_BELOW_ASS.maximumValue)
			return FIVE_ABOVE_ASS;
		else
			return SIX_BELOW_ASS;
	}
	
	public static HairLength getLonger(int currentHairLength){
		if (currentHairLength <= ZERO_BALD.maximumValue)
			return ONE_VERY_SHORT;
		else if (currentHairLength <= ONE_VERY_SHORT.maximumValue)
			return TWO_SHORT;
		else if (currentHairLength <= TWO_SHORT.maximumValue)
			return THREE_SHOULDER_LENGTH;
		else if (currentHairLength <= THREE_SHOULDER_LENGTH.maximumValue)
			return FOUR_MID_BACK;
		else if (currentHairLength <= FOUR_MID_BACK.maximumValue)
			return FIVE_ABOVE_ASS;
		else if (currentHairLength <= FIVE_ABOVE_ASS.maximumValue)
			return SIX_BELOW_ASS;
		else if (currentHairLength <= SIX_BELOW_ASS.maximumValue)
			return SEVEN_TO_FLOOR;
		else
			return SEVEN_TO_FLOOR;
	}

	public static HairLength getHairLengthFromInt(int hairLength) {
		for(HairLength hl : HairLength.values()) {
			if(hairLength>=hl.getMinimumValue() && hairLength<hl.getMaximumValue()) {
				return hl;
			}
		}
		return SEVEN_TO_FLOOR;
	}

	/**
	 * An indication of hair length.
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public Colour getColour() {
		return colour;
	}

	public boolean isSuitableForPulling() {
		return suitableForPulling;
	}
}
