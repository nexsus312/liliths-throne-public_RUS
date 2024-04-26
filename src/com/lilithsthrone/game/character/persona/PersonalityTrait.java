package com.lilithsthrone.game.character.persona;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/***
 * @since 0.2.4
 * @version 0.4.9
 * @author Innoxia
 */
public enum PersonalityTrait {
	
	// Core traits:
	
	CONFIDENT(false, PersonalityCategory.CORE, "уверенность", "[npc.NameIsFull] имеет очень напористый и уверенный в себе характер.", "", PresetColour.BASE_GREEN_LIME) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(SHY);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(CONFIDENT)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже уверен в себе, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorGood(чувствует себя намного увереннее)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(CONFIDENT)
								?"[style.colourDisabled([npc.Name] уже не хватает уверенности, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorBad(чувствует себя очень неуверенно)]!")
						+ "</p>");
		}
	},
	
	SHY(false, PersonalityCategory.CORE, "застенчивость", "[npc.NameIsFull] Имеет застенчивый характер, в окружении других людей и по возможности будет избегать разговоров.", "", PresetColour.BASE_YELLOW_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(CONFIDENT);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(SHY)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже стесняется, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorGood(чувствует себя намного застенчивее)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(SHY)
								?"[style.colourDisabled([npc.Name] уже не страдает от застенчивости, поэтому ничего не происходит....)]"
								:"[npc.Name] [style.colourMinorBad(теряет свою застенчивость)]!")
						+ "</p>");
		}
	},

	KIND(false, PersonalityCategory.CORE, "доброта", "[npc.Name] всегда пытается проявлять доброту к людям, иногда даже жертвуя своим счастьем.", "", PresetColour.BASE_GREEN) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(SELFISH);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(KIND)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже добрый, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorGood(чувствует себя намного добрее)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(KIND)
								?"[style.colourDisabled([npc.Name] уже не очень добрый, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorBad(теряет свою доброту)]!")
						+ "</p>");
		}
	},
	
	SELFISH(false, PersonalityCategory.CORE, "эгоизм", "[npc.Name] всегда ставит себя на первое место, и не будет делать то что не приведет к личной прибыли.", "", PresetColour.BASE_RED) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(KIND);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(SELFISH)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже эгоист, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorGood(чувствует себя гораздо более эгоистичнее)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(SELFISH)
								?"[style.colourDisabled([npc.Name]  уже не эгоистичен, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorBad(теряет свою эгоистичность)]!")
						+ "</p>");
		}
	},

	NAIVE(false, PersonalityCategory.CORE, "наивность", "Нехватка жизненного опыта и воли, [npc.name] абсолютно не понимает жестокость реальности.", "", PresetColour.BASE_PINK_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(CYNICAL);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(NAIVE)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже наив(ен,на), поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorGood(чувствует себя гораздо более наивнее)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(NAIVE)
								?"[style.colourDisabled([npc.Name] уже не наивен, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorBad(теряет свою наивность)]!")
						+ "</p>");
		}
	},
	
	CYNICAL(false, PersonalityCategory.CORE, "циничность", "[npc.NameIsFull] особенно не доверяет намерениям и мотивам других людей.", "", PresetColour.BASE_RED_DARK) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(NAIVE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(CYNICAL)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже циничен, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorGood(чувствует себя гораздо более циничнее)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(CYNICAL)
								?"[style.colourDisabled([npc.Name] уже не циничен, поэтому ничего не происходит....)]"
								:"[npc.Name] [style.colourMinorBad(теряет свой цинизм)]!")
						+ "</p>");
		}
	},
	
	// Combat traits:
	
	BRAVE(false, PersonalityCategory.COMBAT, "храбрость", "[npc.Name] всегда действует в отважной манере, и никогда не боится боя.", "", PresetColour.BASE_ORANGE) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(COWARDLY);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(BRAVE)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже храбрый, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorGood(чувствует себя намного храбрее)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(BRAVE)
								?"[style.colourDisabled([npc.Name] уже не имеет храбрости, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorBad(теряет свою храбрость)]!")
						+ "</p>");
		}
	},
	
	COWARDLY(false, PersonalityCategory.COMBAT, "трусость", "[npc.Name] легко пугается и предпочитает избегать кофликтов.", "", PresetColour.BASE_RED_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(BRAVE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(COWARDLY)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже труслив, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorGood(чувствует себя гораздо трусливее)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(COWARDLY)
								?"[style.colourDisabled([npc.Name] уже не труслив, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorBad(теряет свою трусость)]!")
						+ "</p>");
		}
	},

	// Sex traits:
	
	LEWD(false,
			PersonalityCategory.SEX,
			"развратность",
			"[npc.NameHasFull] имеет глубокие познание о сексе и никогда не откажется от эротических разговоров.",
			"", PresetColour.BASE_PINK) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(PRUDE, INNOCENT);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(LEWD)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже развратен, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorGood(чувствует себя еще более развратно)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(LEWD)
								?"[style.colourDisabled([npc.Name] уже не развратен, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorBad(теряет свою развратность)]!")
						+ "</p>");
		}
	},
	
	INNOCENT(false,
			PersonalityCategory.SEX,
			"невинность",
			"[npc.Name] всегда смущается при упоминании вещей связанных с сексом.",
			"", PresetColour.BASE_BLUE_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LEWD, PRUDE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name] [npc.isFull] уже невинен, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorGood(чувствует себя гораздо невиннее)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name] уже не невинен, поэтому ничего не происходит...)]"
								:"[npc.Name] [style.colourMinorBad(теряет свою невинность)]!")
						+ "</p>");
		}
	},
	
	PRUDE(false,
			PersonalityCategory.SEX,
			"prude",
			"[npc.Name] [npc.do] not like talking about sexual matters, and [npc.verb(refuse)] to even acknowledge that [npc.she] [npc.verb(know)] anything about such things.",
			"", PresetColour.BASE_BLUE_STEEL) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LEWD, INNOCENT);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name] [npc.isFull] already prude, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(feeling a lot more prude)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(INNOCENT)
								?"[style.colourDisabled([npc.Name] already isn't prude, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(losing [npc.her] prudish mannerisms)]!")
						+ "</p>");
		}
	},

	// Speech traits:
	
	LISP(false,
			PersonalityCategory.SPEECH,
			"lisp",
			"[npc.Name] [npc.verb(speak)] with a lisp, pronouncing 's' and 'z' as 'th'.",
			"[style.italicsBad(All of [npc.namePos] in-game speech will be affected by this lisp!)]", PresetColour.BASE_PURPLE_LIGHT) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(LISP)
								?"[style.colourDisabled([npc.Name] already speaks with a lisp, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(speaking with a lisp)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(LISP)
								?"[style.colourDisabled([npc.Name] already [npc.do]n't speak with a lisp, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(able to speak without a lisp)]!")
						+ "</p>");
		}
	},

	STUTTER(false,
			PersonalityCategory.SPEECH,
			"stutter",
			"[npc.NameHasFull] a habit of stuttering and stumbling over [npc.her] words as [npc.she] [npc.verb(speak)].",
			"[style.italicsBad(All of [npc.namePos] in-game speech will be affected by this stutter!)]", PresetColour.BASE_PINK_SALMON) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.Name] already speaks with a stutter, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(speaking with a stutter)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.Name] already [npc.do]n't speak with a stutter, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(able to speak without a stutter)]!")
						+ "</p>");
		}
	},

	MUTE(true,
			PersonalityCategory.SPEECH,
			"mute",
			"[npc.NameIsFull] a mute, and while [npc.she] can make some lewd noises when in the grips of passion, [npc.sheIsFull] otherwise completely unable to speak.",
			"[style.italicsBad(All of [npc.namePos] in-game speech will be removed!)]", PresetColour.BASE_CRIMSON) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(LISP, STUTTER, SLOVENLY);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.NameIsFull] already mute, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(unable to talk)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(STUTTER)
								?"[style.colourDisabled([npc.NameIsFull] already able to speak, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(able to talk)]!")
						+ "</p>");
		}
	},

	SLOVENLY(false,
			PersonalityCategory.SPEECH,
			"slovenly",
			"[npc.Name] [npc.verb(speak)] in a very slovenly manner; dropping syllables and with poor pronunciation, [npc.her] speech can often be very hard to understand.",
			"[style.italicsBad(All of [npc.namePos] in-game speech will be affected by this!)]", PresetColour.BASE_BROWN) {
		@Override
		public List<PersonalityTrait> getMutuallyExclusiveSettings() {
			return Util.newArrayListOfValues(MUTE);
		}
		@Override
		public String getAdditionDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (target.hasPersonalityTrait(SLOVENLY)
								?"[style.colourDisabled([npc.Name] already speaks in a slovenly manner, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorBad(speaking in a slovenly manner)]!")
						+ "</p>");
		}
		@Override
		public String getRemovalDescription(GameCharacter target) {
			return UtilText.parse(target,
					"<p style='text-align:center;'>"
							+ (!target.hasPersonalityTrait(SLOVENLY)
								?"[style.colourDisabled([npc.Name] already [npc.do]n't speak in a slovenly manner, so nothing happens...)]"
								:"[npc.Name] [npc.verb(find)] [npc.herself] [style.colourMinorGood(no longer speaking in a slovenly manner)]!")
						+ "</p>");
		}
	},;
	
	private boolean specialRequirements;
	private PersonalityCategory personalityCategory;
	private String name;
	private String description;
	private String gameplayInformation;
	private Colour colour;
	
	private PersonalityTrait(boolean specialRequirements, PersonalityCategory personalityCategory, String name, String description, String gameplayInformation, Colour colour) {
		this.specialRequirements = specialRequirements;
		this.personalityCategory = personalityCategory;
		this.name = name;
		this.description = description;
		this.gameplayInformation = gameplayInformation;
		this.colour = colour;
	}
	
	public PersonalityCategory getPersonalityCategory() {
		return personalityCategory;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription(GameCharacter character, boolean withGameplayInformation, boolean withMutuallyExclusiveInformation) {
		StringBuilder sb = new StringBuilder();

		sb.append(UtilText.parse(character, description));
		
		if(withGameplayInformation) {
			if(gameplayInformation!=null && !gameplayInformation.isEmpty()) {
				sb.append("<br/>"+UtilText.parse(character, gameplayInformation));
			} else {
				sb.append("<br/>[style.italicsDisabled(No current in-game effects...)]");
			}
		}
		
		if(withMutuallyExclusiveInformation) {
			if(!this.getMutuallyExclusiveSettings().isEmpty()) {
				sb.append("<br/>[style.colourBad(Mutually exclusive)] with ");
				List<String> names = new ArrayList<>();
				for(PersonalityTrait trait : this.getMutuallyExclusiveSettings()) {
					names.add("<span style='color:"+trait.getColour().toWebHexString()+";'>"+trait.getName()+"</span>");
				}
				sb.append(Util.stringsToStringList(names, false)+"!");
			}
		}
		
		return sb.toString();
	}
	
	public Colour getColour() {
		return colour;
	}

	public abstract List<PersonalityTrait> getMutuallyExclusiveSettings();

	public abstract String getAdditionDescription(GameCharacter target);
	
	public abstract String getRemovalDescription(GameCharacter target);
	
	public boolean isSpecialRequirements() {
		return specialRequirements;
	}
}
