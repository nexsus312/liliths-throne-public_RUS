package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.7
 * @author Innoxia
 */
public enum HairStyle {

//	- parted down the middle
//	- side parted
//	- shaved (different from bald)
//	- punk (hair draped over face)
	
	NONE("натуральная", Femininity.ANDROGYNOUS, HairLength.ZERO_BALD) {
		@Override
		public String getName(Body body) {
			if(body!=null && body.isFeral()) {
				if(body.getLegConfiguration()==LegConfiguration.AVIAN) {
					return "оперенная";
				} else {
					return "гривастая";
				}
			}
			return super.getName(body);
		}
	},
	MESSY("неаккуратно", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT),
	LOOSE("распущенно", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT),
	CURLY("кудряво", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT),
	STRAIGHT("прямо", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT),
	SLICKED_BACK("зачесанно назад", Femininity.ANDROGYNOUS, HairLength.ONE_VERY_SHORT),
	SIDECUT("боковой андеркат", Femininity.ANDROGYNOUS, HairLength.TWO_SHORT),
	MOHAWK("ирокез", Femininity.ANDROGYNOUS, HairLength.TWO_SHORT),
	DREADLOCKS("дреды", Femininity.ANDROGYNOUS, HairLength.TWO_SHORT),
	
	AFRO("афро", Femininity.MASCULINE, HairLength.ONE_VERY_SHORT),
	TOPKNOT("топ-узел.", Femininity.MASCULINE, HairLength.THREE_SHOULDER_LENGTH),
	
	PIXIE("пикси", Femininity.FEMININE, HairLength.TWO_SHORT),
	BUN("пучок", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	BOB_CUT("каре", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	CHONMAGE("чонмаге", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	WAVY("волнисто", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	PONYTAIL("конский хвост", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	LOW_PONYTAIL("низкий конский хвост", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	TWIN_TAILS("двойные хвостики", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	SIDE_BRAIDS("боковые косы", Femininity.FEMININE, HairLength.THREE_SHOULDER_LENGTH),
	CHIGNON("шиньон", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	BRAIDED("косички", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	TWIN_BRAIDS("две косы", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	CROWN_BRAID("коса вокруг головы", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	DRILLS("кудрявые локоны ожо", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	HIME_CUT("принцесса (Японская Химе)", Femininity.FEMININE, HairLength.FOUR_MID_BACK),
	BIRD_CAGE("птичья клетка", Femininity.FEMININE, HairLength.SEVEN_TO_FLOOR);
	
	private String descriptor;
	private Femininity femininity;
	private int minimumLengthRequired;

	private HairStyle(String descriptor, Femininity femininity, HairLength minimumLengthRequired) {
		this.descriptor = descriptor;
		this.femininity = femininity;
		this.minimumLengthRequired = minimumLengthRequired.getMinimumValue();
	}

	public String getName(GameCharacter owner) {
		if(owner==null) {
			return descriptor;
		}
		return getName(owner.getBody());
	}

	public String getName(Body body) {
		return descriptor;
	}
	
	/** This should just be used for random character hair style generation. */
	public Femininity getFemininity() {
		return femininity;
	}

	public int getMinimumLengthRequired() {
		return minimumLengthRequired;
	}
	
	/**
	 * @return A random hair style, filtered by femininity and length limitations.
	 */
	public static HairStyle getRandomHairStyle(boolean feminine, int hairLength) {
		List<HairStyle> availableStyles = new ArrayList<>();
		
		for(HairStyle hs : HairStyle.values()) {
			if((hs.getFemininity()==Femininity.ANDROGYNOUS || hs.getFemininity().isFeminine()==feminine) && hs.getMinimumLengthRequired() <= hairLength) {
				availableStyles.add(hs);
			}
		}
		
		// Most likely to have a "normal" hair style:
		if(Math.random()>0.10f) {
			availableStyles.remove(HairStyle.AFRO);
			availableStyles.remove(HairStyle.SIDECUT);
			availableStyles.remove(HairStyle.MOHAWK);
			availableStyles.remove(HairStyle.HIME_CUT);
			availableStyles.remove(HairStyle.CHONMAGE);
			availableStyles.remove(HairStyle.DREADLOCKS);
			availableStyles.remove(HairStyle.BIRD_CAGE);
			availableStyles.remove(HairStyle.DRILLS);
		}
		
		return availableStyles.get(Util.random.nextInt(availableStyles.size()));
	}
}
