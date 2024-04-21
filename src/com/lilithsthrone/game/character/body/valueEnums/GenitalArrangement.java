package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.1.84
 * @version 0.4
 * @author Innoxia
 */
public enum GenitalArrangement {
	
	NORMAL("нормально", "[npc.NameHasFull] наружные гениталии, отдельно от [npc.her] ануса."),
	
	CLOACA("клоака", "[npc.NamePos] гениталии и ануса расположены в щелевидной клоаке, которая располжена там где [npc.her] гениталии должны были быть."),
	
	CLOACA_BEHIND("задняя клоака", "[npc.NamePos]гениталии и ануса расположены в щелевидной клоаке, которая располжена там где [npc.her] анус должен был быть.");
	
	
	private String name;
	private String description;

	private GenitalArrangement(String name, String description) {
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
