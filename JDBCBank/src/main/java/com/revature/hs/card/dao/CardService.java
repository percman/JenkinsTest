package com.revature.hs.card.dao;

import com.revature.hs.card.*;
import com.revature.hs.ui.SetOptions;
import org.apache.log4j.Logger;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class CardService {
    private static CardService cc;
	private static final Logger logger = Logger.getLogger(CardService.class);
	private HashMap<String, Card> allCards;
	private static CardDao dao = CardDaoImpl.getInstance();

	private CardService {}

	public static CardService getInstance() {
		if (cc == null) {
			cc = new CardService();
		}
		return cc;
	}

	public static Deque<Card> openPack(String set) {
		return PackSim.getInstance().openPack(set);
	}

	public List<Card> getCardList(SetOptions set, Rarity rarity) {
    	return dao.getAllCards(rarityToString(rarity), convertSetName(set));
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
				return "GILNEAS";
			case KobaldsAndCatacombs:
				return "LOOTAPALOOZA";
			case KnightsOfTheFrozenThrone:
				return "ICECROWN";
			case UnGoro:
				return "UNGORO";
			case MeanStreetsOfGadgetzan:
				return "GANGS";
			case WhispersOfTheOldGods:
				return "OG";
			case TheGrandTournament:
				return "TGT";
			case GoblinsVsGnomes:
				return "GVG";
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
				return Rarity.COMMON;
			default:
				throw new RarityNotFoundException();
		}
	}

	public static String rarityToString(Rarity r) {
		switch (r) {
			case LEGENDARY:
				return "LEGENDARY";
			case EPIC:
				return "EPIC";
			case RARE:
				return "RARE";
			case COMMON:
				return "COMMON";
			default:
				return null;
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

	public static Card getCard(int id) {
		return dao.getCard(id);
	}

	public static Card getCard(Rarity r, String cardSetString) {
		return dao.getCard(rarityToString(r), cardSetString);
	}

	public static List<Card> getAllCards(String cardSetString) {
		return dao.getAllCards(cardSetString);
	}

}
