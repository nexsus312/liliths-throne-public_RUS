package com.lilithsthrone.world.population;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.12
 * @version 0.3.9
 * @author Innoxia
 */
public class PopulationType {

	public static AbstractPopulationType PERSON = new AbstractPopulationType("человек", "люди") {};
	
	public static AbstractPopulationType FAN = new AbstractPopulationType("фанат", "фанаты") {};
	
	public static AbstractPopulationType HARPY = new AbstractPopulationType("гарпия", "гарпии") {
		@Override
		public String getName() {
			if(Main.game.isSillyModeEnabled()) {
				return "птичка";
			}
			return "гарпия";
		}
		@Override
		public String getNamePlural() {
			if(Main.game.isSillyModeEnabled()) {
				return "птички";
			}
			return "гарпии";
		}
	};
	
	public static AbstractPopulationType CROWD = new AbstractPopulationType("толпа", "толпы") {};

	public static AbstractPopulationType PRIVATE_SECURITY_GUARD = new AbstractPopulationType("частный охранник", "частные охранники") {};
	
	public static AbstractPopulationType ENFORCER = new AbstractPopulationType("Энфорсер", "Энфорсеры") {};
	
	public static AbstractPopulationType SWORD = new AbstractPopulationType("МЕЧ Энфорсер", "МЕЧ Энфорсеры") {};

	public static AbstractPopulationType CENTAUR_CARTS = new AbstractPopulationType("повозка, запряженная кентавром", "повозки, запряженные кентаврами") {};
	
	public static AbstractPopulationType SHOPPER = new AbstractPopulationType("покупатель", "покупатели") {};
	
	public static AbstractPopulationType DINER = new AbstractPopulationType("закусочная", "закусочные") {};

	public static AbstractPopulationType VIP = new AbstractPopulationType("VIP", "VIP") {};
	
	public static AbstractPopulationType GUARD = new AbstractPopulationType("охранник", "охранники") {};
	
	public static AbstractPopulationType SECURITY_GUARD = new AbstractPopulationType("охранник службы безопасности", "охранники службы безопасности") {};

	public static AbstractPopulationType MAID = new AbstractPopulationType("горничная", "горничные") {};

	public static AbstractPopulationType CHEF = new AbstractPopulationType("шеф-повар", "шеф-повара") {};

	public static AbstractPopulationType SLAVE = new AbstractPopulationType("раб", "рабы") {};
	
	public static AbstractPopulationType OFFICE_WORKER = new AbstractPopulationType("офисный работник", "офисные работники") {};
	
	public static AbstractPopulationType TEXTILE_WORKER = new AbstractPopulationType("рабочий-текстильщик", "рабочие-текстильщики") {};
	
	public static AbstractPopulationType CONSTRUCTION_WORKER = new AbstractPopulationType("рабочий-строитель", "рабочие-строители") {};
	
	public static AbstractPopulationType RECEPTIONIST = new AbstractPopulationType("ресепшионист", "ресепшионисты") {};

	public static AbstractPopulationType GANG_MEMBER = new AbstractPopulationType("член банды", "члены банды") {};

	public static AbstractPopulationType STALL_HOLDER = new AbstractPopulationType("владелец ларька", "владельцы ларьков") {};

	public static AbstractPopulationType MILKER = new AbstractPopulationType("дояр", "дояры") {};
	
	public static AbstractPopulationType CASHIER = new AbstractPopulationType("сассир", "сассиры") {};
	
	public static AbstractPopulationType CLERK = new AbstractPopulationType("клерк", "клерки") {};
	
	public static AbstractPopulationType MASSEUSE = new AbstractPopulationType("массажист", "массажисты") {};
	
	public static AbstractPopulationType AMAZON = new AbstractPopulationType("Амазонка", "Амазонки") {};
	
	public static AbstractPopulationType AMAZON_GUARD = new AbstractPopulationType("Амазонка охранник", "Амазонки охранники") {};
	
	public static AbstractPopulationType LUNETTE_DAUGTHER = new AbstractPopulationType("Дочь Люнетты", "Дочери Люнетты") {};
	
	public static AbstractPopulationType COCK_SLEEVE = new AbstractPopulationType("любитель членов", "любители членов") {};

	public static AbstractPopulationType DOLL = new AbstractPopulationType("кукла", "куклы") {};
	
	
	private static List<AbstractPopulationType> allPopulationTypes = new ArrayList<>();
	private static Map<AbstractPopulationType, String> populationToIdMap = new HashMap<>();
	private static Map<String, AbstractPopulationType> idToPlaceMap = new HashMap<>();

	public static List<AbstractPopulationType> getAllPopulationTypes() {
		return allPopulationTypes;
	}
	
	public static boolean hasId(String id) {
		return idToPlaceMap.keySet().contains(id);
	}
	
	public static AbstractPopulationType getPopulationTypeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToPlaceMap.keySet());
		return idToPlaceMap.get(id);
	}

	public static String getIdFromPopulationType(AbstractPopulationType populationType) {
		return populationToIdMap.get(populationType);
	}
	
	static {
		// Hard-coded population types (all those up above):
		
		Field[] fields = PopulationType.class.getFields();
		
		for(Field f : fields) {
			if(AbstractPopulationType.class.isAssignableFrom(f.getType())) {
				AbstractPopulationType populationType;
				try {
					populationType = ((AbstractPopulationType) f.get(null));

					populationToIdMap.put(populationType, f.getName());
					idToPlaceMap.put(f.getName(), populationType);
					allPopulationTypes.add(populationType);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
