package com.revature.hs;

import org.apache.log4j.Logger;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.revature.hs.Rarity.COMMON;

public class CardCollector {
    private static CardCollector cc;
    private HashMap<String, CardSet> setMap;
	private static final Logger logger = Logger.getLogger(CardCollector.class);
	private HashMap<String, Card> allCards;

    public static CardCollector getInstance() {
    	if (cc == null) {
    		cc = new CardCollector();
		}
		return cc;
	}

	private CardCollector() {
		long timer = System.currentTimeMillis();
		FileReader fr = null;
		JSONArray js = null;
		try {
			fr = new FileReader(new File("src/main/resources/cards.collectible.json"));
			js = new JSONArray(new JSONTokener(fr));
		} catch (FileNotFoundException e) {
			logger.warn("Couldn't find cards.collectible.json");
		} finally {
			try {
				fr.close();
			} catch (IOException ioe){
				logger.warn(ioe.getMessage());
			}
		}
		initializeCardDB(js);
		logger.info("CardCollector created, took " + ((System.currentTimeMillis() - timer) / 1000.0) + "seconds");
	}

	public static void refresh() {
    	cc = new CardCollector();
	}

	// Grabs the Hearthstone card information from a file.
	// In the interest of readability, know that a release group of cards is called a "set"
	private void initializeCardDB(JSONArray jsa) {
		JSONObject js;
		String setName = null;
		Card card;

		setMap = new HashMap<>();
		allCards = new HashMap<>();

    	for (Object o: jsa) {
			js = (JSONObject) o;
			try {
				setName = convertSetName(js.getString("set"));
			} catch (JSONException jse) {
				logger.warn("Couldn't get set of card " + js.getString("name"));
				System.exit(1);
			}
			if ("Non-expansion".equals(setName)) {
				continue;
			}
			if (!setMap.keySet().contains(setName)) {
				setMap.put(setName, new CardSet(setName));
			}

			try {
				card = new Card(js);
				setMap.get(setName).addCard(card);
				allCards.put(card.getName(), card);
			} catch (RarityNotFoundException e) {
				try {
					logger.warn(
							js.getString("name") + " has invalid rarity \"" + js.getString("rarity") + "\"");

				} catch (JSONException je) {
					logger.warn("Error when trying to report invalid rarity");
				}
			}
		}
	}

	public Deque<Card> openPack(String set) {
		return setMap.get(set).openPack();
	}

	public Card getCard(String cardName) {
		return allCards.get(cardName);
	}

	public List<Card> getCardList(SetOptions set, Rarity rarity) {
    	return setMap.get(convertSetName(set)).getCardList(rarity);
	}

	public static void printCardList(List<Card> cdList) {
		TextIO textIO = TextIoFactory.getTextIO();
		TextTerminal terminal = textIO.getTextTerminal();
    	cdList.sort(new CardSorter());
    	for (int i = 0; i < cdList.size(); i++) {
    		terminal.println(i + ": " + cdList.get(i).getName());
		}
	}

	private static String convertSetName(String n) {
    	switch (n) {
			case "GILNEAS":
				return "The Witchwood";
			case "LOOTAPALOOZA":
				return "Kobalds and Catacombs";
			case "ICECROWN":
				return "Knights of the Frozen Throne";
			case "UNGORO":
				return "Journey to Un'Goro";
			case "GANGS":
				return "Mean Streets of Gadgetzan";
			case "OG":
				return "Whispers of the Old Gods";
			case "TGT":
				return "The Grand Tournament";
			case "GVG":
				return "Goblins vs. Gnomes";
			default:
				return "Non-expansion";
		}
	}

	public static String convertSetName(SetOptions n) {
		switch (n) {
			case Witchwood:
				return "The Witchwood";
			case KobaldsAndCatacombs:
				return "Kobalds and Catacombs";
			case KnightsOfTheFrozenThrone:
				return "Knights of the Frozen Throne";
			case UnGoro:
				return "Journey to Un'Goro";
			case MeanStreetsOfGadgetzan:
				return "Mean Streets of Gadgetzan";
			case WhispersOfTheOldGods:
				return "Whispers of the Old Gods";
			case TheGrandTournament:
				return "The Grand Tournament";
			case GoblinsVsGnomes:
				return "Goblins vs. Gnomes";
			default:
				return "Non-expansion";
		}
	}

	public static Rarity stringToRarity(String s) throws RarityNotFoundException {
        switch (s) {
			case "LEGENDARY":
				return Rarity.LEGENDARY;
			case "EPIC":
				return Rarity.EPIC;
			case "RARE":
				return Rarity.RARE;
			case "COMMON":
				return COMMON;
			default:
				throw new RarityNotFoundException();
		}
    }

    public static int getCraftCost(Card card) {
    	Rarity r = card.getRarity();
		return getCraftCost(r);
	}

	public static int getCraftCost(Rarity r) {
		switch (r) {
			case COMMON:
				return 40;
			case EPIC:
				return 400;
			case RARE:
				return 100;
			case LEGENDARY:
				return 1600;
			default:
				logger.error("Attempted to get crafting cost of unknown rarity");
				throw new RuntimeException("Attempted to get crafting cost of unknown rarity");
		}
	}

	public static int getDustAmount(Card card) {
		Rarity r = card.getRarity();
		switch (r) {
			case COMMON:
				return 5;
			case EPIC:
				return 20;
			case RARE:
				return 100;
			case LEGENDARY:
				return 400;
			default:
				logger.error("Attempted to get crafting cost of unknown rarity");
				throw new RuntimeException("Attempted to get crafting cost of unknown rarity");
		}
	}



}
