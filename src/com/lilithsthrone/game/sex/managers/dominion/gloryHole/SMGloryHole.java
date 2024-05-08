package com.lilithsthrone.game.sex.managers.dominion.gloryHole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.population.Population;

/**
 * @since 0.2.9
 * @version 0.3.7
 * @author Innoxia
 */
public class SMGloryHole extends SexManagerDefault {

	public SMGloryHole(AbstractSexPosition position, Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(position,
				dominants,
				submissives);
	}

	@Override
	public SexControl getSexControl(GameCharacter character) {
		if(Main.sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_KNEELING)
				|| Main.sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_FUCKED)
				|| Main.sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED)
				|| Main.sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_FUCKING)) {
			return SexControl.FULL;
			
		} else {
			return SexControl.ONGOING_ONLY;
		}
	}
	
	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}

	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return character.isPlayer()
				|| Main.sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_KNEELING)
				|| Main.sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_FUCKED)
				|| Main.sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED);
	}

	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character){
		return character.isPlayer();
	}
	
	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
		return false;
	}

	@Override
	public boolean isPlayerAbleToStopSex() {
		return true;
	}
	
	@Override
	public boolean isCharactersReactingToExposedAreas() {
		return false;
	}
	
	@Override
	public List<InventorySlot> getSlotsConcealed(GameCharacter characterBeingExposed, GameCharacter characterViewing) {
		List<InventorySlot> concealedSlots = new ArrayList<>();
		
		if(Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_KNEELING)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.MOUTH);
			return concealedSlots;
			
		} else if(Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_FUCKED)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.MOUTH);
			concealedSlots.remove(InventorySlot.PENIS);
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.GROIN);
			return concealedSlots;
			
		} else if(Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.MOUTH);
			concealedSlots.remove(InventorySlot.ANUS);
			concealedSlots.remove(InventorySlot.GROIN);
			return concealedSlots;
		}
		
		// The ones on the other side of the hole cannot see one another
		if(Main.sex.getSexPositionSlot(characterViewing).equals(SexSlotUnique.GLORY_HOLE_FUCKING)
				|| Main.sex.getSexPositionSlot(characterViewing).equals(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)
				|| Main.sex.getSexPositionSlot(characterViewing).equals(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			return concealedSlots;
		}
		
		if(Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_FUCKING)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			if(!characterBeingExposed.isTaur()) {
				concealedSlots.remove(InventorySlot.PENIS);
			}
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.GROIN);
			
		} else if(Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)
					|| Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			
			if(!characterBeingExposed.isTaur()) {
				concealedSlots.remove(InventorySlot.PENIS);
			}
			if(characterBeingExposed.getGenitalArrangement()==GenitalArrangement.CLOACA
					|| characterBeingExposed.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
				concealedSlots.remove(InventorySlot.ANUS);
				concealedSlots.remove(InventorySlot.PENIS);
			}
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.GROIN);
			
		}
		
		return concealedSlots;
	}

	@Override
	public String getPublicSexStartingDescription() {
		return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
					+ "Когда вы выпускаете [pc.a_moan+], несколько человек в туалетах оборачиваются, чтобы посмотреть, что происходит."
					+ " Увидев, что дверь в вашу кабинку оставлена открытой, и вы собираетесь приступить к обслуживанию стоящих перед вами членов, несколько из них подходят и готовятся наблюдать..."
				+ "</p>";
	}

	@Override
	public String getRandomPublicSexDescription() {
		Set<AbstractSubspecies> subspeciesSet = new HashSet<>();
		for(Population pop : Main.game.getPlayer().getLocationPlace().getPlaceType().getPopulation()) {
			subspeciesSet.addAll(pop.getSpecies().keySet());
		}
		if(!subspeciesSet.isEmpty()) {
			AbstractSubspecies subspecies = Util.randomItemFrom(subspeciesSet);
			
			return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
						+ UtilText.returnStringAtRandom(
							"Люди, собравшиеся посмотреть на вашу развратную демонстрацию, хохочут и подбадривают вас.",
							"Вы слышите, как кто-то в толпе волком свистит, наблюдая за тем, как вы обслуживаете отверстия в кабинке.",
							"Пара мальчиков-зебр протискивается сквозь толпу, но вместо того, чтобы остановить ваше веселье, они присоединяются к толпе, посмеиваясь и комментируя ваше выступление.",
							"Вы слышите, как толпа, собравшаяся посмотреть на вас, комментирует ваше выступление.",
							"Толпа зрителей со смехом наблюдает за тем, как вы продолжаете обслуживать стоящие перед вами члены.",
							"Оглянувшись, вы видите, как несколько членов толпы трогают себя, наблюдая за тем, как вы обслуживаете отверстия в кабинке.",
							"Толпа подбадривает вас, когда вы продолжаете обслуживать отверстия в кабинке.",
							"Толпа смеется и аплодирует, а вы продолжаете вести себя перед ними как отчаянная шлюха.",
							"Несколько человек из толпы кричат и подбадривают вас, пока вы продолжаете обслуживать отверстия в кабинке.",
							Util.capitaliseSentence(UtilText.generateSingularDeterminer(subspecies.getSingularFemaleName(null)))+" "+subspecies.getSingularFemaleName(null)+" шагает вперед,"
									+ " поглаживая ее обнаженный член, когда она внезапно кончает на пол прямо перед вами.",
							Util.capitaliseSentence(UtilText.generateSingularDeterminer(subspecies.getSingularMaleName(null)))+" "+subspecies.getSingularMaleName(null)+" шагает вперед,"
									+ " поглаживая его обнаженный член, когда он внезапно кончает на пол прямо перед вами.")
					+"</p>";
		}
		
		return "";
	}
}
