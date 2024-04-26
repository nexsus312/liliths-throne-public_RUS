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
			"Adams", "Ali", "Allen", "Anderson",
			"Andrews", "Armstrong", "Atkinson", "Bailey",
			"Baker", "Barker", "Barnes", "Bell",
			"Bennett", "Berry", "Booth", "Bradley",
			"Brooks", "Brown", "Butler", "Campbell",
			"Carr", "Carter", "Chambers", "Chapman",
			"Clark", "Clarke", "Cole", "Collins",
			"Cook", "Cooper", "Cox", "Cunningham",
			"Davies", "Davis", "Dawson", "Dean",
			"Dixon", "Edwards", "Ellis", "Evans",
			"Fisher", "Foster", "Fox", "Gardner",
			"George", "Gibson", "Gill", "Gordon",
			"Graham", "Grant", "Gray", "Green",
			"Griffiths", "Hall", "Hamilton", "Harper",
			"Harris", "Harrison", "Hart", "Harvey",
			"Hill", "Holmes", "Hudson", "Hughes",
			"Hunt", "Hunter", "Jackson", "James",
			"Jenkins", "Johnson", "Johnston", "Jones",
			"Kaur", "Kelly", "Kennedy", "Khan",
			"King", "Knight", "Lane", "Lawrence",
			"Lawson", "Lee", "Lewis", "Lloyd",
			"Macdonald", "Marshall", "Martin", "Mason",
			"Matthews", "Mcdonald", "Miller", "Mills",
			"Mitchell", "Moore", "Morgan", "Morris",
			"Murphy", "Murray", "Owen", "Palmer",
			"Parker", "Patel", "Pearce", "Pearson",
			"Phillips", "Poole", "Powell", "Price",
			"Reid", "Reynolds", "Richards", "Richardson",
			"Roberts", "Robertson", "Robinson", "Rogers",
			"Rose", "Ross", "Russell", "Ryan",
			"Saunders", "Scott", "Shaw", "Simpson",
			"Smith", "Spencer", "Stevens", "Stewart",
			"Stone", "Taylor", "Thomas", "Thompson",
			"Thomson", "Turner", "Walker", "Walsh",
			"Ward", "Watson", "Watts", "Webb",
			"Wells", "West", "White", "Wilkinson",
			"Williams", "Williamson", "Wilson", "Wood",
			"Wright", "Young"};
	
	private static final String[] youkoSurnames = new String[] {
			"Abiko", "Abo", "Aburaya", "Achikita",
			"Adachi", "Adachihara", "Agano", "Agata",
			"Agatsuma", "Agawa", "Aguni", "Ahane",
			"Aida", "Aihara", "Aikawa", "Aikuchi",
			"Aikyo", "Aimoto", "Ainara", "Aino",
			"Aisaka", "Aiuchi", "Akagawa", "Akagi",
			"Akahoshi", "Akai", "Akaike", "Akamatsu",
			"Akamine", "Akanishi", "Akano", "Akasaki",
			"Akashi", "Akashiro", "Akashita", "Akatsuki",
			"Akatsutsumi", "Akemi", "Aki", "Akiba",
			"Akibara", "Akimoto", "Akino", "Akisato",
			"Akishima", "Akishino", "Akita", "Akiya",
			"Amachi", "Amagai", "Amagawa", "Amai",
			"Amamiya", "Amano", "Amari", "Amatani",
			"Amaya", "Amemori", "Ametsuchi", "Amuro",
			"Amusan", "Anabuki", "Ando", "Anno",
			"Anzai", "Aoba", "Aoi", "Aoike",
			"Aoki", "Aomine", "Aonuma", "Aota",
			"Aoyagi", "Aoyama", "Aozaki", "Aozora",
			"Ara", "Aragaki", "Arai", "Arakaki",
			"Arakawa", "Araki", "Aranami", "Arashi",
			"Arashiro", "Arata", "Aratani", "Araya",
			"Arima", "Arioka", "Arisawa", "Arita",
			"Ariyoshi", "Asa", "Asahina", "Asai",
			"Asaka", "Asakawa", "Asano", "Asato",
			"Ashikaga", "Atari", "Atsuda", "Ayano",
			"Ayanokoji", "Ayanokouji", "Azahara", "Azuma",
			"Baba", "Bando", "Budou", "Bushida",
			"Chabashira", "Chage", "Chiaki", "Chiba",
			"Chibana", "Chigusa", "Chikafuji", "Chino",
			"Chisaka", "Chiura", "Chosokabe", "Chousokabe",
			"Date", "Deguchi", "Doi", "Dotani",
			"Eguchi", "Ejiri", "Enatsu", "Endo",
			"Enokida", "Enomoto", "Eto", "Etou",
			"Fuji", "Fujihara", "Fujihashi", "Fujii",
			"Fujikawa", "Fujimori", "Fujimura", "Fujinaka",
			"Fujino", "Fujinomiya", "Fujisaki", "Fujisato",
			"Fujisawa", "Fujiura", "Fujiwara", "Fujiyama",
			"Fujiyoshi", "Fukagai", "Fukami", "Fukase",
			"Fukuda", "Fukuhara", "Fukuizumi", "Fukumoto",
			"Fukunaga", "Fukushima", "Fukuyama", "Fukuyo",
			"Furukawa", "Furusawa", "Furuse", "Furuya",
			"Futaba", "Futamura", "Fuyuki", "Gato",
			"Goda", "Goto", "Gotoh", "Goya",
			"Gushiken", "Hachimitsu", "Hachimura", "Hada",
			"Haga", "Hagino", "Hagiwara", "Hajime",
			"Hama", "Hamada", "Hamadate", "Hamaguchi",
			"Hamakawa", "Hamamura", "Hamano", "Hamazaki",
			"Hanabusa", "Hanai", "Hanamura", "Hanazawa",
			"Handa", "Haneda", "Haneyama", "Hanyu",
			"Hanyuu", "Hara", "Harada", "Haraguchi",
			"Haramoto", "Harigae", "Haruki", "Haruna",
			"Haruno", "Haruta", "Haruyama", "Hasegawa",
			"Hashi", "Hashiguchi", "Hashikura", "Hashioka",
			"Hashira", "Hashitani", "Hashiyama", "Hata",
			"Hatake", "Hatano", "Hataya", "Hatsu",
			"Hattori", "Hayabusa", "Hayagawa", "Hayakawa",
			"Hayama", "Hayasaka", "Hayashibara", "Hayashida",
			"Hidaka", "Higa", "Higashi", "Higashida",
			"Higashiyama", "Himi", "Hino", "Hinode",
			"Hirai", "Hirakawa", "Hiramatsu", "Hirano",
			"Hirasaka", "Hirasawa", "Hirashima", "Hirata",
			"Hiratani", "Hiroi", "Hiromi", "Hirono",
			"Hirosawa", "Hirose", "Hiroshima", "Hirota",
			"Hiruma", "Hisamatsu", "Hitarashi", "Hitotose",
			"Hitotsuyanagi", "Hojo", "Hokinoue", "Homura",
			"Hori", "Horie", "Horigome", "Horikita",
			"Horino", "Hoshi", "Hoshimiya", "Hoshino",
			"Hoshizaki", "Hosoda", "Hosokawa", "Hosoo",
			"Houjou", "Ibuki", "Ichida", "Ichihara",
			"Ichihashi", "Ichikawa", "Ichino", "Ichinomiya",
			"Ichioka", "Ida", "Ieiri", "Iekami",
			"Igarashi", "Ige", "Ikari", "Ike",
			"Ikehara", "Ikemoto", "Ikeru", "Ikesugi",
			"Ikuta", "Imada", "Imaeda", "Imagawa",
			"Imai", "Imaishi", "Imamura", "Imari",
			"Imaruoka", "Imata", "Inagaki", "Inamura",
			"Inazuma", "Inoshishi", "Inouye", "Inui",
			"Inukai", "Iori", "Isago", "Isamu",
			"Isayama", "Ishida", "Ishido", "Ishiguro",
			"Ishihara", "Ishii", "Ishikura", "Ishimoto",
			"Ishiuchi", "Ishiwata", "Ishiyama", "Ishizuka",
			"Isobe", "Isogai", "Isozaki", "Iwaaki",
			"Iwae", "Iwamoto", "Iwano", "Iwaoka",
			"Iwasaki", "Iwashimizu", "Iwata", "Iwatani",
			"Iwayama", "Izuhara", "Izumi", "Izumo",
			"Jinnouchi", "Junko", "Kabe", "Kabuto",
			"Kadokawa", "Kadomatsu", "Kadoshima", "Kadota",
			"Kaetsu", "Kaga", "Kaiba", "Kaji",
			"Kajitani", "Kajiura", "Kajiwara", "Kaki",
			"Kakihara", "Kakimura", "Kakinuma", "Kakita",
			"Kaku", "Kakutani", "Kamata", "Kamei",
			"Kamenashi", "Kamino", "Kamiya", "Kamiyama",
			"Kamori", "Kamoto", "Kanbara", "Kanbayashi",
			"Kanbe", "Kanda", "Kaneda", "Kaneki",
			"Kaneko", "Kanemaru", "Kaneshiro", "Kanno",
			"Kanroji", "Karamatsu", "Karasu", "Kasai",
			"Kasei", "Kashiwa", "Kashiwabara", "Kashiwada",
			"Kashiwade", "Kashiwado", "Kashiwaeda", "Kashiwagi",
			"Kashiwahara", "Katagiri", "Katsura", "Kawa",
			"Kawabata", "Kawada", "Kawahara", "Kawai",
			"Kawakita", "Kawamoto", "Kawamura", "Kawanabe",
			"Kawanaka", "Kawanishi", "Kawano", "Kawasaki",
			"Kawashima", "Kawashita", "Kawata", "Kawauchi",
			"Kazama", "Kazami", "Kaze", "Kazehaya",
			"Kazetani", "Kazuyuki", "Kenma", "Kichida",
			"Kida", "Kidamura", "Kido", "Kihara",
			"Kikyo", "Kinjo", "Kino", "Kinoshita",
			"Kinugasa", "Kirigaya", "Kirimura", "Kirishima",
			"Kiriya", "Kishi", "Kishimoto", "Kitabayashi",
			"Kitagawa", "Kitahara", "Kitamura", "Kitani",
			"Kitano", "Kitao", "Kitaoka", "Kiya",
			"Kiyoko", "Kiyomizu", "Kiyota", "Kiyotake",
			"Kobashi", "Kodaira", "Kogane", "Kohira",
			"Koide", "Koigakubo", "Koike", "Kojima",
			"Kokawa", "Koki", "Komatsu", "Komatsuzaki",
			"Komiya", "Komuro", "Konaka", "Konda",
			"Kondo", "Kondou", "Konishi", "Konno",
			"Konparu", "Kosaka", "Kosugi", "Kotake",
			"Kotani", "Kotobuki", "Kotsuki", "Kouumoto",
			"Koyama", "Koyasu", "Kozue", "KÔzuke",
			"Kozuki", "Kuba", "Kubo", "Kubota",
			"Kuchiki", "Kudou", "Kuga", "Kugimiya",
			"Kuhara", "Kujira", "Kumagai", "Kumai",
			"Kumaki", "Kunida", "Kunimatsu", "Kunisaki",
			"Kurama", "Kurasawa", "Kurata", "Kuribayashi",
			"Kurihara", "Kurihashi", "Kurimoto", "Kurisu",
			"Kuriyama", "Kurizuka", "Kuroba", "Kuroda",
			"Kurogi", "Kurohashi", "Kuroiwa", "Kurokawa",
			"Kuroki", "Kuroko", "Kuromiya", "Kuronuma",
			"Kurosaka", "Kurosaki", "Kuroshima", "Kuroyanagi",
			"Kusayanagi", "Kushida", "Kushieda", "Kusunoki",
			"Kuwabara", "Kuwahara", "Kuwako", "Kyogoku",
			"Kyugoku", "Machi", "Machida", "Maebara",
			"Maejima", "Maekawa", "Maeshima", "Maeyamada",
			"Majima", "Makimura", "Makino", "Makita",
			"Manabe", "Manaka", "Mashimo", "Masuyama",
			"Matsu", "Matsubara", "Matsubayashi", "Matsuda",
			"Matsudaira", "Matsuhashi", "Matsui", "Matsukata",
			"Matsuki", "Matsumae", "Matsumura", "Matsunaga",
			"Matsunawa", "Matsuno", "Matsuo", "Matsushima",
			"Matsuura", "Matsuyama", "Matsuyuki", "Matsuzaki",
			"Mawatari", "Michizoe", "Midorikawa", "Mifune",
			"Mihara", "Mikami", "Mikazuki", "Miki",
			"Minamoto", "Minato", "Minatozaki", "Mine",
			"Misaki", "Mishima", "Miso", "Mita",
			"Mitsue", "Mitsugi", "Mitsugu", "Mitsui",
			"Miura", "Miwa", "Miya", "Miyabe",
			"Miyaguchi", "Miyahara", "Miyaichi", "Miyake",
			"Miyako", "Miyama", "Miyano", "Miyara",
			"Miyashita", "Miyata", "Miyauchi", "Miyazaki",
			"Miyazato", "Miyazawa", "Mizufuka", "Mizuhara",
			"Mizukawa", "Mizuno", "Mizusawa", "Mizuta",
			"Mizutama", "Mizutani", "Mochizuki", "Moegi",
			"Momoi", "Momose", "Moriai", "Morifuji",
			"Morihara", "Morihei", "Morikawa", "Morikita",
			"Morimoto", "Morinaka", "Morishita", "Morita",
			"Moriuchi", "Moriya", "Moriyama", "Moteki",
			"Motohashi", "Motome", "Motozawa", "Mukai",
			"Munekawa", "Mura", "Murahama", "Murahashi",
			"Murakami", "Murakawa", "Murakita", "Muramatsu",
			"Muranaka", "Muraoka", "Murashima", "Murata",
			"Muratagi", "Mushakoji", "Mushakouji", "Mushanokoji",
			"Mushanokouji", "Muto", "Mutsu", "Mutsumi",
			"Myoui", "Naegi", "Nagai", "Nagamatsu",
			"Nagano", "Nagao", "Nagaoka", "Nagashima",
			"Nagasu", "Nagato", "Nagatsuka", "Nagayama",
			"Naito", "Nakada", "Nakafuji", "Nakagame",
			"Nakagawa", "Nakai", "Nakamatsu", "Nakamine",
			"Nakamoto", "Nakanishi", "Nakao", "Nakata",
			"Nakauchi", "Nakaura", "Nakayama", "Nanami",
			"Nanashima", "Nanatsuki", "Nara", "Narisawa",
			"Narita", "Naru", "Natsukawa", "Natsumi",
			"Neho", "Neji", "Niikura", "Nikaido",
			"Ninomiya", "Nishi", "Nishida", "Nishino",
			"Nishio", "Nishiyama", "Nitta", "Nobira",
			"Nobunaga", "Nojima", "Nojiri", "Nomi",
			"Nomura", "Nonaka", "Noto", "Notou",
			"Numa", "Obara", "Oda", "Ōga",
			"Ogami", "Ogasawara", "Ogawa", "Ogita",
			"Ogura", "Oguri", "Ohama", "Ohara",
			"Ohashi", "Ohayashi", "Ohka", "Ohori",
			"Ohtani", "Oka", "Okada", "Okamoto",
			"Okamura", "Okano", "Okashima", "Okawa",
			"Okazaki", "Okochi", "Okota", "Oku",
			"Okubo", "Okudaira", "Okugawa", "Okukawa",
			"Okumura", "Okuno", "Okuyama", "Omori",
			"Omoto", "Omura", "Onishi", "Ōno",
			"Ono", "Onoue", "Oogami", "Ookouchi",
			"Ootani", "Ootono", "Osaka", "Osaki",
			"Osako", "Osato", "Osawa", "Oshima",
			"Ota", "Ōtaka", "Otaki", "Ōtani",
			"Otani", "Otonari", "Otsuka", "Ōtsuki",
			"Owari", "Oyakawa", "Oyama", "Oyamada",
			"Ozaki", "Ozawa", "Ozu", "Rikimaru",
			"Rin", "Rinbayashi", "Ritsushima", "Roka",
			"Royama", "RyŪjin", "Ryuko", "Ryumine",
			"Ryuuen", "RyŪzaki", "Saeki", "Sagami",
			"Saihara", "Saionji", "Saitama", "Saka",
			"Sakabayashi", "Sakaguchi", "Sakahara", "Sakai",
			"Sakaki", "Sakamoto", "Sakane", "Sakatani",
			"Saki", "Sakimoto", "Sakiyama", "Sakuma",
			"Sakura", "Sakurai", "Sakurami", "Sakurano",
			"Sakurazaka", "Samejima", "Samon", "Sanada",
			"Sanjo", "Sanjou", "Sano", "Sasano",
			"Sasayama", "Sasori", "Satomi", "Satoya",
			"Sawa", "Sawada", "Sawashiro", "Saza",
			"Seki", "Sekiguchi", "Senju", "Senri",
			"Serizawa", "Seto", "Setou", "Setsushi",
			"Shibasaki", "Shibata", "Shibayama", "Shibutani",
			"Shibuya", "Shima", "Shimada", "Shimadzu",
			"Shimamoto", "Shimamura", "Shimaoka", "Shimazaki",
			"Shimazu", "Shime", "Shimono", "Shimooka",
			"Shimotsuki", "Shimoyama", "Shimura", "Shinden",
			"Shinkai", "Shinobu", "Shinoda", "Shinohara",
			"Shinyama", "Shio", "Shiokawa", "Shirai",
			"Shiraishi", "Shirakawa", "Shiromori", "Shishido",
			"Shishigami", "Sho", "Shoji", "Shouyu",
			"Shoyu", "Sonoda", "Sueno", "Sueoka",
			"Suga", "Sugano", "Sugawara", "Sugieda",
			"Sugihara", "Sugimori", "Sugimoto", "Sugimura",
			"Sugino", "Sugita", "Sugitani", "Sugiura",
			"Sugiyama", "Sumisu", "Sumitomo", "Sunadori",
			"Suzukaze", "Suzumura", "Suzutani", "Suzuya",
			"Tada", "Tadokoro", "Tahara", "Taira",
			"Tajiri", "Takada", "Takagi", "Takagiri",
			"Takahara", "Takahata", "Takahide", "Takai",
			"Takaishi", "Takaki", "Takakuwa", "Takamaru",
			"Takamatsu", "Takami", "Takamitsu", "Takamori",
			"Takamura", "Takanashi", "Takano", "Takao",
			"Takase", "Takasu", "Takata", "Takayama",
			"Takayanagi", "Takazato", "Takeda", "Takehara",
			"Takei", "Takemiya", "Takemizu", "Takemura",
			"Takeno", "Takeshita", "Taketatsu", "Takeuchi",
			"Taki", "Takinoue", "Tamagawa", "Tamai",
			"Tamara", "Tamaru", "Tamashiro", "Tamatsuki",
			"Tamayama", "Tamon", "Tamura", "Tanabata",
			"Tani", "Tanigawa", "Taniguchi", "Tanihara",
			"Tanikawa", "Tanimoto", "Taniyama", "Tanji",
			"Tanose", "Tanuma", "Tatewaki", "Tatsuda",
			"Tatsumi", "Tatsuoka", "Tatsushima", "Tazawa",
			"Tekazewa", "Terada", "Terasaki", "Terauchi",
			"Tezuka", "Tobe", "Toda", "Togami",
			"Tojo", "Tokufuji", "Tokugawa", "Tokui",
			"Tomatsu", "Tomiie", "Tomita", "Tomiyasu",
			"Tomoeda", "Tomosaka", "Tomura", "Torigoe",
			"Toujou", "Toya", "Toyoda", "Toyoguchi",
			"Toyota", "Tsubo", "Tsuboi", "Tsuchida",
			"Tsuchii", "Tsuchiya", "Tsuda", "Tsudzumi",
			"Tsudzuri", "Tsuge", "Tsujihara", "Tsujii",
			"Tsujimura", "Tsujita", "Tsujiura", "Tsukahara",
			"Tsukamoto", "Tsukigata", "Tsukimi", "Tsukino",
			"Tsukiyama", "Tsukiyomi", "Tsukushi", "Tsunematsu",
			"Tsunoda", "Tsunoi", "Tsuruoka", "Tsuruta",
			"Tsutena", "Tsutsumi", "Tsuzuki", "Tsuzuno",
			"Uchida", "Uchiha", "Uchiyama", "Uehara",
			"Ueki", "Uematsu", "Uemura", "Ueshita",
			"Uesugi", "Ueta", "Umajiri", "Umeda",
			"Umehara", "Umezawa", "Umon", "Uno",
			"Uramoto", "Uraoka", "Urushihara", "Utada",
			"Utsunomiya", "Uzumaki", "Wada", "Wagahara",
			"Wakabayashi", "Wakaki", "Wakamatsu", "Wakata",
			"Wakatsuchi", "Wakuni", "Wakuri", "Watabe",
			"Watanuki", "Watari", "Yabe", "Yada",
			"Yagami", "Yagi", "Yagira", "Yakumo",
			"Yama", "Yamagata", "Yamaha", "Yamahashi",
			"Yamakawa", "Yamamizu", "Yamamura", "Yamanaka",
			"Yamane", "Yamasato", "Yamatani", "Yanagi",
			"Yanagimoto", "Yanagisawa", "Yanai", "Yasui",
			"Yasuki", "Yasunishi", "Yasuraoka", "Yasuyama",
			"Yasuzato", "Yazaki", "Yoichi", "Yoichien",
			"Yoichimae", "Yokohama", "Yokomizo", "Yokomura",
			"Yokotani", "Yokote", "Yokoyama", "Yomohiro",
			"Yonaga", "Yonamine", "Yone", "Yoneda",
			"Yoneichi", "Yonezawa", "Yoshihama", "Yoshihara",
			"Yoshii", "Yoshikawa", "Yoshimoto", "Yoshimura",
			"Yoshino", "Yoshinuma", "Yoshioka", "Yoshisawa",
			"Yui", "Yukimori", "Yukitomo", "Yukiyama",
			"Yukizome", "Yumi", "Yuuma", "Zabatsu"};
	
	private static String[] lilinNames = new String[] {
			"Lovienne",
			"Lasielle",
			"Lyssieth",
			"Lianna",
			"Lilysha",
			"Lynixi",
			"Liloria"};
	
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
						String femName = e.getOptionalFirstOf("fem").isPresent()?e.getMandatoryFirstOf("fem").getTextContent():null;
						String andName = e.getOptionalFirstOf("and").isPresent()?e.getMandatoryFirstOf("and").getTextContent():null;
						String masName = e.getOptionalFirstOf("mas").isPresent()?e.getMandatoryFirstOf("mas").getTextContent():null;
						
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
						surname+="marturabitu";
					} else {
						surname+="martuilani";
					}
				} else {
					surname+="martu";
				}
			}
			
		} else {
			surname = lilinNames[Util.random.nextInt(lilinNames.length)]+"martu";
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
		String[] prefixFem = new String[] {"Aella", "Bella", "Cae", "Deva", "Ella", "Fae", "Hela", "Isa", "Katha", "Loe", "Nysa", "Oella", "Rae", "Sytha", "Vixxa", "Wynna"};
		String[] prefixMas = new String[] {"Ada", "Boro", "Foro", "Helio", "Kiri", "Zara"};
		
		String[] postfixFem = new String[] {"jyx", "ryth", "ney", "nix", "sys", "trix"};
		String[] postfixMas = new String[] {"jyx", "ryth", "ney", "nix", "sys", "trix"};
		
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
