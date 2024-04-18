package com.lilithsthrone.game.settings;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.95
 * @version 0.3.5.5
 * @author FeiFongWong, Innoxia
 */
public enum ForcedTFTendency {
	
	FEMININE_HEAVY(true, false, "Женшина+", "Велика вероятность того, что принудительные превращения сделают вас более женственной, независимо от вкусов NPC.", PresetColour.FEMININE_PLUS),
	
	FEMININE(true, false, "Женщина", "Хотя вкусы NPC по-прежнему имеют значение, принудительные трансформации часто делают вас более женственной.", PresetColour.FEMININE),
	
	NEUTRAL(false, false, "Нейтрально", "Гендерные эффекты принудительных трансформаций будут определяться исключительно вкусами и прихотями управляющего NPC, а также присущей вселенной случайностью.", PresetColour.ANDROGYNOUS),
	
	MASCULINE(false, true, "Мужчина", "Хотя вкусы NPC все еще имеют значение, принудительные трансформации часто делают вас более мужественным.", PresetColour.MASCULINE),
	
	MASCULINE_HEAVY(false, true, "Мужчина+", "Существует большая вероятность того, что принудительное превращение сделает вас более мужественным, независимо от вкусов NPC.", PresetColour.MASCULINE_PLUS);
	
	
	private final String name;
	private final String description;
	private final boolean feminine;
	private final boolean masculine;
	private final Colour colour;
	
	ForcedTFTendency(boolean feminine, boolean masculine, String name, String description, Colour colour) {
		this.name = name;
		this.description = description;
		this.feminine = feminine;
		this.masculine = masculine;
		this.colour = colour;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean isFeminine() {
		return feminine;
	}
	
	public boolean isMasculine() {
		return masculine;
	}
	
	public Colour getColour() {
		return colour;
	}
}
