package com.lilithsthrone.game.character.fetishes;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.4.2
 * @version 0.4.2
 * @author Maxis, Innoxia
 */
public enum FetishPreference {
	
	ZERO_DISABLED(PresetColour.TEXT_GREY, "отключено", 0, 0, 0, 0, 0, "Этот фетиш не будет ни у одного NPC, за исключением особых условий."),
	ONE_HATE(PresetColour.GENERIC_BAD, "ненавижу", 1, 10, 5, 0, 0, "NPC будут только `не люблю/ненавижу` этот фетиш, за исключением особых условий."),
	TWO_DISLIKE(PresetColour.GENERIC_MINOR_BAD, "не люблю", 2, 5, 10, 3, 1, "NPC чаще будут `не люблю/ненавижу` по отношению к этому фетишу, но все еще могут появится `нравится/люблю`."),
	THREE_NEUTRAL(PresetColour.TEXT, "нейтрально", 3, 3, 3, 3, 3, "Нет предпочтений."),
	FOUR_LIKE(PresetColour.GENERIC_MINOR_GOOD, "нравится", 4, 1, 3, 10, 5, "NPC чаще будут использовать `нравится/люблю` по отношению к этому фетишу, но все еще могут появится `не люблю/ненавижу`."),
	FIVE_LOVE(PresetColour.GENERIC_GOOD, "люблю", 5, 0, 0, 3, 5, "NPC будет `нравится` или `люблю` данный фетиш."),
	SIX_ALWAYS(PresetColour.GENERIC_EXCELLENT, "всегда", 6, 0, 0, 0, 1, "NPC всегда будут иметь этот фетиш.");
	
	private Colour colour;
	private String name;
	private int value;
	private int hate;
	private int dislike;
	private int like;
	private int love;
	private String tooltip;
	
	private FetishPreference(Colour colour, String name, int value, int hate, int dislike, int like, int love, String tooltip) {
		this.colour = colour;
		this.name=name;
		this.value=value;
		this.hate=hate;
		this.dislike=dislike;
		this.like=like;
		this.love=love;
		this.tooltip=tooltip;
	}
	
	public static FetishPreference valueOf(Integer i) {
		for(FetishPreference f: FetishPreference.values()) {
			if(f.getValue() == i) {
				return f;
			}
		}
		return null;
	}
	
	public Colour getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getHate() {
		return hate;
	}
	
	public int getDislike() {
		return dislike;
	}
	
	public int getLike() {
		return like;
	}
	
	public int getLove() {
		return love;
	}

	public String getTooltip() {
		return tooltip;
	}
}
