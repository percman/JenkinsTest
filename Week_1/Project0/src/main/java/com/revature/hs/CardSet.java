package com.revature.hs;

import org.apache.commons.math3.distribution.EnumeratedDistribution;

import java.util.*;

import static com.revature.hs.Rarity.*;
import static java.lang.Math.min;

public class CardSet {
	private HashMap<Rarity, List<Card>> cards;
	private String name;
	private Random rng;

	// A list that contains each Rarity a number of times equal to
	// percentage to find in a pack / 100
	 private List<Rarity> rarityTable;
	 private List<Rarity> rareOrBetterTable;

	private EnumeratedDistribution withCommons;
	private EnumeratedDistribution noCommons;

	public static final int COMMON_PERCENTAGE = 71;
	public static final int RARE_PERCENTAGE = 23;
	public static final int EPIC_PERCENTAGE = 5;
	public static final int LEGENDARY_PERCENTAGE = 1;

	private void generateRarityTable() {
		HashMap<Rarity, Integer> rarityMap = new HashMap<>();
		rarityTable = new ArrayList<>();
		rareOrBetterTable = new ArrayList<>();

		rarityMap.put(COMMON, COMMON_PERCENTAGE);
		rarityMap.put(RARE, RARE_PERCENTAGE);
		rarityMap.put(EPIC, EPIC_PERCENTAGE);
		rarityMap.put(LEGENDARY, LEGENDARY_PERCENTAGE);

		for (Rarity r : rarityMap.keySet()) {
			for (int i = 0; i < rarityMap.get(r); i++) {
				rarityTable.add(r);
			}
		}
		rarityMap.remove(COMMON);
		for (Rarity r : rarityMap.keySet()) {
			for (int i = 0; i < rarityMap.get(r); i++) {
				rareOrBetterTable.add(r);
			}
		}

	}

	private void generateRarityDistributions() {
		List rarityWeight =
	}

	private Card getCard() {
		int rarityDex = min((int) rng.nextFloat() * 100, 99);
		return getCard(rarityTable.get(rarityDex));
	}

	private Card getCard(Rarity r) {
		List<Card> cardList = cards.get(r);
		return cardList.get(rng.nextInt(cardList.size()));
	}

	private Card getRareOrBetter() {
		int rarityDex = min((int) rng.nextFloat() * 100, 99);
		return getCard(rareOrBetterTable.get(rarityDex));
	}

	// NOTE: This method of pack generation may lead to slightly more rare-or-better cards than
	// you would get opening packs in hearthstone
	public Deque<Card> openPack() {
		Deque<Card> out = new ArrayDeque<>();
		boolean nonCommon = false;
		Card c;

		for (int i = 0; i < 5; i++) {
			c = getCard();
			if (!nonCommon && c.getRarity() != COMMON) {
				nonCommon = true;
			}
			out.offer(c);
		}
		if (!nonCommon) {
			out.pollLast();
			out.offer(getRareOrBetter());
		}

		return out;
	}

}
