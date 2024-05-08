package com.lilithsthrone.game.character.markings;

import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public enum TattooCountType {

	NUMBERS("цифры") {
		@Override
		public String convertInt(int input) {
			return String.valueOf(input);
		}
	},
	TALLY("метки подсчета") {
		@Override
		public String convertInt(int input) {
			return Util.intToTally(input, 50);
		}
	},
	NUMERALS("Римские цифры") {
		@Override
		public String convertInt(int input) {
			return Util.intToNumerals(input);
		}
	},
	WRITTEN("написано") {
		@Override
		public String convertInt(int input) {
			return Util.capitaliseSentence(Util.intToString(input));
		}
	};
	
	private String name;

	private TattooCountType(String name) {
		this.name = name;
	}

	public abstract String convertInt(int input);
	
	public String getName() {
		return name;
	}
}
