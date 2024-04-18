package com.lilithsthrone.game.settings;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.95
 * @version 0.1.95
 * @author Innoxia
 */
public enum DifficultyLevel {
	
	NORMAL("Человек", "Так как подразумевает игра. Уровень врагов и урон не зависит от уровня игрока.", PresetColour.RACE_HUMAN, false, 1, 1),

	LEVEL_SCALING("Мутант", "Уровень врагов зависит от вашего, но урон не меняется.", PresetColour.RACE_CAT_MORPH, true, 1, 1),
	
	HARD("Демон", "Уровень врагов зависит от вашего и они наносят 200% урона.", PresetColour.RACE_DEMON, true, 2, 1),
	
	NIGHTMARE("Лилин", "Уровень врагов зависит от вашего. Они наносят 200% урона и получают только 50% урона из всех источников.", PresetColour.BASE_PURPLE, true, 2, 0.5f),
	
	HELL("Лилит", "Уровень врагов всегда в два раза выше вашего, они наносят 400% урона и получают только 25% урона из всех источников. Готовьтесь к поражениями. Их будет очень много.", PresetColour.BASE_CRIMSON, true, 4, 0.25f);

	private String name;
	private String description;
	private Colour colour;
	private boolean isNPCLevelScaling;
	private float damageModifierNPC;
	private float damageModifierPlayer;
	
	private DifficultyLevel(String name, String description, Colour colour, boolean isNPCLevelScaling, float damageModifierNPC, float damageModifierPlayer) {
		this.name = name;
		this.description = description;
		this.colour = colour;
		this.isNPCLevelScaling = isNPCLevelScaling;
		this.damageModifierNPC = damageModifierNPC;
		this.damageModifierPlayer = damageModifierPlayer;
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

	public boolean isNPCLevelScaling() {
		return isNPCLevelScaling;
	}

	public float getDamageModifierNPC() {
		return damageModifierNPC;
	}

	public float getDamageModifierPlayer() {
		return damageModifierPlayer;
	}
}
