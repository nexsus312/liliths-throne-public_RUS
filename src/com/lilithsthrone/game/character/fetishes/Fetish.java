package com.lilithsthrone.game.character.fetishes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.?
 * @version 0.4.2
 * @author Innoxia, Maxis
 */
public class Fetish {
	
	// FETISHES:

	// Sex types:
	
	public static AbstractFetish FETISH_ANAL_GIVING = new AbstractFetish(60,
			"анал",
			"исполние анала",
			"фетиш_анал_давать",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>анальное дразнение</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению жопастых-шлюх</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека выполнять секс действия связанные с задницей их партнеров.";
				
			} else if(owner.isPlayer()) {
				return "Вы абсолютно любите заниматся анальным сексом, используя задницу вашего партнера вы заявляете о том что она ваша собственность, все это сводит вас с ума от возбуждения!";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на занятие анальным сексом.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "исполняет анальные секс действия");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "анальное дразнение");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isAnalContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ANAL_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_ANAL_RECEIVING = new AbstractFetish(60,
			"жопастая-шлюха",
			"получение анала",
			"фетиш_анал_получение",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение жопастой-шлюхи</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>анальному дразнению</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека получать анальные секс действия по отношению к себе.";
				
			} else if(owner.isPlayer()) {
				return "Вы абсолютно люите получать анальный секс. Мысли о том что вашу задницу долбят как дешевую шлюху сводят вас с ума от похоти! ";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на получение анального секса.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "получение любого анального внимания");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение жопастой-шлюхи");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}

		@Override
		public boolean isContentEnabled() { return Main.game.isAnalContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ANAL_GIVING; }
	};
	
	public static AbstractFetish FETISH_VAGINAL_GIVING = new AbstractFetish(60,
			"вагинальный",
			"исполнение вагинального",
			"фетиш_вагинальный_заниматся",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение восхвалением киски</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению киска-шлюх</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека заниматься секс действиями связанными с киской партнеров.";
				
			} else if(owner.isPlayer()) {
				return "Не смотря на то что это один из самых стандартных сексуальных актов, ваша любовь к вагинальному сексу превратилась в полную одержимость.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет экстремальную одержимость к занятию вагинальным сексом.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "исполнять вагинальные секс действия");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение восхвалением киски");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_VAGINAL_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_VAGINAL_RECEIVING = new AbstractFetish(60,
			"киска-шлюха",
			"получение вагинального",
			"фетиш_вагинальный_получение",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение киско-шлюхи</span> (Requires vagina)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению восхвалением киски</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию киски человека быть использованной.";
				
			} else if(owner.isPlayer()) {
				return "Не смотря на то что это один из самых стандартных сексуальных актов, ваша киска желает быть использованной всеми возможными способами, это превратилось в полную одержимость.";
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет экстремальную одержимость к получению вагинального секса.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "получение любого вагинального внимания");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение киско-шлюхи");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_VAGINAL_GIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_ORAL_RECEIVING = new AbstractFetish(60,
			"орал",
			"получение орала",
			"фетиш_орал_получение",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>оральное дразнение</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению орального-исполнителя</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится с желанию человека получать оральные секс действия.";
				
			} else if(owner.isPlayer()) {
				return "Вы абсолютно любите получать оральный секс. Ведя голову своего партнера вниз к вашей промежности, вы всегда с нетерпением ждете чьих то губ и языка, доводящих вас до оргазма.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на получение орального секса.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "получение орального секса");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "оральное дразнение");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ORAL_GIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_ORAL_GIVING = new AbstractFetish(60,
			"оральный исполнитель",
			"исполнять орал",
			"фетиш_орал_исполнять",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение орального-исполнителя</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>оральному дразнению</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к жажде человека исполнять оральный секс на своих партнерах.";
				
			} else if(owner.isPlayer()) {
				return "Вы абсолютно любите исполнять оральный секс. Двигаться вниз, между ног машего партнера, ваше любимое занятие, вы всегда с нетерпением жаждите использовать свой рот чтобы довести партнера до оргазма!";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш к исполнению орального секса.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "исполняет оральный секс");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение орального-исполнителя");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ORAL_RECEIVING; }
	};
	
	public static AbstractFetish FETISH_BREASTS_OTHERS = new AbstractFetish(60,
			"любовь к грудям",
			"чужие груди",
			"фетиш_груди_другие",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение любителя-грудей</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение грудями</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека использовать грудь партнера.";
				
			} else if(owner.isPlayer()) {
				return "У вас одержимость грудями. Не важно большие или маленькие, если у кого-то есть пара (или больше) сисек, вы найдете способ их использовать.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш любви к чужим грудям.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "играть с чужой грудью");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение любителя-грудей");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_BREASTS_SELF; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_BREASTS_SELF = new AbstractFetish(60,
			"груди",
			"игра со своей грудью",
			"фетиш_груди_свои",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение грудью</span> (Requires breasts)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению любителя-груди</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека чтобы их грудь была использована другими.";
				
			} else if(owner.isPlayer()) {
				return "Вы одержими своей грудью. Вам не нужно ничего кроме использования своих грудей для удовлетворения партнеров, разве что показывать их своим множественным поклонникам.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на использование [npc.her] груди.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "вашей [npc.breasts] касаются и ласкают");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение грудями");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_BREASTS_OTHERS; }
	};
	
	public static AbstractFetish FETISH_LACTATION_OTHERS = new AbstractFetish(60,
			"любитель молока",
			"кормиться грудью",
			"фетиш_лактация_другие",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение любителя-молока</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению лактацией</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека чтобы у их партнеров была лактация.";
				
			} else if(owner.isPlayer()) {
				return "У вас одержимость кормлением грудью. Вас мало что привлекает, кроме сосания чьих то заполненых грудей и мечтах о молочных сосках.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на грудное молоко.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "кормится грудью");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение любителя-молока");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isLactationContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_LACTATION_SELF; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_LACTATION_SELF = new AbstractFetish(60,
			"лактация",
			"лактация",
			"фетиш_лактация_своя",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение лактацией</span> (Requires breasts)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению любителей-молока</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека иметь кормящие груди.";
				
			} else if(owner.isPlayer()) {
				return "Вы одержимы лактацией. Вас мало что привлекает, кроме заполненности вашей груди и приятного чувства того как вас доят.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на обладание кормящими грудями.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "вашу [npc.breasts] доят");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение лактацией");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isLactationContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_LACTATION_OTHERS; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_LEG_LOVER = new AbstractFetish(60,
			"любитель ног",
			"ноги партнера",
			"фетиш_ноги_любитель",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение любителя-ног</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразению красующихся-ногами</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека к исполнению секс действий по отношению к ногам партнера.";
				
			} else if(owner.isPlayer()) {
				return "Вы абсолютно любите ноги. Использование ног или бедер партнера для секса доствляет вам максимальное возбуждение.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на использование чужих ног и бедер.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "чужие' ноги");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение любителя-ног");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_STRUTTER; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_STRUTTER = new AbstractFetish(60,
			"красующийся ногами",
			"иметь используемые ноги",
			"фетиш_ноги",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение красования-ногами</span> (Requires legs)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению любителя-ног</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека использовать свои ноги в сексульной манере.";
				
			} else if(owner.isPlayer()) {
				return "Вы абсолютно любите красоваться вашими ногами. Использование ног или бедер во время секса невероятно заводит вас.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на использование [npc.her] ног или бедер во время секса.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "использовать бедра или ноги во время секса");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение красующегося ногами");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_LEG_LOVER; }
	};
	
	public static AbstractFetish FETISH_FOOT_GIVING = new AbstractFetish(60,
			"доминирующая стопа",
			"использование стопы",
			"фетиш_стопа_использовать",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение доминирующей-стопы</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению покорной-стопы</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека использованию своих стоп во время секса.";
				
			} else if(owner.isPlayer()) {
				return "Вы абсолютно любите использовать свои стопы во время секса. Восхваление и сексуальные акты по отношению к вашим ступням и пальцам, заводят вас как ничто другое.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на использование [npc.her] ступней во время секса.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "использовать свои ступни во время секса");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение доминирующей-стопы");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isFootContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_FOOT_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_FOOT_RECEIVING = new AbstractFetish(60,
			"покорная стопа",
			"использование стопы партнера",
			"фетиш_стопа_использование",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение покорной-стопы</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению доминирующей-стопы</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека использовать стопы партнера во время секса.";
				
			} else if(owner.isPlayer()) {
				return "Вас восхищают стопы. Когда ваш партнер использует стопы и пальцы во время секса, это невероятно заводит вас.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на использование чужих стоп.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "чужие' стопы");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение покорной-стопы");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isFootContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_FOOT_GIVING; }
	};

	public static AbstractFetish FETISH_ARMPIT_GIVING = new AbstractFetish(60,
			"любитель подмышек",
			"использовать подмышки",
			"фетиш_подмышки_использовать",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение восхвалением подмыщек</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению подмышко-шлюх</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека выполнять секс действия по отношению к подмышкам партнера.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(love)] ничего больше удовлетворения [npc.her] подмышек партнера, и даже предпочитает подмышки для использования в проникающих секс действиях.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "выполнять секс действия с подмышками");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение восхвалением подмышек");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isArmpitContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ARMPIT_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_ARMPIT_RECEIVING = new AbstractFetish(60,
			"подмышко-шлюха",
			"использование своих подмышек",
			"фетиш_аподмышка_получение",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение подмышко-шлюхи</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению восхваления подмышек</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека чтобы использовать свои подмышки.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(love)] ничего кроме [npc.her] подмышки сексуально обслуживаются [npc.her] партнерами, и даже предпочитает подмышки для использования в проникающих секс действиях.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "получение любого внимания к подмышкам");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение подмышко-шлюхи");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isArmpitContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ARMPIT_GIVING; }
	};
	
	public static AbstractFetish FETISH_PENIS_GIVING = new AbstractFetish(60,
			"член жеребец",
			"использование своего члена",
			"фетиш_член_использование",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение членом-жеребцом</span> (Requires penis)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению зависимостью от членов</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека использовать свой член.";
				
			} else if(owner.isPlayer()) {
				return "Вы одержимы проникающим сексом. Вгонять свой член в любое доступное отверстие, это все о чем вы думаете...";
			
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на использование [npc.her] члена.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "использовать свой член");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение членом-жеребцом");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_PENIS_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_PENIS_RECEIVING = new AbstractFetish(60,
			"зависимость от членов",
			"чужие члены",
			"фетиш_член_привыкание",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение зависимостью от членов</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению членом-жеребцом</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека обслуживать члены.";
				
			} else if(owner.isPlayer()) {
				return "Вы безнадежно зависимы от членов. Большие, маленькие, толстые, тонкие, для вас не имеет значения какой, пока он двигается вверх вниз в одной их ваших дыр...";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет безнадежную зависимость от членов.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "чужие' члены");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение зависимостью от членов");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_PENIS_GIVING; }
	};
	
	public static AbstractFetish FETISH_CUM_STUD = new AbstractFetish(60,
			"семенной жребец",
			"кончание",
			"фетиш_сперма",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение семенного-жребца</span> (Требует пенис)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению зависимость от спермы</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека кончать внутрь и на партнеров.";
				
			} else {
				return UtilText.parse(owner,
							"[npc.NameHasFull] особая одержимость кончанием. Закачивая все отверстия спермой до краев это то что [npc.she] [npc.verb(love)] больше всего, извержения на тело партнера так же устраивают вас.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "любая форма самосфокусированной игры со спермой");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение семенного-жребца");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_CUM_ADDICT; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_CUM_ADDICT = new AbstractFetish(60,
			"зависимость от спермы",
			"игра со спермой",
			"фетиш_сперма_зависимость",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.CLOTHING_WHITE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение зависимостью спермой</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению семенного-жребца</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека глотанию или покрытию себя спермой.";
				
			} else if(owner.isPlayer()) {
				return "Вы безнадежно зависимы от спермы. Для вас не имеет значения ее источник; все что вы хотите это рот полностью заполненый вкусным, соленым семенем..."
						+ " Позволить ей скользить вокруг вашего языка, смакуя каждый момент... Ммм... Сперма действительно лучшая...";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш зависимости от спермы.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "чужая' сперма");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение зависимостью от спермы");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_CUM_STUD; }
	};
	
	public static AbstractFetish FETISH_DEFLOWERING = new AbstractFetish(60,
			"лишение девственности",
			"лишение девственности",
			"фетиш_лишение девственности",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("Получить <span style='color:"+ PresetColour.GENERIC_EXPERIENCE.toWebHexString()+ ";'>опыт</span> за <span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>лишение девственности</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека забирать девственность партнера";
				
			} else if(owner.isPlayer()) {
				return "Вам не нравится ничего кроме завоевывания девственности невинных дев. Хотя проникновение в киски будующих шлюх ваше любимое дело, вы все еще наслаждаетесь будучи первым человеком который трахает задницу, соски или глотку партнера.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на лишение девственности. [npc.She] любит быть тем кто разорвет девичью плеву, но также наслаждается будучи первым человеком который трахает задницу, соски или глотку партнера.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "забирать девстевенность");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_PURE_VIRGIN; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_PURE_VIRGIN = new AbstractFetish(60,
			"вагинальная девственность",
			"сохранение вагинальной девственности",
			"фетиш_девственность",
			FetishExperience.BASE_VERY_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"[style.colourGood(Получить)] эффект [style.colourExcellent(Невинная дева)]",
					"[style.colourBad(Страдать)] от эффекта [style.colourTerrible(Порванная плева)]"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека удерживать и хранить свою вагинальную девственность.";
				
			} else if(owner.hasVagina()) {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(prize)] [npc.her] вагинальную девственность выше всего на свете. если [npc.she] [npc.was] когда либо потеряет ее, [npc.she] не знает как [npc.she] будет жить с этим...");
				
			} else {
				if(owner.hasFetish(FETISH_PURE_VIRGIN)) {
					return UtilText.parse(owner, "Хотя [npc.name] на данный момент не имеет вагины, [npc.she] [npc.verb(know)] что если [npc.she] [npc.was] будет иметь ее, [npc.she] будет хранить девственность выше всего на свете.");
					
				} else {
					return UtilText.parse(owner, "С того момент как [npc.name] не имеет вагины, [npc.she] не может мечтать о сохранении своей девственности...");
				}
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "держатся за свою девственность");
		}
		
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.getVaginaType()!=VaginaType.NONE && character.isVaginaVirgin();
		}

		@Override
		public List<String> getPerkRequirements(GameCharacter character) {
			perkRequirementsList.clear();
			
			if(character.getVaginaType()==VaginaType.NONE) {
				perkRequirementsList.add("[style.colourBad(Требует вагину)]");
			} else {
				perkRequirementsList.add("[style.colourGood(Требует вагину)]");
			}
			
			if(!character.isVaginaVirgin()) {
				perkRequirementsList.add("[style.colourBad(Требует вагинальную девственность)]");
			} else {
				perkRequirementsList.add("[style.colourGood(Требует вагинальную девственность)]");
			}
			
			return perkRequirementsList;
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ZERO_PURE;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_DEFLOWERING; }
	};
	
	public static AbstractFetish FETISH_MASTURBATION = new AbstractFetish(60,
			"мастурбация",
			"мастурбация",
			"фетиш_мастурбация",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.TEXT_GREY.toWebHexString()+ ";'>Не имеет особенностей</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека мастурбировать.";
				
			} else {
				return UtilText.parse(owner, "Используя [npc.her] [npc.fingers] доводя [npc.herself] или [npc.her] партнеров до оргазма одно из [npc.namePos] любимых занятий во время секса.");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "мастурбация");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	};
	
	
	// FETISH_SPANKING("spanking", "You love the idea of spanking or being
	// spanked during sex."),

	// Effects:
	
	public static AbstractFetish FETISH_IMPREGNATION = new AbstractFetish(60,
			"оплодотворение",
			"оплодотворение",
			"фетиш_оплодотворение",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.VIRILITY, 5)),
			Util.newArrayListOfValues("<span style='color:"
					+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение фертильностью</span> (Требует пенис)",
					"<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Слабость к</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>дразнению плодовитостью</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека оплодотворять других людей.";
				
			} else if(owner.isPlayer()) {
				return "Вы часто фантазируете о заполнении плодовитых маток своим семенем, идея о сношении своего партнера как животное сводит вас с ума от возбуждения.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на оплодотворение [npc.her] партнеров во время секса.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "оплодотворять других");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение фертильностью");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_PREGNANCY; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_PREGNANCY = new AbstractFetish(60,
			"беременность",
			"быть беременной",
			"фетиш_беременность",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, 5)),
			Util.newArrayListOfValues("<span style='color:"
							+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение плодовитостью</span> (Requires vagina)",
					"<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Слабость к</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>дразнению фертильностью</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека быть беременной.";
				
			} else if(owner.isPlayer()) {
				return "Вы часто фантазируете о том чтобы вас оплодотворили, идея о том что вас сношают как животное сводит вас с ума от похоти.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на желание быть беременной.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "быть беременной");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение плодовитостью");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_IMPREGNATION; }
	};

	public static AbstractFetish FETISH_TRANSFORMATION_GIVING = new AbstractFetish(60,
			"transformer",
			"transforming others",
			"fetish_transformation_giving",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Halves cost of all potion making</span>"),
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for transforming others.";
				
			} else if (owner.isPlayer()){
				return "You love the idea of transforming others. Watching their bodies change, either voluntarily or otherwise, is a massive turn-on for you.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] loves transforming others. Watching their bodies change, either voluntarily or otherwise, is a massive turn-on for [npc.herHim].");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "transforming others");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_TRANSFORMATION_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_TRANSFORMATION_RECEIVING = new AbstractFetish(60,
			"test subject",
			"being transformed",
			"fetish_transformation_receiving",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"[style.boldGood(Increases potency)] <span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>of receiving forced transformations</span>",
					"[style.boldBad(Disables)] ability to spit out TF potions"),
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека to be transformed by others.";
				
			} else if(owner.isPlayer()) {
				return "You love the idea of being transformed. Having your body parts changed, either voluntarily or otherwise, is a massive turn-on for you.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] loves being transformed. Having [npc.her] body parts changed, either voluntarily or otherwise, is a massive turn-on for [npc.herHim].");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "the idea of being transformed");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_TRANSFORMATION_GIVING; }
	};
	
	public static AbstractFetish FETISH_KINK_GIVING = new AbstractFetish(60,
			"kink advocate",
			"giving others fetishes",
//			"fetish_transformation_giving",
			"fetish_kink_giving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					// Unclear what extra effects this fetish should provide, other than triggering forced fetishes
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Enjoy making others try new things!</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for giving new fetishes to other people.";
				
			} else if(owner.isPlayer()) {
				return "The idea of giving people new fetishes, either voluntarily or otherwise, is a massive turn-on for you.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] loves giving others new fetishes. Watching them enjoy perverse new things, either voluntarily or otherwise, is a massive turn-on for [npc.herHim].");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "giving others new fetishes");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_KINK_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_KINK_RECEIVING = new AbstractFetish(60,
			"kink curious",
			"gaining fetishes",
//			"fetish_transformation_receiving",
			"fetish_kink_receiving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					// Unclear what extra effects this fetish should provide, other than not taking corruption from receiving forced fetishes
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Removes corruption gain when a fetish is forced on you.</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for gaining new fetishes.";
				
			} else if (owner.isPlayer()) {
				return "You love the idea of developing new fetishes. Gaining perverse joy from new things, either voluntarily or otherwise, is a massive turn-on for you.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] loves developing new fetishes. Gaining perverse joy from new things, either voluntarily or otherwise, is a massive turn-on for [npc.herHim].");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "the idea of being given new fetishes");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_KINK_GIVING; }
	};
	
	// Behaviour (organised roughly in active/passive pairs):
	

	public static AbstractFetish FETISH_DENIAL = new AbstractFetish(60,
			"orgasm denier",
			"denying orgasms",
			"fetish_denial",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			null,
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for denying their partner's orgasms.";
				
			} else if(owner.isPlayer()) {
				return "Either by teasing them with your body, or preventing them from orgasming, you love denying your partners during sex.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for orgasm denial.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "denying orgasms");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_DENIAL_SELF; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_DENIAL_SELF = new AbstractFetish(60,
			"self-denial",
			"being denied",
			"fetish_denial_self",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 1),
					new Value<>(Attribute.RESISTANCE_LUST, 2)),
			null,
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for having their orgasms denied.";
				
			} else if(owner.isPlayer()) {
				return "You love edging and having your orgasms denied.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for having [npc.her] orgasms denied.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "having your orgasms denied");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_DENIAL; }
	};
	
	public static AbstractFetish FETISH_DOMINANT = new AbstractFetish(60,
			"dominant",
			"acting dominantly",
			"fetish_dominant",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 5)),
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>dominant tease</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>submissive tease</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for being the dominant partner in sex.";
				
			} else if(owner.isPlayer()) {
				return "You love being the dominant partner during sex, and you know just how to show your partners who's in charge.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for being the dominant partner in sex.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "being the dominant partner");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "dominant tease");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_SUBMISSIVE; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_SUBMISSIVE = new AbstractFetish(60,
			"submissive",
			"acting submissively",
			"fetish_submissive",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 2)),
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>submissive tease</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>dominant tease</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for being the submissive partner in sex.";
				
			} else if(owner.isPlayer()) {
				return "You love being the submissive partner during sex. You'll do anything to show your submission, and will happily let your partner do whatever they want with you.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for being the submissive partner in sex.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "being the submissive partner");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "submissive tease");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_DOMINANT; }
	};
	
	public static AbstractFetish FETISH_INCEST = new AbstractFetish(60,
			"incest",
			"incestuous sex",
			"fetish_incest",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"Разблокирует <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>incest tease</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for having sex with their relatives.";
				
			} else if(owner.isPlayer()) {
				if(owner.getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
					return "You always did have the hots for your male cousin...";
				} else {
					return "You always did have the hots for your aunt Lily, as well as your female cousin...";
				}
				
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for incestuous sex.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "having sex with your relatives");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "incest tease");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FIVE_CORRUPT;
		}
		@Override
		public boolean isContentEnabled() { return Main.game.isIncestEnabled(); }
	};
	
	public static AbstractFetish FETISH_SADIST = new AbstractFetish(60,
			"sadist",
			"inflicting pain",
			"fetish_sadist",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 5)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Разблокирует)] sadistic [style.colourSex(sex actions)]",
					"[style.boldExcellent(+5%)] to all [style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+" damage)]",
					"10% of all inflicted",
					"[style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+" damage)] is dealt",
					"back to you as <span style='color:"+ Attribute.DAMAGE_LUST.getColour().toWebHexString()+ ";'>lust damage</span>",
					"[style.boldArcane(+1 essence)] when critically",
					" hitting enemies"),
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for abusing others.";
				
			} else if(owner.isPlayer()) {
				return "You love dishing out pain and humiliation, and causing others to suffer sends you absolutely wild with lust.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for dealing out pain and humiliation.");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "inflicting pain and humiliation on others");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_MASOCHIST; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_MASOCHIST = new AbstractFetish(60,
			"masochist",
			"pain and humiliation",
			"fetish_masochist",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 2)),
			Util.newArrayListOfValues(
					"[style.boldSex(Enjoys)] [style.boldTerrible(painful sex actions)]",
					"25% of all incoming",
					"<span style='color:"+ PresetColour.ATTRIBUTE_HEALTH.toWebHexString()+ ";'>"+Attribute.HEALTH_MAXIMUM.getName()+" damage</span>"+ " is converted",
					" to <span style='color:"+ Attribute.DAMAGE_LUST.getColour().toWebHexString()+ ";'>lust damage</span>",
					"[style.boldArcane(+1 essence)] when you're",
					" critically hit"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for being abused.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(get)] extremely turned on when subjected to painful or humiliating experiences."
					+ " [npc.She] [npc.verb(find)] [npc.herself] getting aroused whenever [npc.her] orifices are stretched out or penetrated too deeply.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "pain and humiliation");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_SADIST; }
	};
	
	public static AbstractFetish FETISH_NON_CON_DOM = new AbstractFetish(60,
			"non-consent",
			"raping",
			"fetish_noncon_dom",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Increases</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>arousal gain when partner is resisting sex</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for raping others.";
				
			} else if(owner.isPlayer()) {
				return "You love nothing more than when someone's being forced, against their will, to have sex. The more they're struggling, the more you get turned on...";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for non-consensual encounters. The more [npc.her] victim struggles, the more [npc.she] gets turned on...");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "having sex with an unwilling partner");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FIVE_CORRUPT;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isNonConEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_NON_CON_SUB; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_NON_CON_SUB = new AbstractFetish(60,
			"unwilling fuck-toy",
			"being raped",
			"fetish_noncon_sub",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Increases</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>arousal gain when you are resisting sex</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for being raped by others.";
				
			} else if(owner.isPlayer()) {
				return "You love nothing more than when you're forced, against your will, to have sex with someone. Struggling and pleading to be released turns you on like nothing else...";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for being the victim in non-consensual encounters. Struggling and pleading to be released turns [npc.herHim] on like nothing else...");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "being forced to have sex against your will");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isNonConEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_NON_CON_DOM; }
	};
	
	public static AbstractFetish FETISH_BONDAGE_APPLIER = new AbstractFetish(60,
			"bondage applier",
			"applying bondage",
			"fetish_bondage_applier",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD),
			null,
			Util.newArrayListOfValues(
					"[style.colourGood(Removes essence cost for sealing, servitude, and enslavement enchantments)]"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for locking others into sealed clothing.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(love)] binding people up in clothing that they're unable to remove, and then taking advantage of their immobility...");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "others wearing sealed clothing");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_BONDAGE_VICTIM; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_BONDAGE_VICTIM = new AbstractFetish(60,
			"bondage bitch",
			"being bound",
			"fetish_bondage_victim",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD),
			null,
			Util.newArrayListOfValues(
					"[style.colourTerrible(5x cost)] to [style.colourSeal(unseal)] self-worn clothing",
					"BDSM set bonus applies [style.colourGood(positive effects)]"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for wearing sealed clothing.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(love)] being bound up in clothing that [npc.sheIs] unable to remove, leaving [npc.herHim] at the mercy of others...");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "wearing sealed clothing");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_BONDAGE_APPLIER; }
	};
	
	public static AbstractFetish FETISH_EXHIBITIONIST = new AbstractFetish(60,
			"exhibitionist",
			"exposing themself",
			"fetish_exhibitionist",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Replaces</span> <span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>exposed status effects</span>"
										+" <span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>with beneficial versions</span>"),
			null) {

		@Override
		public String getShortDescriptor(GameCharacter target) {
			if(target==null) {
				return "exposing themself";
			}
			return UtilText.parse(target, "exposing [npc.herself]");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for showing off their body and having others watch them having sex.";
				
			} else if(owner.isPlayer()) {
				return "You love showing off your body, and the act of parading your naked form in public places turns you on like nothing else.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for exhibiting [npc.her] body.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "exposing yourself to others");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}

		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_VOYEURIST; }
	};
	
	public static AbstractFetish FETISH_VOYEURIST = new AbstractFetish(60,
			"voyeurist",
			"watching others",
			"fetish_voyeurist",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Arousal boost</span> while watching sex scenes"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for watching others having sex.";
				
			} else if(owner.isPlayer()) {
				return "You love watching other people... Especially while they're doing something naughty...";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for watching other people...");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "watching others");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_EXHIBITIONIST; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_BIMBO = new AbstractFetish(60,
			"bimbo",
			"being a bimbo",
			null,
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Talk like a bimbo</span>"),
			null) {
		
		@Override
		public String getName(GameCharacter owner) {
			if(owner==null ||owner.isFeminine()) {
				return "bimbo";
			} else {
				return "bro";
			}
		}
		
		@Override
		public String getShortDescriptor(GameCharacter target) {
			if (target==null ||target.isFeminine()) {
				return "being a bimbo";
			} else {
				return "being a bro";
			}
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for acting like a bimbo.";
				
			} else if(owner.isFeminine()) {
				return UtilText.parse(owner,
						"[npc.NameIsFull] obsessed with the idea of acting like a complete bimbo."
						+ " It's gotten to the point where no matter how intelligent [npc.she] might actually be, [npc.she] can't imagine [npc.herself] as anything other than a ditzy airhead.");
			} else {
				return UtilText.parse(owner,
						"[npc.NameIsFull] obsessed with the idea of acting like a dopey surfer bro."
						+ " It's gotten to the point where no matter how intelligent [npc.she] might actually be, [npc.she] can't imagine [npc.herself] as anything other than an airheaded meathead.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			if(target==null || target.isFeminine()) {
				return getGenericFetishDesireDescription(target, desire, "acting like a bimbo");
			} else {
				return getGenericFetishDesireDescription(target, desire, "acting like a bro");
			}
		}

		@Override
		public List<String> getExtraEffects(GameCharacter owner) {
			if(owner==null || owner.isFeminine()) {
				return Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Talk like a bimbo</span>");
			} else {
				return Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Talk like a bro</span>");
			}
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner==null || owner.isFeminine()) {
				return bimboString;
			} else {
				return broString;
			}
		}

		@Override
		public FetishPreference getFetishPreferenceDefault() {
			return FetishPreference.TWO_DISLIKE;
		}
	};
	
	public static AbstractFetish FETISH_CROSS_DRESSER = new AbstractFetish(60,
			"cross dressing",
			"cross dressing",
			"fetish_cross_dresser",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Immune to clothing femininity status effects</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for wearing clothes that are either too feminine or too masculine for them.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(love)] wearing all manner of different clothing, and [npc.she] [npc.do]n't care if it's considered by others to be too masculine or feminine for [npc.her] body.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "wearing clothing more suited to the opposite gender");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}

		@Override
		public FetishPreference getFetishPreferenceDefault() {
			return FetishPreference.TWO_DISLIKE;
		}
	};
	
	public static AbstractFetish FETISH_SIZE_QUEEN = new AbstractFetish(60,
			"size queen",
			"deep penetrations",
			"fetish_size_queen",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			Util.newArrayListOfValues(PresetColour.BASE_YELLOW, PresetColour.BASE_PINK),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 1)),
			Util.newArrayListOfValues(
					"[style.colourGood(Enjoys)] [style.colourSex(being stretched)]",
					"Treats [style.colourSex('uncomfortably deep')] insertions as being [style.colourGood('comfortable')]"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for having their partners as well-hung as possible.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(prefer)] [npc.her] partners to be extremely well-endowed, and [npc.verb(love)] to feel them as deep inside of [npc.herHim] as physically possible.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "taking large insertions");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isPenetrationLimitationsEnabled(); }
	};
	
	// Derived fetishes:
	
	public static AbstractFetish FETISH_SWITCH = new AbstractFetish(60,
			"switch",
			"being a switch",
			"fetish_switch",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5)),
			null,
			Util.newArrayListOfValues(
					Fetish.FETISH_DOMINANT,
					Fetish.FETISH_SUBMISSIVE)) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for being either the dominant or submissive partner in sex.";
				
			} else if(owner.isPlayer()) {
				return "You're perfectly happy with switching between dom and sub as the situation calls for it.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] is happy to play as either the dom or sub during sex.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "switching between dom and sub");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	};
	
	public static AbstractFetish FETISH_BREEDER = new AbstractFetish(60,
			"breeder",
			"breeding",
			"fetish_breeder",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.FERTILITY, 25),
					new Value<>(Attribute.VIRILITY, 25)),
			null,
			Util.newArrayListOfValues(
					Fetish.FETISH_PREGNANCY,
					Fetish.FETISH_IMPREGNATION)) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for both being pregnant and impregnating others.";
				
			} else if (owner.isPlayer()) {
				return "You have a dream. A dream of a world in which everyone is pregnant, including yourself!";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] wants nothing more than to share [npc.her] love of pregnancies with everyone [npc.she] meets.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "anything to do with reproduction");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	};
	
	public static AbstractFetish FETISH_SADOMASOCHIST = new AbstractFetish(60,
			"sadomasochist",
			"sadomasochism",
			"fetish_sadomasochist",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 3),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10)),
			null,
			Util.newArrayListOfValues(
					Fetish.FETISH_SADIST,
					Fetish.FETISH_MASOCHIST)) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for abusing others and being abused in turn.";
				
			} else if (owner.isPlayer()) {
				return "You don't care whether you're on the giving or receiving end; if there's pain and humiliation involved, you're up for anything.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] loves pain and humiliation in all of its forms.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "all forms of pain and humiliation");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	};
	
	public static AbstractFetish FETISH_LUSTY_MAIDEN = new AbstractFetish(60,
			"lusty maiden",
			"lusty maiden",
			"fetish_lusty_maiden",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Empowers</span> <span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>'pure virgin'</span>",
					"<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Amplifies</span> <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>'broken virgin'</span>"),
			null) {
		@Override
		public List<AbstractFetish> getFetishesForAutomaticUnlock() {
			return Util.newArrayListOfValues(
					Fetish.FETISH_PURE_VIRGIN,
					Main.game.isAnalContentEnabled()
						?Fetish.FETISH_ANAL_RECEIVING
						:null,
					Fetish.FETISH_ORAL_GIVING,
					Fetish.FETISH_BREASTS_SELF);
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека for retaining their vaginal virginity while using their ass, breasts, or mouth to get their partners to climax.";
				
			} else if (owner.isPlayer()) {
				return "You are the ultimate tease, seducing and pleasuring others with your ass, mouth, breasts, and even the promise of your pussy,"
							+ " but you'll never actually allow anyone to penetrate your feminine sex and take your precious virginity.";
			} else {
				return UtilText.parse(owner, "[npc.Name] loves to pleasure others with [npc.her] ass, mouth, breasts, and even the promise of [npc.her] pussy,"
							+ " but [npc.she]'ll never actually allow anyone to penetrate [npc.her] feminine sex and take [npc.her] precious virginity.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "avoiding vaginal sex");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	};
	
	
	// Helper methods:
	
	private static String getAppliedFetishAttackLevelEffectDescription(GameCharacter character, AbstractFetish fetish, String fetishAttackName) {
		FetishLevel level = character.getFetishLevel(fetish);
		return "+"+level.getBonusTeaseDamage()+" base damage to "+fetishAttackName;
	}
	
	// Access methods:
	
	public static List<AbstractFetish> allFetishes;
	
	public static Map<AbstractFetish, String> fetishToIdMap = new HashMap<>();
	public static Map<String, AbstractFetish> idToFetishMap = new HashMap<>();
	
	/**
	 * @param id Will be in the format of: 'innoxia_maid'.
	 */
	public static AbstractFetish getFetishFromId(String id) {
		id = Util.getClosestStringMatch(id, idToFetishMap.keySet());
		
		return idToFetishMap.get(id);
	}
	
	public static String getIdFromFetish(AbstractFetish fetish) {
		return fetishToIdMap.get(fetish);
	}

	static {
		allFetishes = new ArrayList<>();
		
		// Hard-coded fetishes (all those up above):
		
		Field[] fields = Fetish.class.getFields();
		
		for(Field f : fields){
			if (AbstractFetish.class.isAssignableFrom(f.getType())) {
				
				AbstractFetish fetish;
				
				try {
					fetish = ((AbstractFetish) f.get(null));

					fetishToIdMap.put(fetish, f.getName());
					idToFetishMap.put(f.getName(), fetish);
					allFetishes.add(fetish);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static List<AbstractFetish> getAllFetishes() {
		return allFetishes;
	}
	
}
