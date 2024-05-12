package com.lilithsthrone.game.combat;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.3.4.5
 * @version 0.3.4.5
 * @author Innoxia
 */
public enum CombatBehaviour {
	
	BALANCED("сбалансировано",
			"Попросите [npc.name] использовать любые боевые приемы, которые [npc.she] считает наиболее подходящими.",
			"[npc.NameIsFull], использует любой боевой прием, который [npc.she] сочтет наиболее подходящим."),
	
	ATTACK("атака",
			"Попросите [npc.name] быть агрессивнее и предпочитать атаки основным оружием и атакующие заклинания вместо защитных способностей.",
			"По возможности [npc.name] будет переходить в наступление, предпочитая использовать разрушительные атаки, а не защитные способности."),
	
	DEFEND("защита",
			"Попросите [npc.name] защищаться и отдавать предпочтение способностям, предотвращающим входящий урон.",
			"По возможности [npc.name] предпочитает обороняться и использовать способности, предотвращающие входящий урон."),
	
	SEDUCE("соблазнение",
			"Попросите [npc.name] начать соблазнять [npc.her] противников вместо того, чтобы использовать повреждающие заклинания или атаки оружием.",
			"По возможности [npc.name] сосредоточится на соблазнении [npc.her] противников, а не на использовании повреждающих или защитных способностей."),
	
	SPELLS("заклинания",
			"Попросите [npc.name] сосредоточиться на использовании всех заклинаний, которые [npc.sheHasFull] имеет в [npc.her] распоряжении.",
			"По возможности [npc.name] будет использовать заклинания, которые [npc.sheHasFull] в [npc.her] распоряжении."),
	
	SUPPORT("поддержка",
			"Попросите [npc.name] сосредоточиться на использовании заклинаний или других способностей, поддерживающих [npc.her] союзников.",
			"По возможности [npc.name] будет использовать заклинания или другие способности, поддерживающие [npc.her] союзников.");

	String name;
	String orderDescription;
	String description;
	
	private CombatBehaviour(String name, String orderDescription, String description) {
		this.name = name;
		this.orderDescription = orderDescription;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getOrderDescription(GameCharacter character) {
		return UtilText.parse(character, orderDescription);
	}

	public String getDescription(GameCharacter character) {
		return UtilText.parse(character, description);
	}

}
