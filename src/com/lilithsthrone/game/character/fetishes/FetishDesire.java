package com.lilithsthrone.game.character.fetishes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.99
 * @version 0.2.9
 * @author Innoxia
 */
public enum FetishDesire {
	/*fondness1, это названия иконок. НЕ ПЕРЕВОДИТЬ. */
	ZERO_HATE(0, "ненавидит", "ненавидит", "ненавидит", "fondness1", PresetColour.BASE_CRIMSON),
	
	ONE_DISLIKE(1, "не любит", "не любит", "не любит", "fondness2", PresetColour.BASE_RED),
	
	TWO_NEUTRAL(2, "безразлично", "безразлично к", "безразлично к", "fondness3", PresetColour.BASE_BLUE_STEEL),
	
	THREE_LIKE(3, "нравится", "нравится", "нравится", "fondness4", PresetColour.BASE_PINK_LIGHT),
	
	FOUR_LOVE(4, "любит", "любит", "любит", "fondness5", PresetColour.BASE_PINK);
	
	private int value;
	private String name;
	private String nameAsPlayerVerb;
	private String nameAsVerb;
	private String SVGImage;
	private String SVGImageDesaturated;
	private Colour colour;
	private List<String> modifiersList;
	
	private FetishDesire(int value, String name, String nameAsPlayerVerb, String nameAsVerb, String pathName, Colour colour) {
		this.value = value;
		this.name = name;
		this.nameAsPlayerVerb = nameAsPlayerVerb;
		this.nameAsVerb = nameAsVerb;
		this.colour = colour;
		
		modifiersList = new ArrayList<>();
		modifiersList.add("Модифицирует секс действия' [style.boldLust("+ Util.capitaliseSentence(Attribute.LUST.getAbbreviatedName())+ " повышая)]");

		
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/" + pathName + ".svg");
			if(is==null) {
				System.err.println("Ошибка! Иконка жажды фетиша не найдена (Попытка чтения '"+pathName+"')!");
			}
			String base = Util.inputStreamToString(is);

			SVGImage = base;
			
			SVGImage = SvgUtil.colourReplacement(this.toString(), colour, SVGImage);
			
			SVGImageDesaturated = base;
			
			SVGImageDesaturated = SvgUtil.colourReplacement(this.toString(), PresetColour.BASE_GREY, SVGImageDesaturated);

			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isNegative() {
		return this==ZERO_HATE || this==ONE_DISLIKE;
	}
	
	public boolean isPositive() {
		return this==THREE_LIKE || this==FOUR_LOVE;
	}
	
	public static int getCostToChange() {
		return 0;
	}
	
	public static FetishDesire getDesireFromValue(int value) {
		for(FetishDesire desire : FetishDesire.values()) {
			if(desire.getValue() == value) {
				return desire;
			}
		}
		
		if(value<=ZERO_HATE.getValue()) {
			return ZERO_HATE;
		} else {
			return FOUR_LOVE;
		}
	}
	
	public FetishDesire getPreviousDesire() {
		switch(this) {
			case ZERO_HATE:
				return ZERO_HATE;
			case ONE_DISLIKE:
				return ZERO_HATE;
			case TWO_NEUTRAL:
				return ONE_DISLIKE;
			case THREE_LIKE:
				return TWO_NEUTRAL;
			case FOUR_LOVE:
				return THREE_LIKE;
		}
		return TWO_NEUTRAL;
	}
	
	public FetishDesire getNextDesire() {
		switch(this) {
			case ZERO_HATE:
				return ONE_DISLIKE;
			case ONE_DISLIKE:
				return TWO_NEUTRAL;
			case TWO_NEUTRAL:
				return THREE_LIKE;
			case THREE_LIKE:
				return FOUR_LOVE;
			case FOUR_LOVE:
				return FOUR_LOVE;
		}
		return TWO_NEUTRAL;
	}
	
	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public String getNameAsPlayerVerb() {
		return nameAsPlayerVerb;
	}
	
	public String getNameAsVerb() {
		return nameAsVerb;
	}

	public String getSVGImage() {
		return SVGImage;
	}
	
	public String getSVGImageDesaturated() {
		return SVGImageDesaturated;
	}

	public Colour getColour() {
		return colour;
	}
	
	public List<String> getModifiersAsStringList() {
		return modifiersList;
	}

}
