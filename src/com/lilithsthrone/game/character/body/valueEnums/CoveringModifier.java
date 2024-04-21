package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.99
 * @version 0.4
 * @author Innoxia
 */
public enum CoveringModifier {

	EYE("глаз", false),
	FLUID("жидкость", false),
	MAKEUP("макияж", false),
	GLOSSY("глянцевая", false),
	MATTE("матовая", false),
	SPARKLY("сверкающая", false),
	METALLIC("металлическая", false),

	BLAZING("пыла(ет, ющая)", false),
	SHIMMERING("мерцающая", false),
	GLITTERING("блестящая", false),
	SWIRLING("клубится", false),
	
	GOOEY("липко", false) {
		@Override
		public String getName() {
			return UtilText.returnStringAtRandom(
					"липкая",
					"мокрая",
					"хлюпающая");
		}
	},
	
	// Generic:
	SMOOTH("гладкая", false),
	ROUGH("жесткая", false),
	
	//Skin:
	LEATHERY("кожистая", false),
	
	// Fur/hair:
	SHORT("короткая", true),
	SILKEN("шелковистая", true),
	FLUFFY("пушистая", true),
	SHAGGY("лохматая", true),
	FURRY("как шерсть", true), // FURRY is only used for head hair, not body-covering fur.
	COARSE("грубая", true);
	
	private String descriptor;
	private boolean furryModifier;

	private CoveringModifier(String descriptor, boolean furryModifier) {
		this.descriptor = descriptor;
		this.furryModifier = furryModifier;
	}

	public String getName() {
		return descriptor;
	}
	
	/**
	 * @return true if this is a modifier which is typically assigned to furry or hairy coverings.
	 */
	public boolean isFurryModifier() {
		return furryModifier;
	}
}
