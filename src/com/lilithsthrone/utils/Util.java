package com.lilithsthrone.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * This is just a big mess of utility classes that I wanted to throw somewhere.
 * 
 * @since 0.1.0
 * @version 0.4.1
 * @author Innoxia, CognitiveMist
 */
public class Util {
	
	public static Random random = new Random();

	private static Map<KeyCode, String> KEY_NAMES = new LinkedHashMap<KeyCode, String>() {
		private static final long serialVersionUID = 1L;
	{
		put(KeyCode.ADD, "+");
		put(KeyCode.ALT, "Alt");
		put(KeyCode.AMPERSAND, "&");
		put(KeyCode.ASTERISK, "*");
		put(KeyCode.BACK_QUOTE, "\"");
		put(KeyCode.BACK_SLASH, "\\");
		put(KeyCode.BACK_SPACE, "Back space");
		put(KeyCode.BRACELEFT, "{");
		put(KeyCode.BRACERIGHT, "}");
		put(KeyCode.CAPS, "Caps");
		put(KeyCode.CLOSE_BRACKET, "]");
		put(KeyCode.COLON, ":");
		put(KeyCode.COMMA, ",");
		put(KeyCode.CONTROL, "Ctrl");
		put(KeyCode.DELETE, "Delete");
		put(KeyCode.DIVIDE, "/");
		put(KeyCode.DOLLAR, "$");
		put(KeyCode.DOWN, "Down");
		put(KeyCode.END, "End");
		put(KeyCode.ENTER, "Enter");
		put(KeyCode.EQUALS, "=");
		put(KeyCode.ESCAPE, "Esc");
		put(KeyCode.EURO_SIGN, "&euro;"); // €
		put(KeyCode.EXCLAMATION_MARK, "!");
		put(KeyCode.GREATER, ">");
		put(KeyCode.KP_DOWN, "Down");
		put(KeyCode.KP_LEFT, "Left");
		put(KeyCode.KP_RIGHT, "Right");
		put(KeyCode.KP_UP, "Up");
		put(KeyCode.LEFT, "Left");
		put(KeyCode.LEFT_PARENTHESIS, "(");
		put(KeyCode.LESS, "<");
		put(KeyCode.MINUS, "-");
		put(KeyCode.NUMPAD0, "0");
		put(KeyCode.NUMPAD1, "1");
		put(KeyCode.NUMPAD2, "2");
		put(KeyCode.NUMPAD3, "3");
		put(KeyCode.NUMPAD4, "4");
		put(KeyCode.NUMPAD5, "5");
		put(KeyCode.NUMPAD6, "6");
		put(KeyCode.NUMPAD7, "7");
		put(KeyCode.NUMPAD8, "9");
		put(KeyCode.NUMPAD9, "9");
		put(KeyCode.OPEN_BRACKET, "[");
		put(KeyCode.PAGE_DOWN, "Pg Dn");
		put(KeyCode.PAGE_UP, "Pg Up");
		put(KeyCode.PERIOD, ".");
		put(KeyCode.PLUS, "+");
		put(KeyCode.POUND, "&pound;"); // £
		put(KeyCode.POWER, "^");
		put(KeyCode.QUOTE, "\"");
		put(KeyCode.RIGHT, "Right");
		put(KeyCode.RIGHT_PARENTHESIS, ")");
		put(KeyCode.SEMICOLON, ";");
		put(KeyCode.SHIFT, "Sft");
		put(KeyCode.SLASH, "/");
		put(KeyCode.SPACE, "Space");
		put(KeyCode.SUBTRACT, "-");
		put(KeyCode.TAB, "Tab");
	}};

	// What madness is this
	public static String inputStreamToString(InputStream is) {
		if (is == null)
			return "";
		try (java.util.Scanner s = new java.util.Scanner(is)) {
			return s.useDelimiter("\\A").hasNext() ? s.next() : "";
		}
	}
	
	public static Color midpointColor(Color first, Color second) {
		
		double r = (first.getRed() + second.getRed())/2;
		double g = (first.getGreen() + second.getGreen())/2;
		double b = (first.getBlue() + second.getBlue())/2;
//		System.out.println(r+","+g+","+b);
		return Color.color(r, g, b);
	}
	
	public static String toWebHexString(Color colour) {
		String c = colour.toString().substring(2, 8);
//		System.out.println(c);
		return "#"+c;
	}

	public static Color newColour(String colourString) {
		int hex = Integer.valueOf(colourString.substring(1), 16);
		return newColour((hex & 0xFF0000) >> 16, (hex & 0xFF00) >> 8, (hex & 0xFF));
//		return Color.color(
//				Integer.valueOf(colourString.substring(1, 3), 16) / 255,
//				Integer.valueOf(colourString.substring(3, 5), 16) / 255,
//				Integer.valueOf(colourString.substring(5, 7), 16) / 255);
	}
	
	public static Color newColour(double r, double g, double b) {
		return Color.color(r / 255, g / 255, b / 255);
	}

	public static Color newColour(int hex) {
		return newColour((hex & 0xFF0000) >> 16, (hex & 0xFF00) >> 8, (hex & 0xFF));
	}
	
	/**
	 * Takes an input, and a maximum value, and returns LT's universal "dropoff" formula to it.
	 * @param input
	 * @param maxValue
	 * @return
	 */
	public static float getModifiedDropoffValue(float input, float maxValue) {
		if(Math.abs(input)>Math.abs(maxValue)) {
			input = Math.signum(input) * maxValue;
		}
//		float value = Math.abs(input)/Math.abs(maxValue);
//		//y = 0.75 * cos((x*(pi/2))-(pi/2))
//		return ((int)((Math.signum(input) * maxValue * 0.75f * Math.cos((value * (Math.PI/2)) - (Math.PI/2)))*100))/100f;
		
		
//		sin(x*pi/2)/2
		if(input < maxValue/2) {
			return input;
			
		} else {
			float excess = Math.abs(input) - Math.abs(maxValue/2);
			float value = (excess/Math.abs(maxValue))*2;
			float multiplier = (float) (Math.sin(value * (Math.PI/2))/2f);
//			System.out.println(input+", "+value +", "+ multiplier);
			return Math.round((maxValue/2 + (multiplier * (maxValue/2)))*100)/100f;
		}
	}

	/**
	 * @param containingFolderId To be in the format: <b>"/statusEffects"</b>
	 * @return A map of Files with the author as the key, mapped to a map of ids to files (id is based on file name and folder path).
	 */
	public static Map<String, Map<String, File>> getExternalModFilesById(String containingFolderId) {
		return getExternalModFilesById(containingFolderId, null, null);
	}
	
	/**
	 * @param containingFolderId To be in the format: <b>"/statusEffects"</b>
	 * @param filterFolderName If a non-null String is passed in, only files within folders with that String as their name will be added to the returned map.
	 * @param filterPathName If a non-null String is passed in, only files with that String as their name will be added to the returned map.
	 * @return A map of Files with the author as the key, mapped to a map of ids to files (id is based on file name and folder path).
	 */
	public static Map<String, Map<String, File>> getExternalModFilesById(String containingFolderId, String filterFolderName, String filterPathName) {
		File dir = new File("res/mods");
		Map<String, Map<String, File>> returnMap = new HashMap<>();
		
		if(dir.exists() && dir.isDirectory()) {
			File[] directoryListing = dir.listFiles();
			if(directoryListing != null) {
				for(File directory : directoryListing) {
					String modAuthorName = directory.getName();
					returnMap.putIfAbsent(modAuthorName, new HashMap<>());
					File modAuthorDirectory = new File(directory.getAbsolutePath()+containingFolderId);
					
					populateMapFiles(modAuthorName, directory.getName()+"_", modAuthorDirectory, returnMap, filterFolderName, filterPathName);
				}
			}
		}
		
		return returnMap;
	}

	/**
	 * @param containingFolderId To be in the format: <b>"res/statusEffects"</b>
	 * @return A map of Files with the author as the key, mapped to a map of ids to files (id is based on file name and folder path).
	 */
	public static Map<String, Map<String, File>> getExternalFilesById(String containingFolderId) {
		return getExternalFilesById(containingFolderId, null, null);
	}
	
	/**
	 * @param containingFolderId To be in the format: <b>"res/statusEffects"</b>
	 * @param filterFolderName If a non-null String is passed in, only files within folders with that String as their name will be added to the returned map.
	 * @param filterPathName If a non-null String is passed in, only files with that String as their name will be added to the returned map.
	 * @return A map of Files with the author as the key, mapped to a map of ids to files (id is based on file name and folder path).
	 */
	public static Map<String, Map<String, File>> getExternalFilesById(String containingFolderId, String filterFolderName, String filterPathName) {
		File dir = new File(containingFolderId);
		Map<String, Map<String, File>> returnMap = new HashMap<>();
		
		if(dir.exists() && dir.isDirectory()) {
			File[] authorDirectoriesListing = dir.listFiles();
			if(authorDirectoriesListing != null) {
				for(File authorDirectory : authorDirectoriesListing) {
					if(authorDirectory.isDirectory()){
						String authorName = authorDirectory.getName();
						returnMap.putIfAbsent(authorName, new HashMap<>());
						
						populateMapFiles(authorName, authorDirectory.getName()+"_", authorDirectory, returnMap, filterFolderName, filterPathName);
					}
				}
			}
		}
		
		return returnMap;
	}
	
	private static Map<String, Map<String, File>> populateMapFiles(String modAuthorName, String idPrefix, File directory, Map<String, Map<String, File>> returnMap, String filterFolderName, String filterPathName) {
		if(filterFolderName==null || filterFolderName.equalsIgnoreCase(directory.getName())) {
			File[] innerDirectoryListing = directory.listFiles((path, filename) -> filename.toLowerCase().endsWith(".xml"));
			
			if(innerDirectoryListing != null) {
				for(File innerChild : innerDirectoryListing) {
					if(filterPathName==null || filterPathName.equalsIgnoreCase(innerChild.getName().split("\\.")[0])) {
						try {
							String id = (idPrefix!=null?idPrefix:"")+innerChild.getName().split("\\.")[0];
							returnMap.get(modAuthorName).put(id, innerChild);
						} catch(Exception ex) {
							System.err.println("Загрузка внешних файлов модов не удалась в Util.getExternalModFilesById()");
							System.err.println("Путь к файлу: "+innerChild.getAbsolutePath());
							ex.printStackTrace();
						}
					}
				}
			}
		}
		
		File[] additionalDirectories =  directory.listFiles();

		if(additionalDirectories != null) {
			for(File f : additionalDirectories) {
				if(f.isDirectory()) {
					populateMapFiles(modAuthorName, (idPrefix!=null?idPrefix:"")+f.getName()+"_", f, returnMap, filterFolderName, filterPathName);
				}
			}
		}
		
		return returnMap;
	}
	
	public static String getXmlRootElementName(File XMLFile) {
		try {
			Document doc = Main.getDocBuilder().parse(XMLFile);

			// Cast magic:
			doc.getDocumentElement().normalize();
			
			Element coreElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in statusEffect files it's <statusEffect>
			
			return coreElement.getTagName();
			
		} catch(Exception ex) {
			ex.printStackTrace(System.err);
			return "";
		}
	}
	
	public static class Value<T, S> {
		private T key;
		private S value;
		
		public Value(T key, S value) {
			this.key = key;
			this.value = value;
		}

		public T getKey() {
			return key;
		}

		public S getValue() {
			return value;
		}
	}

	@SafeVarargs
	public static <T, S> LinkedHashMap<T, S> newHashMapOfValues(Value<T, S>... values) {
		LinkedHashMap<T, S> map = new LinkedHashMap<>();

		for (Value<T, S> v : values) {
			if(v!=null) {
				map.put(v.getKey(), v.getValue());
			}
		}
		
		return map;
	}


	public String keyCodeToShortString(KeyCode keyCode) {
		switch (keyCode) {
		case OPEN_BRACKET:
			return "[";
		case CLOSE_BRACKET:
			return "]";
		case UP:
			return "Up";
		case DOWN:
			return "Down";
		case LEFT:
			return "Left";
		case RIGHT:
			return "Right";
		default:
			return keyCode.toString();
		}
	}
	
	public static void openLinkInDefaultBrowser(String url) {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("xdg-open " + url);
		} catch (IOException e0) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
				e0.printStackTrace();
			}
		}
	}

	public static String getFileTime(File file) {
		try {
			Instant fileTime = Files.getLastModifiedTime(file.toPath()).toInstant();
			return Units.dateTime(fileTime);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Unknown";
	}
	
	/**
	 * @param values The values to add to the new list.
	 * @return A list of provided values, with nulls stripped.
	 */
	@SafeVarargs
	public static <U> ArrayList<U> newArrayListOfValues(U... values) {
		ArrayList<U> list = new ArrayList<>(Arrays.asList(values));
		list.removeIf(e -> e==null);
		return list;
	}
	
	@SafeVarargs
	/**
	 * @param lists The lists to merge.
	 * @return A new ArrayList which contains all the elements from both lists.
	 */
	public static <U> ArrayList<U> mergeLists(List<U>... lists) {
		ArrayList<U> mergedList = new ArrayList<>();
		
		for(List<U> list : lists) {
			if(list!=null) {
				for(U value : list) {
					mergedList.add(value);
				}
			}
		}
		
		return mergedList;
	}

	@SafeVarargs
	/**
	 * @param collections The collections to merge.
	 * @return A new ArrayList which contains all the elements from all collections.
	 */
	public static <U> ArrayList<U> mergeCollectionsToList(Collection<U>... collections) {
		ArrayList<U> mergedList = new ArrayList<>();
		for(Collection<U> collection : collections) {
			if(collection!=null) {
				for(U value : collection) {
					mergedList.add(value);
				}
			}
		}
		return mergedList;
	}
	
	@SafeVarargs
	public static <U> HashSet<U> newHashSetOfValues(U... values) {
		return new HashSet<>(Arrays.asList(values));
	}
	
	@SafeVarargs
	/**
	 * @param maps The maps to draw entries from.
	 * @return A new map containing all of the entries from the provided 'maps'. Nulls are stripped, and 'maps' are unaltered.
	 */
	public static <U, T> Map<U, List<T>> mergeMaps(Map<U, List<T>>... maps) {
		Map<U, List<T>> mergedMap = new HashMap<>();
		
		for(Map<U, List<T>> map : maps) {
			if(map!=null) {
				for(Entry<U, List<T>> entry : map.entrySet()) {
					mergedMap.putIfAbsent(entry.getKey(), new ArrayList<>());
					mergedMap.get(entry.getKey()).addAll(entry.getValue());
				}
			}
		}
		
		return mergedMap;
	}
	
	/**
	 * Check a weighted Integer map for validity.
	 * A valid map has no negative weights, and at least one positive weight.
	 * 
	 * @param map The weighted map to check
	 * @param printWarning If true, print a warning to {@link System#err}
	 * @return True if the map is valid; false otherwise
	 */
	public static <T> boolean checkWeightedMap(Map<T, Integer> map, boolean printWarning) {
		if(map.isEmpty()) {
			return true;
		}
		boolean hasPositiveValue = false;
		for(Integer weight : map.values()) {
			if(weight > 0) {
				hasPositiveValue = true;
			} else if(weight < 0) {
				if(printWarning) {
					System.err.println("Предупреждение: отрицательные веса внутри карты весов!\nПервые 10 элементов: "
							+ map.entrySet().stream().limit(10)
							.map(e -> e.getKey().toString() + "=" + e.getValue().toString())
							.collect(Collectors.joining(", ")));
					if(Main.DEBUG) {
						new IllegalArgumentException().printStackTrace();
					}
				}
				return false;
			}
		}
		if(printWarning && !hasPositiveValue) {
			System.err.println("Внимание: в карте весов все веса равны нулю!\nПервые 10 элементов: "
					+ map.entrySet().stream().limit(10)
					.map(e -> e.getKey().toString() + "=" + e.getValue().toString())
					.collect(Collectors.joining(", ")));
			if(Main.DEBUG) {
				new IllegalArgumentException().printStackTrace();
			}
		}
		return hasPositiveValue;
	}

	/**
	 * Check a weighted Float map for validity.
	 * A valid map has no negative weights, and at least one positive weight.
	 * 
	 * @param map The weighted map to check
	 * @param printWarning If true, print a warning to {@link System#err}
	 * @return True if the map is valid; false otherwise
	 */
	public static <T> boolean checkWeightedFloatMap(Map<T, Float> map, boolean printWarning) {
		if(map.isEmpty()) {
			return true;
		}
		boolean hasPositiveValue = false;
		for(Float weight : map.values()) {
			if(weight > 0f) {
				hasPositiveValue = true;
			} else if(weight < 0f) {
				if(printWarning) {
					System.err.println("Предупреждение: отрицательные веса внутри карты весов!\nПервые 10 элементов: "
							+ map.entrySet().stream().limit(10)
							.map(e -> e.getKey().toString() + "=" + e.getValue().toString())
							.collect(Collectors.joining(", ")));
					if(Main.DEBUG) {
						new IllegalArgumentException().printStackTrace();
					}
				}
				return false;
			}
		}
		if(printWarning && !hasPositiveValue) {
			System.err.println("Внимание: в карте весов все веса равны нулю!\nПервые 10 элементов: "
					+ map.entrySet().stream().limit(10)
					.map(e -> e.getKey().toString() + "=" + e.getValue().toString())
					.collect(Collectors.joining(", ")));
			if(Main.DEBUG) {
				new IllegalArgumentException().printStackTrace();
			}
		}
		return hasPositiveValue;
	}

	public static <T> T getHighestProbabilityEntryFromWeightedMap(Map<T, Integer> map) {
		checkWeightedMap(map, true);
		T top = null;
		int high = 0;
		for(Entry<T, Integer> entry : map.entrySet()) {
			if(entry.getValue()>high) {
				high = entry.getValue();
				top = entry.getKey();
			}
		}
		return top;
	}
	
	public static <T> T getRandomObjectFromWeightedMap(Map<T, Integer> map) {
		return getRandomObjectFromWeightedMap(map, Util.random);
	}
	
	public static <T> T getRandomObjectFromWeightedMap(Map<T, Integer> map, Random rnd) {
		checkWeightedMap(map, true);
		int total = 0;
		for(int i : map.values()) {
			total+=i;
		}
		
		if(total==0) {
			return null;
		}
		
		int choice = rnd.nextInt(total) + 1;
		
		total = 0;
		for(Entry<T, Integer> entry : map.entrySet()) {
			total+=entry.getValue();
			if(choice<=total) {
				return entry.getKey();
			}
		}

		return null;
	}
	
	public static <T> T getRandomObjectFromWeightedFloatMap(Map<T, Float> map) {
		checkWeightedFloatMap(map, true);
		float total = 0;
		for(float f : map.values()) {
			total+=f;
		}
		
		float choice = (float) (Math.random()*total);
		
		total = 0;
		for(Entry<T, Float> entry : map.entrySet()) {
			total+=entry.getValue();
			if(choice<=total) {
				return entry.getKey();
			}
		}

		return null;
	}
	
private static String[] numbersLessThanTwenty = {
        "ноль",
        "один",
        "два",
        "три",
        "четыре",
        "пять",
        "шесть",
        "семь",
        "восемь",
        "девять",
        "десять",
        "одиннадцать",
        "двенадцать",
        "тринадцать",
        "четырнадцать",
        "пятнадцать",
        "шестнадцать",
        "семнадцать",
        "восемнадцать",
        "девятнадцать"
};
private static String[] positionsLessThanTwenty = {
        "нулевой",
        "первый",
        "второй",
        "третий",
        "четвертый",
        "пятый",
        "шестой",
        "седьмой",
        "восьмой",
        "девятый",
        "десятый",
        "одиннадцатый",
        "двенадцатый",
        "тринадцатый",
        "четырнадцатый",
        "пятнадцатый",
        "шестнадцатый",
        "семнадцатый",
        "восемнадцатый",
        "девятнадцатый"
};
private static String[] tensGreaterThanNineteen = {
        "",
        "",
        "двадцать",
        "тридцатый",
        "сорок",
        "пятьдесят",
        "шестьдесят",
        "семьдесят",
        "восемьдесят",
        "девяносто"
};
	
	/**
	 * Only works for values -99,999 to 99,999.
	 * @param integer
	 * @return
	 */
	public static String intToString(int integer) {
		String intToString = "";
		
		if(integer<0) {
			intToString = "minus ";
		}
		integer = Math.abs(integer);
		if (integer >= 100_000) {
			return String.valueOf(integer); // Too big
		}
		
		
		if(integer>=1000) {
			if((integer/1000)<20) {
				intToString+=numbersLessThanTwenty[(integer/1000)]+" тысяч";
			} else {
				intToString+=tensGreaterThanNineteen[integer/10000] + (((integer/1000)%10!=0)?"-"+numbersLessThanTwenty[(integer/1000)%10]:"")+" тысяч";
			}
		}
		
		if(integer>=100) {
			if(integer>=1000 && integer%1000 != 0) {
				intToString+=", ";
			}
			integer = integer % 1000;
			if (intToString.isEmpty() || integer>=100) {
				intToString += numbersLessThanTwenty[integer/100]+" сотен";
			}
			if(integer%100!=0) {
				intToString+=" and ";
				integer = integer % 100;
			}
		}
		
		if(integer%100<20) {
			if (integer%100 == 0) {
				if (intToString.isEmpty()) {
					return "ноль";
				}
			} else {
				intToString+=numbersLessThanTwenty[integer%100];
			}
		} else {
			intToString+=tensGreaterThanNineteen[(integer%100)/10] + ((integer%10!=0)?"-"+numbersLessThanTwenty[integer%10]:"");
		}
		
		return intToString;
	}
	
private static String[] primarySequence = {
        "первичный",
        "вторичный",
        "третичный",
        "четвертичный",
        "пятичный",
        "шестичный",
        "седьмичный",
        "восьмичный",
        "девятичный",
        "десятичный"
};
	
	public static String intToPrimarySequence(int integer) {
		if(integer>0 && integer<=primarySequence.length) {
			return primarySequence[integer-1];
		}
		return intToString(integer);
	}
	

	public static String intToDate(int integer) {
		int remainderHundred = integer%100;
		if(remainderHundred<=10 || remainderHundred>20) {
			if(integer%10==1) {
				return integer+"st";
			} else if(integer%10==2) {
				return integer+"nd";
			} else if(integer%10==3) {
				return integer+"rd";
			}
		}
		return integer+"th";
	}
	
	/**
	 * @param integer Input number to convert.
	 * @return 'once', 'twice', or 'integer times'
	 */
	public static String intToCount(int integer) {
		if(integer==1) {
			return "однажды";
		} else if(integer==2) {
			return "дважды";
		}
		
		return intToString(integer)+" раз";
	}

	/**
	 * @param integer Input number to convert.
	 * @return 'first', 'second', etc.
	 */
	public static String intToPosition(int integer) {
		String intToString = "";
		
		if(integer<0) {
			intToString = "минус ";
		}
		integer = Math.abs(integer);
		if (integer >= 100_000) {
			return String.valueOf(integer); // Too big
		}
		
		
		if(integer>=1000) {
			if((integer/1000)<20) {
				intToString+=numbersLessThanTwenty[(integer/1000)]+" тысяч";
			} else {
				intToString+=tensGreaterThanNineteen[integer/10000] + (((integer/1000)%10!=0)?"-"+numbersLessThanTwenty[(integer/1000)%10]:"")+" тысяч";
			}
		}
		
		if(integer>=100) {
			if(integer>=1000 && integer%1000 != 0) {
				intToString+=", ";
			}
			integer = integer % 1000;
			if (intToString.isEmpty() || integer>=100) {
				intToString += numbersLessThanTwenty[integer/100]+" сотен";
			}
			if(integer%100!=0) {
				intToString+=" and ";
				integer = integer % 100;
			}
		}
		
		if(integer%100<20) {
			if (integer%100 == 0) {
				if (intToString.isEmpty()) {
					return "zero";
				}
			} else {
				intToString+=positionsLessThanTwenty[integer%100];
			}
		} else {
			intToString+=tensGreaterThanNineteen[(integer%100)/10] + ((integer%10!=0)?"-"+positionsLessThanTwenty[integer%10]:"");
		}
		
		return intToString;
	}
	
	private final static TreeMap<Integer, String> numeralMap = new TreeMap<Integer, String>();
	static {
        numeralMap.put(1000, "M");
        numeralMap.put(900, "CM");
        numeralMap.put(500, "D");
        numeralMap.put(400, "CD");
        numeralMap.put(100, "C");
        numeralMap.put(90, "XC");
        numeralMap.put(50, "L");
        numeralMap.put(40, "XL");
        numeralMap.put(10, "X");
        numeralMap.put(9, "IX");
        numeralMap.put(5, "V");
        numeralMap.put(4, "IV");
        numeralMap.put(1, "I");
    }
	
	public static String intToNumerals(int integer) {
		if(integer<=0) {
			return "0";
		}
		int l =  numeralMap.floorKey(integer);
        if (integer == l) {
            return numeralMap.get(integer);
        }
        return numeralMap.get(l) + intToNumerals(integer-l);
	}
	
	public static String intToTally(int integer, int max) {
		StringBuilder numeralSB = new StringBuilder();
		int limit = Math.min(integer, max);
		for(int i=0; i<limit/5; i++) {
			numeralSB.append("<strike>IIII</strike> ");
		}
		for(int i=0; i<limit%5; i++) {
			numeralSB.append("I");
		}
		if(limit<integer) {
			numeralSB.append("... (Всего: "+integer+")");
		}
		
		return numeralSB.toString();
	}
	
	public static String getKeyCodeCharacter(KeyCode code) {
		String name = KEY_NAMES.get(code);
		return name != null? name : code.getName();
	}

	/**
	 * @return A capitalised version of the sentence. This method ignores spaces and html formatting, so it should be safe to use on formatted inputs.
	 */
	public static String capitaliseSentence(String sentence) {
		if(sentence==null || sentence.isEmpty()) {
			return sentence;
		}
		int openingCurly = 0;
		int closingCurly = 0;
		int openingAngular = 0;
		int closingAngular = 0;
		int openingSquare = 0;
		int closingSquare = 0;
		for(int i = 0; i<sentence.length(); i++) {
			if(sentence.charAt(i)=='(') {
				openingCurly++;
			} else if(sentence.charAt(i)=='<') {
				openingAngular++;
			} else if(sentence.charAt(i)=='[') {
				openingSquare++;
			}
			
			if(openingCurly==closingCurly && openingAngular==closingAngular && openingSquare==closingSquare && sentence.charAt(i)!=' ') {
				return (i>0?sentence.substring(0, i):"") + Character.toUpperCase(sentence.charAt(i)) + sentence.substring(i+1);
			}
			
			if(sentence.charAt(i)==')') {
				closingCurly++;
			} else if(sentence.charAt(i)=='>') {
				closingAngular++;
			} else if(sentence.charAt(i)==']') {
				closingSquare++;
			}
		}
		return Character.toUpperCase(sentence.charAt(0)) + sentence.substring(1);
	}

	public static boolean isVowel(char c) {
		return "AEIOUaeiou".indexOf(c) != -1;
	}

	/**
	 * Turns a normal sentence into a stuttering sentence. Example:
	 * "How far is it to the town hall?" "H-How far is it to the town h-hall?"
	 * "How far i-is it to the t-town hall?"
	 * 
	 * @param sentence
	 *            sentence to apply stutters
	 * @param frequency
	 *            of stutter words (i.e. 4 would be 1 in 4 words are stutters)
	 * @return
	 *            modified sentence
	 */
	public static String addStutter(String sentence, int frequency) {
		StringBuilder modifiedSentence = new StringBuilder();
		int openingCurly = 0;
		int closingCurly = 0;
		int openingAngular = 0;
		int closingAngular = 0;
		int openingSquare = 0;
		int closingSquare = 0;
		float chance = 1f/frequency;
		for(int i = sentence.length()-1; i>=0; i--) {
			if(sentence.charAt(i)=='(') {
				openingCurly++;
			} else if(sentence.charAt(i)==')') {
				closingCurly++;
			} else if(sentence.charAt(i)=='<') {
				openingAngular++;
			} else if(sentence.charAt(i)=='>') {
				closingAngular++;
			} else if(sentence.charAt(i)=='[') {
				openingSquare++;
			} else if(sentence.charAt(i)==']') {
				closingSquare++;
			}
			
			if(sentence.charAt(i)==' '
					&& Character.isLetter(sentence.charAt(i+1))
					&& openingCurly==closingCurly
					&& openingAngular==closingAngular
					&& openingSquare==closingSquare) {
				if(Math.random()<chance) {
					modifiedSentence.append("-");
					modifiedSentence.append(sentence.charAt(i+1));
				}
			}
			modifiedSentence.append(sentence.charAt(i));
		}
		
		modifiedSentence.reverse();
		return modifiedSentence.toString();
	}

	private static Pattern endOfSentence = Pattern.compile("[,.!?]");
	
	private static boolean isEndOfSentence(char c) {
		return endOfSentence.matcher(String.valueOf(c)).matches();
	}

//	private static String insertIntoSentences(String sentence, int frequency, String[] inserts, boolean middle) {
//		StringBuilder modifiedSentence = new StringBuilder();
//		int openingCurly = 0;
//		int closingCurly = 0;
//		int openingSquare = 0;
//		int closingSquare = 0;
//		float chance = 1f/frequency;
//		for(int i = sentence.length()-1; i>=0; i--) {
//			if(sentence.charAt(i)=='(') {
//				openingCurly++;
//			} else if(sentence.charAt(i)==')') {
//				closingCurly++;
//			} else if(sentence.charAt(i)=='[') {
//				openingSquare++;
//			} else if(sentence.charAt(i)==']') {
//				closingSquare++;
//			}
//			if(i!=sentence.length()-1
//					&& sentence.charAt(i+1)==' '
//					&& !isEndOfSentence(sentence.charAt(i))
//					&& (i==0 || !middle || !isEndOfSentence(sentence.charAt(i-1)))
//					&& openingCurly==closingCurly
//					&& openingSquare==closingSquare) {
//				if(Math.random()<chance) {
//					String word = Util.randomItemFrom(inserts);
//					char[] charArray = word.toCharArray();
//					for(int cIndex=charArray.length-1; cIndex>=0; cIndex--) {
//						modifiedSentence.append(charArray[cIndex]);
//					}
//				}
//			}
//			modifiedSentence.append(sentence.charAt(i));
//		}
//		
//		modifiedSentence.reverse();
//		return modifiedSentence.toString();
//	}

	/**
	 * Inserts words randomly into a sentence.<br/>
	 *
	 * @param sentence
	 *            sentence to insert words into
	 * @param frequency
	 *            how often words are inserted. 1/frequency is the probability of inserting a word
	 * @param inserts
	 *            list of strings to insert into. These are appended to the end of words, so ensure
	 *            any whitespace wanted is put before the insert. A space separates the next word
	 * @param middle
	 *            boolean, whether to avoid inserting at the start/end of a sentence
	 * @return
	 *            modified sentence
	 */
	public static String insertIntoSentences(String sentence, int frequency, String[] inserts, boolean middle) {//TODO middle does nothing
		boolean debug = false;
		// So it begins...
		// This long and highly inefficient method splits the sentence into sections which consist of either nested brackets or conditional hash tags
		// I tried to use regex but nested brackets proved to be troublesome
			// String WITH_DELIMITER_CONDITIONAL = "((?=(#IF))|(?<=(#ENDIF)))"; // Selects an empty character before #IF or after #ENDIF
			// String WITH_DELIMITER_PARSING = "((?=(\\[))|(?<=(\\])))"; // Selects an empty character before [ or after ]
		List<String> splitSentence = new ArrayList<>();
		List<String> conditionalTags = Util.newArrayListOfValues("#ENDIF", "#ELSEIF", "#ELSE", "#IF");
		int openingCurly = 0;
		int closingCurly = 0;
		int openingSquare = 0;
		int closingSquare = 0;
		int conditionalHashIndex = -1;
		int conditionalHashIndexEnd = -1;
		StringBuilder currentString = new StringBuilder();
		for(int i=0; i<sentence.length(); i++) {
			boolean noBrackets = openingCurly==closingCurly && openingSquare==closingSquare;
			boolean opening = false;
			boolean conditionalHashFound = false;
			if(sentence.charAt(i)=='(') {
				openingCurly++;
				opening = true;
			} else if(sentence.charAt(i)==')') {
				closingCurly++;
			} else if(sentence.charAt(i)=='[') {
				openingSquare++;
				opening = true;
			} else if(sentence.charAt(i)==']') {
				closingSquare++;
			} else if(sentence.charAt(i)=='#') {
				conditionalHashFound = true;
				conditionalHashIndex = i;
				for(String s : conditionalTags) {
					try {
						if(sentence.substring(conditionalHashIndex, conditionalHashIndex+s.length()).equals(s)) {
							conditionalHashIndexEnd = conditionalHashIndex+s.length();
							break;
						}
					} catch(Exception ex) {
						// I can't be bothered to check if the index goes below 0 so I'll just throw this Exception in the bin :^)
					}
				}
			}
			
			if(conditionalHashFound) {
				splitSentence.add(currentString.toString());
				currentString.setLength(0);
				currentString.append(sentence.charAt(i));
				
			} else if(conditionalHashIndex>-1) {
				if(i==conditionalHashIndexEnd) {
					conditionalHashIndex = -1;
					conditionalHashIndexEnd = -1;
					splitSentence.add(currentString.toString());
					currentString.setLength(0);
				}
				currentString.append(sentence.charAt(i));
				
			} else {
				if(!noBrackets && openingCurly==closingCurly && openingSquare==closingSquare
						|| (noBrackets && (openingCurly!=closingCurly || openingSquare!=closingSquare))) { // If brackets have just opened or all nested brackets have just closed, then finish this segment and append to list
					if(!opening) {
						currentString.append(sentence.charAt(i));
					}
					splitSentence.add(currentString.toString());
					currentString.setLength(0);
					if(opening) {
						currentString.append(sentence.charAt(i));
					}
				} else {
					currentString.append(sentence.charAt(i));
				}
			}
		}
		if(currentString.length()>0) { // Add final string
			splitSentence.add(currentString.toString());
		}
		// So it ends...
		
		List<String> finalSplitSentence = new ArrayList<>();
		for(String s : splitSentence) {
			if(!s.contains("#") && !s.contains("[") && !s.contains("(")) {
				Collections.addAll(finalSplitSentence, s.split("(?<=(\\s))"));
			} else {
				finalSplitSentence.add(s);
			}
		}

		List<Integer> availableIndexes = new ArrayList<>();
		List<Integer> availableCommaIndexes = new ArrayList<>();
		for(int i=0; i<finalSplitSentence.size(); i++) {
			String s = finalSplitSentence.get(i);
			if(s.matches(".*[a-zA-Z,]+.*")
					&& !s.contains("#") && !s.contains("[") && !s.contains("(")
					&& !isEndOfSentence(s.charAt(s.length()-1))
					&& (i==finalSplitSentence.size()-1 || !isEndOfSentence(finalSplitSentence.get(i+1).charAt(0)))) {
				if(s.contains(",")) {
					availableCommaIndexes.add(i);
				} else if(!s.endsWith(". ")) {// Prevents insertions at the very start of new sentences
					availableIndexes.add(i);
				}
				if(debug) {
					System.out.println(s);
				}
			}
		}

		int totalInserts = Math.max(1, (int) (1f/frequency * availableIndexes.size()+availableCommaIndexes.size()));
		List<String> availableInserts = new ArrayList<>();
		Collections.addAll(availableInserts, inserts);
		List<String> availableCommaInserts = new ArrayList<>(availableInserts);
		availableInserts.removeIf(i->i.contains(","));
		availableCommaInserts.removeIf(i->!i.contains(","));

		if(debug) {
			System.out.println("Total inserts:"+totalInserts);
		}
		
		List<String> insertPool = new ArrayList<>();
		
		while(((!availableIndexes.isEmpty() && (!availableInserts.isEmpty() || !availableCommaInserts.isEmpty())) || (!availableCommaIndexes.isEmpty() && !availableCommaInserts.isEmpty()))
				&& totalInserts>0) {
			// Prioritise replacing commas with comma inserts, as these are the easiest to read:
			if(!availableCommaIndexes.isEmpty() && !availableCommaInserts.isEmpty()) {
				int randomindex = Util.randomItemFrom(availableCommaIndexes);
				String sentenceToReplace = finalSplitSentence.get(randomindex);
				
				// Try not to repeat random inserts:
				
				if(insertPool.isEmpty()) {
					insertPool.addAll(availableCommaInserts);
				}
				// Remove inserts which are the same as either the word to be replaced or those to either side of it (to avoid instances such as 'would you like, like, to do it')
				List<String> adjacentsRemovedPool = new ArrayList<>(insertPool);
				String adjacentLeft = randomindex>0?finalSplitSentence.get(randomindex-1):"";
				String adjacentRight = randomindex<finalSplitSentence.size()-1?finalSplitSentence.get(randomindex+1):"";
				adjacentsRemovedPool.removeIf(i->i.contains(adjacentLeft.trim().replaceAll("[,.!?]", "")));
				adjacentsRemovedPool.removeIf(i->i.contains(sentenceToReplace.trim().replaceAll("[,.!?]", "")));
				adjacentsRemovedPool.removeIf(i->i.contains(adjacentRight.trim().replaceAll("[,.!?]", "")));
				if(debug) {
					System.out.println("comma adjL: "+adjacentLeft);
					System.out.println("comma adjR: "+adjacentRight);
				}
				if(adjacentsRemovedPool.isEmpty()) {
					adjacentsRemovedPool = new ArrayList<>(insertPool);
				}
				String insert = Util.randomItemFrom(insertPool);
				if(insertPool.size()!=availableCommaInserts.size()) {
					insertPool = new ArrayList<>(availableCommaInserts);
				}
				insertPool.removeIf(i->i.equals(insert));
				
				finalSplitSentence.set(randomindex, sentenceToReplace.replaceFirst(",", insert));
				
				// Make sure that indexes adjacent to one another aren't replaced:
				availableIndexes.remove(Integer.valueOf(randomindex-1));
				availableCommaIndexes.remove(Integer.valueOf(randomindex-1));
				availableCommaIndexes.remove(Integer.valueOf(randomindex));
				availableCommaIndexes.remove(Integer.valueOf(randomindex+1));
				availableIndexes.remove(Integer.valueOf(randomindex+1));
				totalInserts--;
				
			// If no comma inserts are left, then proceed to inserting anywhere in the sentence:
			} else if(!availableIndexes.isEmpty() && (!availableInserts.isEmpty() || !availableCommaInserts.isEmpty())) {
				int randomindex = Util.randomItemFrom(availableIndexes);
				String sentenceToReplace = finalSplitSentence.get(randomindex);
				String insert;
				List<String> availableInsertsPool;
				if(!availableInserts.isEmpty()) {
					availableInsertsPool = availableInserts;
				} else {
					availableInsertsPool = availableCommaInserts;
				}
				
//				if(!availableInserts.isEmpty()) {
//					if(insertPool.isEmpty()) {
//						insertPool.addAll(availableInserts);
//					}
//					insert = Util.randomItemFrom(insertPool);
//					if(insertPool.size()!=availableInserts.size()) {
//						insertPool = new ArrayList<>(availableInserts);
//					}
//					insertPool.removeIf(i->i.equals(insert));
//					
//				} else {
				// Try not to repeat random inserts:
				if(insertPool.isEmpty()) {
					insertPool.addAll(availableInsertsPool);
				}
				// Remove inserts which are the same as either the word to be replaced or those to either side of it (to avoid instances such as 'would you like, like, to do it')
				List<String> adjacentsRemovedPool = new ArrayList<>(insertPool);
				String adjacentLeft = randomindex>0?finalSplitSentence.get(randomindex-1):"";
				String adjacentRight = randomindex<finalSplitSentence.size()-1?finalSplitSentence.get(randomindex+1):"";
				adjacentsRemovedPool.removeIf(i->i.contains(adjacentLeft.trim().replaceAll("[,.!?]", "")));
				adjacentsRemovedPool.removeIf(i->i.contains(sentenceToReplace.trim().replaceAll("[,.!?]", "")));
				adjacentsRemovedPool.removeIf(i->i.contains(adjacentRight.trim().replaceAll("[,.!?]", "")));
				if(debug) {
					System.out.println("adjL: "+adjacentLeft);
					System.out.println("adjR: "+adjacentRight);
				}
				if(adjacentsRemovedPool.isEmpty()) {
					adjacentsRemovedPool = new ArrayList<>(insertPool);
				}
				insert = Util.randomItemFrom(adjacentsRemovedPool);
				if(insertPool.size()!=availableInsertsPool.size()) {
					insertPool = new ArrayList<>(availableInsertsPool);
				}
				insertPool.removeIf(i->i.equals(insert));
//				}
				
				if(sentenceToReplace.contains(" ")) {
					finalSplitSentence.set(randomindex, sentenceToReplace.replaceFirst(" ", insert+" "));
				} else if(!Character.isUpperCase(sentenceToReplace.charAt(0))) {
					finalSplitSentence.set(randomindex, insert+" "+sentenceToReplace);
				} else {
					finalSplitSentence.set(randomindex, sentenceToReplace+insert);
				}
				
				// Make sure that indexes adjacent to one another aren't replaced:
				availableIndexes.remove(Integer.valueOf(randomindex-1));
				availableIndexes.remove(Integer.valueOf(randomindex));
				availableIndexes.remove(Integer.valueOf(randomindex+1));
				totalInserts--;
			}
		}

		StringBuilder sb = new StringBuilder();
		for(String s : finalSplitSentence) {
			sb.append(s);
		}
		if(debug) {
			System.out.println("-----");
			System.out.println(sb.toString());
		}
		return sb.toString();
	}

	private static String insertIntoSentences(String sentence, int frequency, String[] inserts) {
		return insertIntoSentences(sentence, frequency, inserts, true);
	}

	public static String[] bimboWords = new String[] { ", типа,", ", типа,", ", типа,", ", эм,", ", ээ,", ", ах," };
	/**
	 * Turns a normal sentence into the kind of thing a Bimbo would come out with.
	 * Can be safely used in conjunction with addStutter.
	 * If using addStutter after using addBimbo, bimbo words can also become stuttered.<br/>
	 * Example: "How far is it to the town hall?"<br/>
	 * "How, like, far is it to the town, uh, hall and stuff?"<br/>
	 * "How far is, like, it to the, um, town hall and stuff?"<br/>
	 * "Like, How far is it to the, like, town hall?"<br/>
	 * Used in conjunction with addStutter(): "L-Like, How far is it t-to the, like, town hall?"
	 *
	 * @param sentence
	 *            sentence to apply bimbo modifications
	 * @param frequency
	 *            of bimbo interjections (i.e. 4 would be 1 in 4 words have a
	 *            bimbo interjection)
	 * @return
	 *            modified sentence
	 */
	public static String addBimbo(String sentence, int frequency) {
		sentence = insertIntoSentences(sentence, frequency, bimboWords);
		StringBuilder utilitiesStringBuilder = new StringBuilder();
		utilitiesStringBuilder.append(sentence);
		
		// 1/3 chance of having a bimbo sentence ending: TODO improve so it can be added anywhere
		if(!sentence.endsWith("~") && !sentence.endsWith("-") && !sentence.endsWith("#ENDIF")) {
			int deleteindex = utilitiesStringBuilder.length() - 1;
			if(sentence.endsWith("?!")) {
				deleteindex = utilitiesStringBuilder.length() - 2;
			} else if(sentence.endsWith("...")) {// || sentence.endsWith("&hellip;")
				deleteindex = utilitiesStringBuilder.length() - 3;
			}
			switch (random.nextInt(6)) {
				case 0:
					CharSequence cs = utilitiesStringBuilder.subSequence(deleteindex, utilitiesStringBuilder.length());
					utilitiesStringBuilder.delete(deleteindex, utilitiesStringBuilder.length());
					utilitiesStringBuilder.append(" и все такое");
					utilitiesStringBuilder.append(cs);
					break;
				case 1:
					utilitiesStringBuilder.delete(deleteindex, utilitiesStringBuilder.length());
					utilitiesStringBuilder.append(", капишь?");
					break;
				default:
					break;
			}
		}

		String returnString = utilitiesStringBuilder.toString();
		returnString = returnString.replaceAll("Hello", "Приветик");
		returnString = returnString.replaceAll("hello", "приветик");
		returnString = returnString.replaceAll("Goodbye", "Прка");
		returnString = returnString.replaceAll("goodbye", "пока");
		return returnString;
	}
	
	private static String[] broWords = new String[] { ", типа,", ", типа, чувак,", ", типа, братан,", ", типа,", ", ээ,", ", эм,", ", ах," };
	public static String addBro(String sentence, int frequency) {
		sentence = insertIntoSentences(sentence, frequency, broWords);
		StringBuilder utilitiesStringBuilder = new StringBuilder();
		utilitiesStringBuilder.append(sentence);
		
		// 1/3 chance of having a bimbo sentence ending: TODO improve so it can be added anywhere
		if(!sentence.endsWith("~") && !sentence.endsWith("-") && !sentence.endsWith("#ENDIF")) {
			switch (random.nextInt(6)) {
				case 0:
					char end = utilitiesStringBuilder.charAt(utilitiesStringBuilder.length() - 1);
					utilitiesStringBuilder.deleteCharAt(utilitiesStringBuilder.length() - 1);
					utilitiesStringBuilder.append(" and stuff");
					utilitiesStringBuilder.append(end);
					break;
				case 1:
					utilitiesStringBuilder.deleteCharAt(utilitiesStringBuilder.length() - 1);
					utilitiesStringBuilder.append(UtilText.returnStringAtRandom(", капиш, бро?", ", капиш, друже?"));
					break;
				default:
					break;
			}
		}
		
		
		
		return utilitiesStringBuilder.toString();
	}

	private static String[] muteSexSounds = new String[] { "... ~Оох!~", "... ~Ммм!~", "... ~Аах!~" };
	/**
	 * @param sentence The sentence to mute.
	 * @param sexMoans If the character should moan/pant due to being in sex.
	 * @return A series of "..." if no sexMoans, or "... ~Ooh!~", "... ~Mmm!~", "... ~Aah!~" if sexMoans
	 */
	public static String replaceWithMute(String sentence, boolean sexMoans) {
		int length = Math.max(1, sentence.split(" ").length/3);
		
		StringBuilder muteSB = new StringBuilder();
		for(int i=0; i<length; i++) {
			if(sexMoans) {
				muteSB.append(muteSexSounds[random.nextInt(muteSexSounds.length)]+" ");
				
			} else {
				muteSB.append("... ");
			}
		}
		muteSB.deleteCharAt(muteSB.length()-1);
		return muteSB.toString();
	}
		
	private static String[] muffledSounds = new String[] { " ~Хрмм~", " ~Ммм~", " ~Мрмм~" };
	/**
	 * Turns a normal sentence into a muffled sentence.<br/>
	 * Example:<br/>
	 * "How far is it to the town hall?"<br/>
	 * "How ~Mrph~ far is it ~Mmm~ to the town ~Mrph~ hall?"<br/>
	 *
	 * @param sentence
	 *            sentence to apply muffles
	 * @param frequency
	 *            of muffled words (i.e. 4 would be 1 in 4 words are muffled)
	 * @return
	 *            modified sentence
	 */
	public static String addMuffle(String sentence, int frequency) {
		return insertIntoSentences(sentence, frequency, muffledSounds);
	}
	
	public static String replaceWithMuffle(String sentence, int wordToMuffleRatio) {
		int muffles = sentence.split(" ").length/wordToMuffleRatio;
		StringBuilder muffleSB = new StringBuilder();
		for(int i=0; i<muffles; i++) {
			muffleSB.append(muffledSounds[random.nextInt(muffledSounds.length)]);
		}
		muffleSB.delete(0, 1); // Remove space at start
		return muffleSB.toString();
	}

	private static String[] sexSounds = new String[] { " ~Аах!~", " ~Ммм!~", " ~Оох!~" };
	/**
	 * Turns a normal sentence into a sexy sentence.<br/>
	 * Example:<br/>
	 * "How far is it to the town hall?"<br/>
	 * "How ~Aah!~ far is it ~Mmm!~ to the town ~Aah!~ hall?"<br/>
	 *
	 * @param sentence
	 *            sentence to apply sexy modifications
	 * @param frequency
	 *            of sex sounds (i.e. 4 would be 1 in 4 words are sexy)
	 * @return
	 *            modified sentence
	 */
	public static String addSexSounds(String sentence, int frequency) {
		return insertIntoSentences(sentence, frequency, sexSounds);
	}

	private static String[] drunkSounds = new String[] { " ~Ик!~" };
	/**
	 * Turns a normal sentence into a drunk one.<br/>
	 * Example:<br/>
	 * "How far is it to the town hall?"<br/>
	 * "How ~Hic!~ far ish it ~Hic!~ to the town ~Hic!~ hall?"<br/>
	 *
	 * @param sentence to apply drunk modifications to.
	 * @param frequency of drunk sounds (i.e. 4 would be 1 in 4 words are drunk)
	 * @return modified sentence
	 */
	public static String addDrunkSlur(String sentence, int frequency) {
		sentence = insertIntoSentences(sentence, frequency, drunkSounds, false);
		
		String [] split = sentence.split("\\[(.*?)\\]");
		for(String s : split) {
			String [] splitConditional = s.split("#IF\\((.*?)\\)|#ELSEIF\\((.*?)\\)"); // Do not replace text inside conditional parsing statements
			for(String s2 : splitConditional) {
				String sReplace = s2
						.replaceAll("Hi ", "Привеееет ")
						.replaceAll("yes", "дааа")
						.replaceAll("Is", "Этото")
						.replaceAll("is", "этото")
						.replaceAll("It's", "Этото")
						.replaceAll("it's", "этото")
						.replaceAll("So", "Так-с")
						.replaceAll("so", "так-с");
					
					sentence = sentence.replace(s2, sReplace);
			}
		}
		
		return sentence;
		
//		return insertIntoSentences(sentence, frequency, drunkSounds, false)
//			.replaceAll("Hi ", "Heeey ")
//			.replaceAll("yes", "yesh")
//			.replaceAll("is", "ish")
//			.replaceAll("So", "Sho")
//			.replaceAll("so", "sho");
	}

private static Map<String, String> slovenlySpeechReplacementMap = new LinkedHashMap<>();
static {
    slovenlySpeechReplacementMap.put("What are", "Чё за");
    slovenlySpeechReplacementMap.put("what are", "чё за");
    
    slovenlySpeechReplacementMap.put("Are", "Ась");
    slovenlySpeechReplacementMap.put("are", "ась");

    slovenlySpeechReplacementMap.put("You're", "Те вона");
    slovenlySpeechReplacementMap.put("you're", "те вона");
    
    slovenlySpeechReplacementMap.put("Your", "Тевое");
    slovenlySpeechReplacementMap.put("your", "тевое");
    
    slovenlySpeechReplacementMap.put("You ", "Те "); // End with a space as sentences which are simply 'You.' are awkward to read when converted to 'Те.'
    slovenlySpeechReplacementMap.put("you", "те");
    
    slovenlySpeechReplacementMap.put("Yourself", "Сибе");
    slovenlySpeechReplacementMap.put("yourself", "сибе");

    slovenlySpeechReplacementMap.put("You'd", "Теб бы");
    slovenlySpeechReplacementMap.put("you'd", "теб бы");

    slovenlySpeechReplacementMap.put("Her", "Ийо");
    slovenlySpeechReplacementMap.put("her", "ийо");

    slovenlySpeechReplacementMap.put("His", "Иго");
    slovenlySpeechReplacementMap.put("his", "иго");
    
    slovenlySpeechReplacementMap.put("Going to", "Собрась");
    slovenlySpeechReplacementMap.put("going to", "собрась");
    
    slovenlySpeechReplacementMap.put("To", "Ка");
    slovenlySpeechReplacementMap.put("to", "ка");
    slovenlySpeechReplacementMap.put("Into", "Ф");
    slovenlySpeechReplacementMap.put("into", "ф");

    slovenlySpeechReplacementMap.put("The", "То");
    slovenlySpeechReplacementMap.put("the", "то");

    slovenlySpeechReplacementMap.put("Them", "Ихни");
    slovenlySpeechReplacementMap.put("them", "ихни");

    slovenlySpeechReplacementMap.put("They", "Ани");
    slovenlySpeechReplacementMap.put("they", "ани");

    slovenlySpeechReplacementMap.put("These", "Ети");
    slovenlySpeechReplacementMap.put("these", "ети");
    
    slovenlySpeechReplacementMap.put("And", "Й");
    slovenlySpeechReplacementMap.put("and", "й");
    
    slovenlySpeechReplacementMap.put("Of", "Ота");
    slovenlySpeechReplacementMap.put("of", "ота");
    slovenlySpeechReplacementMap.put("Who", "Каво");
    slovenlySpeechReplacementMap.put("who", "каво");
    slovenlySpeechReplacementMap.put("Whoever", "Каво слушь");
    slovenlySpeechReplacementMap.put("whoever", "каво слушь");
    
    slovenlySpeechReplacementMap.put("Was", "Былл");
    slovenlySpeechReplacementMap.put("was", "былл");
    
    slovenlySpeechReplacementMap.put("What", "Шо");
    slovenlySpeechReplacementMap.put("what", "шо");
    
    slovenlySpeechReplacementMap.put("Isn't", "Несть");
    slovenlySpeechReplacementMap.put("isn't", "нест");
    slovenlySpeechReplacementMap.put("Aren't", "Несть");
    slovenlySpeechReplacementMap.put("aren't", "нест");
    
    slovenlySpeechReplacementMap.put("This one", "Тоте");
    slovenlySpeechReplacementMap.put("this one", "тоте");
    slovenlySpeechReplacementMap.put("That one", "Тоте");
    slovenlySpeechReplacementMap.put("that one", "тоте");
    
    slovenlySpeechReplacementMap.put("Before", "Докуда");
    slovenlySpeechReplacementMap.put("before", "докуда");
    
    slovenlySpeechReplacementMap.put("Give me", "Дайка");
    slovenlySpeechReplacementMap.put("give me", "дайка");
    
    slovenlySpeechReplacementMap.put("We're", "Мы-с");
    slovenlySpeechReplacementMap.put("we're", "мы-с");
    
    slovenlySpeechReplacementMap.put("So that", "Чтобык");
    slovenlySpeechReplacementMap.put("so that", "чтобык");

    slovenlySpeechReplacementMap.put("Have not", "Не имут");
    slovenlySpeechReplacementMap.put("have not", "не имут");
    slovenlySpeechReplacementMap.put("Haven't", "Не имут");
    slovenlySpeechReplacementMap.put("haven't", "не имут");
    slovenlySpeechReplacementMap.put("Have", "Имут");
    slovenlySpeechReplacementMap.put("have", "имут");

    slovenlySpeechReplacementMap.put("Here", "Туточки");
    slovenlySpeechReplacementMap.put("here", "туточки");
    
    slovenlySpeechReplacementMap.put("My", "Моёк");
    slovenlySpeechReplacementMap.put("my", "моёк");

    slovenlySpeechReplacementMap.put("Myself", "Сам-с");
    slovenlySpeechReplacementMap.put("myself", "сам-с");
    
    slovenlySpeechReplacementMap.put("That", "Туй");
    slovenlySpeechReplacementMap.put("that", "туй");

    slovenlySpeechReplacementMap.put("Some", "Никаки");
    slovenlySpeechReplacementMap.put("some", "никаки");

    slovenlySpeechReplacementMap.put("This", "Ета");
    slovenlySpeechReplacementMap.put("this", "ета");
    
    slovenlySpeechReplacementMap.put("For", "За-с");
    slovenlySpeechReplacementMap.put("for", "за-с");
    
    slovenlySpeechReplacementMap.put("Very", "Охренеть как");
    slovenlySpeechReplacementMap.put("very", "охренеть как");
    
    slovenlySpeechReplacementMap.put("Yes", "Ага");
    slovenlySpeechReplacementMap.put("yes", "ага");

    slovenlySpeechReplacementMap.put("Hurry", "Поспиши");
    slovenlySpeechReplacementMap.put("hurry", "поспиши");
    
    slovenlySpeechReplacementMap.put("Doesn't", "Ни делит");
    slovenlySpeechReplacementMap.put("doesn't", "ни делит");
    
    slovenlySpeechReplacementMap.put("Because", "Патамушта");
    slovenlySpeechReplacementMap.put("because", "патамушта");
}
	/**
	 * Replaces words in the sentence to give the impression that the speaker is talking in a slovenly manner. The replacements are:
			<br/>Are -> Is
			<br/>You're -> You's
			<br/>Your -> Yer
			<br/>You -> Ya
			<br/>Yourself - Yerself
			<br/>You'd -> You's
			<br/>Her -> 'Er
			<br/>His -> 'Is
			<br/>To -> Ta
			<br/>Into -> inta
			<br/>The -> Da
			<br/>Them -> Dem
			<br/>These -> Dese
			<br/>And -> An'
			<br/>Of -> 'O
			<br/>Who -> 'O
			<br/>Who -> 'O
			<br/>Was -> Were
			<br/>Isn't -> ain't
			<br/>Aren't -> ain't
			<br/>This one -> This 'un
			<br/>That one -> That 'un
			<br/>Before -> 'afore
			<br/>Give me -> Gimme
			<br/>Going to -> gonna
			<br/><i>X</i>ing -> <i>X</i>in'
			<br/>We're -> We's
			<br/>So that -> so's
			<br/>Have not -> 'aven't
			<br/>Haven't -> 'aven't
			<br/>Have -> 'ave
			<br/>My -> Me
			<br/>Myself -> Meself
			<br/>That -> Dat
			<br/>Some -> Sum
			<br/>For -> Fer
			<br/>Here -> 'ere
			<br/>Very -> Real
			<br/>Yes -> Yeah
			<br/>Hurry -> 'Urry
			<br/>Doesn't -> Don't
			<br/>Because -> 'Cause
	 *
	 * @param sentence The speech to which the lisp should be applied.
	 * @return The modified sentence.
	 */
	public static String applySlovenlySpeech(String sentence) {
		//Use non-letter regex replacement ([^A-Za-z0-9]) 
		String modifiedSentence = sentence;
		for(Entry<String, String> entry : slovenlySpeechReplacementMap.entrySet()) {
			modifiedSentence = modifiedSentence.replaceAll("([^A-Za-z0-9\\.]|^)"+entry.getKey()+"([^A-Za-z0-9\\]])", "$1"+entry.getValue()+"$2");
		}
		modifiedSentence = modifiedSentence.replaceAll("ing([^A-Za-z0-9\\]])", "in'$1");
		return modifiedSentence;
	}
	
	/**
	 * Applies a lisp to speech (a speech defect in which s is pronounced like th in thick and z is pronounced like th in this). Modified sibilants are italicised in order to assist with reading.<br/>
	 * Example:<br/>
	 * "Is there a zoo that's nearby?"<br/>
	 * "I<i>th</i> there a <i>th</i>oo that'<i>th</i> nearby?"<br/>
	 *
	 * @param sentence The speech to which the lisp should be applied.
	 * @return The modified sentence.
	 */
	public static String applyLisp(String sentence) {
		StringBuilder modifiedSentence = new StringBuilder();
		int openingCurly = 0;
		int closingCurly = 0;
		int openingAngular = 0;
		int closingAngular = 0;
		int openingSquare = 0;
		int closingSquare = 0;
		for(int i = sentence.length()-1; i>=0; i--) {
			if(sentence.charAt(i)=='(') {
				openingCurly++;
			} else if(sentence.charAt(i)==')') {
				closingCurly++;
			} else if(sentence.charAt(i)=='<') {
				openingAngular++;
			} else if(sentence.charAt(i)=='>') {
				closingAngular++;
			}else if(sentence.charAt(i)=='[') {
				openingSquare++;
			} else if(sentence.charAt(i)==']') {
				closingSquare++;
			}
			
			if(openingCurly==closingCurly && openingAngular==closingAngular && openingSquare==closingSquare) {
				if(sentence.charAt(i)=='s' || sentence.charAt(i)=='z') {
					modifiedSentence.append(">i/<ht>i<");
				} else if((sentence.charAt(i)=='S' && (i-1>=0 && sentence.charAt(i-1)!='L')) || sentence.charAt(i)=='Z') {
					modifiedSentence.append(">i/<hT>i<");
				} else {
					modifiedSentence.append(sentence.charAt(i));
				}
				
			} else {
				modifiedSentence.append(sentence.charAt(i));
			}
		}
		
		modifiedSentence.reverse();
		return modifiedSentence.toString();
	}
	
	
	/**
	 * Builds a string representing the list of items in a collection.
	 *
	 * If there is one item, that string will be returned:
	 * <code>"something"</code>.
	 * If there are two items, they are combined with the combining word:
	 * <code>"something and nothing"</code>.
	 * If there are three or more items, all will be combined with commas, except the last two will use the combining word:
	 * <code>"something, nothing and everything"</code>.
	 *
	 * @param items a {@link Collection} of items to turn into a pretty list
	 * @param stringExtractor the function used to get the strings out of the objects in the collection
	 * @param combiningWord the word used to combine the last two items
	 * @param <T> the type of the objects in the collection
	 * @return a pretty string list representing the collection
	 */
	private static <T> String toStringList(Collection<T> items, Function<T, String> stringExtractor, String combiningWord) {
		Iterator<T> itemIterator = items.iterator();
		StringBuilder utilitiesStringBuilder = new StringBuilder();
		try {
			T currentItem = itemIterator.next();
	
			utilitiesStringBuilder.append(stringExtractor.apply(currentItem));
			if (itemIterator.hasNext()) { // If more than one item, enter the loop
				currentItem = itemIterator.next();
				while (itemIterator.hasNext()) { // Use commas until we're on the last item
					utilitiesStringBuilder.append(", " + stringExtractor.apply(currentItem));
					currentItem = itemIterator.next();
				}
				utilitiesStringBuilder.append((items.size()>2?", ":" ") + combiningWord + " " + stringExtractor.apply(currentItem));
			}
		} catch(NoSuchElementException ex) {
			System.err.println("Util.toStringList() error - NoSuchElementException! (It's probably nothing to worry about...)");
			ex.printStackTrace();
		}
		return utilitiesStringBuilder.toString();
	}

	public static String subspeciesToStringList(Collection<AbstractSubspecies> subspecies, boolean capitalise) {
		return Util.toStringList(subspecies,
				(AbstractSubspecies o) -> 
				"<span style='color:"+o.getColour(null).toWebHexString()+";'>"
					+(capitalise
							?Util.capitaliseSentence(o.getNamePlural(null))
							:o.getNamePlural(null))
					+"</span>",
				"and");
	}
	
	public static String charactersToStringListOfNames(Collection<GameCharacter> characters) {
		return Util.toStringList(characters,
				(GameCharacter c) -> 
					UtilText.parse(c, "[npc.name]"),
				"and");
	}

	public static String clothesToStringList(Collection<AbstractClothing> clothingSet, boolean capitalise) {
		return Util.toStringList(clothingSet, (AbstractClothing o) -> (capitalise?Util.capitaliseSentence(o.getClothingType().getName()):o.getClothingType().getName()), "и");
	}

	public static String setToStringListCoverableArea(Set<CoverableArea> coverableAreaSet) {
		return Util.toStringList(coverableAreaSet, (CoverableArea o) -> Util.capitaliseSentence(o.getName()), "and");
	}

	public static String stringsToStringList(List<String> list, boolean capitalise) {
		return Util.toStringList(list, (String o) -> capitalise?Util.capitaliseSentence(o):o, "и");
	}

	public static String stringsToStringChoice(List<String> list, boolean capitalise) {
		return Util.toStringList(list, (String o) -> capitalise?Util.capitaliseSentence(o):o, "или");
	}

	public static String coloursToStringList(Collection<Colour> colourSet) {
		return Util.toStringList(colourSet, Colour::getName, "and");
	}

	public static String coverableAreaListToStringList(List<CoverableArea> coverableAreaCollection) {
		return Util.toStringList(coverableAreaCollection, CoverableArea::getName, "и");
	}

	public static String inventorySlotsToStringList(List<InventorySlot> inventorySlots) {
		return Util.toStringList(inventorySlots, InventorySlot::getName, "и");
	}
	
	public static String inventorySlotsToParsedStringList(List<InventorySlot> inventorySlots, GameCharacter owner) {
		return Util.toStringList(inventorySlots, ((slot) -> slot.getNameOfAssociatedPart(owner)), "и");
	}
	
	public static String tattooInventorySlotsToStringList(List<InventorySlot> inventorySlots) {
		return Util.toStringList(inventorySlots, InventorySlot::getTattooSlotName, "и");
	}

	public static String displacementTypesToStringList(List<DisplacementType> displacedList) {
		return Util.toStringList(displacedList, DisplacementType::getDescriptionPast, "и");
	}

	public static <Any> Any randomItemFrom(List<Any> list) {
		if(list.isEmpty()) {
			return null;
		}
		return list.get(Util.random.nextInt(list.size()));
	}

	public static <Any> Any randomItemFrom(Set<Any> set) {
		List<Any> list = new ArrayList<>(set);
		return randomItemFrom(list);
	}

	public static <Any> Any randomItemFrom(Any[] array) {
		return array[Util.random.nextInt(array.length)];
	}

	public static int randomItemFrom(int[] array) {
		return array[Util.random.nextInt(array.length)];
	}

	@SafeVarargs
	public static <Any> Any randomItemFromValues(Any... values) {
		return values[Util.random.nextInt(values.length)];
	}

	/**
	 * This method will determine the closest string in {@code choices} to the given {@code input}.
	 * The Levenshtein edit distance metric is used for this calculation.
	 *
	 * @param input String for which to find the closest match.
	 * @param choices Collection of valid Strings, among which the closest match to {@code input}
	 *                   will be found.
	 * @param maxDistance The maximum distance for a match. If no match within this distance,
	 *                    return null.
	 * @return The closest match.
	 */
	public static String getClosestStringMatch(String input, Collection<String> choices, int maxDistance) {
		// If input is empty, just return the empty string. It would make no sense to guess, so hopefully the caller will handle the case correctly.
		if (input.isEmpty() || choices.contains(input)) {
			return input;
		}
		int stringMatchDistance = Integer.MAX_VALUE;
		String closestString = input;
		for(String choice : choices) {
			int newDistance = getLevenshteinDistance(input, choice);
			if(newDistance < stringMatchDistance) {
				closestString = choice;
				stringMatchDistance = newDistance;
			}
		}
		if(stringMatchDistance>maxDistance) {
			System.err.println("Warning: getClosestStringMatch() did not find a close enough match for '"+input+"'; returning null. (Closest match was '"+closestString+"' at distance: "+stringMatchDistance+")");
			return null;
		}
		if(stringMatchDistance>0) { // Only show error message if difference is more than just capitalisation differences
			System.err.println("Warning: getClosestStringMatch() did not find an exact match for '"+input+"'; returning '"+closestString+"' instead. (Distance: "+stringMatchDistance+")");
		}
		if(Main.DEBUG) {
			new IllegalArgumentException().printStackTrace(System.err);
		}
		return closestString;
	}
	
	public static String getClosestStringMatch(String input, Collection<String> choices) {
		return getClosestStringMatch(input, choices, Integer.MAX_VALUE);
	}

	private static String unordered(String input, int prefix) {
		// TODO This could be improved if, by some method, the non-prefix words were left as an
		//      unordered set, rather than rejoining them in alphabetical order, since typos can
		//      occur in the first letter, too. However, this would require
		//      com.lilithsthrone.utils.Util.getLevenshteinDistance to handle java.util.Set<E>.
		//      A harder problem is how to handle the omission or addition of an underscore, for
		//      which two words should match with one, or vice-versa.
		String p = "";
		String r = input;
		int prefixLen = 0;
		for (int i = 0; i < prefix; i++) {
			int idx = input.indexOf('_', prefixLen);
			if (idx < 0) {
				// we've ran out of words, the whole thing is prefix
				p = input;
				r = "";
				break;
			}
			prefixLen = idx+1;
			p = input.substring(0, prefixLen);
			r = input.substring(prefixLen);
			//System.out.println("len: "+prefixLen+", "+p+"|"+r);
		}
		return p + Arrays.stream(r.split("_")).sorted().collect(Collectors.joining("_"));
	}

	/**
	 * This method will determine the closest string in {@code choices} to the given {@code input}.
	 * All strings will be treated as underscore-delimited words that have no order.
	 * The Levenshtein edit distance metric is used for this calculation.
	 *
	 * @param input String for which to find the closest match.
	 * @param choices Collection of valid Strings, among which the closest match to {@code input}
	 *                   will be found.
	 * @return The closest match.
	 */
	public static String getClosestStringMatchUnordered(String input, Collection<String> choices) {
		return getClosestStringMatchUnordered(input, 0, choices);
	}

	/**
	 * This method will determine the closest string in {@code choices} to the given {@code input}.
	 * The first {@code prefix} underscore-delimited words of each string will be preserved, but
	 * all words after that will be treated as having no order.
	 * The Levenshtein edit distance metric is used for this calculation.
	 *
	 * @param input String for which to find the closest match.
	 * @param prefix Number of underscore-delimited words for which the ordering should be
	 *               preserved. If zero or less, the whole string is considered unordered. If it
	 *               is the number of words or more, the whole string is considered ordered.
	 * @param choices Collection of valid Strings, among which the closest match to {@code input}
	 *                will be found.
	 * @return The closest match.
	 */
	public static String getClosestStringMatchUnordered(String inputRaw, int prefix, Collection<String> choices) {
		// If inputRaw is empty, just return the empty string. It would make no sense to guess, so hopefully
		// the caller will handle the case correctly.
		if (inputRaw.isEmpty() || choices.contains(inputRaw)) {
			return inputRaw;
		}

		// Util.unordered expects words to be underscore-delimited. However, some misbehaving
		// mods uses spaces or hyphens instead. We'll fix that for them here, to try to get more
		// accurate matches. We assume all values in choices are well-behaved.
		String input = inputRaw.replaceAll("[ -]", "_");

		if (choices.contains(input)) {
			System.err.println("Warning: getClosestStringMatchUnordered() did not find an exact match for '"+inputRaw+"'; returning '"+input+"' instead. (Invalid word delimiter)");
			return input;
		}

		Map<String,String> unorderedChoices = choices.stream().collect(Collectors
				.toMap(s -> Util.unordered(s, prefix), Function.identity(), (a,b) -> {
					System.err.println("Warning: keeping " + a + " and discarding " + b + "!");
					return a;
				}));
		String unorderedInput = unordered(input, prefix);
		if (unorderedChoices.containsKey(unorderedInput)) {
			String unorderedMatch = unorderedChoices.get(unorderedInput);
			System.err.println("Warning: getClosestStringMatchUnordered() did not find an exact match for '"+inputRaw+"'; returning '"+unorderedMatch+"' instead. (Reordered words)");
			return unorderedMatch;
		}
		int distance = Integer.MAX_VALUE;
		String closestString = input;
		for(String unorderedChoice : unorderedChoices.keySet()) {
			int newDistance = getLevenshteinDistance(unorderedInput, unorderedChoice);
			if(newDistance < distance) {
				closestString = unorderedChoices.get(unorderedChoice);
				distance = newDistance;
			}
		}
		if(distance>0) { // Only show error message if difference is more than just capitalisation differences
			System.err.println("Warning: getClosestStringMatchUnordered() did not find an exact match for '"+inputRaw+"'; returning '"+closestString+"' instead. (Distance: "+distance+")");
		}
//		throw new IllegalArgumentException();
		return closestString;
	}
	
	public static int getLevenshteinDistance(String inputOne, String inputTwo) {
		// Don't care about case:
		inputOne = inputOne.toLowerCase();
		inputTwo = inputTwo.toLowerCase();
		
		// i == 0
		int[] costs = new int[inputTwo.length() + 1];
		
		for (int j = 0; j < costs.length; j++) {
			costs[j] = j;
		}
		for (int i = 1; i <= inputOne.length(); i++) {
			// j == 0; nw = lev(i - 1, j)
			costs[0] = i;
			int nw = i - 1;
			for (int j = 1; j <= inputTwo.length(); j++) {
				int cj = Math.min(
						1 + Math.min(costs[j], costs[j - 1]),
						inputOne.charAt(i - 1) == inputTwo.charAt(j - 1)
							? nw
							: nw + 1);
				nw = costs[j];
				costs[j] = cj;
			}
		}
		return costs[inputTwo.length()];
	}
	
	private static Map<String, List<String>> errorLogMap = new HashMap<>();
	public static void logGetNpcByIdError(String method, String id) {
		if(Main.DEBUG) { // So this doesn't flood error.log
			errorLogMap.putIfAbsent(method, new ArrayList<>());
			if(!errorLogMap.get(method).contains(id)) {
				System.err.println("Main.game.getNPCById("+id+") returning null in method: "+method);
				errorLogMap.get(method).add(id);
			}
		}
	}

	public static String getFileName(File f) {
		return f.getName().substring(0, f.getName().lastIndexOf('.'));
	}
	
	public static String getFileIdentifier(File f) {
		return f.getName().substring(0, f.getName().lastIndexOf('.')).replaceAll("'", "Q");
	}
	
	public static String getFileName(String filePath) {
		return filePath.substring(0, filePath.lastIndexOf('.'));
	}
	
	public static String getFileIdentifier(String filePath) {
		return filePath.substring(0, filePath.lastIndexOf('.')).replaceAll("'", "Q");
	}

	public static  <T extends Enum<T>> List<T> toEnumList(final Collection<Element> elements, final Class<T> enumType) {
		return elements.stream()
			.map(Element::getTextContent)
			.map(x -> {
				try { return T.valueOf(enumType, x); }
				catch (Exception e) { return null; } })
			.filter(x -> x != null)
			.collect(Collectors.toList());
	}
}
