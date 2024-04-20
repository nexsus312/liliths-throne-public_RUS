package com.lilithsthrone.game.character.attributes;

import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.65
 * @version 0.3.8.2
 * @author Innoxia
 */
public enum PhysiqueLevel {
	
	ZERO_WEAK("Хрупкий/Хрупкая", 0, 10, PresetColour.PHYSIQUE_STAGE_ZERO) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.PHYSIQUE_PERK_0;
		}
	},
	
	ONE_AVERAGE("Среднее", 10, 30, PresetColour.PHYSIQUE_STAGE_ONE) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.PHYSIQUE_PERK_1;
		}
	},
	
	TWO_STRONG("Спортивное", 30, 50, PresetColour.PHYSIQUE_STAGE_TWO) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.PHYSIQUE_PERK_2;
		}
	},
	
	THREE_POWERFUL("Сильное", 50, 70, PresetColour.PHYSIQUE_STAGE_THREE) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.PHYSIQUE_PERK_3;
		}
	},
	
	FOUR_MIGHTY("Могущественное", 70, 90, PresetColour.PHYSIQUE_STAGE_FOUR) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.PHYSIQUE_PERK_4;
		}
	},
	
	FIVE_HERCULEAN("Геркулесово", 90, 100, PresetColour.PHYSIQUE_STAGE_FIVE) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.PHYSIQUE_PERK_5;
		}
	};
	
	private String name;
	private int minimumValue, maximumValue;
	private Colour colour;

	private PhysiqueLevel(String name, int minimumValue, int maximumValue, Colour colour) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.colour = colour;
	}
	
	public abstract AbstractStatusEffect getRelatedStatusEffect();

	public String getName() {
		return name;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}

	public Colour getColour() {
		return colour;
	}
	
	public static PhysiqueLevel getPhysiqueLevelFromValue(float value){
		for(PhysiqueLevel al : PhysiqueLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue())
				return al;
		}
		return FIVE_HERCULEAN;
	}
}
