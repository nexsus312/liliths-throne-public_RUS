package com.lilithsthrone.game.dialogue.story;

import java.io.File;
import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.misc.PrologueFemale;
import com.lilithsthrone.game.character.npc.misc.PrologueMale;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.quests.QuestType;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CosmeticsDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class CharacterCreation {

	public static final int TIME_TO_NAME = 120;
	public static final int TIME_TO_APPEARANCE = 60;
	public static final int TIME_TO_CLOTHING = 30;
	public static final int TIME_TO_BACKGROUND = 150;
	public static final int TIME_TO_JOB = 150;
	public static final int TIME_TO_SEX_EXPERIENCE = 150;
	public static final int TIME_TO_FINAL_CHECK = 150;

	public static SpellSchool getStartingTomeSpellSchool() {
		if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 1) {
			return SpellSchool.EARTH;
		} else if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 2) {
			return SpellSchool.AIR;
		} else if(Main.game.getPlayer().getBirthMonth().getValue()  % 4 == 3) {
			return SpellSchool.WATER;
		}
		return SpellSchool.FIRE;
	}
	
	public static SpellSchool getStartingDemonstoneSpellSchool() {
		if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 2) {
			return SpellSchool.EARTH;
		} else if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 3) {
			return SpellSchool.AIR;
		} else if(Main.game.getPlayer().getBirthMonth().getValue()  % 4 == 0) {
			return SpellSchool.WATER;
		}
		return SpellSchool.FIRE;
	}

	public static final DialogueNode CHARACTER_CREATION_START = new DialogueNode("Отказ от ответственности", "", true) {

		@Override
		public String getContent() {
			return Main.disclaimer;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Согласится", "Вы соглашаетесь с тем, что вы достигли возраста, позволяющего просматривать порнографические материалы, и даете согласие на просмотр графического контента.", ALPHA_MESSAGE);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode ALPHA_MESSAGE = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel() {
			return "Version " + Main.VERSION_NUMBER + " | <b style='color:" + PresetColour.BASE_YELLOW_LIGHT.toWebHexString() + ";'>"+Main.VERSION_DESCRIPTION+"</b>";
		}
		
		@Override
		public String getContent() {
			return Main.getPatchNotes();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Начать", "Перейти к созданию персонажа.", CHOOSE_APPEARANCE){
					@Override
					public void effects() {
						Main.game.clearTextStartStringBuilder();
						Main.game.clearTextEndStringBuilder();
						Main.getProperties().setValue(PropertyValue.newWeaponDiscovered, false);
						Main.getProperties().setValue(PropertyValue.newClothingDiscovered, false);
						Main.getProperties().setValue(PropertyValue.newItemDiscovered, false);
						Main.game.getPlayer().calculateStatusEffects(0);
						Main.game.getPlayer().setHealthPercentage(1);
						Main.game.getPlayer().setManaPercentage(1);
						getDressed();
						resetBodyAppearance();
						
						Main.game.setRenderAttributesSection(true);
						Main.game.getPlayer().setName(new NameTriplet("Неизвестен", "Неизвестено", "Неизвестена"));
						Main.game.getPlayer().setSurname("");
						BodyChanging.setTarget(Main.game.getPlayer());
					}
				};
				
			} else if (index == 2) {
				return new Response("Начать (Импорт)", "Импортируйте персонажа из предыдущей версии, чтобы использовать его при старте игры.", IMPORT_CHOOSE) {
					@Override
					public void effects() {
						Main.game.getPlayerCell().resetInventory();
					}
				};
			}
			return null;
		};
	};

	public static void resetBodyAppearance() {
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_LIGHT), true);
		Main.game.getNpc(Lilaya.class).setSkinCovering(new Covering(BodyCoveringType.HUMAN, Main.game.getPlayer().getCovering(BodyCoveringType.HUMAN).getPrimaryColour()), true);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_BROWN), true);
		Main.game.getPlayer().setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_BROWN), true);
		Main.game.getPlayer().setBreastShape(BreastShape.ROUND);
		Main.game.getPlayer().setVaginaLabiaSize(LabiaSize.TWO_AVERAGE.getValue());

		Main.game.getPlayer().setFacialHair(BodyHair.ZERO_NONE);
		switch(Main.game.getPlayer().getFemininity()) {
			case MASCULINE_STRONG:
				Main.game.getPlayer().setUnderarmHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setAssHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setPubicHair(BodyHair.FOUR_NATURAL);
				break;
			case MASCULINE:
				Main.game.getPlayer().setUnderarmHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setAssHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setPubicHair(BodyHair.FOUR_NATURAL);
				break;
			case ANDROGYNOUS:
				Main.game.getPlayer().setUnderarmHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setAssHair(BodyHair.TWO_MANICURED);
				Main.game.getPlayer().setPubicHair(BodyHair.FOUR_NATURAL);
				break;
			case FEMININE:
				Main.game.getPlayer().setUnderarmHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setAssHair(BodyHair.TWO_MANICURED);
				Main.game.getPlayer().setPubicHair(BodyHair.THREE_TRIMMED);
				break;
			case FEMININE_STRONG:
				Main.game.getPlayer().setUnderarmHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setAssHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setPubicHair(BodyHair.ZERO_NONE);
				break;
		}
	}
	
	public static void setGenderFemale() {
		Femininity fem = Femininity.FEMININE;
		switch(BodyChanging.getTarget().getFemininity()) {
			case ANDROGYNOUS:
				fem = Femininity.ANDROGYNOUS;
				break;
			case MASCULINE:
				fem = Femininity.FEMININE;
				break;
			case MASCULINE_STRONG:
				fem = Femininity.FEMININE_STRONG;
				break;
			default:
				break;
		}
		BodyChanging.getTarget().setBody(Gender.F_V_B_FEMALE, RacialBody.HUMAN, RaceStage.HUMAN, false);
		BodyChanging.getTarget().setFemininity(fem.getMedianFemininity());
		
		if(BodyChanging.getTarget().isPlayer()) {
			getDressed();
			CharacterCreation.resetBodyAppearance();
		}
	}
	
	public static void setGenderMale() {
		Femininity fem = Femininity.MASCULINE;
		switch(BodyChanging.getTarget().getFemininity()) {
			case ANDROGYNOUS:
				fem = Femininity.ANDROGYNOUS;
				break;
			case FEMININE:
				fem = Femininity.MASCULINE;
				break;
			case FEMININE_STRONG:
				fem = Femininity.MASCULINE_STRONG;
				break;
			default:
				break;
		}
		BodyChanging.getTarget().setBody(Gender.M_P_MALE, RacialBody.HUMAN, RaceStage.HUMAN, false);
		BodyChanging.getTarget().setFemininity(fem.getMedianFemininity());

		if(BodyChanging.getTarget().isPlayer()) {
			getDressed();
			CharacterCreation.resetBodyAppearance();
		}
	}
	
	private static void equipPiercings() {
		Colour colour1 = Main.game.getPlayer().isFeminine()?PresetColour.CLOTHING_GOLD:PresetColour.CLOTHING_BLACK_STEEL;
		Colour colour2 = Main.game.getPlayer().isFeminine()?PresetColour.CLOTHING_ROSE_GOLD:PresetColour.CLOTHING_SILVER;
		
		for(InventorySlot slot : InventorySlot.getPiercingSlots()) {
			if(Main.game.getPlayer().getClothingInSlot(slot)!=null){
				Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(slot), true, Main.game.getPlayer());
			}
		}
		
		// Ear piercings:
		if(Main.game.getPlayer().isPiercedEar()) {
			Main.game.getPlayer().equipClothingFromGround(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", colour1, false), true, Main.game.getPlayer());
			
		} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_EAR)!=null){
			Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_EAR), true, Main.game.getPlayer());
		}
		
		// Lip piercings:
		if(Main.game.getPlayer().isPiercedLip()) {
			Main.game.getPlayer().equipClothingFromGround(Main.game.getItemGen().generateClothing("innoxia_piercing_lip_double_ring", colour1, false), true, Main.game.getPlayer());
			
		} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_LIP)!=null){
			Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_LIP), true, Main.game.getPlayer());
		}
		
		// Navel piercings:
		if(Main.game.getPlayer().isPiercedNavel() && Main.game.getPlayer().isFeminine()) {
			Main.game.getPlayer().equipClothingFromGround(Main.game.getItemGen().generateClothing("innoxia_piercing_gemstone_barbell", colour2, false), InventorySlot.PIERCING_STOMACH, true, Main.game.getPlayer());
			
		} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_STOMACH)!=null){
			Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_STOMACH), true, Main.game.getPlayer());
		}

		// Nipples piercings:
		if(Main.game.getPlayer().isPiercedNipple()) {
			Main.game.getPlayer().equipClothingFromGround(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell_pair", colour2, false), InventorySlot.PIERCING_NIPPLE, true, Main.game.getPlayer());
			
		} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_NIPPLE)!=null){
			Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_NIPPLE), true, Main.game.getPlayer());
		}

		// Nose piercings:
		if(Main.game.getPlayer().isPiercedNose()) {
			Main.game.getPlayer().equipClothingFromGround(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", colour1, false), true, Main.game.getPlayer());
			
		} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_NOSE)!=null){
			Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_NOSE), true, Main.game.getPlayer());
		}

		// Penis piercings:
		if(Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isPiercedPenis()) {
			Main.game.getPlayer().equipClothingFromGround(Main.game.getItemGen().generateClothing("innoxia_piercing_penis_ring", colour2, false), true, Main.game.getPlayer());
			
		} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_PENIS)!=null){
			Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_PENIS), true, Main.game.getPlayer());
		}

		// Tongue piercings:
		if(Main.game.getPlayer().isPiercedTongue()) {
			Main.game.getPlayer().equipClothingFromGround(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", colour1, false), InventorySlot.PIERCING_TONGUE, true, Main.game.getPlayer());
			
		} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_TONGUE)!=null){
			Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_TONGUE), true, Main.game.getPlayer());
		}

		// Vagina piercings:
		if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isPiercedVagina()) {
			Main.game.getPlayer().equipClothingFromGround(Main.game.getItemGen().generateClothing("innoxia_piercing_ringed_barbell", colour2, false), InventorySlot.PIERCING_VAGINA, true, Main.game.getPlayer());
			
		} else if(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_VAGINA)!=null){
			Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(InventorySlot.PIERCING_VAGINA), true, Main.game.getPlayer());
		}
	}
	
	public static void getDressed() {
		getDressed(Main.game.getPlayer(), true);
	}
	
	public static void getDressed(GameCharacter character, boolean spawnClothingOnFloor) {
		character.resetInventory(false);
		Main.game.getPlayerCell().resetInventory();
		
		equipPiercings();
		
		switch(character.getFemininity()) {
			case MASCULINE_STRONG:
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_briefs", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_long_sleeved_shirt", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_RED, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_suit_jacket", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_trousers", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_mens_smart_shoes", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_GOLD, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_GOLD, false), true, character);
				
				if(spawnClothingOnFloor) {
					spawnClothingInArea();
				}
				break;
				
			case MASCULINE:
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_short_sleeved_shirt", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_trousers", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_mens_smart_shoes", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_SILVER, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_SILVER, false), true, character);

				if(spawnClothingOnFloor) {
					spawnClothingInArea();
				}
				break;
				
			case ANDROGYNOUS:
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_WHITE, false), true, character);
				if(character.getBreastRawSizeValue()!=0) {
					character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_croptop_bra", PresetColour.CLOTHING_WHITE, false), true, character);
				} else {
					Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_chest_croptop_bra", PresetColour.CLOTHING_WHITE, false));
				}
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_short_sleeved_shirt", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_GREY, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_low_top_skater_shoes", PresetColour.CLOTHING_RED, false), true, character);
				
				if(spawnClothingOnFloor) {
					spawnClothingInArea();
				}
				break;
				
			case FEMININE:
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_plunge_bra", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_SKATER_DRESS, PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_trainer_socks", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_heels", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_PINK_LIGHT, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_SILVER, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_heart_necklace", PresetColour.CLOTHING_SILVER, false), true, character);

				if(spawnClothingOnFloor) {
					spawnClothingInArea();
				}
				break;
				
			case FEMININE_STRONG:
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_thong", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_plunge_bra", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_SLIP_DRESS, PresetColour.CLOTHING_RED_BURGUNDY, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_pantyhose", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_stiletto_heels", PresetColour.CLOTHING_RED_BURGUNDY, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_GOLD, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_heart_necklace", PresetColour.CLOTHING_GOLD, false), true, character);

				if(spawnClothingOnFloor) {
					spawnClothingInArea();
				}
				break;
			default:
				break;
		}
		
		if(character.isPlayer()
				&& ((character.getName(false).equals("James") || character.getName(false).equals("Jane") || character.getName(false).equals("Tracy")) && character.getSurname().equals("Bond"))) {
			character.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_western_kkp_western_kkp")));
		}
	}
	
	private static void generateClothingOnFloor(String clothingType, Colour colour) {
		generateClothingOnFloor(ClothingType.getClothingTypeFromId(clothingType), colour, null, null);
	}
	
	private static void generateClothingOnFloor(AbstractClothingType clothingType, Colour colour) {
		generateClothingOnFloor(clothingType, colour, null, null);
	}
	
	private static void generateClothingOnFloor(AbstractClothingType clothingType, Colour colour, Colour colour2, Colour colour3) {
		for(AbstractClothing clothing : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
			if(clothing.getClothingType()==clothingType) {
				return;
			}
		}
		Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing(clothingType, colour, colour2, colour3, false));
	}
	
	private static void spawnClothingInArea() {
		
		
		switch(Main.game.getPlayer().getFemininity()) {
			case MASCULINE:
			case MASCULINE_STRONG:
				generateClothingOnFloor("bloom_wasp609_rainCoat_rain_coat", PresetColour.CLOTHING_BLUE_NAVY);
				generateClothingOnFloor(ClothingType.getClothingTypeFromId("innoxia_foot_trainers"), PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_BLUE_GREY, PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_foot_work_boots", PresetColour.CLOTHING_TAN);
				generateClothingOnFloor("innoxia_foot_low_top_skater_shoes", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_sock_socks", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_leg_cargo_trousers", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_GREY);
				generateClothingOnFloor("innoxia_groin_boxers", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_eye_aviators", PresetColour.CLOTHING_BLACK_STEEL);
				generateClothingOnFloor("innoxia_eye_glasses", PresetColour.CLOTHING_BLACK_STEEL);
				generateClothingOnFloor("innoxia_hand_gloves", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_head_cap", PresetColour.CLOTHING_BLUE);
				generateClothingOnFloor("innoxia_neck_scarf", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_torsoOver_hoodie", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_torsoOver_ribbed_jumper", PresetColour.CLOTHING_GREY);
				generateClothingOnFloor("innoxia_torso_short_sleeved_shirt", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_torso_tshirt", PresetColour.CLOTHING_BLUE_LIGHT);
				generateClothingOnFloor("innoxia_groin_briefs", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_torso_long_sleeved_shirt", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_neck_tie", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_torsoOver_suit_jacket", PresetColour.CLOTHING_BLACK);
				break;
				
			case ANDROGYNOUS:
				generateClothingOnFloor("bloom_wasp609_rainCoat_rain_coat", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor(ClothingType.getClothingTypeFromId("innoxia_foot_trainers"), PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_PURPLE_DARK, PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_foot_heels", PresetColour.CLOTHING_BLACK);
				
				generateClothingOnFloor("innoxia_groin_thong", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_groin_lacy_panties", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_groin_briefs", PresetColour.CLOTHING_WHITE);

				generateClothingOnFloor("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_RED);

				generateClothingOnFloor("innoxia_sock_kneehigh_socks", PresetColour.CLOTHING_WHITE);

				generateClothingOnFloor("innoxia_leg_cargo_trousers", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_trousers", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_skirt", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_yoga_pants", PresetColour.CLOTHING_PINK_LIGHT);
				generateClothingOnFloor("innoxia_leg_tight_jeans", PresetColour.CLOTHING_BLUE_NAVY);
				generateClothingOnFloor("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_GREY);
				generateClothingOnFloor("innoxia_leg_distressed_jeans", PresetColour.CLOTHING_BLUE_GREY);

				generateClothingOnFloor("innoxia_neck_scarf", PresetColour.CLOTHING_RED);
				
				generateClothingOnFloor("innoxia_head_cap", PresetColour.CLOTHING_BLUE);
				
				generateClothingOnFloor("innoxia_stomach_underbust_corset", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor("innoxia_torso_tshirt", PresetColour.CLOTHING_BLUE_LIGHT);
				generateClothingOnFloor("innoxia_torso_blouse", PresetColour.CLOTHING_BLUE_LIGHT);
				generateClothingOnFloor(ClothingType.TORSO_CAMITOP_STRAPS, PresetColour.CLOTHING_GREEN);
				
				generateClothingOnFloor("innoxia_torsoOver_hoodie", PresetColour.CLOTHING_PINK_LIGHT);
				generateClothingOnFloor("innoxia_torsoOver_open_front_cardigan", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor("innoxia_finger_ring", PresetColour.CLOTHING_SILVER);
				generateClothingOnFloor("innoxia_neck_heart_necklace", PresetColour.CLOTHING_SILVER);
				generateClothingOnFloor(ClothingType.WRIST_BANGLE, PresetColour.CLOTHING_SILVER);
				generateClothingOnFloor("innoxia_ankle_anklet", PresetColour.CLOTHING_SILVER);
				
				generateClothingOnFloor("innoxia_eye_glasses", PresetColour.CLOTHING_BLACK_STEEL);
				break;
				
			case FEMININE:
			case FEMININE_STRONG:
				generateClothingOnFloor("bloom_wasp609_rainCoat_rain_coat", PresetColour.CLOTHING_PURPLE_DARK);
				generateClothingOnFloor("innoxia_torsoOver_womens_winter_coat", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_sock_stockings", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor(ClothingType.HIPS_SUSPENDER_BELT, PresetColour.CLOTHING_BLACK);
				
				generateClothingOnFloor("innoxia_groin_panties", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_groin_thong", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_groin_lacy_panties", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_groin_vstring", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_chest_fullcup_bra", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor("innoxia_sock_pantyhose", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_sock_kneehigh_socks", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_sock_thighhigh_socks", PresetColour.CLOTHING_WHITE);

				generateClothingOnFloor("innoxia_eye_aviators", PresetColour.CLOTHING_ROSE_GOLD);
				generateClothingOnFloor("innoxia_eye_glasses", PresetColour.CLOTHING_BLACK_STEEL);

				generateClothingOnFloor("innoxia_foot_ankle_boots", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_foot_low_top_skater_shoes", PresetColour.CLOTHING_PINK_LIGHT);
				generateClothingOnFloor("innoxia_foot_thigh_high_boots", PresetColour.CLOTHING_TAN);
				generateClothingOnFloor("innoxia_foot_stiletto_heels", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_foot_heels", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor("innoxia_hand_elbow_length_gloves", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_head_headband", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor(ClothingType.getClothingTypeFromId("innoxia_head_headband_bow"), PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_PINK);

				generateClothingOnFloor("innoxia_leg_hotpants", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_leg_mini_skirt", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_skirt", PresetColour.CLOTHING_PINK);
				generateClothingOnFloor("innoxia_leg_yoga_pants", PresetColour.CLOTHING_PINK_LIGHT);
				generateClothingOnFloor("innoxia_leg_pencil_skirt", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_tight_jeans", PresetColour.CLOTHING_BLUE_NAVY);
				generateClothingOnFloor("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_GREY);
				generateClothingOnFloor("innoxia_leg_distressed_jeans", PresetColour.CLOTHING_BLUE_GREY);
				
				generateClothingOnFloor("innoxia_neck_scarf", PresetColour.CLOTHING_RED);
				
				generateClothingOnFloor("innoxia_stomach_underbust_corset", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor(ClothingType.getClothingTypeFromId("innoxia_torso_feminine_short_sleeve_shirt"), PresetColour.CLOTHING_BLUE_LIGHT);
				generateClothingOnFloor("innoxia_torso_blouse", PresetColour.CLOTHING_BLUE_LIGHT);
				generateClothingOnFloor(ClothingType.TORSO_CAMITOP_STRAPS, PresetColour.CLOTHING_GREEN);
				generateClothingOnFloor(ClothingType.TORSO_LONG_SLEEVE_DRESS, PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor(ClothingType.TORSO_SHORT_CROPTOP, PresetColour.CLOTHING_PINK);
				generateClothingOnFloor(ClothingType.TORSO_VIRGIN_KILLER_SWEATER, PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor(ClothingType.TORSO_SLIP_DRESS, PresetColour.CLOTHING_RED);
				generateClothingOnFloor(ClothingType.TORSO_SKATER_DRESS, PresetColour.CLOTHING_BLACK);
				
				generateClothingOnFloor("innoxia_torsoOver_open_front_cardigan", PresetColour.CLOTHING_BLACK);
				
				generateClothingOnFloor(ClothingType.WRIST_BANGLE, PresetColour.CLOTHING_GOLD);
				generateClothingOnFloor("innoxia_ankle_anklet", PresetColour.CLOTHING_GOLD);
				break;
		}
	}
	
	public static final DialogueNode CHOOSE_APPEARANCE = new DialogueNode("Ночная прогулка", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<p>"
						+ "К тому времени, когда такси наконец подъезжает к Британскому музею, вы уже опаздываете почти на пять минут."
						+ " Вы приехали в Лондон, чтобы посетить вечер, посвященный открытию новой выставки вашей тети Лили,"
							+ " торопливо расплачиваясь с водителем за проезд и выходя из машины, вы надеетесь, что она еще не начала свою речь."
					+ "</p>"
					+ "<p>"
						+ "Уличные фонари мерцают, когда вы спешите к входу, освещая окрестности тусклым оранжевым светом."
						+ " Проходит совсем немного времени, и вы оказываетесь у входа в музей, где, к своему огорчению, видите, что образовалась небольшая очередь."
						+ " Не имея другого выбора, кроме как встать в очередь и ждать, вы бросаете короткий взгляд на большие стеклянные окна современного фасада здания, чтобы увидеть свое размытое отражение в стекле..."
					+ "</p>"
					+ "<br/>"
					
					+ CharacterModificationUtils.getStartDateDiv()
					
					+ "<div class='cosmetics-container' style='background:transparent;'>"
					
						+ CharacterModificationUtils.getGenderChoiceDiv()
						
						+ CharacterModificationUtils.getFemininityChoiceDiv()
						
						+ "<div class='container-full-width' style='text-align:center;'>"
							+ "К вам будут обращаться как к <span style='color:"+Main.game.getPlayer().getGender().getColour().toWebHexString()+";'>"
								+UtilText.generateSingularDeterminer(Main.game.getPlayer().getGender().getName())+ " " + Main.game.getPlayer().getGender().getName()+"</span>.<br/>"
							+ "<i>Вы можете изменить все имена полов в меню опций.</i>"
						+ "</div>"

						+ CharacterModificationUtils.getBirthdayChoiceDiv()
						
						+ CharacterModificationUtils.getOrientationChoiceDiv()
						
						+ CharacterModificationUtils.getPersonalityChoiceDiv(false)
						
					+"</div>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Ждите своей очереди и надейтесь, что мероприятие еще не началось.", CHOOSE_NAME) {
					@Override
					public int getSecondsPassed() {
						return TIME_TO_NAME;
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "Возврат в главное меню.", OptionsDialogue.MENU);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_NAME = new DialogueNode("Ночная прогулка", "", true) {

		boolean unsuitableName = false, unsuitableSurname = false;
		
		@Override
		public String getHeaderContent() {
			return "<p>"
						+ "[npcMale.speech("+(Main.game.getPlayer().isFeminine()?"Мисс":"Сэр")+",)]"
						+ " обращается к вам швейцар, очевидно, закончив с другими людьми в очереди,"
						+ " [npcMale.speech(У вас есть приглашение?)]"
					+ "</p>"
					+ "<p>"
						+ "Вы отворачиваетесь от стекла и делаете шаг вперед, улыбаясь."
						+ " [pc.speech(Да, он у меня прямо здесь... эээ... подождите...)]"
					+ "</p>"
					+ "<p>"
						+ "Потянувшись к своему "+(Main.game.getPlayer().isFeminine()?"сумочка":"кошелек")+", вы чувствуете, как ваше сердце начинает колотиться, когда вы обнаруживаете, что внутри нет приглашения."
						+ " [pc.speech(Нет, нет, нет! Я, наверное, оставил его в такси!)]"
					+ "</p>"
					+ "<p>"
						+ "[npcMale.speech(Не беспокойтесь,)]"
						+ " отвечает мужчина,"
						+ " [npcMale.speech(если вы сообщите мне свое имя, я смогу проверить, есть ли вы в списке.)]"
					+ "</p>"
					+ "<p>"
						+ "Вздохнув с облегчением, вы называете мужчине свое имя..."
					+ "</p>"
					+"<br/>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<div style='position:relative; display:inline-block; padding-bottom:0; margin 0 auto; vertical-align:middle; width:100%; text-align:center;'>"
							+ "<i>"
								+ "Ваше имя может быть задано в трех значениях: мужчина, гермафродит, женщина."
								+ " Ваше имя автоматически переключится на то, которое соответствует женственности вашего тела."
							+ "</i>"
							+ "<br/>"
							+ "<p style='display:inline-block; padding:0; margin:0; height:32px; line-height:32px; width:100px;'>First name: </p>"
							+ "</form style='display:inline-block; padding:0; margin:0; text-align:center;'>"
									+ "<input type='text' id='nameMasculineInput' style=' color:"+PresetColour.MASCULINE.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getMasculine())+ "'>"
									
							+ "</form style='display:inline-block; padding:0; margin:0; text-align:center;'>"
								+ "<input type='text' id='nameAndrogynousInput' style=' color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getAndrogynous())+ "'>"
								
							+ "</form style='display:inline-block; padding:0; margin:0; text-align:center;'>"
								+ "<input type='text' id='nameFeminineInput' style=' color:"+PresetColour.FEMININE.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getFeminine())+ "'>"
							
							+ "<br/>"
							+ "<p style='display:inline-block; padding:0; margin:0; height:32px; line-height:32px; width:100px;'>Surname: </p>"
							+ "<form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' id='surnameInput' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getSurname())+ "'></form>"
						+ "</div>"
						+ "<br/>"
						+ "<i>Длина вашего имени должна составлять от 2 до 32 символов. Нельзя использовать символы квадратных скобок или обычных. (Фамилию можно не заполнять.)</i>"
						+ (unsuitableName ? "<p style='text-align:center;padding-top:0;'><b style=' color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Invalid name.</b></p>" : "")
						+ (unsuitableSurname ? "<p style='text-align:center;padding-top:0;'><b style=' color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>Invalid Surname.</b></p>" : "")
					+ "</div>"
					
					+ "<p id='hiddenFieldName' style='display:none;'></p>"
					+ "<p id='hiddenFieldSurname' style='display:none;'></p>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Продолжить", "Используя это имя, перейти к следующему этапу экрана создания персонажа."){
					@Override
					public int getSecondsPassed() {
						if (unsuitableName || unsuitableSurname)  {
							return super.getSecondsPassed();
						}
						return TIME_TO_APPEARANCE;
					}
					@Override
					public void effects() {
						List<String> fieldsList = Util.newArrayListOfValues("nameMasculineInput", "nameAndrogynousInput", "nameFeminineInput");
						List<String> namesList = new ArrayList<>();
						for(String s : fieldsList) {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('"+s+"').value;");
							if(Main.mainController.getWebEngine().getDocument()!=null) {
								if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 2
										|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32
										|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().matches("[^\\[\\]\\.]+")) {
									unsuitableName = true;
								} else {
									unsuitableName = false;
									namesList.add(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
								}
							}
						}
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldSurname').innerHTML=document.getElementById('surnameInput').value;");
						if(Main.mainController.getWebEngine().getDocument()!=null) {
							if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()>=1
									&& (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length() > 32
											|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().matches("[^\\[\\]\\.]+"))) {
								unsuitableSurname = true;
							} else {
								unsuitableSurname = false;
							}
						}
						if (unsuitableName || unsuitableSurname)  {
							Main.game.setContent(new Response("" ,"", CHOOSE_NAME));
							
						} else {
							Main.game.getPlayer().setName(new NameTriplet(namesList.get(0), namesList.get(1), namesList.get(2)));
							Main.game.getPlayer().setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent());

							Main.game.getPlayerCell().resetInventory();
							Main.game.getPlayer().moveToAdjacentMatchingCellType(false, PlaceType.MUSEUM_LOBBY);
							Main.game.setContent(new Response("" ,"", CHOOSE_ADVANCED_APPEARANCE));
							getDressed();
						}
					}
				};
				
			} else if (index == 2) {
				return new Response("Random", "Generate a random name based on your gender.", CHOOSE_NAME){
					@Override
					public void effects() {
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldSurname').innerHTML=document.getElementById('surnameInput').value;");
						if(Main.mainController.getWebEngine().getDocument()!=null) {
							if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()>=1
									&& (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length() > 16
											|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().matches("[^\\[\\]\\.]+")))
								unsuitableSurname = true;
							else {
								unsuitableSurname = false;
							}
						}
						if(!unsuitableSurname) {
							Main.game.getPlayer().setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent());
						}

						Main.game.getPlayer().setName(Name.getRandomTriplet(Subspecies.HUMAN));
					}
				};
				
			} else if (index == 3) {
				return new Response("Random Surname", "Generate a random surname.", CHOOSE_NAME){
					@Override
					public void effects() {
						List<String> fieldsList = Util.newArrayListOfValues("nameMasculineInput", "nameAndrogynousInput", "nameFeminineInput");
						List<String> namesList = new ArrayList<>();
						for(String s : fieldsList) {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('"+s+"').value;");
							if(Main.mainController.getWebEngine().getDocument()!=null) {
								if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 2
										|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 16
										|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().matches("[^\\[\\]\\.]+"))
									unsuitableName = true;
								else {
									unsuitableName = false;
									namesList.add(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
								}
							}
						}
						if(!unsuitableName) {
							Main.game.getPlayer().setName(new NameTriplet(namesList.get(0), namesList.get(1), namesList.get(2)));
						}
						
						Main.game.getPlayer().setSurname(Name.getSurname(Main.game.getPlayer()));
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Return to gender selection.", CHOOSE_APPEARANCE) {
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_NAME;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE = new DialogueNode("В музее", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<p>"
						+ "[pc.speech(It's "+(Main.game.getPlayer().getSurname().length()!=0?"[pc.surname], [pc.name] [pc.surname]":"[pc.name]")+",)]"
						+ " говорите вы, нетерпеливо поглядывая на планшет, в котором он изучает свой список."
					+ "</p>"
					+ "<p>"
						+ "Наконец вы видите, как он проводит пальцем по вашему имени, и, улыбнувшись, отходит в сторону, приглашая вас пройти вперед."
						+ " [npcMale.speech(Хорошего вечера, "+(Main.game.getPlayer().getSurname().length()!=0
								?(Main.game.getPlayer().isFeminine()?"Мисс":"М-р.")+" [pc.surname]"
								:(Main.game.getPlayer().isFeminine()?"Мисс":"Сэр"))+".)]"
					+ "</p>"
					+ "<p>"
						+ "Поблагодарив его, вы торопливо проходите через вход и через несколько мгновений оказываетесь в огромном центральном холле музея."
						+ " На балконах верхнего этажа развешаны большие плакаты, на которых жирным шрифтом гордо заявлено, что это 'Выставка Аккадской империи: Вечер открытия'."
						+ " В дальнем конце большого зала вы видите толпы людей, окружающих большую сцену, и облегченно вздыхаете, заметив, что сейчас она пуста."
					+ "</p>"
					+ "<p>"
						+ "[pc.thought(Фух... Я все-таки успел вовремя...)]"
					+ "</p>"
					+ "<p>"
						+ "Поскольку вступительная речь Лили, похоже, опаздывает так же, как и вы, вы решаете подойти к ближайшему зеркалу, чтобы убедиться, что выглядите презентабельно..."
					+ "</p>"
					+ "<br/>"
					+ "<div class='container-full-width'>"
						+ "<h5 style='text-align:center;'>Appearance</h5>"
						+ Main.game.getPlayer().getBodyDescription()
					+ "</div>"
					+ "<br/>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>Вы можете изменить свой внешний вид, зайдя в каждое из подменю ниже.</i>"
					+ "</div>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить",
						"Ваша одежда немного грязновата после спешки. Приведите себя в порядок, прежде чем перейти к главной сцене.",
						InventoryDialogue.INVENTORY_MENU) {
					@Override
					public int getSecondsPassed() {
						return TIME_TO_CLOTHING;
					}
					@Override
					public void effects() {
						equipPiercings();
						InventoryDialogue.setBuyback(false);
						InventoryDialogue.setInventoryNPC(null);
						InventoryDialogue.setNPCInventoryInteraction(InventoryInteraction.CHARACTER_CREATION);
					}
				};
				
			} else if (index == 2) {
				return new Response("Основа", "Войдите в меню настройки для всех основных аспектов вашего тела.", CHOOSE_ADVANCED_APPEARANCE_CORE);
				
			} else if (index == 3) {
				return new Response("Лицо", "Войти в меню настроек связаных с вашим лицом.", CHOOSE_ADVANCED_APPEARANCE_FACE);
				
			} else if (index == 4) {
				return new Response("Волосы", "Войти в меню настроек связаных с вашими волосами.", CHOOSE_ADVANCED_APPEARANCE_HAIR);
				
			} else if (index == 5) {
				return new Response("Грудь", "Войти в меню настроек связаных с вашей грудью.", CHOOSE_ADVANCED_APPEARANCE_BREASTS);
				
			} else if (index == 6) {
				return new Response("Задница & Бедра", "Войти в меню настроек связаных с вашей задницей, бедрами и анусом.", CHOOSE_ADVANCED_APPEARANCE_ASS);
				
			} else if (index == 7) {
				return new Response((Main.game.getPlayer().hasPenis()?"Пенис":"Вагина"), "Войти в меню настройки связанной с "+(Main.game.getPlayer().hasPenis()?"пенисом":"вагиной")+".", CHOOSE_ADVANCED_APPEARANCE_GENITALS);
				
			}  else if (index == 8) {
				return new Response("Макияж", "ВВойти в меню настроек связаных с макияжем.", CHOOSE_ADVANCED_APPEARANCE_COSMETICS);
				
			} else if (index == 9) {
				return new Response("Пирсинг", "Войти в меню настроек связаных с пирсингом.", CHOOSE_ADVANCED_APPEARANCE_PIERCINGS);
				
			} else if (index == 10) {
				return new Response("Тату", "Войти в меню настроек связаных с тату", CHOOSE_ADVANCED_APPEARANCE_TATTOOS);
				
			} else if (index == 11) {
				return new Response("Дополнительные волосы", "Войти в меню настроек связаных с лицом, лобком и волосами на теле.", CHOOSE_ADVANCED_APPEARANCE_BODY_HAIR);
				
			} else if (index == 0) {
				return new Response("Вернутся", "Подтвердите свой выбор и вернитесь к настройкам.", CHOOSE_NAME) {
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_APPEARANCE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.MUSEUM, PlaceType.MUSEUM_ENTRANCE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_CORE = new DialogueNode("Основа внешнего вида тела", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>На все эти параметры можно повлиять позже в игре.</i>"
					+ "</div>"
						
					+ CharacterModificationUtils.getHeightChoiceDiv()
					
					+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.HUMAN, BodyCoveringType.HUMAN, "Цвет кожи", "Цвет кожи, покрывающей ваше тело.", true, false, false)
					
					+ "<div class='cosmetics-container' style='background:transparent;'>"
					
						+ CharacterModificationUtils.getBodySizeChoiceDiv()
						
						+ CharacterModificationUtils.getMuscleChoiceDiv()
						
						+ "<div class='container-full-width' style='text-align:center;'>"
							+ "Ваши мускулы и размер тела влияет на на ваш внешний вид:<br/>"
							+ "<b style='color:"+Main.game.getPlayer().getBodyShape().toWebHexStringColour()+";'>"+Util.capitaliseSentence(Main.game.getPlayer().getBodyShape().getName(false))+"</b>"
						+ "</div>"
					
					+"</div>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свой выбор и вернитесь к настройкам.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_FACE = new DialogueNode("Внешний вид лица", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>На все эти параметры можно повлиять позже в игре.</i>"
					+ "</div>"

					+ CharacterModificationUtils.getLipSizeDiv()
					
					+ CharacterModificationUtils.getLipPuffynessDiv()

					+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Main.game.getPlayer().getEyeType().getRace(), BodyCoveringType.EYE_HUMAN, "Цвет радужки", "Цвет радужки вашего глаза.", true, false, false);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свой выбор и вернитесь к настройкам.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_HAIR = new DialogueNode("Внешний вид волос", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>На все эти параметры можно повлиять позже в игре.</i>"
					+ "</div>"

					+ CharacterModificationUtils.getKatesDivHairLengths(false, "Длинна волос", "Выберите, какой длины у вас волосы.")
					
					+ CharacterModificationUtils.getKatesDivHairStyles(false, "Стиль прически", "Выберите свой стиль прически. Некоторые прически недоступны при более короткой длине волос.")

					+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Main.game.getPlayer().getHairType().getRace(), BodyCoveringType.HAIR_HUMAN, "Цвет волос", "Цвет ваших волос.", true, false);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свой выбор и вернитесь к настройкам.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_BREASTS = new DialogueNode("Внешний вид груди", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>На все эти параметры можно повлиять позже в игре.</i>"
					+ "</div>"
						
					+ CharacterModificationUtils.getBreastSizeDiv()
					
					+ CharacterModificationUtils.getBreastShapeDiv()
					
					+ CharacterModificationUtils.getNippleSizeDiv()
					
					+ CharacterModificationUtils.getAreolaeSizeDiv()
					
					+ CharacterModificationUtils.getNipplePuffynessDiv()
					
					+ CharacterModificationUtils.getSelfTransformLactationDiv();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свой выбор и вернитесь к настройкам.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_ASS = new DialogueNode("Внешний вид задницы", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>На все эти параметры можно повлиять позже в игре.</i>"
					+ "</div>"
						
					+ CharacterModificationUtils.getAssSizeDiv()
					
					+ CharacterModificationUtils.getHipSizeDiv()
					
					+ CharacterModificationUtils.getBleachedAnusDiv();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свой выбор и вернитесь к настройкам.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_GENITALS = new DialogueNode("Внешний вид гениталий", "", true) {
		
		@Override
		public String getLabel() {
			if(Main.game.getPlayer().hasPenis()) {
				return "Внешний вид пениса";
			} else {
				return "Внешний вид вагины";
			}
		}
		
		@Override
		public String getHeaderContent() {
			if(Main.game.getPlayer().hasPenis()) {
				return "<div class='container-full-width' style='text-align:center;'>"
							+ "<i>На все эти параметры можно повлиять позже в игре.</i>"
						+ "</div>"
							
							+ CharacterModificationUtils.getPenisSizeDiv()
							
							+ CharacterModificationUtils.getTesticleSizeDiv()
							
							+ CharacterModificationUtils.getSelfTransformCumProductionDiv();
				
			} else {
				return "<div class='container-full-width' style='text-align:center;'>"
							+ "<i>На все эти параметры можно повлиять позже в игре.</i>"
						+ "</div>"
	
							+ CharacterModificationUtils.getVaginaCapacityDiv()
							
							+ CharacterModificationUtils.getLabiaSizeDiv()
							
							+ CharacterModificationUtils.getClitorisSizeDiv();
				
			}
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свой выбор и вернитесь к настройкам.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_PIERCINGS = new DialogueNode("Пирсинг", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>На все эти параметры можно повлиять позже в игре.</i>"
					+ "</div>"
						
					+CharacterModificationUtils.getKatesDivPiercings(true);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свой выбор и вернитесь к настройкам.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_TATTOOS = new DialogueNode("Тату", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>Позже в игре вы сможете делать зачарованные и светящиеся татуировки. Однако пока выбор татуировок ограничен более обыденными вариантами.</i>"
					+ "</div>"
					+CharacterModificationUtils.getKatesDivTattoos();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свой выбор и вернитесь к настройкам.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_TATTOOS_ADD = new DialogueNode("Секрет Суккубы", "-", true) {

		@Override
		public String getLabel() {
			return "Add "+Util.capitaliseSentence(CharacterModificationUtils.tattooInventorySlot.getName()) +" Тату";
		}
		
		@Override
		public String getContent() {
			return CharacterModificationUtils.getKatesDivTattoosAdd();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(CharacterModificationUtils.tattoo.getType().equals(TattooType.getTattooTypeFromId("innoxia_misc_none"))
						&& CharacterModificationUtils.tattoo.getWriting().getText().isEmpty()
						&& CharacterModificationUtils.tattoo.getCounter().getType()==TattooCounterType.NONE) {
					return new Response("Применить", "Чтобы сделать татуировку, вам нужно выбрать ее тип, добавить надпись или счетчик!", null);
					
				} else {
					return new Response("Применить", 
							UtilText.parse(BodyChanging.getTarget(), "Добавить это тату."), CHOOSE_ADVANCED_APPEARANCE_TATTOOS) {
						@Override
						public void effects() {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.setName(CharacterModificationUtils.tattoo.getType().getName());
							BodyChanging.getTarget().addTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo);
						}
					};
				}
			
			} else if(index==2) {
				return new Response("Сохранить/Загрузить", "Сохранить/Загрузить пресеты тату.", CosmeticsDialogue.TATTOO_SAVE_LOAD) {
					@Override
					public void effects() {
						CosmeticsDialogue.initTattooSaveLoadDialogue(CHOOSE_ADVANCED_APPEARANCE_TATTOOS_ADD);
					}
				};
			
			} else if(index==0) {
				return new Response("Назад", "Решите не делать эту татуировку и вернитесь на главный экран выбора.", CHOOSE_ADVANCED_APPEARANCE_TATTOOS);
			}
			
			return null;
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_COSMETICS = new DialogueNode("Косметика", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>На все эти параметры можно повлиять позже в игре.</i>"
					+ "</div>"
							
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "Румяна", "Румяна (также называемые тенями) используются для окрашивания щек, чтобы придать им более молодой вид и подчеркнуть скулы.", true, false)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "Губная помада", "Губная помада используется для придания цвета, текстуры и защиты губ.", true, false)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "Подводка для глаз", "Подводка для глаз наносится по контуру глаз, помогая определить форму или подчеркнуть различные черты.", true, false)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "Тени для век", "Тени для век используются для того, чтобы выделить глаза или сделать их более привлекательными.", true, false)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Лак для ногтей", "Лак для ногтей используется для окрашивания и защиты ногтей на ваших [pc.hands].", true, false)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Лак для ногтей на ногах", "Лак для ногтей используется для окрашивания и защиты ногтей на ваших [pc.feet].", true, false);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свой выбор и вернитесь к настройкам.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_BODY_HAIR = new DialogueNode("Волосы на теле", "", true) {
		
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
												+ "<i>На все эти параметры можно повлиять позже в игре.</i>"
											+ "</div>");
			
			if(Main.game.isPubicHairEnabled() || Main.game.isFacialHairEnabled() || Main.game.isBodyHairEnabled()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						false, Race.NONE, Main.game.getPlayer().getBodyHairCoveringType(), "Волосы на теле", "Это волосы, которые покрывают все области, кроме головы.", false, false, false));
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"Волосы на теле", "Это волосы, которые покрывают все области, кроме головы.", "Все опции дополнительных волос на теле отключены. Вы не увидите никаких дополнительных волос."));
				
				return UtilText.nodeContentSB.toString();
			}
			
			if(Main.game.isFacialHairEnabled()) {
				if (Main.game.isFemaleFacialHairEnabled()) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivFacialHair(false, "Волосы на лице", "Волосы на теле, расположенные на лице."));
				} else {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivFacialHair(false, "Волосы на лице", "Волосы на теле, которые находятся на лице. Женские персонажи не могут отращивать волосы на лице."));
				}
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"Волосы на лице", "Волосы на теле, которые находятся на лице. Женские персонажи не могут отращивать волосы на лице.", "В настоящее время волосы на лице отключены в настройках. Пока волосы на лице отключены, вы не увидите никакого содержимого."));
			}
			
			if(Main.game.isPubicHairEnabled()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPubicHair(false, "Лобковые волосы", "Волосы на теле в области гениталий; расположены на половых органах и вокруг них, а также в промежности."));
				
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"Лобковые волосы", "Волосы на теле в области гениталий; расположены на половых органах и вокруг них, а также в промежности..", "Лобковые волосы в настоящее время отключены в настройках. Пока лобковые волосы отключены, вы не увидите никакого содержимого."));
			}
			
			if(Main.game.isBodyHairEnabled()) {
				UtilText.nodeContentSB.append(
						CharacterModificationUtils.getKatesDivUnderarmHair(false, "Волосы подмышками", "Волосы на теле, расположенные в подмышках."));
				
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"Волосы подмышками", "Волосы в подмышечных впадинах.", "Волосы подмышками в настоящее время отключены в настройках. Пока эта функция отключена, вы не увидите никакого содержимого подмышечных волос."));
			}
			
			if(Main.game.isAssHairEnabled()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivAssHair(false, "Волосы на заднице", "Волосы на теле, найденные вокруг вашей задницы."));
				
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"Волосы на заднице", "Волосы на теле, найденные вокруг вашей задницы.", "Волосы на заднице в настоящее время отключены в настройках. Вы не увидите никакого содержимого задницы, пока оно отключено."));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Подтвердите свой выбор и вернитесь к настройкам.", CHOOSE_ADVANCED_APPEARANCE);
				
			} else {
				return null;
			}
		}
	};
	
	public static String getCheckingClothingDescription() {
		StringBuilder sb = new StringBuilder();

		File dir = new File("res/");
		if(!dir.exists()) {
			sb.append("<p style='text-align:center;'>"
						+ "[style.italicsBad(Игра не может прочитать папку 'res', и поэтому жизненно важные предметы одежды будут отсутствовать! Пожалуйста, обратитесь к разделу 'MISSING FOLDERS' в README.txt, прежде чем продолжить!)]"
					+ "</p>");
		}
		
		sb.append("<div class='container-full-width' style='background:transparent;'>"
					+ "<p>"
						+ "На главной сцене, кажется, нет никаких признаков деятельности, поэтому, получив еще несколько минут, вы решаете немного приукрасить свою одежду."
						+ " В конце концов, это важный вечер для Лили, и вы хотите, чтобы она видела, что вы приложили некоторые усилия к своему внешнему виду."
					+ "</p>"
					+ "<p>"
						+ "Поворачиваясь то в одну, то в другую сторону, чтобы получше рассмотреть себя в зеркале, вы начинаете замечать, как "+(Main.game.getPlayer().isFeminine()?"горячо":"симпотично")+" вы выглядите сегодня вечером..."
					+ "</p>"
					+ "<p>"
						+ "[pc.thought(Почему я вдруг так возбужден?)]"
					+ "</p>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>Выберите, что вы решили надеть в музей.</i><br/>"
						+ "<i>Прежде чем продолжить, вам нужно будет надеть какую-нибудь обувь, а также одежду, скрывающую гениталии и грудь.</i>"
					+ "</div>"
				+ "</div>");
		
		return sb.toString();
	}
	
	public static void moveNPCIntoPlayerTile() {
		if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC || (Main.game.getPlayer().getSexualOrientation()==SexualOrientation.AMBIPHILIC && Main.game.getPlayer().hasVagina())) {
			Main.game.getNpc(PrologueMale.class).setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
			
		} else {
			Main.game.getNpc(PrologueFemale.class).setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
		}
	}
	
	public static void moveNPCOutOfPlayerTile() {
		Main.game.getNpc(PrologueMale.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
		Main.game.getNpc(PrologueFemale.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
	}
	
	public static boolean femalePrologueNPC() {
		return Main.game.getPlayer().getSexualOrientation()==SexualOrientation.GYNEPHILIC || (Main.game.getPlayer().getSexualOrientation()==SexualOrientation.AMBIPHILIC && Main.game.getPlayer().hasPenis());
	}
	
	public static final DialogueNode CHOOSE_BACKGROUND = new DialogueNode("В музее", "-", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "Удовлетворенные своим внешним видом, вы отворачиваетесь от зеркала и начинаете идти к главной сцене."
						+ " С каждым шагом вы необъяснимо заводитесь все больше и больше, и к тому моменту, когда вы преодолели половину расстояния до шумной толпы посетителей,"
							+(Main.game.getPlayer().hasPenis()
									?" вы изо всех сил пытаетесь удержать себя от эрекции."
									:" Вы можете почувствовать, как ваша киска мокнет от возбуждения.")
					+ "</p>"
					+ "<p>"
						+ "Пригнувшись за ближайшей колонной, вы трясете головой, пытаясь отогнать грязные мысли, которые начинают просачиваться в ваш разум."
						+ " Когда вы прислоняетесь спиной к холодному камню и делаете глубокий вдох, голос внезапно прерывает ваши мысли,");
			
			if(!femalePrologueNPC()) {
				UtilText.nodeContentSB.append(" [prologueMale.speech(Тоже хотите отдохнуть от толпы?)]"
						+ "</p>"
						+ "<p>"
							+ "Обернувшись, вы видите высокого красивого мужчину, который, должно быть, всего на пару лет старше вас, одаривающего вас самой очаровательной улыбкой, которую вы когда-либо видели."
							+ " Прежде чем вы осознаете, что делаете, ваши глаза путешествуют вверх и вниз по всем [unit.size] частям его мужественного, мускулистого тела, и вам удается удержаться от отчаянного стона."
						+ "</p>"
						+ "<p>"
							+ "[pc.thought(Сфокусируйся, [pc.name], Фокусируйся!)] вы стараетесь вести себя как можно непринужденнее, улыбаясь незнакомцу, стоящему перед вами."
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(На самом деле,)] говорите вы, [pc.speech(Я только что приехал. Я думал, что опоздаю, но, похоже, еще ничего не началось.)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueMale.speech(А, вы, должно быть, просто пропустили объявление,)] отвечает он, [prologueMale.speech(вступительная речь задерживается на полчаса."
								+ " Я пробовал болтаться в этой толпе, но я не историк, и большинство разговоров довольно сухие...)]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Хаха,)]"
							+ " вы смеетесь, отчаянно пытаясь не представлять, как он выглядит обнаженным,"
							+ " [pc.speech(Я <i>очень</i> хорошо понимаю что ты имешь в виду. Моя тетя - дама, произносящая вступительную речь, и каждый раз, когда я встречаю ее друзей из музея, я никогда не могу уследить за их разговором."
									+ " Ну, кроме Артура. Он ближе к нашему возрасту, и с ним очень легко и весело общаться.)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueMale.speech(Ха! Вы знаете Артура? Я здесь по его приглашению. Мы с ним давно знакомы,)]"
							+ " весело отвечает мужчина, его улыбка заставляет ваше сердце учащенно биться."
							+ " [prologueMale.speech(Я [prologueMale.name] кстати, рад познакомиться с вами "+(Main.game.getPlayer().isFeminine()?"М-с. ...?":"М-р. ...?")+")]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Аналогично,)] вы отвечаете, пожимая предложенную им руку и стараясь не думать о том, насколько сильной и доминирующей является его хватка. [pc.speech(Я [pc.Name].)]"
						+ "</p>"
						+ "<p>"
							+ "Вы и [prologueMale.name] продолжаете разговаривать друг с другом, ожидая начала презентации."
							+ " Вскоре тема разговора переходит на работу, и вы узнаете, что он - пилот авиакомпании, базирующейся в аэропорту на окраине города."
							+ " Затем разговор переходит на то, чем вы занимаетесь, и в итоге вы еще некоторое время говорите об этом..."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(" [prologueFemale.speech(Тоже хотите отдохнуть от толпы?)]"
						+ "</p>"
						+ "<p>"
							+ "Обернувшись, вы видите красивую женщину примерно того же возраста, что и вы, которая дарит вам самую потрясающую улыбку, которую вы когда-либо видели."
							+ " Прежде чем вы осознаете, что делаете, ваши глаза путешествуют вверх и вниз по каждому [unit.size] изгибу ее женского тела, и вы только успеваете сдерживать себя, чтобы не издать голодный стон."
						+ "</p>"
						+ "<p>"
							+ "[pc.thought(Сфокусируйся [pc.name], Сфокусируйся!)] вы стараетесь вести себя как можно непринужденнее, улыбаясь незнакомцу, стоящему перед вами."
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(На самом деле,)] говорите вы, [pc.speech(Я только что приехал(а). Я думал(а), что опоздаю, но, похоже, еще ничего не началось.)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueFemale.speech(А, вы, должно быть, просто пропустили объявление,)] отвечает она, [prologueFemale.speech(вступительная речь задерживается на полчаса."
								+ " Я пробовала потусоваться в той толпе, но я не историк, и большинство разговоров довольно сухие...)]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Хаха,)]"
							+ " вы смеетесь, отчаянно пытаясь не представлять, как она выглядит обнаженной,"
							+ " [pc.speech(Я <i>очень</i> хорошо понимаю что вы имеете в виду. Моя тетя выступает на открытии, и каждый раз, когда я встречаю ее друзей из музея, я не могу уследить за их разговором."
									+ " Ну, кроме Артура. Он ближе к нашему возрасту, и с ним очень легко и весело общаться.)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueFemale.speech(О! Вы знаете Артура? Я здесь по его приглашению, вообще-то. Мы с ним давно знакомы,)]"
							+ " весело отвечает женщина, ее улыбка заставляет ваше сердце учащенно биться."
							+ " [prologueFemale.speech(Я [prologueFemale.name] к слову, рада познакомиться с вами "+(Main.game.getPlayer().isFeminine()?"М-с. ...?":"М-р. ...?")+")]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(Аналогично,)] вы отвечаете, пожимая предложенную ей руку и стараясь не думать о том, какая мягкая и нежная у нее кожа. [pc.speech(Я [pc.Name].)]"
						+ "</p>"
						+ "<p>"
							+ "Вы и [prologueFemale.name] продолжайте общаться друг с другом в ожидании начала презентации."
							+ " Вскоре тема разговора переходит на работу, и вы узнаете, что она готовится стать врачом и учится здесь, в городском университете."
							+ " Затем разговор переходит на то, чем вы занимаетесь, и в итоге вы еще некоторое время говорите об этом..."
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new ResponseEffectsOnly("Назад", "Вернитесь к выбору одежды.") {
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_BACKGROUND;
					}
					@Override
					public void effects() {
						moveNPCOutOfPlayerTile();
						InventoryDialogue.setBuyback(false);
						InventoryDialogue.setInventoryNPC(null);
						InventoryDialogue.setNPCInventoryInteraction(InventoryInteraction.CHARACTER_CREATION);
						Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
					}
				};
				
			} else if (index == 1) {
				return new Response("Выбрать Работу", "Перейдите к экрану выбора работы.", BACKGROUND_SELECTION_MENU) {
					@Override
					public int getSecondsPassed() {
						return TIME_TO_JOB;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode BACKGROUND_SELECTION_MENU = new DialogueNode("В музее", "-", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append("<div class='container-full-width'>"
									+ "<h6 style='text-align:center'>Выбор работы</h6>"
									+ "<p style='text-align:center'>Нажмите на значок рядом с желаемой работой, а затем нажмите 'Продолжить'.</p>"
								+ "</div>");

			UtilText.nodeContentSB.append("<div class='container-full-width'>");
			for(Occupation history : Occupation.getAvailableHistories(Main.game.getPlayer())) {
				UtilText.nodeContentSB.append(
						"<div class='container-full-width'>"
							+"<div class='container-full-width' style='margin:0;padding:0;'>"
								+ "<h6 style='color:"+history.getAssociatedPerk().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(history.getName(Main.game.getPlayer()))+"</h6>"
							+ "</div>"
							+"<div class='container-full-width' style='margin:0 8px; width: calc(10% - 16px);'>"
								+ "<div id='OCCUPATION_" + history + "' class='fetish-icon full"
									+ (Main.game.getPlayer().getHistory()==history
										? " owned' style='border:2px solid " + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"
										: " unlocked' style='border:2px solid " + PresetColour.TEXT_GREY.toWebHexString() + ";" + "'>")
									+ "<div class='fetish-icon-content'>"+history.getAssociatedPerk().getSVGString(Main.game.getPlayer())+"</div>"
								+ "</div>"
							+ "</div>"
							+"<div class='container-full-width' style='margin:0 8px; width: calc(90% - 16px);'>"
								+ "<p>"
									+ history.getDescription(Main.game.getPlayer())
								+ "</p>"
							+ "</div>"
						+ "</div>");
			}
			
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Возврат к предыдущему экрану.", CHOOSE_BACKGROUND) {
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_JOB;
					}
				};
				
			} else if (index == 1) {
				if(Main.game.getPlayer().getHistory().getAssociatedPerk()==null) {
					return new Response("Продолжить", "Прежде чем продолжить, необходимо выбрать работу!", null);
				} else {
					return new Response("Продолжить", femalePrologueNPC()?"Сказать [prologueFemale.name] чем вы зарабатываете на жизнь.":"Сказать [prologueMale.name] чем вы зарабатываете на жизнь.", CHOOSE_SEX_EXPERIENCE) {
						@Override
						public int getSecondsPassed() {
							return TIME_TO_SEX_EXPERIENCE;
						}
						@Override
						public void effects() {
							Main.game.getPlayer().getVirginityLossMap().replaceAll((k, v) ->
								(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.GYNEPHILIC
									|| (Main.game.getPlayer().getSexualOrientation()==SexualOrientation.AMBIPHILIC && !Main.game.getPlayer().isFeminine()))
									?new SimpleEntry<>("", "ваша девушка")
									:new SimpleEntry<>("", "ваш парень"));
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNode CHOOSE_SEX_EXPERIENCE = new DialogueNode("Начать", "", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>");
			switch(Main.game.getPlayer().getHistory()) {
				case ATHLETE:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я профессиональный атлет,)]"
							+ " вы объясняете,"
							+ " [pc.speech(и я провожу большую часть своего времени, тренируясь и посещая соревнования.)]");
					break;
				case BUTLER:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я работаю дворецким в одной очень влиятельной семье в этом городе,)]"
							+ " вы объясняете,"
							+ " [pc.speech(но я взял выходной, чтобы присутствовать на презентации Лили.)]");
					break;
				case CHEF:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я шеф-повар в ресторане, расположенном за углом отсюда,)]"
							+ " вы объясняете,"
							+ " [pc.speech(но я взял(а) выходной, чтобы присутствовать на презентации Лили.)]");
					break;
				case CONSTRUCTION_WORKER:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я строитель,)]"
							+ " вы объясняете,"
							+ " [pc.speech(и сейчас я руковожу крупным проектом на окраине города.)]");
					break;
				case MAID:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я работаю старшей горничной в одной очень влиятельной семье в этом городе,)]"
							+ " вы объясняете,"
							+ " [pc.speech(но я взяла выходной, чтобы присутствовать на презентации Лили.)]");
					break;
				case MUSICIAN:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я играю в городском оркестре,)]"
							+ " вы объясняете,"
							+ " [pc.speech(я также занимаюсь частным преподаванием музыки.)]");
					break;
				case OFFICE_WORKER:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я работаю в одном из корпоративных офисов в центре города,)]"
							+ " вы объясняете,"
							+ " [pc.speech(в основном занимаюсь административной и бумажной работой.)]");
					break;
				case SOLDIER:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я служу в армии,)]"
							+ " вы объясняете,"
							+ " [pc.speech(я в отпуске до конца недели, а потом вернусь в казарму.)]");
					break;
				case STUDENT:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я учусь в городском университете,)]"
							+ " вы объясняете,"
							+ " [pc.speech(хотя я еще не решил(а), что выбрать в качестве специализации.)]");
					break;
				case TEACHER:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я работаю учителем в местной средней школе,)]"
							+ " вы объясняете,"
							+ " [pc.speech(Но поскольку сейчас середина каникул, на этой неделе я могу не напрягаться.)]");
					break;
				case UNEMPLOYED:
					UtilText.nodeContentSB.append(
							"[pc.speech(Сейчас я нахожусь в перерыве от работы,)]"
							+ " вы объясняете,"
							+ " [pc.speech(Вообще-то я подумываю о том, чтобы поступить на работу в музей.)]");
					break;
				case WRITER:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я профессиональный автор,)]" // I write erotic novels...
							+ " вы объясняете,"
							+ " [pc.speech(и сейчас я жду ответа от своего издателя по поводу моего последнего романа.)]");
					break;
				case ARISTOCRAT:
					UtilText.nodeContentSB.append(
							"[pc.speech(Мне не нужно беспокоиться о работе,)]"
							+ " вы объясняете,"
							+ " [pc.speech(Мое семейное поместье обеспечивает все необходимые мне доходы, поэтому я провожу время, путешествуя и наслаждаясь жизнью.)]");
					break;
				case TOURIST:
					UtilText.nodeContentSB.append(
							"[pc.speech(Я здесь в отпуске,)]"
							+ " вы объясняете,"
							+ " [pc.speech(Пока я нахожусь здесь, в Великобритании, я не хочу думать о работе.)]");
					break;
				default:
					break;
			}
			UtilText.nodeContentSB.append("</p>");
			
			if(femalePrologueNPC()) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "По мере того как вы двое продолжаете разговаривать, сначала о работе, а затем на более общие темы, вы все больше и больше заводитесь."
							+ " Более того, вы стали замечать, что щеки [prologueFemale.namePos] начинают краснеть, а она продолжает жадно разглядывать ваше тело, когда думает, что вы не смотрите."
						+ "</p>"
						+ "<p>"
							+ "В качестве последнего доказательства того, что она заводится так же, как и вы, она начинает открыто говорить о своей сексуальной жизни."
							+ " Поначалу вас немного удивляет ее открытость, но чем больше она говорит, тем комфортнее вам становится говорить о сексе с этим относительно незнакомым человеком."
						+ "</p>"
						+ "<p>"
							+ "И вот, проговорив с [prologueFemale.name] не более десяти минут, вы рассказываете ей все подробности своего сексуального опыта..."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "По мере того как вы двое продолжаете разговаривать, сначала о работе, а затем на более общие темы, вы все больше и больше заводитесь."
							+ " Более того, вы начинаете замечать, что щеки [prologueMale.namePos] начинают краснеть, а он все время бросает голодные взгляды на ваше тело, когда думает, что вы не смотрите."
						+ "</p>"
						+ "<p>"
							+ "В качестве последнего доказательства того, что он заводится не меньше вас, он начнет открыто говорить о своей сексуальной жизни."
							+ " Поначалу вас немного удивляет его открытость, но чем больше он говорит, тем комфортнее вам становится говорить о сексе с этим относительно незнакомым человеком."
						+ "</p>"
						+ "<p>"
							+ "И вот, проговорив с [prologueMale.name] не более десяти минут, вы рассказываете ему все подробности своего сексуального опыта..."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(
						"<div class='container-full-width' style='text-align:center;'>"
							+ "<i>При увеличении сексуального опыта вы получите больше испорченности. (Свою испорченность, как и другие атрибуты, вы можете увидеть на панели персонажа в левой части экрана).)"
							+ "<br/>"
							+ "Selecting '<span style='color:"+FetishDesire.FOUR_LOVE.getColour().toWebHexString()+";'>"+FetishDesire.FOUR_LOVE.getName()+"</span>'"
								+ " жажда фетиша приведет к тому, что ваш персонаж начнет игру с этим фетишем, в то время как остальные четыре желания просто определяют отношение персонажа к этому фетишу.</i>"
						+ "</div>"
						+CharacterModificationUtils.getSexualExperienceDiv()
						+CharacterModificationUtils.getFetishChoiceDiv());
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Продолжить", "Когда вы будете довольны своим сексуальным опытом, переходите к заключительной части создания персонажа.", FINAL_CHECK) {
					@Override
					public int getSecondsPassed() {
						return TIME_TO_FINAL_CHECK;
					}
					@Override
					public void effects() {
						if(!Main.game.getPlayer().hasPenis()) {
							for(SexAreaOrifice ot : SexAreaOrifice.values()) {
								SexType st = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, ot);
								Main.game.getPlayer().resetVirginityLoss(st);
								st = new SexType(SexParticipantType.SELF, SexAreaPenetration.PENIS, ot);
								Main.game.getPlayer().resetVirginityLoss(st);
							}
							Main.game.getPlayer().setPenisVirgin(true);
							
						}
						if(!Main.game.getPlayer().hasVagina()) {
							for(SexAreaPenetration pt : SexAreaPenetration.values()) {
								SexType st = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pt);
								Main.game.getPlayer().resetVirginityLoss(st);
								st = new SexType(SexParticipantType.SELF, SexAreaOrifice.VAGINA, pt);
								Main.game.getPlayer().resetVirginityLoss(st);
							}
							Main.game.getPlayer().setVaginaVirgin(true);
						}
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "Возврат к выбору фона.", BACKGROUND_SELECTION_MENU) {
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_SEX_EXPERIENCE;
					}
				};
				
			} else {
				return null;
			}
		}
	};

	private static void applyGameStart() {
		CharacterModificationUtils.resetImpossibeSexExperience();
		
		Main.getProperties().addRaceDiscovered(Subspecies.HUMAN);
		Main.game.getPlayer().setGenderIdentity(Main.game.getPlayer().getGender());
		
		Main.game.getNpc(Lilaya.class).setSkinCovering(new Covering(BodyCoveringType.HUMAN, Main.game.getPlayer().getCovering(BodyCoveringType.HUMAN).getPrimaryColour()), true);

		Main.game.getNpc(Lilaya.class).setBirthday(LocalDateTime.of(Main.game.getPlayer().getBirthday().getYear()-22+18, Main.game.getNpc(Lilaya.class).getBirthMonth(), Main.game.getNpc(Lilaya.class).getDayOfBirth(), 12, 0));
		
		Main.game.clearTextStartStringBuilder();
		Main.game.clearTextEndStringBuilder();

		Main.game.setWeatherInSeconds(Weather.MAGIC_STORM, 5*60*60);
		
		Main.game.getPlayerCell().resetInventory();

		Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_quest_clothing_keys"), false);
	}

	private static void applySkipPrologueStart(boolean imported) {
		Main.game.getPlayer().addCharacterEncountered(Main.game.getNpc(Lilaya.class));
		Main.game.getPlayer().addCharacterEncountered(Main.game.getNpc(Rose.class));
		
		Main.getProperties().addRaceDiscovered(Main.game.getNpc(Lilaya.class).getSubspecies());
		Main.getProperties().addRaceDiscovered(Main.game.getNpc(Rose.class).getSubspecies());
		
		Main.game.applyStartingDateChange();
		if(!imported) {
			Main.game.getPlayer().setAgeAppearanceDifference(-Game.TIME_SKIP_YEARS);
		}

		Main.game.getPlayer().addSpecialPerk(Perk.SPECIAL_PLAYER);
		
		moveNPCOutOfPlayerTile();
	}
	
	public static final DialogueNode FINAL_CHECK = new DialogueNode("Начать", "", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(
				"<div class='container-full-width' style='text-align:center;'>"
					+ "<i>Когда вы будете довольны своим внешним видом, нажмите кнопку "Начать игру", чтобы начать!<br/>"
					+ "[style.colourGood(На этом создание персонажа закончено, поэтому продолжайте только после того, как будете довольны своим выбором!)]</i>"
				+ "</div>"
				+ "<br/>"
				+ "<div class='container-full-width'>"
					+ "<h5 style='text-align:center;'>Окончательное обличие</h5>"
					+ Main.game.getPlayer().getBodyDescription()
				+ "</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Начать Игру", "Используйте этого персонажа и начните игру с самого начала, с попыток найти Артура в музее.", PrologueDialogue.INTRO){
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.MAIN));
						
						applyGameStart();
					}
				};
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Пропустить пролог", "Начните игру и пропустите пролог.<br/><br/><i style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Не рекомендуется для тех, кто играет впервые!</i>"){
					@Override
					public int getSecondsPassed() {
						return 60*60;
					}
					@Override
					public void effects() {
						
						Main.game.setRenderMap(true);
						
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.MAIN));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS));
						
						Main.game.getPlayer().incrementMoney(5000);

						DamageType damageType = DamageType.FIRE;
						switch(CharacterCreation.getStartingDemonstoneSpellSchool()) {
							case AIR:
								damageType = DamageType.POISON;
								break;
							case EARTH:
								damageType = DamageType.PHYSICAL;
								break;
							case ARCANE:
							case FIRE:
								damageType = DamageType.FIRE;
								break;
							case WATER:
								damageType = DamageType.ICE;
								break;
						}
						if(Main.game.getPlayer().getMainWeapon(0)==null) {
							Main.game.getPlayer().equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_crystal_rare", damageType));
						} else {
							Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_crystal_rare", damageType), false);
						}
						
						Spell startingSpell = Spell.FIREBALL;
						switch(getStartingTomeSpellSchool()) {
							case AIR:
								startingSpell = Spell.POISON_VAPOURS;
								break;
							case EARTH:
								startingSpell = Spell.SLAM;
								break;
							case FIRE:
							case ARCANE:
								startingSpell = Spell.FIREBALL;
								break;
							case WATER:
								startingSpell = Spell.ICE_SHARD;
								break;
						}
						AbstractItem spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(startingSpell));
						Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCell(PlaceType.LILAYA_HOME_ROOM_PLAYER).getInventory().addItem(spellBook);
						
						applyGameStart();
						applySkipPrologueStart(false);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "Возврат к выбору фона.", CHOOSE_SEX_EXPERIENCE){
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_FINAL_CHECK;
					}
					@Override
					public void effects() {
						// Remove attribute gain sentences in the start game screen:
						Main.game.clearTextEndStringBuilder();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	
	private static StringBuilder importSB;
	public static final DialogueNode IMPORT_CHOOSE = new DialogueNode("Импорт", "", true) {
		
		@Override
		public String getContent(){
			importSB = new StringBuilder();

			importSB.append("<p style='text-align:center;'>"
					+ "Эти персонажи считываются из папки 'data/characters'."
					+ " Если вы хотите импортировать персонаж из предыдущей версии, выполните следующие действия:<br/><br/>"
					+ "<b>1.</b> Откройте старую версию игры и экспортируйте своего старого персонажа (меню -> опции -> экспорт).<br/>"
					+ "<b>2.</b> Скопируйте экспортированный файл .xml (в папке <i>data/characters</i> старой версии).<br/>"
					+ "<b>3.</b> Вставьте его в папку <i>data/characters</i> этой версии.<br/>"
					+ "<b>4.</b> Нажмите 'Обновить', и ваш старый файл персонажа должен появиться в списке ниже!<br/><br/>"
//					+ "(If it doesn't work, please let me know as a comment on my blog, and I'll get it fixed ASAP!)"
					+ "</p>"
					+ "<p>"
					+ "<table align='center'>"
					+ "<tr>"
					+ "<th></th>"
					+ "<th>Имя</th>"
					+ "<th></th>"
					+ "</tr>");
			
			int i=1;
			for(File f : Main.getCharactersForImport()){
				importSB.append(getImportRow(i, f.getName()));
				i++;
			}

			importSB.append("</table>"
					+ "</p>"
					+ "<p id='hiddenPField' style='display:none;'></p>");

			return importSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Обновить", "Обновить эту страницу.", IMPORT_CHOOSE);
				
			} else if (index == 0) {
				return new Response("Назад", "Вернутся в главное меню", OptionsDialogue.MENU);
				
			} else {
				return null;
			}
		}
	};
	private static String getImportRow(int i, String name) {
		String baseName = Util.getFileName(name);
		String identifier = Util.getFileIdentifier(name);
		
		return "<tr>"
				+ "<td>"
					+ i+"."
				+ "</td>"
				+ "<td style='min-width:200px;'>"
					+ baseName
				+ "</td>"
				+ "<td>"
					+ "<div class='saveLoadButton' id='IMPORT_CHARACTER_" + identifier + "' style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Загрузить</div>"
				+ "</td>"
				+ "</tr>";
	}

	private static boolean resetImportedCharacter = false;

	public static final DialogueNode START_GAME_WITH_IMPORT = new DialogueNode("Начать игру", "", true) {
		
		@Override
		public String getLabel() {
			return "Импортированные персонажи";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "<b>TODO:</b> Я включу возможность полного создания персонажа с импортированными персонажами в какой-то момент!"
					+ "</p>"
					+ "<br/>"
					+"<details>"
						+ "<summary class='quest-title' style='color:" + QuestType.MAIN.getColour().toWebHexString() + ";'>Импортировать лог</summary>"
						+ Main.game.getCharacterUtils().getCharacterImportLog()
					+ "</details>"
					+ "<div class='container-full-width'>"
						+ "<h5 style='text-align:center;'>Внешность</h5>"
						+ Main.game.getPlayer().getBodyDescription()
					+ "</div>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Начать", "Используйте этого персонажа и начните игру с самого начала.", INTRO_2_FROM_IMPORT){
					@Override
					public void effects() {
						if(resetImportedCharacter){
							resetPlayerCharacter();
						}
						Main.game.getPlayer().resetAllQuests();
						Main.game.getPlayer().getCharactersEncountered().clear();
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.MAIN));
						applyGameStart();
					}
				};
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("Пропустить пролог", "Начните игру и пропустите пролог.<br/><br/><i style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Не рекомендуется для тех, кто играет впервые!</i>"){
					@Override
					public void effects() {
						Main.game.setRenderMap(true);
						if(resetImportedCharacter){
							resetPlayerCharacter();
						}
						Main.game.getPlayer().incrementMoney(5000);

						Main.game.getPlayer().resetAllQuests();
						Main.game.getPlayer().getCharactersEncountered().clear();
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.MAIN));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS));

						DamageType damageType = DamageType.FIRE;
						switch(CharacterCreation.getStartingDemonstoneSpellSchool()) {
							case AIR:
								damageType = DamageType.POISON;
								break;
							case EARTH:
								damageType = DamageType.PHYSICAL;
								break;
							case ARCANE:
							case FIRE:
								damageType = DamageType.FIRE;
								break;
							case WATER:
								damageType = DamageType.ICE;
								break;
						}
						if(Main.game.getPlayer().getMainWeapon(0)==null) {
							Main.game.getPlayer().equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_crystal_rare", damageType));
						} else {
							Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_crystal_rare", damageType), false);
						}
						
						AbstractItem spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.FIREBALL));
						if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 1) {
							spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.SLAM));
						} else if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 2) {
							spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.POISON_VAPOURS));
						} else if(Main.game.getPlayer().getBirthMonth().getValue()  % 4 == 3) {
							spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.ICE_SHARD));
						}
						Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCell(PlaceType.LILAYA_HOME_ROOM_PLAYER).getInventory().addItem(spellBook);
						
						applyGameStart();
						applySkipPrologueStart(true);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					}
				};

			} else if (index == 5) {
				return new ResponseEffectsOnly(resetImportedCharacter
						?"Сброс персонажа: <span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Вкл</span>"
						:"Сброс персонажа: <span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Выкл</span>",
						"Сбросьте опыт и деньги до 0, очищает весь инвентарь, кроме экипированной одежды и оружия. " +
								"Заклинания и перки заклинаний также удалены."){
					@Override
					public void effects(){
						resetImportedCharacter = !resetImportedCharacter;
					}
				};


			}
			// Throws error when going back and then resuming
//			else if (index == 0) {
//				return new Response("Back", "Return to new game screen.", OptionsDialogue.MENU);
//			}
			else {
				return null;
			}
		}
	};

	private static void resetPlayerCharacter(){
		PlayerCharacter player = Main.game.getPlayer();
		player.clearNonEquippedInventory(true);
		player.setEssenceCount(0);
		player.incrementExperience(player.getExperienceNeededForNextLevel(player.getLevel()), false);
		player.setLevel(1);
		player.resetSpells();
		player.resetPerksMap(false);
	}

	public static final DialogueNode INTRO_2_FROM_IMPORT = new DialogueNode("В музее", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60*10;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_2");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return PrologueDialogue.INTRO_2.getResponse(responseTab, index);
		}
	};
	
}
