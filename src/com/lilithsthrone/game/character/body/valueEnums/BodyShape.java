package com.lilithsthrone.game.character.body.valueEnums;

import java.util.List;

import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

import javafx.scene.paint.Color;

/**
 * @since 0.1.83
 * @version 0.2.8
 * @author Innoxia
 */
public enum BodyShape {
	
	/*
	 * Ectomorph: Lean and long, no muscle
	 * Endomorph: Big, high body fat
	 * Mesomorph: Muscular and well-built
	 */
	
	// BodySize == ZERO_SKINNY
	SKINNY_SOFT("тощ(ий,ая)", BodySize.ZERO_SKINNY, Muscle.ZERO_SOFT, Util.newArrayListOfValues("худ(ой,ая)", "тощ(ий,ая)")),
	SKINNY_LIGHTLY_MUSCLED("миниатюрн(ый,ая)", BodySize.ZERO_SKINNY, Muscle.ONE_LIGHTLY_MUSCLED, Util.newArrayListOfValues("худ(ой,ая)", "тощ(ий,ая)")),
	SKINNY_TONED("гибк(ий,ая)", BodySize.ZERO_SKINNY, Muscle.TWO_TONED, Util.newArrayListOfValues("худ(ой,ая)")),
	SKINNY_MUSCULAR("Исхудавш(ий,ая)", BodySize.ZERO_SKINNY, Muscle.THREE_MUSCULAR, Util.newArrayListOfValues("худ(ой,ая)", "в тонусе")),
	SKINNY_RIPPED("Занимается гимнастикой", BodySize.ZERO_SKINNY, Muscle.FOUR_RIPPED, Util.newArrayListOfValues("худ(ой,ая)", "в тонусе")),

	// BodySize == ONE_SLENDER
	SLENDER_SOFT("тонк(ий,ая)", BodySize.ONE_SLENDER, Muscle.ZERO_SOFT, Util.newArrayListOfValues("тонк(ий,ая)", "стройн(ый,ая)")),
	SLENDER_LIGHTLY_MUSCLED("худ(ой,ая)", BodySize.ONE_SLENDER, Muscle.ONE_LIGHTLY_MUSCLED, Util.newArrayListOfValues("тонк(ий,ая)", "стройн(ый,ая)")),
	SLENDER_TONED("проворн(ый,ая)", BodySize.ONE_SLENDER, Muscle.TWO_TONED, Util.newArrayListOfValues("тонк(ий,ая)", "стройн(ый,ая)", "в тонусе")),
	SLENDER_MUSCULAR("гибк(ий,ая)", BodySize.ONE_SLENDER, Muscle.THREE_MUSCULAR, Util.newArrayListOfValues("тонк(ий,ая)", "в тонусе")),
	SLENDER_RIPPED("Занимается аэробикой", BodySize.ONE_SLENDER, Muscle.FOUR_RIPPED, Util.newArrayListOfValues("тонк(ий,ая)", "в тонусе")),
	
	// BodySize == TWO_AVERAGE
	AVERAGE_SOFT("упитанн(ый,ая)", BodySize.TWO_AVERAGE, Muscle.ZERO_SOFT, Util.newArrayListOfValues("упитанн(ый,ая)", "мягк(ий,ая)")),
	AVERAGE_LIGHTLY_MUSCLED("средн(ий,ая)", BodySize.TWO_AVERAGE, Muscle.ONE_LIGHTLY_MUSCLED, Util.newArrayListOfValues("немного в тонусе")),
	AVERAGE_TONED("здоров(ый,ая)", BodySize.TWO_AVERAGE, Muscle.TWO_TONED, Util.newArrayListOfValues("в тонусе")),
	AVERAGE_MUSCULAR("спортивн(ый,ая)", BodySize.TWO_AVERAGE, Muscle.THREE_MUSCULAR, Util.newArrayListOfValues("в тонусе", "мускулист(ый,ая)")),
	AVERAGE_RIPPED("атлет", BodySize.TWO_AVERAGE, Muscle.FOUR_RIPPED, Util.newArrayListOfValues("в тонусе", "мускулист(ый,ая)", "сильн(ый,ая)")),
	
	// BodySize == THREE_LARGE
	LARGE_SOFT("толст(ый,ая)", BodySize.THREE_LARGE, Muscle.ZERO_SOFT, Util.newArrayListOfValues("упитанн(ый,ая)", "мягк(ий,ая)", "толст(ый,ая)")),
	LARGE_LIGHTLY_MUSCLED("полн(ый,ая)", BodySize.THREE_LARGE, Muscle.ONE_LIGHTLY_MUSCLED, Util.newArrayListOfValues("упитанн(ый,ая)", "мягк(ий,ая)", "полн(ый,ая)")),
	LARGE_TONED("дородн(ый,ая)", BodySize.THREE_LARGE, Muscle.TWO_TONED, Util.newArrayListOfValues("больш(ой,ая)", "сильн(ый,ая)")),
	LARGE_MUSCULAR("мощн(ый,ая)", BodySize.THREE_LARGE, Muscle.THREE_MUSCULAR, Util.newArrayListOfValues("больш(ой,ая)", "мускулист(ый,ая)", "сильн(ый,ая)")),
	LARGE_RIPPED("крепк(ий,ая)", BodySize.THREE_LARGE, Muscle.FOUR_RIPPED, Util.newArrayListOfValues("огромн(ый,ая)", "мускулист(ый,ая)", "сильн(ый,ая)")),
	
	// BodySize == FOUR_HUGE
	HUGE_SOFT("жирн(ый,ая)", BodySize.FOUR_HUGE, Muscle.ZERO_SOFT, Util.newArrayListOfValues("упитанн(ый,ая)", "мягк(ий,ая)", "толст(ый,ая)")),
	HUGE_LIGHTLY_MUSCLED("коренаст(ый,ая)", BodySize.FOUR_HUGE, Muscle.ONE_LIGHTLY_MUSCLED, Util.newArrayListOfValues("упитанн(ый,ая)", "мягк(ий,ая)", "полн(ый,ая)")),
	HUGE_TONED("крепк(ий,ая)", BodySize.FOUR_HUGE, Muscle.TWO_TONED, Util.newArrayListOfValues("больш(ой,ая)", "сильн(ый,ая)")),
	HUGE_MUSCULAR("плотн(ый,ая)", BodySize.FOUR_HUGE, Muscle.THREE_MUSCULAR, Util.newArrayListOfValues("больш(ой,ая)", "мускулист(ый,ая)", "сильн(ый,ая)")),
	HUGE_RIPPED("подъемный кран", BodySize.FOUR_HUGE, Muscle.FOUR_RIPPED, Util.newArrayListOfValues("огромн(ый,ая)", "мускулист(ый,ая)", "сильн(ый,ая)"));
	
	private String name;
	private List<String> limbDescriptors;
	private BodySize relatedBodySize;
	private Muscle relatedMuscle;
	
	private BodyShape(String name, BodySize relatedBodySize, Muscle relatedMuscle, List<String> limbDescriptors) {
		this.name = name;
		this.relatedBodySize = relatedBodySize;
		this.relatedMuscle = relatedMuscle;
		this.limbDescriptors = limbDescriptors;
	}

	public String getName(boolean withDeterminer) {
		if(withDeterminer) {
			return UtilText.generateSingularDeterminer(name) + " " + name;
		} else {
			return name;
		}
	}

	public BodySize getRelatedBodySize() {
		return relatedBodySize;
	}

	public Muscle getRelatedMuscle() {
		return relatedMuscle;
	}
	
	public static BodyShape valueOf(Muscle muscle, BodySize bodySize) {
		for(BodyShape bs : BodyShape.values()) {
			if(muscle == bs.getRelatedMuscle() && bodySize == bs.getRelatedBodySize()) {
				return bs;
			}
		}
		return AVERAGE_LIGHTLY_MUSCLED;
	}
	
	public Color getDerivedColor() {
		return Util.midpointColor(relatedBodySize.getColour().getColor(), relatedMuscle.getColour().getColor());
	}
	
	public String toWebHexStringColour() {
		return Util.toWebHexString(getDerivedColor());
	}

	public List<String> getLimbDescriptors() {
		return limbDescriptors;
	}
}
