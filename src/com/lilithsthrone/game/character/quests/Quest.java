package com.lilithsthrone.game.character.quests;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.fields.Aurokaris;
import com.lilithsthrone.game.character.npc.fields.Lunexis;
import com.lilithsthrone.game.character.npc.fields.Ursa;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.BraxOffice;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4.6.3
 * @author Innoxia
 */
public enum Quest {
	
	
	// Main quests:

	MAIN_PROLOGUE(QuestType.MAIN, 1, 5) {
		@Override
		public String getName() {
			return "Пережить вечер";
		}
		@Override
		public String getDescription() {
			return "Вы обещали своей тете Лили посетить открытие новой экспозиции в ее музее. Вам нужно пережить скуку предстоящего вечера.";
		}
		@Override
		public String getCompletedDescription() {
			return "Ваш вечер в музее оказался гораздо более насыщенным событиями, чем вам хотелось бы."
					+ " Таинственный демон по имени Лилит обманом заманил вас через магический портал в параллельную вселенную."
					+ " Очнувшись посреди незнакомой улицы, вы были спасены из тяжелой ситуации полудемоном `Лилайей`."
					+ " Похоже, она - версия вашей тети Лили в этой вселенной, и в обмен на согласие помогать ей в экспериментах она разрешила вам пожить в ее доме.";
		}
	},

	MAIN_1_A_LILAYAS_TESTS(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "Тесты Лилайи";
		}
		@Override
		public String getDescription() {
			return "В любой момент вы можете найти Лилайю в ее лаборатории, где она будет готова продолжить проводить над вами свои опыты. Может быть, она найдет способ отправить вас обратно домой?";
		}
		@Override
		public String getCompletedDescription() {
			return "Лилайя провела еще несколько тестов на тебе, но она не может продвинуться в своих исследованиях без помощи своего старого коллеги, Артура.";
		}
		@Override
		public void applySkipQuestEffects() {
			((Arthur) Main.game.getNpc(Arthur.class)).generateNewTile();
		}
	},

	MAIN_1_B_DEMON_HOME(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "Поиски Артура; Дом демонов";
		}
		@Override
		public String getDescription() {
			return "Лилайя сообщила вам, что ее старый коллега, Артур, может знать больше о типе магии, используемой в портале."
					+ " Однако, похоже, она испытывает к нему сильную неприязнь, и вам поручено заставить его извиниться перед Лилайей, прежде чем она позволит ему" + " прийти и работать с ней."
					+ " Артур живет в многоквартирном доме под названием «Солти Тауэрс», в районе города, известном как «Дом демонов», так что вы можете найти его там.";
		}
		@Override
		public String getCompletedDescription() {
			return "Прибыв в дом Артура, вы обнаружили, что энфорсеры Доминиона арестовали его по подозрению в заговоре против Лилит." + " После ареста его отвезли в штаб-квартиру энфорсеров.";
		}
		@Override
		public void applySkipQuestEffects() {
			// No effects applied
		}
	},

	MAIN_1_C_WOLFS_DEN(QuestType.MAIN, 3, 20) {
		@Override
		public String getName() {
			return "Поиски Артура; Волчье логово";
		}
		@Override
		public String getDescription() {
			return "Артур был арестован энфорсерами Доминиона и доставлен их штаб-квартиру."
					+ " Похоже, вам придется навести там справки и найти способ спасти Артура.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы были вынуждены противостоять инспектору энфорсеров по имени Бракс"
					+ " К счастью, вам удалось справиться с ним, но затем вы узнали, что Артур был продан в рабство!";
		}
		@Override
		public void applySkipQuestEffects() {
			BraxOffice.setBraxsPostQuestStatus(false);
			BraxOffice.givePlayerEnforcerUniform(null,-1);
		}
	},

	MAIN_1_D_SLAVERY(QuestType.MAIN, 3, 10) {
		@Override
		public String getName() {
			return "В поисках Артура; Продан в рабство";
		}
		@Override
		public String getDescription() {
			return "Победив Бракса, вы узнали, что Артур был продан в рабство торговцу по имени Скарлетт."
					+ " Вам предстоит отправиться на Аллею рабов, найти Скарлетт и узнать об Артуре.";
		}
		@Override
		public String getCompletedDescription() {
			return "В переулке работорговцев вы нашли гарпию Скарлетт, которая оказалась одним из самых грубых людей, которых вы когда-либо встречали.";
		}
		@Override
		public void applySkipQuestEffects() {
			// No effects applied
		}
	},
	
	MAIN_1_E_REPORT_TO_HELENA(QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "В поисках Артура; Найти Елену";
		}
		@Override
		public String getDescription() {
			return "Найдя Скарлетт на Аллее рабов, вы обнаружили, что она больше не владеет Артуром."
					+ " Прежде чем она расскажет вам подробности, она хочет, чтобы вы отправились в гнезда гарпий и доложили матриарху, Елене, что ее бизнес потерпел полное фиаско.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы сообщили о проблемах Скарлетт ее матриарху, Елене."
					+ " Похоже, она не испытывала особого сочувствия к Скарлетт и быстро улетела, чтобы поговорить с ней лично.";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
			Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
		}
	},
	
	MAIN_1_F_SCARLETTS_FATE(QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "Поиски Артура; судьба Скарлетт";
		}
		@Override
		public String getDescription() {
			return "Вам нужно вернуться в магазин Скарлетт, чтобы узнать, что с ней стало. Надеюсь, Елена не была слишком жестока с ней, и она сможет рассказать вам, что случилось с Артуром...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы вернулись в магазин Скарлетт, но обнаружили, что Елена поработила ее!";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
			Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.POSITIVE_TWO_OBEDIENT.getMedianValue());
			Main.game.getNpc(Scarlett.class).resetInventory(true);
			AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_BLACK_STEEL, false);
			collar.setSealed(true);
			Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(collar, true, Main.game.getNpc(Helena.class));
			Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", PresetColour.CLOTHING_PINK, false), true, Main.game.getNpc(Helena.class));
		}
	},
	
	MAIN_1_G_SLAVERY(QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "Поиски Артура; Рабство";
		}
		@Override
		public String getDescription() {
			return "Елена готова продать вам Скарлетт, и это, похоже, единственный способ получить нужную информацию."
					+ " Чтобы купить Скарлетт, вам понадобится лицензия рабовладельца.";
		}
		@Override
		public String getCompletedDescription() {
			return "Елена продала вам Скарлетт, что позволило вам приказать Скарлетт рассказать, что случилось с Артуром.";
		}
		@Override
		public void applySkipQuestEffects() {
			AbstractClothing ballgag = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.MOUTH);
			if (ballgag != null) {
				ballgag.setSealed(false);
				Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(ballgag, true, Main.game.getNpc(Helena.class));
			}
			
			// Complete slavery side quest:
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLAVERY)) {
				Main.game.getPlayer().startQuest(QuestLine.SIDE_SLAVERY);
			}
			List<Quest> slaverSkipQuests = Util.newArrayListOfValues(
					Quest.SIDE_SLAVER_NEED_RECOMMENDATION,
					Quest.SIDE_SLAVER_RECOMMENDATION_OBTAINED,
					Quest.SIDE_UTIL_COMPLETE);
			for(int i=0; i<slaverSkipQuests.size()-1; i++) {
				Quest q = slaverSkipQuests.get(i);
				if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLAVERY)==q) {
					q.applySkipQuestEffects();
					Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLAVERY, slaverSkipQuests.get(i+1));
				}
			}
			
			Main.game.getNpc(Scarlett.class).setAffection(Main.game.getNpc(Helena.class), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
			Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.NEGATIVE_FOUR_DEFIANT.getMedianValue());
			Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
			Main.game.getPlayer().addSlave(Main.game.getNpc(Scarlett.class));
			
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
			
			((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
		}
	},
	
	MAIN_1_H_THE_GREAT_ESCAPE(QuestType.MAIN, 10, 200) {
		@Override
		public String getName() {
			return "Поиски Артура; Великий побег";
		}
		@Override
		public String getDescription() {
			return "Оказывается, Артур был продан чрезвычайно опасному демону по имени Зараникс, который живет в Доме демонов."
					+ " Вам предстоит отправиться в дом демона и спасти Артура!";
		}
		@Override
		public String getCompletedDescription() {
			return "Победив Зараникса, вы спасли Артура и вернули его в дом Лилайи.";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, true);
			Main.game.getNpc(Arthur.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
		}
	},
	
	MAIN_1_I_ARTHURS_TALE(QuestType.MAIN, 1, 30) {
		@Override
		public String getName() {
			return "Поиски Артура; Заключение";
		}

		@Override
		public String getDescription() {
			return "Теперь, когда вы спасли Артура из лап Зараникса, вам следует вернуться в дом Лилайи и узнать о том что с ним произошло.";
		}

		@Override
		public String getCompletedDescription() {
			return "Артур объяснил, что занимается запретным искусством телепортационных заклинаний."
					+ " Через одного из своих агентов Зараникс узнал об этом, и ему не составило труда обратить Артура в рабство за измену."
					+ " Теперь, когда вы спасли его, он хочет отплатить вам за услугу, выяснив, как отправить вас обратно домой.";
		}
		@Override
		public void applySkipQuestEffects() {
			Cell arthurRoomCell = Lab.addArthurRoom();
			Main.game.getNpc(Arthur.class).setLocation(arthurRoomCell, true);
		}
	},
	
	// This quest is no longer used, but is left here for old version support
	MAIN_1_J_ARTHURS_ROOM(QuestType.MAIN, 1, 30) {
		@Override
		public String getName() {
			return "Поиски Артура; Собственная комната";
		}
		@Override
		public String getDescription() {
			return "Лилайя очень не хочет видеть Артура в своей лаборатории и поручила вам помочь Розе найти подходящую комнату для его проживания.<br/>"
					+ "<i>Зайдите в одну из пустых комнат в доме Лилайи и через окно управления комнатой улучшите ее до «Комнаты Артура».</i>";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы нашли подходящую комнату для Артура и, с помощью Розы, перенесли значительное количество колдовских приборов в его новую лабораторию-спальню.";
		}
	},
	
	
	MAIN_2_A_INTO_THE_DEPTHS(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "В подземный город Покорности";
		}
		@Override
		public String getDescription() {
			return "Артур смог объяснить механизм, с помощью которого вы были перенесены в этот новый мир, но, кажется, он умолчал о некоторых деталях."
					+ " Он сказал, что объяснит все до конца, как только точно узнает, что происходит, но для этого ему нужно будет поговорить с одной из семи старших Лилин."
					+ " После долгих споров Лилайя согласилась убедить свою мать помочь, но передавать сообщение придется вам.<br/>"
					+ "<i>Спуститесь в подземный город Покорности и попросите аудиенции у матери Лилайи, Лиссиет.</i>";
		}
		@Override
		public String getCompletedDescription() {
			return "По совету Артура вы спустились в Покорность и обнаружили, где находится дворец Лисиет.";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING), false);
		}
	},
	
	MAIN_2_B_SIRENS_CALL(QuestType.MAIN, 25, 300) {
		@Override
		public String getName() {
			return "Зов сирены";
		}
		@Override
		public String getDescription() {
			return "Стражники у ворот дворца Лисиет сказали вам, что сейчас она не принимает гостей."
					+ " Единственный способ добиться ее аудиенции - позаботиться о ее проблемной дочери, «Темной сирене»."
					+ " Сейчас она живет в каменной крепости в одном из центральных туннелей Покорности, откуда посылает банды импов терроризировать невинных жителей.</br>"
					+ "Если вам удастся поработить ее в прямом бою или используя хитрость, вы заслужите аудиенцию у Лисиет.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы сумели поработить проблемную дочь Лиссиет и заслужили аудиенцию!";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING));
			ImpCitadelDialogue.clearFortress(false);
			// Set tunnels to be cleared manually, as they haven't been cleared when skipping quests:
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaDefeated, true);
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)) {
					character.returnToHome();
				}
			}
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesDefeated, true);
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)) {
					character.returnToHome();
				}
			}
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesDefeated, true);
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_MALES)) {
					character.returnToHome();
				}
			}
		}
	},
	
	MAIN_2_C_SIRENS_FALL(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "Падение сирены";
		}

		@Override
		public String getDescription() {
			return "Вернитесь во дворец Лисиет и доложите стражникам, что вы поработили «Темную сирену»."
					+ " Этого должно быть достаточно, чтобы вы получили аудиенцию у Лисиет.";
		}

		@Override
		public String getCompletedDescription() {
			return "За то, что вы поработили «Темную сирену», стражники у ворот дворца Лисиет дали вам разрешение войти и получить аудиенцию.";
		}
		@Override
		public void applySkipQuestEffects() {
			if(Main.game.getPlayer().hasItemType(ItemType.LYSSIETHS_RING)) {
				Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING));
			}
			if(!Main.game.getPlayer().hasClothingType(ClothingType.FINGER_LYSSIETHS_RING, true)) {
				Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(ClothingType.FINGER_LYSSIETHS_RING), false);
			}
		}
	},
	
	MAIN_2_D_MEETING_A_LILIN(QuestType.MAIN, 1, 100) {
		@Override
		public String getName() {
			return "Встреча с Лилин";
		}

		@Override
		public String getDescription() {
			return "Отправляйтесь в тронный зал Лисиет и попытайтесь наконец получить ответы на вопросы о том, почему вы здесь и как можно вернуться в свой прежний мир.";
		}

		@Override
		public String getCompletedDescription() {
			return "Лиссиет рассказала, что этот мир на самом деле ваш собственный, и что Лилит превратила его в другую реальность, когда вышла из зеркала.";
		}
		@Override
		public void applySkipQuestEffects() {
			((DarkSiren)Main.game.getNpc(DarkSiren.class)).postDefeatReset();
			AbstractItemEffectType.getBookEffect(Main.game.getPlayer(), Subspecies.LILIN, null, false);
			Main.game.getNpc(Lyssieth.class).incrementAffection(Main.game.getPlayer(), 25);
			Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 10);
			Main.game.getNpc(DarkSiren.class).incrementAffection(Main.game.getPlayer(), 10);
			Main.game.getNpc(Arthur.class).incrementAffection(Main.game.getPlayer(), 10);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.firstReactionLiberate, true);
			if(Main.game.getNpc(DarkSiren.class).getAffection(Main.game.getPlayer())<0) {
				Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getPlayer(), 0);
			}
		}
	},
	
	MAIN_3_ELIS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "Пункт назначения Элис";
		}

		@Override
		public String getDescription() {
			return "Лиссиет рассказала вам, что для того, чтобы победить старшую лилин-пегатаур Лунетт, вам нужно заручиться помощью Миноталлис - лилин, которая правит городом Элис."
					+ " Мераксис также упоминала о помощи юко, но это может подождать до прибытия в Элис.";
		}
		@Override
		public String getCompletedDescription() {
			return "Лиссиет рассказала вам, что для того, чтобы победить старшую лилин-пегатаур Лунетт, вам нужно заручиться помощью Миноталлис - лилин, которая правит городом Элис."
					+ " С этой целью вы впервые покинули Доминион...";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_B_MEETING_MERAXIS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "К красному дракону";
		}

		@Override
		public String getDescription() {
			return "Когда вы покидали Доминион, к вам обратилась Мераксис, которая сказала, чтобы вы встретились с ней в таверне «Красный дракон» в Элис, который, судя по всему, находится недалеко от восточных ворот города."
					+ " Мераксис также сказала, что договорится о встрече с Миноталлис, когда вы прибудете, и что она обеспечит вас жильем.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы встретили Мераксис в таверне «Красный дракон», где ей удалось найти для вас жилье в виде арендованной комнаты на втором этаже таверны.";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_C_MEETING_MINOTALLYS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "Встреча с Миноталлис";
		}

		@Override
		public String getDescription() {
			return "Мераксис организовала для вас встречу с Миноталлис по поводу угрозы, которую Лунетт представляет для города Элис."
					+ " Сообщите Мераксис, что вы готовы к встрече в любое время между [units.time(9)]-[units.time(18)].";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы с Мераксис отправились в ратушу Элис на встречу с Миноталлис, где также встретились с ее личным помощником Арионом."
					+ " Миноталлис отрицает текущее положение дел на Фолойских полях и заявила, что будет действовать только в том случае, если город Фемискира окажется под угрозой.";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_D_TO_THEMISCYRA(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "Путь в Фемискиру";
		}

		@Override
		public String getDescription() {
			return "Вы согласились отправиться в Фемискиру вместе с Мераксис и выяснить, угрожает ли городу армия Лунетты.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы отправились в Фемискиру вместе с Мераксис, но, добравшись до города, обнаружили, что он разрушен армией Лунетты!";
		}
		@Override
		public void applySkipQuestEffects() {
			if(Main.game.getWorlds().get(WorldType.WORLD_MAP).getCell(PlaceType.getPlaceTypeFromId("innoxia_fields_themiscyra"))==null) {
				Main.game.getWorlds().get(WorldType.WORLD_MAP).getCell(11, 32).getPlace().setPlaceType(PlaceType.getPlaceTypeFromId("innoxia_fields_themiscyra"));
			}
		}
	},
	
	MAIN_3_E_THEMISCYRA_ATTACK(QuestType.MAIN, 1, 250) {
		@Override
		public String getName() {
			return "Спасти королеву";
		}
		@Override
		public String getDescription() {
			return "Отделившись от Мераксис, вы объединились с амазонкой девочкой-коровкой по имени Аурокарис."
					+ " Вам нужно отправиться в путешествие по Фемискире и найти Мераксис и Урсу, королеву амазонок, которая должна находиться во дворце.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вместе с Аурокарисис вы прошли через Фемискиру и нашли Мераксис и Урсу на площади перед дворцом."
					+ " После встречи с Лунексис, лидером армии Лунетты, Мераксис телепортировала вас пятерых обратно в ратушу Элис, где Миноталлис окончательно убедилась что Лунетта представляет угрозу для Элис.";
		}
		@Override
		public void applySkipQuestEffects() {
			if(Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_elis_town")).getCell(PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_amazon_camp"))==null) {
				Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_elis_town")).getCell(10, 20).getPlace().setPlaceType(PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_amazon_camp"));
			}
			Main.game.getNpc(Ursa.class).setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_amazon_camp"), true);
			Main.game.getNpc(Aurokaris.class).setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_amazon_camp"), true);
			Main.game.getNpc(Lunexis.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, true);
		}
	},
	
	MAIN_3_F_PREPARING_ELIS(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "Помощь от «МЕЧ»";
		}
		@Override
		public String getDescription() {
			return "Поскольку Лунетта планирует напасть на Элис в ближайшем будущем, вы сказали Миноталлис, что поможете привести оборону города в порядок."
					+ " Вам нужно отправиться на станцию энфорсеров в Элис и попросить помощи у группы энфорсеров «МЕЧ».";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы отправились на станцию энфорсеров в Элис и попросили группу энфорсеров «МЕЧ», помочь привести оборону города в порядок.";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_G_SWORD_SCAPEGOAT(QuestType.MAIN, 1, 25) {
		@Override
		public String getName() {
			return "Козел отпущения МЕЧа";
		}
		@Override
		public String getDescription() {
			return "«МЕЧ» энфорсеры сказали что сначала вы должны помочь им."
					+ " Вам предстоит участвовать в операции по остановке дочери старшей лилин, если их узнают, вы должны будете помочь им."
					+ " Вы должны встретиться с ними на станции энфорсеров во вторник вечером, после [units.time(17)], чтобы начать операцию.";
		}
		@Override
		public String getCompletedDescription() {
			return "Во вторник днем вы встретились с сотрудниками МЕЧа, чтобы принять участие в операции по задержанию дочери старшей лилин.";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_H_SWORD_MISSION(QuestType.MAIN, 25, 250) {
		@Override
		public String getName() {
			return "Остановить суккуба";
		}
		@Override
		public String getDescription() {
			return "Вам нужно оставаться с энфорсерам МЕЧа, пока они зачищают штаб-квартиру суккубов."
					+ " Вам не нужно сражаться, но энфорсеры могут быть благодарны, если вы окажете им помощь.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы работали с энфорсерами МЕЧа, чтобы очистить штаб-квартиру суккубов."
					+ " Хотя ей удалось сбежать, вы положили решительный конец ее незаконной деятельности по порабощению.";
		}
		@Override
		public void applySkipQuestEffects() {
			// TODO
		}
	},
	
	MAIN_3_I_ARION_REPORT(QuestType.MAIN, 1, 25) {//TODO
		@Override
		public String getName() {
			return "Сообщить Миноталлис";
		}
		@Override
		public String getDescription() {
			return "[style.italicsBad(На данный момент это конец основного квеста, ждем обновлений!)]"
					+ "<br/>Теперь, когда энфорсеры МЕЧа работают над подготовкой обороны города, вам нужно вернуться в ратушу, чтобы сообщить об этом Миноталлис.";
		}
		@Override
		public String getCompletedDescription() {
			return "-";
		}
	},
	
	MAIN_3_J_TODO(QuestType.MAIN, 1, 25) {//TODO
		@Override
		public String getName() {
			return "";
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String getCompletedDescription() {
			return "-";
		}
	},
	

	// Side Quests:

	SIDE_UTIL_COMPLETE(QuestType.SIDE, 1, 0) {
		@Override
		public String getName() {
			return "Задание Выполнено!";
		}

		@Override
		public String getDescription() {
			return "Задание выполнено!";
		}

		@Override
		public String getCompletedDescription() {
			return "Задание выполнено!";
		}
	},
	
	SIDE_DISCOVER_ALL_ITEMS(QuestType.SIDE, 1, 100) {
		@Override
		public String getName() {
			return "Коллекционер";
		}

		@Override
		public String getDescription() {
			return "В этом новом мире есть много любопытных предметов. Вам интересно, сможете ли вы найти их все...";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы обнаружили все предметы, которые только можно найти!";
		}
	},

	SIDE_DISCOVER_ALL_RACES(QuestType.SIDE, 1, 100) {
		@Override
		public String getName() {
			return "Коллекционер";
		}

		@Override
		public String getDescription() {
			return "Кажется, в этом мире появилось множество новых странных рас. Вам интересно, сможете ли вы открыть их все...";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы открыли все расы, которые только можно найти!.";
		}
	},
	
	
	// For when you discover your first essence:
	
	SIDE_ENCHANTMENTS_LILAYA_HELP(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Попросите Лилайю о помощи";
		}

		@Override
		public String getDescription() {
			return "Недавно вы почувствовали, как в ваше тело проникает странная сила, и хотя она, похоже, не оказала никакого явного воздействия, вам, вероятно, следует пройти обследование."
					+ " Лилайя наверняка знает больше, так что, возможно, вам стоит пойти и поговорить с ней об этом.";
		}

		@Override
		public String getCompletedDescription() {
			return "Лилайя сообщила вам, что вы способны собирать «эссенции» из чужой магической ауры."
					+ " Кажется, она немного обеспокоена тем, что вы способны на это, ведь обычно только Лилин могут собирать эссенции таким образом...";
		}
	},

	// For the first time you get pregnant:
	
	SIDE_PREGNANCY_CONSULT_LILAYA(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Лилайя знает лучше";
		}

		@Override
		public String getDescription() {
			return "Не может быть... Вы беременны? Вы <b>беременны</b>! Конечно, Лилайя знает, что делать?!";
		}

		@Override
		public String getCompletedDescription() {
			return "Лилайе удалось успокоить вас и заверить, что беременность в этом мире - не такая уж большая проблема, как дома.";
		}
	},
	
	SIDE_PREGNANCY_LILAYA_THE_MIDWIFE(QuestType.SIDE, 1, 20) {
		@Override
		public String getName() {
			return "Акушерка Лилайя";
		}

		@Override
		public String getDescription() {
			return "Лилайя сказала, что сможет помочь вам родить, когда вы будете готовы. Вам нужно будет подождать, пока ваш живот закончит расти, и тогда вы сможете пойти к Лилайе, чтобы родить.";
		}

		@Override
		public String getCompletedDescription() {
			return "Лилайя помогла вам родить. Она сказала, что если вы снова забеременеете, она всегда сможет вам помочь.";
		}
	},
	
	// When getting eggs implanted in you for the first time:
	
	SIDE_INCUBATION_WAITING(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Живой инкубатор";
		}
		@Override
		public String getDescription() {
			return "Нет никаких сомнений: в ваше тело имплантировали кладку яиц! Вам ничего не остается, кроме как ждать, пока они созреют, а затем отложить их...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы ждали, пока созреют яйца, имплантированные в ваше тело, а затем успешно отложили и вылупили их!";
		}
	},
	
//	SIDE_INCUBATION_LILAYA_HELP(QuestType.SIDE, 1, 20) {
//		@Override
//		public String getName() {
//			return "Egg-laying assistance";
//		}
//		@Override
//		public String getDescription() {
//			return "Lilaya said that she'd be able to help you lay your eggs whenever you're ready. You're going to need to wait until they're ready to be hatched, then you can go and see Lilaya to lay them.";
//		}
//		@Override
//		public String getCompletedDescription() {
//			return "Lilaya helped you to lay your eggs. She said that if ever you get implanted with eggs again, she can always help out.";
//		}
//	},
	
	// Getting a slaver license:
	
	SIDE_SLAVER_NEED_RECOMMENDATION(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Рекомендательное письмо";
		}

		@Override
		public String getDescription() {
			return "Поинтересовавшись, как получить лицензию рабовладельца в здании управления рабством, вы узнали, что сначала вам понадобится рекомендательное письмо. Лилайя должна помочь с этим.";
		}

		@Override
		public String getCompletedDescription() {
			return "Лилайя дала вам рекомендательное письмо и, более того, предложила поселить ваших рабов в своем особняке.";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getDialogueFlags().values.add(DialogueFlagValue.finchIntroduced);
		}
	},
	
	SIDE_SLAVER_RECOMMENDATION_OBTAINED(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Предъявите письмо";
		}

		@Override
		public String getDescription() {
			return "Теперь, когда вы получили рекомендательное письмо от Лилайи, вам следует вернуться в здание администрации рабства на аллее рабов и вручить его [finch.name].";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы представили рекомендательное письмо [finch.name] и после уплаты пошлины вы получали лицензию рабовладельца!";
		}
		@Override
		public void applySkipQuestEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.SLAVER_LICENSE), false));
		}
	},
	
	// Accommodation:
	
	SIDE_ACCOMMODATION_NEED_LILAYAS_PERMISSION(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Хозяйка Лилайя";
		}

		@Override
		public String getDescription() {
			return "В особняке Лилайи полно пустых комнат, которые можно использовать как жилье для гостей. Спросите у нее, не могли бы вы использовать их для размещения своих друзей и родственников.";
		}

		@Override
		public String getCompletedDescription() {
			return "Лилайя разрешила вам использовать пустующие комнаты для размещения друзей и родственников, но при условии, что вы будете оплачивать все расходы.";
		}
	},

	// Doll sotrage:
	
	SIDE_DOLL_STORAGE_ASK_FOR_SPACE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Куда деть кукол?";
		}
		@Override
		public String getDescription() {
			return "Хотя в особняке Лилайи полно пустых комнат, в которых можно хранить кукол, лучше спросить у нее разрешения, прежде чем принести их домой...";
		}
		@Override
		public String getCompletedDescription() {
			return "Лилайя разрешила вам использовать пустые комнаты для хранения кукол, которых вы купите.";
		}
	},
	
	// Other:
	
	SIDE_HYPNO_WATCH_VICKY(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Заказ в «Магические искусства»";
		}

		@Override
		public String getDescription() {
			return "Артур сообщил вам, что Зараникс поручил ему найти магический метод изменения сексуальной ориентации человека."
					+ " Хотя он заверил вас, что не намерен сам использовать такой предмет, Артур выразил заинтересованность в завершении исследования,"
						+ " и попросил вас принести специальный заказ из магазина «Магические искусства» в Торговой галерее.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы забрали пакет из «Магические искусства» и принесли его Артуру.";
		}
	},
	
	SIDE_HYPNO_WATCH_TEST_SUBJECT(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Подопытный";
		}

		@Override
		public String getDescription() {
			return "После того как Лилайя, следуя инструкциям Артура, зачаровала часы, она спросила, можно ли испытать их на вас...";
//					+ " You could either offer yourself, or, if you own any slaves, offer one of those to Arthur instead.";
		}

		@Override
		public String getCompletedDescription() {
			return "Гипночасы, похоже, сработали, хотя Лилайя прекратила испытание до того, как оно возымело постоянный эффект."
					+ " Она предупредила, что они будуи оказывать сильное развращающее воздействие на разум того, на кого нацелены, и, прежде чем передать их вам, хорошенько расколдовала их.";
		}
	},
	
	
	LIGHTNING_SPELL_1_PAYMENT(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Цена силы";
		}
		@Override
		public String getDescription() {
			return "Показав Артуру шар молний, который вы нашли в хранилище энфорсеров, вы получили ответ, что сможете узнать секреты заклинаний молний, заключенных в нем."
					+ " Хотя получаемые заклинания будут более мощными, чем те, на которые способен шар,"
						+ " Артур объяснил вам, что такое извлечение не только потребует огромного количества магических эссенций, но и навсегда лишит шар присущих ему заклинаний."
					+ "<br/>Когда вы будете готовы, дайте Артуру шар магической молнии и позвольте ему извлечь из вашей ауры 500 магических эссенций.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы отдали Артуру шар магической молнии, который нашли на складе энфорсеров, а также позволили ему извлечь из вашей ауры 500 магических эссенций."
					+ " В обмен на это вам обещано, что скоро вы получите мощное заклинание магической молнии.";
		}
	},
	
	LIGHTNING_SPELL_2_WAITING(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Извлечение молнии";
		}
		@Override
		public String getDescription() {
			return "Артур сообщил вам, что потребуется некоторое время, чтобы извлечь секреты магической молнии из шара."
					+ " Вам придется вернуться к нему через две недели, чтобы узнать о результатах его исследований...";
		}
		@Override
		public String getCompletedDescription() {
			return "Артур с восторгом сообщил вам, что ему не только удалось извлечь из глобуса секреты двух заклинаний молний,"
					+ " он также смог перевести его оставшуюся силу в кристалл меньшего размера, который он прикрепил к кольцу для вас.";
		}
	},
	
	
	
	// Angry Harpies:
	
	HARPY_PACIFICATION_ONE(QuestType.SIDE, 6, 25) {
		@Override
		public String getName() {
			return "Гнезда в хаосе";
		}

		@Override
		public String getDescription() {
			return "Инфорсер сообщил вам, что гнезда гарпий сейчас очень опасны."
					+ " После дальнейших расспросов вы узнали, что того, кто сумеет успокоить трех главных матриархов, ждет крупная награда.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вам удалось вернуть контроль одному гнезду гарпий!";
		}
	},
	HARPY_PACIFICATION_TWO(QuestType.SIDE, 6, 25) {
		@Override
		public String getName() {
			return "Минус одно, два осталось";
		}

		@Override
		public String getDescription() {
			return "Вам удалось подчинить себе одну из матриархов, но осталось еще две!";
		}

		@Override
		public String getCompletedDescription() {
			return "Вам удалось вернуть контроль над двумя гнездами гарпий!";
		}
	},
	HARPY_PACIFICATION_THREE(QuestType.SIDE, 6, 25) {
		@Override
		public String getName() {
			return "Осталась одна матриарх";
		}

		@Override
		public String getDescription() {
			return "Вам удалось подчинить себе двух матриархов, но осталась еще одна!";
		}

		@Override
		public String getCompletedDescription() {
			return "Вам удалось вернуть контроль всем трем крупных гнездам гарпий!";
		}
	},
	HARPY_PACIFICATION_REWARD(QuestType.SIDE, 6, 50) {
		@Override
		public String getName() {
			return "Гарпия "+(Main.game.getPlayer().isFeminine()?"королева":"король");
		}

		@Override
		public String getDescription() {
			return "Вернитесь на пост энфорсеров, чтобы сообщить о своем успехе.";
		}

		@Override
		public String getCompletedDescription() {
			return "После того как вы сообщили энфорсерам, что умиротворили все три крупных гнезда гарпий, они возобновили регулярное патрулирование, и теперь по гнездам гарпий можно путешествовать!";
		}
	},
	
	
	
	// Slime Queen:
	
	SLIME_QUEEN_ONE(QuestType.SIDE, 10, 25) {
		@Override
		public String getName() {
			return "Проблемные слизни";
		}

		@Override
		public String getDescription() {
			return "Когда вы только прибыли в Покорность, энфорсер по имени Клэр сообщила вам о ситуации, сложившейся в туннелях."
					+ " Судя по всему, растет число слизней, которые нападают на невинных путешественников и превращают их в новых слизней."
					+ " Если вы сможете предоставить какую-либо информацию о том, откуда берутся эти агрессивные слизни, вы сможете получить вознаграждение в размере пяти тысяч пламен."
					+ "<br/>"
					+ "<p style='text-align:center;'><i>Вам нужно победить слизней в <b>туннелях Покорности</b> чтобы узнать больше подробностей.</i></p>";
		}

		@Override
		public String getCompletedDescription() {
			return "Один из слизней, с которым вы столкнулись в туннелях, рассказал вам, что приказ превращать людей им дала некая «Королева слизи».";
		}
	},
	
	SLIME_QUEEN_TWO(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Отчитаться";
		}

		@Override
		public String getDescription() {
			return "Вам необходимо передать информацию о «Королеве слизи» в один из постов энфорсеров в Покорности.";
		}

		@Override
		public String getCompletedDescription() {
			return "Энфорсер, к которому вы обратились, сказал вам, что до них доходили слухи о Королеве слизи, но в Покорности не было найдено никаких следов этого существа."
					+ " Они предложили вам посетить пещеры летучих мышей и пообещали дополнительно 20 тысяч пламени, если вы сможете отыскать эту королеву и положить конец её проискам.";
		}
	},
	
	SLIME_QUEEN_THREE(QuestType.SIDE, 15, 25) {
		@Override
		public String getName() {
			return "Поиск королевы слизней";
		}

		@Override
		public String getDescription() {
			return "Спуститесь в пещеры летучих мышей и разыщите королеву слизней, о которой ходят слухи.";
		}

		@Override
		public String getCompletedDescription() {
			return "В центре озера слизи вы обнаружили логово Королевы слизней!";
		}
	},
	
	SLIME_QUEEN_FOUR(QuestType.SIDE, 20, 50) {
		@Override
		public String getName() {
			return "Противостояние королеве";
		}

		@Override
		public String getDescription() {
			return "Поднимитесь на башню и найдите королеву слизней.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы нашли Королеву слизней на вершине башни.";
		}
	},
	
	SLIME_QUEEN_FIVE_SUBMIT(QuestType.SIDE, 1, 25) {
		@Override
		public String getName() {
			return "Помочь королеве";
		}

		@Override
		public String getDescription() {
			return "Вы решили помочь Королеве слизи в ее планах по превращению населения Покорности в слизней.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы решили помочь Королеве слизней в ее планах и согласились обмануть энфорсеров, чтобы они поверили, что она больше не представляет угрозы!";
		}
	},
	
	SLIME_QUEEN_SIX_SUBMIT(QuestType.SIDE, 1, 200) {
		@Override
		public String getName() {
			return "Итоговый отчет";
		}

		@Override
		public String getDescription() {
			return "Доложите Клэр, что королева слизней больше не будет проблемой.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы сообщили Клэр, что королева слизней больше не представляет угрозы, и получили награду в двадцать тысяч пламени."
				+ " Теперь ваша королева в безопасности от расследования энфорсеров, и это лишь вопрос времени, когда вся Покорность станет раем для слизней!";
		}
	},
	
	SLIME_QUEEN_FIVE_CONVINCE(QuestType.SIDE, 1, 25) {
		@Override
		public String getName() {
			return "Убедить королеву";
		}

		@Override
		public String getDescription() {
			return "Вы решаете убедить королеву слизней отказаться от своих планов.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы убедили Кэтрин отказаться от плана превращения всех жителей Покорности в слизней.";
		}
	},
	
	SLIME_QUEEN_SIX_CONVINCE(QuestType.SIDE, 1, 200) {
		@Override
		public String getName() {
			return "Итоговый отчет";
		}

		@Override
		public String getDescription() {
			return "Доложите Клэр, что королева слизней больше не будет проблемой.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы сообщили Клэр, что королева слизней больше не представляет угрозы, и получили награду в двадцать тысяч пламени.";
		}
	},
	
	SLIME_QUEEN_FIVE_FORCE(QuestType.SIDE, 1, 25) {
		@Override
		public String getName() {
			return "Принудить королеву";
		}

		@Override
		public String getDescription() {
			return "Заставьте королеву слизней отказаться от своих планов.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы заставили Кэтрин отказаться от плана превращения всех жителей Покорности в слизней.";
		}
	},
	
	SLIME_QUEEN_SIX_FORCE(QuestType.SIDE, 1, 200) {
		@Override
		public String getName() {
			return "Итоговый отчет";
		}

		@Override
		public String getDescription() {
			return "Доложите Клэр, что королева слизней больше не будет проблемой.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы сообщили Клэр, что королева слизней больше не представляет угрозы, и получили награду в двадцать тысяч пламени.";
		}
	},
	
	
	// Teleporting:
	
	TELEPORTING_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Побег со склада";
		}

		@Override
		public String getDescription() {
			return "Случайно телепортировавшись на склад подразделения энфорсеров «МЕЧ», вы с Клэр должны избежать обнаружения и совершить побег.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вам и Клэр удалось сбежать со склада МЕЧа";
		}
	},

	TELEPORTING_CAUGHT(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Выдержать испытания";
		}

		@Override
		public String getDescription() {
			if(Main.game.isNonConEnabled()) {
				return "Потерпев поражение от энфорсеров на складе МЕЧа, вы были приговорены к заточению в колодках общего пользования на Аллее рабов. Выдержите это испытание, пока Клэр не придет вас спасти...";
			} else {
				return "Потерпев поражение от энфорсеров на складе МЕЧа, вы оказались заперты в камере штаб-квартиры энфорсеров. Вам придется ждать, пока Клэр не придет, чтобы спасти вас...";
			}
		}

		@Override
		public String getCompletedDescription() {
			if(Main.game.isNonConEnabled()) {
				return "Потерпев поражение от энфорсеров на складе МЕЧа, вам пришлось выдержать несколько часов заточния в колодках общего пользования, в переулке рабов, прежде чем появилась Клэр, чтобы освободить вас.";
			} else {
				return "Потерпев поражение от Энфорсеров на складе МЕЧа, вам пришлось несколько часов просидеть взаперти в камере штаб-квартиры Энфорсеров, прежде чем появилась Клэр и освободила вас.";
			}
		}
	},
	
	
	// Daddy:
	
	DADDY_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Встречая [daddy.name]";
		}

		@Override
		public String getDescription() {
			return "Роза попросила вас посетить [daddy.name], чтобы убедить [daddy.herHim] оставить Лилайю в покое."
					+ " ([daddy.He] можно встретить только в квартире дома демонов между "+Units.time(LocalTime.of(18, 00))+" и "+Units.time(LocalTime.of(21, 00))+".)";
		}

		@Override
		public String getCompletedDescription() {
			return "По просьбе Розы вы встретились с [daddy.name] в квартире дома демонов.";
		}
	},
	
	DADDY_MEETING(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Ужин с [daddy.name]";
		}

		@Override
		public String getDescription() {
			return "[daddy.Name] настаивает на объяснении мотивов за ужином. Вам придется либо принять [daddy.her] предложение, либо прямо отказать [daddy.herHim] и настоять на том, чтобы [daddy.she] оставил Лилайю в покое.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы дали [daddy.name] свой ответ, в ответ на [daddy.her] просьбу пригласить вас на ужин.";
		}
	},
	
	DADDY_REFUSED(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "[daddy.Name] отказано";
		}

		@Override
		public String getDescription() {
			return "Вы сказали [daddy.name] что вам совсем неинтересно пойти поесть с [daddy.herHim], и чтобы [daddy.sheIs] больше никогда не беспокоил Лилайю.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы сказали [daddy.name] что вы совсем не заинтересованы в том, чтобы пообедать с [daddy.herHim], и чтобы [daddy.sheIs] никогда больше не беспокоил Лилайю.";
		}
	},
	
	DADDY_REFUSED_2(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "[daddy.Name] отказано";
		}

		@Override
		public String getDescription() {
			return "Вы сказали [daddy.name] что вы не заинтересованы в том, чтобы убедить Лилайю встретиться с [daddy.herHim], и что [daddy.she] никогда не должен мешать вашим [lilaya.relation(pc)].";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы сказали [daddy.name] что вы не заинтересованы в том, чтобы убедить Лилайю встретиться с [daddy.herHim], и что [daddy.she] никогда не должен мешать вашим [lilaya.relation(pc)].";
		}
	},
	
	DADDY_ACCEPTED(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Убеждение Лилайи";
		}

		@Override
		public String getDescription() {
			return "Вы согласились убедить Лилайю встретиться с [daddy.name] на ужин, а затем помочь убедить ее попросить Лисиет встретиться с [daddy.herHim].";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы убедили Лилайю встретиться с [daddy.name] на ужин, при условии, что вы пойдете с ней. ";
		}
	},
	
	DADDY_LILAYA_MEETING(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Свидание Лилайи с [daddy.name]";
		}

		@Override
		public String getDescription() {
			return "Лилайя согласилась пойти на ужин с [daddy.name], так что теперь вам остается только сопровождать ее и следить за тем, чтобы вечер прошел гладко.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы пошли с Лилайей на встречу с [daddy.name] для ужина, и хотя у нее были плохие новости для [daddy.herHim] Что касается романтических предпочтений Лисиет, то ей, похоже, [daddy.herHim] достаточно понравился...";
		}
	},
	
	
	// Buying Brax:
	
	BUYING_BRAX_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Коллекция парфюма";
		}

		@Override
		public String getDescription() {
			return "Кэнди сказала, что рассмотрит возможность продажи [brax.name] вам, но прежде чем она даст вам определенный ответ, она хочет, чтобы вы сходили и забрали ее заказ духов из магазина «Секрет суккубы» в торговом центре.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы оплатили и забрали заказаные Кэнди духи из «Секрет суккубы».";
		}
	},
	
	BUYING_BRAX_DELIVER_PERFUME(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Доставка парфюма";
		}

		@Override
		public String getDescription() {
			return "Теперь, когда вы получили флаконы духов, вам нужно доставить их Кэнди в штаб-квартиру энфорсеров.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы доставили ей флаконы духов.";
		}
	},
	
	BUYING_BRAX_LOLLIPOPS(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Контрабанда леденцов";
		}

		@Override
		public String getDescription() {
			return "Кэнди сказала, что готова продать вам Бракса, но ей нужно подумать, сколько [brax.sheIs] стоит."
					+ " Она сказала, что у нее будет для вас цена после того, как вы принесете коробку контрабандных леденцов с КПП энфорсеров в Гнездах гарпий.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы получили леденцы на КПП энфорсеров в Гнездах гарпий.";
		}
	},
	
	BUYING_BRAX_DELIVER_LOLLIPOPS(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Леденцы для Кэнди";
		}

		@Override
		public String getDescription() {
			return "Теперь, когда коробка с контрабандными леденцами у вас в руках, вы должны вернуть их Кэнди в штаб-квартиру энфорсеров.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы передали коробку с контрабандными леденцами Кэнди, которая, похоже, не обратила внимания на предупреждения которыми покрыта вся коробка.";
		}
	},
	
	BUYING_BRAX_LIPSTICK(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Волчий вес в губной помаде";
		}

		@Override
		public String getDescription() {
			return "Кэнди сказала, что Бракс слишком ценен, чтобы продавать его за деньги, но она готова отдать [brax.herHim] вам в обмен на нечто столь же ценное: коробку лимитированных помад под маркой «Сто поцелуев»."
					+ " Судя по всему, Кэнди выяснила, где находится одна из последних коробок, оставшихся для продажи, - магазин в торговом центре под названием «Закуски Ральфа».";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы забрали у Ральфа коробку с «Сотней поцелуев».";
		}
	},
	
	BUYING_BRAX_DELIVER_LIPSTICK(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "У волчьей двери";
		}

		@Override
		public String getDescription() {
			return "Теперь, когда вы получили коробку с «Сотней поцелуев», осталось только доставить ее Кэнди в обмен на право собственности на [brax.name].";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы передали Кэнди коробку с «Сотней поцелуев» и наконец-то получили свой приз - право собственности на [brax.name].";
		}
	},

	
	// Vengar:
	
	VENGAR_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Крысиные норы";
		}
		@Override
		public String getDescription() {
			return "Вы согласились помочь Акселю разобраться с Венгаром, который, судя по всему, является лидером самой крупной и опасной банды Покорности. Вы можете отправиться прямо в его убежище, «Крысиные норы», или сначала обратиться за помощью к Клэр.";
		}
		@Override
		public String getCompletedDescription() {
			return "Используя пароль, который дал вам Аксель, вы смогли проникнуть в убежище Венгара - в Крысиные норы.";
		}
	},
	
	VENGAR_ONE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Встреча с Венгаром";
		}
		@Override
		public String getDescription() {
			return "Чтобы найти Венгара, вам нужно найти главный зал и быть там между часами "+Units.time(LocalDateTime.of(1, 1, 1, 6, 0))+" и "+Units.time(LocalDateTime.of(1, 1, 1, 22, 0))+".";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы обнаружили Венгара, сидящего на троне в главном зале, и, подойдя к нему, получили выбор: либо присоединиться к его банде, либо сразиться с его телохранителями девочками-крысами.";
		}
	},
	
	VENGAR_TWO_CONFLICT(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Конфликт";
		}
		@Override
		public String getDescription() {
			return "Решив бросить вызов Венгару, вы должны победить его в бою, чтобы утвердить свое господство над его бандой.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам удалось победить Венгара, но прежде чем вы смогли предпринять какие-либо дальнейшие действия, «МЕЧ» начали свой рейд на Крысиные норы.";
		}
	},
	
	VENGAR_TWO_COOPERATION(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Покорность Акселя";
		}
		@Override
		public String getDescription() {
			return "Венгар рассказал, что уже давно хочет сосредоточиться на своем законном бизнесе по производству рома, но не может просто оставить Акселя в покое, чтобы не потерять уважение со стороны своей банды."
					+ " В обмен на прекращение вымогательства вы согласились уговорить Акселя прийти и продемонстрировать свою покорность Венгару.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам удалось убедить Акселя отправиться в Крысиные болота и продемонстрировать Венгару свою покорность."
					+ " Сопровождая его туда, вы смогли внести свой вклад и повлиять на то, что случилось с мальчиком-аллигатором.";
		}
	},
	
	VENGAR_TWO_ENFORCERS(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Рейд";
		}
		@Override
		public String getDescription() {
			return "Убедившись, что Венгар находится в Крысиных болотах, вы активировали резонансный камень, давая сигнал ожидающим энфорсерам МЕЧа начать свой рейд.";
		}
		@Override
		public String getCompletedDescription() {
			return "Энфорсеры МЕЧа совершили успешный рейд в Крысиные норы и смогли задержать Венгара.";
		}
	},
	
	VENGAR_THREE_COOPERATION_END(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Конец Венгара";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда [axel.name] продемонстрировал [axel.her] покорность Венгару, остается только вернуться в казино «Берлога»...";
		}
		@Override
		public String getCompletedDescription() {
			return "После того как [axel.name] продемонстрировал [axel.her] покорность Венгару, группа энфорсеров МЕЧа явилась на рейд в Крысиные Ущелья и арестовала мальчика-крысу!";
		}
	},

	VENGAR_THREE_END(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Вернуться к Акселю";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда с Венгаром разобрались, вам нужно вернуться к Акселю и сообщить ему о случившемся.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы вернулись к Акселю и рассказали ему, как будет обстоять дело впредь.";
		}
	},
	
	VENGAR_OPTIONAL_CLAIRE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Помощь Клэр";
		}
		@Override
		public String getDescription() {
			return "Решив, что лучше всего будет сообщить о ситуации Клэр, вы спросили ее, не могут ли энфорсеры чем-то помочь."
					+ " Судя по всему, команда МЕЧа уже готова к налету на Крысиные норы, но прежде чем начать атаку, им нужно знать, что Венгар находится внутри."
					+ " Клэр дала вам резонансный камень, который нужно активировать, если вы хотите, чтобы они поддержали вас, когда окажетесь внутри.";
		}
		@Override
		public String getCompletedDescription() {
			return "Решив, что лучше всего будет сообщить о ситуации Клэр, вы спросили ее, не могут ли энфорсеры чем-то помочь."
					+ " Судя по всему, команда МЕЧа уже готова к налету на Крысиные норы, но прежде чем начать атаку, им нужно знать, что Венгар находится внутри."
					+ " Клэр дала вам резонансный камень, который нужно активировать, если вы хотите, чтобы они поддержали вас, когда окажетесь внутри.";
		}
	},

	// Wes:

	WES_FAIL(QuestType.SIDE, 1, 0) {
		@Override
		public String getName() {
			return "Упущенная возможность";
		}
		@Override
		public String getDescription() {
			return "После того как вы сказали Уэсли, что не намерены помогать ему в расследовании, мальчик-лис исчез, и вы можете быть уверены, что он больше никогда не попытается обратиться к вам за помощью...";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},
	
	WES_START(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Требуется помощь";
		}
		@Override
		public String getDescription() {
			return "Во время путешествия по Доминиону к вам обратился таинственный энфорсер МЕЧа под прикрытием, который попросил вас о помощи."
					+ " Он хочет, чтобы вы встретились с ним возле антикварного магазина в торговом центре между [units.time(13)]-[units.time(14)].";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы согласились помочь Уэсли расследовать дело его начальника.";
		}
	},

	WES_1(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "В поисках Элль";
		}
		@Override
		public String getDescription() {
			return "Уэс рассказал вам, что его старший офицер, [elle.name] (или сокращенно «Элль»), берет с собой очки ночного видения, отправляясь на подозрительно длинные обеды по средам, и всегда возвращается с мокрыми ботинками."
					+ " Вам нужно найти ее и записать все улики на магическое записывающее устройство, которое дал вам Уэс..."
					+ "<br/>Она берет [style.colourOrange(необычно длинные перерывы на обед)]."
					+ "<br/>Она делает это только по [style.colourOrange(Средам)]."
					+ "<br/>Она берет набор [style.colourOrange(очков ночного видения)] с собой."
					+ "<br/>После этого ее [style.colourOrange(обувь часто мокрая)] и иногда содержат следы некоторых видов [style.colourOrange(светящихся остатков на них)]."
					+ "<br/>[style.italicsMinorGood(Вы можете получить помощь у Лилайи.)]";
		}
		@Override
		public String getCompletedDescription() {
			return "Выяснив, что Элль ведет свои теневые дела в Пещерах летучих мышей, вам удалось собрать доказательства того, что она продавала оружие опасной преступной группировке.";
		}
	},

	WES_2(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Использование доказательств";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда ваше магическое записывающее устройство содержит уличающие доказательства коррупции Элль, у вас есть два варианта, что с ним делать."
					+ " Вы можете передать его в качестве анонимной наводки Клэр или Кэнди, или, если хотите, предать Уэса и встать на сторону Эль,"
						+ " Вы можете подождать у штаб-квартиры энфорсеров между [units.time(16)] и [units.time(18)] и раскрыть все [elle.race], когда она уйдет с работы.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы воспользовались собранными вами уликами, чтобы положить конец всей этой истории с Уэсом и Элль.";
		}
	},

	WES_3_WES(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "На стороне Уэса";
		}
		@Override
		public String getDescription() {
			return "Вы решили выполнить просьбу Уэса и передать улики энфорсерам в качестве анонимной наводки."
					+ " Вам следует подождать хотя бы неделю, пока все уляжется, а затем попросить о встрече с Уэсом в штаб-квартире энфорсеров между [units.time(9)]-[units.time(17)].";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы встали на сторону Уэса и, анонимно передав улики, встретились с мальчиком-лисом в штаб-квартире энфорсеров, чтобы узнать, что он получил повышение и занял место Элль."
					+ " В награду за помощь он предоставил вам доступ в отдел заявок штаба энфорсеров."
					+ " Теперь вы можете свободно посещать Уэса в штаб-квартире энфорсеров в часы [units.time(9)]-[units.time(17)].";
		}
	},

	WES_3_ELLE(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "На стороне Элль";
		}
		@Override
		public String getDescription() {
			return "Ты решил предать Уэса и раскрыть все Эль."
					+ " Благодарная за неожиданную поддержку, [elle.race] пообещала вознаградить вас, если вы вернетесь в штаб-квартиру энфорсеров по истечении хотя бы недели...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы встали на сторону Элль и, вернувшись на встречу с ней в штаб-квартиру энфорсеров, обнаружили, что Уэс попал в рабство."
					+ " В благодарность за вашу поддержку [elle.race] предоставила вам доступ в отдел заявок штаба энфорсеров."
					+ " Теперь вы можете свободно посещать Элль (и Уэса) в штаб-квартире Энфорсера в часы [units.time(9)]-[units.time(17)].";
		}
	},

	
	// Rebel Base for HLF Quest
	
	REBEL_BASE_HANDLE_REFUSED(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Жми на рычаг!";
		}
		@Override
		public String getDescription() {
			return "В пещерах летучих мышей вы нашли странную рукоятку. Кто знает, для чего она нужна или что она делает.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы потянули за ручку вопреки здравому смыслу.";
		}
	},

	REBEL_BASE_PASSWORD_PART_ONE(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Тяни рычаг, узнай секрет";
		}
		@Override
		public String getDescription() {
			return "Странный рычаг запросил какой-то пароль, которого у вас нет. Возможно, поиски поблизости позволят найти какие-то подсказки.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы нашли половину страницы журнала, на которой говорилось, что пароль состоит из двух слов. Вы смогли расшифровать только одно слово, второе было оторвано.";
		}
	},

	REBEL_BASE_PASSWORD_PART_TWO(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Заполнить пробел";
		}
		@Override
		public String getDescription() {
			return "Вторая половина пароля должна быть на другой половине страницы журнала. Возможно, ее еще можно найти поблизости.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы нашли обе половины пароля, вместе они составляют фразу «RUAT CAELUM».";
		}
	},

	REBEL_BASE_PASSWORD_COMPLETE(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Сезам, откройся!";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда пароль известен, вы можете попробовать дернуть рычаг еще раз.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы обнаружили, что рычаг был прикреплен к двери, которая вела в скрытую пещеру, отходящую от пещер летучих мышей.";
		}
	},

	REBEL_BASE_EXPLORATION(QuestType.SIDE,
			15,
			5) {
		@Override
		public String getName() {
			return "Погружение в спелеологию";
		}
		@Override
		public String getDescription() {
			return "Неизвестно, для чего нужна эта скрытая пещера и куда она ведет. Возможно, вы сможете найти в ней ответы.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы узнали, что скрытая пещера была убежищем давно исчезнувшей группы повстанцев. Судя по всему, они не победили.";
		}
	},

	REBEL_BASE_ESCAPE(QuestType.SIDE,
			15,
			100) {
		@Override
		public String getName() {
			return "Выйти сухим из воды";
		}
		@Override
		public String getDescription() {
			return "Пора бежать, желательно до того, как эта пещера обрушится...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам удалось выбраться целым и невредимым. Все остальное, что хранилось в этой пещере, теперь похоронено навсегда.";
		}
	},

	REBEL_BASE_FAILED(QuestType.SIDE,
			15,
			0) {
		@Override
		public String getName() {
			return "Холодные ноги";
		}
		@Override
		public String getDescription() {
			return "Вам удалось выбраться целым и невредимым, но все секреты, которые хранила эта пещера, теперь похоронены навсегда.";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},

	REBEL_BASE_FIREBOMBS_START(QuestType.SIDE,
			1,
			5) {
		@Override
		public String getName() {
			return "Во все взрывные";
		}
		@Override
		public String getDescription() {
			return "Огненные бомбы, которые вы добыли в таинственной пещере, могут пригодиться в бою. Вам нужно найти того, кто сможет их сделать или продать.";
		}
		@Override
		public String getCompletedDescription() {
			return "Рокси согласилась поискать для вас новые зажигательные бомбы.";
		}
	},

	REBEL_BASE_FIREBOMBS_FINISH(QuestType.SIDE,
			1,
			5) {
		@Override
		public String getName() {
			return "Огонь от крыс";
		}
		@Override
		public String getDescription() {
			return "Рокси понадобится два дня, чтобы собрать новый запас зажигательных бомб. Тогда вам следует вернуться к ней.";
		}
		@Override
		public String getCompletedDescription() {
			return "Каким-то образом Рокси не обманула вас, и вы раздобыли запас зажигательных бомб.";
		}
	},

	REBEL_BASE_FIREBOMBS_FAILED(QuestType.SIDE,
			1,
			0) {
		@Override
		public String getName() {
			return "Нежелание Рокси";
		}
		@Override
		public String getDescription() {
			return "Без образца, который можно было бы принести Рокси, она либо не поняла, либо не захотела возиться с попытками повторить найденные вами зажигательные бомбы...";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},

	//Eisek Quests
	
	EISEK_STALL_QUEST_STAGE_ONE(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Сбор материалов";
		}
		@Override
		public String getDescription() {
			return "Вы узнали, что нужно Эйсеку для ремонта его ларька, а также что он хотел бы видеть на новой вывеске. Теперь вам осталось собрать материалы у торговцев в городе, чтобы сделать приятный сюрприз. Может быть, здесь есть тот, кто торгует тканями?";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы заказали у Моники новую вывеску и несколько кусков ткани.";
		}
	},
	
	EISEK_STALL_QUEST_STAGE_TWO(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Нужен тент";
		}
		@Override
		public String getDescription() {
			return "Пока Моника оформляет заказ, вам стоит поискать деревянные столбы для навеса. В местной кузнице, возможно, смогут модифицировать соединение.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы разместили заказ в компании «Имсу и Хейл» на модифицированные соединения.";
		}
	},
	
	EISEK_STALL_QUEST_STAGE_THREE(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Все сходится";
		}
		@Override
		public String getDescription() {
			return "Вам следует зайти к Хейлу через день, а к Монике - через три дня, чтобы узнать, готов ли ваш заказ.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы собрали все материалы.";
		}
	},
	
	EISEK_STALL_QUEST_STAGE_FOUR(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Собираем все воединоr";
		}
		@Override
		public String getDescription() {
			return "У вас есть все необходимое, чтобы улучшить стойку Айзека. Расскажите ему об этом, когда увидите его в следующий раз.";
		}
		@Override
		public String getCompletedDescription() {
			return "Насколько вы могли судить, Айзек был очень рад тому, что вы для него сделали, и его ларек выглядит лучше, чем когда-либо.";
		}
	},
	
	EISEK_MOB_QUEST_STAGE_ONE(QuestType.SIDE,
			10,
			25) {
		@Override
		public String getName() {
			return "Все против одного";
		}
		@Override
		public String getDescription() {
			return "Айзек объяснил, почему его преследует толпа, но он мало что о них знает. Если вы хотите быть уверены, что они не вернутся, вам придется найти их и встретиться с ними лицом к лицу."
					+ "<br/>Поскольку, судя по всему, они состояли из местных жителей, возможно, поиск по городу поможет.";
		}
		@Override
		public String getCompletedDescription() {
			return "Благодаря удаче и тому, что толпа вывесила большой красочный плакат, вы нашли место их встречи.";
		}
	},
	
	EISEK_MOB_QUEST_STAGE_TWO(QuestType.SIDE,
			10,
			100) {
		@Override
		public String getName() {
			return "Оставьте дракона в покое!";
		}
		@Override
		public String getDescription() {
			return "Вы нашли место, где собиралась толпа. Пора с ними разобраться!";
		}
		@Override
		public String getCompletedDescription() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_intimidate"))) {
			    return "Вы решили попытаться убедить толпу оставить Айзека в покое своим устрашающим телосложением.";
			} else if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_intimidate_arcane"))) {
			    return "Вы решили попытаться убедить толпу оставить Айзека в покое с помощью своего магического мастерства.";
			} else if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_persuade"))) {
			    if(!Main.game.isSillyModeEnabled()) {
			    	return "Вы убедили толпу оставить Айзека в покое, произнеся проникновенную речь.";
			    } else {
			    	return "Вы уничтожили аргументы толпы с помощью ФАКТОВ и ЛОГИКИ.";
			    }
			} else if (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("dsg_elis_eisek_mob_quest_seduce"))) {
			    return "Вы убедили толпу оставить Айзека в покое, используя свое мастерство в магии похоти для разжигания оргии.";
			} else {
			    return "Вы не смогли убедить толпу оставить Айзека в покое.";
			}
		}
	},
	
	EISEK_MOB_QUEST_STAGE_TWO_FAILED(QuestType.SIDE,
			10,
			0) {
		@Override
		public String getName() {
			return "Туда его!";
		}
		@Override
		public String getDescription() {
			return "Вы не смогли убедить толпу оставить Айзека в покое. Вам следует вернуться к нему с плохими новостями, раз уж вы не смогли справиться с толпой.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы не смогли убедить толпу оставить Айзека в покое.";
		}
	},
	
	EISEK_MOB_QUEST_STAGE_THREE_FAILED(QuestType.SIDE,
			10,
			0) {
		@Override
		public String getName() {
			return "Плохие новости";
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String getCompletedDescription() {
			return "Хотя он и пытался скрыть это, Айзек, похоже, был расстроен тем, что толпа все еще где-то там, в заговоре против него.";
		}
	},
	
	EISEK_MOB_QUEST_STAGE_THREE(QuestType.SIDE,
			10,
			250) {
		@Override
		public String getName() {
			return "Хорошие новости";
		}
		@Override
		public String getDescription() {
			return "Вам следует вернуться к Айзеку с хорошими новостями, раз уж вы разобрались с толпой.";
		}
		@Override
		public String getCompletedDescription() {
			return "Хотя Айзек и пытался скрыть это, он выглядел очень довольным тем, что теперь толпа оставит его в покое. Вы даже получили несколько редких плодов дракона.";
		}
	},
	
	EISEK_SILLYMODE_QUEST_STAGE_ONE(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Странная толпа";
		}
		@Override
		public String getDescription() {
			return "Вы столкнулись с толпой другого рода, которая была странно одержима Айзеком. В итоге все закончилось ничем, но вы решили узнать, чем занимаются эти странные люди.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы попали в какой-то подвал, где они собрались.";
		}
	},
	
	EISEK_SILLYMODE_QUEST_STAGE_TWO(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Темнейшее подземелье";
		}
		@Override
		public String getDescription() {
			return "Вы проследили за обитателями подвала и решили заглянуть внутрь. К сожалению, они не очень-то оценили ваше вторжение и заблокировали путь, по которому вы пришли.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы почти у выхода, осталось только одно препятствие...";
		}
	},
	
	EISEK_SILLYMODE_QUEST_STAGE_THREE(QuestType.SIDE,
			1,
			10) {
		@Override
		public String getName() {
			return "Подземелье очищено";
		}
		@Override
		public String getDescription() {
			return "Победив лидера этой странной группы, вам остается только уйти.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы успешно выбрались из подземелья и показали кучке задротов, кто здесь хозяин.";
		}
	},
	
	// Fetching beer barrels for Oglix:
	
	OGLIX_BEER_BARRELS_1(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Бочки Хейрона";
		}
		@Override
		public String getDescription() {
			return "Согласившись помочь Огликс расширить выбор пивных сучек, вы должны отправиться в таверну «Меч кентавра» и попросить у ее владельца Хейрона свободные бочки."
					+ " Если он откажется помочь, Огликс велела вам передать кентавру, что «Огликс велела быть хорошей лошадкой».";
		}
		@Override
		public String getCompletedDescription() {
			return "Благодаря специальной фразе «Голикс велела быть хорошей лошадкой» вам удалось убедить Хейрона отправить четыре запасных бочонка в таверну Огликс.";
		}
	},
	
	OGLIX_BEER_BARRELS_2(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Доклад хорошей лошадки";
		}
		@Override
		public String getDescription() {
			return "Заручившись помощью Хейрона, вам теперь нужно вернуться к Огликс и сообщить ей, что Хейрон решил стать «хорошей лошадкой» для Огликс.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы вернулись к Огликс и сообщили ей о своем успехе."
					+ " Заполучив четыре дополнительных бочки, в которых можно запереть новых пивных сучек, Огликс попросила вас предоставить перспективные кандидатуры из криминального населения близлежащих переулков."
					+ " Кроме того, она попросила вас пробраться в заднюю часть ее таверны между [units.time(6)]-[units.time(7)] Если вы хотели узнать, что означает фраза «Голикс велела быть хорошей лошадкой»...";
		}
	},

	
	// Helping Lunexis to escape:
	
	LUNEXIS_ESCAPE(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Освободить Лунексис";
		}
		@Override
		public String getDescription() {
			return "Сдавшись Лунексис и пообещав быть ее послушным мастурбатором, вы получили приказ своей новой госпожи помочь ей сбежать из плена."
					+ " Желая отомстить тому, кто телепортировал ее в Элис, кентавресса разработала план, согласно которому вы должны убедить Мераксис телепортировать вас троих обратно в Фемискиру."
					+ " Там госпожа вознаградит тебя, оставив в качестве одного из своих личных рабов мастурбаторов...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы убедили Мераксис телепортироваться вместе с вами и Лунексис обратно в Фемискиру."
					+ " Обман стал явным, и хотя Мераксис пыталась бороться, вскоре она была покорена и использована вашей госпожой, чтобы вернуть колеблющуюся лояльность своей армии кентавров.";
		}
	},

	LUNEXIS_ESCAPE_FAILED(QuestType.SIDE, 1, 0) {
		@Override
		public String getName() {
			return "Предать Лунексис";
		}
		@Override
		public String getDescription() {
			return "Решив раскрыть все Мераксис, вы предали Лунексис, и вам было запрещено впредь контактировать с демонической кентаврессой.";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},

	
	// Doll factory quests:
	
	DOLL_FACTORY_1(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "По приказу Ловиенны";
		}
		@Override
		public String getDescription() {
			return "Из дневника Ангеликс стало известно, что похищенных беженцев телепортировали в магазин «Роскошь Ловиенны» в Доминионе."
					+ " Если вы хотите узнать, что случилось с жертвами Ангеликс, вам нужно исследовать этот магазин...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы отправились в магазин «Роскошь Ловиенны» и попытались выяснить, здесь ли забирают беженцев, похищенных Ангеликс."
					+ " Хотя вы ничего не смогли обнаружить, к вам подошла женщина, когда вы уходили, и предложила свою помощь.";
		}
	},
	
	DOLL_FACTORY_2(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Взлом и проникновение";
		}
		@Override
		public String getDescription() {
			return "Женщина, которую вы встретили возле «Роскоши Ловиенны», знает, как пройти в заднюю часть помещения, где, по ее мнению, держат похищенных беженцев и используют их в качестве рабского труда."
					+ " Не имея другого способа проникнуть внутрь и разобраться в том, что там происходит, вы согласились с ее планом и сказали, что встретитесь с ней возле магазина между [units.time(1)]-[units.time(4)].";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы встретились с женщиной возле «Роскоши Ловиенны» и сумели проникнуть в заднюю часть помещения, не включив сигнализацию.";
		}
	},
	
	DOLL_FACTORY_3(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Добраться до дна";
		}
		@Override
		public String getDescription() {
			return "Кукольная фабрика, расположенная на заднем дворе «Роскоши Ловиенны», оказалась всего лишь фасадом."
					+ " Оказалось, что настоящая фабрика, где вы наверняка найдете похищенных беженцев, находится глубоко под землей, так что вам предстоит спуститься вниз...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы спустились на лифте на настоящую фабрику кукол, где стали свидетелями того, как Ангеликса превращает пленницу в секс-куклу.";
		}
	},
	
	DOLL_FACTORY_4(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Соберите улики";
		}
		@Override
		public String getDescription() {
			return "Никто не поверит в то, что вы видели, поэтому вам придется собрать неопровержимые доказательства того, как создаются куклы Ловиенн."
					+ " Бухгалтерские книги, схемы машин и другие подобные документы обязательно найдутся где-нибудь в офисе...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам удалось собрать неопровержимые доказательства того, как создаются куклы Ловиенн.";
		}
	},
	
	DOLL_FACTORY_5(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Вверх и в даль";
		}
		@Override
		public String getDescription() {
			return "Теперь в вашем распоряжении неопровержимые доказательства создания кукол Ловиенн, и вам нужно сбежать с фабрики...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам удалось сбежать с нижнего завода, но при выходе из лифта вы столкнулись с самой Ангеликс, которая предложила вам сделку...";
		}
	},
	
	DOLL_FACTORY_6A(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Падение Ангеликс";
		}
		@Override
		public String getDescription() {
			return "Вы отказались заключать сделку с Ангеликс и вместо этого передали собранные вами улики этой женщине."
					+ " Когда информация о том, что происходит в ее магазине, станет достоянием общественности, Ловиенн, скорее всего, подвергнет Ангеликс суровому наказанию."
					+ " Возможно, если вы вернетесь в магазин в часы работы, вы узнаете, что с ней стало...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы отказались заключать сделку с Ангеликс и вместо этого передали собранные вами улики этой женщине."
					+ " Вернувшись в «Роскошь Ловиенны» в часы работы, вы обнаружили, что представители общественности не воспринимают всерьез доказательства того, как создаются секс-куклы."
					+ " Более того, в продажу поступила лимитированная секс-кукла суккуб, которая является точной копией Ангеликс...";
		}
	},
	
	DOLL_FACTORY_6B(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Помощник Ангеликс";
		}
		@Override
		public String getDescription() {
			return "Вы предали доверие женщины и передали ее Ангеликс вместе с собранными вами уликами."
					+ " Суккуб поблагодарила вас и сказала, что вы должны вернуться в магазин в часы работы, чтобы получить особую награду...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы предали доверие женщины и передали ее Ангеликс вместе с собранными вами уликами."
					+ " Взамен Ангеликс превратит ваших рабов в кукол за небольшую плату или заплатит вам, если вы не захотите их возвращать."
					+ " Она также предложила вам интимную встречу...";
		}
	},
	
	
	// Romance quests:

	RELATIONSHIP_NYAN_1_STOCK_ISSUES(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Помощь Ньян";
		}
		@Override
		public String getDescription() {
			return "Ньян объяснила, что не может продавать зачарованную одежду из-за того, что ее оптовый поставщик внезапно перестал выходить с ней на связь."
					+ " Судя по всему, этот поставщик раньше был в хороших отношениях с Ньян, и его нехарактерное поведение заставило нервную девушку-кошку заподозрить, что с ним случилось что-то ужасное.<br/>"
					+ "Возможно, вы могли бы предложить Ньян свою помощь, выяснив, что случилось с этим поставщиком?";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы предложили Ньян свою помощь в выяснении того, что случилось с ее оптовым поставщиком зачарованной одежды.";
		}
	},
	
	RELATIONSHIP_NYAN_2_STOCK_ISSUES_AGREED_TO_HELP(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "Спасение Кей";
		}
		@Override
		public String getDescription() {
			return "Ньян рассказала вам, что ее поставщик, [kay.nameFull], открыл свое дело в складском районе Доминиона."
					+ " Вам нужно отправиться на северо-запад города, найти склад Кея и выяснить, почему он внезапно прервал связь с Ньян.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы не только нашли склад Кея, но и смогли выяснить, что его бизнес фактически захвачен парой задиристых охотников за головами.";
		}
	},
	
	RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS(QuestType.RELATIONSHIP, 10, 100) {
		@Override
		public String getName() {
			return "Задиры-охотники за головами";
		}
		@Override
		public String getDescription() {
			return "Вольфганг и Карл, пара охотников за головами, нанятых Кеем для охраны его склада, ополчились на своего работодателя и фактически захватили полный контроль над его бизнесом."
					+ " Так или иначе, вам придется убедить этих задир-доберманов оставить Кей в покое...";
		}
		@Override
		public String getCompletedDescription() {
			return "Разобравшись с Вольфгангом и Карлом и отправив их в «Логово охотников за головами» на Аллее рабов, вы одновременно спасли бизнес Кей и обеспечили Ньян доступ к запасам зачарованной одежды."
					+ " Выразив вам свою вечную благодарность, Кей сказал, что вы можете нанести ему визит, когда пожелаете.";
		}
	},
	
	RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "Награда Ньян";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда вы спасли бизнес Кея, вам следует вернуться к Ньян и сообщить ей о случившемся.";
		}
		@Override
		public String getCompletedDescription() {
			return "Очень довольная Ньян выплатила вам обещанное вознаграждение, а также предложила пожизненную 25-процентную скидку в своем магазине."
					+ " Она также сообщила, что одинока, в неуклюжей попытке подкатить к вам...";
		}
	},
	
	
	
	ROMANCE_HELENA_FAILED(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Разъяренный матриарх";
		}

		@Override
		public String getDescription() {
			return "После того как вы отказались продать Скарлетт Елене, надменная гарпия-матриарх отказалась от своих планов по организации рабского бизнеса и унеслась в свое гнездо."
					+ " Учитывая то, как она безжалостно оскорбила вас перед уходом, вы можете быть уверены, что она больше никогда не захочет вас видеть...";
		}

		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},
	
	ROMANCE_HELENA_1_OFFER_HELP(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Предложить помощь";
		}

		@Override
		public String getDescription() {
			return "Расспросив Елену о ее бизнесе, вы обнаружили, что ей едва удается поддерживать заведение в рабочем состоянии."
					+ " Высказав желание немного улучшить свой магазин, Елена обнаружила, что у нее просто нет ни времени, ни желания заниматься этим самостоятельно."
					+ " Может быть, вы предложите ей свою помощь?";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы предложили Елене помочь ей сделать кое-какие улучшения в магазине.";
		}
	},

	ROMANCE_HELENA_2_PURCHASE_PAINT(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "Купить краску";
		}

		@Override
		public String getDescription() {
			return "Елена рассказала, что первым делом она хочет перекрасить весь интерьер своего магазина."
					+ " Не предоставив вам денег на расходы, гарпия ожидает, что вы отправитесь на склад «Сделай сам» Аргуса и купите банку золотой краски марки «Пурпурная звезда»."
					+ " Купив ее, вы должны вернуться к Елене."
					+ "<br/><i>(Склад «Сделай сам» Аргуса можно найти к югу от Аллеи рабов, рядом с каналом.)</i>";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы купили необходимую краску в «Депо Аргуса» и вернулись к Елене.";
		}
	},

	ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Декоратор экстерьера (1/3)";
		}

		@Override
		public String getDescription() {
			return "Купив золотую краску, вы вернулись к Елене, но она потребовала, чтобы вы как можно скорее начали перекрашивать ее магазин...";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы содрали всю старую краску с фасада магазина Елены.";
		}
	},

	ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Декоратор экстерьера (2/3)";
		}

		@Override
		public String getDescription() {
			return "Вам нужно вернуться в магазин Елены в часы работы, чтобы узнать, каким будет ваше следующее задание...";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы перекрасили весь фасад магазина Елены, а также получили доставку мебели от суккутавра по имени «Наталья».";
		}
	},

	ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Декоратор экстерьера (3/3)";
		}

		@Override
		public String getDescription() {
			return "Вам нужно вернуться в магазин Елены в часы работы, чтобы узнать, каким будет ваше следующее задание...";
		}

		@Override
		public String getCompletedDescription() {
			return "Под руководством матриарха гарпий вы нарисовали золотыми буквами надпись «Бутик Елены» над дверью ее магазина.";
		}
	},

	ROMANCE_HELENA_4_SCARLETTS_RETURN(QuestType.RELATIONSHIP, 1, 100) {
		@Override
		public String getName() {
			return "Возвращение Скарлетт";
		}

		@Override
		public String getDescription() {
			boolean slave = Main.game.getNpc(Scarlett.class).isSlave() || Main.game.getNpc(Scarlett.class).getHomeWorldLocation()==WorldType.EMPTY;
			boolean playerOwner = Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer();
			return "Елена рассказала вам о своем плане по ребрендингу своего магазина рабов, где клиенты могли бы покупать рабов на заказ."
					+ " Для обучения этих рабов она выбрала своего старого наставника по этикету, который, судя по всему, является не кем иным, как сестрой Скарлетт."
					+ (slave
						?" Елена поставила условие: она должна освободить непокорную сестру из рабства, пообещать никогда больше не порабощать ее, а затем держать ее на работе..."
							+ "<br/>"
							+(playerOwner
								?"Вам придется привезти Скарлетт к Елене и продать ее ей..."
								:"Вам придется найти Скарлетт и выкупить ее у того, кто стал ее новым владельцем. По словам Елены, ее купил владелец антикварного магазина где-то в Торговом центре.")
						:" Елена поставила условие: она должна держать свою непокорную сестру на работе и обещать никогда больше не порабощать ее..."
							+ "<br/>"
							+ "Вам придется подняться в гнездо Елены, найти Скарлетт, а затем сказать ей, чтобы она вернулась к Елене...");
		}
		
		@Override
		public String getCompletedDescription() {
			return "Согласно пожеланиям старого тренера Елены по этикету, Скарлетт отныне будет работать личным помощником матриарха гарпий.";
		}
	},

	ROMANCE_HELENA_5_SCARLETT_TRAINER(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Помощник гарпии";
		}

		@Override
		public String getDescription() {
			return "Две гарпии ушли пораньше, чтобы навестить сестру Скарлетт. Вам нужно вернуться в магазин Елены в часы работы, чтобы узнать, каким будет ваше следующее задание...";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы вернулись в магазин Елены и обнаружили, что все готово к тому, чтобы матриарх гарпий начала принимать покупателей. Однако перед этим вам нужно помочь ей еще с парой вещей...";
		}
	},

	ROMANCE_HELENA_6_ADVERTISING(QuestType.RELATIONSHIP, 1, 15) {
		@Override
		public String getName() {
			return "Размещение рекламы";
		}

		@Override
		public String getDescription() {
			return "Получив полдюжины заколдованных плакатов, демонстрирующих красоту Елены, вы должны развесить их у входа на Аллею рабов, чтобы помочь рекламировать ее магазин.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы вывешиваете плакаты с рекламой «Бутика Елены» у входа на Аллею рабов.";
		}
	},

	ROMANCE_HELENA_7_GRAND_OPENING_PREPARATION(QuestType.RELATIONSHIP, 1, 15) {
		@Override
		public String getName() {
			return "Подготовка к торжественному открытию";
		}

		@Override
		public String getDescription() {
			return "После того как вы развесили плакаты, появилась Скарлетт и привела вас обратно в магазин Елены."
					+ " Ваша новая задача - подготовить все к завтрашнему торжественному открытию, а это значит, что придется работать всю ночь...";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы со Скарлетт закончили подготовку к торжественному открытию магазина.";
		}
	},

	ROMANCE_HELENA_8_FINISH(QuestType.RELATIONSHIP, 1, 100) {
		@Override
		public String getName() {
			return "Подготовка напитков";
		}

		@Override
		public String getDescription() {
			return "Не желая, чтобы Скарлетт доставляла неприятности во время торжественного открытия, Елена поручила вам двоим оставаться в задней комнате и готовить напитки для гостей.";
		}

		@Override
		public String getCompletedDescription() {
			return "Вы со Скарлетт оставались в задней комнате и готовили напитки, пока не закончилось торжественное открытие."
					+ "  Наконец-то Елена оценила ваши усилия и сказала, что в качестве вознаграждения готова пригласить вас на свидание...";
		}
	},
	
	

	ROMANCE_NATALYA_FAILED_INTERVIEW(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Интервью провалено";
		}
		@Override
		public String getDescription() {
			return "Отказавшись выполнить просьбу Натальи во время интервью, вы были вышвырнуты из «Доминион Экспресс» и вам было сказано никогда не возвращаться...";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},

	ROMANCE_NATALYA_FAILED_CONTRACT(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Отказ от контракта";
		}
		@Override
		public String getDescription() {
			return "Отказавшись подписать контракт, который вам предложила Наталья, вы были вышвырнуты из «Доминион Экспресс» и вам было сказано никогда не возвращаться...";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},
	
	ROMANCE_NATALYA_1_INTERVIEW_START(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Интервью";
		}
		@Override
		public String getDescription() {
			return "Наталья, хозяйка конюшни в компании «Доминион Экспресс», предложила вам пройти собеседование на должность «кобылки».";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы приняли предложение Натальи пройти собеседование на должность кобылки в «Доминион Экспресс».";
		}
	},

	ROMANCE_NATALYA_2_CONTRACT_SIGNED(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Натальина кобылка";
		}
		@Override
		public String getDescription() {
			return "Приняв предложение о собеседовании на должность «кобылки», вы теперь должны успешно пройти его и подписать контракт.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы успешно прошли собеседование с Натальей, и после подписания контракта вам сообщили, что теперь вам нужно превратиться в [style.a_shemale] тавра.";
		}
	},
	
	ROMANCE_NATALYA_3_TRAINING_1(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Дрессировка кобылки";
		}
		@Override
		public String getDescription() {
			return "Наталья сказала вам, что первая часть вашего обучения будет включать в себя повторение вашего орального выступления на одном из рабов-кентавров.";
		}
		@Override
		public String getCompletedDescription() {
			return "После превращения в тавра [style.a_shemale] вы начали свое обучение с отсасывания члена у одного из самых непокорных рабов-кентавров Доминион Экспресс.";
		}
	},

	ROMANCE_NATALYA_4_TRAINING_2(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Дополнительные тренировки";
		}
		@Override
		public String getDescription() {
			return "И снова Наталья велела вам вернуться на следующий день, чтобы продолжить обучение, в ходе которого вы научитесь любить делать римминг.";
		}
		@Override
		public String getCompletedDescription() {
			return "Второй этап вашего обучения включал в себя нанесение разноцветной помады и выполнение анилингуса госпоже Наталье.";
		}
	},

	ROMANCE_NATALYA_5_TRAINING_3(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Заключительная тренировка";
		}
		@Override
		public String getDescription() {
			return "Наталья велела вам вернуться на следующий день, чтобы закончить обучение, которое будет включать в себя римминг рабу-кентавру, а также вас оседлают и анально оттрахают как Наталья, так и раб, чью задницу вы обслуживаете.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы сделали анилингус рабу-кентавру, а затем вас оседлали и анально оттрахали, Наталья заявила, что обучение вас как кобылки завершено.";
		}
	},

	ROMANCE_MONICA_1_TO_THE_FARM(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "На ферму";
		}
		@Override
		public String getDescription() {
			return "После того как вы предложили помочь вернуть ее индивидуальный молокоотсос, Моника рассказала, что его можно найти на ее прежнем месте работы - на ферме, расположенной к северо-востоку от Элис под названием «Молочная Эвеликс»."
					+ " Вам придется пойти на эту ферму и попросить у Моники молокоотсос...";
		}
		@Override
		public String getCompletedDescription() {
			return "Найдя ферму, где раньше работала Моника, вы попросили именной молокоотсос, и вам была назначена встреча с владельцем фермы...";
		}
	},

	ROMANCE_MONICA_2_UNREASONABLE_DEMAND(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Необоснованное требование";
		}
		@Override
		public String getDescription() {
			return "Вам удалось добиться встречи с хозяйкой фермы, Эвеликс, которая принимает облик высокомерного и жадного суккуба."
					+ " Признав, что молокоотсос ничего не стоит, она требует, чтобы вы отдали ей огромную сумму денег или подписали подозрительный контракт в обмен на него...";
		}
		@Override
		public String getCompletedDescription() {
			return "Вам удалось получить от Эвеликс индивидуальный молокоотсос Моники.";
		}
	},

	ROMANCE_MONICA_3_THE_JOURNEY_HOME(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Путь домой";
		}
		@Override
		public String getDescription() {
			return "Теперь, когда персонализированный молокоотсос Моники у вас в руках, осталось только вернуть его законной владелице.";
		}
		@Override
		public String getCompletedDescription() {
			return "Вы вернули Монике индивидуальный молокоотсос, к ее удивлению и восторгу.";
		}
	},
	;

	private int level, experienceReward;
	private QuestType questType;

	private Quest(QuestType questType, int level, int experienceReward) {
		this.questType = questType;

		this.level = level;
		this.experienceReward = experienceReward;
	}

	public abstract String getName();

	public abstract String getDescription();

	public abstract String getCompletedDescription();
	
	public void applySkipQuestEffects() {	
	}
	
	public int getLevel() {
		return level;
	}

	public QuestType getQuestType() {
		return questType;
	}

	public int getExperienceReward() {
		return experienceReward;
	}
	
	public static Quest getQuestFromId(String quest) {
		if(quest.equalsIgnoreCase("MAIN_3_A_FINDING_THE_YOUKO")) {
			return Quest.MAIN_3_ELIS;
		}
		if(quest.equalsIgnoreCase("MAIN_3_D_TO_THEMISCRYA")) {
			return Quest.MAIN_3_D_TO_THEMISCYRA;
		}
		
		return Quest.valueOf(quest);
	}

}
