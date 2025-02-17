package com.lilithsthrone.game.character.body.valueEnums;

import java.util.List;

import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.0
 * @version 0.3.8.2
 * @author Innoxia
 */
public enum FluidTypeBase {
	
	CUM(Util.newArrayListOfValues("сперма", "крем", "спущенка", "малафья", "семя", "конча"),
			BodyCoveringType.CUM,
			PresetColour.CUM),
	
	GIRLCUM(Util.newArrayListOfValues("конча"),
			BodyCoveringType.GIRL_CUM,
			PresetColour.GIRLCUM),
	
	MILK(Util.newArrayListOfValues("молоко"),
			BodyCoveringType.MILK,
			PresetColour.MILK);
	
	private List<String> names;
	private AbstractBodyCoveringType coveringType;
	private Colour colour;

	private FluidTypeBase(List<String> names, AbstractBodyCoveringType coveringType, Colour colour) {
		this.names = names;
		this.coveringType = coveringType;
		this.colour = colour;
	}

	public List<String> getNames() {
		return names;
	}
	
	public AbstractBodyCoveringType getCoveringType() {
		return coveringType;
	}

	public Colour getColour() {
		return colour;
	}
}
