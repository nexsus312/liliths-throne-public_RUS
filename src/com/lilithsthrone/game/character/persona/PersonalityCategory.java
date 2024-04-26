package com.lilithsthrone.game.character.persona;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.5
 * @version 0.3.5
 * @author Innoxia
 */
public enum PersonalityCategory {

	CORE("основа", PresetColour.GENERIC_EXCELLENT),
	
	COMBAT("бой", PresetColour.GENERIC_COMBAT),
	
	SEX("секс", PresetColour.GENERIC_SEX),
	
	SPEECH("речь", PresetColour.BASE_PURPLE_LIGHT);

	private String name;
	private Colour colour;
	
	private PersonalityCategory( String name, Colour colour) {
		this.colour = colour;
		this.name = name;
	}

	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}
}
