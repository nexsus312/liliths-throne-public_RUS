package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.84
 * @version 0.3.7
 * @author Innoxia
 */
public enum PiercingType {
	
	EAR("ухо",
			"Уши - самая распространенная область тела, которую прокалывают, что позволяет использовать серьги и другие украшения, связанные с ушами."),
	
	NOSE("нос",
			"Пирсинг носа позволяет носить украшения, такие как кольца или шпильки."),
	
	LIP("губа",
			"Пирсинг губ позволяет носить кольца для губ."),
	
	TONGUE("язык",
			"Пирсинг языка позволит вам оснастить язык прямыми барбеллами"),
	
	NAVEL("пупок",
			"Прокалывание пупка позволит вам носить украшения, связанные с пупком."),
	
	NIPPLE("сосок",
			"Пирсинг сосков позволит вам оснастить соски барбеллами."),
	
	VAGINA("вагина",
			"Прокалывание вагины позволит вам носить украшения, связанные с вагиной."),
	
	PENIS("пенис",
			"Прокалывание пениса позволит вам носить украшения, связанные с пенисом.");
	
	private String name;
	private String description;

	private PiercingType(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
