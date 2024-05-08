package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * These actions are not automatically added to any character's available sex actions, and must be manually added in Sex.java.
 * 
 * @since 0.4.3.2
 * @version 0.4.3.2
 * @author Innoxia
 */
public class MiscActions {
	
	public static final SexAction LEVEL_DRAIN_TOGGLE = new SexAction(
			SexActionType.MISC_NO_TURN_END,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private boolean isOppositeDom() {
			return Main.sex.isDom(Main.sex.getCharacterOrgasming())!=Main.sex.isDom(Main.game.getPlayer());
		}
		private boolean isCharacterImmune() {
			return Main.sex.getCharacterOrgasming().isImmuneToLevelDrain() || Main.game.isBadEnd(); // Do not allow level draining in bad ends
		}
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(!isOppositeDom() || isCharacterImmune()) {
				return "[style.colourDisabled(Вытягивание уровня: "
						+(Main.sex.playerLevelDrain
							?"Вкл)]"
							:"Выкл)]");
			}
			return "[style.colourSex(Вытягивание уровня:)] "
					+(Main.sex.playerLevelDrain
						?"[style.colourMinorGood(Вкл)]"
						:"[style.colourMinorBad(Выкл)]");
		}
		@Override
		public String getActionDescription() {
			return UtilText.parse(Main.sex.getCharacterOrgasming(),
					"'"+Util.capitaliseSentence(Perk.ORGASMIC_LEVEL_DRAIN.getName(Main.game.getPlayer()))+"' эффект перка:<br/>"
							+ "Переключите, будете ли вы откачивать уровень [npc.namePos] при оргазме [npc.she]."
							+(!isOppositeDom()
								?" [style.italicsMinorBad(Вы можете вытягивать только противоположных партнеров (покор./дом.)!)]"
								:" Вы можете вытягивать только противоположных партнеров (покор./дом.).")
							+(isCharacterImmune()
								?(Main.sex.getCharacterOrgasming().isImmuneToLevelDrain()
									?"<br/>[style.italicsTerrible(У [npc.Name] не может быть откачен [npc.her] уровень!)]"
									:"<br/>[style.italicsTerrible(Вы не можете вытягивать уровень во время плохого конца!)]")
								:""));
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public void applyEffects() {
			if(isOppositeDom() && !isCharacterImmune()) {
				Main.sex.playerLevelDrain = !Main.sex.playerLevelDrain;
			}
		}
	};
	
}
