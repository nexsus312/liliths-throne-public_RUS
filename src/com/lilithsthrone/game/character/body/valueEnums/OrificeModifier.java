package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.83
 * @version 0.3.7.2
 * @author Innoxia
 */
public enum OrificeModifier {
	
	PUFFY("пухл(ая,ый)", ""),
	
	RIBBED("ребрист(ая,ый)", ""),
	
	TENTACLED("щупальцев(ая,ый)", ""),
	
	MUSCLE_CONTROL("мускулист(ая,ый)", "Предотвращает становление этого отверстия 'слишком свободным', маленький размер вставляемого объекта не имеет значение, как и вместимость отверстия.");
	
	
	private String name;
	private String description;

	private OrificeModifier(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}
	
	public boolean isSpecialEffects() {
		return !description.isEmpty();
	}
	
	public String getDescription() {
		if(description.isEmpty()) {
			return "Не влияет на геймлпей";
		}
		return description;
	}
}
