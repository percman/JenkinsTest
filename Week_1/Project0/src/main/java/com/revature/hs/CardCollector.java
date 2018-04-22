package com.revature.hs;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CardCollector {
    private static CardCollector cc;
    private List<String> setList;
	private static final Logger logger = Logger.getLogger(CardCollector.class);

    public static CardCollector getInstance() {
    	if (cc == null) {
    		cc = new CardCollector();
		}
		return cc;
	}

	private HashMap<Rarity, List<Card>> prepareSet() {
		HashMap<Rarity, List<Card>> out = new HashMap<>();
    	for (Rarity r: Rarity.values()) {
			out.put(r, new LinkedList<Card>());
		}
		return out;
	}

	private void initializeCardDB(JSONArray jsa) {
		JSONObject js;
		Card c;
		String rarityString;
    	for (Object o: jsa) {
			js = (JSONObject) o;
			rarityString = js.getString("rarity");
			if ("FREE".equals(rarityString)) {
				continue;
			}
		}
	}

	private String convertSetName(String n) {
    	if(n.equals("GILNEAS")) {
    		return "The Witchwood";
		}
		if(n.equals("LOOTAPALOOZA")) {
    		return "Kobalds and Catacombs";
		}
		if (n.equals("ICECROWN")) {
    		return "Knights of the Frozen Throne";
		}
		if (n.equals("UNGORO")) {
    		return "Journey to Un'Goro";
		}
		if (n.equals("GANGS")) {
    		return "Mean Streets of Gadgetzan";
		}
		if (n.equals("KARA")) {
    		return "One Night in Karazhan";
		}
		if (n.equals("OG")) {
    		return "Whispers of the Old Gods";
		}
		if (n.equals())
	}

	private CardCollector() {
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
	}

	public static Rarity stringToRarity(String s) throws RarityNotFoundException {
        if (s.equals("LEGENDARY")) {
            return Rarity.LEGENDARY;
        }
        if (s.equals("EPIC")){
            return Rarity.EPIC;
        }
        if (s.equals("RARE")) {
            return Rarity.RARE;
        }
        if (s.equals("COMMON")) {
            return Rarity.COMMON;
        }
        throw new RarityNotFoundException();
    }

}
