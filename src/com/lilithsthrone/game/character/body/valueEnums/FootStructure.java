package com.lilithsthrone.game.character.body.valueEnums;

/**
 * @since 0.2.8
 * @version 0.4
 * @author Innoxia
 */
public enum FootStructure {

	/**no feet*/
	NONE("нет", "[npc.She] [npc.do] не имеет ног."),
	
	/**walk with feet flat on the ground*/
	PLANTIGRADE("плантиградное", "[npc.She] [npc.verb(walk)] со [npc.her] стопами плоско на земле."),

	/**walk on toes with the heel permanently raised*/
	DIGITIGRADE("дигитиградное", "[npc.She] [npc.verb(walk)] на [npc.her] [npc.toes], с помощью [npc.her] пяток которые постоянно подняты."),

	/**walk on hoof with the rest of the foot permanently raised*/
	UNGULIGRADE("унгулиградное", "[npc.She] [npc.verb(walk)] на [npc.her] [npc.toes], с остальной частью [npc.her] стопы, которая постоянно поднята."),

	/**have segmented legs like a spider, so foot is the 'tarsus' segment.*/
	ARACHNOID("арахнид", "[npc.She] [npc.verb(walk)] на концах [npc.her] сегментированных паучьх ног."),
	
	/**use tentacle-legs to walk around on*/
	TENTACLED("щупальцевое", "[npc.She] [npc.verb(use)] нижние части [npc.her] щупалец для передвижения.");
	
	private String name;
	private String description;

	private FootStructure(String name, String description) {
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
