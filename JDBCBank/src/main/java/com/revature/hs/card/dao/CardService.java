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
    private HashMap<String, PackSim> setMap;
	private static final Logger logger = Logger.getLogger(CardService.class);
	private HashMap<String, Card> allCards;


	public static Deque<Card> openPack(String set) {

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
				return Rarity.COMMON;
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
