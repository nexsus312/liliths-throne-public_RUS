package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Arbitrary measurements in increments of 1, going from 0 to 7.
 * 
 * @since 0.1.83
 * @version 0.3.1
 * @author Innoxia
 */
public enum OrificePlasticity {

	/*-------MOULDABLE:---------*/

	/**Instantly returns to starting value after sex.*/
	ZERO_RUBBERY("резинов(ая,ый)",
			"[style.colourPlasticity(мгновенно восстанавливает свою вместимость)]",
			"[style.colourPlasticity(мгновенно возвращается к своей изначальной вместимости)]",
			0, 0, 100, PresetColour.GENERIC_SIZE_ONE),

	/**Quickly returns to starting value after sex, at a rate of 2 cm per 30 minutes.*/
	ONE_SPRINGY("пружинист(ая,ый)",
			"[style.colourPlasticity(очень быстро восстанавливает всю свою изначальную вместимость)]",
			"[style.colourPlasticity(очень быстро восстанавливает всю их изначальную вместимость)]",
			1, 0, 2/(60*30f), PresetColour.GENERIC_SIZE_TWO),

	/**Returns to starting value after sex, at a rate of 2 cm per hour.*/
	TWO_TENSILE("растяжим(ая,ый)",
			"[style.colourPlasticity(восстанавливает всю свою изначальную вместимость со средней скоростью)]",
			"[style.colourPlasticity(восстанавливает всю их изначальную вместимость со средней скоростью)]",
			2, 0, 2/(60*60f), PresetColour.GENERIC_SIZE_THREE),

	/*-------NORMAL:---------*/

	/**Normal value, along with FOUR_ACCOMMODATING. Will slowly return to starting value after sex, at a rate of 2 cm per 3 hours.*/
	THREE_RESILIENT("упруг(ая,ий)",
			"[style.colourPlasticity(медленно восстанавливает всю свою изначальную вместимость)]",
			"[style.colourPlasticity(медленно восстанавливает всю их изначальную вместимость)]",
			3, 0, 2/(60*60f*4), PresetColour.GENERIC_SIZE_FOUR),

	/**Normal value, along with THREE_RESILIENT. Will recover about 98% of its original size after sex, at a rate of 2 cm per 12 hours.*/
	FOUR_ACCOMMODATING("вместительн(ая,ый)",
			"[style.colourPlasticity(очень медленно восстанавливает [style.italicsMinorBad(большую часть, но не всю)], своей изначальной вместимости)]",
			"[style.colourPlasticity(очень медленно восстанавливает [style.italicsMinorBad(большую часть, но не всю)], их изначальной вместимости)]",
			4, 0.2f, 2/(60*60f*12), PresetColour.GENERIC_SIZE_FIVE),

	/*-------STRETCHY:---------*/

	/**Will recover about 80% of its original size after sex, at a rate of 2 cm per 24 hours.*/
	FIVE_YIELDING("поддающ(аяся,ийся)",
			"[style.colourPlasticity(очень медленно восстанавливает [style.italicsMinorBad(часть, но не всю)], своей изначальной вместимости)]",
			"[style.colourPlasticity(очень медленно восстанавливает [style.italicsMinorBad(часть, но не всю)], их изначальной вместимости)]",
			5, 0.2f, 2/(60*60f*24), PresetColour.GENERIC_SIZE_SIX),

	/**Will only recover about 40% of its original size after sex, at a rate of 2 cm per 24 hours.*/
	SIX_MALLEABLE("тягуч(ая,ий)",
			"[style.colourPlasticity(очень медленно восстанавливает [style.italicsBad(только часть)] своей изначальной вместимости)]",
			"[style.colourPlasticity(очень медленно восстанавливает [style.italicsBad(только часть)] их изначальной вместимости)]",
			6, 0.6f, 2/(60*60f*24), PresetColour.GENERIC_SIZE_SEVEN),

	/**Will recover none of its original size after sex.*/
	SEVEN_MOULDABLE("формируем(ая,ый)",
			"остается [style.italicsTerrible(постоянно растянуто)] [style.colourPlasticity(и не восстанавливает своей изначальной вместимости)]",
			"остается [style.italicsTerrible(постоянно растянуто)] [style.colourPlasticity(и не восстанавливает их изначальной вместимости)]",
			7, 1, 2/(60*60f*24), PresetColour.GENERIC_SIZE_EIGHT);

	
	private String descriptor;
	private String description;
	private String descriptionPlural;
	private int value;
	private float capacityIncreaseModifier;
	private float recoveryModifier;
	private Colour colour;

	private OrificePlasticity(String descriptor, String description, String descriptionPlural, int value, float capacityIncreaseModifier, float recoveryModifier, Colour colour) {
		this.descriptor = descriptor;
		this.description = description;
		this.descriptionPlural = descriptionPlural;
		this.value = value;
		this.capacityIncreaseModifier = capacityIncreaseModifier;
		this.recoveryModifier = recoveryModifier;
		this.colour = colour;
	}

	public static OrificePlasticity getElasticityFromInt(int value) {
		for(OrificePlasticity oe : OrificePlasticity.values()) {
			if(value == oe.getValue()) {
				return oe;
			}
		}
		return SEVEN_MOULDABLE;
	}

	/**
	 * To fit into a sentence: "You have a "+getDescriptor()+" ass."
	 */
	public String getDescriptor() {
		return descriptor;
	}
	public String getDescription() {
		return description;
	}
	public String getDescriptionPlural() {
		return descriptionPlural;
	}

	public int getValue() {
		return value;
	}

	public float getRecoveryModifier() {
		return recoveryModifier;
	}
	
	public float getCapacityIncreaseModifier() {
		return capacityIncreaseModifier;
	}

	public Colour getColour() {
		return colour;
	}
}
