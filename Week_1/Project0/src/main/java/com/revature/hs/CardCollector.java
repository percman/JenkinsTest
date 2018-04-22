package com.revature.hs;

public class CardCollector {
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
