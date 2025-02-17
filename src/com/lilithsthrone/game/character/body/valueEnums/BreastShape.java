package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.86
 * @version 0.3.1
 * @author Innoxia
 */
public enum BreastShape {

	UDDERS(true, "вымя", "животное"),
	
	ROUND(false, "округлое", "круглое"),
	POINTY(false, "остроконечная", "остроконечная"),
	PERKY(false, "Упругая", "Упругая"),
	SIDE_SET(false, "по бокам", "по бокам"),
	WIDE(false, "широкая", "широкая"),
	NARROW(false, "узкая", "узкая");
	
	private boolean restrictedToCrotchBoobs;
	private String transformName;
	private String descriptor;

	private BreastShape(boolean restrictedToCrotchBoobs, String transformName, String descriptor) {
		this.restrictedToCrotchBoobs = restrictedToCrotchBoobs;
		this.transformName = transformName;
		this.descriptor = descriptor;
	}

	public boolean isRestrictedToCrotchBoobs() {
		return restrictedToCrotchBoobs;
	}

	public String getTransformName() {
		return transformName;
	}
	
	public String getDescriptor() {
		return descriptor;
	}
	
	public static List<BreastShape> getUdderBreastShapes() {
		return getBreastShapes(true);
	}
	
	public static List<BreastShape> getNonUdderBreastShapes() {
		return getBreastShapes(false);
	}
	
	private static List<BreastShape> getBreastShapes(boolean udders) {
		List<BreastShape> shapes = new ArrayList<>();
		
		for(BreastShape shape : BreastShape.values()) {
			if(shape.isRestrictedToCrotchBoobs()==udders) {
				shapes.add(shape);
			}
		}
		
		return shapes;
	}
}
