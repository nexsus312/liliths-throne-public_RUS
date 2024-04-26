package com.lilithsthrone.game.character.persona;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.9.1
 * @author Innoxia
 */
public class Name {
	// Some help from behindthename.com's name lists to find unusual forms and/or same-letter names.
	// Name etymologies / explanations omitted to comply with their terms of use about redistributing their article contents.
	private static List<NameTriplet> human = (Util.newArrayListOfValues(
					new NameTriplet("Александр", "Алекс", "Александрия"),
					new NameTriplet("Алексий", "Алекс", "Алексия"),
					new NameTriplet("Алекс", "Алекс", "Алекс"),
					new NameTriplet("Эш", "Эши", "Эшли"),
					
					new NameTriplet("Барт", "Бэйли", "Барбара"),
					new NameTriplet("Бен", "Бенни", "Белла"),
					new NameTriplet("Бриджер", "Беверли", "Бриджит"),
					new NameTriplet("Брайан", "Бри", "Брианна"),
					new NameTriplet("Брент", "Бретт", "Бритта"),
					
					new NameTriplet("Кэри", "Кейси", "Кэденс"),
					new NameTriplet("Карл", "Кэрол", "Каролина"),
					new NameTriplet("Сесил", "Сесил", "Сесилия"),
					new NameTriplet("Чарли", "Чарли", "Чарли"),
					new NameTriplet("Крис", "Крис", "Кристина"),
					new NameTriplet("Чак", "Чарли", "Шарлотта"),
					
					new NameTriplet("Даниэль", "Дэнни", "Дани"),
					new NameTriplet("Дейл", "Дана", "Диана"),
					new NameTriplet("Дэвид", "Деб", "Дебби"),
					new NameTriplet("Дин", "Девин", "Дианна"),
					
					new NameTriplet("Эдвард", "Эдди", "Эдна"),
					new NameTriplet("Эли", "Эмери", "Эвелин"),
					new NameTriplet("Эллиот", "Эмерсон", "Элейн"),
					new NameTriplet("Эммануэль", "Ману", "Эммануэль"),
					new NameTriplet("Эмиль", "Эм", "Эмили"),
					new NameTriplet("Эван", "Эвелин", "Иветт"),
					
					new NameTriplet("Феликс", "Флик", "Фелисити"),
					new NameTriplet("Фрэнк", "Фрэнки", "Фрэнсис"),
					new NameTriplet("Фред", "Фредди", "Гейл"),
					
					new NameTriplet("Гэйб", "Гэбби", "Gale"),
					new NameTriplet("Джордж", "Джорджи", "Джинджер"),
					new NameTriplet("Грег", "Грей", "Грейс"),
					
					new NameTriplet("Гарри", "Харли", "Хейли"),
					new NameTriplet("Генри", "Хенни", "Генриетта"),
					new NameTriplet("Хэнк", "Хайден", "Холли"),
					
					new NameTriplet("Иэн", "Индиго", "Илия"),
					new NameTriplet("Изидор", "Иззи", "Изабель"),

					new NameTriplet("Джеймс", "Джейми", "Джей"),
					new NameTriplet("Джек", "Джеки", "Жаклин"),
					new NameTriplet("Дженсен", "Джеки", "Жасмин"),
					new NameTriplet("Гарет", "Джей", "Дженнифер"),
					new NameTriplet("Иэн", "Жан", "Жанна"),
					new NameTriplet("Джером", "Джерри", "Джери"),
					new NameTriplet("Джесси", "Джесс", "Джессика"),
					new NameTriplet("Джон", "Жан", "Джейн"),
					new NameTriplet("Джозеф", "Джоджо", "Джози"),

					new NameTriplet("Карл", "Карол", "Карла"),
					new NameTriplet("Кевин", "Кел", "Кейти"),
					new NameTriplet("Каспер", "Кэт", "Кэтрин"),
					new NameTriplet("Кеннет", "Келли", "Кендра"),
					new NameTriplet("Кристофер", "Крис", "Кристи"),

					new NameTriplet("Лоуренс", "Лорен", "Лорен"),
					new NameTriplet("Ли", "Ли", "Леа"),
					new NameTriplet("Леонард", "Линден", "Леа"),
					new NameTriplet("Лен", "Луми", "Лора"),
					new NameTriplet("Лес", "Лесли", "Лесли"),
					new NameTriplet("Льюис", "Лу", "Луиза"),
					
					new NameTriplet("Мэдисон", "Мэдди", "Мадлен"),
					new NameTriplet("Марк", "Марион", "Мария"),
					new NameTriplet("Максвелл", "Макс", "Максин"),
					new NameTriplet("Мелвин", "Мел", "Мелисса"),
					new NameTriplet("Майкл", "Мики", "Микаэла"),
					//new NameTriplet("Mike", "Max", "Miranda"), // moved "Miranda" to "Randy/Randi/Miranda"
					
					new NameTriplet("Натан", "Нэт", "Натали"),
					new NameTriplet("Николас", "Ники", "Николь"),
					new NameTriplet("Норман", "Ноубл", "Нора"),
					
					new NameTriplet("Оскар", "Оделл", "Опал"),
					new NameTriplet("Оливер", "Оли", "Оливия"),
					
					new NameTriplet("Пат", "Пэтси", "Триша"),
					new NameTriplet("Пейдж", "Паркер", "Пейдж"),
					new NameTriplet("Питер", "Пейтон", "Петра"),
					new NameTriplet("Филлип", "Пип", "Фиби"),
					
					new NameTriplet("Квентин", "Куинн", "Квинта"),
					
					new NameTriplet("Рэнди", "Рэнди", "Миранда"),
					new NameTriplet("Ричард", "Рики", "Рэйчел"),
					new NameTriplet("Роберт", "Робби", "Робин"),
					
					new NameTriplet("Сэмюэл", "Сэм", "Саманта"),
					new NameTriplet("Стивен", "Стеф", "Стефани"),
					//new NameTriplet("Stanley", "Sam", "Stephanie"),
					new NameTriplet("Стэн", "Саша", "Саммер"),
					
					new NameTriplet("Теренс", "Терри", "Тереза"),
					new NameTriplet("Теодор", "Тедди", "Дора"),
					new NameTriplet("Томас", "Томми", "Тэмсин"),
					new NameTriplet("Тим", "Темпл", "Тина"),
					new NameTriplet("Трейси", "Трейси", "Тесса"),
					new NameTriplet("Тони", "Тони", "Тоня"),

					new NameTriplet("Улисс", "Амбер", "Урсула"),

					new NameTriplet("Валентин", "Вэл", "Валери"),
					new NameTriplet("Вин", "Вэл", "Вайолет"),
					new NameTriplet("Виктор", "Вики", "Виктория"),
					new NameTriplet("Верджил", "Вик", "Вирджиния"),

					new NameTriplet("Уоллес", "Уоллис", "Ванда"),
					new NameTriplet("Уильям", "Винтер", "Уитни"),
					new NameTriplet("Уилл", "Уинн", "Виллоу"),
					new NameTriplet("Уинн", "Уинн", "Гвен")
	));
	
	private static List<NameTriplet> equine = (Util.newArrayListOfValues(
					new NameTriplet("Аква", "Аква", "Аква"),
					
					new NameTriplet("Брэмбл", "Брэмбл", "Брэмбл"),

					new NameTriplet("Дэшер", "Дэшер", "Дэшер"),
					new NameTriplet("Дэззл", "Дэззл", "Дэззл"),

					new NameTriplet("Флинт", "Флинт", "Флинт"),
					
					new NameTriplet("Флит", "Флит", "Флит"),
					
					new NameTriplet("Миднайт", "Миднайт", "Миднайт"),
					new NameTriplet("Мунвинд", "Мунвинд", "Мунвинд"),

					new NameTriplet("Нимбус", "Нимбус", "Нимбус"),

					new NameTriplet("Перл", "Перл", "Перл"),
					new NameTriplet("Прикси", "Прикси", "Прикси"),
					
					new NameTriplet("Скайфит", "Скайфит", "Скайфит"),
					new NameTriplet("Старр", "Старр", "Старр"),
					new NameTriplet("Спирит", "Спирит", "Спирит"),
					
					new NameTriplet("Тандермейн", "Тандермейн", "Тандермейн"),
					new NameTriplet("Твайлайт", "Твайлайт", "Твайлайт"),
					
					new NameTriplet("Вайлдлайт", "Вайлдлайт", "Вайлдлайт")));
	
	// Similar to equine names
	private static List<NameTriplet> reindeer = (Util.newArrayListOfValues(
			
			new NameTriplet("Дэшер", "Дэшер", "Дэшер"),
			new NameTriplet("Дэнсер", "Дэнсер", "Дэнсер"),
			new NameTriplet("Прэнсер", "Прэнсер", "Прэнсер"),
			new NameTriplet("Виксен", "Виксен", "Виксен"),
			new NameTriplet("Комет", "Комет", "Комет"),
			new NameTriplet("Купид", "Купид", "Купид"),
			new NameTriplet("Дандер", "Дандер", "Дандер"),
			new NameTriplet("Бликсем", "Бликсем", "Бликсем"),
			
			new NameTriplet("Аква", "Аква", "Аква"),
			
			new NameTriplet("Брэмбл", "Брэмбл", "Брэмбл"),

			new NameTriplet("Дэшер", "Дэшер", "Дэшер"),
			new NameTriplet("Дэззл", "Дэззл", "Дэззл"),

			new NameTriplet("Флинт", "Флинт", "Флинт"),
			
			new NameTriplet("Флит", "Флит", "Флит"),
			
			new NameTriplet("Миднайт", "Миднайт", "Миднайт"),
			new NameTriplet("Мунвинд", "Мунвинд", "Мунвинд"),

			new NameTriplet("Нимбус", "Нимбус", "Нимбус"),

			new NameTriplet("Перл", "Перл", "Перл"),
			new NameTriplet("Прикси", "Прикси", "Прикси"),
			
			new NameTriplet("Скайфит", "Скайфит", "Скайфит"),
			new NameTriplet("Старр", "Старр", "Старр"),
			new NameTriplet("Спирит", "Спирит", "Спирит"),
			
			new NameTriplet("Тандермейн", "Тандермейн", "Тандермейн"),
			new NameTriplet("Твайлайт", "Твайлайт", "Твайлайт"),
			
			new NameTriplet("Вайлдлайт", "Вайлдлайт", "Вайлдлайт")));
	
	// No offence if your name is on here... x_x
	// Significantly modified with help from behindthename.com. (No more infinite Carls!)
	// Code from later on edited a bit to throw some of these names onto other NPCs.
	private static List<NameTriplet> prostitute = (Util.newArrayListOfValues(
					new NameTriplet("Арло", "Арден", "Арлин"),
					new NameTriplet("Амброуз", "Эмбер", "Эмбер"),
					new NameTriplet("Август", "Обри", "Осень"),

					new NameTriplet("Болдуин", "Бэмби", "Бэмби"),
					new NameTriplet("Брэндон", "Брэнди", "Брэнди"),
					new NameTriplet("Бретт", "Бритт", "Бритни"),
					new NameTriplet("Брайан", "Бринн", "Брианна"),

					new NameTriplet("Кэссиди", "Кэсси", "Кассандра"),
					new NameTriplet("Карл", "Чарли", "Шарлин"),
					new NameTriplet("Чад", "Ченнинг", "Шантель"),
					new NameTriplet("Чип", "Ченнинг", "Хлоя"),
					new NameTriplet("Клаудио", "Клод", "Клаудия"),
					new NameTriplet("Коул", "Конни", "Кортни"),
					new NameTriplet("Крис", "Крисси", "Кристал"),
					new NameTriplet("Кейси", "Кейси", "Кейси"),

					new NameTriplet("Дом", "Долли", "Долли"),
					new NameTriplet("Девон", "Девон", "Девон"),
					new NameTriplet("Дейл", "Дакота", "Дакота"),

					new NameTriplet("Эмметт", "Эммалу", "Эммалу "), // Great Scott!
					
					new NameTriplet("Хит", "Хизер", "Хизер"),

					new NameTriplet("Джимми", "Джинни", "Дженни"),
					new NameTriplet("Джо", "Джо", "Джолин"),

					new NameTriplet("Кайл", "Кайли", "Кайла"),
					new NameTriplet("Кен", "Кенни", "Кендра"),
					new NameTriplet("Крис", "Крис", "Криста"),
					new NameTriplet("Келси", "Келси", "Келси"),

					new NameTriplet("Лоуренс", "Лорен", "Лорен"),
					
					new NameTriplet("Митч", "Мисти", "Мисти"),
					new NameTriplet("Мел", "Мел", "Мелоди"),
					new NameTriplet("Майк", "Ноэль", "Ноэль"),
					
					new NameTriplet("Ники", "Никки", "Никки"),
					new NameTriplet("Ноэль", "Noel", "Noelle"),
		
					new NameTriplet("Пирс", "Феникс", "Пенелопа"),
					
					new NameTriplet("Рис", "Рис", "Реба"),
					new NameTriplet("Ренар", "Рене", "Рене"),
					new NameTriplet("Руди", "Руби", "Руби"),

					new NameTriplet("Саванна", "Саванна", "Саванна"),
					new NameTriplet("Сэм", "Сэм", "Саманта"),
					new NameTriplet("Скотт", "Шелби", "Скарлет"),
					new NameTriplet("Сет", "Септембер", "Серена"),
					new NameTriplet("Шелби", "Шелби", "Шелби"),
					new NameTriplet("Шон", "Шейн", "Шона"),
					new NameTriplet("Сид", "Сидни", "Сьерра"),

					new NameTriplet("Тэмми", "Тэмми", "Тэмми"),
					new NameTriplet("Тейт", "Тара", "Тара"),
					new NameTriplet("Тейлор", "Тейлор", "Тейлор"),
					new NameTriplet("Тристан", "Трина", "Трина"),
					
					new NameTriplet("Винсент", "Вик", "Виксен"),
					
					new NameTriplet("Янси", "Йорки", "Йоланда")
		
					//new NameTriplet("Urleen", "Urleen", "Urleen") // supplanted by the Arlo/Arden/Arleen triplet - "Urleen" seems much rarer
	));
	
	public static List<NameTriplet> petNames = Util.newArrayListOfValues(
			new NameTriplet("Эйс", "Эбби", "Абби"),
			new NameTriplet("Бандит", "Кейси", "Бэмби"),
			new NameTriplet("Чемпион", "Дотти", "Кэнди"),
			new NameTriplet("Герцог", "Dottie", "Герцогиня"),
			new NameTriplet("Эмбер", "Эмбер", "Эмбер"),
			new NameTriplet("Феликс", "Феррис", "Фокси"),
			new NameTriplet("Ганнер", "Голди", "Голди"),
			new NameTriplet("Инди", "Инди", "Айви"),
			new NameTriplet("Джет", "Джуэл", "Джой"),
			new NameTriplet("Кинг", "Киппер", "Китти"),
			new NameTriplet("Лео", "Лу", "Лола"),
			new NameTriplet("Максвелл", "Макс", "Максин"),
			new NameTriplet("Оли", "Олли", "Оливия"),
			new NameTriplet("Пеппер", "Пенни", "Персик"),
			new NameTriplet("Скаут", "Сэнди", "Сэнди"),
			new NameTriplet("Спот", "Сокс", "Сокс"),
			new NameTriplet("Техас", "Тэсс", "Тесси"),
			new NameTriplet("Виски", "Вискерс", "Виллоу"));
	
	public static final String[] surnames = new String[] {
			"Адамс", "Али", "Аллен", "Андерсон",
			"Эндрюс", "Армстронг", "Аткинсон", "Бейли",
			"Бейкер", "Баркер", "Барнес", "Белл",
			"Беннетт", "Берри", "Бут", "Брэдли",
			"Брукс", "Браун", "Батлер", "Кэмпбелл",
			"Карр", "Картер", "Чемберс", "Чепмен",
			"Кларк", "Кларке", "Коул", "Коллинз",
			"Кук", "Купер", "Кокс", "Каннингем",
			"Дэвис", "Дэвис", "Доусон", "Дин",
			"Диксон", "Эдвардс", "Эллис", "Эванс",
			"Фишер", "Фостер", "Фокс", "Гарднер",
			"Джордж", "Гибсон", "Гилл", "Гордон",
			"Грэм", "Грант", "Грэй", "Грин",
			"Гриффитс", "Холл", "Гамильтон", "Харпер",
			"Харрис", "Харрисон", "Харт", "Харви","Хилл",
			"Холмс", "Хадсон", "Хьюз","Хант", "Хантер",
			"Джексон", "Джеймс","Дженкинс", "Джонсон",
			"Джонстон", "Джонс","Каур", "Келли",
			"Кеннеди", "Хан","Кинг", "Найт", "Лейн",
			"Лоуренс","Лосон", "Ли", "Льюис",
			"Ллойд","Макдональд", "Маршалл", "Мартин",
			"Мейсон","Мэттьюс", "Макдональд", "Миллер",
			"Миллс","Митчелл", "Мур", "Морган",
			"Моррис","Мерфи", "Мюррей", "Оуэн",
			"Палмер","Паркер", "Патель", "Перс",
			"Пирсон","Филлипс", "Пул", "Пауэлл",
			"Прайс","Рид", "Рейнольдс", "Ричардс",
			"Ричардсон","Робертс", "Робертсон", "Робинсон",
			"Роджерс","Роуз", "Росс", "Рассел",
			"Райан","Сондерс", "Скотт", "Шоу",
			"Симпсон","Смит", "Спенсер", "Стивенс",
			"Стьюарт","Стоун", "Тейлор", "Томас",
			"Томпсон","Томсон", "Тернер", "Уокер",
			"Уолш","Уорд", "Уотсон", "Уоттс",
			"Вебб","Уеллс", "Уэст", "Уайт",
			"Вилкинсон","Уильямс", "Уильямсон", "Уилсон",
			"Вуд","Райт", "Янг"};
	
	private static final String[] youkoSurnames = new String[] {
"Абико", "Або", "Абурая", "Ачикита",
"Адати", "Адачихара", "Агано", "Агата",
"Агацума", "Агава", "Агуни", "Ахане",
"Аида", "Аихара", "Айкава", "Айкути",
"Айкё", "Аимото", "Аинара", "Айно",
"Айсака", "Аюити", "Акагава", "Акаги",
"Акахоши", "Акаи", "Акаике", "Акамацу",
"Акаминэ", "Аканиши", "Акано", "Акасаки",
"Акаси", "Акасиро", "Акасита", "Акацуки",
"Акацуцуми", "Акэми", "Аки", "Акиба",
"Акибара", "Акимото", "Акино", "Акисато",
"Акисима", "Акисино", "Акита", "Акия",
"Амати", "Амагаи", "Амагава", "Амаи",
"Амамия", "Амано", "Амари", "Аматани",
"Амая", "Амэмори", "Амэцутчи", "Амуро",
"Амусан", "Анабуки", "Андо", "Анно",
"Андзай", "Аоба", "Аои", "Аоикэ",
"Аоки", "Аоминэ", "Аонума", "Аота",
"Аояги", "Аояма", "Аозаки", "Аозора",
"Ара", "Арагаки", "Арай", "Аракаки",
"Аракава", "Араки", "Аранами", "Араси",
"Арасиро", "Арата", "Аратани", "Арая",
"Арима", "Ариока", "Арисава", "Арита",
"Ариёси", "Аса", "Асахина", "Асаи",
"Асака", "Асакава", "Асано", "Асато",
"Асика", "Атали", "Ацуда", "Аяно",
"Аянокодзи", "Аянокодзи", "Адзахара", "Адзума",
"Баба", "Бандо", "Будо", "Бусида",
"Чабасира", "Чагэ", "Тиаки", "Тиба",
"Чибана", "Тигуса", "Тикафудзи", "Тино",
"Тисака", "Тиура", "Тёсокабэ", "Тёсокабэ",
"Датэ", "Дэгути", "Дои", "Дотани",
"Эгути", "Эдзири", "Энацу", "Эндо",
"Энокида", "Эномото", "Это", "Этоу",
"Фудзи", "Фудзихара", "Фудзихаси", "Фудзии",
"Фудзикава", "Фудзимори", "Фудзимура", "Фудзинака",
"Фудзино", "Фудзиномия", "Фудзисаки", "Фудзисато",
"Фудзисава", "Фудзиура", "Фудзивара", "Фудзияма",
"Фудзиёси", "Фукагаи", "Фуками", "Фукасэ",
"Фукуда", "Фукухара", "Фукуидзуми", "Фукумото",
"Фукунага", "Фукусима", "Фукуяма", "Фукуё",
"Фурукава", "Фурусава", "Фурусэ", "Фуруя",
"Футаба", "Футамура", "Фуюки", "Гато",
"Года", "Гото", "Готох", "Гоя",
"Гусикэн", "Хатимицу", "Хатимура", "Хада",
"Хага", "Хагино", "Хагивара", "Хадзимэ",
"Хама", "Хамада", "Хамадатэ", "Хамагути",
"Хамакава", "Хамамура", "Хамано", "Хамазаки",
"Ханабуса", "Ханаи", "Ханамура", "Ханадзава",
"Ханда", "Ханэда", "Ханэяма", "Ханю",
"Ханюу", "Хара", "Харада", "Харагути",
"Харамото", "Харигаэ", "Харуки", "Харуна",
"Харуно", "Харута", "Харуяма", "Хасэгава",
"Хаси", "Хасигути", "Хасикура", "Хасиока",
"Хасира", "Хаситани", "Хасияма", "Хата",
"Хатакэ", "Хатано", "Хатая", "Хацу",
"Хаттори", "Хаябуса", "Хаягава", "Хаякава",
"Хаяма", "Хаясака", "Хаясибара", "Хаясида",
"Хидака", "Хига", "Хигаси", "Хигасида",
"Хигасияма", "Хими", "Хино", "Хинодэ",
"Хираи", "Хиракава", "Хирамацу", "Хирано",
"Хирасака", "Хирасава", "Хирасима", "Хирата",
"Hиратани", "Хирои", "Хироми", "Хироно",
"Хиросава", "Хиросэ", "Хиросима", "Хирота",
"Хирума", "Хисамацу", "Хитараши", "Хитотосэ",
"Хитоцуянаги", "Ходзё", "Хокиное", "Хомура",
"Хори", "Хориэ", "Хоригомэ", "Хорикита",
"Хорино", "Хоши", "Хосимия", "Хосино",
"Хосидзаки", "Хосода", "Хосокава", "Хосоо",
"Ходзё", "Ибуки", "Итида", "Итихара",
"Итихаси", "Итикава", "Итино", "Итиномия",
"Итиока", "Ида", "Иэири", "Иэками",
"Игараси", "Игэ", "Икари", "Икэ",
"Икэхара", "Икэмото", "Икэру", "Икэсуги",
"Икута", "Имада", "Имаэда", "Имагава",
"Имаи", "Имайси", "Имамура", "Имари",
"Имауока", "Имата", "Инагаки", "Инамура",
"Инадзума", "Иносиси", "Иноуе", "Инуи",
"Инукай", "Иори", "Исаго", "Исаму",
"Исаияма", "Исида", "Исидо", "Исигуро",
"Исихара", "Исии", "Исикура", "Исимото",
"Исиути", "Исивата", "Исияма", "Исидзука",
"Исобэ", "Исогаи", "Исодзаки", "Ивааки",
"Иваэ", "Ивамото", "Ивано", "Иваока",
"Ивасаки", "Ивасимидзу", "Ивата", "Иватани",
"Иваяма", "Идзухара", "Идзуми", "Идзумо",
"Дзинноути", "Дзюнко", "Кабэ", "Кабуто",
"Кадокава", "Кадомацу", "Кадосима", "Кадота",
"Каэцу", "Кага", "Кайба", "Кадзи",
"Кадзитани", "Кадзиура", "Кадзивара", "Каки",
"Какихара", "Какимура", "Какинума", "Какита",
"Каку", "Какутани", "Камата", "Камэй",
"Каменаси", "Камино", "Камия", "Камияма",
"Камори", "Камото", "Канбара", "Канбаяси",
"Канбэ", "Канда", "Канэда", "Канэки",
"Канэко", "Канэмару", "Канэсиро", "Канно",
"Канродзи", "Карамацу", "Карасу", "Касаи",
"Касэй", "Касива", "Касивабара", "Касивада",
"Касивадэ", "Касивадо", "Касиваэда", "Касиваги",
"Касивахара", "Катагири", "Кацура", "Кава",
"Кавабата", "Кавада", "Кавахара", "Каваи",
"Кавакита", "Кавамото", "Кавамура", "Каванабэ",
"Каванака", "Каваниси", "Кавано", "Кавасаки",
"Кавасима", "Кавасита", "Кавата", "Каваути",
"Кадзама", "Кадзами", "Кадзэ", "Кадзэхая",
"Кадзэтани", "Кадзуюки", "Кэнма", "Китида",
"Кида", "Кидамура", "Кидо", "Кихара",
"Кикё", "Киндзё", "Кино", "Киносита",
"Кинугаса", "Киригая", "Киримура", "Кирисима",
"Кирия", "Киси", "Кисимото", "Китабаяси",
"Китагава", "Китахара", "Китамура", "Китани",
"Китано", "Китао", "Китаока", "Кия",
"Кийоко", "Кийомидзу", "Кийота", "Кийотакэ",
"Кобаси", "Кодайра", "Коганэ", "Кохира",
"Коидэ", "Койгакубо", "Койкэ", "Кодзима",
"Кокава", "Коки", "Комацу", "Комацудзаки",
"Комия", "Комура", "Конака", "Конда",
"Кондо", "Кондоу", "Кониси", "Конно",
"Конпару", "Косака", "Косуги", "Котакэ",
"Котани", "Котобуки", "Коцудзаки", "Коцуки", "Коумото",
"Кояма", "Коясу", "Козуэ", "Кодзукэ",
"Кодзуки", "Куба", "Кубо", "Кубота",
"Кучики", "Кудо", "Куга", "Кугимия",
"Кухара", "Кудзира", "Кумагаи", "Кумаи",
"Кумаки", "Кунида", "Кунимацу", "Кунисаки",
"Курама", "Курасава", "Курата", "Курибаяси",
"Курихара", "Курихаши", "Куримото", "Курису",
"Курияма", "Куридзука", "Куроба", "Курода",
"Куроги", "Курохаши", "Куроива", "Курокава",
"Куроки", "Куроко", "Куромия", "Куронума",
"Куроcака", "Куроcаки", "Курошима", "Куроянаги",
"Кусаянаги", "Кусида", "Кусиэда", "Кусуноки",
"Кувабара", "Кувахара", "Кувако", "Кёгоку",
"Кюгоку", "Мати", "Матида", "Маэбара",
"Маэдзима", "Маэкава", "Маэшима", "Маэямада",
"Мадзима", "Макимура", "Макино", "Макита",
"Манабэ", "Манака", "Масимо", "Масуяма",
"Мацу", "Мацубара", "Мацубаяси", "Мацуда",
"Мацудаира", "Мацухаси", "Мацуи", "Мацуката",
"Мацуки", "Мацумаэ", "Мацумура", "Мацунага",
"Мацунава", "Мацуно", "Мацуо", "Мацусима",
"Мацуура", "Мацуяма", "Мацуюки", "Мацузаки",
"Маватари", "Митизое", "Мидорикава", "Мифунэ",
"Михара", "Миками", "Микадзуки", "Мики",
"Минамото", "Минато", "Минатодзаки", "Минэ",
"Мисаки", "Мисима", "Мисо", "Мита",
"Мицуэ", "Мицуги", "Мицугу", "Мицуи",
"Миура", "Мива", "Мия", "Миябэ",
"Миягути", "Мияхара", "Мияити", "Миякэ",
"Мияко", "Мияма", "Мияно", "Мияра",
"Миясита", "Мията", "Мияути", "Миядзаки",
"Миядзато", "Миядзава", "Мидзуфука", "Мидзухара",
"Мидзукава", "Мидзуно", "Мидзусава", "Мидзута",
"Мидзутама", "Мидзутани", "Мотидзуки", "Моэги",
"Момои", "Момосэ", "Мориаи", "Морифудзи",
"Морихара", "Морихэй", "Морикава", "Морикита",
"Моримото", "Моринака", "Морисита", "Морита",
"Мориути", "Мория", "Морияма", "Мотэки",
"Мотохаси", "Мотомэ", "Мотодзава", "Мукаи",
"Мунэкава", "Мура", "Мурахама", "Мурахаси",
"Мураками", "Муракава", "Муракита", "Мурамацу",
"Муранака", "Мураока", "Мурасима", "Мурата",
"Муратаги", "Мусакодзи", "Мусакоудзи", "Мусанокодзи",
"Мусанокоудзи", "Муто", "Муцу", "Муцуми",
"Мёуи", "Наэги", "Нагаи", "Нагамацу",
"Нагано", "Нагао", "Нагаока", "Нагасима",
"Нагасу", "Нагато", "Нагацука", "Нагаяма",
"Найто", "Накада", "Накафудзи", "Накагамэ",
"Накагава", "Накаи", "Накамацу", "Накаминэ",
"Накамото", "Наканиси", "Накао", "Наката",
"Накаути", "Накаура", "Накаяма", "Нанами",
"Нанасима", "Нанацуки", "Нара", "Нарисава",
"Нарита", "Нару", "Нацукава", "Нацуми",
"Нэхо", "Нэдзи", "Ниикура", "Никаидо",
"Ниномия", "Ниси", "Нисида", "Нисино",
"Нисио", "Нисияма", "Нитта", "Нобира",
"Нобунага", "Нодзима", "Нодзири", "Номи",
"Номура", "Нонака", "Ното", "Нотоу",
"Нума", "Обара", "Ода", "Ога",
"Огами", "Огасавара", "Огава", "Огита",
"Огура", "Огури", "Охама", "Охара",
"Охаси", "Охаяси", "Охка", "Охори",
"Оотани", "Ока", "Окада", "Окамото",
"Окамура", "Окано", "Окасима", "Окава",
"Оказаки", "Окоти", "Окота", "Оку",
"Окубо", "Окудайра", "Окугава", "Окукава",
"Окумура", "Окуно", "Окуяма", "Омори",
"Омото", "Омура", "Ониси", "Оно",
"Оно", "Оноуэ", "Оогами", "Оокоути",
"Оотани", "Оотоно", "Осака", "Осаки",
"Осако", "Осато", "Осава", "Осима",
"Ота", "Отака", "Отаки", ...таки", "Отани",
"Отонари", "Оцука", "Оцуки",
"Овари", "Оякава", "Ояма", "Оямада",
"Одзаки", "Одзава", "Одзу", "Рикимару",
"Рин", "Ринбаяси", "Рицусима", "Рока",
"Рояма", "Рюдзин", "Рюко", "Рюминэ",
"Рюуэн", "Рюдзаки", "Саэки", "Сагами",
"Сайхара", "Саиондзи", "Саитама", "Сака",
"Сакабаяси", "Сакагути", "Сакахара", "Сакаи",
"Сакаки", "Сакамото", "Саканэ", "Сакатани",
"Саки", "Сакимото", "Сакияма", "Сакума",
"Сакура", "Сакурай", "Сакурами", "Сакурано",
"Сакуразака", "Самэдзима", "Самон", "Санада",
"Сандзё", "Сандзёу", "Сано", "Сасано",
"Сасаяма","Сасори", "Сатоми", "Сатоя",
"Сава", "Савада", "Савасиро", "Садза",
"Сэки", "Сэкигути", "Сэндзю", "Сэнри",
"Сэридзава", "Сэто", "Сэтоу", "Сэцуси",
"Сибасаки", "Сибата", "Сибаяма", "Сибутани",
"Сибуя", "Сима", "Симада", "Симадзу",
"Симамото", "Симамура", "Симаока", "Симадзаки",
"Симадзу", "Симэ", "Симоно", "Симоока",
"Симоцуки", "Симояма", "Симура", "Синдэн",
"Синкай", "Синобу", "Синода", "Синохара",
"Синяма", "Сио", "Сиокава", "Сираи",
"Сираиси", "Сиракава", "Сиромори", "Сисидо",
"Сисигами", "Сё", "Сёдзи", "Сёю",
"Сёю", "Сонода", "Суэно", "Суэока",
"Суга", "Сугано", "Сугавара", "Сугиэда",
"Сугихара", "Сугимори", "Сугимото", "Сугимура",
"Сугино", "Сугита", "Сугитани", "Сугиура",
"Сугияма", "Сумису", "Сумитомо", "Сунадори",
"Судзукадзе", "Судзумура", "Судзутани", "Судзуя",
"Тада", "Тадокоро", "Тахара", "Таира",
"Тадзири", "Такада", "Такаги", "Такагири",
"Такахара", "Такахата", "Такахидэ", "Такаи",
"Такаиси", "Такаки", "Такакува", "Такамару",
"Такамацу", "Таками", "Такамицу", "Такамори",
"Такамура", "Таканаси", "Такано", "Такао",
"Такасэ", "Такасу", "Таката", "Такаяма",
"Такаянаги", "Такадзато", "Такэда", "Такэхара",
"Такэи", "Такэмия", "Такэмидзу", "Такэмура",
"Такэно", "Такэсита", "Такэтацу", "Такэути",
"Таки", "Такиное", "Тамагава", "Тамаи",
"Тамара", "Тамару", "Тамасиро", "Тамацуки",
"Тамаяма", "Тамон", "Тамура", "Танабата",
"Тани", "Танигава", "Танигути", "Танихара",
"Таникава", "Танимото", "Танияма", "Тандзи",
"Таносэ", "Танума", "Татэваки", "Тацуда",
"Тацуми", "Тацуока", "Тацусима", "Тадзава",
"Тэкадзэва", "Тэрада", "Тэрасаки", "Тэраути",
"Тэдзука", "Тобэ", "Тода", "Тогами",
"Тодзё", "Токуфудзи", "Токугава", "Токуи",
"Томацу", "Томииэ", "Томита", "Томиясу",
"Томоэда", "Томосака", "Томура", "Торигое",
"Тодзё", "Тоя", "Тоёда", "Тоёгути",
"Тоёта", "Цубо", "Цубои", "Цутида",
"Цутии", "Цутия", "Цуда", "Цудзуми",
"Цудзури", "Цугэ", "Цудзихара", "Цудзи",
"Цудзимура", "Цудзита", "Цудзиура", "Цукахара",
"Цукамото", "Цукигата", "Цукими", "Цукино",
"Цукияма", "Цукиёми", "Цукуси", "Цунэмацу",
"Цунода", "Цунои", "Цуруока", "Цурута",
"Цутэна", "Цутсуми", "Цудзуки", "Цудзуно",
"Утида", "Учиха", "Утияма", "Уэхара",
"Уэки", "Уэмацу", "Уэмура", "Уэсита",
"Уэсуги", "Уэта", "Умадзири", "Умэда",
"Умэхара", "Умэдзава", "Умон", "Уно",
"Урамото", "Ураока", "Урусихара", "Утада",
"Уцуномия", "Удзумаки", "Вада", "Вагахара",
"Вакабаяси", "Вакаки", "Вакамацу", "Ваката",
"Вакатути", "Вакуни", "Вакури", "Ватабэ",
"Ватануки", "Ватари", "Ябэ", "Яда",
"Ягами", "Яги", "Ягира", "Якумо",
"Яма", "Ямагата", "Ямаха", "Ямахаси",
"Ямакава", "Ямамидзу", "Ямамура", "Яманака",
"Яманэ", "Ямасато", "Яматани", "Янаги",
"Янагимото", "Янагисава", "Янаи", "Ясуи",
"Ясуки", "Ясуниси", "Ясураока", "Ясуяма",
"Ясудзато", "Ядзаки", "Ёити", "Ёитиэн",
"Ёитимаэ", "Ёкохама", "Ёкомидзо", "Ёкомура",
"Ёкотани", "Ёкотэ", "Ёкояма", "Ёмохиро",
"Ёнага", "Ёнаминэ", "Ёнэ", "Ёнэда",
"Ёнэити", "Ёнэдзава", "Ёсихама", "Ёсихара",
"Ёсии", "Ёсикава", "Ёсимото", "Ёсимура",
"Ёсино", "Ёсинума", "Ёсиока", "Ёсисава",
"Юи", "Юкимори", "Юкитомо", "Юкияма",
"Юкидзомэ", "Юми", "Юума", "Дзабацу"};
	
	private static String[] lilinNames = new String[] {
			"Лавиния",
			"Ласиэль",
			"Лисиэт",
			"Лианна",
			"Лилиша",
			"Линикси",
			"Лилория"};
	
	private static Map<String, List<NameTriplet>> racialNames = new HashMap<>();
	
	static {
		for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
			if(subspecies.getRace()==Race.HORSE_MORPH) {
				racialNames.put(Subspecies.getIdFromSubspecies(subspecies), equine);
			}
			if(subspecies.getRace()==Race.REINDEER_MORPH) {
				racialNames.put(Subspecies.getIdFromSubspecies(subspecies), reindeer);
			}
		}
		
		// Modded names:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", null, "names");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String raceID = innerEntry.getKey().replaceAll("_race", "");
					raceID = raceID.replaceAll("_names", "");
					
					Map<String, List<NameTriplet>> importedNames = importNames(innerEntry.getValue(), entry.getKey(), true, raceID);
					if(importedNames!=null && !importedNames.isEmpty()) {
						for(Entry<String, List<NameTriplet>> importedNameEntry : importedNames.entrySet()) {
							racialNames.putIfAbsent(importedNameEntry.getKey(), new ArrayList<>());
							racialNames.get(importedNameEntry.getKey()).addAll(importedNameEntry.getValue());
						}
//						System.out.println("Added modded names of race: "+raceID);
					}
				} catch(Exception ex) {
					System.err.println("Loading modded names failed. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res names:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", null, "names");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String raceID = innerEntry.getKey().replaceAll("_race", "");
					raceID = raceID.replaceAll("_names", "");
					
					Map<String, List<NameTriplet>> importedNames = importNames(innerEntry.getValue(), entry.getKey(), false, raceID);
					if(importedNames!=null && !importedNames.isEmpty()) {
						for(Entry<String, List<NameTriplet>> importedNameEntry : importedNames.entrySet()) {
							racialNames.putIfAbsent(importedNameEntry.getKey(), new ArrayList<>());
							racialNames.get(importedNameEntry.getKey()).addAll(importedNameEntry.getValue());
						}
//						System.out.println("Added res names of race: "+raceID);
					}
				} catch(Exception ex) {
					System.err.println("Loading names failed. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
	}
	

	private static Map<String, List<NameTriplet>> importNames(File XMLFile, String author, boolean mod, String raceID) {
		if (XMLFile.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in statusEffect files it's <statusEffect>
				
				boolean additionalNames = Boolean.valueOf(coreElement.getAttribute("additional"));
				
				Map<String, List<NameTriplet>> importedNameMap = new HashMap<>();
				
				for(Element outerElement : coreElement.getAllOf("subspecies")) {
					String subspeciesId = outerElement.getAttribute("id");
					List<NameTriplet> importedNames = new ArrayList<>();
					for(Element e : outerElement.getAllOf("nameTriplet")) {
						String femName = e.getOptionalFirstOf("фем").isPresent()?e.getMandatoryFirstOf("фем").getTextContent():null;
						String andName = e.getOptionalFirstOf("и").isPresent()?e.getMandatoryFirstOf("и").getTextContent():null;
						String masName = e.getOptionalFirstOf("мас").isPresent()?e.getMandatoryFirstOf("мас").getTextContent():null;
						
						if(femName!=null || andName!=null || masName!=null) {
							if(femName==null) {
								femName = andName!=null?andName:masName;
							}
							if(andName==null) {
								andName = femName!=null?femName:masName;
							}
							if(masName==null) {
								masName = andName!=null?andName:femName;
							}
							importedNames.add(new NameTriplet(masName, andName, femName));
//							System.out.println("Added ("+subspeciesId+"): "+masName+", "+andName+", "+femName);
						}
					}
					if(subspeciesId.isEmpty() || subspeciesId.equalsIgnoreCase("ALL")) {
						for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
							if(subspecies.getRace()==Race.getRaceFromId(raceID)) {
								importedNameMap.putIfAbsent(Subspecies.getIdFromSubspecies(subspecies), new ArrayList<>());
								importedNameMap.get(Subspecies.getIdFromSubspecies(subspecies)).addAll(importedNames);
							}
						}
						
					} else {
						importedNameMap.putIfAbsent(subspeciesId, new ArrayList<>());
						importedNameMap.get(subspeciesId).addAll(importedNames);
					}
				}
				if(additionalNames) {
					for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
						if(subspecies.getRace()==Race.getRaceFromId(raceID)) {
							importedNameMap.get(Subspecies.getIdFromSubspecies(subspecies)).addAll(human);
						}
					}
				}
				
				return importedNameMap;
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractRacialBody was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
		return null;
	}
	
	public static String getRandomName(GameCharacter gc) {
		switch(gc.getFemininity()) {
			case MASCULINE_STRONG:
			case MASCULINE:
				return getRandomTriplet(gc.getSubspecies()).getMasculine();
			case ANDROGYNOUS:
				return getRandomTriplet(gc.getSubspecies()).getAndrogynous();
			case FEMININE:
			case FEMININE_STRONG:
			default:
				return getRandomTriplet(gc.getSubspecies()).getFeminine();
		}
	}
	
	/** Surnames of all demons and half-demons reflect their Lilin lineage.<br/>
	 * For the case of descendents of Lyssieth, a surname would be:<br/>
	 * Lyssieth<b>martusarri</b> (Lyssieth's designated heir. Only Lilaya has this surname. This needs to be manually set.)<br/>
	 * Lyssieth<b>marturabitu</b> (Eldest daughter, if not the designated heir. As most Lilin's eldest daughters are also their designated heir, this surname is very rare.)<br/>
	 * Lyssieth<b>martuilani</b> (A direct daughter of Lyssieth.)<br/>
	 * Lyssieth<b>martu</b> (Lyssieth's grand-daughters or further.)<br/>
	 * <b>Note:</b> Imps descended from Lilin (in these examples, Lyssieth) are given the surname 'Lyssiethmartu', <i>however</i>, in LT's society, it is considered a great insult against Lyssieth to ever address an imp by this.
	 *  If they are ever transformed into a demon, they may use this surname, even if the Lilin who transformed them is not Lyssieth herself. (Again, however, that would be a grave insult against Lyssieth.)
	 * @param gc
	 * @return
	 */
	private static String getDemonSurname(GameCharacter gc) {
		String surname = "";
		GameCharacter mother = gc.getMother();
		
		if(mother!=null) {
			while(mother.getMother()!=null) {
				mother = mother.getMother();
			}
			if(mother.getSubspecies()==Subspecies.LILIN
					|| mother.getSubspecies()== Subspecies.ELDER_LILIN) {
				surname = mother.getName(false);
				List<GameCharacter> offspring = mother.getAllCharactersOfRelationType(Relationship.Parent);
				if(offspring.contains(gc)) {
					offspring.sort((c1, c2) -> c1.getAgeValue()-c2.getAgeValue());
					if(offspring.get(0).equals(gc)) {
						surname+="мартурабиту";
					} else {
						surname+="мартуилани";
					}
				} else {
					surname+="марту";
				}
			}
			
		} else {
			surname = lilinNames[Util.random.nextInt(lilinNames.length)]+"марту";
		}
		
		return surname;
	}
	
	public static String getSurname(GameCharacter gc) {
		GameCharacter mother = gc.getMother();
		if(mother!=null) {
			while(mother.getMother()!=null) {
				mother = mother.getMother();
			}
			return mother.getSurname();
		}
		
		if(gc.getBody()!=null
				&& (gc.getSubspecies()==Subspecies.FOX_ASCENDANT
						|| gc.getSubspecies()==Subspecies.FOX_ASCENDANT_ARCTIC
						|| gc.getSubspecies()==Subspecies.FOX_ASCENDANT_FENNEC)) {
			return youkoSurnames[Util.random.nextInt(youkoSurnames.length)];
		}
		if(gc.getBody()!=null) {
			if(gc.getRace()==Race.DEMON || gc.getRace()==Race.ELEMENTAL) {
				return getDemonSurname(gc);
			}
		}
		return surnames[Util.random.nextInt(surnames.length)];
	}
	
	public static NameTriplet getRandomTriplet(AbstractSubspecies subspecies) {
		NameTriplet name = Util.randomItemFrom(human);
		AbstractRace r = subspecies.getRace();
		
		if(r==Race.DEMON || r==Race.ELEMENTAL) {
			name = getDemonName();
			
		} else if(racialNames.containsKey(Subspecies.getIdFromSubspecies(subspecies))) {
			name = Util.randomItemFrom(racialNames.get(Subspecies.getIdFromSubspecies(subspecies)));
			
		} else if(Math.random()<0.1) { // If no racial names are found, then occasionally throw some "prostitute" names in there
			name = Util.randomItemFrom(prostitute); 
		}
		
		return name;
	}
	
	public static List<NameTriplet> getAllNameTriplets(AbstractSubspecies subspecies) {
		if(racialNames.containsKey(Subspecies.getIdFromSubspecies(subspecies))) {
			return new ArrayList<>(racialNames.get(Subspecies.getIdFromSubspecies(subspecies)));
		}
		return new ArrayList<>(human);
	}
	
	private static NameTriplet getDemonName() {
		String[] prefixFem = new String[] {"Элла", "Белла", "Кэй", "Дева", "Элла", "Фэй", "Хела", "Иса", "Ката", "Лоу", "Ниса", "Оэлла", "Рэй", "Сайта", "Викса", "Винна"};
		String[] prefixMas = new String[] {"Ада", "Боро", "Форо", "Гелио", "Кири", "Зара"};
		
		String[] postfixFem = new String[] {"джикс", "рит", "ней", "никс", "сис", "трикс"};
		String[] postfixMas = new String[] {"джикс", "рит", "ней", "никс", "сис", "трикс"};
		
		String femName = prefixFem[Util.random.nextInt(prefixFem.length)] + postfixFem[Util.random.nextInt(postfixFem.length)];
		char startingChar = femName.charAt(0);

		String masName = prefixMas[Util.random.nextInt(prefixMas.length)] + postfixMas[Util.random.nextInt(postfixMas.length)];
		
		List<String> masculineNames = new ArrayList<>();
		for(String s : prefixMas) {
			if(s.charAt(0) == startingChar) {
				masculineNames.add(s);
			}
		}
		if(!masculineNames.isEmpty()) {
			masName = masculineNames.get(Util.random.nextInt(masculineNames.size())) + postfixMas[Util.random.nextInt(postfixMas.length)];
		}
		
		return new NameTriplet(masName, femName, femName);
	}
	
	public static NameTriplet getRandomProstituteTriplet() {
		// occasionally throw some "regular" names in there - 25% of the time
		if(Math.random()<0.25) {
			return Util.randomItemFrom(human);
		}
		else
		{
			return Util.randomItemFrom(prostitute);
		}
	}
}
