package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.FluidInterface;
import com.lilithsthrone.game.character.effects.Addiction;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.83
 * @version 0.4.4
 * @author Innoxia
 */
public enum FluidModifier {
	
	VISCOUS(PresetColour.BASE_PURPLE_DARK,
			false,
			"вязкая",
			"Она довольно вязкая и медленно капает большими каплями, очень похожа на густую патоку.",
			"Придает этой жидкости густую консистенцию, где-то между жидким и твердым состоянием."),
	
	STICKY(PresetColour.BASE_YELLOW_LIGHT,
			false,
			"липкая",
			"Она довольно липкая и ее сложно полностью смыть без мыла.",
			"Заставляет эту жидкость прилипать ко всему, с чем она соприкасается."),
	
	SLIMY(PresetColour.BASE_BLUE_LIGHT,
			false,
			"слизистая",
			"У неё слизистая, маслянистая текстура.",
			"Придает этой жидкости слизистое ощущение."),
	
	BUBBLING(PresetColour.BASE_LILAC_LIGHT,
			false,
			"пузырится",
			"Она шипит и пузырится, как газированный напиток.",
			"Заставляет эту жидкость пузыриться, как газированный напиток."),
	
	// SPECIAL EFFECTS:

	MUSKY(PresetColour.BASE_TAN,
			true,
			"мускус",
			"У нее сильный мускусный запах.",
			"Мускусная сперма и конча вызовут 'отмеченность мускусом' у любого, кто ими покрыт во время секса."),
	
	
	MINERAL_OIL(PresetColour.BASE_BLACK,
			true,
			"минеральное масло",
			"Оно богато минералами, полезными для кожи, но не для латекса.",
			"Жидкости, пропитанные минеральным маслом, быстро разрушают презервативы, приводя к их разрыву в момент оргазма."),
	
	ALCOHOLIC(PresetColour.BASE_ORANGE,
			true,
			"крепкоалкогольная",
			"Она обладает высоким содержанием алкоголя и сильно опьянит тех, кто его употребит.",
			"Крепкоалкогольные жидкости значительно увеличат уровень опьянения у того, кто их употребляет.") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			return target.incrementAlcoholLevel(millilitres * 0.001f); //TODO factor in body size
		}
	},

	ALCOHOLIC_WEAK(PresetColour.BASE_ORANGE_LIGHT,
			true,
			"алкогольная",
			"Она имеет низкое содержание алкоголя и опьянит тех, кто его употребляет.",
			"Алкогольные жидкости увеличат уровень опьянения у того, кто их употребляет.") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			return target.incrementAlcoholLevel(millilitres * 0.0001f); //TODO factor in body size
		}
	},
	
	ADDICTIVE(PresetColour.BASE_PINK,
			true,
			"привыкающая",
			"Она сильно вызывает привыкание, и любой, кто употребляет ее слишком много, быстро станет зависимым.",
			"Привыкающие жидкости заставят любого, кто их употребляет, стать зависимым от этого конкретного типа жидкости.") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			if(target==null || fluidProvider==null) {
				return ""; // catch for if one of the characters is null, which was the case in GameCharacter.calculateGenericSexEffects
			}
			if(target.isDoll()) {
				return "";
			}
			boolean curedWithdrawal = target.getAddiction(fluid.getType())!=null && Main.game.getMinutesPassed()-target.getAddiction(fluid.getType()).getLastTimeSatisfied()>=24*60;
			boolean appendAddiction = !Main.game.isInSex() || curedWithdrawal;
			if(target.addAddiction(new Addiction(fluid.getType(), Main.game.getMinutesPassed(), fluidProvider.getId()))) {
				return UtilText.parse(target,
						"<p style='padding:0; margin:0; text-align:center;'>"
							+ "Благодаря свойствам, вызывающим привыкание "+(fluidProvider==null?"":(fluidProvider.equals(target)?"[npc.her]":UtilText.parse(fluidProvider, "[npc.namePos]")))+" "+fluid.getName(fluidProvider)
								+", [npc.name] [npc.verb(find)] [npc.herself] [style.colourArcane(craving)]"
								+ " <span style='color:"+fluid.getType().getRace().getColour().toWebHexString()+";'>"+fluid.getType().getRace().getName(fluidProvider.getBody(), fluid.isFeral(fluidProvider))+"</span> "+fluid.getName(fluidProvider)+"!"
						+ "</p>");
				
				
			} else {
				target.setLastTimeSatisfiedAddiction(fluid.getType(), Main.game.getMinutesPassed());
				if(appendAddiction) {
					return UtilText.parse(target, fluidProvider,
							"<p style='padding:0; margin:0; text-align:center;'>"
								+ "[npc.NamePos] [style.colourArcane(craving)] for <span style='color:"+fluid.getType().getRace().getColour().toWebHexString()+";'>"
									+fluid.getType().getRace().getName(fluidProvider.getBody(), fluid.isFeral(fluidProvider))
								+"</span> "+fluid.getName(fluidProvider)
									+" удовлетворе(н,на)!"
								+ (curedWithdrawal
									?" [npc.She] [npc.verb(feel)] глубоко признател(ен,ьна) "+(fluidProvider==null?"":UtilText.parse(fluidProvider, "[npc.name]"))+" за предоставление [npc.herHim] с тем, что [npc.she] нужно больше всего..."
											+ (target.isSlave()?target.incrementObedience(5):"")
									:" [npc.She] [npc.was] не страдает от синдрома отмены, но [npc.she] до сих пор [npc.verb(feel)] благодарен "
											+(fluidProvider==null?"":UtilText.parse(fluidProvider, "[npc.name]"))+" за рещение [npc.her] зависимости...")
							+ "</p>");
				}
				return "";
			}
		}
	},
	
	HALLUCINOGENIC(PresetColour.BASE_PINK_DEEP,
			true,
			"психоактивная",
			"Каждый, кто попробует это, испытывает психоактивные эффекты, которые могут проявляться в галлюцинациях, связанных с лактацией, или повышенной восприимчивости к гипнотическому внушению.",
			"Психоактивные жидкости вызовут галюциногенный трип у всех кто их принимает, вызывая искажение в их взгляде на половые органы а так же открывая их к возможности гипнотической манипуляции.") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			if(target.isDoll()) {
				return "";
			}
			target.addPsychoactiveFluidIngested(fluid.getType());
			boolean appendPsychoactive = !target.hasStatusEffect(StatusEffect.PSYCHOACTIVE);
			target.addStatusEffect(StatusEffect.PSYCHOACTIVE, 6*60*60);
			if(appendPsychoactive) {
				return UtilText.parse(target,
						"<p style='padding:0; margin:0; text-align:center;'>"
							+ "Из-за психоактивных свойств "+(fluidProvider==null?"":(fluidProvider.equals(target)?"[npc.her]":UtilText.parse(fluidProvider, "[npc.namePos]")))+" "+fluid.getName(fluidProvider)
								+", [npc.name] [npc.verb(start)] <span style='color:"+PresetColour.PSYCHOACTIVE.toWebHexString()+";'>под приходом</span>!"
						+ "</p>");
			}
			return "";
		}
	};
	
	private Colour colour;
	private boolean specialEffects;
	private String name;
	private String description;
	private String briefDescription;
	
	private FluidModifier(Colour colour, boolean specialEffects, String name, String briefDescription, String description) {
		this.colour = colour;
		this.specialEffects = specialEffects;
		this.name = name;
		this.briefDescription = briefDescription;
		this.description = description;
	}

	public Colour getColour() {
		return colour;
	}

	public boolean isSpecialEffects() {
		return specialEffects;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBriefDescription() {
		return briefDescription;
	}
	
	public String getDescription() {
		return description;
	}

	public boolean isAppliesSpecialEffects() {
		return description!=null;
	}
	
	public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
		return "";
	}
}
