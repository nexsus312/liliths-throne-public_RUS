package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.utils.TreeNode;

/**
 * @since 0.1.1
 * @version 0.4
 * @author Innoxia
 */
public enum QuestLine {

	// Main quests:

	MAIN("Трон Лилит", "Вы выполнили все основные задания в этой версии!",
			QuestType.MAIN,
			QuestTree.mainQuestTree),

	// Side quests:

//	SIDE_ITEM_DISCOVERY("Item Discovery", "You have found all the different items that are in this version!",
//			QuestType.SIDE,
//			Quest.SIDE_DISCOVER_ALL_ITEMS),
//
//	SIDE_RACE_DISCOVERY("Race Discovery", "You have found all the different races that are in this version!",
//			QuestType.SIDE,
//			Quest.SIDE_DISCOVER_ALL_RACES),

	SIDE_ENCHANTMENT_DISCOVERY("Эссенции и зачарования", "Теперь вы знаете, как использовать эссенции для создания и изменения зачарованных предметов!",
			QuestType.SIDE,
			QuestTree.enchantmentTree),

	SIDE_FIRST_TIME_PREGNANCY("Залет", "С помощью Лилайи вам удалось завершить свою первую беременность. Возможно, первую из многих...",
			QuestType.SIDE,
			QuestTree.pregnancyTree),

	SIDE_FIRST_TIME_INCUBATION("Яйцеварка", "Вы успешно высидели, отложили и вылупили яица, которые были отложены внутри вас!",
			QuestType.SIDE,
			QuestTree.incubationTree),

	SIDE_SLAVERY("Рабовладелец", "Благодаря рекомендательному письму Лилайи вам удалось получить вожделенную лицензию рабовладельца!",
			QuestType.SIDE,
			QuestTree.slaveryTree),

	SIDE_ACCOMMODATION("Как дома", "Лилайя с радостью разрешила вам использовать свободные комнаты для размещения ваших друзей и родственников при условии, что вы будете оплачивать расходы, которые они понесут...",
			QuestType.SIDE,
			QuestTree.accommodationTree),

	SIDE_DOLL_STORAGE("Хранилище для кукол", "Лилайя сказала вам, что вы можете использовать любую свободную комнату для хранения купленных вами кукол...",
			QuestType.SIDE,
			QuestTree.dollStorageTree),

	SIDE_HYPNO_WATCH("Эксперимент Артура", "Вы помогли Артуру завершить исследование Гипночасов, изменяющих ориентацию, которые теперь находятся в вашем распоряжении!",
			QuestType.SIDE,
			QuestTree.hypnoWatchTree),

	SIDE_ARCANE_LIGHTNING("Магическая молния", "Артур смог извлечь секреты магической молнии из шара, который вы ему дали, что позволило вам выучить два невероятно мощных заклинания.",
			QuestType.SIDE,
			QuestTree.arcaneLightningTree),
	
	SIDE_HARPY_PACIFICATION("Злые птички", "Вам удалось успокоить всех трех матриархов гарпий, в результате чего Гнезда гарпий стали безопасными для путешествий!",
			QuestType.SIDE,
			QuestTree.angryHarpyTree),

	SIDE_SLIME_QUEEN("Королева Слизней", "Вы справились с королевой слизней!",
			QuestType.SIDE,
			QuestTree.slimeQueenTree),

	SIDE_TELEPORTATION("Проблемы с телепортацией", "Научившись телепортироваться, вы смогли сбежать со склада энфорсеров.",
			QuestType.SIDE,
			QuestTree.teleportingTree),

	SIDE_DADDY("Любознательный инкуб", "Вы разобрались с демоном, [daddy.name], который проявлял интерес к Лилайе.",
			QuestType.SIDE,
			QuestTree.daddyTree),

	SIDE_BUYING_BRAX("Обретение волка", "После того как она заставила вас выполнить для нее ряд утомительных заданий, Кэнди наконец продала вам [brax.name].",
			QuestType.SIDE,
			QuestTree.buyingBraxTree),

	SIDE_VENGAR("Тирания Венгара", "Вы разобрались с Венгаром и позаботились о том, чтобы Аксель больше не волновался.",
			QuestType.SIDE,
			QuestTree.vengarTree),


	SIDE_WES("Энфорсер-изгой", "Вам удалось успешно справиться с главой энфорсеров.",
			QuestType.SIDE,
			QuestTree.wesTree),
        
	SIDE_REBEL_BASE("Разграбление могил", "Вам удалось сбежать из заброшенного убежища повстанцев.",
		QuestType.SIDE,
		QuestTree.rebelBaseTree),

	SIDE_REBEL_BASE_FIREBOMBS("Пряные тефтели", "Вы получили постоянный запас магических огненных бомб.",
		QuestType.SIDE,
		QuestTree.rebelBaseFirebombTree),
	
	SIDE_EISEK_STALL("Ремонт", "Вы помогли Айзеку отремонтировать его ларек.",
		    QuestType.SIDE,
		    QuestTree.eisekStallTree),
	
	SIDE_EISEK_MOB("Менталитет толпы", "Вы убедили толпу, преследовавшую Айзека, оставить его в покое.",
		    QuestType.SIDE,
		    QuestTree.eisekMobTree),
	
	SIDE_EISEK_SILLYMODE("Драконьи энтузиасты", "Вы столкнулись со странной группой одержимых драконами чудаков и зачистили их подземелье.",
		    QuestType.SIDE,
		    QuestTree.eisekSillyModeTree),

	SIDE_OGLIX_BEER_BARRELS("Бонанза Пивных сучек", "Вы приобрели для Огликс еще больше бочек, что позволило вам отправить к ней четырех преступниц из ближайших переулков, чтобы они стали новыми пивными сучками!",
			QuestType.SIDE,
			QuestTree.beerBarrelTree),

	SIDE_LUNEXIS_ESCAPE("Обслуживание Лунексис", "Вы послушались приказа своей госпожи и обеспечил ей побег, тем самым предопределив свою судьбу стать одним из ее мастурбаторов",
			QuestType.SIDE,
			QuestTree.lunexisEscapeTree),

	SIDE_DOLL_FACTORY("Проблема с куклами", "Вы раскрыли правду о том, как создаются секс-куклы премиум-класса, продающиеся в «Роскошь Ловиенн»...",
			QuestType.SIDE,
			QuestTree.dollFactoryTree),
	
	// Romance quests:
	
	RELATIONSHIP_NYAN_HELP("Проблемы с поставщиком", "Вы помогли Ньян решить проблему, возникшую у нее с поставщиком.",
			QuestType.RELATIONSHIP,
			QuestTree.nyanTree),

	ROMANCE_HELENA("Помощь Ее Высочеству", "Вы успешно выполнили все задания, которые дала вам Елена, и в награду можете заказывать у нее рабынь на заказ и ходить с ней на свидания каждую пятницу вечером.",
			QuestType.RELATIONSHIP,
			QuestTree.helenaTree),

	ROMANCE_NATALYA("Дрессировка кобылок", "Пройдя обучение у госпожи Натальи, вы стали квалифицированной кобылкой и должны сексуально обслуживать рабов-кентавров «Доминион Экспресс».",
			QuestType.RELATIONSHIP,
			QuestTree.natalyaTree),

	ROMANCE_MONICA("Молочница Моника", "Вы успешно достали персональный молокоотсос Моники, и в результате она очень вам благодарна.",
			QuestType.RELATIONSHIP,
			QuestTree.monicaTree),
	;

	private String name, completedDescription;
	private QuestType type;
	private TreeNode<Quest> questTree;

	private QuestLine(String name, String completedDescription, QuestType type, TreeNode<Quest> questTree) {
		this.name = name;
		this.completedDescription = completedDescription;
		this.type = type;
		this.questTree = questTree;
	}

	public String getName() {
		return name;
	}

	public String getCompletedDescription() {
		return completedDescription;
	}

	public QuestType getType() {
		return type;
	}

	public TreeNode<Quest> getQuestTree() {
		return questTree;
	}

}
