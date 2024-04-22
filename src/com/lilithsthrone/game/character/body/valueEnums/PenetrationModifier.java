package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.4
 * @author Innoxia
 */
public enum PenetrationModifier {

	SHEATHED("с оболочкой", "Помогает скрыть выпуклости в одежде в неэрегированном состоянии."),
	
	RIBBED("ребристый", ""),
	
	TENTACLED("щупальцевый", ""),
	
	KNOTTED("узловой", "Дает возможность затолкнуть его внутрь отверстия в момент оргазма, удвайвая диаметр и сковывая партнеров вместе. (Требует чтобы отверстие было достаточно глубоким для вмещения основы узла.)"),
	
	BLUNT("тупой", ""),

	TAPERED("узкий", "Уменьшает эффективный диаметр на 5%. (Несовместимо с 'широкий'.)") {
		@Override
		public List<PenetrationModifier> getMutuallyExclusivePenetrationModifiers() {
			return Util.newArrayListOfValues(FLARED);
		}
	},
	
	FLARED("широкий", "Увеличивает эффективный диаметр на 5%. (Несовместимо с 'узкий'.)") {
		@Override
		public List<PenetrationModifier> getMutuallyExclusivePenetrationModifiers() {
			return Util.newArrayListOfValues(TAPERED);
		}
	},
	
	BARBED("колючий", ""),
	
	VEINY("жилистый", ""),
	
	PREHENSILE("цепкий", ""),
	
	OVIPOSITOR("яйцеклад",
			"Дает возможность откладывать яйца в текущее проникаемое отверстие. (Требует чтобы яйца уже были оплодотворены. Яйца нельзя отложить если у партнера есть беременность.)",
			Util.newArrayListOfValues(
					SexAreaPenetration.PENIS,
					SexAreaPenetration.CLIT));
	
	private String name;
	private String description;
	private List<SexAreaPenetration> restrictedPenetrationTypes;

	private PenetrationModifier(String name, String description) {
		this(name, description, null);
	}
	
	private PenetrationModifier(String name, String description, List<SexAreaPenetration> restrictedPenetrationTypes) {
		this.name = name;
		this.description = description;
		this.restrictedPenetrationTypes = restrictedPenetrationTypes;
	}
	
	public static List<PenetrationModifier> getPenetrationModifiers() {
		return getPenetrationModifiers(null);
	}
	
	public static List<PenetrationModifier> getPenetrationModifiers(SexAreaPenetration penetrationType) {
		List<PenetrationModifier> penetrationModifiers = new ArrayList<>(Arrays.asList(PenetrationModifier.values()));
		penetrationModifiers.removeIf(pm->pm.getRestrictedPenetrationTypes()!=null && penetrationType!=null && !pm.getRestrictedPenetrationTypes().contains(penetrationType));
		return penetrationModifiers;
	}

	public List<PenetrationModifier> getMutuallyExclusivePenetrationModifiers() {
		return new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isSpecialEffects() {
		return !description.isEmpty();
	}
	
	public String getDescription() {
		if(description.isEmpty()) {
			return "Не влияет на геймплей.";
		}
		return description;
	}

	public List<SexAreaPenetration> getRestrictedPenetrationTypes() {
		return restrictedPenetrationTypes;
	}
}
