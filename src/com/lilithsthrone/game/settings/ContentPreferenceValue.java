package com.lilithsthrone.game.settings;

/**
 * @since 0.1.78
 * @version 0.2.11
 * @author Innoxia
 */
public enum ContentPreferenceValue {

	ZERO_NONE("Без", 0),
	
	ONE_MINIMAL("Минимум", 1),
	
	TWO_LOW("Мало", 5),
	
	THREE_AVERAGE("Средне", 10),
	
	FOUR_HIGH("Много", 20),
	
	FIVE_ABUNDANT("Очень Много", 40);

	private String name;
	private int value;
	
	private ContentPreferenceValue(String name, int value) {
		this.name= name;
		this.value=value;
	}
	
	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
