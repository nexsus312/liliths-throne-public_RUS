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
					+ " Если он откажется помочь, Голикс велел вам передать кентавру, что «Голикс велел быть хорошей лошадкой».";
		}
		@Override
		public String getCompletedDescription() {
			return "Благодаря специальной фразе «Голикс велел быть хорошей лошадкой» вам удалось убедить Хейрона отправить четыре запасных бочонка в таверну Огликса.";
		}
	},
	
	OGLIX_BEER_BARRELS_2(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Доклад хорошей лошадки";
		}
		@Override
		public String getDescription() {
			return "Заручившись помощью Хейрона, вам теперь нужно вернуться к Огликс и сообщить ей, что Хейрон решил стать «хорошей лошадкой» для Голикса.";
		}
		@Override
		public String getCompletedDescription() {
			return "You returned to Oglix and informed her of your success."
					+ " Having now secured four additional barrels in which to lock new beer-bitches, Oglix asked you to supply promising candidates from the nearby alleyways' criminal population."
					+ " Additionally, she told you to sneak around the back of her tavern between [units.time(6)]-[units.time(7)] if you wanted to see what the phrase 'Golix says to be a good horsie' meant...";
		}
	},

	
	// Helping Lunexis to escape:
	
	LUNEXIS_ESCAPE(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Free Lunexis";
		}
		@Override
		public String getDescription() {
			return "Having surrendered to Lunexis and pledged to be her obedient cock-sleeve, you've been ordered by your new Mistress to assist her in escaping from captivity."
					+ " Wanting to get her revenge on the one who teleported her to Elis, the centauress has devised a plan where you're to convince Meraxis to teleport the three of you back to Themiscyra."
					+ " Once there, your Mistress will reward you by keeping you as one of her personal cock-sleeve slaves...";
		}
		@Override
		public String getCompletedDescription() {
			return "You convinced Meraxis to teleport herself, along with you and Lunexis, back to Themiscyra."
					+ " Once there, your deception was made clear, and although she tried to fight, Meraxis was soon subdued and used by your Mistress to win back the wavering loyalty of her centauress army.";
		}
	},

	LUNEXIS_ESCAPE_FAILED(QuestType.SIDE, 1, 0) {
		@Override
		public String getName() {
			return "Lunexis Betrayed";
		}
		@Override
		public String getDescription() {
			return "Deciding to reveal everything to Meraxis, you betrayed Lunexis, and were banned from having any further contact with the demonic centauress.";
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
			return "On Lovienne's Orders";
		}
		@Override
		public String getDescription() {
			return "Angelixx's diary revealed that the kidnapped refugees were being teleported to the shop 'Lovienne's Luxury' in Dominion."
					+ " If you're to discover what's happened to Angelixx's victims, then you'll need to investigate this store...";
		}
		@Override
		public String getCompletedDescription() {
			return "You travelled to the shop 'Lovienne's Luxury' and attempted to discover if the refugees kidnapped by Angelixx were being taken there."
					+ " Although you weren't able to discover anything, a woman approached you as you left and offered her help.";
		}
	},
	
	DOLL_FACTORY_2(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Breaking and Entering";
		}
		@Override
		public String getDescription() {
			return "The woman you met outside 'Lovienne's Luxury' knows a way into the rear of the premises, where she believes the kidnapped refugees are being held and used as slave labour."
					+ " With no other way to gain entry and get to the bottom of what's happening in there, you agreed to her plan and said you'd meet her near to the shop between [units.time(1)]-[units.time(4)].";
		}
		@Override
		public String getCompletedDescription() {
			return "You met the woman outside 'Lovienne's Luxury' and managed to break in to the rear of the premises without setting off the alarm system.";
		}
	},
	
	DOLL_FACTORY_3(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Getting to the Bottom";
		}
		@Override
		public String getDescription() {
			return "The doll factory to the rear of 'Lovienne's Luxury' has turned out to be nothing but a facade."
					+ " The real factory, and where you're sure to find the kidnapped refugees, has been revealed to be deep underground, so it's down you go...";
		}
		@Override
		public String getCompletedDescription() {
			return "You travelled down the elevator to the real doll factory, where you witnessed Angelixx converting a captive into a sex doll.";
		}
	},
	
	DOLL_FACTORY_4(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Gather Evidence";
		}
		@Override
		public String getDescription() {
			return "Nobody will believe what you've seen, so you're going to need to gather hard evidence of how Lovienne's dolls are created."
					+ " Ledgers, machine schematics, or other such documents are sure to be found in an office somewhere...";
		}
		@Override
		public String getCompletedDescription() {
			return "You managed to gather hard evidence of how Lovienne's dolls are created.";
		}
	},
	
	DOLL_FACTORY_5(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Up and Away";
		}
		@Override
		public String getDescription() {
			return "With hard evidence of Lovienne's dolls are created now in your possession, you need to escape from the factory...";
		}
		@Override
		public String getCompletedDescription() {
			return "You managed to escape from the lower factory, but as you exited the elevator you were confronted by Angelixx herself, who offered you a deal...";
		}
	},
	
	DOLL_FACTORY_6A(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Angelixx's Fall";
		}
		@Override
		public String getDescription() {
			return "You refused to make a deal with Angelixx, and instead handed the evidence you'd gathered over to the woman."
					+ " When word of what's going on in her shop is made public, Lovienne is likely to administer a severe punishment to Angelixx."
					+ " Perhaps if you return to the shop during opening hours you'll find out what's to become of her...";
		}
		@Override
		public String getCompletedDescription() {
			return "You refused to make a deal with Angelixx, and instead handed the evidence you'd gathered over to the woman."
					+ " Returning to 'Lovienne's Luxury' during opening hours, you discovered that the evidence of how sex dolls are created is not being taken seriously by members of the public."
					+ " What's more, there's now a limited-edition succubus sex doll for sale, who's the spitting image of Angelixx...";
		}
	},
	
	DOLL_FACTORY_6B(QuestType.SIDE, 30, 10) {
		@Override
		public String getName() {
			return "Angelixx's Associate";
		}
		@Override
		public String getDescription() {
			return "You betrayed the trust of the woman and handed her over to Angelixx, along with the evidence you'd gathered."
					+ " The succubus offered you her thanks and told you to return to the shop during opening hours to get a special reward...";
		}
		@Override
		public String getCompletedDescription() {
			return "You betrayed the trust of the woman and handed her over to Angelixx, along with the evidence you'd gathered."
					+ " In return, Angelixx will convert your slaves to dolls in exchange for a small fee, or will instead pay you if you don't want them back."
					+ " She also offered you her intimate company...";
		}
	},
	
	
	// Romance quests:

	RELATIONSHIP_NYAN_1_STOCK_ISSUES(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Helping Nyan";
		}
		@Override
		public String getDescription() {
			return "Nyan explained that she's unable to sell any enchanted clothing due to the fact that her wholesale supplier has suddenly stopped communicating with her."
					+ " Apparently, this supplier used to be on good terms with Nyan, and his uncharacteristic behaviour has caused the nervous cat-girl to suspect something terrible has happened to him.<br/>"
					+ "Perhaps you could offer Nyan your help by finding out what's happened to this supplier?";
		}
		@Override
		public String getCompletedDescription() {
			return "You offered Nyan your help in finding out what's happened to her wholesale enchanted clothing supplier.";
		}
	},
	
	RELATIONSHIP_NYAN_2_STOCK_ISSUES_AGREED_TO_HELP(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "Saving Kay";
		}
		@Override
		public String getDescription() {
			return "Nyan told you that her supplier, [kay.nameFull], has his business set up in Dominion's warehouse district."
					+ " You'll need to travel to the north-west of the city, find Kay's warehouse, and discover why he's suddenly cut off all communication with Nyan.";
		}
		@Override
		public String getCompletedDescription() {
			return "You not only found Kay's warehouse, but were also able to discover that his business has been effectively seized by a pair of bullying bounty hunters.";
		}
	},
	
	RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS(QuestType.RELATIONSHIP, 10, 100) {
		@Override
		public String getName() {
			return "Bounty Hunter Bullies";
		}
		@Override
		public String getDescription() {
			return "Wolfgang and Karl, a pair of bounty hunters hired by Kay to protect his warehouse, have turned on their employer and effectively seized full control of his business."
					+ " One way or another, you're going to have to convince these dobermann bullies to leave Kay alone...";
		}
		@Override
		public String getCompletedDescription() {
			return "By dealing with Wolfgang and Karl and sending them skulking off back to Slaver Alley's 'Bounty Hunter Lodge', you've both saved Kay's business and ensured that Nyan once again has access to a stock of enchanted clothing."
					+ " Offering you his eternal thanks, Kay said that you're welcome to pay him a visit whenever you like.";
		}
	},
	
	RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "Nyan's Reward";
		}
		@Override
		public String getDescription() {
			return "Now that you've saved Kay's business, you should return to Nyan and inform her of what's happened.";
		}
		@Override
		public String getCompletedDescription() {
			return "A very happy Nyan paid you your promised reward, and also offered to give you a lifetime 25% discount in her store."
					+ " She also let slip that she's single, in a clumsy attempt to try and hit on you...";
		}
	},
	
	
	
	ROMANCE_HELENA_FAILED(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Furious Matriarch";
		}

		@Override
		public String getDescription() {
			return "After you refused to sell Scarlett to Helena, the haughty harpy matriarch gave up on her plans to run a slavery business and stormed off back to her nest."
					+ " With the way she ruthlessly insulted you before leaving, you can be sure that she's never going to want to see you again...";
		}

		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},
	
	ROMANCE_HELENA_1_OFFER_HELP(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Offer to help";
		}

		@Override
		public String getDescription() {
			return "After asking Helena about her business, you discovered that she's barely managing to keep the place running."
					+ " After expressing her desire to make some improvements to her shop, Helena revealed that she simply doesn't have the time nor inclination to do the work herself."
					+ " Perhaps you could offer her your help?";
		}

		@Override
		public String getCompletedDescription() {
			return "You offered to help Helena make some improvements to her shop.";
		}
	},

	ROMANCE_HELENA_2_PURCHASE_PAINT(QuestType.RELATIONSHIP, 1, 25) {
		@Override
		public String getName() {
			return "Purchase Paint";
		}

		@Override
		public String getDescription() {
			return "Helena revealed that the first thing she wants done is to have the entire exterior of her shop repainted."
					+ " Having provided you with no money for expenses, the harpy is expecting you to travel to 'Argus's DIY Depot' and buy a can of 'Purple-star' branded golden paint."
					+ " Once you've bought it, you need to return to Helena."
					+ "<br/><i>('Argus's DIY Depot' can be found a fair way to the south of Slaver Alley, next to the canal.)</i>";
		}

		@Override
		public String getCompletedDescription() {
			return "You purchased the required paint from 'Argus's DIY Depot' and returned to Helena.";
		}
	},

	ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Exterior Decorator (1/3)";
		}

		@Override
		public String getDescription() {
			return "Having purchased the golden paint, you returned to Helena only to have her demand that you get started on repainting the exterior of her shop as soon as possible...";
		}

		@Override
		public String getCompletedDescription() {
			return "You stripped off all of the old paint from the front of Helena's store.";
		}
	},

	ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Exterior Decorator (2/3)";
		}

		@Override
		public String getDescription() {
			return "You need to return to Helena's store during opening hours to see what your next task will be...";
		}

		@Override
		public String getCompletedDescription() {
			return "You repainted the entire frontage of Helena's store, and additionally received a delivery of furniture from a succutaur named 'Natalya'.";
		}
	},

	ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "Exterior Decorator (3/3)";
		}

		@Override
		public String getDescription() {
			return "You need to return to Helena's store during opening hours to see what your next task will be...";
		}

		@Override
		public String getCompletedDescription() {
			return "Under the harpy matriarch's supervision, you painted the words 'Helena's Boutique' in golden lettering above the door to her store.";
		}
	},

	ROMANCE_HELENA_4_SCARLETTS_RETURN(QuestType.RELATIONSHIP, 1, 100) {
		@Override
		public String getName() {
			return "Scarlett's Return";
		}

		@Override
		public String getDescription() {
			boolean slave = Main.game.getNpc(Scarlett.class).isSlave() || Main.game.getNpc(Scarlett.class).getHomeWorldLocation()==WorldType.EMPTY;
			boolean playerOwner = Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer();
			return "Helena revealed to you her plan to rebrand her slave shop as a place where clients could order custom slaves."
					+ " The person she has in mind to train these custom slaves is her old etiquette coach, who apparently is none other than Scarlett's sister."
					+ (slave
						?" The condition she's given Helena is that she free her unruly sister from slavery, promise never to enslave her again, and then keep her employed..."
							+ "<br/>"
							+(playerOwner
								?"You're going to have to bring Scarlett to Helena and sell her back to her..."
								:"You're going to have to find Scarlett and purchase her from whoever is her new owner. According to Helena, she's apparently been purchased by the owner of an antique shop somewhere in the Shopping Arcade.")
						:" The condition she's given Helena is that she keep her unruly sister employed and promise never to enslave her again..."
							+ "<br/>"
							+ "You're going to have to go up to Helena's nest, find Scarlett, and then tell her to return to Helena...");
		}
		
		@Override
		public String getCompletedDescription() {
			return "According to the wishes of Helena's old etiquette coach, Scarlett will from now on be working as the harpy matriarch's personal assistant.";
		}
	},

	ROMANCE_HELENA_5_SCARLETT_TRAINER(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Harpy Helper";
		}

		@Override
		public String getDescription() {
			return "The two harpies left early to head off and visit Scarlett's sister. You need to return to Helena's store during opening hours to see what your next task will be...";
		}

		@Override
		public String getCompletedDescription() {
			return "You returned to Helena's store to discover that everything is set up and ready for the harpy matriarch to start accepting customers. Before that, however, you need to help her with a couple more things...";
		}
	},

	ROMANCE_HELENA_6_ADVERTISING(QuestType.RELATIONSHIP, 1, 15) {
		@Override
		public String getName() {
			return "Advertising";
		}

		@Override
		public String getDescription() {
			return "Having been given half a dozen enchanted posters showing off Helena's beauty, your task is to put them up at the entrance of Slaver Alley in order to help advertise her store.";
		}

		@Override
		public String getCompletedDescription() {
			return "You put up posters advertising 'Helena's Boutique' at the entrance to Slaver Alley.";
		}
	},

	ROMANCE_HELENA_7_GRAND_OPENING_PREPARATION(QuestType.RELATIONSHIP, 1, 15) {
		@Override
		public String getName() {
			return "Preparing for the Grand Opening";
		}

		@Override
		public String getDescription() {
			return "After putting up the posters, Scarlett appeared and lead you back to Helena's shop."
					+ " Your new task is to get things ready for tomorrow's grand opening, which means working through the night...";
		}

		@Override
		public String getCompletedDescription() {
			return "You and Scarlett finished the preparations for the store's grand opening.";
		}
	},

	ROMANCE_HELENA_8_FINISH(QuestType.RELATIONSHIP, 1, 100) {
		@Override
		public String getName() {
			return "Preparing Drinks";
		}

		@Override
		public String getDescription() {
			return "Not wanting Scarlett to cause any trouble during the grand opening, Helena has tasked the two of you with staying in the back room and making drinks for the guests.";
		}

		@Override
		public String getCompletedDescription() {
			return "You and Scarlett stayed in the back room making drinks until the grand opening was over."
					+ "  Finally showing some appreciation for your efforts, Helena told you that she'd be willing to let you take her on a date as your reward...";
		}
	},
	
	

	ROMANCE_NATALYA_FAILED_INTERVIEW(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Interview Failed";
		}
		@Override
		public String getDescription() {
			return "Having refused to do as Natalya asked during her interview, you were thrown out of Dominion Express and told never to return...";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},

	ROMANCE_NATALYA_FAILED_CONTRACT(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Contract Refused";
		}
		@Override
		public String getDescription() {
			return "Having refused to sign the contract which Natalya offered to you, you were thrown out of Dominion Express and told never to return...";
		}
		@Override
		public String getCompletedDescription() {
			return getDescription();
		}
	},
	
	ROMANCE_NATALYA_1_INTERVIEW_START(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Interviewed";
		}
		@Override
		public String getDescription() {
			return "Natalya, the Stable Mistress at the company Dominion Express, has offered you the opportunity to be interviewed for the position of 'filly'.";
		}
		@Override
		public String getCompletedDescription() {
			return "You accepted Natalya's offer of an interview for the position of 'filly' at Dominion Express.";
		}
	},

	ROMANCE_NATALYA_2_CONTRACT_SIGNED(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Natalya's Filly";
		}
		@Override
		public String getDescription() {
			return "Having accepted the offer of an interview for the position of 'filly', you now need to successfully pass it and sign the contract.";
		}
		@Override
		public String getCompletedDescription() {
			return "You successfully passed Natalya's interview, and after you'd signed the contract, you were told that you now need to be transformed into [style.a_shemale] taur.";
		}
	},
	
	ROMANCE_NATALYA_3_TRAINING_1(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Filly Training";
		}
		@Override
		public String getDescription() {
			return "You were told by Natalya that the first part of your training will involve repeating your oral performance on one of the centaur slaves.";
		}
		@Override
		public String getCompletedDescription() {
			return "After being transformed into [style.a_shemale] taur, you began your filly training by sucking the cock of one of Dominion Express's more unruly centaur slaves.";
		}
	},

	ROMANCE_NATALYA_4_TRAINING_2(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "More Training";
		}
		@Override
		public String getDescription() {
			return "Once again, you were told by Natalya to return the following day to continue your training, which will involve learning to love giving rimjobs.";
		}
		@Override
		public String getCompletedDescription() {
			return "The second stage of your training involved wearing colourful lipstick and performing anilingus on Mistress Natalya.";
		}
	},

	ROMANCE_NATALYA_5_TRAINING_3(QuestType.RELATIONSHIP, 1, 5) {
		@Override
		public String getName() {
			return "Final Training";
		}
		@Override
		public String getDescription() {
			return "Natalya instructed you to return the following day to finish your training, which will involve giving a rimjob to a centaur slave, and being mounted and anally fucked by both Natalya and the slave whose ass you service.";
		}
		@Override
		public String getCompletedDescription() {
			return "After performing anilingus on a centaur slave and then being mounted and anally fucked by them, Natalya declared that your filly training is complete.";
		}
	},

	ROMANCE_MONICA_1_TO_THE_FARM(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "To The Farm";
		}
		@Override
		public String getDescription() {
			return "After you'd offered to help recover her personalised breast pump, Monica told you that it could be found at her old workplace; a farm located to the North-East of Elis named 'Evelyx's Dairy'."
					+ " You'll have to go to this farm and ask for Monica's breast pump...";
		}
		@Override
		public String getCompletedDescription() {
			return "Having found the farm which is Monica's old workplace, you asked for the cow-girl's personalised breast pump, and were granted a meeting with the farm's owner...";
		}
	},

	ROMANCE_MONICA_2_UNREASONABLE_DEMAND(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "An Unreasonable Demand";
		}
		@Override
		public String getDescription() {
			return "You managed to get a meeting with the farm's owner, Evelyx, who takes the form of an arrogant, greedy succubus."
					+ " Although admitting that the breast pump was worthless, she's demanding that you either give her a huge sum of flames, or sign a suspicious contract, in exchange for it...";
		}
		@Override
		public String getCompletedDescription() {
			return "You managed to obtain Monica's personalised breast pump from Evelyx.";
		}
	},

	ROMANCE_MONICA_3_THE_JOURNEY_HOME(QuestType.RELATIONSHIP, 1, 10) {
		@Override
		public String getName() {
			return "The Journey Home";
		}
		@Override
		public String getDescription() {
			return "Now that Monica's personalised breast pump is in your possession, all that's left to do is to return it to its rightful owner.";
		}
		@Override
		public String getCompletedDescription() {
			return "You returned Monica's personalised breast pump to her, much to her surprise and delight.";
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
