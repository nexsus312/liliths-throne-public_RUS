package com.lilithsthrone.utils;

import com.lilithsthrone.game.character.race.AbstractSubspecies;

/**
 * @since 0.1.82
 * @version 0.3.3
 * @author Innoxia
 * Нечего переводить
 */
public class CreditsSlot {

	private String name;
	private String tagLine;
	
	private int uncommonCount;
	private int rareCount;
	private int epicCount;
	private int legendaryCount;
	
	private AbstractSubspecies subspeciesTier;
	
	public CreditsSlot(String name, String tagLine, int uncommonCount, int rareCount, int epicCount, int legendaryCount) {
		this.name = name;
		this.tagLine = tagLine;
		this.uncommonCount = uncommonCount;
		this.rareCount = rareCount;
		this.epicCount = epicCount;
		this.legendaryCount = legendaryCount;
	}
	
	public CreditsSlot(String name, String tagLine, int uncommonCount, int rareCount, int epicCount, int legendaryCount, AbstractSubspecies subspeciesTier) {
		this(name, tagLine, uncommonCount, rareCount, epicCount, legendaryCount);
		this.subspeciesTier = subspeciesTier;
	}

	public String getName() {
		return name;
	}

	public String getTagLine() {
		return tagLine;
	}

	public int getUncommonCount() {
		return uncommonCount;
	}

	public int getRareCount() {
		return rareCount;
	}

	public int getEpicCount() {
		return epicCount;
	}

	public int getLegendaryCount() {
		return legendaryCount;
	}

	public AbstractSubspecies getSubspeciesTier() {
		return subspeciesTier;
	}
	
}
