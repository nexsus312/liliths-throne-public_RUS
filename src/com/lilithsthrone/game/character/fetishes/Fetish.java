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
			"трасформатор",
			"трансформировать других",
			"фетиш_трасформация_другие",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Вдвое снижает стоимость создания зелий</span>"),
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека трансформировать других.";
				
			} else if (owner.isPlayer()){
				return "Вы любите идею трасформации других людей. Смотреть как меняются их тела, добровольно или иначе, очень сильно заводит вас.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] любит трасформировать других. мотреть как меняются их тела, добровольно или иначе, очень возбуждающе для [npc.herHim].");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "трансформировать других");
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
			"подопытный",
			"быть целью трансформации",
			"фетиш_трасформация_получение",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"[style.boldGood(Увеличивает эффекты)] <span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>применямых или принужденных трансофрмаций</span>",
					"[style.boldBad(Отключает)] способность выплевывать трасформирующие зелья"),
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека быть трасформированным другими.";
				
			} else if(owner.isPlayer()) {
				return "Вам нравится идея о изменении своего тело. Чувствовать как меняется ваше тело, добровольно или иначе, очень возбуждает вас.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] любит трансформации. Чувствовать как [npc.her] части тела меняются, добровольно или иначе, очень возбуждающе для [npc.herHim].");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "идея быть жертвой трансформации");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_TRANSFORMATION_GIVING; }
	};
	
	public static AbstractFetish FETISH_KINK_GIVING = new AbstractFetish(60,
			"защитник фетишей",
			"давать другим фетиши",
//			"fetish_transformation_giving",
			"фетиш_кинк_давать",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					// Unclear what extra effects this fetish should provide, other than triggering forced fetishes
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Наслаждайтесь давая другим возможность попробовать что-то новое!</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека пробуждать новые фетиши у других людей.";
				
			} else if(owner.isPlayer()) {
				return "Идея пробуждения в людях фетишей, добровольно или иначе, очень сильно вас заводит.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] любит пробуждать в людях фетиши. Смотреть как они наслаждаются новыми извращениями, добровольно или иначе, очень возбуждающе для [npc.herHim].");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "пробуждать фетиши у людей");
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
			"любопытсво к фетишам",
			"получать фетиши",
//			"fetish_transformation_receiving",
			"фетиш_кинк_получение",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					// Unclear what extra effects this fetish should provide, other than not taking corruption from receiving forced fetishes
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Убирает получение испорченности когда вас принуждают к фетишам.</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека получать новые фетиши.";
				
			} else if (owner.isPlayer()) {
				return "Вам нравится идея получения новых фетишей. Получая извращенное удовольствие от новых вещей, добровольно или иначе, очень заводит вас.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] нравится идея получения новых фетишей. Получая извращенное удовольствие от новых вещей, добровольно или иначе, очень возбуждающе для [npc.herHim].");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "идея получения новых фетишей");
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
			"отрицатель оргазмов",
			"отказ от оргазмов",
			"фетиш_оказ",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			null,
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека недопускать оргазм своего партнера.";
				
			} else if(owner.isPlayer()) {
				return "Либо дразня их своим телом, или недопуская их оргазма, вы любите недопускать оргазма ваших партнеров.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на недопускание оргазмов.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "недопускать оргазмы");
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
			"сама-отказ",
			"быть недопущенным до оргазма",
			"фетиш_отказ_сам",
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
				return "Этот фетиш относится к желанию человека недопускать сообственных оргазмов.";
				
			} else if(owner.isPlayer()) {
				return "Вы любите ходить по краю и отказыватся от сообственного оргазма..";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на отказ от [npc.her] оргазмов.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "недопущенные оргазмы");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_DENIAL; }
	};
	
	public static AbstractFetish FETISH_DOMINANT = new AbstractFetish(60,
			"доминант",
			"действовать доминирующе",
			"фетиш_доминант",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 5)),
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение доминированием</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению покорностью</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека доминировать над партнером в сексе.";
				
			} else if(owner.isPlayer()) {
				return "Вы любите доминировать над партнером во время секса, и вы знаете как показать партнеру кто главный.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на доминирование над партнером во время секса.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "доминировать во время секса");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнить доминированием");
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
			"покорность",
			"действовать покорно",
			"фетиш_покорность",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 2)),
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Разблокирует</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение покорностью</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Слабость к</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнению доминированием</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека быть покорным во время секса.";
				
			} else if(owner.isPlayer()) {
				return "Вам нравится подчинятся во время секса. Вы сделаете все чтобы показать свое подчинение, и вы счастливы дать вашему партнеру возможность делать что угодно.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на покорность во время секса.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "быть покорным");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение покорностю");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_DOMINANT; }
	};
	
	public static AbstractFetish FETISH_INCEST = new AbstractFetish(60,
			"инцест",
			"родственный секс",
			"фетиш_инцест",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"Разблокирует <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>дразнение инцестом</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека иметь секс со своими родственниками.";
				
			} else if(owner.isPlayer()) {
				if(owner.getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
					return "Вам всегда западал в душу двоюродный брат...";
				} else {
					return "Вам всегда западала в душу ваша тетя Лили, а так же ваша двоюродная сестра...";
				}
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на инцест.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "иметь секс со своими родстенниками");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "дразнение инцестом");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FIVE_CORRUPT;
		}
		@Override
		public boolean isContentEnabled() { return Main.game.isIncestEnabled(); }
	};
	
	public static AbstractFetish FETISH_SADIST = new AbstractFetish(60,
			"садист",
			"причинять боль",
			"фетиш_садист",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 5)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Разблокирует)] садисткие [style.colourSex(секс действия)]",
					"[style.boldExcellent(+5%)] для всех [style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+" урона)]",
					"10% всего причененного",
					"[style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+" урона)] вернется",
					"назад как <span style='color:"+ Attribute.DAMAGE_LUST.getColour().toWebHexString()+ ";'>похоть</span>",
					"[style.boldArcane(+1 эссенция)] при критах",
					" поражающих врагов"),
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека мучить других.";
				
			} else if(owner.isPlayer()) {
				return "Боль и унижение ваша пища и вино, видеть вызванные вами страдания невероятно возбуждающе.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имет фетиш на причинение боли и унижения.");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "причинять боль и унижение другим");
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
			"мазохизм",
			"боль и унижение",
			"фетиш_мазохизм",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 2)),
			Util.newArrayListOfValues(
					"[style.boldSex(Наслаждайтесь)] [style.boldTerrible(болезненными секс действиями)]",
					"25% всего поступающего урона",
					"<span style='color:"+ PresetColour.ATTRIBUTE_HEALTH.toWebHexString()+ ";'>"+Attribute.HEALTH_MAXIMUM.getName()+" урон</span>"+ " преобразуется",
					" в <span style='color:"+ Attribute.DAMAGE_LUST.getColour().toWebHexString()+ ";'>похоть</span>",
					"[style.boldArcane(+1 эссенция)] во время",
					" критических ударов"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека быть униженным.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(get)] экстремально возбужденными когда испытавают болезненный или унижающий опыт."
					+ " [npc.She] [npc.verb(find)] [npc.herself] возбуждается когда [npc.her] отверстия растягиваются или проникаются слишком глубоко.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "боль и унижение");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_SADIST; }
	};
	
	public static AbstractFetish FETISH_NON_CON_DOM = new AbstractFetish(60,
			"без согласия",
			"насиловать",
			"фетиш_безсогл_дом",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Увеличивает</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>возбуждение когда партнер сопротивляется сексу</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека насиловать других.";
				
			} else if(owner.isPlayer()) {
				return "Вы любите принуждать других иметь секс против их воли. Чем больше они сопротивляются, тем больше вы возбуждаетесь...";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на изнасилование. Чем больше [npc.her] жертвы сопростивляются, тем больше [npc.she] возбуждается...");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "иметь секс без согласия партнера");
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
			"безвольная секс игрушка",
			"быть изнасилованой",
			"фетиш_безсогл_подчинение",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Увеличивает</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>возбуждение когда вы сопротивляетесь сексу</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека быть изнасилованным другими.";
				
			} else if(owner.isPlayer()) {
				return "Вы любите когда вас принуждают иметь секс против вашей воли. Сопротивление и мольбы освобождают похоть как ничто другое...";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш быть жертвой во время секса. Сопротивление и мольбы освобождают [npc.herHim] похоть как ничто другое...");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "иметь секс против вашей воли");
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
			"применитель бондажа",
			"применять бондаж",
			"фетиш_бондаж_применять",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD),
			null,
			Util.newArrayListOfValues(
					"[style.colourGood(Убирает стоимость для запечатывая, принуждения и порабощающих печатей.)]"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека одевать других в запечатанную одежду.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(love)] запечатывать одежду людей, чтобы они не могли ее снять и пользуется преимуществом их неподвижности...");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "другие носят запечатанную одежду");
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
			"бондаж сучка",
			"быть скованной",
			"фетиш_бондаж_жертва",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD),
			null,
			Util.newArrayListOfValues(
					"[style.colourTerrible(5x стоимость)] для [style.colourSeal(распечатывания)] носимой одежды",
					"БДСМ бонус набора накладывает [style.colourGood(позитивные эффекты)]"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека в запечатанной одежде.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(love)] быть запечатанным в одежде которую [npc.sheIs] не может снять, оставляя [npc.herHim] на милосердие другиъ...");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "носить запечатанную одежду");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_BONDAGE_APPLIER; }
	};
	
	public static AbstractFetish FETISH_EXHIBITIONIST = new AbstractFetish(60,
			"эксгибиционизм",
			"обнажить себя",
			"фетиш_эксгибиционизм",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Заменяет</span> <span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>статус обнажения effects</span>"
										+" <span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>более выгодным вариантом</span>"),
			null) {

		@Override
		public String getShortDescriptor(GameCharacter target) {
			if(target==null) {
				return "обнажать себя";
			}
			return UtilText.parse(target, "обнажает [npc.herself]");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека показывать других и иметь наблюдателей во время секса.";
				
			} else if(owner.isPlayer()) {
				return "Вы любите показывать свое тело, посещение мест будучи без одежды невероятно возбуждает вас..";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш на обнажение [npc.her] тела.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "обнажать себя для других");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}

		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_VOYEURIST; }
	};
	
	public static AbstractFetish FETISH_VOYEURIST = new AbstractFetish(60,
			"вуайеризм",
			"наблюдать за другими",
			"фетиш_вуайерист",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Бонус к возбуждению</span> во время наблюдения секс сцен"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека смотреть как другие занимаются сексом.";
				
			} else if(owner.isPlayer()) {
				return "Вы любите наблюдать за людьми... Особенно когда они шалят...";
			} else {
				return UtilText.parse(owner, "[npc.Name] имеет фетиш наблюдения за людьми...");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "наблюдать других");
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
			"бимбо",
			"быть бимбо",
			null,
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Разговаривать как бимбо</span>"),
			null) {
		
		@Override
		public String getName(GameCharacter owner) {
			if(owner==null ||owner.isFeminine()) {
				return "бимбо";
			} else {
				return "бро";
			}
		}
		
		@Override
		public String getShortDescriptor(GameCharacter target) {
			if (target==null ||target.isFeminine()) {
				return "быть бимбо";
			} else {
				return "быть бро";
			}
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека действовать как бимбо.";
				
			} else if(owner.isFeminine()) {
				return UtilText.parse(owner,
						"[npc.NameIsFull] одержима идеей дейстовать как полная бимбо."
						+ " Дошло до того что не важно насколько интеллектуальной [npc.she] может быть на самом деле, [npc.she] не может представить [npc.herself] никак кроме пустоголовой глупышки.");
			} else {
				return UtilText.parse(owner,
						"[npc.NameIsFull] одержим идеей вести себя как глупый бро серфер."
						+ " Дошло до того что не важно насколько интеллектуальным [npc.she] может быть на самом деле, [npc.she] не может представить [npc.herself] никак кроме легкомысленного болвана.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			if(target==null || target.isFeminine()) {
				return getGenericFetishDesireDescription(target, desire, "действовать как бимбо");
			} else {
				return getGenericFetishDesireDescription(target, desire, "действовать как бро");
			}
		}

		@Override
		public List<String> getExtraEffects(GameCharacter owner) {
			if(owner==null || owner.isFeminine()) {
				return Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Говорить как бимбо</span>");
			} else {
				return Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>Говорить как бро</span>");
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
			"переодевание",
			"переодевание",
			"фетиш_переодевание",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>Иммунитет к женственному статусу одежды</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека носить одежду которая слишком женственная или мужественная для них.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(love)] носить всевозможные разнообразные одежды и [npc.she] не важно не важно что другие считают что она слишком женственна илим межественная для их [npc.her] тела.");
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
			"королевский размер",
			"глубокие проникновения",
			"фетиш_размер_королева",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			Util.newArrayListOfValues(PresetColour.BASE_YELLOW, PresetColour.BASE_PINK),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 1)),
			Util.newArrayListOfValues(
					"[style.colourGood(Наслаждается)] [style.colourSex(растягиванием)]",
					"Обращается к [style.colourSex('некомфортно глубоким')] проникновениям как к [style.colourGood('комфортным')]"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "Этот фетиш относится к желанию человека иметь `огромных` и обеспеченых партнеров.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] [npc.verb(prefer)] [npc.her] партнеры были чрезвычайно хорошо обеспечены и [npc.verb(love)] чувствовать их так глубоко внутри себя как [npc.herHim] физически способны.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "получать большие проникновения");
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
			"переключатель",
			"быть переключателем",
			"фетиш_переключатель",
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
				return "Этот фетиш относится к желанию человека быть как доминирующим так и покорным.";
				
			} else if(owner.isPlayer()) {
				return "Вы совершенно счастливы переключаясь между дом. и покор. в зависимости от ситуации.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] может переключатся между дом. и покор. в зависимости от ситуации.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "переключатся между дом. и покор.");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	};
	
	public static AbstractFetish FETISH_BREEDER = new AbstractFetish(60,
			"племенной",
			"сношение",
			"фетиш_сношение",
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
				return "Этот фетиш относится к желанию человека как иметь беременность так и оплодотворять других.";
				
			} else if (owner.isPlayer()) {
				return "У вас был сон. Мир мечты в котором все вокруг беременны, включая вас!";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] не хочет ничего кроме как делить [npc.her] любовь к берменности со всеми кого [npc.she] встретит.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "все что связано с размножением");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	};
	
	public static AbstractFetish FETISH_SADOMASOCHIST = new AbstractFetish(60,
			"садомазохизм",
			"садомазохизм",
			"фетиш_садомазохизм",
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
				return "Этот фетиш относится к желанию человека жестоко обращатся с другими и получать такое же отношение от других.";
				
			} else if (owner.isPlayer()) {
				return "Вам не важно причинять или получать; если включено боль и унижение, вы готовы на все.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] любит все формы боли и унижения.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "все формы боли и унижений");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	};
	
	public static AbstractFetish FETISH_LUSTY_MAIDEN = new AbstractFetish(60,
			"похотливая дева",
			"похотливая дева",
			"фетиш_похотливая_дева",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Усиляет</span> <span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>'невинная дева'</span>",
					"<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Умножает</span> <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>'порваная плева'</span>"),
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
				return "Этот фетиш относится к желанию человека сохранять свою вагинальную девственность используя в замен свою задницу, груди или рот.";
				
			} else if (owner.isPlayer()) {
				return "Вы самая лучшая дразнилка, соблазнять и удовлетворять других своей задницей, ртом, грудями и даже обещанием своей киски,"
							+ " но на самом деле вы никогда не позволите никому проникнуть в вашу узкую, тугую киску, и забрать вашу драгоценную девственность.";
			} else {
				return UtilText.parse(owner, "[npc.Name] любит удовлетворять других используя [npc.her] задницу, рот, груди и даже обещая [npc.her] киску,"
							+ " но [npc.she] никогда не позволите никому проникнуть в [npc.her] узкую, тугую киску, и забрать [npc.her] драгоценную девственность.");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "избегать вагинального секса");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	};
	
	
	// Helper methods:
	
	private static String getAppliedFetishAttackLevelEffectDescription(GameCharacter character, AbstractFetish fetish, String fetishAttackName) {
		FetishLevel level = character.getFetishLevel(fetish);
		return "+"+level.getBonusTeaseDamage()+" базового урона к "+fetishAttackName;
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
