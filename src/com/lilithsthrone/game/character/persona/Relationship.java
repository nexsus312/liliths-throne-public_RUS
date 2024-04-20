package com.lilithsthrone.game.character.persona;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.gender.PronounType;

// TODO: needs plural forms
/**
 * @since 0.2.0
 * @version 0.4.4.1
 * @author Innoxia, orvail
 */
public enum Relationship {

	/** For ovipositor egg incubation */
    IncubatorParent("мать инкубатор", "отец инкубатор", "родитель инкубатор", 0),
    IncubatorChild("инкубированная дочь", "инкубированный сын", "инкубированный ребенок", 0),
    
    Parent("мать", "отец", "родитель", 0),
    GrandParent("бабушка", "дедушка", "прародитель", 1),
    GrandGrandParent("прабабушка", "прадедушка", "пра-прародитель", 2),
    Child("дочь", "сые", "ребенок", 0),
    GrandChild("внучка", "внук", "внук(чка)", 1),
    GrandGrandChild("правнучка", "правнук", "пра-внук(чка)", 2),
    Sibling("сестра", "брат", "родственник", 0),
    SiblingTwin("Сестры-близнецы", "Братья-близнецы", "двойняшки", 0),
    HalfSibling("сводная сестра", "сводный брат", "сводный родственник", 1.25),
    Cousin("кузен/кузина", 2),
    Pibling("тетя", "дядя", "тетя-дядя(без пола)", 1.5),
    GrandPibling("пратетя", "прадядя", "пратетя-прадядя(без пола)", 2.5),
    Nibling("племянница", "племянник", "племяшка", 1.5);

    private final String displayF;
    private final String displayM;
    private final String displayN;
    private final double distance;

    Relationship(String displayF, String displayM, String displayN, double distance) {
        this.displayF = displayF;
        this.displayM = displayM;
        this.displayN = displayN;
        this.distance = distance;
    }

    Relationship(String display, double distance) {
        this(display, display, display, distance);
    }

    public String toString(PronounType pronounType) {
        switch (pronounType) {
            case FEMININE:
                return displayF;
            case NEUTRAL:
                return displayN;
            case MASCULINE:
                return displayM;
            default:
                throw new RuntimeException();
        }
    }

    public String getName(GameCharacter character) {
    	if(character.isFeminine()) {
    		return getDisplayF();
    	} else {
    		return getDisplayM();
    	}
    }
    
    public String getDisplayF() {
        return displayF;
    }

    public String getDisplayM() {
        return displayM;
    }

    public String getDisplayN() {
        return displayN;
    }

    public double getDistance() {
        return distance;
    }
}
