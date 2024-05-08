package com.lilithsthrone.game.sex;

import com.lilithsthrone.utils.colours.BaseColour;

/**
 * @since 0.1.69.9
 * @version 0.3.1
 * @author Innoxia
 */
public enum SexPace {
	
	SUB_RESISTING(false, "сопротивляясь", BaseColour.CRIMSON),
	SUB_NORMAL(false, "нормально", BaseColour.PINK),
	SUB_EAGER(false, "подчиняясь", BaseColour.PINK_DEEP),
	
	DOM_GENTLE(true, "нежно", BaseColour.PINK_LIGHT),
	DOM_NORMAL(true, "нормально", BaseColour.PINK),
	DOM_ROUGH(true, "жестко", BaseColour.CRIMSON);
	
	private boolean isDom;
	private String name;
	private BaseColour colour;
	
	private SexPace(boolean isDom, String name, BaseColour colour) {
		this.isDom = isDom;
		this.name = name;
		this.colour = colour;
	}
	
	public SexPace getOppositeDomEquivalent() {
		switch(this) {
			case DOM_GENTLE:
				return SUB_NORMAL;
			case DOM_NORMAL:
				return SUB_NORMAL;
			case DOM_ROUGH:
				return SUB_EAGER;
			case SUB_EAGER:
				return DOM_ROUGH;
			case SUB_NORMAL:
				return DOM_NORMAL;
			case SUB_RESISTING:
				return DOM_GENTLE;
		}
		return SUB_NORMAL;
	}
	
	public boolean isDom() {
		return isDom;
	}

	public String getName() {
		return name;
	}

	public BaseColour getColour() {
		return colour;
	}
}
