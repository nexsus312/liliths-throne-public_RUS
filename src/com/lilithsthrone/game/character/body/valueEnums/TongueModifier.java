package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.4
 * @author Innoxia
 */
public enum TongueModifier {
	
	RIBBED("ребристый"),
	
	TENTACLED("щупальцевый"),
	
	BIFURCATED("раздвоенный"),
	
	WIDE("широкий"),
	
	FLAT("плоский"),
	
	STRONG("сильный"),
	
	TAPERED("сужающийся");
	
	private String descriptor;

	private TongueModifier(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getName() {
		return descriptor;
	}
}
