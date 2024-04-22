package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Anus;
import com.lilithsthrone.game.character.body.Ass;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.BreastCrotch;
import com.lilithsthrone.game.character.body.Clitoris;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Testicle;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.BodyPartClothingBlock;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.1
 * @version 0.4.0
 * @author Innoxia
 */
public enum LegConfiguration {
	
	/**
	 * This LegConfiguration is the standard for humans, demons, angels, and the vast majority of animal-morphs.
	 */
	BIPEDAL("двуногий",
			0,
			0,
			true,
			true,
			false,
			false,
			WingSize.THREE_LARGE,
			false,
			2,
			"Самый распространенный тип нижней части тела; ноги и пах находятся в таком же положении, как и у обычного человека. ",
			"Выше [npc.her] паха, занимая нижнию часть [npc.her] живота,",
			TFModifier.TF_MOD_LEG_CONFIG_BIPEDAL,
			"") {
		@Override
		public List<GenitalArrangement> getAvailableGenitalConfigurations() {
			return Util.newArrayListOfValues(
					GenitalArrangement.NORMAL,
					GenitalArrangement.CLOACA,
					GenitalArrangement.CLOACA_BEHIND);
		}
		@Override
		public List<Class<? extends BodyPartInterface>> getFeralParts() {
			return Util.newArrayListOfValues();
		}
		@Override
		public List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character) {
			return null; // Bipedal configuration doesn't block any slots by default.
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			character.setLegType(LegType.DEMON_COMMON);
		}
		@Override
		public boolean isTailLostOnInitialTF() {
			return false;
		}
	},
	
	/**
	 * This LegConfiguration is available for almost every mammalian race, with some notable exceptions being humans, demons, and angels.
	 */
	QUADRUPEDAL("четвероногий",
			-50,
			0,
			false,
			false,
			true,
			true,
			WingSize.FOUR_HUGE,
			true,
			4,
			"Ноги и пах заменяются на четыре ноги и животное тело, соответствующего животного-морфа, а гениталии смещаются в то же место, что и у его животного эквивалента."
				+ " Самый распространенный пример - `кентавр`, в котором ноги и пах персонажа заменены телом и гениталиями лошади.",
			"Внизу под пахом [npc.her] животного тела,",
			TFModifier.TF_MOD_LEG_CONFIG_TAUR,
			"statusEffects/race/raceBackgroundLegQuadrupedal") {
		@Override
		public List<Class<? extends BodyPartInterface>> getFeralParts() {
			return Util.newArrayListOfValues(Ass.class, Anus.class, BreastCrotch.class, Leg.class, Tail.class, Tentacle.class, Penis.class, Testicle.class, Vagina.class, Clitoris.class);
		}
		@Override
		public List<GenitalArrangement> getAvailableGenitalConfigurations() {
			return Util.newArrayListOfValues(
					GenitalArrangement.NORMAL,
					GenitalArrangement.CLOACA,
					GenitalArrangement.CLOACA_BEHIND);
		}
		@Override
		public List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character) {
			if(character.isFeral()) {
				return Util.newArrayListOfValues(
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.TORSO_OVER,
											InventorySlot.TORSO_UNDER,
											InventorySlot.CHEST,
											InventorySlot.STOMACH,
											InventorySlot.HAND,
											InventorySlot.HIPS,
											InventorySlot.LEG,
											InventorySlot.FOOT,
											InventorySlot.SOCK,
											InventorySlot.GROIN),
									character.getLegType().getRace(),
									"Поскольку у [npc.nameHasFull] животное тело [npc.a_legRace], в этот слот можно надеть только одежду, подходящую для четвероногих тауров или четвероногих животных.",
									Util.newArrayListOfValues(
											ItemTag.FITS_TAUR_BODY,
											ItemTag.FITS_FERAL_ALL_BODY,
											ItemTag.FITS_FERAL_QUADRUPED_BODY,
											ItemTag.ONLY_FITS_FERAL_ALL_BODY,
											ItemTag.ONLY_FITS_FERAL_QUADRUPED_BODY)),
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.WEAPON_MAIN_1,
											InventorySlot.WEAPON_MAIN_2,
											InventorySlot.WEAPON_MAIN_3,
											InventorySlot.WEAPON_OFFHAND_1,
											InventorySlot.WEAPON_OFFHAND_2,
											InventorySlot.WEAPON_OFFHAND_3),
									character.getLegType().getRace(),
									"Поскольку у [npc.nameHasFull] животное тело [npc.a_legRace], [npc.she] не может использовать обычное оружие!",
									Util.newArrayListOfValues(
											ItemTag.WEAPON_FERAL_EQUIPPABLE)));
				
			} else {
				return Util.newArrayListOfValues(
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.LEG,
											InventorySlot.GROIN),
									character.getLegType().getRace(),
									"Поскольку у [npc.nameHasFull] нижняя часть тела [npc.a_legRace], в этот слот можно надеть только подходящую для тауров одежду.",
									Util.newArrayListOfValues(
											ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
											ItemTag.FITS_TAUR_BODY)));
			}
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			this.setLegsToAvailableDemonLegs(character, LegType.DEMON_HORSE_HOOFED);
		}
		@Override
		public boolean isTailLostOnInitialTF() {
			return false;
		}
	},

	/**
	 * This LegConfiguration is available for snakes and eels.
	 */
	TAIL_LONG("змеехвост",
			0,
			0,
			true,
			true,
			false,
			false,
			WingSize.FOUR_HUGE,
			false,
			0,
			"Ноги и пах заменяются чрезвычайно длинным хвостом соответствующего животного-морфа, а гениталии смещаются в клоаку."
				+ " Самый распространенный пример - `ламия`, в которой ноги и пах персонажа заменяются телом и гениталиями змеи.",
			"Выше [npc.her] паха, занимая нижнюю область [npc.her] гуманоидного брюха,",
			TFModifier.TF_MOD_LEG_CONFIG_TAIL_LONG,
			"statusEffects/race/raceBackgroundLegTailLong") {
		@Override
		public List<Class<? extends BodyPartInterface>> getFeralParts() {
			return Util.newArrayListOfValues(Ass.class, Anus.class, Leg.class, Penis.class, Testicle.class, Vagina.class, Clitoris.class);
		}
		@Override
		public List<GenitalArrangement> getAvailableGenitalConfigurations() {
			return Util.newArrayListOfValues(
					GenitalArrangement.CLOACA,
					GenitalArrangement.CLOACA_BEHIND); // Shouldn't ever spawn by default, but give player the option
		}
		@Override
		public List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character) {
			if(character.isFeral()) {
				return Util.newArrayListOfValues(
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.TORSO_OVER,
											InventorySlot.TORSO_UNDER,
											InventorySlot.CHEST,
											InventorySlot.STOMACH,
											InventorySlot.HAND,
											InventorySlot.HIPS,
											InventorySlot.LEG,
											InventorySlot.FOOT,
											InventorySlot.SOCK,
											InventorySlot.GROIN),
									character.getLegType().getRace(),
									"Поскольку у [npc.nameHasFull] животное тело [npc.a_legRace], в этот слот можно надеть только одежду, подходящую для длиннохвостых или длиннохвостых животных.",
									Util.newArrayListOfValues(
											ItemTag.FITS_LONG_TAIL_BODY,
											ItemTag.FITS_FERAL_ALL_BODY,
											ItemTag.FITS_FERAL_LONG_TAIL_BODY,
											ItemTag.ONLY_FITS_FERAL_ALL_BODY,
											ItemTag.ONLY_FITS_FERAL_LONG_TAIL_BODY)),
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.WEAPON_MAIN_1,
											InventorySlot.WEAPON_MAIN_2,
											InventorySlot.WEAPON_MAIN_3,
											InventorySlot.WEAPON_OFFHAND_1,
											InventorySlot.WEAPON_OFFHAND_2,
											InventorySlot.WEAPON_OFFHAND_3),
									character.getLegType().getRace(),
									"Поскольку у [npc.nameHasFull] животное тело [npc.a_legRace], [npc.she] не может использовать обычное оружие!",
									Util.newArrayListOfValues(
											ItemTag.WEAPON_FERAL_EQUIPPABLE)));
				
			} else {
				return Util.newArrayListOfValues(
						new BodyPartClothingBlock(
								Util.newArrayListOfValues(
										InventorySlot.LEG,
										InventorySlot.GROIN),
								character.getLegType().getRace(),
								"Поскольку у [npc.nameHasFull] нижняя часть тела [npc.a_legRace], в этот слот можно надеть только одежду, подходящую для длиннохвостых.",
								Util.newArrayListOfValues(
										ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
										ItemTag.FITS_LONG_TAIL_BODY)));
			}
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			this.setLegsToAvailableDemonLegs(character, LegType.DEMON_SNAKE);
		}
		@Override
		public boolean isTailLostOnInitialTF() {
			return true;
		}
		@Override
		public boolean isAbleToGrowTail() {
			return false;
		}
		@Override
		public boolean isThighSexAvailable() {
			return false;
		}
		@Override
		public String getMovementVerbPresentFirstPersonSingular() {
			return "ползти";
		}
		@Override
		public String getMovementVerbPresentThirdPersonSingular() {
			return "ползает";
		}
		@Override
		public String getMovementVerbPresentParticiple() {
			return "ползание";
		}
		@Override
		public String getMovementVerbPastParticiple() {
			return "поползла";
		}
		@Override
		public String getIndividualMovementVerbPresentFirstPersonSingular() {
			return "скользить";
		}
		@Override
		public String getIndividualMovementVerbPresentThirdPersonSingular() {
			return "скользит";
		}
		@Override
		public String getIndividualMovementVerbPresentParticiple() {
			return "скользящий";
		}
		@Override
		public String getIndividualMovementVerbPastParticiple() {
			return "передвигался скользящим движением";
		}
	},

	/**
	 * This LegConfiguration is available for fish.
	 */
	TAIL("русалко-хвост",
			25,
			-95,
			true,
			true,
			false,
			false,
			WingSize.THREE_LARGE,
			false, 
			0,
			"Ноги и пах персонажа заменяются хвостом соответствующего животного-морфа, а гениталии смещаются в клоаку."
					+ " Самый распространенный пример - `русалка`, в которой ноги и пах персонажа заменяются телом и гениталиями рыбы.",
			"Выше [npc.her] пах, занимая нижнюю часть [npc.her] гуманоидного брюха,",
			TFModifier.TF_MOD_LEG_CONFIG_TAIL,
			"statusEffects/race/raceBackgroundLegTailShort") {
		@Override
		public List<Class<? extends BodyPartInterface>> getFeralParts() {
			return Util.newArrayListOfValues(Ass.class, Anus.class, Leg.class, Penis.class, Testicle.class, Vagina.class, Clitoris.class);
		}
		@Override
		public List<GenitalArrangement> getAvailableGenitalConfigurations() {
			return Util.newArrayListOfValues(
					GenitalArrangement.CLOACA,
					GenitalArrangement.CLOACA_BEHIND); // Shouldn't ever spawn by default, but give player the option
		}
		@Override
		public List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character) {
			if(character.isFeral()) { // Tail races will never be feral, but just in case...
				return Util.newArrayListOfValues(
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.TORSO_OVER,
											InventorySlot.TORSO_UNDER,
											InventorySlot.CHEST,
											InventorySlot.STOMACH,
											InventorySlot.HAND,
											InventorySlot.HIPS,
											InventorySlot.LEG,
											InventorySlot.FOOT,
											InventorySlot.SOCK,
											InventorySlot.GROIN),
									character.getLegType().getRace(),
									"Поскольку у [npc.nameHasFull] животное тело [npc.a_legRace], в этот слот можно надевать только одежду, подходящую для хвостатых или хвостатых животных.",
									Util.newArrayListOfValues(
											ItemTag.FITS_TAIL_BODY,
											ItemTag.FITS_FERAL_ALL_BODY,
											ItemTag.FITS_FERAL_TAIL_BODY,
											ItemTag.ONLY_FITS_FERAL_ALL_BODY,
											ItemTag.ONLY_FITS_FERAL_TAIL_BODY)),
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.WEAPON_MAIN_1,
											InventorySlot.WEAPON_MAIN_2,
											InventorySlot.WEAPON_MAIN_3,
											InventorySlot.WEAPON_OFFHAND_1,
											InventorySlot.WEAPON_OFFHAND_2,
											InventorySlot.WEAPON_OFFHAND_3),
									character.getLegType().getRace(),
									"Поскольку у [npc.nameHasFull] животное тело [npc.a_legRace], [npc.she] не может использовать обычное оружие!",
									Util.newArrayListOfValues(
											ItemTag.WEAPON_FERAL_EQUIPPABLE)));
				
			} else if(character.hasStatusEffect(StatusEffect.AQUATIC_TAIL_POSITIVE)) {
				return Util.newArrayListOfValues(
						new BodyPartClothingBlock(
								Util.newArrayListOfValues(
										InventorySlot.LEG,
										InventorySlot.GROIN),
								character.getLegType().getRace(),
								"Поскольку у [npc.nameHasFull] нижняя часть тела [npc.a_legRace], в этот слот можно надеть только одежду, подходящую для хвоста.",
								Util.newArrayListOfValues(
										ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
										ItemTag.FITS_TAIL_BODY)));
				
			} else {
				return null; // When in bipedal configuration, doesn't block any slots.
			}
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			this.setLegsToAvailableDemonLegs(character, LegType.DEMON_FISH);
		}
		@Override
		public boolean isTailLostOnInitialTF() {
			return true;
		}
		@Override
		public boolean isAbleToGrowTail() {
			return false;
		}
		@Override
		public boolean isThighSexAvailable() {
			return false;
		}
	},

	/**
	 * This LegConfiguration is available for spiders and scorpions.
	 */
	ARACHNID("арахнид",
			-25,
			100,
			false,
			true,
			true,
			true,
			WingSize.FOUR_HUGE,
			true,
			8,
			"Конфигурация, в которой ноги и пах персонажа заменяются восьминогим, животным телом соответствующего арахнида-морфа, а его гениталии смещаются туда же, куда и у его животного эквивалента."
					+ " Самый распространенный пример - `арахна`, в которой ноги и пах персонажа заменены телом и гениталиями паука.",
			"Занимая нижнюю часть [npc.her] гуманоидного брюха,",
			TFModifier.TF_MOD_LEG_CONFIG_ARACHNID,
			"statusEffects/race/raceBackgroundLegArachnid") {
		@Override
		public List<Class<? extends BodyPartInterface>> getFeralParts() {
			return Util.newArrayListOfValues(Ass.class, Anus.class, Leg.class, Penis.class, Testicle.class, Vagina.class, Clitoris.class);
		}
		@Override
		public List<FootStructure> getPermittedFootStructuresOverride() {
			return Util.newArrayListOfValues(FootStructure.ARACHNOID);
		}
		@Override
		public List<GenitalArrangement> getAvailableGenitalConfigurations() {
			return Util.newArrayListOfValues(
					GenitalArrangement.NORMAL);
		}
		@Override
		public List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character) {
			if(character.isFeral()) {
				return Util.newArrayListOfValues(
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.TORSO_OVER,
											InventorySlot.TORSO_UNDER,
											InventorySlot.CHEST,
											InventorySlot.STOMACH,
											InventorySlot.HAND,
											InventorySlot.HIPS,
											InventorySlot.LEG,
											InventorySlot.FOOT,
											InventorySlot.SOCK,
											InventorySlot.GROIN),
									character.getLegType().getRace(),
									"Поскольку у [npc.nameHasFull] животное тело [npc.a_legRace], В этот слот можно надевать только одежду, подходящую для арахнидов или арахнидо-животных.",
									Util.newArrayListOfValues(
											ItemTag.FITS_ARACHNID_BODY,
											ItemTag.FITS_FERAL_ALL_BODY,
											ItemTag.FITS_FERAL_ARACHNID_BODY,
											ItemTag.ONLY_FITS_FERAL_ALL_BODY,
											ItemTag.ONLY_FITS_FERAL_ARACHNID_BODY)),
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.WEAPON_MAIN_1,
											InventorySlot.WEAPON_MAIN_2,
											InventorySlot.WEAPON_MAIN_3,
											InventorySlot.WEAPON_OFFHAND_1,
											InventorySlot.WEAPON_OFFHAND_2,
											InventorySlot.WEAPON_OFFHAND_3),
									character.getLegType().getRace(),
									"Поскольку у [npc.nameHasFull] животное тело [npc.a_legRace], [npc.she] не может использовать обычное оружие!",
									Util.newArrayListOfValues(
											ItemTag.WEAPON_FERAL_EQUIPPABLE)));
				
			} else {
				return Util.newArrayListOfValues(
						new BodyPartClothingBlock(
								Util.newArrayListOfValues(
										InventorySlot.LEG,
										InventorySlot.GROIN,
		//								InventorySlot.ANKLE,
										InventorySlot.FOOT,
										InventorySlot.SOCK),
								character.getLegType().getRace(),
								"Поскольку у [npc.nameHasFull] нижняя часть тела [npc.a_legRace], в этот слот можно надеть только одежду, подходящую для арахнидов.",
								Util.newArrayListOfValues(
										ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
										ItemTag.FITS_ARACHNID_BODY)));
			}
		}
		@Override
		public boolean isGenitalsExposed(GameCharacter character) { // As genitals are beneath the arachnid body, they are not easily visible.
			return false;
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			this.setLegsToAvailableDemonLegs(character, LegType.DEMON_SPIDER);
		}
		@Override
		public boolean isTailLostOnInitialTF() {
			return true;
		}
		@Override
		public boolean isThighSexAvailable() {
			return false;
		}
	},

	/**
	 * This LegConfiguration is available for octopuses and squids.<br/>
	 * <br/>
	 * <i>Ф'нглуи мглв'нафх Ктулху Р'лиех Вгах'нагл фхтан;<br/>
	 * О ты, кто лежит мертвым, но вечно видит сны,<br/>
	 * Слушай, слуга Твой взывает к Тебе.<br/>
	 * Услышь меня, о могучий Ктулху!<br/>
	 * Услышь меня, Повелитель Грез! В башне Твоей в Р'лиех Они заточили тебя,<br/>
	 * но Дагон разорвет Твои проклятые оковы,<br/>
	 * и Царство Твое снова восстанет из праха. Жители Глубин знают Твое тайное Имя,<br/>
	 * Гидра знает, где пребываешь Ты;<br/>
	 * Открой Твой знак, дабы я мог узнать<br/>
	 * Твою волю здесь, на Земле.<br/>
	 * Когда смерть умрет, тогда наступит время Твое,<br/>
	 * И Ты больше не станешь спать;<br/>
	 * Надели меня властью успокаивать волны,<br/>
	 * Чтобы мог я услышать Твой Зов.<br/>
	 * Ф'нглуи мглв'нафх Ктулху Р'лиех Вгах'нагл фхтан</i>
	 */
	CEPHALOPOD("головоногий",
			50,
			-75,
			true,
			true,
			false,
			false,
			WingSize.THREE_LARGE,
			false,
			8,
			// I believe that "tentacled" is technically incorrect as a catch-all term for cephalopods, as octopuses have eight 'arms', while squids have eight arms plus two tentacles. Oh well.
			"Ноги и пах персонажа заменяются щупальцевым, животным телом соответствующего головоногого морфа, а гениталии смещаются туда же, куда и у его животного эквивалента."
					+ " Самый распространенный пример - `кракен`, в котором ноги и пах персонажа заменены телом и гениталиями кальмара.",
			"Выше [npc.her] паха, занимая нижнюю часть [npc.her] гуманоидного брюха,",
			TFModifier.TF_MOD_LEG_CONFIG_CEPHALOPOD,
			"statusEffects/race/raceBackgroundLegCephalopod") {
		@Override
		public List<Class<? extends BodyPartInterface>> getFeralParts() {
			return Util.newArrayListOfValues(Ass.class, Anus.class, Leg.class, Penis.class, Testicle.class, Vagina.class, Clitoris.class);
		}
		@Override
		public List<GenitalArrangement> getAvailableGenitalConfigurations() {
			return Util.newArrayListOfValues(
					GenitalArrangement.CLOACA);
		}
		@Override
		public List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character) {
			if(character.isFeral()) {
				return Util.newArrayListOfValues(
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.TORSO_OVER,
											InventorySlot.TORSO_UNDER,
											InventorySlot.CHEST,
											InventorySlot.STOMACH,
											InventorySlot.HAND,
											InventorySlot.HIPS,
											InventorySlot.LEG,
											InventorySlot.FOOT,
											InventorySlot.SOCK,
											InventorySlot.GROIN),
									character.getLegType().getRace(),
									"Поскольку у [npc.nameHasFull] животное тело [npc.a_legRace], в этот слот можно надевать только одежду, подходящую для головоногих или головоногих животных",
									Util.newArrayListOfValues(
											ItemTag.FITS_CEPHALOPOD_BODY,
											ItemTag.FITS_FERAL_ALL_BODY,
											ItemTag.FITS_FERAL_CEPHALOPOD_BODY,
											ItemTag.ONLY_FITS_FERAL_ALL_BODY,
											ItemTag.ONLY_FITS_FERAL_CEPHALOPOD_BODY)),
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.WEAPON_MAIN_1,
											InventorySlot.WEAPON_MAIN_2,
											InventorySlot.WEAPON_MAIN_3,
											InventorySlot.WEAPON_OFFHAND_1,
											InventorySlot.WEAPON_OFFHAND_2,
											InventorySlot.WEAPON_OFFHAND_3),
									character.getLegType().getRace(),
									"Поскольку у [npc.nameHasFull] животное тело [npc.a_legRace], [npc.she] не может использовать обычное оружие!",
									Util.newArrayListOfValues(
											ItemTag.WEAPON_FERAL_EQUIPPABLE)));
				
			} else {
				return Util.newArrayListOfValues(
						new BodyPartClothingBlock(
								Util.newArrayListOfValues(
										InventorySlot.LEG,
										InventorySlot.GROIN,
		//								InventorySlot.ANKLE,
										InventorySlot.FOOT,
										InventorySlot.SOCK),
								character.getLegType().getRace(),
								"Поскольку у [npc.nameHasFull] нижняя часть тела [npc.a_legRace], only cephalopod-suitable clothing can be worn in this slot.",
								Util.newArrayListOfValues(
										ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
										ItemTag.FITS_CEPHALOPOD_BODY)));
			}
		}
		
		@Override
		public boolean isGenitalsExposed(GameCharacter character) { // Genitals are under tentacles, so are not visible even when naked.
			return false;
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			this.setLegsToAvailableDemonLegs(character, LegType.DEMON_OCTOPUS);
		}
		@Override
		public boolean isTailLostOnInitialTF() {
			return true;
		}
		@Override
		public String getMovementVerbPresentFirstPersonSingular() {
			return "ползти";
		}
		@Override
		public String getMovementVerbPresentThirdPersonSingular() {
			return "ползает";
		}
		@Override
		public String getMovementVerbPresentParticiple() {
			return "ползание";
		}
		@Override
		public String getMovementVerbPastParticiple() {
			return "ползла";
		}
		@Override
		public String getIndividualMovementVerbPresentFirstPersonSingular() {
			return "скользить";
		}
		@Override
		public String getIndividualMovementVerbPresentThirdPersonSingular() {
			return "скользит";
		}
		@Override
		public String getIndividualMovementVerbPresentParticiple() {
			return "скольжение";
		}
		@Override
		public String getIndividualMovementVerbPastParticiple() {
			return "проскальзывает";
		}
	},
	

	/**
	 * This LegConfiguration is a 'tauric' configuration for bird races.
	 */
	AVIAN("птица",
			0,
			0,
			false,
			true,
			true,
			true,
			WingSize.THREE_LARGE,
			true,
			2,
			"Ноги и пах персонажа заменяются птичьим телом соответствующего животного-морфа, а его гениталии смещаются в клоаку, расположенную сзади."
					+ " Самый распространенный пример - `гарпия-моа`, в которой ноги и пах обычной гарпии заменяются телом и гениталиями дикой птицы.",
			"Выше [npc.her] паха, занимая нижнию часть [npc.her] гуманоидного брюха,",
			TFModifier.TF_MOD_LEG_CONFIG_AVIAN,
			"statusEffects/race/raceBackgroundLegAvian") {
		@Override
		public List<GenitalArrangement> getAvailableGenitalConfigurations() {
			return Util.newArrayListOfValues(
					GenitalArrangement.CLOACA_BEHIND);
		}
		@Override
		public List<Class<? extends BodyPartInterface>> getFeralParts() {
			return Util.newArrayListOfValues(Ass.class, Anus.class, BreastCrotch.class, Leg.class, Tail.class, Tentacle.class, Penis.class, Testicle.class, Vagina.class, Clitoris.class);
		}
		@Override
		public List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character) {
			if(character.isFeral()) {
				return Util.newArrayListOfValues(
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.TORSO_OVER,
											InventorySlot.TORSO_UNDER,
											InventorySlot.CHEST,
											InventorySlot.STOMACH,
											InventorySlot.HAND,
											InventorySlot.HIPS,
											InventorySlot.LEG,
											InventorySlot.FOOT,
											InventorySlot.SOCK,
											InventorySlot.GROIN),
									character.getLegType().getRace(),
									"Поскольку у [npc.nameHasFull] животное тело [npc.a_legRace], в этот слот можно надеть только одежду, подходящую для птиц или пернатых.",
									Util.newArrayListOfValues(
											ItemTag.FITS_CEPHALOPOD_BODY,
											ItemTag.FITS_FERAL_ALL_BODY,
											ItemTag.FITS_FERAL_CEPHALOPOD_BODY,
											ItemTag.ONLY_FITS_FERAL_ALL_BODY,
											ItemTag.ONLY_FITS_FERAL_CEPHALOPOD_BODY)),
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.WEAPON_MAIN_1,
											InventorySlot.WEAPON_MAIN_2,
											InventorySlot.WEAPON_MAIN_3,
											InventorySlot.WEAPON_OFFHAND_1,
											InventorySlot.WEAPON_OFFHAND_2,
											InventorySlot.WEAPON_OFFHAND_3),
									character.getLegType().getRace(),
									"Поскольку у [npc.nameHasFull] животное тело [npc.a_legRace], [npc.she] не может использовать обычное оружие!",
									Util.newArrayListOfValues(
											ItemTag.WEAPON_FERAL_EQUIPPABLE)));
				
			} else {
				return Util.newArrayListOfValues(
						new BodyPartClothingBlock(
								Util.newArrayListOfValues(
										InventorySlot.LEG,
										InventorySlot.GROIN),
								character.getLegType().getRace(),
								"Поскольку у [npc.nameHasFull] нижняя часть тела [npc.a_legRace], в этот слот можно надеть только одежду, подходящую для птиц.",
								Util.newArrayListOfValues(
										ItemTag.FITS_NON_BIPED_BODY_HUMANOID,
										ItemTag.FITS_AVIAN_BODY))
//						new BodyPartClothingBlock(
//								Util.newArrayListOfValues(
//										InventorySlot.FOOT,
//										InventorySlot.SOCK),
//								character.getLegType().getRace(),
//								"Due to the fact that [npc.nameHasFull] the lower body of [npc.a_legRace], only avian-suitable clothing can be worn in this slot.",
//								Util.newArrayListOfValues(
//										ItemTag.FITS_TALONS_EXCLUSIVE,
//										ItemTag.FITS_TALONS))
						);
			}
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			this.setLegsToAvailableDemonLegs(character, LegType.DEMON_EAGLE);
		}
		@Override
		public boolean isTailLostOnInitialTF() {
			return false;
		}
	},

	/**
	 * This LegConfiguration is a configuration for feral biped-ish races with wings instead of forelegs.<br/>
	 * <b>This should only ever be used for ferals!</b>
	 */
	WINGED_BIPED("крылоногий",
			0,
			0,
			true,
			true,
			false,
			false,
			WingSize.THREE_LARGE,
			false,
			2,
			"Ноги и пах персонажа заменяются телом соответствующего животного-морфа, а вместо ног используются крылья-руки."
					+ " Самые распространенные примеры - одичавшие виверны и одичавшие летучие мыши, у которых вместо рук крылья, и которые используют эти руки-крылья, чтобы ходить на четвереньках.",
			"Выше [npc.her] паха, занимая нижнюю часть [npc.her] гуманоидного брюха,",
			TFModifier.TF_MOD_LEG_CONFIG_WINGED_BIPED,
			"statusEffects/race/raceBackgroundLegAvian") {
		@Override
		public List<GenitalArrangement> getAvailableGenitalConfigurations() {
			return Util.newArrayListOfValues(
					GenitalArrangement.NORMAL,
					GenitalArrangement.CLOACA,
					GenitalArrangement.CLOACA_BEHIND);
		}
		@Override
		public List<Class<? extends BodyPartInterface>> getFeralParts() {
			return Util.newArrayListOfValues(Ass.class, Anus.class, BreastCrotch.class, Leg.class, Tail.class, Tentacle.class, Penis.class, Testicle.class, Vagina.class, Clitoris.class);
		}
		@Override
		public List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character) {
			if(character.isFeral()) {
				return Util.newArrayListOfValues(
							new BodyPartClothingBlock(
									Util.newArrayListOfValues(
											InventorySlot.WEAPON_MAIN_1,
											InventorySlot.WEAPON_MAIN_2,
											InventorySlot.WEAPON_MAIN_3,
											InventorySlot.WEAPON_OFFHAND_1,
											InventorySlot.WEAPON_OFFHAND_2,
											InventorySlot.WEAPON_OFFHAND_3),
									character.getLegType().getRace(),
									"Поскольку у [npc.nameHasFull] животное тело [npc.a_legRace], [npc.she] не может использовать обычное оружие!",
									Util.newArrayListOfValues(
											ItemTag.FITS_ARM_WINGS,
											ItemTag.FITS_ARM_WINGS_EXCLUSIVE)));
				
			} else {
				return null; // This is a feral only leg configuration.
			}
		}
		@Override
		public void setLegsToDemon(GameCharacter character) {
			character.setLegType(LegType.DEMON_COMMON);
		}
		@Override
		public boolean isTailLostOnInitialTF() {
			return false;
		}
	};

	private String name;
	private int landSpeedModifier;
	private int waterSpeedModifier;
	private boolean bipedalPositionedGenitals;
	private boolean bipedalPositionedCrotchBoobs;
	private boolean largeGenitals;
	private boolean tall;
	
	private WingSize minimumWingSizeForFlight;
	private boolean wingsOnLegConfiguration;
	
	private int numberOfLegs;
	
	private String genericDescription;
	private String crotchBoobLocationDescription;

	private TFModifier tfModifier;

	private String subspeciesStatusEffectBackgroundPath;
	
	private LegConfiguration(
			String name,
			int landSpeedModifier,
			int waterSpeedModifier,
			boolean bipedalPositionedGenitals,
			boolean bipedalPositionedCrotchBoobs,
			boolean largeGenitals,
			boolean tall,
			WingSize minimumWingSizeForFlight,
			boolean wingsOnLegConfiguration,
			int numberOfLegs,
			String genericDescription,
			String crotchBoobLocationDescription,
			TFModifier tfModifier,
			String subspeciesStatusEffectBackgroundPath) {
		
		this.name = name;
		
		this.landSpeedModifier = landSpeedModifier;
		this.waterSpeedModifier = waterSpeedModifier;
		
		this.bipedalPositionedGenitals = bipedalPositionedGenitals;
		this.bipedalPositionedCrotchBoobs = bipedalPositionedCrotchBoobs;
		this.largeGenitals = largeGenitals;
		this.tall = tall;
		
		this.minimumWingSizeForFlight=minimumWingSizeForFlight;
		this.wingsOnLegConfiguration=wingsOnLegConfiguration;
		
		this.numberOfLegs = numberOfLegs;
		
		this.genericDescription = genericDescription;
		this.crotchBoobLocationDescription = crotchBoobLocationDescription;

		this.tfModifier = tfModifier;
		
		this.subspeciesStatusEffectBackgroundPath = subspeciesStatusEffectBackgroundPath;
	}

	public static LegConfiguration getValueFromString(String configuration) {
		if(configuration.equalsIgnoreCase("TAUR")) {
			configuration = "QUADRUPEDAL";
		}
		return LegConfiguration.valueOf(configuration);
	}
	
	/**
	 * @return A list of BodyPartInterface classes which are considered to be fully animalistic as part of this LegConfiguration. e.g. A taur's Tail, Ass, BreastCrotch, Penis, and Vagina are all animalistic.
	 */
	public abstract List<Class<? extends BodyPartInterface>> getFeralParts();
	
	/**
	 * @return true if this LegConfiguration removes the character's tail when applying its transformation.
	 */
	public abstract boolean isTailLostOnInitialTF();
	
	/**
	 * @return true if this LegConfiguration prevents the character from growing a tail.
	 */
	public boolean isAbleToGrowTail() {
		return true;
	}
	
	public String getName() {
		return name;
	}

	public String getMovementVerbPresentFirstPersonSingular() {
		return "идет";
	}

	public String getMovementVerbPresentThirdPersonSingular() {
		return "ходит";
	}

	public String getMovementVerbPresentParticiple() {
		return "идет";
	}

	public String getMovementVerbPastParticiple() {
		return "ходил";
	}

	public String getIndividualMovementVerbPresentFirstPersonSingular() {
		return "шаг";
	}

	public String getIndividualMovementVerbPresentThirdPersonSingular() {
		return "шаги";
	}

	public String getIndividualMovementVerbPresentParticiple() {
		return "шагая";
	}

	public String getIndividualMovementVerbPastParticiple() {
		return "шагнул";
	}
	
	public int getLandSpeedModifier() {
		return landSpeedModifier;
	}

	public int getWaterSpeedModifier() {
		return waterSpeedModifier;
	}

	/**
	 * @param body The corresponding body.
	 * @return The minimum WingSize required for flight.
	 */
	public WingSize getMinimumWingSizeForFlight(Body body) {
		return body.isFeral() ? WingSize.THREE_LARGE : minimumWingSizeForFlight;
	}

	public boolean isWingsOnLegConfiguration() {
		return wingsOnLegConfiguration;
	}

	/**
	 * @return true If this leg configuration has genitals in roughly the same place as on a biped.
	 */
	public boolean isBipedalPositionedGenitals() {
		return bipedalPositionedGenitals;
	}

	/**
	 * @return true If this leg configuration has crotch-boobs in roughly the same place as on a biped.
	 */
	public boolean isBipedalPositionedCrotchBoobs() {
		return bipedalPositionedCrotchBoobs;
	}

	public boolean isLargeGenitals() {
		return largeGenitals;
	}

	/**
	 * @return true if this configuration is classed as being 'tall' (such as quadrupeds).
	 */
	public boolean isTall() {
		return tall;
	}

	public boolean isThighSexAvailable() {
		return true;
	}

	public boolean isOneOf(LegConfiguration... values) {
		return Arrays.asList(values).contains(this);
	}
	
	public int getNumberOfLegs() {
		return numberOfLegs;
	}

	public List<FootStructure> getPermittedFootStructuresOverride() {
		return new ArrayList<>();
	}
	
	public boolean isGenitalsExposed(GameCharacter character) {
		return true;
	}

	public abstract List<GenitalArrangement> getAvailableGenitalConfigurations();

	public String getGenericDescription() {
		return genericDescription;
	}

	public String getCrotchBoobLocationDescription() {
		return crotchBoobLocationDescription;
	}

	public TFModifier getTFModifier() {
		return tfModifier;
	}
	
	public void setLegsToDemon(GameCharacter character) {
		throw new IllegalArgumentException("Демонические ноги для этой конфигурации ног еще не реализованы!");
	}

	public void setLegsToAvailableDemonLegs(GameCharacter character, AbstractLegType legType) {
		this.setLegsToAvailableDemonLegs(character, legType, LegType.DEMON_COMMON);
	}

	public void setLegsToAvailableDemonLegs(GameCharacter character, AbstractLegType legType, AbstractLegType fallBackLegType) {
		character.setLegType(legType.isAvailableForSelfTransformMenu(character) ? legType : fallBackLegType);
	}

	public void setWingsToDemon(GameCharacter character) {
		character.setWingType(WingType.DEMON_COMMON);
		character.setWingSize(this.minimumWingSizeForFlight.getValue());
	}

	/**
	 * @return A list of BodyPartClothingBlock objects which define how this LegConfiguration is blocking InventorySlots. Returns null if it doesn't affect inventorySlots in any way.
	 */
	public abstract List<BodyPartClothingBlock> getBodyPartClothingBlock(GameCharacter character);

	public String getSubspeciesStatusEffectBackgroundPath() {
		return subspeciesStatusEffectBackgroundPath;
	}
	
	/**
	 * @return How many times longer a character's serpent tail is than their height.
	 */
	public static int getDefaultSerpentTailLengthMultiplier() {
		return 5;
	}
}
