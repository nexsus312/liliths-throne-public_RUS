package com.lilithsthrone.controller.eventListeners.tooltips;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MouseEvent;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.controller.TooltipUpdateThread;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.4.5
 * @author Innoxia
 */
public class TooltipResponseDescriptionEventListener implements EventListener {
	private int index;
	private boolean nextPage = false;
	private boolean previousPage = false;
	
	private static StringBuilder tooltipSB;
	static {
		tooltipSB = new StringBuilder();
	}

	@Override
	public void handleEvent(Event event) {

		Main.mainController.setTooltipContent("");

		if (nextPage) {
			if (Main.game.isHasNextResponsePage()) {

				Main.mainController.setTooltipSize(360, 60);
				
				double xPosition = ((MouseEvent) event).getScreenX() + 16 - 180;
				if (xPosition + 360 > Main.primaryStage.getX() + Main.primaryStage.getWidth() - 16)
					xPosition = Main.primaryStage.getX() + Main.primaryStage.getWidth() - 360 - 16;
				double yPosition = Main.primaryStage.getY() + Main.primaryStage.getHeight() - (34*(MainController.RESPONSE_COUNT/5) + 4) - Main.mainController.getTooltip().getHeight()
						- (Main.mainScene.getWindow().getHeight() - Main.mainScene.getHeight() - Main.mainScene.getY());

				Main.mainController.getTooltip().setAnchorX(xPosition);
				Main.mainController.getTooltip().setAnchorY(yPosition);

				Main.mainController.setTooltipContent("<div class='title'>Следующая страница</div>");
				
				Main.mainController.getTooltip().setAnchorX(xPosition);
				Main.mainController.getTooltip().setAnchorY(yPosition);
				
				TooltipUpdateThread.updateToolTip(xPosition,yPosition);
			}
		} else if (previousPage) {
			if (Main.game.getResponsePage() != 0) {
				
				Main.mainController.setTooltipSize(360, 60);
				
				double xPosition = ((MouseEvent) event).getScreenX() + 16 - 180;
				if (xPosition + 360 > Main.primaryStage.getX() + Main.primaryStage.getWidth() - 16)
					xPosition = Main.primaryStage.getX() + Main.primaryStage.getWidth() - 360 - 16;
				double yPosition = Main.primaryStage.getY() + Main.primaryStage.getHeight() - (34*(MainController.RESPONSE_COUNT/5) + 4) - Main.mainController.getTooltip().getHeight()
						- (Main.mainScene.getWindow().getHeight() - Main.mainScene.getHeight() - Main.mainScene.getY());

				Main.mainController.getTooltip().setAnchorX(xPosition);
				Main.mainController.getTooltip().setAnchorY(yPosition);

				Main.mainController.setTooltipContent("<div class='title'>Предыдущая страница</div>");
				
				Main.mainController.getTooltip().setAnchorX(xPosition);
				Main.mainController.getTooltip().setAnchorY(yPosition);
				
				TooltipUpdateThread.updateToolTip(xPosition,yPosition);
			}
			
		} else {
			Response response = null;
			if(Main.game.getCurrentDialogueNode()!=null) {
				if (Main.game.getResponsePage() == 0) {
					response = Main.game.getCurrentDialogueNode().getResponse(Main.game.getResponseTab(), index);
				} else {
					if (index != 0) {
						response = Main.game.getCurrentDialogueNode().getResponse(Main.game.getResponseTab(), Main.game.getResponsePage() * MainController.RESPONSE_COUNT + index - 1);
					} else {
						response = Main.game.getCurrentDialogueNode().getResponse(Main.game.getResponseTab(), Main.game.getResponsePage() * MainController.RESPONSE_COUNT + MainController.RESPONSE_COUNT-1);
					}
				}
			}
			
			if (response != null) {
				tooltipSB.setLength(0);
				
				int boxHeight = 130;
				
				if(!response.hasRequirements()) {
					if(response instanceof ResponseSex) {
						if(((ResponseSex)response).isMasturbation()) {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX_AS_DOM.toWebHexString() + ";'>Мастурбация</span></div>");
						} else if(((ResponseSex)response).isPlayerInDominantSlot()) {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX_AS_DOM.toWebHexString() + ";'>Доминирующий секс</span></div>");
						} else {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Покорный секс</span></div>");
						}
						boxHeight+=44;
						tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");
						
					} else if(response.isCombatHighlight()) {
						tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_COMBAT.toWebHexString() + ";'>Бой</span></div>");
						boxHeight+=44;
						tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");
						
					} else if(response.getAssociatedCombatMove()!=null) {
						AbstractCombatMove move = response.getAssociatedCombatMove();
						boolean coreMove = Main.game.getPlayer().getEquippedMoves().contains(move);
						
						tooltipSB.append("<div class='title'>"
											+ "<span style='color:" + (coreMove?PresetColour.GENERIC_MINOR_GOOD:PresetColour.GENERIC_MINOR_BAD).toWebHexString() + ";'>"
												+Util.capitaliseSentence(move.getName(0, Main.game.getPlayer()))
											+"</span>"
										+ "</div>");
						boxHeight+=44;
						
						int cost = move.getAPcost(Main.game.getPlayer());
						int cooldown = move.getCooldown(Main.game.getPlayer());
						
						tooltipSB.append(
								"<div class='subTitle' style='width:46%; margin:2% 2% 0% 2%;'>"
									+"<span style='color:"+(PresetColour.ACTION_POINT_COLOURS[cost]).toWebHexString()+";'>"
									+(coreMove?cost:(cost-1)+"[style.colourBad(+1)]")
									+"</span> Действий"
								+ "</div>"
								+ "<div class='subTitle' style='width:46%; margin:2% 2% 0% 2%;'>"
									+ "<span style='color:"+(cooldown-(coreMove?0:1)<=0?PresetColour.GENERIC_MINOR_GOOD:PresetColour.GENERIC_MINOR_BAD).toWebHexString()+";'>"
									+(coreMove?cooldown:(cooldown-1)+"[style.colourBad(+1)]")
									+"</span> turn"+(cooldown==1?"":"s")+" перезарядка"
								+ "</div>");
						
						boxHeight+=36;
						
						tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");

						tooltipSB.append(
								"<div class='description-small'>"
										+ (coreMove
											?"<i>Это одино из ваших [style.colorMinorGood(основных движений)], поэтому его использование не требует дополнительных затрат действий и перезарядки!</i>"
											:"<i>Это [style.colourMinorBad(не одно из ваших основных движений)], поэтому его использование требует [style.colourBad(+1 Действие)] и [style.colourBad(+1 ход перезарядки)] за использование!</i>")
								+"</div>");
						boxHeight+=54;
						
					} else {
						tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");
					}
					
				
				} else {
					if(response.isAvailable()) {
						if(response instanceof ResponseSex) {
							if(((ResponseSex)response).isPlayerInDominantSlot()) {
								tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX_AS_DOM.toWebHexString() + ";'>Доминирующий секс</span> (<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Доступно</span>)</div>");
							} else {
								tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Покорный секс</span> (<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Доступно</span>)</div>");
							}
						} else if(response.isCombatHighlight()) {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_COMBAT.toWebHexString() + ";'>Бой</span> (<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Доступно</span>)</div>");
						} else {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Available</span></div>");
						}
						boxHeight+=44;
						
						if(response.getSexPace()!=null) {
							tooltipSB.append("<div class='subTitle'><span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Темп секса:</span>"
									+ " <span style='color:" + response.getSexPace().getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(response.getSexPace().getName())+"</span></div>");
							boxHeight+=44;
						}
						
						tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");
						
					} else if(response.isAbleToBypass()) {
						if(response instanceof ResponseSex) {
							if(((ResponseSex)response).isPlayerInDominantSlot()) {
								tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX_AS_DOM.toWebHexString() + ";'>Доминирующий секс</span>"
										+ " (<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Развращает</span>)</div>");
							} else {
								tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Покорный секс</span>"
										+ " (<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Развращает</span>)</div>");
							}
						} else if(response.isCombatHighlight()) {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_COMBAT.toWebHexString() + ";'>Бой</span> (<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Развращает</span>)</div>");
						} else {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>Развращает</span></div>");
						}
						boxHeight+=44;
						
						if(response.getSexPace()!=null) {
							tooltipSB.append("<div class='subTitle'><span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Темп секса:</span>"
									+ " <span style='color:" + response.getSexPace().getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(response.getSexPace().getName())+"</span></div>");
							boxHeight+=44;
						}
						
						tooltipSB.append("<div class='description'>" + response.getTooltipText() + "</div>");
						
					} else {
						if(response instanceof ResponseSex) {
							if(((ResponseSex)response).isPlayerInDominantSlot()) {
								tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX_AS_DOM.toWebHexString() + ";'>Доминирующий секс</span>"
										+ " (<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Недоступно</span>)</div>");
							} else {
								tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Покорный секс</span>"
										+ " (<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Недоступно</span>)</div>");
							}
						} else if(response.isCombatHighlight()) {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_COMBAT.toWebHexString() + ";'>Бой</span> (<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Недоступно</span>)</div>");
						} else {
							tooltipSB.append("<div class='title'><span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Недоступно</span></div>");
						}
						boxHeight+=44;
						
						if(response.getSexPace()!=null) {
							tooltipSB.append("<div class='subTitle'><span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Фаза секса:</span>"
									+ " <span style='color:" + response.getSexPace().getColour().toWebHexString() + ";'>"+Util.capitaliseSentence(response.getSexPace().getName())+"</span></div>");
							boxHeight+=44;
						}
						
						tooltipSB.append("<div class='description'><span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>" + response.getTooltipText() + "</span></div>");
					}
					
					tooltipSB.append(
							"<div class='description' style='height:"+((response.lineHeight()+2)*18)+"; text-align:center;'>"
									+ "<b>Доступность:</b>"
									+response.getTooltipBlockingList()+response.getTooltipRequiredList()
							+"</div>");
					
					tooltipSB.append(
							"<div class='description-small'>"
									+response.getTooltipCorruptionBypassText()
							+"</div>");
					
					String extraSexInfo = response.getAdditionalSexActionInformationText();
					if(!extraSexInfo.isEmpty()) {
						tooltipSB.append(
								"<div class='description-small'>"
										+extraSexInfo
								+"</div>");
						boxHeight+=54;
					}
					
					boxHeight+=54;
					
					boxHeight+= 28 + ((response.lineHeight()+1)*18);
				}
				
				/* TODO
				 * Verify that there is no adverse effects to using this method to calculate the tooltip height,
				 * then remove all boxHeight calculations above, I guess, and apply this method to other
				 * tooltip types that could use this.
				 */
				int realHeight = Main.mainController.setTooltipContent(UtilText.parse(tooltipSB.toString()));
				
//				if(false) {
//					// for every response tooltip, print the height values
//					// very spammy
//					System.out.println("predicted: " + boxHeight);
//					System.out.println("measured:  " + realHeight);
//				}
				
				boxHeight = realHeight;

				Main.mainController.setTooltipSize(360, boxHeight);
				
				double xPosition = ((MouseEvent) event).getScreenX() + 16 - 180;
				if (xPosition + 360 > Main.primaryStage.getX() + Main.primaryStage.getWidth() - 16)
					xPosition = Main.primaryStage.getX() + Main.primaryStage.getWidth() - 360 - 16;
				
				double yPosition = Main.primaryStage.getY() + Main.primaryStage.getHeight() - (34*(MainController.RESPONSE_COUNT/5) + 4) - boxHeight
						- (Main.mainScene.getWindow().getHeight() - Main.mainScene.getHeight() - Main.mainScene.getY());
				
				Main.mainController.getTooltip().setAnchorX(xPosition);
				Main.mainController.getTooltip().setAnchorY(yPosition);
				
				TooltipUpdateThread.updateToolTip(xPosition,yPosition);
			}
			
		}
	}

	public TooltipResponseDescriptionEventListener setIndex(int index) {
		this.index = index;

		nextPage = false;
		previousPage = false;
		return this;
	}

	public TooltipResponseDescriptionEventListener nextPage() {
		nextPage = true;
		previousPage = false;

		return this;
	}

	public TooltipResponseDescriptionEventListener previousPage() {
		nextPage = false;
		previousPage = true;

		return this;
	}

}
