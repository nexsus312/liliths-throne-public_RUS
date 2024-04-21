package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public enum EyeShape {

	ROUND("круглые"),
	HORIZONTAL("горизонтальные"),
	VERTICAL("вертикальные"),
	HEART("в форме сердца"),
	STAR("в форме звезды");
	
	private String descriptor;

	private EyeShape(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getName() {
		return descriptor;
	}
}
