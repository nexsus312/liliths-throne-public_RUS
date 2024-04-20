package com.lilithsthrone.game.character.gender;

/**
 * @since 0.1.86
 * @version 0.1.97
 * @author Innoxia
 */
public enum GenderNames {

	Y_PENIS_Y_VAGINA_Y_BREASTS(true, true, true, "Футанари", "гермафродит", "гермафродит"),
	Y_PENIS_Y_VAGINA_N_BREASTS(true, true, false, "Футанари", "гермафродит", "гермафродит"),
	Y_PENIS_N_VAGINA_Y_BREASTS(true, false, true, "транссексуал", "транссексуал", "грудастый мальчик"),
	Y_PENIS_N_VAGINA_N_BREASTS(true, false, false, "трап", "трап", "мужчина"),
	N_PENIS_Y_VAGINA_Y_BREASTS(false, true, true, "женщина", "томбой",  "мужеподобный(ая)"),
	N_PENIS_Y_VAGINA_N_BREASTS(false, true, false, "женщина", "томбой", "кантбой"),
	N_PENIS_N_VAGINA_Y_BREASTS(false, false, true, "манекен", "neuter", "манекен"),
	N_PENIS_N_VAGINA_N_BREASTS(false, false, false, "манекен", "нейтрал", "манекен");
	
	
	private boolean hasPenis, hasVagina, hasBreasts;
	private String feminine, masculine, neutral;
	
	private GenderNames(boolean hasPenis, boolean hasVagina, boolean hasBreasts, String feminine, String neutral, String masculine){
		this.hasPenis = hasPenis;
		this.hasVagina = hasVagina;
		this.hasBreasts = hasBreasts;
		this.feminine = feminine;
		this.neutral = neutral;
		this.masculine = masculine;
	}

	public boolean isHasPenis() {
		return hasPenis;
	}

	public boolean isHasVagina() {
		return hasVagina;
	}

	public boolean isHasBreasts() {
		return hasBreasts;
	}
	
	public String getFeminine() {
		return feminine;
	}

	public String getMasculine() {
		return masculine;
	}

	public String getNeutral() {
		return neutral;
	}
}
