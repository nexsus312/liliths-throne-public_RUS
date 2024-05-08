package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.97
 * @version 0.4.1
 * @author Innoxia
 */
public enum OrgasmCumTarget {

	// Specials:
	LILAYA_PANTIES("в трусики Лилайи", false),
	
	WALL("на стену", false) {
		@Override
		public String getName() {
			return UtilText.parse("на [pc.wall]");
		}
	},
	FLOOR("на пол", false),
	
	INSIDE("внутрь", true),
	INSIDE_SWITCH_DOUBLE("внутрь (двойной)", true),
	
	ARMPITS("на подмышку", true),
	ASS("на задницу", true),
	GROIN("на промежность", true),
	BREASTS("на грудь", true),
	FACE("на лицо", true),
	HAIR("на волосы", true),
	STOMACH("на живот", true),
	LEGS("на ноги", true),
	FEET("на ступни", true),
	BACK("на спину", true),
	
	SELF_GROIN("на свою промежность", false),
	SELF_STOMACH("на свой живот", false),
	SELF_LEGS("на свои ноги", false),
	SELF_FEET("на свои ступни", false),
	SELF_BREASTS("на свои груди", false),
	SELF_HANDS("на свои руки", false),
	SELF_FACE("на свое лицо", false);
	
	private String name;
	private boolean requiresPartner;

	private OrgasmCumTarget(String name, boolean requiresPartner) {
		this.name = name;
		this.requiresPartner = requiresPartner;
	}

	public String getName() {
		return name;
	}

	public boolean isRequiresPartner() {
		return requiresPartner;
	}
	
}
