package com.lilithsthrone.game.dialogue.utils;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishPreference;
import com.lilithsthrone.game.character.gender.AndrogynousIdentification;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderNames;
import com.lilithsthrone.game.character.gender.GenderPronoun;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.persona.SexualOrientationPreference;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesPreference;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.settings.ContentPreferenceValue;
import com.lilithsthrone.game.settings.DifficultyLevel;
import com.lilithsthrone.game.settings.ForcedFetishTendency;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.settings.KeyCodeWithModifiers;
import com.lilithsthrone.game.settings.KeyboardAction;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Artist;
import com.lilithsthrone.rendering.ArtistWebsite;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.CreditsSlot;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4.2
 * @author Innoxia, Maxis
 */
public class OptionsDialogue {
	
	private static boolean confirmNewGame = false;
	public static boolean startingNewGame = false;
	
	private static boolean alphabeticalFileSort = false;
	
	public static final DialogueNode MENU = new DialogueNode("Menu", "Menu", true) {
		
		@Override
		public String getLabel() {
			return "";
		}
		
		@Override
		public String getContent(){
			return "<h1 class='special-text' style='font-size:48px; line-height:52px; text-align:center;'>"+Main.GAME_NAME+"</h1>"
					+ (Main.game.isSillyMode()
						?"<p class='special-text' style='text-align:center; margin:0 0; padding:0 0;'><i>Или я не могу поверить, что упал в волшебное зеркало и попал в мир, где моя тетя - демон?!</i></p>"
						:"")
					+ "<h5 class='special-text' style='text-align:center;'>Создано: "+Main.AUTHOR+"</h5>"
					+ "<br/>"
					+ "<p>"
						+ "Эта игра представляет собой текстовую эротическую ролевую игру и содержит большое количество графического сексуального контента. Прежде чем играть в эту игру, вы должны согласиться с отказом от ответственности!"
					+ "</p>"
					+"<p>"
						+ "Вы можете посетить блог разработчика: (https://lilithsthrone.blogspot.co.uk) чтобы увидеть прогресс разработки (используйте 'Блог' кнопку для открытия браузера)."
						+ " [style.italicsMinorBad(<b>Заметка:</b> На blogspot внедряется интрузивная проверка возраста, поэтому я, скорее всего, скоро создам новый блог.)]"
					+ "</p>"
					+ "<p style='text-align:center'>"
						+ "<b>Пожалуйста, используйте мой блог или github, чтобы получить последнюю официальную версию Lilith's Throne!</b>"
						+ "<b>Данная версия игры модифицирована для поддержки Русского языка!!! Все права принадлежат правообладателям, проект распостраняется в соотвествии с лицензией.</b>"
					+ "</p>"
					+ getJavaVersionInformation()
					+ (Toolkit.getDefaultToolkit().getScreenSize().getHeight()<800
							?"<p style='text-align:center; color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>"
								+ "Если разрешение игры не соответствует вашему экрану, нажмите клавиши: 'Windows' + 'Стрелка вверх' для увеличения!"
							+ "</p>"
							:"")
					+ "<br/>"
					+ (Main.game.isStarted() || Main.getProperties().name.isEmpty()
							?""
							:"<h4 style='text-align:center;'>Last save:</h4>"
								+ "<h5 style='color:" + Main.getProperties().nameColour + ";text-align:center;'>" + Main.getProperties().name + "</h5>"
								+ "<p style='text-align:center;'><b>Level " + Main.getProperties().level + " " + Util.capitaliseSentence(Main.getProperties().race) + "</b></p>"
								+ "<p style='text-align:center;'>" + UtilText.formatAsMoney(Main.getProperties().money, "b") + "</p>"
								+ "<div style='text-align:center; display:block; margin:auto;'>" + UtilText.formatAsEssences(Main.getProperties().arcaneEssences, "b", false) + "</div>"
								+ "<p style='text-align:center;'>Quest: " + Util.capitaliseSentence(Main.getProperties().quest) + "</p>");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				 if(confirmNewGame || !Main.game.isStarted()) {
					return new ResponseEffectsOnly(
							(!Main.game.isStarted()
									?"Новая игра"
									:"<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Подтвердить</b>"),
							"Начать новую игру."
								+(Main.game.isStarted()
									?"<br/><br/>[style.italicsMinorBad(Не забудьте сначала сохранить игру!)]</b>"
									:"")){
						@Override
						public void effects() {
							if(!startingNewGame) {
								startingNewGame = true;
								
								//Fixes a bug where inventory would stay on screen
								if (Main.game.isStarted()) {
									Main.game.setInCombat(false);
									Main.game.setInSex(false);
								}
								
								Main.mainController.setAttributePanelContent("");
								Main.mainController.setRightPanelContent("");
								Main.mainController.setButtonsLeftContent("");
								Main.mainController.setButtonsRightContent("");
								Main.game.setRenderMap(false);
								Main.startNewGame(CharacterCreation.CHARACTER_CREATION_START);
								confirmNewGame = false;
							}
						}
					};
					
				 } else {
					 return new Response(
							 "Новая игра",
							 "Начать новую игру."
								+(Main.game.isStarted()
									?"<br/><br/>[style.italicsMinorBad(Не забудьте сначала сохранить игру!)]"
									:""),
								MENU){
							@Override
							public void effects() {
								confirmNewGame = true;
							}
						};
				 }
				
			} else if (index == 2) {
				return new Response("Сохранить/Загрузить", "Открывает окно сохранить/загрузить", SAVE_LOAD){
					@Override
					public void effects() {
						loadConfirmationName = ""; overwriteConfirmationName = ""; deleteConfirmationName = "";
						confirmNewGame=false;
					}
				};
				
			} else if (index == 3) {
				return new Response("Экспорт персонажа", "Открыть окно экспорта персонажа.", IMPORT_EXPORT){
					@Override
					public void effects() {
						loadConfirmationName = ""; overwriteConfirmationName = ""; deleteConfirmationName = "";
						confirmNewGame=false;
					}
				};
				
			} else if (index == 4) {
				return new Response("Отказ от ответственности", "Ознакомьтесь с отказом от ответственности игры.", DISCLAIMER){
					@Override
					public void effects() {
						confirmNewGame=false;
					}
				};
				
			} else if (index == 5) {
				return new ResponseEffectsOnly("Выход", "Выход из игры и закрытие программы.<br/><br/><b>Не забудьте сначала сохранить игру!</b>"){
					@Override
					public void effects() {
						Main.primaryStage.close();
						confirmNewGame=false;
						System.exit(0);
					}
				};
				
			} else if (index == 6) {
				return new Response("Настройки", "Открыть страницу с настройками.", OPTIONS){
					@Override
					public void effects() {
						confirmNewGame=false;
						
					}
				};

			} else if (index == 7) {
				return new Response("Настройки контента", "Настройте что вы хотели бы видеть в игре.", MISCELLANEOUS){
					@Override
					public void effects() {
						confirmNewGame=false;
					}
				};
			
			} else if (index == 8) {
				return new Response("Примечания к патчу", "Просмотрите примечания к патчу для этой версии.", PATCH_NOTES);
			
			} else if (index == 9) {
				return new Response("Титры", "Просмотрите экран титров игры.", CREDITS);
				
			} else if (index == 11) {
				return new ResponseEffectsOnly("Блог", "Открывает страницу:<br/><br/><i>https://lilithsthrone.blogspot.co.uk/</i><br/><br/><b>Внешне в вашем браузере по умолчанию.</b>"){
					@Override
					public void effects() {
						Util.openLinkInDefaultBrowser("https://lilithsthrone.blogspot.co.uk/");
						confirmNewGame=false;
					}
				};
			
			} else if (index == 12) {
				return new ResponseEffectsOnly("Github", "Открывает страницу:<br/><br/><i>https://github.com/Innoxia/liliths-throne-public</i><br/><br/><b>Внешне в вашем браузере по умолчанию.</b>"){
					@Override
					public void effects() {
						Util.openLinkInDefaultBrowser("https://github.com/Innoxia/liliths-throne-public");
						confirmNewGame=false;
					}
				};
			
			} else if (index == 13) {
				return new ResponseEffectsOnly("Вики", "Открывает страницу:<br/><br/><i>https://www.lilithsthrone.com/wiki/doku.php</i><br/><br/><b>Внешне в вашем браузере по умолчанию.</b>"){
					@Override
					public void effects() {
						Util.openLinkInDefaultBrowser("https://www.lilithsthrone.com/wiki/doku.php");
						confirmNewGame=false;
					}
				};
			
			} else if (index == 0) {
				if(Main.game.isStarted()) {
					return new ResponseEffectsOnly("Возобновить", "Вернитесь к тому, что вы делали до открытия этого меню."){
						@Override
						public void effects() {
							Main.mainController.openOptions();
							confirmNewGame=false;
							
						}
					};
					
				} else {
					if(Main.isLoadGameAvailable(Main.getProperties().lastSaveLocation)) {
						return new ResponseEffectsOnly("Продолжить", "Продолжайте играть с момента вашего последнего сохранения."){
							@Override
							public void effects() {
								Main.loadGame(Main.getProperties().lastSaveLocation);
								confirmNewGame=false;
								
							}
						};
					} else if ( "".equals(Main.getProperties().lastSaveLocation) ) {
						return new Response("Продолжить", "Ранее сохраненная игра для возобновления отсутствует.", null);
					} else {
						return new Response("Продолжить", "Ранее сохраненная игра (по названию '"+Main.getProperties().lastSaveLocation+"') не найдена в папке: 'data/saves'.", null);
					}
				}
				
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getJavaVersionInformation() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'>"
					+ "Ваша версия Java: "+System.getProperty("java.version"));
//				+" | ");
		
//		String[] version = System.getProperty("java.version").split("\\.");
//		if(version[0]!=null) {
//			if(Integer.valueOf(version[0])<9) {
//				sb.append("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>You have an old version of java!</span> This game needs at least 9.0.1 to work correctly!");
//			} else {
//				sb.append("<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Your java is up to date!</span>");
//			}
//		}
//		if(version.length>=2) {
//			if(Integer.valueOf(version[1])<8) {
//				sb.append("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>You have an old version of java!</span> This game needs at least v1.8.0_131 to work correctly!");
//				
//			} else {
//				if(version.length==3){
//					String[] versionMinor = version[2].split("_");
//					if(versionMinor.length>=2)
//						if(Integer.valueOf(versionMinor[1])<131) {
//							sb.append("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>You have an old version of java!</span> This game needs at least v1.8.0_131 to work correctly!");
//							
//						} else {
//							sb.append("<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Your java is up to date!</span>");
//						}
//				} else {
//					sb.append("This game needs at least v1.8.0_131 to work correctly!");
//				}
//			}
//		}
		
		sb.append("</p>");
		
		return sb.toString();
	}

	public static String loadConfirmationName = "";
	public static String overwriteConfirmationName = "";
	public static String deleteConfirmationName = "";
	
	public static final DialogueNode SAVE_LOAD = new DialogueNode("Сохранения", "", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();

			saveLoadSB.append(
					"<p style='text-align:center;'>"
						+ "<b>Пожалуйста, обратите внимание:</b>"
					+ "</p>"
					+ "<p>"
						+ "1. Для сохранения имен файлов будут использоваться только стандартные символы (буквы и цифры).<br/>"
						+ "2. Файл `Автосохранения` автоматически перезаписывается каждый раз, когда вы перемещаетесь между картами.<br/>"
						+ "3. Файл `Быстрого сохранения` автоматически перезаписывается при каждом быстром сохранении (назначено на клавишу: "+Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.QUICKSAVE).getFullName()+").<br/>"
						+ "4. Вы не можете сохранять данные во время сцен, которые ограничивают ваши движения, включая боевые действия и секс."
					+ "</p>"
					+ "<div class='container-full-width' style='padding:0; margin:0;'>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); background:transparent;'>"
							+ "Время"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(50% - 16px); text-align:center; background:transparent;'>"
							+ "Имя"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
							+ "Сохранить | Загрузить | Удалить"
						+ "</div>"
					+ "</div>");


			int i=0;
			
			if(Main.game.isStarted()) {
				saveLoadSB.append(getSaveLoadRow(null, null, i%2==0));
				i++;
			}
			
//			Main.getSavedGames(alphabeticalFileSort).sort(Comparator.comparingLong(File::lastModified).reversed());
			
			for(File f : Main.getSavedGames(alphabeticalFileSort)) {
				saveLoadSB.append(getSaveLoadRow("<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"+Util.getFileTime(f)+"</span>", f.getName(), i%2==0));
				i++;
			}
			
			saveLoadSB.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Подтверждения: ",
						"Переключите подтверждения, отображаемые при нажатии для загрузки, перезаписи или удаления сохраненной игры."
							+ " Когда включено: любая кнопка будет требовать двух нажатий."
							+ " Когда выключено: любая кнопка будет требовать одного нажатия.",
						SAVE_LOAD) {
					@Override
					public String getTitle() {
						return "Подтверждения: "+(Main.getProperties().hasValue(PropertyValue.overwriteWarning)
								?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Вкл</span>"
								:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Выкл</span>");
					}
					
					@Override
					public void effects() {
						loadConfirmationName = "";
						overwriteConfirmationName = "";
						deleteConfirmationName = "";
						Main.getProperties().setValue(PropertyValue.overwriteWarning, !Main.getProperties().hasValue(PropertyValue.overwriteWarning));
						Main.getProperties().savePropertiesAsXML();
					}
				};

			} else if (index == 2) {
				return new Response("Сортировка: Дата", "Отсортируйте все сохраненные вами игры по дате их создания.", SAVE_LOAD) {
					@Override
					public void effects() {
						alphabeticalFileSort = false;
					}
				};

			} else if (index == 3) {
				return new Response("Сортировка: Имя", "Отсортируйте все сохраненные вами игры по их названию.", SAVE_LOAD) {
					@Override
					public void effects() {
						alphabeticalFileSort = true;
					}
				};

			} else if (index == 0) {
				return new Response("Назад", "Вернутся в главное меню.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode IMPORT_EXPORT = new DialogueNode("Экспорт персонажа", "", true) {
	
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();
	
			saveLoadSB.append("<p>"
						+ "Здесь вы можете экспортировать своего текущего персонажа или удалить всех персонажий которых вы экспортировали в прошлом."
						+ " Любого NPC можно экспортировать в игре, просмотрев экран информации о нем (либо на экране `присутствующие персонажи`, либо на экране `контакты` вашего телефона), а затем нажав кнопку `экспортировать персонажа` (в правом нижнем углу пользовательского интерфейса)."
					+ "</p>"
					+ "<p>"
						+ "Экспортированные персонажи могут быть использованы в качестве играбельного персонажа при начале новой игры (выберите `Начать (импорт)`.), или в качестве импортного раба в Аукционном блоке на Аллее рабов."
					+ "</p>"
					+ "<div class='container-full-width' style='padding:0; margin:0;'>"
						+ "<div class='container-quarter-width' style='text-align:center;'>"
							+ "Time"
						+ "</div>"
						+ "<div class='container-half-width' style='width:calc(55% - 16px); text-align:center; background:transparent;'>"
							+ "Name"
						+ "</div>"
						+ "<div class='container-quarter-width' style='width:calc(20% - 16px); text-align:center; background:transparent;'>"
							+ "Functions"
						+ "</div>"
					+ "</div>");
			
			Main.getCharactersForImport().sort(Comparator.comparingLong(File::lastModified).reversed());
			int i = 0;
			for(File f : Main.getCharactersForImport()){
				saveLoadSB.append(OptionsDialogue.getImportRow("<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"+Util.getFileTime(f)+"</span>", f.getName(), i%2==0));
			}
			
			saveLoadSB.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Подтверждения: ",
						"Переключите подтверждения, отображаемые при нажатии для загрузки, перезаписи или удаления сохраненной игры."
							+ " Когда включено: любая кнопка будет требовать двух нажатий."
							+ " Когда выключено: любая кнопка будет требовать одного нажатия.",
							IMPORT_EXPORT) {
					@Override
					public String getTitle() {
						return "Подтверждения: "+(Main.getProperties().hasValue(PropertyValue.overwriteWarning)
								?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Вкл</span>"
								:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Выкл</span>");
					}
					
					@Override
					public void effects() {
						OptionsDialogue.loadConfirmationName = "";
						OptionsDialogue.overwriteConfirmationName = "";
						OptionsDialogue.deleteConfirmationName = "";
						Main.getProperties().setValue(PropertyValue.overwriteWarning, !Main.getProperties().hasValue(PropertyValue.overwriteWarning));
						Main.getProperties().savePropertiesAsXML();
					}
				};
	
			} else if (index == 2) {
				if(Main.game.isStarted()) {
					return new Response("Экспорт персонажа", "Эспортирует файл персонажа в папку: 'data/characters/'.", IMPORT_EXPORT){
						@Override
						public void effects() {
							Main.game.getCharacterUtils().saveCharacterAsXML(Main.game.getPlayer());
							Main.game.flashMessage(PresetColour.GENERIC_GOOD, "Персонаж экспортрован!");
						}
					};
				} else {
					return new Response("Экспорт персонажа", "Вам нужно сначала начать игру!", null);
				}
			
			} else if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню.", OptionsDialogue.MENU);
	
			} else {
				return null;
			}
		}
	
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getSaveLoadRow(String date, String name, boolean altColour) {
		if(name!=null){
			String baseName = Util.getFileName(name);
			String identifierName = Util.getFileIdentifier(name);

			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";":"")+"'>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); background:transparent;'>"
							+ date
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(50% - 16px); background:transparent;'>"
							+ baseName
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px);text-align:center; background:transparent;'>"
							+ (Main.isSaveGameAvailable()
									?(name.equals(overwriteConfirmationName)
										?"<div class='square-button saveIcon' id='OVERWRITE_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveConfirm()+"</div></div>"
										:"<div class='square-button saveIcon' id='OVERWRITE_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskOverwrite()+"</div></div>")
									:"<div class='square-button saveIcon disabled' id='OVERWRITE_" + identifierName + "_DISABLED'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveDisabled()+"</div></div>")
							
							+ (name.equals(loadConfirmationName)
									?"<div class='square-button saveIcon' id='LOAD_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoadConfirm()+"</div></div>"
									:"<div class='square-button saveIcon' id='LOAD_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoad()+"</div></div>")
	
	
							+ (name.equals(deleteConfirmationName)
								?"<div class='square-button saveIcon' id='DELETE_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDeleteConfirm()+"</div></div>"
								:"<div class='square-button saveIcon' id='DELETE_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div></div>")
						+ "</div>"
					+ "</div>";
			
		} else {
			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";":"")+"'>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); background:transparent;'>"
							+ "-"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(50% - 16px); background:transparent;'>"
							+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='new_save_name' value='New Save' style='padding:0;margin:0;width:100%;'></form>"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
							+ (Main.isSaveGameAvailable()
								?"<div class='square-button saveIcon' id='NEW_SAVE' style='float:left;'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSave()+"</div></div>"
								:"<div class='square-button saveIcon disabled' id='NEW_SAVE_DISABLED' style='float:left;'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveDisabled()+"</div></div>")
						+ "</div>"
					+ "</div>";
				
		}
	}

	private static String getImportRow(String date, String name, boolean altColour) {
		String baseName = Util.getFileName(name);
		String identifierName = Util.getFileIdentifier(name);
		
		return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+"'>"
					+ "<div class='container-quarter-width' style='background:transparent;'>"
						+ date
					+ "</div>"
					+ "<div class='container-half-width' style='width: calc(55% - 16px); background:transparent;'>"
						+ baseName
					+ "</div>"
					+ "<div class='container-quarter-width' style='padding:auto 0; margin:auto 0; width:20%; text-align:center; background:transparent;'>"
					+ (name.equals(deleteConfirmationName)
							?"<div class='square-button big' id='DELETE_CHARACTER_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDeleteConfirm()+"</div></div>"
							:"<div class='square-button big' id='DELETE_CHARACTER_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div></div>")
					+ "</div>"
				+ "</div>";
	}
	
	
	public static final DialogueNode OPTIONS = new DialogueNode("Options", "Options", true) {
		
		@Override
		public String getContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
					+ "<b>Светлая/Темная тема:</b>"
					+ "<br/>Это переключает основной цвет между светлой и темной темой. (В разработке!)"
					+ "</p>"
					
					+"<p>"
					+ "<b>Размер текста:</b><br/>"
					+ "Это изменяет базовый размер шрифта игры. В настоящее время это влияет только на размер текста в диалогах, но в будущем я расширю его, чтобы включить каждый элемент окон.<br/>"
					+ "Минимальный размер текста: "+Game.FONT_SIZE_MINIMUM+". Стандартный размер текста "+Game.FONT_SIZE_NORMAL+". Максимальный размер текста "+Game.FONT_SIZE_HUGE+".<br/>"
					+ "Текущий размер текста: "+Main.getProperties().fontSize+"."
					+ "</p>"

					+"<p>"
					+ "<b>Затухание:</b>"
					+ "<br/>Эта опция отвечает за затухание основной части текста при каждом появлении новой сцены."
					+ " Хотя оно делает переходы между сценами немного красивее, по умолчанию он отключен, так как может вызывать раздражающие задержки на экранах инвентаря."
					+ "</p>"

					+"<p>"
					+ "<b>Сложность (Значение: "+Main.getProperties().difficultyLevel.getName()+"):</b>");
			
			for(DifficultyLevel dl : DifficultyLevel.values()) {
				UtilText.nodeContentSB.append("<br/>"+(
						Main.getProperties().difficultyLevel==dl
							?"<b style='color:"+dl.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(dl.getName())+"</b> "+dl.getDescription()
							:"<span style='color:"+dl.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(dl.getName())+"</span> [style.colourDisabled("+dl.getDescription()+")]")
						 );
			}

			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Клавиши", "Откройте страницу клавиш, где вы можете настроить все привязки клавиш в игре.", KEYBINDS);
				
			} else if (index == 2) {

				if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
					return new Response("Темная тема", "Переключите тему на темный вариант.", OPTIONS){
						@Override
						public void effects() {
							Main.mainController.switchTheme();
							
						}
						};
				} else {
					return new Response("Светлая тема (WIP)", "Переключите тему на светлый вариант.<br/><br/><b>Все еще в разработке...</b>.", OPTIONS){
						@Override
						public void effects() {
							Main.mainController.switchTheme();
							
						}
					};
				}

			} else if (index == 3) {
				return new Response("Размер текста -",
						"Уменьшить размер шрифта игры. Значение по умолчанию - 18. Текущее значение: "+Main.getProperties().fontSize+".",
								OPTIONS){
					@Override
					public void effects() {
						if (Main.getProperties().fontSize > Game.FONT_SIZE_MINIMUM) {
							Main.getProperties().fontSize--;
						}
						Main.saveProperties();
						
					}
				};
			
			} else if (index == 4) {
				return new Response("Размер текста +",
						"Увеличивает размер шрифта игры. Значение по умолчанию - 18. Текущее значение: "+Main.getProperties().fontSize+".",
								OPTIONS){
					@Override
					public void effects() {
						if (Main.getProperties().fontSize < Game.FONT_SIZE_HUGE) {
							Main.getProperties().fontSize++;
						}
						Main.saveProperties();
						
					}
				};
			
			} else if (index == 5) {
				return new Response("Затухание: " + (Main.getProperties().hasValue(PropertyValue.fadeInText)
						? "[style.boldGood(Вкл)]"
						: "[style.boldBad(Выкл)]"), "Включите затухание игрового текста. Если включить эту функцию, это может вызвать небольшие лаги в экранах инвентаря.", OPTIONS) {
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.fadeInText, !Main.getProperties().hasValue(PropertyValue.fadeInText));
						Main.saveProperties();
					}
				};
				
			} else if (index == 6) {
				return new Response("Местоимения полов", "Настройте все половые местоимения и имена.", OPTIONS_PRONOUNS);
				
			} else if (index == 7) {
				return new Response("Предпочтительные единицы", "Установите желаемые единицы измерения.", UNIT_PREFERENCE);
			} else if (index == 8) {
				return new Response("Сложность: "+Main.getProperties().difficultyLevel.getName(), "Переключите сложность игры.", OPTIONS){
					@Override
					public void effects() {
						switch(Main.getProperties().difficultyLevel) {
							case NORMAL:
								Main.getProperties().difficultyLevel = DifficultyLevel.LEVEL_SCALING;
								break;
							case LEVEL_SCALING:
								Main.getProperties().difficultyLevel = DifficultyLevel.HARD;
								break;
							case HARD:
								Main.getProperties().difficultyLevel = DifficultyLevel.NIGHTMARE;
								break;
							case NIGHTMARE:
								Main.getProperties().difficultyLevel = DifficultyLevel.HELL;
								break;
							case HELL:
								Main.getProperties().difficultyLevel = DifficultyLevel.NORMAL;
								break;
						}
						Main.saveProperties();
						
						for(NPC npc : Main.game.getAllNPCs()) {
							if(!Main.game.isInCombat() || !Main.combat.getAllCombatants(false).contains(npc)) {
								npc.setMana(npc.getAttributeValue(Attribute.MANA_MAXIMUM));
								npc.setHealth(npc.getAttributeValue(Attribute.HEALTH_MAXIMUM));
							}
						}
					}
				};
			} else if (index == 0) {
				return new Response("Назад", "Вернуться в главное меню.", MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode KEYBINDS = new DialogueNode("Options", "Options", true) {

		@Override
		public String getHeaderContent() {
			return "<p>"
					+ "<table align='center'>"
					+ "<tr><th>Action</th><th>Основные клавиши</th><th>Вторичные клавиши</th></tr>"
					+ getKeybindTableRow(KeyboardAction.MENU)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.QUICKSAVE)
					+ getKeybindTableRow(KeyboardAction.QUICKLOAD)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.MENU_SELECT)
					+ getKeybindTableRow(KeyboardAction.INVENTORY)
					+ getKeybindTableRow(KeyboardAction.JOURNAL)
					+ getKeybindTableRow(KeyboardAction.MAP)
					+ getKeybindTableRow(KeyboardAction.CHARACTERS)
					+ getKeybindTableRow(KeyboardAction.ZOOM)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.MOVE_NORTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_WEST)
					+ getKeybindTableRow(KeyboardAction.MOVE_SOUTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_EAST)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.RESPOND_1)
					+ getKeybindTableRow(KeyboardAction.RESPOND_2)
					+ getKeybindTableRow(KeyboardAction.RESPOND_3)
					+ getKeybindTableRow(KeyboardAction.RESPOND_4)
					+ getKeybindTableRow(KeyboardAction.RESPOND_5)
					+ getKeybindTableRow(KeyboardAction.RESPOND_6)
					+ getKeybindTableRow(KeyboardAction.RESPOND_7)
					+ getKeybindTableRow(KeyboardAction.RESPOND_8)
					+ getKeybindTableRow(KeyboardAction.RESPOND_9)
					+ getKeybindTableRow(KeyboardAction.RESPOND_10)
					+ getKeybindTableRow(KeyboardAction.RESPOND_11)
					+ getKeybindTableRow(KeyboardAction.RESPOND_12)
					+ getKeybindTableRow(KeyboardAction.RESPOND_13)
					+ getKeybindTableRow(KeyboardAction.RESPOND_14)
					+ getKeybindTableRow(KeyboardAction.RESPOND_0)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.RESPOND_NEXT_PAGE)
					+ getKeybindTableRow(KeyboardAction.RESPOND_PREVIOUS_PAGE)

					+ getKeybindTableRow(KeyboardAction.RESPOND_NEXT_TAB)
					+ getKeybindTableRow(KeyboardAction.RESPOND_PREVIOUS_TAB)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.MOVE_RESPONSE_CURSOR_NORTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_RESPONSE_CURSOR_WEST)
					+ getKeybindTableRow(KeyboardAction.MOVE_RESPONSE_CURSOR_SOUTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_RESPONSE_CURSOR_EAST)
					+ "</table>"
					+ "</p>";
		}
		
		@Override
		public String getContent(){
			return "";
		}

		ArrayList<Properties> presets;

		private void loadPresets() {
			presets = new ArrayList<>();

			// Load all text files in the folder as properties
			File presetFolder = new File("res/keybinds");
			if (presetFolder.exists() && presetFolder.isDirectory()) {
				for (File f : presetFolder.listFiles((dir, name) -> name.endsWith(".txt"))) {
					try (FileInputStream input = new FileInputStream(f)) {
						Properties preset = new Properties();
						preset.load(input);
						presets.add(preset);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				presetFolder.mkdirs();
			}
		}

		private void loadPreset(Properties preset) {
			// Clear existing mappings
			Main.getProperties().hotkeyMapPrimary.clear();
			Main.getProperties().hotkeyMapSecondary.clear();

			// Create a key mapping for every action contained in the given property
			for (KeyboardAction ka : KeyboardAction.values()) {
				if (preset.containsKey(ka.name())) {
					String[] keys = preset.getProperty(ka.name()).split(" or ");
					Main.getProperties().hotkeyMapPrimary.put(ka, KeyCodeWithModifiers.fromString(keys[0]));
					if (keys.length == 2) Main.getProperties().hotkeyMapSecondary.put(ka, KeyCodeWithModifiers.fromString(keys[1]));
				}
			}
		}

		private void savePreset(int index) {
			// Create new properties containing current key mappings
			Properties preset = new Properties();
			preset.setProperty("NAME", "Custom " + index);
			preset.setProperty("DESCRIPTION", "Пере-применить сохраненные ранее клавиши.");

			for (Map.Entry<KeyboardAction, KeyCodeWithModifiers> e : Main.getProperties().hotkeyMapPrimary.entrySet())
				if (e.getValue() != null)
					preset.setProperty(e.getKey().name(), e.getValue().toString());

			for (Map.Entry<KeyboardAction, KeyCodeWithModifiers> e : Main.getProperties().hotkeyMapSecondary.entrySet()) {
				if (e.getValue() != null) {
					// Write or append to existing entry
					String primary = preset.getProperty(e.getKey().name());
					primary = primary == null ? e.getValue().toString() : primary + " или " + e.getValue().toString();
					preset.setProperty(e.getKey().name(), primary);
				}
			}

			// Write properties to file
			try (FileOutputStream output = new FileOutputStream("res/keybinds/custom" + index + ".txt")) {
				preset.store(output, "");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			// Load the presets if uninitialized
			if (presets == null) loadPresets();

			if (index == 0) {
				return new Response("Назад", "Вернуться в меню настроек.", OPTIONS);
				
			} else if (index <= presets.size()) {
				Properties preset = presets.get(index - 1);
				return new Response(preset.getProperty("NAME", "Custom " + index), preset.getProperty("DESCRIPTION", "Reapply your previously saved key bindings."), KEYBINDS) {
					@Override
					public void effects() {
						loadPreset(preset);
						Main.saveProperties();
					}
				};
				
			} else if (index == 14) {
				return new Response("Сохранить предустановку",
						"Сохраните текущие привязки клавиш в файле. Если вы хотите удалить сохраненные пресеты, перейдите в папку 'res/keybinds' и удалите ненужные файлы .txt."
								+ " (Они перестанут отображаться в этом списке после перезапуска игры.)",
						KEYBINDS) {
					@Override
					public void effects() {
						savePreset(presets.size() - 2);
						loadPresets();
					}
				};
			}
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};

	private static String getKeybindTableRow(KeyboardAction action) {
		return "<tr>"
				+ "<td>"
				+ action.getName()
				+ "</td>"
				+ "<td style='min-width:160px;'>"
				+ "<div class='bindingButton"
				+ (Main.mainController.getActionToBind() == action
						&& Main.mainController.isPrimaryBinding() ? " active" : "")
				+ "' id='KB_PRIMARY_"
				+ action
				+ "'>"
				+ (Main.getProperties().hotkeyMapPrimary.get(action) == null ? "<span class='option-disabled'>-</span>" : Main.getProperties().hotkeyMapPrimary.get(action).getFullName())
				+ "</div>"
				+ "<div class='bindingClearButton"
				+ (Main.getProperties().hotkeyMapPrimary.get(action) == null ? " empty" : "")
				+ "' id='KB_PRIMARY_CLEAR_"
				+ action
				+ "'><b>x</b></div>"
				+ "</td>"
				+ "<td style='min-width:160px;'>"
				+ "<div class='bindingButton"
				+ (Main.mainController.getActionToBind() == action
						&& !Main.mainController.isPrimaryBinding() ? " active" : "")
				+ "' id='KB_SECONDARY_"
				+ action
				+ "'>"
				+ (Main.getProperties().hotkeyMapSecondary.get(action) == null ? "<span class='option-disabled'>-</span>" : Main.getProperties().hotkeyMapSecondary.get(action).getFullName())
				+ "</div>"
				+ "<div class='bindingClearButton"
				+ (Main.getProperties().hotkeyMapSecondary.get(action) == null ? " empty" : "")
				+ "' id='KB_SECONDARY_CLEAR_"
				+ action
				+ "'><b>x</b></div>"
				+ "</td>"
				+ "</tr>";
	}
	
	public static final DialogueNode OPTIONS_PRONOUNS = new DialogueNode("Options", "Options", true) {

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
						+ "<h5 style='text-align:center;'>Глобальные имена полов:</h5>"
						+ "<table align='center'>"
							+ "<tr>"
							+ "<th>Части тела</th>"
								+ "<th style='color:"+PresetColour.MASCULINE.toWebHexString()+";'>Мужественный</th>"
								+ "<th style='color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>Неопределенный</th>"
								+ "<th style='color:"+PresetColour.FEMININE.toWebHexString()+";'>Женственная</th>"
							+ "</tr>");
			
			for(GenderNames gn : GenderNames.values()) {
				sb.append(getGenderNameTableRow(gn));
			}
							
			sb.append("</table>"
					+ "</p>"
					
					+ "<p>"
						+ "<h5 style='text-align:center;'>Особые местоимения игрока:</h5>"
						+ "<table align='center'>"
							+ "<tr>"
								+ "<th>Местоимения</th>"
								+ "<th style='color:"+PresetColour.MASCULINE.toWebHexString()+";'>Мужественный</th>"
								+ "<th style='color:"+PresetColour.FEMININE.toWebHexString()+";'>Женственная</th>"
							+ "</tr>"
							+ getPronounTableRow(GenderPronoun.NOUN)
							+ getPronounTableRow(GenderPronoun.YOUNG_NOUN)
							+ getPronounTableRow(GenderPronoun.SECOND_PERSON)
							+ getPronounTableRow(GenderPronoun.THIRD_PERSON)
							+ getPronounTableRow(GenderPronoun.POSSESSIVE_BEFORE_NOUN)
							+ getPronounTableRow(GenderPronoun.POSSESSIVE_ALONE)
						+ "</table>"
					+ "</p>"
					+ "<h5 style='text-align:center;'><span style='color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>Неопределенные тела</span> (опция 3)</h5>"
					+ "<p>"
					+ "<b style='color:"+PresetColour.FEMININE.toWebHexString()+";'>Жественная:</b> Относится как к <b style='color:"+PresetColour.FEMININE.toWebHexString()+";'>женщине</b>.<br/>"
					+ "<b style='color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>Женственная одежда:</b> Отношение связано с женственностью одежды."
							+ " Если одежда нейтральная, относится как к <b style='color:"+PresetColour.FEMININE.toWebHexString()+";'>женщине</b>.<br/>"
					+ "<b style='color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>Мужественная одежда:</b> Отношение связано с жентсвенностью одежды."
							+ " Если одежда нейтральная, относится как к <b style='color:"+PresetColour.MASCULINE.toWebHexString()+";'>мужчине</b>.<br/>"
					+ "<b style='color:"+PresetColour.MASCULINE.toWebHexString()+";'>Мужественность:</b> Относится как к <b style='color:"+PresetColour.MASCULINE.toWebHexString()+";'>мужчине</b>.<br/>"
					+ "</p>");
			
			return sb.toString();	
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Сохранить", "Сохраните все местоимения, которые отображаются в данный момент.") {
					@Override
					public void effects() {
						for(GenderNames gn : GenderNames.values()) {
							Main.getProperties().genderNameMale.put(gn, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_MASCULINE_" + gn +"').value")).toLowerCase());
							Main.getProperties().genderNameNeutral.put(gn, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_ANDROGYNOUS_" + gn +"').value")).toLowerCase());
							Main.getProperties().genderNameFemale.put(gn, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_FEMININE_" + gn +"').value")).toLowerCase());
						}
						for (GenderPronoun gp : GenderPronoun.values()) {
							Main.getProperties().genderPronounFemale.put(gp, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('feminine_" + gp +"').value")).toLowerCase());
							Main.getProperties().genderPronounMale.put(gp, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('masculine_" + gp +"').value")).toLowerCase());
						}
						Main.saveProperties();
						Main.game.flashMessage(PresetColour.GENERIC_GOOD, "Местоимения сохранены!");
					}
				};
				
			} else if (index == 2) {
				return new Response("По умолчанию", "Сбрасывает все местоимения.", OPTIONS_PRONOUNS){
					@Override
					public void effects() {
						for(GenderNames gn : GenderNames.values()) {
							Main.getProperties().genderNameMale.put(gn, gn.getMasculine());
							Main.getProperties().genderNameNeutral.put(gn, gn.getNeutral());
							Main.getProperties().genderNameFemale.put(gn, gn.getFeminine());
						}
						for (GenderPronoun gp : GenderPronoun.values()) {
							Main.getProperties().genderPronounFemale.put(gp, gp.getFeminine());
							Main.getProperties().genderPronounMale.put(gp, gp.getMasculine());
						}
						Main.saveProperties();
						
					}
				};
				
			} else if (index == 3) {
				return new Response("<span style='color:"+Main.getProperties().androgynousIdentification.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(Main.getProperties().androgynousIdentification.getName())+"</span>",
						"Переключите способ которым игра относится к неопределенным телам описанным выше.", OPTIONS_PRONOUNS){
					@Override
					public void effects() {
						switch(Main.getProperties().androgynousIdentification){
							case FEMININE:
								Main.getProperties().androgynousIdentification=AndrogynousIdentification.CLOTHING_FEMININE;
								break;
							case CLOTHING_FEMININE:
								Main.getProperties().androgynousIdentification=AndrogynousIdentification.CLOTHING_MASCULINE;
								break;
							case CLOTHING_MASCULINE:
								Main.getProperties().androgynousIdentification=AndrogynousIdentification.MASCULINE;
								break;
							case MASCULINE:
								Main.getProperties().androgynousIdentification=AndrogynousIdentification.FEMININE;
								break;
							default:
								break;
						}
						
						Main.saveProperties();
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "Вернутся в меню настроек.", OPTIONS);
				
			} else {
				return null;
			}
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getGenderNameTableRow(GenderNames name) {
		return "<tr>"
					+ "<td>"
						+ (name.isHasPenis()?"[style.colourGood(Пенис)]":"[style.colourDisabled(Пенис)]")
						+ " " + (name.isHasVagina()?"[style.colourGood(Вагина)]":"[style.colourDisabled(Вагина)]")
						+ " " + (name.isHasBreasts()?"[style.colourGood(Груди)]":"[style.colourDisabled(Груди)]")
					+ "</td>"
					+ "<td style='min-width:160px;'>"
						+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='GENDER_NAME_MASCULINE_" + name + "' value='"
						+ UtilText.parseForHTMLDisplay(Main.getProperties().genderNameMale.get(name))
						+ "'>"
						+ "</form>"
					+ "</td>"
					+ "<td style='min-width:160px;'>"
						+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='GENDER_NAME_ANDROGYNOUS_" + name + "' value='"
						+ UtilText.parseForHTMLDisplay(Main.getProperties().genderNameNeutral.get(name))
						+ "'>"
						+ "</form>"
					+ "</td>"
					+ "<td style='min-width:160px;'>"
						+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='GENDER_NAME_FEMININE_" + name + "' value='"
						+ UtilText.parseForHTMLDisplay(Main.getProperties().genderNameFemale.get(name))
						+ "'>"
						+ "</form>"
					+ "</td>"
				+ "</tr>";
	}
	
	private static String getPronounTableRow(GenderPronoun pronoun) {
		return "<tr>"
				+ "<td>"
					+ pronoun.getName()
				+ "</td>"
					+ "<td style='min-width:160px;'>"
					+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='masculine_" + pronoun + "' value='"+ UtilText.parseForHTMLDisplay(Main.getProperties().genderPronounMale.get(pronoun))+ "'>"
					+ "</form>"
				+ "</td>"
				+ "<td style='min-width:160px;'>"
					+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='feminine_" + pronoun + "' value='"+ UtilText.parseForHTMLDisplay(Main.getProperties().genderPronounFemale.get(pronoun))+ "'></form>"
				+ "</td>"
				+ "</tr>";
	}
	
	
	public static final DialogueNode PATCH_NOTES = new DialogueNode("Описание обновления", "Описание обновления", true) {
		
		@Override
		public String getContent(){
			return Main.getPatchNotes();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернутся в меню настроек.", MENU);
				
			}else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}

		@Override
		public boolean isContentParsed() {
			return false;
		}
	};
	
	public static final DialogueNode DISCLAIMER = new DialogueNode("", "", true) {
		
		@Override
		public String getContent(){
			return Main.disclaimer;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернутся в меню настроек.", MENU);
				
			}else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	
	public static final DialogueNode GENDER_PREFERENCE = new DialogueNode("Половые предпочтения", "", true) {
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
					+ "Эти опции определяют частоту встреч случайных NPC по половому признаку."
					+ " Некоторые NPC, например, случайные нападающие суккубы, имеют ограничения по полу, но ваши предпочтения будут учтены по мере возможности.<br/>"
					+ "<b>Визуальное представление шансов на встречу можно увидеть в полосках внизу каждого раздела.</b>"
					+ " (Различные оттенки каждого пола служат исключительно для распознавания в полосках и ничего кроме этого не значат.)"
					+ "<br/>"
					+ "Считается, что у персонажа есть грудь, если она не меньше чашечки АА."
					+ "</div>");
			
			UtilText.nodeContentSB.append(getGenderPreferencesPanel(PronounType.MASCULINE));
			UtilText.nodeContentSB.append(getGenderPreferencesPanel(PronounType.NEUTRAL));
			UtilText.nodeContentSB.append(getGenderPreferencesPanel(PronounType.FEMININE));
			
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 11) {
				return new Response("По умолчанию", "Восстановите все половые предпочтения до значений по умолчанию.", GENDER_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().resetGenderPreferences();
						Main.getProperties().savePropertiesAsXML();
					}
				};
			}
			return getContentOptionsResponse(responseTab, index);
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getGenderPreferencesPanel(PronounType type) {
		int count = 0;
		Colour colour = PresetColour.MASCULINE;
		switch(type) {
			case FEMININE:
				colour = PresetColour.FEMININE;
				break;
			case MASCULINE:
				colour = PresetColour.MASCULINE;
				break;
			case NEUTRAL:
				colour = PresetColour.ANDROGYNOUS;
				break;
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='text-align:center;'>"
				+ "<p><b style='color:"+type.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(type.getName())+"</b></p>");
		
		for(Gender g : Gender.values()) {
			if(g.getType()==type) {
				sb.append(
						"<div style='display:inline-block; margin:4px auto;width:100%;'>"
							+ "<div style='display:inline-block; margin:0 auto;'>"
								+ "<div style='width:140px; float:left;'><b style='color:"+colour.getShades(8)[count]+";'>" +Util.capitaliseSentence(g.getName())+"</b></div>");
				
				for(ContentPreferenceValue preference : ContentPreferenceValue.values()) {
					sb.append("<div id='"+preference+"_"+g+"' class='preference-button"+(Main.getProperties().genderPreferencesMap.get(g)==preference.getValue()?" выбрано":"")+"'>"+Util.capitaliseSentence(preference.getName())+"</div>");
				}
								
				sb.append("<p><br/>"
								+ "<span style='color:"+colour.getShades(8)[count]+";'>" +Util.capitaliseSentence(g.getName())+"s</span> имеет "
										+(g.getGenderName().isHasVagina()?"[style.colourGood(вагину)]":"нет [style.colourBad(вагины)]")+", "
										+(g.getGenderName().isHasPenis()?"[style.colourGood(пенис)]":"нет [style.colourBad(пениса)]")+", и "
										+ (g.getGenderName().isHasBreasts()?"[style.colourGood(груди)]":"нет [style.colourBad(грудей)]")+"."
								+ "</p>"
							+ "</div>"
						+ "</div>"
						+ "<hr/>");
				count++;
			}
		}
		
		sb.append(
				getGenderRepresentation()
				+"</div>");
		
		return sb.toString();
	}

	private static String getOrientationRepresentation() {
		float total=0;
		for(SexualOrientation o : SexualOrientation.values()) {
			total+=Main.getProperties().orientationPreferencesMap.get(o);
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(total==0) {
			sb.append("<div style='width:100%;height:12px;background:"+PresetColour.ANDROGYNOUS.toWebHexString()+";float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
		} else {
			sb.append("<div style='width:100%;height:12px;background:#222;float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
			for(SexualOrientation o : SexualOrientation.values()) {
				sb.append("<div style='width:" + (Main.getProperties().orientationPreferencesMap.get(o)/total) * (100) + "%; height:12px; background:"
						+ o.getColour().toWebHexString() + "; float:left; border-radius: 2;'></div>");
			}
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode ORIENTATION_PREFERENCE = new DialogueNode("Предпочтения по ориентациям", "", true) {
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
					+ "Эти опции определяют частоту встречи сексуальной ориентации случайных NPC."
					+ " Обратите внимание, что раса и женственность NPC могут влиять на их ориентацию, а некоторые NPC имеют заранее определенную ориентацию, но ваши предпочтения будут учтены по мере возможности.</br>"
					+ "<b>Визуальное представление шансов на встречу можно увидеть в столбиках внизу.</b>"
					+ " (Различные оттенки каждой ориентации служат исключительно для распознавания в полосках и ничего кроме этого не значат.)"
					+ "</div>"
		
					+ "<div class='container-full-width' style='text-align:center;'>");
			
			UtilText.nodeContentSB.append(getOrientationPreferencesPanel(SexualOrientation.ANDROPHILIC));
			UtilText.nodeContentSB.append(getOrientationPreferencesPanel(SexualOrientation.AMBIPHILIC));
			UtilText.nodeContentSB.append(getOrientationPreferencesPanel(SexualOrientation.GYNEPHILIC));

			UtilText.nodeContentSB.append(getOrientationRepresentation() + "</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 11) {
				return new Response("По умолчанию", "Восстановите все параметры ориентации до значений по умолчанию.", ORIENTATION_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().resetOrientationPreferences();
						Main.getProperties().savePropertiesAsXML();
					}
				};
			}
			return getContentOptionsResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode FETISH_PREFERENCE = new DialogueNode("Предпочтения по фетишам", "", true) {
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
							+ "Эти опции определяют вероятность того, что случайные NPC будут иметь эти фетиши и предпочтения."
							+ " Некоторые расы более склонны к определенным фетишам, но ваши предпочтения будут учтены по мере возможности.<br/>"
							+ " Настройки контента позволяют включать/выключать соответствующие фетиши."
							+ "</div>"
							
							+ "<div class='container-full-width' style='text-align:center;'>");
			for(AbstractFetish fetish : Fetish.getAllFetishes()) {
				if(fetish.getFetishesForAutomaticUnlock().isEmpty()) {
					UtilText.nodeContentSB.append(getFetishPreferencesPanel(fetish));
				}
			}
			
			UtilText.nodeContentSB.append("</div>");
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 11) {
				return new Response("По умолчанию", "Сбросьте все фетиш-предпочтения к настройкам по умолчанию.", FETISH_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().resetFetishPreferences();
						Main.getProperties().savePropertiesAsXML();
					}
				};
			}
			return getContentOptionsResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};

	private static String getOrientationPreferencesPanel(SexualOrientation orient) {
		Colour colour = orient.getColour();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div style='display:inline-block; margin:4px auto;width:100%;'>"
				+ "<div style='display:inline-block; margin:0 auto;'>"
					+ "<div style='width:140px; float:left;'><b style='color:"+colour.toWebHexString()+";'>" +Util.capitaliseSentence(orient.getName())+"</b></div>");
		
		for(SexualOrientationPreference preference : SexualOrientationPreference.values()) {
			sb.append("<div id='"+preference+"_"+orient+"' class='preference-button"+(Main.getProperties().orientationPreferencesMap.get(orient)==preference.getValue()?" выбрано":"")+"'>"
							+Util.capitaliseSentence(preference.getName())
						+"</div>");
		}
						
		sb.append("</div>"
				+ "</div>"
				+ "<hr></hr>");
		
		return sb.toString();
	}

	public static String getInformationDiv(String id, TooltipInformationEventListener information) {
		Game.informationTooltips.put(id, information);
		return "<div class='title-button no-select' id='"+id+"' style='position:relative; float:left; background:transparent; padding:0; margin:0;'>"
					+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()
				+"</div>";
	}
	
	private static String getFetishPreferencesPanel(AbstractFetish fetish) {
		StringBuilder sb = new StringBuilder();
		
		Colour highlightColour = FetishPreference.valueOf(Main.getProperties().fetishPreferencesMap.get(fetish)).getColour();
		
		sb.append("<div style='display:inline-block; margin:4px auto;width:100%;'>"
				+ "<div style='display:inline-block; margin:0 auto;'>"
				+ getInformationDiv(fetish.getId()+"_INFO", new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(fetish.getName(Main.game.getPlayer())), fetish.getDescription(null)))
				+ "<div style='width:150px; float:left;'><b style='color:"+highlightColour.toWebHexString()+";'>"+Util.capitaliseSentence(fetish.getName(null))+"</b></div>");
		
		for(FetishPreference preference : FetishPreference.values()) {
			String disabledMsg=null;
			if(!fetish.isContentEnabled()) {
				if (Fetish.FETISH_SIZE_QUEEN.equals(fetish)) {
					disabledMsg = "Разница в размерах при проникновении";
				} else if (Fetish.FETISH_NON_CON_DOM.equals(fetish) || Fetish.FETISH_NON_CON_SUB.equals(fetish)) {
					disabledMsg = "Без согласия";
				} else if (Fetish.FETISH_INCEST.equals(fetish)) {
					disabledMsg = "Инцест";
				} else if (Fetish.FETISH_LACTATION_SELF.equals(fetish) || Fetish.FETISH_LACTATION_OTHERS.equals(fetish)) {
					disabledMsg = "Лактация";
				} else if (Fetish.FETISH_ANAL_RECEIVING.equals(fetish) || Fetish.FETISH_ANAL_GIVING.equals(fetish)) {
					disabledMsg = "Анальные действия";
				} else if (Fetish.FETISH_FOOT_RECEIVING.equals(fetish) || Fetish.FETISH_FOOT_GIVING.equals(fetish)) {
					disabledMsg = "Фут-фетиш";
				} else if (Fetish.FETISH_ARMPIT_RECEIVING.equals(fetish) || Fetish.FETISH_ARMPIT_GIVING.equals(fetish)) {
					disabledMsg = "Действия с подмышками";
				} else {
					disabledMsg = "Неопределенное содержание";
				}
			}
			if(disabledMsg!=null) {
				// Disabled fetishes to default, the fetish won't be a valid option for the generator anyway
				Main.getProperties().fetishPreferencesMap.put(fetish, fetish.getFetishPreferenceDefault().getValue());
				sb.append("<div style='display:inline-block;'><span class='option-disabled'>Фетиш принудительно выключен из-за "+disabledMsg+" настройки!</span></div>");
				break;
			} else {
				sb.append("<div id='"+preference+"_"+Fetish.getIdFromFetish(fetish)+"' class='preference-button"+(Main.getProperties().fetishPreferencesMap.get(fetish)==preference.getValue()?" выбрано":"")+"'"
						+ " style='width:70px;'"
						+ ">"
							+Util.capitaliseSentence(preference.getName())
						+"</div>");
			}
		}
		
		sb.append("</div>"
				+ "</div>"
				+ "<hr></hr>");
		
		return sb.toString();
	}
	
	private static String getGenderRepresentation() {
		
		float total=0;
		for(Gender g : Gender.values()) {
			total+=Main.getProperties().genderPreferencesMap.get(g);
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(total==0) {
			sb.append("<div style='width:100%;height:12px;background:"+PresetColour.FEMININE.getShades()[3]+";float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
		} else {
			sb.append("<div style='width:100%;height:12px;background:#222;float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
			int f=0, m=0, n=0;
			for(Gender g : Gender.values()) {
				switch(g.getType()) {
					case MASCULINE:
						if(Main.getProperties().genderPreferencesMap.get(g)>0) {
							sb.append("<div style='width:calc(" + (Main.getProperties().genderPreferencesMap.get(g)/total) * (100) + "% - 1px); height:12px;"
									+ " background:"+PresetColour.MASCULINE.getShades(8)[m] + "; float:left; border-radius: 2;'></div>");
							sb.append("<div style='width:1px; height:12px; background:#000; float:left; border-radius: 2;'></div>");
						}
						m++;
						break;
					case NEUTRAL:
						if(Main.getProperties().genderPreferencesMap.get(g)>0) {
							sb.append("<div style='width:calc(" + (Main.getProperties().genderPreferencesMap.get(g)/total) * (100) + "% - 1px); height:12px;"
									+ " background:"+PresetColour.ANDROGYNOUS.getShades(8)[n] + "; float:left; border-radius: 2;'></div>");
							sb.append("<div style='width:1px; height:12px; background:#000; float:left; border-radius: 2;'></div>");
						}
						n++;
						break;
					case FEMININE:
						if(Main.getProperties().genderPreferencesMap.get(g)>0) {
							sb.append("<div style='width:calc(" + (Main.getProperties().genderPreferencesMap.get(g)/total) * (100) + "% - 1px); height:12px;"
									+ " background:"+PresetColour.FEMININE.getShades(8)[f] + "; float:left; border-radius: 2;'></div>");
							sb.append("<div style='width:1px; height:12px; background:#000; float:left; border-radius: 2;'></div>");
						}
						f++;
						break;
					default:
						break;
				}
			}
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode AGE_PREFERENCE = new DialogueNode("Предпочтения по возрасту", "", true) {
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
					+ "Эти опции определяют возраст случайных NPC в зависимости от их женственности."
					+ " Некоторые NPC, такие как демоны и гарпии, могут казаться моложе, чем они есть на самом деле, но ваши предпочтения будут учтены по мере возможности.<br/>"
					+ "<b>Визуальное представление возрастных шансов можно увидеть в виде полос в нижней части каждого раздела.</b>"
					+ "</div>");
			
			UtilText.nodeContentSB.append(getAgePreferencesPanel(PronounType.MASCULINE));
			UtilText.nodeContentSB.append(getAgePreferencesPanel(PronounType.NEUTRAL));
			UtilText.nodeContentSB.append(getAgePreferencesPanel(PronounType.FEMININE));
			
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 11) {
				return new Response("По умолчанию", "Восстановите все возрастные предпочтения до значений по умолчанию.", AGE_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().resetAgePreferences();
						Main.getProperties().savePropertiesAsXML();
					}
				};
			}
			return getContentOptionsResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getAgePreferencesPanel(PronounType type) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='text-align:center;'>"
				+ "<p><b style='color:"+type.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(type.getName())+"</b></p>");
		
		int i=AgeCategory.values().length-1;
		for(AgeCategory ageCat : AgeCategory.values()) {
			sb.append(
					"<div style='display:inline-block; margin:4px auto;width:100%;'>"
						+ "<div style='display:inline-block; margin:0 auto;'>"
							+ "<div style='width:140px; float:left;'><b style='color:"+type.getColour().getShades(AgeCategory.values().length)[i]+";'>" +Util.capitaliseSentence(ageCat.getName())+"</b></div>");
			
			for(ContentPreferenceValue preference : ContentPreferenceValue.values()) {
				sb.append(
						"<div id='"+type+"_"+preference+"_"+ageCat+"' class='preference-button"+(Main.getProperties().agePreferencesMap.get(type).get(ageCat)==preference.getValue()?" выбрано":"")+"'>"
								+Util.capitaliseSentence(preference.getName())
						+"</div>");
			}
							
			sb.append("</div>"
					+ "</div>"
					+ "<hr/>");
			i--;
		}
		
		sb.append(
				getAgeRepresentation(type)
				+"</div>");
		
		return sb.toString();
	}
	
	private static String getAgeRepresentation(PronounType type) {
		
		float total=0;
		for(AgeCategory ageCat : AgeCategory.values()) {
			total+=Main.getProperties().agePreferencesMap.get(type).get(ageCat);
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(total==0) {
			sb.append("<div style='width:100%;height:12px;background:"+type.getColour().getShades()[3]+";float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
		} else {
			sb.append("<div style='width:100%;height:12px;background:#222;float:left;margin:4vw 0 0 0;border-radius: 2px;'>");

			int i=(AgeCategory.values().length*2)-1;
			for(AgeCategory ageCat : AgeCategory.values()) {
				if(Main.getProperties().agePreferencesMap.get(type).get(ageCat)>0) {
					sb.append("<div style='width:calc(" + (Main.getProperties().agePreferencesMap.get(type).get(ageCat)/total) * (100) + "% - 1px); height:12px;"
							+ " background:"+type.getColour().getShades(AgeCategory.values().length*2)[i] + "; float:left; border-radius: 2;'></div>");
					sb.append("<div style='width:1px; height:12px; background:#000; float:left; border-radius: 2;'></div>");
				}
				i--;
				i--;
			}
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode FURRY_PREFERENCE = new DialogueNode("Предпочтения по фурри", "", true) {
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
						+ "Эти параметры определяют количество фурри-контента, который вы встретите в игре."
						+ " Опция `Встречи с людьми` определяет, какова вероятность того, что случайные NPC окажутся полностью людьми."
						+ " <b>В данный момент эти опции затрагивают только случайных NPC, но я сделаю все возможное, чтобы добавить уменьшенные фурри версии всех основных NPC!</b>"
						
						+ "<br/>[style.italicsGood(Наведите курсор на кнопки, чтобы узнать, что означает каждый вариант!)]"
						
						+ "<br/>Обратите внимание, что некоторые расы, такие как демоны и гарпии, ограничены в выборе фурри предпочтений."
					+ "</div>"
							
					+ "<span style='height:16px;width:800px;float:left;'></span>");
					
			UtilText.nodeContentSB.append("<div class='container-full-width'>");
					
				UtilText.nodeContentSB.append("<div class='container-half-width inner' style='width:31.3%; margin:1%; padding:1%;'>");
					UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RACE_HUMAN.toWebHexString()+"; float:left; width:100%; text-align:center;'>Частота появления людей</b>");
					UtilText.nodeContentSB.append("<div style='display:inline-block; padding-left:5%; width:100%;'>");
							UtilText.nodeContentSB.append(getSpawnRateDiv(
											"HUMAN_SPAWN_RATE",
											PresetColour.RACE_HUMAN,
											Main.getProperties().humanSpawnRate+"%",
											Main.getProperties().humanSpawnRate,
											0,
											100));
					UtilText.nodeContentSB.append("</div>");
				UtilText.nodeContentSB.append("</div>");

				UtilText.nodeContentSB.append("<div class='container-half-width inner' style='width:31.3%; margin:1%; padding:1%;'>");
					UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RACE_CENTAUR.toWebHexString()+"; float:left; width:100%; text-align:center;'>Частота появления тавров</b>");
					UtilText.nodeContentSB.append("<div style='display:inline-block; padding-left:5%; width:100%;'>");
							UtilText.nodeContentSB.append(getSpawnRateDiv(
											"TAUR_SPAWN_RATE",
											PresetColour.RACE_CENTAUR,
											Main.getProperties().taurSpawnRate+"%",
											Main.getProperties().taurSpawnRate,
											0,
											100));
					UtilText.nodeContentSB.append("</div>");
				UtilText.nodeContentSB.append("</div>");

				UtilText.nodeContentSB.append("<div class='container-half-width inner' style='width:31.3%; margin:1%; padding:1%;'>");
					UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RACE_HALF_DEMON.toWebHexString()+"; float:left; width:100%; text-align:center;'>Частота появления полу-демонов</b>");
					UtilText.nodeContentSB.append("<div style='display:inline-block; padding-left:5%; width:100%;'>");
							UtilText.nodeContentSB.append(getSpawnRateDiv(
											"HALF_DEMON_SPAWN_RATE",
											PresetColour.RACE_HALF_DEMON,
											Main.getProperties().halfDemonSpawnRate+"%",
											Main.getProperties().halfDemonSpawnRate,
											0,
											100));
					UtilText.nodeContentSB.append("</div>");
				UtilText.nodeContentSB.append("</div>");
			UtilText.nodeContentSB.append("</div>");
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.RACE_CENTAUR, "Таурическая-фурри верхня часть тела", "Установите насколько фурри будет верхняя часть тела тавров."));
			UtilText.nodeContentSB.append(
					(Main.getProperties().taurFurryLevel==2
						?"<div id='TAUR_FURRY_LIMIT_"+2+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+FurryPreference.MINIMUM.getColour().toWebHexString()+";'>"
							+ FurryPreference.MINIMUM.getName()
							+ "</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+2+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+ "[style.colourDisabled("+FurryPreference.MINIMUM.getName()+")]"
							+ "</div>")
					+(Main.getProperties().taurFurryLevel==1
						?"<div id='TAUR_FURRY_LIMIT_"+1+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+FurryPreference.HUMAN.getColour().toWebHexString()+";'>"
							+ "Человек"
							+ "</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+1+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+ "[style.colourDisabled(Человек)]"
							+ "</div>")
					+(Main.getProperties().taurFurryLevel==0
						?"<div id='TAUR_FURRY_LIMIT_"+0+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"
							+ "Нетронутый"
							+ "</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+0+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+ "[style.colourDisabled(Нетронутый)]"
							+ "</div>")
					
					+(Main.getProperties().taurFurryLevel==5
						?"<div id='TAUR_FURRY_LIMIT_"+5+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+FurryPreference.MAXIMUM.getColour().toWebHexString()+";'>"
							+ FurryPreference.MAXIMUM.getName()
							+ "</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+5+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+ "[style.colourDisabled("+FurryPreference.MAXIMUM.getName()+")]"
							+ "</div>")
					+(Main.getProperties().taurFurryLevel==4
						?"<div id='TAUR_FURRY_LIMIT_"+4+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+FurryPreference.NORMAL.getColour().toWebHexString()+";'>"
							+ FurryPreference.NORMAL.getName()
							+ "</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+4+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+ "[style.colourDisabled("+FurryPreference.NORMAL.getName()+")]"
							+ "</div>")
					+(Main.getProperties().taurFurryLevel==3
						?"<div id='TAUR_FURRY_LIMIT_"+3+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+FurryPreference.REDUCED.getColour().toWebHexString()+";'>"
							+ FurryPreference.REDUCED.getName()
							+ "</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+3+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+ "[style.colourDisabled("+FurryPreference.REDUCED.getName()+")]"
							+ "</div>"));
			UtilText.nodeContentSB.append("</div></div>");
			
			
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align: center;'>"
												+ "<div style='display:inline-block; margin:4px auto;'>"
													+"<div style='float:left; text-align:right; margin-right:16px;'>"
														+ "<b>Установить все фурри предпочтения:</b>"
													+ "</div>");
			for(FurryPreference fp : FurryPreference.values()) {
				UtilText.nodeContentSB.append("<div id='ALL_FURRY_"+fp+"' class='normal-button' style='width:80px; margin:0 2px;'>"+fp.getName()+"</div>");
			}
			UtilText.nodeContentSB.append("</div>"
												+ "<div style='display:inline-block; margin:4px auto;'>"
													+"<div style='float:left; text-align:right; margin-right:16px;'>"
														+ "<b>Установить все частоты появления:</b>"
													+ "</div>");
			for(SubspeciesPreference sp : SubspeciesPreference.values()) {
				UtilText.nodeContentSB.append("<div id='ALL_SPAWN_"+sp+"' class='normal-button' style='width:80px; margin:0 2px;'>"+Util.capitaliseSentence(sp.getName())+"</div>");
			}
			UtilText.nodeContentSB.append("</div>"
											+"</div>");
												
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align: center;'>"
											+"<div class='container-full-width' style='text-align:center; background:"+getEntryBackgroundColour(false)+";'>"
												+"<div class='container-full-width' style='text-align:center; width:calc(60% - 16px);background:transparent; margin:0 0 0 40%; padding:0;'>"
													+ "<b style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+"; float:left; width:50%; text-align:center;'>Фурри предпочтения</b>"
													+ "<b style='color:"+PresetColour.BASE_YELLOW_LIGHT.toWebHexString()+"; float:left; width:50%; text-align:center;'>Частота появления</b>"
												+ "</div>"
											+ "</div>");

			int i=0;
			for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
				if(subspecies.isDisplayedInFurryPreferences()) {
					UtilText.nodeContentSB.append(getSubspeciesPreferencesPanel(subspecies, i%2==0));
					i++;
				}
			}
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==11) {
				return new Response("По умолчанию", "Сбросьте все настройки фурри и появлений до значений по умолчанию.", FURRY_PREFERENCE) {
					@Override
					public void effects() {
						for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
							Main.getProperties().setFeminineFurryPreference(subspecies, subspecies.getDefaultFemininePreference());
							Main.getProperties().setMasculineFurryPreference(subspecies, subspecies.getDefaultMasculinePreference());

							Main.getProperties().setFeminineSubspeciesPreference(subspecies, subspecies.getSubspeciesPreferenceDefault());
							Main.getProperties().setMasculineSubspeciesPreference(subspecies, subspecies.getSubspeciesPreferenceDefault());
						}
						Main.saveProperties();
					}
				};
			}
			return getContentOptionsResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};

	private static String getSpawnRateDiv(String id, Colour colour, String valueDisplay, int value, int minimum, int maximum) {
		StringBuilder contentSB = new StringBuilder();

		contentSB.append("<div class='container-full-width' style='padding:0; margin:2px 0;'>");
		
			contentSB.append(
					"<div id='"+id+"_INCREASE_LARGE' class='normal-button"+(value==maximum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
							+ (value==maximum?"[style.boldDisabled(+)]":"[style.boldGood(+)]")
					+ "</div>"
					+ "<div id='"+id+"_INCREASE' class='normal-button"+(value==maximum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
							+ (value==maximum?"[style.boldDisabled(+)]":"[style.boldMinorGood(+)]")
					+ "</div>"
					+ "<div class='container-full-width' style='text-align:center; width:40%; float:right; margin:0;'>"
						+ "<b>"+valueDisplay+"</b>"
					+ "</div>"
					+ "<div id='"+id+"_DECREASE' class='normal-button"+(value==minimum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
						+ (value==minimum?"[style.boldDisabled(-)]":"[style.boldMinorBad(-)]")
					+ "</div>"
					+ "<div id='"+id+"_DECREASE_LARGE' class='normal-button"+(value==minimum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
					+ (value==minimum?"[style.boldDisabled(-)]":"[style.boldBad(-)]")
				+ "</div>");
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	private static String getEntryBackgroundColour(boolean alternative) {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			if(alternative) {
				return "#d9d9d9";
			}
			return "#dddddd";
		} else {
			if(alternative) {
				return "#222222";
			}
			return "#1f1f1f";  
		}
	}
	
	private static String getSubspeciesPreferencesPanel(AbstractSubspecies s, boolean altColour) {
		StringBuilder sb = new StringBuilder();
		String baseStyle = "max-width:30px; width:14%; margin:0 1%; padding:0;";
		String subspeciesId = Subspecies.getIdFromSubspecies(s);
		
		sb.append("<div class='container-full-width' style='text-align:center; background:"+getEntryBackgroundColour(altColour)+"; padding:0; margin:0 0 6px 0; border-left:solid 4px "+s.getColour(null).toWebHexString()+";'>");
		
			// Feminine:
			sb.append("<div class='container-full-width' style='text-align:center; width:40%; background:transparent; margin:0; padding:0;'>"
						+"<b style='color:"+PresetColour.FEMININE.toWebHexString()+"; float:left; width:100%; text-align:center;'>" +Util.capitaliseSentence(s.getSingularFemaleName(null))+"</b>"
					+"</div>");
			
			sb.append("<div class='container-full-width' style='text-align:center; width:30%; background:transparent; margin:2px 0; padding:0;'>");

				for(FurryPreference preference : FurryPreference.values()) {
					sb.append("<div id='FEMININE_"+preference+"_"+subspeciesId+"' class='square-button small"+(!s.isFurryPreferencesEnabled()?" disabled":"")
								+(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(s)==preference && s.isFurryPreferencesEnabled()
									?" selected' style='"+baseStyle+" border-color:"+preference.getColour().toWebHexString()+";'><div class='square-button-content'>"+preference.getSVGImage(false)+"</div></div>"
									:"' style='"+baseStyle+"'><div class='square-button-content'>"+preference.getSVGImage(true)+"</div></div>"));
				}
				sb.append("</div>");
				sb.append("<div class='container-full-width' style='text-align:center; width:30%; background:transparent; margin:2px 0; padding:0;'>");
				for(SubspeciesPreference preference : SubspeciesPreference.values()) {
					sb.append("<div id='FEMININE_SPAWN_"+preference+"_"+subspeciesId+"' class='square-button small"+(!s.isSpawnPreferencesEnabled()?" disabled":"")
								+(Main.getProperties().getSubspeciesFemininePreferencesMap().get(s)==preference && s.isSpawnPreferencesEnabled()
									?" selected' style='"+baseStyle+" border-color:"+PresetColour.FEMININE_PLUS.toWebHexString()+";'><div class='square-button-content'>"+preference.getSVGImage(false)+"</div></div>"
									:"' style='"+baseStyle+"'><div class='square-button-content'>"+preference.getSVGImage(true)+"</div></div>"));
				}
				
			sb.append("</div>");
			
			// Masculine:
			sb.append("<div class='container-full-width' style='text-align:center; width:40%; background:transparent; margin:0; padding:0;'>"
					+"<b style='color:"+PresetColour.MASCULINE.toWebHexString()+"; float:left; width:100%; text-align:center;'>" +Util.capitaliseSentence(s.getSingularMaleName(null))+"</b>"
				+"</div>");
		
			sb.append("<div class='container-full-width' style='text-align:center; width:30%; background:transparent; margin:2px 0; padding:0;'>");
			
				for(FurryPreference preference : FurryPreference.values()) {
					sb.append("<div id='MASCULINE_"+preference+"_"+subspeciesId+"' class='square-button small"+(!s.isFurryPreferencesEnabled()?" disabled":"")
								+(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(s)==preference && s.isFurryPreferencesEnabled()
									?" selected' style='"+baseStyle+" border-color:"+preference.getColour().toWebHexString()+";'><div class='square-button-content'>"+preference.getSVGImage(false)+"</div></div>"
									:"' style='"+baseStyle+"'><div class='square-button-content'>"+preference.getSVGImage(true)+"</div></div>"));
				}
			sb.append("</div>");
			sb.append("<div class='container-full-width' style='text-align:center; width:30%; background:transparent; margin:2px 0; padding:0;'>");
				for(SubspeciesPreference preference : SubspeciesPreference.values()) {
					sb.append("<div id='MASCULINE_SPAWN_"+preference+"_"+subspeciesId+"' class='square-button small"+(!s.isSpawnPreferencesEnabled()?" disabled":"")
								+(Main.getProperties().getSubspeciesMasculinePreferencesMap().get(s)==preference && s.isSpawnPreferencesEnabled()
									?" selected' style='"+baseStyle+" border-color:"+PresetColour.MASCULINE_PLUS.toWebHexString()+";'><div class='square-button-content'>"+preference.getSVGImage(false)+"</div></div>"
									:"' style='"+baseStyle+"'><div class='square-button-content'>"+preference.getSVGImage(true)+"</div></div>"));
				}
				
			sb.append("</div>");

			sb.append("<div class='title-button no-select' id='SUBSPECIES_PREFERENCE_INFO_"+subspeciesId+"' style='position:absolute; margin:0; padding:0; left:1%; right:auto; top:auto; bottom:auto;'>"
							+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()
						+"</div>");
		sb.append("</div>");
		
		return sb.toString();
	}

	public static final DialogueNode UNIT_PREFERENCE = new DialogueNode("Единицы измерения", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
							+ "Эти параметры определяют единицы измерения, используемые в игре."
							+ " При использовании автоматического определения будет предпринята попытка определить правильные настройки отталкиваясь от языка вашей системы."
							+ " <br/><b>Переопределение любой опции отключает автоопределение.</b>"
							+ "</div>"

							+ "<span style='height:16px;width:800px;float:left;'></span>");

			UtilText.nodeContentSB.append(getContentPreferenceDiv("AUTO_LOCALE",
					PresetColour.BASE_BLUE_LIGHT,
					"Авто",
					"Если эта опция включена, используется системный язык. В противном случае применяются следующие параметры.",
					Main.getProperties().hasValue(PropertyValue.autoLocale)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv("METRIC_SIZES",
					PresetColour.BASE_BLUE_STEEL,
					"Метрические размеры",
					"В игре вместо футов и дюймов будут использоваться метры и сантиметры.",
					Main.getProperties().hasValue(PropertyValue.metricSizes)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv("METRIC_FLUIDS",
					PresetColour.BASE_BLUE_STEEL,
					"Метрические жидкости",
					"В игре вместо галлонов и унций будут использоваться литры и миллилитры.",
					Main.getProperties().hasValue(PropertyValue.metricFluids)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv("METRIC_WEIGHTS",
					PresetColour.BASE_BLUE_STEEL,
					"Метрические веса",
					"В игре вместо фунтов и унций будут использоваться килограммы и граммы.",
					Main.getProperties().hasValue(PropertyValue.metricWeights)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv("TWENTYFOUR_HOUR_TIME",
					PresetColour.BASE_LILAC_LIGHT,
					"24-часовое время",
					"Время будет отображаться в виде 24 часов, а не AM/PM.",
					Main.getProperties().hasValue(PropertyValue.twentyFourHourTime)));

			UtilText.nodeContentSB.append(getContentPreferenceDiv("INTERNATIONAL_DATE",
					PresetColour.BASE_LILAC_LIGHT,
					"Международные даты",
					"Сокращенная дата будет отображаться как день.месяц.год, а не месяц/день/год.",
					Main.getProperties().hasValue(PropertyValue.internationalDate)));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться в меню настроек.", OPTIONS);

			} else {
				return null;
			}
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
		    return DialogueNodeType.OPTIONS;
		}
	};
	
	/**
	 * To be followed by two closing div elements.
	 */
	private static String getCustomContentPreferenceDivStart(Colour colour, String title, String description) {
		StringBuilder contentSB = new StringBuilder();
		
		contentSB.append(
				"<div class='container-full-width' style='padding:0; margin:2px 0;'>"
					+ "<div class='container-half-width' style='width:calc(55% - 16px);'>"
						+ "<b style='text-align:center; color:"+colour.toWebHexString()+";'>"+title+"</b><b>:</b> "
						+ description
					+ "</div>"
					+ "<div class='container-half-width' style='width:calc(45% - 16px);'>");
		
		return contentSB.toString();
	}
	
	private static String getContentPreferenceDiv(String id, Colour colour, String title, String description, boolean enabled) {
		StringBuilder contentSB = new StringBuilder();
		
		contentSB.append(
				"<div class='container-full-width' style='padding:0; margin:2px 0;'>"
					+ "<div class='container-half-width' style='width:calc(55% - 16px);'>"
						+ "<b style='text-align:center; color:"+colour.toWebHexString()+";'>"+ title+"</b><b>:</b> "
						+ description
					+ "</div>"
					+ "<div class='container-half-width' style='width:calc(45% - 16px);'>");
		
		if(enabled) {
			contentSB.append(
					"<div class='normal-button selected' style='width:25%; margin-right:4%; text-align:center; float:right;'>"
							+ "[style.boldGood(Вкл)]"
						+ "</div>"
					+ "<div id='"+id+"_OFF' class='normal-button' style='width:25%; margin-right:4%; text-align:center; float:right;'>"
						+ "[style.colourDisabled(Выкл)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div id='"+id+"_ON' class='normal-button' style='width:25%; margin-right:4%; text-align:center; float:right;'>"
						+ "[style.colourDisabled(Вкл)]"
					+ "</div>"
					+"<div class='normal-button selected' style='width:25%; margin-right:4%; text-align:center; float:right;'>"
						+ "[style.boldBad(Выкл)]"
					+ "</div>");
		}

		contentSB.append("</div>"
				+ "</div>");
		
		return contentSB.toString();
	}
	
	private static String getBreastsContentPreferenceVariableDiv(
			String id,
			Colour colour,
			String title,
			String description,
			String valueDisplay,
			int value, int minimum, int maximum,
			String valueDisplayUdders,
			int valueUdders, int minimumUdders, int maximumUdders) {
		
		StringBuilder contentSB = new StringBuilder();

		contentSB.append(
				"<div class='container-full-width' style='padding:0; margin:2px 0;'>"
					+ "<div class='container-half-width' style='width:calc(55% - 16px);'>"
						+ "<b style='text-align:center; color:"+colour.toWebHexString()+";'>"+ title+"</b><b>:</b> "
						+ description
					+ "</div>"
					+ "<div class='container-half-width' style='width:calc(45% - 16px);'>");
		
		contentSB.append(
				"<div class='container-full-width' style='width:100%; margin:0; padding:0; text-align:right;'>"
					+ "Breasts: "
					+ "<div id='"+id+"_ON' class='normal-button"+(value==maximum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
							+ (value==maximum?"[style.boldDisabled(+)]":"[style.boldGood(+)]")
					+ "</div>"
					+ "<div class='container-full-width' style='text-align:center; width:calc(30%); float:right; margin:0;'>"
						+ "<b>"+valueDisplay+"</b>"
					+ "</div>"
					+ "<div id='"+id+"_OFF' class='normal-button"+(value==minimum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
						+ (value==minimum?"[style.boldDisabled(-)]":"[style.boldBad(-)]")
					+ "</div>"
				+ "</div>");
		
		contentSB.append(
				"<div class='container-full-width' style='width:100%; margin:0; padding:0; text-align:right;'>"
					+ "Udders: "
					+ "<div id='"+id+"_UDDERS_ON' class='normal-button"+(valueUdders==maximumUdders?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
							+ (valueUdders==maximumUdders?"[style.boldDisabled(+)]":"[style.boldGood(+)]")
					+ "</div>"
					+ "<div class='container-full-width' style='text-align:center; width:calc(30%); float:right; margin:0;'>"
						+ "<b>"+valueDisplayUdders+"</b>"
					+ "</div>"
					+ "<div id='"+id+"_UDDERS_OFF' class='normal-button"+(valueUdders==minimumUdders?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
						+ (valueUdders==minimumUdders?"[style.boldDisabled(-)]":"[style.boldBad(-)]")
					+ "</div>"
				+ "</div>");
		
		contentSB.append("</div>"
				+"</div>");
		
		return contentSB.toString();
	}

	private static String getSkinColourContentPreferenceVariableDiv(
			String id,
			Colour colour,
			String title,
			String description) {
		
		StringBuilder contentSB = new StringBuilder();
		int minimum = 0;
		int maximum = 10;

		contentSB.append("<div class='container-full-width' style='padding:0; margin:2px 0;'>");
			contentSB.append(
					"<div class='container-half-width' style='width:calc(55% - 16px);'>"
						+ "<b style='text-align:center; color:"+colour.toWebHexString()+";'>"+ title+"</b><b>:</b> "
						+ description
					+ "</div>");
			
			contentSB.append("<div class='container-half-width' style='width:calc(45% - 16px);'>");
			
				for(Entry<Colour, Integer> entry : Main.getProperties().skinColourPreferencesMap.entrySet()) {
					Colour skinColour = entry.getKey();
					int value = entry.getValue();
					contentSB.append(
							"<div class='container-full-width' style='width:100%; margin:0; padding:0; text-align:right;'>"
								+ "<span style='color:"+skinColour.toWebHexString()+";'>"+Util.capitaliseSentence(skinColour.getName())+":</span> "
								+ "<div id='"+id+"_"+(skinColour).getId()+"_ON' class='normal-button"+(value==maximum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
										+ (value==maximum?"[style.boldDisabled(+)]":"[style.boldGood(+)]")
								+ "</div>"
								+ "<div class='container-full-width' style='text-align:center; width:calc(30%); float:right; margin:0;'>"
									+ "[style.colourSize"+value+"("+value+")]"
								+ "</div>"
								+ "<div id='"+id+"_"+(skinColour).getId()+"_OFF' class='normal-button"+(value==minimum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
									+ (value==minimum?"[style.boldDisabled(-)]":"[style.boldBad(-)]")
								+ "</div>"
							+ "</div>");
				}
			
			contentSB.append("</div>");
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	private static String getContentPreferenceVariableDiv(String id, Colour colour, String title, String description, String valueDisplay, int value, int minimum, int maximum) {
		StringBuilder contentSB = new StringBuilder();

		contentSB.append(
				"<div class='container-full-width' style='padding:0; margin:2px 0;'>"
					+ "<div class='container-half-width' style='width:calc(55% - 16px);'>"
						+ "<b style='text-align:center; color:"+colour.toWebHexString()+";'>"+ title+"</b><b>:</b> "
						+ description
					+ "</div>"
					+ "<div class='container-half-width' style='width:calc(45% - 16px);'>");
		
		contentSB.append(
				"<div id='"+id+"_ON' class='normal-button"+(value==maximum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
						+ (value==maximum?"[style.boldDisabled(+)]":"[style.boldGood(+)]")
				+ "</div>"
				+ "<div class='container-full-width' style='text-align:center; width:calc(30%); float:right; margin:0;'>"
					+ "<b>"+valueDisplay+"</b>"
				+ "</div>"
				+ "<div id='"+id+"_OFF' class='normal-button"+(value==minimum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
					+ (value==minimum?"[style.boldDisabled(-)]":"[style.boldBad(-)]")
				+ "</div>");
		
		contentSB.append("</div>"
				+"</div>");
		
		return contentSB.toString();
	}
	
	
	public static final DialogueNode CREDITS = new DialogueNode("Титры", "", true) {
		
		@Override
		public String getContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Спасибо за игру "+Main.GAME_NAME+", Я надеюсь вам понравилась игра, так же как и мне разрабатывать ее!"
						+ " Огромное спасибо всем кто предоставил финансовую поддержку! Благодаря вам, я могу потратить больше времени разрабатывая "+Main.GAME_NAME+", и я обещаю что буду делать игру настолько хорошей, насколько только смогу!"
					+ "</p>"
					+"<p style='text-align:center;'>"
						+ Main.GAME_NAME+" была создана:<br/>"
						+ "<b style='color:#9b78fa;'>Innoxia</b>"
						+ "<br/><br/>"
						+ "Художники чьи арты персонажей могут быть найдены в игре:<br/>");
			
			for(Artist artist : Artwork.allArtists) {
				if (!artist.getName().equals("Custom")) {
					UtilText.nodeContentSB.append("<b style='color:"+artist.getColour().toWebHexString()+";'>"+artist.getName()+"</b><br/>");
				}
			}

			UtilText.nodeContentSB.append("<br/>"
					+ "Contributors:</br>" // In alphabetical order:
					+ "<b style='color:#21bfc5;'>AceXP</b></br>"
					+ "<b style='color:#21bfc5;'>DJ Addi</b></br>"
					+ "<b style='color:#21bfc5;'>DSG</b></br>"
					+ "<b style='color:#21bfc5;'>Irbynx</b></br>"
					+ "<b style='color:#21bfc5;'>Maxis010</b></br>"
					+ "<b style='color:#21bfc5;'>Nnxx</b></br>"
					+ "<b style='color:#21bfc5;'>Norin</b></br>"
					+ "<b style='color:#21bfc5;'>NoStepOnSnek</b></br>"
					+ "<b style='color:#21bfc5;'>Phlarx</b></br>"
					+ "<b style='color:#21bfc5;'>Pimgd</b></br>"
					+ "<b style='color:#21bfc5;'>PoyntFury</b></br>"
					+ "<b style='color:#21bfc5;'>Rfpnj</b></br>"
					+ "<b style='color:#21bfc5;'>Tukaima</b></br>");
			
			UtilText.nodeContentSB.append("<br/>"
						+ "Special thanks to:<br/>"
						+ "<b>Sensei</b>,<br/>"
						+ "<b style='color:#fa0063;'>loveless</b>, <b style='color:#c790b2;'>Blue999</b>, and <b style='color:#ec9538;'>DesuDemona</b><br/>"
						+ "<b style='color:#21bec4;'>Github & wiki contributors</b><br/>"
						+ "<b style='color:#e06e5f;'>Everyone who's financially supported me</b>,<br/>"
						+ "<b>Bug reporters</b>,<br/>"
						+ "and<br/>"
						+ "<b>Everyone for playing Lilith's Throne!</b>"
					+ "</p>"
					+ "<br/>"
					+ "<h5 style='text-align:center; color:"+PresetColour.RARITY_LEGENDARY.toWebHexString()+";'>Legendary Patrons</h5>"
					+ "<p style='text-align:center;'>");
			
			for(CreditsSlot cs : Main.credits) {
				if(cs.getLegendaryCount()>0) {
					UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("<div style='width:50%; display:inline-block; text-align:right;'>");
					if(cs.getName().equals("Anonymous")) {
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_UNCOMMON.toWebHexString()+";'>?</b> ");
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_RARE.toWebHexString()+";'>?</b> ");
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>?</b> ");
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_LEGENDARY.toWebHexString()+";'>?</b> ");
					} else {
						for(int i=0; i<cs.getUncommonCount()%5; i++) {
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_UNCOMMON.toWebHexString()+";'>&#9679</b> ");
						}
						
						for(int i=0; i<cs.getRareCount()%5; i++) {
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_RARE.toWebHexString()+";'>&#9679</b> ");
						}
						
						for(int i=0; i<cs.getEpicCount()/5; i++) {// 5-marks:
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>&#127775</b> ");
						}
						for(int i=0; i<cs.getEpicCount()%5; i++) {
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>&#9679</b> ");
						}
						
						for(int i=0; i<cs.getLegendaryCount()/5; i++) {// 5-marks:
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_LEGENDARY.toWebHexString()+";'>&#127775</b> ");
						}
						for(int i=0; i<cs.getLegendaryCount()%5; i++) {
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_LEGENDARY.toWebHexString()+";'>&#9679</b> ");
						}
					}
					UtilText.nodeContentSB.append("</div>");
					UtilText.nodeContentSB.append("<div style='width:50%; display:inline-block; text-align:left;'>");
					UtilText.nodeContentSB.append("&nbsp;"+(cs.getSubspeciesTier()!=null?"<b style='color:"+cs.getSubspeciesTier().getColour(null).toWebHexString()+";'>"+cs.getName()+"</b>":cs.getName()));
					UtilText.nodeContentSB.append("</div>");
				}
			}
			
			UtilText.nodeContentSB.append(
					"</p>"
					+ "<br/>"
					+ "<h5 style='text-align:center; color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>Epic Patrons</h5>"
					+ "<p style='text-align:center;'>");
			
			for(CreditsSlot cs : Main.credits) {
				if(cs.getLegendaryCount()==0 && cs.getEpicCount()>0) {
					UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("<div style='width:50%; display:inline-block; text-align:right;'>");
					for(int i=0; i<cs.getUncommonCount()%5; i++) {
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_UNCOMMON.toWebHexString()+";'>&#9679</b> ");
					}
					
					for(int i=0; i<cs.getRareCount()%5; i++) {
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_RARE.toWebHexString()+";'>&#9679</b> ");
					}
					
					for(int i=0; i<cs.getEpicCount()/5; i++) {// 5-marks:
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>&#127775</b> ");
					}
					for(int i=0; i<cs.getEpicCount()%5; i++) {
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>&#9679</b> ");
					}
					UtilText.nodeContentSB.append("</div>");
					UtilText.nodeContentSB.append("<div style='width:50%; display:inline-block; text-align:left;'>");
					UtilText.nodeContentSB.append("&nbsp;"+(cs.getSubspeciesTier()!=null?"<b style='color:"+cs.getSubspeciesTier().getColour(null).toWebHexString()+";'>"+cs.getName()+"</b>":cs.getName()));
					UtilText.nodeContentSB.append("</div>");
				}
			}
			
			UtilText.nodeContentSB.append(
					"</p>"
					+ "<br/>"
					+ "<h5 style='text-align:center; color:"+Subspecies.DEMON.getColour(null).toWebHexString()+";'>Demonic backers</h5>"
					+ "<p style='text-align:center;'>");
			
			for(CreditsSlot cs : Main.credits) {
				if(cs.getLegendaryCount()==0 && cs.getEpicCount()==0) {
					UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("&nbsp;"+(cs.getSubspeciesTier()!=null?"<b style='color:"+cs.getSubspeciesTier().getColour(null).toWebHexString()+";'>"+cs.getName()+"</b>":cs.getName()));
				}
			}
			
			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Назад", "Вернуться в меню настроек.", MENU);
				
			} else {
				int i=1;
				for(Artist artist : Artwork.allArtists) {
					for(ArtistWebsite website : artist.getWebsites()) {
						if(index==i) {
							return new ResponseEffectsOnly(website.getName(), "Открывает страницу:<br/><br/><i>"+website.getURL()+"</i><br/><br/><b>Внешне в вашем браузере по умолчанию.</b>"){
								@Override
								public void effects() {
									Util.openLinkInDefaultBrowser(website.getURL());
								}
							};
						}
						i++;
					}
				}
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static Response getContentOptionsResponse(int responseTab, int index) {
		if (index == 1) {
			if (Main.game.getCurrentDialogueNode().equals(MISCELLANEOUS)) {
				return new Response("Прочее", "Вы уже смотрите на прочие настройки!", null);
			}
			return new Response("Прочее", "Посмотреть прочие настройки.", MISCELLANEOUS);
		} else if (index == 2) {
			if (Main.game.getCurrentDialogueNode().equals(GAMEPLAY)) {
				return new Response("Геймплей", "Вы уже смотрите на настройки геймплея!", null);
			}
			return new Response("Геймплей", "Посмотреть настройки геймплея.", GAMEPLAY);
		} else if (index == 3) {
			if (Main.game.getCurrentDialogueNode().equals(SEX)) {
				return new Response("Секс & Фетиши", "Вы уже смотрите на настройки секс & фетиши!", null);
			}
			return new Response("Секс & Фетиши", "Посмотреть настройки секс & фетиши.", SEX);
		} else if (index == 4) {
			if (Main.game.getCurrentDialogueNode().equals(BODIES)) {
				return new Response("Тела", "Вы уже смотрите на настройки тел!", null);
			}
			return new Response("Тела", "Посмотреть настройки тел.", BODIES);
		} else if (index == 5) {
			return new Response("[style.colourMinorBad(Сбросить)]",
					"Сбрасывает <b>все 'прочее', 'геймплей', 'секс & фетиши', и 'тела'</b> к настройкам по умолчанию!"
							+"<br/>Не сбрасывает настройки Пола, Ориентации, Возраст, Фурри или предпочтения по фетишам.",
					MISCELLANEOUS) {
				@Override
				public void effects() {
					for (PropertyValue pv : PropertyValue.values()) {
						Main.getProperties().setValue(pv, pv.getDefaultValue());
					}
					Main.getProperties().resetContentOptions();
					Main.saveProperties();
				}
			};
		} else if (index == 6) {
			if (Main.game.getCurrentDialogueNode().equals(GENDER_PREFERENCE)) {
				return new Response("Половые предпочтения", "Вы уже смотрите на настройки предпочтений полов!", null);
			}
			return new Response("Половые предпочтения", "Установите предпочитаемые появления полов.", GENDER_PREFERENCE);
		} else if (index == 7) {
			if (Main.game.getCurrentDialogueNode().equals(ORIENTATION_PREFERENCE)) {
				return new Response("Предпочтения по ориентации", "Вы уже смотрите на настройки предпочтений ориентации!", null);
			}
			return new Response("Предпочтения по ориентации", "Установите предпочитаемые появления ориентаций.", ORIENTATION_PREFERENCE);
		} else if (index == 8) {
			if (Main.game.getCurrentDialogueNode().equals(AGE_PREFERENCE)) {
				return new Response("Предпочтения по возрасту", "Вы уже смотрите на предпочтения по возрасту!", null);
			}
			return new Response("Предпочтения по возрасту", "Установите предпочитаемый возраст появления NPC.", AGE_PREFERENCE);
		} else if (index == 9) {
			if (Main.game.getCurrentDialogueNode().equals(FURRY_PREFERENCE)) {
				return new Response("Предпочтения по фурри", "Вы уже смотрите на предпочтения по фурри!", null);
			}
			return new Response("Предпочтения по фурри", "Установите предпочитаемый уровень фурри для появления.", FURRY_PREFERENCE);
		} else if (index == 10) {
			if (Main.game.getCurrentDialogueNode().equals(FETISH_PREFERENCE)) {
				return new Response("Предпочтения по фетишам", "Вы уже смотрите на предпочтения по фетишам!", null);
			}
			return new Response("Предпочтения по фетишам", "Установите предпочитения по встречаемым фетишам.", FETISH_PREFERENCE);
		} else if (index == 0) {
			return new Response("Назад", "Вернуться в главное меню.", MENU);
		}
		return null;
	}
	
	public static final DialogueNode MISCELLANEOUS = new DialogueNode("Настройки контента (Прочее)", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.GENERIC_GOOD, "Частота автосохранения", "Выберите, как часто игра будет автосохраняться при переходе с одной карты на другую."));
			for (int i = 2; i>=0; i--) {
				UtilText.nodeContentSB.append("<div id='AUTOSAVE_FREQUENCY_"+i+"' class='normal-button"+(Main.getProperties().autoSaveFrequency == i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().autoSaveFrequency == i
						?"[style.boldGood("
						:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.autoSaveLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			UtilText.nodeContentSB.append(getContentPreferenceDiv("ARTWORK",
					PresetColour.BASE_BLUE_LIGHT,
					"Арты",
					"Позволяет отображать иллюстрации на информационных экранах некоторых персонажей.",
					Main.getProperties().hasValue(PropertyValue.artwork)));
			UtilText.nodeContentSB.append(getContentPreferenceDiv("THUMBNAIL",
					PresetColour.BASE_BLUE_STEEL,
					"Миниатюры",
					"Включает всплывающие подсказки с уменьшенными изображениями персонажа.",
					Main.getProperties().hasValue(PropertyValue.thumbnail)));
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_AQUA, "Предпочитаемый художник", "Работы какого художника используются по умолчанию."));
			List<Artist> artists = new ArrayList<>(Artwork.allArtists);
			Collections.reverse(artists);// So that they're in alphabetical order
			for (Artist artist : artists) {
				if (!artist.getName().equals("Custom")) {
					UtilText.nodeContentSB.append(
							(Main.getProperties().preferredArtist.equals(artist.getFolderName())
									?"<div id='ARTIST_"+artist.getFolderName()+"' class='normal-button selected' style='width:75%; text-align:center; float:right;'>"
									+"<b style='color:"+artist.getColour().toWebHexString()+";'>"+artist.getName()+"</b>"
									+"</div>"
									:"<div id='ARTIST_"+artist.getFolderName()+"' class='normal-button' style='width:75%; text-align:center; float:right;'>"
									+"[style.boldDisabled("+artist.getName()+")]"
									+"</div>"));
				}
			}
			UtilText.nodeContentSB.append("</div></div>");
			UtilText.nodeContentSB.append(getContentPreferenceDiv("SHARED_ENCYCLOPEDIA",
					PresetColour.GENERIC_EXCELLENT,
					"Общая энциклопедия",
					"Когда включено, записи в энциклопедии будут распостранятся на все сохранения. Если выключено, то каждое новое сохранения будет требовать разблокировки записей.",
					Main.getProperties().hasValue(PropertyValue.sharedEncyclopedia)));
			UtilText.nodeContentSB.append(getContentPreferenceDiv("WEATHER_INTERRUPTION",
					PresetColour.GENERIC_ARCANE,
					"Штормовые прерывания",
					"Когда включено, магический шторм прервет диалог чтобы сказать вам о том что шторм начался.",
					Main.getProperties().hasValue(PropertyValue.weatherInterruptions)));
			UtilText.nodeContentSB.append(getContentPreferenceDiv("DIALOGUE_COPY",
					PresetColour.BASE_BLUE_STEEL,
					"Автоматическое копирование текста",
					"Если эта функция включена, текст текущей сцены будет автоматически копироваться в системный буфер обмена каждый раз, когда загружается новая сцена."
							+" Эта опция позволяет легко вставлять текст игры в программы для чтения текста без необходимости каждый раз выделять и копировать текст сцены.",
					Main.getProperties().hasValue(PropertyValue.automaticDialogueCopy)));
			UtilText.nodeContentSB.append(getContentPreferenceDiv("SILLY",
					PresetColour.GENERIC_GOOD,
					"Глупый режим",
					"Это позволяет использовать забавный текст на протяжении всей игры. (Гарпии заменяются на птичек и т.д.)",
					Main.getProperties().hasValue(PropertyValue.sillyMode)));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getContentOptionsResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode GAMEPLAY = new DialogueNode("Настройки контента (Геймплей)", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("ENCHANTMENT_LIMITS",
					PresetColour.GENERIC_ARCANE,
					"Вместимость зачарований",
					"Когда включено, персонаж имеет определенное ограничение на максимальное количество зачарований, которое зависит от уровня и навыков. Когда отключено, зачаровывать можно до бесконечности (пока есть ресурс), использование не реккомендуется.",
					Main.getProperties().hasValue(PropertyValue.enchantmentLimits)));
			UtilText.nodeContentSB.append(getContentPreferenceDiv("BAD_END",
					PresetColour.GENERIC_TERRIBLE,
					"Плохие концы",
					"Включите возможность встречать `плохие концы`, которые по сути заканчивают игру."
							+"<br/>[style.italicsMinorBad(Пожалуйста отметьте что плохие концы содержат сцены без согласия. Выключено ли это предпочтение или включено.)]",
					Main.getProperties().hasValue(PropertyValue.badEndContent)));
			UtilText.nodeContentSB.append(getContentPreferenceDiv("LEVEL_DRAIN",
					PresetColour.GENERIC_TERRIBLE,
					"Выкачивание уровня",
					"Включить использование перка 'выкачивание уроня оргазмом' для использования уникальным NPC, (некоторые сцены с Эмбер), которые выкачивают ваш уровень во время секса с ними.",
					Main.getProperties().hasValue(PropertyValue.levelDrain)));
			UtilText.nodeContentSB.append(getContentPreferenceDiv("OPPORTUNISTIC_ATTACKERS",
					PresetColour.BASE_CRIMSON,
					"Оппортунистические нападающие",
					"Это делает случайные атаки более вероятными, когда у вас много похоти, мало здоровья, вы покрыты жидкостью, обнажены или пьяны.",
					Main.game.isOpportunisticAttackersEnabled()));
			UtilText.nodeContentSB.append(getContentPreferenceDiv("OFFSPRING_ENCOUNTERS",
					PresetColour.BASE_INDIGO,
					"Встречи с потомством",
					"Это позволит вам случайным образом встречать своих потомков по всему миру."
					+ "<br/><i>Эта настройка не влияет ни на карту потомков, ни на потомков, с которыми вы уже встречались.</i>",
					Main.game.isOffspringEncountersEnabled()));
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_BLUE_LIGHT, "Женственность одежды", "Это накладывает ограничения на значения женственности у одежды."));
			for (int i=Main.getProperties().clothingFemininityTitles.length-1; i>=0; i--) {
				if (Main.getProperties().getClothingFemininityLevel() == i) {
					UtilText.nodeContentSB.append("<div id='CLOTHING_FEMININITY_"+i
							+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"
							+Main.getProperties().clothingFemininityColours[i].toWebHexString()+";'><b>"+Main.getProperties().clothingFemininityTitles[i]+"</b></div>");
				} else {
					UtilText.nodeContentSB.append("<div id='CLOTHING_FEMININITY_"+i
							+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+"[style.colourDisabled("+Main.getProperties().clothingFemininityTitles[i]+")]</div>");
				}
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_PINK, "Пропуск совращения", "Если включено, требования к совращению для секс действий можно игнорировать."));
			for (int i = 2; i>=0; i--) {
				UtilText.nodeContentSB.append("<div id='BYPASS_SEX_ACTIONS_"+i+"' class='normal-button"+(Main.getProperties().bypassSexActions == i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().bypassSexActions == i
						?"[style.boldGood("
						:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.bypassSexActionsLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
					"PREGNANCY_DURATION",
					PresetColour.BASE_PINK_DEEP,
					"Продолжительность беременности",
					"Это максимальный срок, в течение которого беременность длится от зачатия до рождения.",
					Main.getProperties().pregnancyDuration+" недель"+(Main.getProperties().pregnancyDuration == 1?"":""),
					Main.getProperties().pregnancyDuration,
					1,
					40));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("SPITTING_ENABLED",
					PresetColour.BASE_BLUE,
					"Отказ от трасформаций",
					"Принуждение к трасформации можно обойти выпленув зелье, если включено.",
					!Main.game.isSpittingDisabled()));
			
			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
					"FORCED_TF",
					PresetColour.TRANSFORMATION_GENERIC,
					"Принудительные трансформации",
					"Устанавливает количество NPC которые появляются с '"+Fetish.FETISH_TRANSFORMATION_GIVING.getName(null)+"', который позволяет NPC попытатся превратить вас.",
					Main.getProperties().forcedTFPercentage+"%",
					Main.getProperties().forcedTFPercentage,
					0,
					100));
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_GREEN, "Принудительные расовые ограничения трансформаций", "Это позволит вам установить максимальный предел фурри, в которую NPC будет насильно превращать вас."));
			for (FurryPreference fp : Util.newArrayListOfValues(FurryPreference.REDUCED,
					FurryPreference.MINIMUM,
					FurryPreference.HUMAN,
					FurryPreference.MAXIMUM,
					FurryPreference.NORMAL)) {
				if (Main.getProperties().getForcedTFPreference() == fp) {
					UtilText.nodeContentSB.append("<div id='FORCED_TF_FURRY_LIMIT_"+fp
							+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"
							+fp.getColour().toWebHexString()+";'>"+fp.getName()+"</div>");
				} else {
					UtilText.nodeContentSB.append("<div id='FORCED_TF_FURRY_LIMIT_"+fp
							+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+"[style.colourDisabled("+fp.getName()+")]</div>");
				}
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_GREEN, "Тенденция полов при принудительных трансформациях", "Это позволит вам переопределить вкусы NPC, если принудительная трансформация изменит вашу половую принадлежность."));
			for (ForcedTFTendency ftt : Util.newArrayListOfValues(ForcedTFTendency.NEUTRAL,
					ForcedTFTendency.FEMININE,
					ForcedTFTendency.FEMININE_HEAVY,
					ForcedTFTendency.MASCULINE_HEAVY,
					ForcedTFTendency.MASCULINE)) {
				if (Main.getProperties().getForcedTFTendency() == ftt) {
					UtilText.nodeContentSB.append("<div id='FORCED_TF_TENDENCY_"+ftt
							+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"
							+ftt.getColour().toWebHexString()+";'>"+ftt.getName()+"</div>");
				} else {
					UtilText.nodeContentSB.append("<div id='FORCED_TF_TENDENCY_"+ftt
							+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+"[style.colourDisabled("+ftt.getName()+")]</div>");
				}
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
					"FORCED_FETISH",
					PresetColour.FETISH,
					"Принудительные фетиши",
					"Устанавливает количество NPC которые появляются с '"+Fetish.FETISH_KINK_GIVING.getName(null)+"', который вызывает попытки NPC принудительно дать вам фетиш.",
					Main.getProperties().forcedFetishPercentage+"%",
					Main.getProperties().forcedFetishPercentage,
					0,
					100));
			
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.FETISH, "Тенденця принудительных фетишей",
					"Это позволит вам переопределить вкусы NPC и контролировать тенденцию принудительных фетишей к верхушке или низу."));
			for (ForcedFetishTendency fft : Util.newArrayListOfValues(ForcedFetishTendency.NEUTRAL,
					ForcedFetishTendency.BOTTOM,
					ForcedFetishTendency.BOTTOM_HEAVY,
					ForcedFetishTendency.TOP_HEAVY,
					ForcedFetishTendency.TOP)) {
				if (Main.getProperties().getForcedFetishTendency() == fft) {
					UtilText.nodeContentSB.append("<div id='FORCED_FETISH_TENDENCY_"+fft
							+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"
							+fft.getColour().toWebHexString()+";'>"+fft.getName()+"</div>");
				} else {
					UtilText.nodeContentSB.append("<div id='FORCED_FETISH_TENDENCY_"+fft
							+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+"[style.colourDisabled("+fft.getName()+")]</div>");
				}
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("COMPANION",
					PresetColour.BASE_GREEN_LIGHT,
					"Компаньоны",
					"Включите возможность добавлять рабов или дружественных жильцов в качестве компаньонов."
							+"<br/>[style.boldBad(ВНИМАНИЕ:)] Это экспериментальная функция, и поддержка компаньонов была прекращена в версии 0.3.9, поэтому никаких особых диалогов или действий с участием компаньонов за пределами Доминиона не будет.",
					Main.getProperties().hasValue(PropertyValue.companionContent)));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getContentOptionsResponse(responseTab, index);
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode SEX = new DialogueNode("Настройки контента (Секс & Фетиши)", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("NON_CON",
					PresetColour.BASE_CRIMSON,
					"Без согласия",
					"Это включает стадию 'сопротивления' в секс сценах, которая содержит в себе более жесткие диалоги, а также диалоговые отсылки и действия, связанные с этим содержанием."
							+"<br/>[style.italicsMinorBad(Плохие концы включают контент без согласия в независмости от этой настройки.)]",
					Main.getProperties().hasValue(PropertyValue.nonConContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("SADISTIC_SEX",
					PresetColour.BASE_RED,
					"Садистский секс",
					"Позволяет выполнять `садисткие` секс действия, такие как душить, шлепать, плевать на партнеров во время секса.",
					Main.getProperties().hasValue(PropertyValue.sadisticSexContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("LIPSTICK_MARKING",
					PresetColour.BASE_RED_DARK,
					"Отметки помады",
					"Поцелуи буду наносить отметки помадой на части тела во время секса.",
					Main.getProperties().hasValue(PropertyValue.lipstickMarkingContent)));
			
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("VOLUNTARY_NTR",
					PresetColour.GENERIC_MINOR_BAD,
					"Добровольный NTR",
					"Добавляет опции в которых враги могут иметь секс с вашими компаньенами для избегания боя.",
					Main.getProperties().hasValue(PropertyValue.voluntaryNTR)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("INVOLUNTARY_NTR",
					PresetColour.GENERIC_BAD,
					"Недобровольный NTR",
					"Враги могут пожелать иметь секс только с вашим компаньеном, после поражения в бою."
							+" Когда выключено, все секс сцены после боя будут связаны только с вами.",
					Main.getProperties().hasValue(PropertyValue.involuntaryNTR)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("INCEST",
					PresetColour.BASE_ROSE,
					"Инцест",
					"Включает опции инцеста для игрока и NPC.",
					Main.getProperties().hasValue(PropertyValue.incestContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("LACTATION",
					PresetColour.BASE_YELLOW_LIGHT,
					"Лактация",
					"Включает контент связанный с лактацией.",
					Main.getProperties().hasValue(PropertyValue.lactationContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("SEXUAL_UDDERS",
					PresetColour.BASE_ORANGE_LIGHT,
					"Грудь перед промежностью & вымя",
					"Включает связанные секс действия и превращения. (Под `Грудь перед промежностью`, подразумеваются виды у которых грудь находится там же где и вымя у коровы).",
					Main.getProperties().hasValue(PropertyValue.udderContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("URETHRAL",
					PresetColour.BASE_PINK_DEEP,
					"Контент с уретрой",
					"Включает связанные с уретрой секс дествия и превращения.",
					Main.getProperties().hasValue(PropertyValue.urethralContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("NIPPLE_PEN",
					PresetColour.BASE_PINK_DEEP,
					"Проникновения в соски",
					"Включает связанные с сосками превращения и проникновения.",
					Main.getProperties().hasValue(PropertyValue.nipplePenContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("ANAL",
					PresetColour.BASE_ORANGE,
					"Анал",
					"Когда выключено, делает недоступными секс действия связанные с аналом.",
					Main.getProperties().hasValue(PropertyValue.analContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("GAPE",
					PresetColour.BASE_PINK_DEEP,
					"Зияющий контент",
					"Когда выключено, изменяет описание зияющих отверстий на `растянутые`, так же прячет специальный связанный с этим контент.",
					Main.getProperties().hasValue(PropertyValue.gapeContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("PENETRATION_LIMITATION",
					PresetColour.BASE_PINK_DEEP,
					"Разница в размерах при проникновении",
					"Когда включено, отверстия будут иметь максимальную глубину, из-за чего большие пенисы могут быть слишком длинными для полного проникновения.",
					Main.getProperties().hasValue(PropertyValue.penetrationLimitations)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("PENETRATION_LIMITATION_DYNAMIC",
					PresetColour.BASE_PINK_DEEP,
					"Эффект глубины и эластичности",
					"Когда включено, отверстия будут иметь свойство эластичности, в зависимости от него отверстие будет либо восстанавливать свой размер, либо оставатся растянутым навсегда."
							+" (Заметка: Работает только когда включена: 'Разница в размерах при проникновении')",
					Main.getProperties().hasValue(PropertyValue.elasticityAffectDepth)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("FOOT",
					PresetColour.BASE_TAN,
					"Фут-фетиш",
					"Когда выключено, убирает весь связанный со ступнями контент во время секс сцен.",
					Main.getProperties().hasValue(PropertyValue.footContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("ARMPIT",
					PresetColour.BASE_PINK_LIGHT,
					"Подмышки",
					"Когда выключено, убирает весь связанный с подмышками контент во время секс сцен.",
					Main.getProperties().hasValue(PropertyValue.armpitContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("MUSK",
					PresetColour.BASE_YELLOW_LIGHT,
					"Мускус",
					"Когда выключено, в некоторых сценах содержание мускуса будет уменьшено или полностью исключено, а эффект статуса `отмечен мускусом` будет отключен.",
					Main.getProperties().hasValue(PropertyValue.muskContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("FURRY_TAIL_PENETRATION",
					PresetColour.BASE_MAGENTA,
					"Проникновения хвостами",
					"Отмечает все типы хвостов как пригодные для проникновения, позволяя использовать их в секс действиях проникновения.",
					Main.getProperties().hasValue(PropertyValue.furryTailPenetrationContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("INFLATION_CONTENT",
					PresetColour.CUM,
					"Заполнение спермой",
					"Включает механику заполнения спермой.",
					Main.getProperties().hasValue(PropertyValue.inflationContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("AUTO_SEX_CLOTHING_MANAGEMENT",
					PresetColour.BASE_BLUE_STEEL,
					"Восстановление одежды после секса",
					"После секс сцены одежда вернется в состояние которые было до нее. (Застегнется, оденется и т.д.)",
					Main.getProperties().hasValue(PropertyValue.autoSexClothingManagement)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("AUTO_SEX_CLOTHING_STRIP",
					PresetColour.BASE_PINK_LIGHT,
					"Автоматическое раздевание",
					"Секс сцены начнутся без одежды, за некоторыми исключениями.",
					Main.getProperties().hasValue(PropertyValue.autoSexStrip)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("RAPE_PLAY_BY_DEFAULT",
					PresetColour.BASE_CRIMSON,
					"Игра в насильника",
					"Когда включено, покорные персонажи которые имеют фетиш 'покорная секс игрушка' могут начать игру в изнасилование без разрешения.",
					Main.getProperties().hasValue(PropertyValue.rapePlayAtSexStart)));
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_PINK, "Полные описания обнажения", "Установить как часто раскрытые части тела полностью описываются во время секса."));
			for (int i = 2; i>=0; i--) {
				UtilText.nodeContentSB.append("<div id='FULL_EXPOSURE_DESCRIPTIONS_"+i+"' class='normal-button"+(Main.getProperties().bypassSexActions == i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().fullExposureDescriptions == i
						?"[style.boldGood("
						:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.fullExposureDescriptionsLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getContentOptionsResponse(responseTab, index);
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode BODIES = new DialogueNode("Content Options (Bodies)", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("AGE",
					PresetColour.AGE_TWENTIES,
					"Возраст",
					"Это включает описания возраста, в котором находятся персонажи.",
					Main.getProperties().hasValue(PropertyValue.ageContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("FERAL",
					PresetColour.BASE_TAN,
					"Звери",
					"Это позволяет использовать животный контент, который содержит сексуальные и несексуальные взаимодействия с разумными персонажами, имеющими полностью животные тела.",
					Main.getProperties().hasValue(PropertyValue.feralContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("CUM_REGENERATION",
					PresetColour.CUM,
					"Регенерация спермы",
					"Включает контент связанный с регенераций спермы. Так же уменьшение количества от множественных оргазмов и статус полных яиц."
							+"<br>Когда выключено, яйца всегда считаются полными, без плюсов и минусов.",
					Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("FUTA_BALLS",
					PresetColour.BASE_PINK,
					"Фута яйца",
					"Когда включено, футанари NPC будут иметь яйца.",
					Main.getProperties().hasValue(PropertyValue.futanariTesticles)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("CLOACA",
					PresetColour.BASE_PINK_LIGHT,
					"Двуногие клоаки",
					"Когда включено, некоторые двуногие расы (Гарпии и аллигатор-морфы), будут иметь клоаку."
							+" Когда выключено, все двуногие у которых должна быть клоака будут иметь стандартную конфигурацию гениталий."
							+" Некоторые особые расы, такие как ламия, всегда имеют клоаки, на них эта опция не влияет.",
					Main.getProperties().hasValue(PropertyValue.bipedalCloaca)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("VESTIGIAL_MULTI_BREAST",
					PresetColour.BASE_PURPLE_LIGHT,
					"Рудиментарные мульти-груди",
					"Когда включено, персонажи которые имеют несколько рядов грудей, будут иметь груди ниже основных описываемые как рудименты."
							+" Когда выключено, ряды грудей буду описываемыми на одну чашечку меньше чем те которые над ними.",
					Main.getProperties().hasValue(PropertyValue.vestigialMultiBreasts)));
			
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.NIPPLES, "Мульти-Груди", "Выберите будет ли несколько рядов грудей у фурри которые должны иметь несколько грудей."));
			int[] buttonOrder = new int[] {2, 1, 0, 3}; // Order buttons in this manner so that they appear to be a little more logical
			for (int i : buttonOrder) {
				UtilText.nodeContentSB.append("<div id='MULTI_BREAST_PREFERENCE_"+i+"' class='normal-button"+(Main.getProperties().multiBreasts == i?" выбрано":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().multiBreasts == i
							?(i == 0
								?"[style.boldTerrible("
								:(i == 1
									?"[style.boldBad("
									:"[style.boldGood("))
							:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.multiBreastsLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.NIPPLES_CROTCH, "Грудь перед промежностью & Вымя", "Выберите будет ли вымя и грудь перед промежностью у фурри и тавров которые должны иметь их."));
			for (int i = com.lilithsthrone.game.Properties.uddersLabels.length-1; i>=0; i--) {
				UtilText.nodeContentSB.append("<div id='UDDER_PREFERENCE_"+i+"' class='normal-button"+(Main.getProperties().getUddersLevel() == i?" выбрано":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().getUddersLevel() == i
						?(i == 0?"[style.boldBad(":"[style.boldGood(")
						:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.uddersLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("HAIR_FACIAL",
					PresetColour.BASE_LILAC_LIGHT,
					"Волосы на лице",
					"Включает описание волос на лице и связаного контента.",
					Main.getProperties().hasValue(PropertyValue.facialHairContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("HAIR_PUBIC",
					PresetColour.BASE_LILAC,
					"Лобковые волосы",
					"Включает описание волос на лобке и связаного контента.",
					Main.getProperties().hasValue(PropertyValue.pubicHairContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("HAIR_BODY",
					PresetColour.BASE_PURPLE,
					"Волосы подмышками",
					"Включает описание волос на подмышках и связаного контента.",
					Main.getProperties().hasValue(PropertyValue.bodyHairContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("HAIR_ASS",
					PresetColour.BASE_PURPLE_DARK,
					"Волосы на заднице",
					"Включает описание волос на заднице и связаного контента..",
					Main.getProperties().hasValue(PropertyValue.assHairContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("FEMININE_BEARD",
					PresetColour.BASE_BLUE_STEEL,
					"Женские бороды",
					"Позволяет женским персонажам иметь бороду.",
					Main.getProperties().hasValue(PropertyValue.feminineBeardsContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("FURRY_HAIR",
					PresetColour.CLOTHING_DESATURATED_BROWN,
					"Фурри прически",
					"Переключает будут ли персонажи с головами фурри иметь человеческую прическу.",
					Main.getProperties().hasValue(PropertyValue.furryHairContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("SCALY_HAIR",
					PresetColour.BASE_GREEN_DARK,
					"Чешуйчатые прически",
					"Переключает, будут ли персонажи с типом головы `рептилия` или `амфибия` появляться на свет с волосами на голове, похожими на человеческие.",
					Main.getProperties().hasValue(PropertyValue.scalyHairContent)));
			
			UtilText.nodeContentSB.append(getContentPreferenceDiv("LIP_LISP",
					PresetColour.BASE_GREEN_DARK,
					"Запинка больших губ",
					"Переключает, будут ли персонажи с очень большими губами говорить с запинкой.",
					Main.getProperties().hasValue(PropertyValue.lipLispContent)));
			
			UtilText.nodeContentSB.append(getBreastsContentPreferenceVariableDiv(
					"PREGNANCY_BREAST_GROWTH",
					PresetColour.BASE_PINK,
					"Средний рост груди при беременности",
					"Устанавливает <b>средний</b> размер роста чашечки который будет получать персонаж от каждой беременности. Настоящий рост груди будет в пределах "+Util.intToString(Main.getProperties().pregnancyBreastGrowthVariance)+" размера этого значения.",
					Main.getProperties().pregnancyBreastGrowth == 0
							?"[style.boldDisabled(Выключено)]"
							:Main.getProperties().pregnancyBreastGrowth+" чашка"+(Main.getProperties().pregnancyBreastGrowth != 1?"":""),
					Main.getProperties().pregnancyBreastGrowth, 0, 10,
					Main.getProperties().pregnancyUdderGrowth == 0
							?"[style.boldDisabled(Выключено)]"
							:Main.getProperties().pregnancyUdderGrowth+" чашка"+(Main.getProperties().pregnancyUdderGrowth != 1?"":""),
					Main.getProperties().pregnancyUdderGrowth, 0, 10));
			
			UtilText.nodeContentSB.append(getBreastsContentPreferenceVariableDiv(
					"PREGNANCY_BREAST_GROWTH_LIMIT",
					PresetColour.BASE_PINK_LIGHT,
					"Ограничение роста груди при беременности",
					"Установите максимальный предел размера чашечек, до которого может вырасти грудь персонажей после беременностей.",
					CupSize.getCupSizeFromInt(Main.getProperties().pregnancyBreastGrowthLimit).getCupSizeName()+"-чашка",
					Main.getProperties().pregnancyBreastGrowthLimit, 0, 100,
					CupSize.getCupSizeFromInt(Main.getProperties().pregnancyUdderGrowthLimit).getCupSizeName()+"-чашка",
					Main.getProperties().pregnancyUdderGrowthLimit, 0, 100));
			
			UtilText.nodeContentSB.append(getBreastsContentPreferenceVariableDiv(
					"PREGNANCY_LACTATION",
					PresetColour.BASE_YELLOW,
					"Средняя лактация при беременности",
					"Устанавливает <b>сренее</b> увеличение лактации персонажей от каждой беременности. Настоящее увеличение лактации будет в пределах "
							+Units.fluid(Main.getProperties().pregnancyLactationIncreaseVariance)+" этого значения.",
					Main.getProperties().pregnancyLactationIncrease == 0
							?"[style.boldDisabled(Выключено)]"
							:Units.fluid(Main.getProperties().pregnancyLactationIncrease),
					Main.getProperties().pregnancyLactationIncrease, 0, 1000,
					Main.getProperties().pregnancyUdderLactationIncrease == 0
							?"[style.boldDisabled(Выключено)]"
							:Units.fluid(Main.getProperties().pregnancyUdderLactationIncrease),
					Main.getProperties().pregnancyUdderLactationIncrease, 0, 1000));
			
			UtilText.nodeContentSB.append(getBreastsContentPreferenceVariableDiv(
					"PREGNANCY_LACTATION_LIMIT",
					PresetColour.BASE_YELLOW_LIGHT,
					"Лимит лактации при беременности",
					"Установите максимальный размер лактации получаемый персонажами от беременности.",
					Units.fluid(Main.getProperties().pregnancyLactationLimit, Units.ValueType.PRECISE, Units.UnitType.SHORT),
					Main.getProperties().pregnancyLactationLimit, 0, Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue(),
					Units.fluid(Main.getProperties().pregnancyUdderLactationLimit, Units.ValueType.PRECISE, Units.UnitType.SHORT),
					Main.getProperties().pregnancyUdderLactationLimit, 0, Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue()));
			
			UtilText.nodeContentSB.append(getBreastsContentPreferenceVariableDiv(
					"BREAST_SIZE_PREFERENCE",
					PresetColour.NIPPLES,
					"Предпочтительный размер чашечек",
					"Влияет на размер чашек у случайно сгенерированных NPC (не ниже AA).",
					(Main.getProperties().breastSizePreference>=0?"+":"")+Main.getProperties().breastSizePreference,
					Main.getProperties().breastSizePreference, -20, 20,
					(Main.getProperties().udderSizePreference>=0?"+":"")+Main.getProperties().udderSizePreference,
					Main.getProperties().udderSizePreference, -20, 20));
			
			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
					"PENIS_SIZE_PREFERENCE",
					PresetColour.PENIS,
					"Предпочтительный размер пенисов",
					"Влияет на размер пениса случайно сгенерированных NPC (не ниже "+Units.size(8)+").",
					(Main.getProperties().penisSizePreference>=0?"+":"")+Units.size(Main.getProperties().penisSizePreference, Units.ValueType.PRECISE, Units.UnitType.SHORT),
					Main.getProperties().penisSizePreference,
					-20,
					20));
			
			UtilText.nodeContentSB.append(getContentPreferenceVariableDiv(
					"TRAP_PENIS_SIZE_PREFERENCE",
					PresetColour.BASE_PINK_LIGHT,
					Util.capitaliseSentence(Gender.N_P_TRAP.getName())+" размер пениса",
					"Размер пениса случайно сгенерированного "+Gender.N_P_TRAP.getName()+". 100% - это неизменный размер. Размер яичек и выработка спермы также будут изменяться пропорционально этому параметру.",
					(100+Main.getProperties().trapPenisSizePreference)+"%",
					Main.getProperties().trapPenisSizePreference,
					-90,
					100));
			
			UtilText.nodeContentSB.append(getSkinColourContentPreferenceVariableDiv(
					"SKIN_COLOUR_PREFERENCE",
					PresetColour.RACE_HUMAN,
					"Предпочтительные цвета кожи",
					"Влияет на веса цветов кожи человека для случайно сгенерированных NPC."
							+" Это не касается `больших` фурри NPC, так как у них нет человеческих кожных покровов."));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getContentOptionsResponse(responseTab, index);
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
}
