package com.lilithsthrone.game.character.persona;

/**
 * @since 0.2.4
 * @version 0.2.4
 * @author Irbynx
 */
public enum MoralityValue {
	
	SURRENDER("Сдача", "Позиция избегания борьбы до победы."),
	
	VIOLENCE("Насилие", "Позиция применения чрезмерного насилия."),
	
	DISHONESTY("Нечестность", "Позиция лжи, полу-правды и различной нечистой информации."),
	
	SLAVERY("Рабство", "Позиция владения рабами.");
	
	private String name;
	private String description;
	
	private MoralityValue(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
}
