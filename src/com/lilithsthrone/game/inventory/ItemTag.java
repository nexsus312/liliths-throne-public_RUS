package com.lilithsthrone.game.inventory;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.2.1
 * @version 0.4.5.5
 * @author Innoxia
 */
public enum ItemTag {

	CHEAT_ITEM, // Cheat items are hidden in the debug spawner, and are also not added to the Encyclopedia.
	SILLY_MODE, // Silly mode items only appear in shopkeepers inventories when silly mode is on.
	
	REMOVE_FROM_DEBUG_SPAWNER,
	NOT_FOR_SALE,
	
	REINDEER_GIFT, // Can be found in the presents that the reindeer sell (who appear in Dominion during winter months).
	SOLD_BY_RALPH, // Will also be used for any future consumable and miscellaneous item vendors.
	SOLD_BY_NYAN, // Clothing (is added to all clothing vendors)
	SOLD_BY_MONICA, // Clothing (is added to Elis only)
	SOLD_BY_KATE, // Jewellery
	SOLD_BY_FINCH, // BDSM and sex-related stuff
	SOLD_BY_VICKY, // Weapons
	SOLD_BY_EISEK, // Produce

	// Items with these tags can randomly spawn in encounters in the commented area
	// Please note that due to legacy issues, clothing and weapons only use the 'DOMINION_ALLEYWAY_SPAWN' tag to determine whether or not it can randomly spawn in any area.
		// This will liekly be changed at a later date, so please use the appropriate area spawn tag for your clothing/weapon, even though it does nothing for now
	ALL_AREAS_SPAWN, // Every area in the game
	DOMINION_ALLEYWAY_SPAWN, // Dominion
	SUBMISSION_TUNNEL_SPAWN, // Submission (excluding Bat Caverns)
	BAT_CAVERNS_SPAWN, // Bat Caverns
	ELIS_ALLEYWAY_SPAWN, // Foloi Fields
	
	SPELL_BOOK,
	SPELL_SCROLL,
	ESSENCE,
	ATTRIBUTE_TF_ITEM,
	RACIAL_TF_ITEM,
	MISC_TF_ITEM, // Fetish or non-racial body part transformations
	BOOK,
	GIFT,

	// These three all remove 'thirst quenched', 'thirst quenched (junk food)', and 'thirst quenched (quality food)' and then apply their related status effect
	DRINK, // Drink items apply the 'thirst quenched' status effect upon use
	DRINK_POOR, // Basic drink items apply the 'thirst quenched (basic)' status effect upon use
	DRINK_QUALITY, // Quality drink items apply the 'thirst quenched (quality)' status effect upon use
	
	// These three all remove 'recently eaten', 'recently eaten (junk food)', and 'recently eaten (quality food)' and then apply their related status effect
	FOOD, // Food items apply the 'recently eaten' status effect upon use
	FOOD_POOR, // Junk food items apply the 'recently eaten (junk)' status effect upon use
	FOOD_QUALITY, // Quality food items apply the 'recently eaten (quality)' status effect upon use
	
	ALCOHOLIC, // For easy detection of alcoholic items in some scenes
	
	// To mark consumables as containing caffeine, with the number representing the equivalent alcoholic level to be applied to spider-morphs
	// Only one of these should be applied to an item
	CAFFEINATED_005(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 5% до [style.boldAlcohol(intoxication level)] для [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_010(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 10% до [style.boldAlcohol(intoxication level)] для [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_015(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 15% до [style.boldAlcohol(intoxication level)] для [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_020(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 20% до [style.boldAlcohol(intoxication level)] для [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_025(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 25% до [style.boldAlcohol(intoxication level)] для [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_030(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 30% до [style.boldAlcohol(intoxication level)] для [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_040(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 40% до [style.boldAlcohol(intoxication level)] для [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_050(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 50% до [style.boldAlcohol(intoxication level)] для [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_075(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 75% до [style.boldAlcohol(intoxication level)] для [style.boldSpider(spider-morphs)]"), false),
	CAFFEINATED_100(Util.newArrayListOfValues("[style.boldMinorBad(Adds)] 100% до [style.boldAlcohol(intoxication level)] для [style.boldSpider(spider-morphs)]"), false),
	
	CONTRABAND_LIGHT(// 'Restricted' items will not be bought by honest shopkeepers
			Util.newArrayListOfValues(
					"[style.colourDarkBlue(Ограничивающий)]",
					"[style.colourMinorBad(Честные торговцы не купят это)]"),
			false),
	CONTRABAND_MEDIUM(// 'Illegal' items will not be bought by honest shopkeepers and will be confiscated by Enforcers
			Util.newArrayListOfValues(
					"[style.colourDarkBlue(Нелегально)]",
					"[style.colourMinorBad(Честные торговцы не купят это)]",
					"[style.colourBad(Энфорсеры конфискуют это)]"),
			false),
	CONTRABAND_HEAVY(// 'Highly Illegal' items will not be bought by honest shopkeepers and Enforcers will arrest anyone possessing them
			Util.newArrayListOfValues(
					"[style.colourDarkBlue(Крайне Нелегально)]",
					"[style.colourMinorBad(Честные торговцы не купят это)]",
					"[style.colourTerrible(Энфорсеры арестуют обладателей этого)]"),
			false),
	
	//-------------- WEAPONS & CLOTHING --------------//
	
	// ----- Weapon-specific tags: ----- //
	WEAPON_FERAL_EQUIPPABLE( // Allows ferals to equip this weapon (as they cannot equip weapons by default)
			Util.newArrayListOfValues(
					"[style.colourFeral(Может быть экипирован животными)]"),
			false),
	
	WEAPON_BLADE, // Should be added to all weapons that use an arcane blade
	
	WEAPON_FIREARM, // Should be added to all weapons that should be considered to be a firearm
	
	WEAPON_UNARMED, // Should be added to all weapons that should use unarmed damage calculations instead of melee
	//   ------------------------------ //
	
	/** Excludes this clothing from being randomly chosen to equip on an NPC in automatic outfit generation.
	 *  Also excludes the clothing from randomly spawning as tile-exploration loot.
	 *  This only really affects common-rarity clothing, as all clothing of a rarity higher than common are typically only able to be added to characters directly. */
	NO_RANDOM_SPAWN,
	
	UNIQUE_NO_NPC_EQUIP, // Prevents the clothing/weapon from being equipped onto an NPC. Only works on items with a 'UNIQUE' rarity level

	UNLOCKS_ENCOUNTER( // Special tag which has no effect other than displaying text for clothing stickers. Used for the 'rental mommy' tshirt, so look there for an example.
			Util.newArrayListOfValues(
			"[style.colourBlueLight(Может вызывать встречи)]"),
			false),
	
	NIGHT_VISION_SELF( // Makes this clothing or weapon provide immunity to the darkness debuff for just the wearer while equipped
			Util.newArrayListOfValues(
					"[style.colourGood(Снимает эффект 'Тьма')]"),
			false),
	
	NIGHT_VISION_AREA( // Makes this clothing or weapon provide immunity to the darkness debuff for all characters in the area in which the wearer is located while equipped
			Util.newArrayListOfValues(
					"[style.colourExcellent(Снимает эффект 'Тьма' действует на всех вокруг)]"),
			false),
	
	REVEALS_CONCEALABLE_SLOT, // If a piece of clothing has this tag, it will always be visible, even if another item of clothing is concealing its slot. (Used for spreader bar.)

	TRANSPARENT( // This item of clothing does not conceal any areas. Used for chastity cages & condoms (so penis is still visible). Could also be used for sheer clothing material.
			Util.newArrayListOfValues(
					"[style.colourSex(Не скрывает части тела)]"),
			false),
	
	IGNORE_HAIR_RESTRICTION, // If a piece of clothing has this tag, it can always be equipped into the HAIR slot, even if the equipping character has no hair.
	
	DRESS, // For helping to generate clothing in CharacterUtils
	SWIMWEAR, // For helping to generate clothing in CharacterUtils

	PROVIDES_KEY( // The person who equips this clothing will get an unlock key, making the unsealing cost 0
			Util.newArrayListOfValues(
					"[style.colourGood(Предоставляет снаряжателю ключ)]"),
			false),
	
	UNENCHANTABLE(  // Prevents the player from enchanting this in the inventory menu.
			Util.newArrayListOfValues(
					"[style.colourBad(Невозможно зачаровать)]"),
			false),

	SPREADS_FEET( // Prevents double foot actions, like wrap-around footjobs
			Util.newArrayListOfValues(
					"[style.colourBad(Ограничивает секс действия)]"),
			false),

	MUFFLES_SPEECH( // Causes the wearer to not be able to talk. E.g. Ball gags
			Util.newArrayListOfValues(
					"[style.colourBad(Глушит речь)]"),
			false),

	BLOCKS_SIGHT( // Causes the wearer to suffer from 'blinded' status effect
			Util.newArrayListOfValues(
					"[style.colourBad(Блокирует зрение)]"),
			false),
	
	HINDERS_ARM_MOVEMENT( // Hinders the ability of the wearer to use their arms. E.g. Hand cuffs
			Util.newArrayListOfValues(
					"[style.colourBad(Мешает движению рук)]",
					"[style.colourTerrible(Блокирует полет рук-крыльев)]"),
			false),

	HINDERS_LEG_MOVEMENT(  // Hinders the ability of the wearer to run or use their legs properly. E.g. Spreader bar
			Util.newArrayListOfValues(
					"[style.colourBad(Мешает движению ног)]",
					"[style.colourTerrible(Блокирует побег из боя всеми путями кроме полета)]"),
			false),

	PREVENTS_COMBAT_ESCAPE(  // Prevents the character from escaping from combat
			Util.newArrayListOfValues(
					"[style.colourTerrible(Предотвращает побег из боя)]"),
			false),
	
	DISCARDED_WHEN_UNEQUIPPED( //  Makes the clothing be thrown away when unequipped. E.g. Condoms
			Util.newArrayListOfValues(
					"[style.colourMinorBad(После снятия будет выброшено)]"),
			false),

	ENABLE_SEX_EQUIP( // Allows this clothing to be equipped during sex. E.g. Condoms or strapons
			Util.newArrayListOfValues(
					"[style.colourSex(Разрешено одевать во время секса)]"),
			false),
	
	// Self-explanatory requirements in order to equip this clothing:
	REQUIRES_PENIS(
			Util.newArrayListOfValues(
					"[style.colourSex(Требует пенис)]"),
			false),
	REQUIRES_NO_PENIS(
			Util.newArrayListOfValues(
					"[style.colourSex(Требует отсутсвия пениса)]"),
			false),
	REQUIRES_VAGINA(
			Util.newArrayListOfValues(
					"[style.colourSex(Требует вагину)]"),
			false),
	REQUIRES_NO_VAGINA(
			Util.newArrayListOfValues(
					"[style.colourSex(Требует отсутсвия вагины)]"),
			false),
	REQUIRES_FUCKABLE_NIPPLES(
			Util.newArrayListOfValues(
					"[style.colourSex(Требует трахабельные соски)]"),
			false),
	
	// These 'FITS' tags are used to check for whether clothing is suitable for certain body parts. They should be pretty self-explanatory.
	// Mouths (mouth clothing fits all mouth types by default, which is why there's no need for 'FITS_MUZZLES' or 'FITS_BEAKS'):
	FITS_MUZZLES_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит только для намордников)]"),
			false),
	FITS_BEAKS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит только для клюва)]"),
			false),
	
	// legs:
	FITS_HOOFS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит только для копыт)]"),
			false),
	FITS_HOOFS(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит для копыт)]"),
			false),
	
	FITS_TALONS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит только для когтей)]"),
			false),
	FITS_TALONS(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит для когтей)]"),
			false),
	
	// Arms:
	FITS_FEATHERED_ARM_WINGS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Подходит только для пернатых рук-крыльев)]"),
			false),
	FITS_FEATHERED_ARM_WINGS(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Подходит для пернатых рук-крыльев)]"),
			false),
	FITS_LEATHERY_ARM_WINGS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Подходит только для кожанных рук-крыльев)]"),
			false),
	FITS_LEATHERY_ARM_WINGS(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Подходит для кожанных рук-крыльев)]"),
			false),
	FITS_ARM_WINGS_EXCLUSIVE(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Подходит только для рук-крыльев)]"),
			false),
	FITS_ARM_WINGS(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Подходит для рук-крыльев)]"),
			false),

	// Bodies:
	FITS_NON_BIPED_BODY_HUMANOID(
			Util.newArrayListOfValues(
					"[style.colourHuman(Подходит для гуманоидных частей тела не двуногих)]"),
			false),
	FITS_TAUR_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Подходит только для четвероногих)]"),
			false),
	FITS_LONG_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Подходит только для тел с длинным хвостом)]"),false), //lamia, eels
	FITS_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Подходит только для тел с хвостом)]"),false), //mermaids
	FITS_ARACHNID_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Подходит только для тел арахнидов)]"),false), //spiders and scorpions
	FITS_CEPHALOPOD_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Подходит только для тел головоногих)]"),false), //octopuses and squids
	FITS_AVIAN_BODY(
			Util.newArrayListOfValues(
					"[style.colourTfGeneric(Подходит только для птичьих тел)]"),false), //bird-taurs

	// Feral status:
	// These tags are non-exclusive, so clothing with them can additionally be equipped by non-ferals:
	FITS_FERAL_ALL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит всем животным телам)]"),false), //All feral bodies can equip clothing marked with this tag.
	FITS_FERAL_QUADRUPED_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит для четвероногих тел)]"),false), //Quadrupedal feral bodies can equip clothing marked with this tag
	FITS_FERAL_LONG_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит для тел с длинными хвостами)]"),false), //Long-tailed feral bodies can equip clothing marked with this tag
	FITS_FERAL_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит для тел с хвостами)]"),false), //Tailed feral bodies can equip clothing marked with this tag
	FITS_FERAL_ARACHNID_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит для тел арахнидов)]"),false), //Arachnid feral bodies can equip clothing marked with this tag
	FITS_FERAL_CEPHALOPOD_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит для тел головоногих)]"),false), //Cephalopod feral bodies can equip clothing marked with this tag
	FITS_FERAL_AVIAN_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит для птичьх тел)]"),false), //Avian feral bodies can equip clothing marked with this tag
	// These tags are exclusive, so clothing with them can ONLY be equipped by the corresponding body type (unless the clothing additionally has other permissive tags):
	ONLY_FITS_FERAL_ALL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит только для тел животных)]"),false),
	ONLY_FITS_FERAL_QUADRUPED_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит только для четвероногих тел)]"),false),
	ONLY_FITS_FERAL_LONG_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит только для тел с длинными хвостами)]"),false),
	ONLY_FITS_FERAL_TAIL_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит только для тел с хвостами)]"),false),
	ONLY_FITS_FERAL_ARACHNID_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит только для тел арахнидов)]"),false),
	ONLY_FITS_FERAL_CEPHALOPOD_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит только для тел головоногих)]"),false),
	ONLY_FITS_FERAL_AVIAN_BODY(
			Util.newArrayListOfValues(
					"[style.colourFeral(Подходит только для птичьх тел)]"),false),
	
	
	RIGID_MATERIAL( // The clothing is made out of a rigid material, and as such, groping actions cannot be performed on it. Used for chastity cages/belts.
			Util.newArrayListOfValues(
					"[style.colourTerrible(Блокирует действия лапанья)]"),
			false),
	
	PREVENTS_ERECTION_PHYSICAL( // Prevents the wearer from getting an erection during sex, by means of physically limiting the space into which the erection could take shape (i.e. chastity cages). As of 0.3.1, only affects descriptors.
			Util.newArrayListOfValues(
					"[style.colourTerrible(Предотвращает эрекцию)]"),
			false),
	PREVENTS_ERECTION_OTHER( // Prevents the wearer from getting an erection during sex, by means other than physical limitations. As of 0.3.1, only affects descriptors.
			Util.newArrayListOfValues(
					"[style.colourTerrible(Предотвращает эрекцию)]"),
			false),
	
	// Sex-related clothing:

	/**<b>IMPORTANT</b> This tag should only ever be given to clothing going into the PENIS InventorySlot, as otherwise it will throw errors.*/
	CONDOM(true), // Gives this clothing condom behaviour
	
	CHOKER_SNAP( // Snaps (into wearer's inventory) if throat stretches.
			Util.newArrayListOfValues(
					"[style.colourSex(Рвется если горло выпирает слишком сильно во время секса)]"),
			true),
	
	CHASTITY( // Tags the clothing as being some form of chastity device, meaning that it will apply the 'CHASTITY_1' status effect when equipped
			Util.newArrayListOfValues(
					"[style.colourTerrible(Приспособление для целомудрия)]"),
			true),
	
	// To detect whether creampies should leak out or not:
	
	PLUGS_ANUS( // Counts as being inserted into the wearer's anus. E.g. butt plugs or anal beads
			Util.newArrayListOfValues(
					"[style.colourSex(Затыкает анус (не пачкается от кримпаев))]"),
			true),
	SEALS_ANUS( // Counts as sealing, but not inserted into, the wearer's anus. E.g. Tape
			Util.newArrayListOfValues(
					"[style.colourSex(Закрывает анус (не пачкается от кримпаев))]"),
			true),
	
	PLUGS_VAGINA( // Counts as being inserted into the wearer's vagina. E.g. insertable dildo
			Util.newArrayListOfValues(
					"[style.colourSex(Затыкает киску (не пачкается от кримпаев))]"),
			true),
	SEALS_VAGINA( // Counts as sealing, but not inserted into, the wearer's vagina. E.g. Tape
			Util.newArrayListOfValues(
					"[style.colourSex(Закрывает киску (не пачкается от кримпаев))]"),
			true) {
		@Override
		public List<String> getClothingTooltipAdditions() {
			if(Main.game.isUrethraEnabled()) {
				return Util.newArrayListOfValues("[style.colourSex(Закрывает киску (включая уретру))]");
			} else {
				return Util.newArrayListOfValues("[style.colourSex(Закрывает киску)]");
			}
		}
	},
	
	PLUGS_NIPPLES( // Counts as being inserted into the wearer's nipples. E.g. insertable nipple-dildos
			Util.newArrayListOfValues(
					"[style.colourSex(Затыкает соски (не пачкается от кримпаев))]"),
			true),
	SEALS_NIPPLES( // Counts as sealing, but not inserted into, the wearer's nipples. E.g. Pasties
			Util.newArrayListOfValues(
					"[style.colourSex(Закрывает соски (не пачкается от кримпаев))]"),
			true),
	
	MILKING_EQUIPMENT(
			Util.newArrayListOfValues(
					"[style.colourMilk(Доильное оборудование (выкачивает кримпаи))]"),
			true),
	
	/** <b>This is automatically assigned to items, and should not be manually added to ItemTags!</b> */
	DILDO_SELF(
			Util.newArrayListOfValues(
					"[style.colourSub(Вставляемый)] [style.colourSex(Дилдо)]"),
			true),

	/** <b>This is automatically assigned to items, and should not be manually added to ItemTags!</b> */
	DILDO_OTHER(
			Util.newArrayListOfValues(
					"[style.colourDom(Носимый)] [style.colourSex(Дилдо)]"),
			true),

	/** <b>This is automatically assigned to items, and should not be manually added to ItemTags!</b> */
	ONAHOLE_SELF(
			Util.newArrayListOfValues(
					"[style.colourSex(Ебабельный мастурбатор)]"),
			true),

	/** <b>This is automatically assigned to items, and should not be manually added to ItemTags!</b> */
	ONAHOLE_OTHER(
			Util.newArrayListOfValues(
					"[style.colourSex(Носимый мастурбатор)]"),
			true),
	;

	private List<String> clothingTooltipAdditions;
	private boolean sexToy;

	/**
	 * @param clothingTooltipAdditions The descriptions that should be appended to clothing tooltips for when this ItemTag is present on clothing.
	 * @param sexToy If true, then this item tag marks the associating clothing as a sex toy, which, if worn by a dom, is unable to be removed by subs in sex.
	 */
	private ItemTag(List<String> clothingTooltipAdditions, boolean sexToy) {
		this.clothingTooltipAdditions = clothingTooltipAdditions;
		this.sexToy = sexToy;
	}
	/**
	 * @param sexToy If true, then this item tag marks the associating clothing as a sex toy, which, if worn by a dom, is unable to be removed by subs in sex.
	 */
	private ItemTag(boolean sexToy) {
		this.clothingTooltipAdditions = null;
		this.sexToy = sexToy;
	}
	
	private ItemTag() {
		this.clothingTooltipAdditions = null;
		this.sexToy = false;
	}

	public List<String> getClothingTooltipAdditions() {
		if(clothingTooltipAdditions==null) {
			return new ArrayList<>();
		}
		return clothingTooltipAdditions;
	}
	
	/**
	 * @return true if this tag makes the clothing to which it is applied a sex toy, meaning that NPCs will not remove it during sex unless it is blocking the part they wish to access.
	 */
	public boolean isSexToy() {
		return sexToy;
	}
	
}
