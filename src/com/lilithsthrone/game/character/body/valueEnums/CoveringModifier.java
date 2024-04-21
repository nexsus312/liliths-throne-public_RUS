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
	SPARKLY("сверкает", false),
	METALLIC("металлическое", false),

	BLAZING("пыла(ет, ющая)", false),
	SHIMMERING("мерца(ет, ющая)", false),
	GLITTERING("блест(ит, ящая)", false),
	SWIRLING("клубится", false),
	
	GOOEY("липко", false) {
		@Override
		public String getName() {
			return UtilText.returnStringAtRandom(
					"липко",
					"мокро",
					"хлюпает");
		}
	},
	
	// Generic:
	SMOOTH("гладк(ая, ой)", false),
	ROUGH("жестко", false),
	
	//Skin:
	LEATHERY("кожистый", false),
	
	// Fur/hair:
	SHORT("коротк(ая, ой)", true),
	SILKEN("шелковист(ая, ой)", true),
	FLUFFY("пушист(ая, ой)", true),
	SHAGGY("лохмат(ая, ой)", true),
	FURRY("как шерсть", true), // FURRY is only used for head hair, not body-covering fur.
	COARSE("груб(ая, ой)", true);
	
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
