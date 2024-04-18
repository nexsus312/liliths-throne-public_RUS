package com.lilithsthrone.game.settings;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.95
 * @version 0.2.3
 * @author FeiFongWong
 */
public enum ForcedFetishTendency {
	
	BOTTOM_HEAVY("Дно+", "Принудительные фетиши почти всегда добавляют дно и убирают верхние деятельности и поведения.", PresetColour.BASE_PINK_LIGHT),
	BOTTOM("Дно", "Вкусы NPC все еще имеют значение, принудительные фетиши часто добавляют дно и убирают верхние деятельности и поведения.", PresetColour.BASE_PINK),
	NEUTRAL("Нейтрально", "Принудительные фетиши будут определяться исключительно вкусами и прихотями контролирующего NPC, а также присущей вселенной случайностью.", PresetColour.ANDROGYNOUS),
	TOP ("Верх", "В то время как вкусы NPC все еще имеют значение, принудительные фетиши, как правило, добавляют верхние и убирают донные действия и поведение.", PresetColour.BASE_PURPLE_LIGHT),
	TOP_HEAVY("Верх+", "Принудительные фетиши почти всегда будут добавлять к действиям и поведению верх и убирать дно.", PresetColour.BASE_PURPLE);

	private final String name;
	private final String description;
	private final Colour colour;

	ForcedFetishTendency(String name, String description, Colour colour) {
		this.name = name;
		this.description = description;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public Colour getColour() {
		return colour;
	}

}
