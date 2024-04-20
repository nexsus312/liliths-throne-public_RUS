package com.lilithsthrone.game.character.gender;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.69
 * @version 0.1.69
 * @author Innoxia
 */
public enum AndrogynousIdentification {
	FEMININE("Женщина", PresetColour.FEMININE),
	CLOTHING_FEMININE("Женственная одежда", PresetColour.ANDROGYNOUS),
	CLOTHING_MASCULINE("Мужественная одежда", PresetColour.ANDROGYNOUS),
	MASCULINE("Мужчина", PresetColour.MASCULINE);
	
	private String name;
	private Colour colour;
	
	private AndrogynousIdentification(String name, Colour colour) {
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
