package com.lilithsthrone.world.population;

/**
 * @since 0.2.12
 * @version 0.4.4.5
 * @author Innoxia
 */
public enum PopulationDensity {

	ONE("один"),
	
	OCCASIONAL("немногие"),
	
	COUPLE("парочка"),
	
	FEW("немного"),
	
	TRIO("трио"),
	
	SPARSE("разрозенные"),
	
	SEVERAL("несколько"),
	
	HALF_DOZEN("полдюжины"),
	
	DOZEN("люжина"),

	DOZENS("дюжины"),
	
	MANY("много"),
	
	NUMEROUS("многочисленные"),
	
	DENSE("тесно собранные"),
	
	SMALL("маленькое количество"),
	
	HUNDREDS("сотни"),
	
	THOUSANDS("тысячи");

	String name;
	
	private PopulationDensity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
