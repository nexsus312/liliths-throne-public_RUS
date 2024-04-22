package com.lilithsthrone.game.character.body.valueEnums;

/**
 * Measurements are in inches. Measured in bust to underbust using the UK system.
 * 
 * @since 0.1.0
 * @version 0.1.83
 * @author Innoxia
 */
public enum CupSize {
	
	FLAT("плоско", "плоско", 0),

	// Training bra sizes:
	
	TRAINING_AAA("почти незаметная", "тренеруемая-AAA", 1) {
		@Override
		public boolean isTrainingBraSize() {
			return true;
		}
	},
	TRAINING_AA("почти незаметная", "тренеруемая-AA", 2) {
		@Override
		public boolean isTrainingBraSize() {
			return true;
		}
	},
	TRAINING_A("почти незаметная", "тренеруемая-A", 3) {
		@Override
		public boolean isTrainingBraSize() {
			return true;
		}
	},
	
	// Normal cup sizes:
	
	AA("очень маленький", "AA", 4),
	A("маленький", "A", 5),
	B("небольшой", "B", 6),
	C("средний", "C", 7),
	D("большой", "D", 8),
	DD("большой", "DD", 9),
	E("значительный", "E", 10),
	F("значительный", "F", 11),
	FF("значительный", "FF", 12),
	G("огромный", "G", 13),
	GG("огромный", "GG", 14),
	H("огромный", "H", 15),
	HH("гигантский", "HH", 16),
	J("гигантский", "J", 17),
	JJ("гигантский", "JJ", 18),
	K("гигантский", "K", 19),
	KK("гигантский", "KK", 20),
	L("гигантский", "L", 21),
	LL("колоссальный", "LL", 22),
	M("колоссальный", "M", 23),
	MM("колоссальный", "MM", 24),
	N("колоссальный", "N", 25),
	
	// Hyper sizes:
	
	X_AA("экстремальный", "X-AA", 26),
	X_A("экстремальный", "X-A", 27),
	X_B("экстремальный", "X-B", 28),
	X_C("экстремальный", "X-C", 29),
	X_D("экстремальный", "X-D", 30),
	X_DD("экстремальный", "X-DD", 31),
	X_E("экстремальный", "X-E", 32),
	X_F("экстремальный", "X-F", 33),
	X_FF("экстремальный", "X-FF", 34),
	X_G("экстремальный", "X-G", 35),
	X_GG("экстремальный", "X-GG", 36),
	X_H("экстремальный", "X-H", 37),
	X_HH("экстремальный", "X-HH", 38),
	X_J("экстремальный", "X-J", 39),
	X_JJ("экстремальный", "X-JJ", 40),
	X_K("экстремальный", "X-K", 41),
	X_KK("экстремальный", "X-KK", 42),
	X_L("экстремальный", "X-L", 43),
	X_LL("экстремальный", "X-LL", 44),
	X_M("экстремальный", "X-M", 45),
	X_MM("экстремальный", "X-MM", 46),
	X_N("экстремальный", "X-N", 47),

	XX_AA("монстр", "XX-AA", 48),
	XX_A("монстр", "XX-A", 49),
	XX_B("монстр", "XX-B", 50),
	XX_C("монстр", "XX-C", 51),
	XX_D("монстр", "XX-D", 52),
	XX_DD("монстр", "XX-DD", 53),
	XX_E("монстр", "XX-E", 54),
	XX_F("монстр", "XX-F", 55),
	XX_FF("монстр", "XX-FF", 56),
	XX_G("монстр", "XX-G", 57),
	XX_GG("монстр", "XX-GG", 58),
	XX_H("монстр", "XX-H", 59),
	XX_HH("монстр", "XX-HH", 60),
	XX_J("монстр", "XX-J", 61),
	XX_JJ("монстр", "XX-JJ", 62),
	XX_K("монстр", "XX-K", 63),
	XX_KK("монстр", "XX-KK", 64),
	XX_L("монстр", "XX-L", 65),
	XX_LL("монстр", "XX-LL", 66),
	XX_M("монстр", "XX-M", 67),
	XX_MM("монстр", "XX-MM", 68),
	XX_N("монстр", "XX-N", 69),

	XXX_AA("гипер", "XXX-AA", 70),
	XXX_A("гипер", "XXX-A", 71),
	XXX_B("гипер", "XXX-B", 72),
	XXX_C("гипер", "XXX-C", 73),
	XXX_D("гипер", "XXX-D", 74),
	XXX_DD("гипер", "XXX-DD", 75),
	XXX_E("гипер", "XXX-E", 76),
	XXX_F("гипер", "XXX-F", 77),
	XXX_FF("гипер", "XXX-FF", 78),
	XXX_G("гипер", "XXX-G", 79),
	XXX_GG("гипер", "XXX-GG", 80),
	XXX_H("гипер", "XXX-H", 81),
	XXX_HH("гипер", "XXX-HH", 82),
	XXX_J("гипер", "XXX-J", 83),
	XXX_JJ("гипер", "XXX-JJ", 84),
	XXX_K("гипер", "XXX-K", 85),
	XXX_KK("гипер", "XXX-KK", 86),
	XXX_L("гипер", "XXX-L", 87),
	XXX_LL("гипер", "XXX-LL", 88),
	XXX_M("гипер", "XXX-M", 89),
	XXX_MM("гипер", "XXX-MM", 90),
	XXX_N("гипер", "XXX-N", 91);

	private String descriptor;
	private String cupSizeName;
	private int measurement;

	private CupSize(String descriptor, String cupSizeName, int measurement) {
		this.descriptor = descriptor;
		this.cupSizeName = cupSizeName;
		this.measurement = measurement;
	}
	
	public boolean isTrainingBraSize() {
		return false;
	}
	
	/**
	 * @return The minimum size which is regarded as a character 'having breasts' by the game.
	 */
	public static CupSize getMinimumCupSizeForBreasts() {
		return CupSize.AA;
	}
	
	public static CupSize getMinimumCupSizeForEggIncubation() {
		return CupSize.C;
	}
	
	public static CupSize getMinimumCupSizeForPaizuri() {
		return CupSize.C;
	}
	
	public static CupSize getMinimumCupSizeForPenetration() {
		return CupSize.C;
	}

	/**
	 * @param size Measurement in inches from bust to underbust.
	 */
	public static CupSize getCupSizeFromInt(int size) {
		for (CupSize cs : values()) {
			if (size == cs.measurement) {
				return cs;
			}
		}
		return XXX_N;
	}

	/**
	 * To fit into a sentence: "You have "+getDescriptor()+" breasts."
	 */
	public String getDescriptor() {
		return descriptor;
	}

	public String getCupSizeName() {
		return cupSizeName;
	}

	public int getMeasurement() {
		return measurement;
	}
	
	public static CupSize getMaximumCupSize() {
		return XXX_N;
	}
}
