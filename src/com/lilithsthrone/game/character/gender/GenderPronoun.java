package com.lilithsthrone.game.character.gender;

/**
 * @since 0.1.67
 * @version 0.1.86
 * @author Innoxia
 */
public enum GenderPronoun {

	NOUN("Существительное", "Женщина", "Мужчина", "Человек"),
	YOUNG_NOUN("Молодое Существительное", "Девочка", "Мальчик", "Человек"),
	
	SECOND_PERSON("Второе лицо", "она", "он", "они"),
	THIRD_PERSON("Третье лицо", "ее", "его", "их"),
	POSSESSIVE_BEFORE_NOUN("Местоимение перед существительным", "ее", "его", "их"),
	POSSESSIVE_ALONE("Нетральная принадлежность", "ее", "его", "их");
	
	private String name, feminine, masculine, neutral;
	
	private GenderPronoun(String name, String feminine, String masculine, String neutral){
		this.name = name;
		this.feminine = feminine;
		this.masculine = masculine;
	}

	public String getName() {
		return name;
	}
	
	public String getFeminine() {
		return feminine;
	}

	public String getMasculine() {
		return masculine;
	}

	public String getNeutral() {
		return neutral;
	}
}
