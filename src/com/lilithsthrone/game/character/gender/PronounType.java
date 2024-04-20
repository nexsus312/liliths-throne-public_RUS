package com.lilithsthrone.game.character.gender;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.86
 * @version 0.1.86
 * @author Innoxia
 */
public enum PronounType {
	FEMININE("женский", PresetColour.FEMININE),
	NEUTRAL("неопределенный", PresetColour.ANDROGYNOUS),
	MASCULINE("мужской", PresetColour.MASCULINE);
	
	private String name;
	private Colour colour;
	
	private PronounType(String name, Colour colour) {
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
