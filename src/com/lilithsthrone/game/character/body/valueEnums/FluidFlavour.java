package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.83
 * @version 0.3.9
 * @author Innoxia
 */
public enum FluidFlavour {
	
	CUM("сперма", PresetColour.CUM,
			Util.newArrayListOfValues(
					"соленая")),
	
	MILK("молоко", PresetColour.MILK,
			Util.newArrayListOfValues(
					"кремовое")),
	
	GIRL_CUM("конча", PresetColour.GIRLCUM,
			Util.newArrayListOfValues(
					"сладкая")),
	
	FLAVOURLESS("без вкуса", PresetColour.BASE_GREY,
			Util.newArrayListOfValues(
					"без аромата",
					"без вкуса")),

	BUBBLEGUM("баблгам", PresetColour.BASE_PINK_LIGHT,
			Util.newArrayListOfValues(
					"сладко")),
	
	BEER("пиво", PresetColour.BASE_TAN,
			Util.newArrayListOfValues(
					"дрожжи",
					"со вкусом пива")),
	
	VANILLA("ваниль", PresetColour.BASE_YELLOW_PALE,
			Util.newArrayListOfValues(
					"сладко",
					"со вкусом ванили")),
	
	STRAWBERRY("клубника", PresetColour.BASE_CRIMSON,
			Util.newArrayListOfValues(
					"сладко",
					"со вкусом клубники")),
	
	CHOCOLATE("шоколад", PresetColour.BASE_BROWN,
			Util.newArrayListOfValues(
					"шоколадно",
					"со вкусом шоколада")),
	
	PINEAPPLE("ананас", PresetColour.BASE_YELLOW_LIGHT,
			Util.newArrayListOfValues(
					"кисло-сладкий",
					"кисло",
					"ярко, с кислинкой",
					"со вкусом ананаса")),
	
	HONEY("мед", PresetColour.BASE_YELLOW,
			Util.newArrayListOfValues(
					"сладко",
					"со вкусом меда")),
	
	MINT("мята", PresetColour.BASE_GREEN_LIME,
			Util.newArrayListOfValues(
					"мятно")),
	
	CHERRY("вишня", PresetColour.BASE_RED_DARK,
			Util.newArrayListOfValues(
					"сладно",
					"со вкусом вишни")),
	
	// ------ Icons for these made by 'Charisma is my Dump Stat': ------ //
	
	COFFEE("кофе", PresetColour.BASE_BROWN_DARK,
			Util.newArrayListOfValues(
					"горько",
					"со вкусом кофе")),
	
	TEA("чай", PresetColour.BASE_GREEN,
			Util.newArrayListOfValues(
					"со вкусом чая")),
	
	MAPLE("кленовый сироп", PresetColour.BASE_RED,
			Util.newArrayListOfValues(
					"сладко",
					"со вкусом кленового сиропа")),
	
	CINNAMON("корица", PresetColour.BASE_BROWN,
			Util.newArrayListOfValues(
					"со вкусом корицы")),

	LEMON("лемон", PresetColour.BASE_YELLOW,
			Util.newArrayListOfValues(
					"кисло",
					"со вкусом лимона")),
	
	// ------------ //
	
	// ------ Icons for these made by 'DSG': ------ //
	
	ORANGE("апельсин", PresetColour.BASE_ORANGE,
			Util.newArrayListOfValues(
					"со вкусом апельсина")),
	
	GRAPE("виноград", PresetColour.BASE_PURPLE,
			Util.newArrayListOfValues(
					"со вкусом винограда")),
	
	MELON("арбуз", PresetColour.BASE_GREEN_LIGHT,
			Util.newArrayListOfValues(
					"со вкусом арбуза")),
	
	COCONUT("кокос", PresetColour.BASE_BROWN_DARK,
			Util.newArrayListOfValues(
					"со вкусом кокоса")),
	
	BLUEBERRY("голубика", PresetColour.BASE_BLUE_DARK,
			Util.newArrayListOfValues(
					"со вкусом голубики")),
	
	BANANA("банан", PresetColour.BASE_YELLOW_LIGHT,
			Util.newArrayListOfValues(
					"со вкусом банана"))
	
	// ------------ //
	
	;
	
	private String name;
	private Colour colour;
	private List<String> flavourDescriptors;

	private FluidFlavour(String name, Colour colour, List<String> flavourDescriptors) {
		this.name = name;
		this.colour=colour;
		this.flavourDescriptors = flavourDescriptors;
	}
	
	/**
	 * To go into: "You can't get the rich strawberry taste out of your mouth."<br/>
	 * Or: "Strawberry-flavoured"
	 */
	public String getName() {
		return name;
	}
	
	public Colour getColour() {
		return colour;
	}

	public List<String> getFlavourDescriptors() {
		return flavourDescriptors;
	}
	
	public String getRandomFlavourDescriptor() {
		return flavourDescriptors.get(Util.random.nextInt(flavourDescriptors.size()));
	}
	
	public static List<FluidFlavour> getUnnaturalFlavourings() {
		List<FluidFlavour> list = new ArrayList<>(Arrays.asList(FluidFlavour.values()));
		list.remove(CUM);
		list.remove(MILK);
		list.remove(GIRL_CUM);
		return list;
	}
}
