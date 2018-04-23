package com.revature.hs;

import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;
import org.apache.log4j.Logger;

import java.util.*;

import static com.revature.hs.Rarity.*;
import static java.lang.Math.min;

public class CardSet {
	private HashMap<Rarity, List<Card>> cards;
	private String name;
	private static final Logger logger = Logger.getLogger(CardSet.class);

	private EnumeratedDistribution withCommons;
	private EnumeratedDistribution noCommons;
	private Random rng;

	public static final double COMMON_PERCENTAGE = 88.75;
	public static final double RARE_PERCENTAGE = 8.922;
	public static final double EPIC_PERCENTAGE = 1.939;
	public static final double LEGENDARY_PERCENTAGE = .388;

	public static final double NC_RARE_PERCENTAGE = 23.0;
	public static final double NC_EPIC_PERCENTAGE = 5.0;
	public static final double NC_LEGENDARY_PERCENTAGE = 1.0;

	public CardSet(String name) {
		logger.info("Preparing CardSet " + name);
		this.name = name;
		logger.debug("Generating Rarity Distributions");
		generateRarityDistributions();
		logger.debug("Preparing Rarity Lists");
		prepareRarityLists();
		logger.debug("Generating RNG");
		rng = new Random();
	}

	public void addCard(Card card) {
		cards.get(card.getRarity()).add(card);
	}

	private void prepareRarityLists() {
		cards = new HashMap<>();
		for (Rarity r: Rarity.values()) {
			cards.put(r, new LinkedList<>());
		}
	}

	private void generateRarityDistributions() {
		generateNoCommons();
		generateWithCommons();
	}

	private void generateWithCommons() {
		List<Pair<Rarity, Double>> weightList = new LinkedList<>();
		weightList.add(new Pair(COMMON, COMMON_PERCENTAGE));
		weightList.add(new Pair(RARE, RARE_PERCENTAGE));
		weightList.add(new Pair(EPIC, EPIC_PERCENTAGE));
		weightList.add(new Pair(LEGENDARY, LEGENDARY_PERCENTAGE));
		withCommons = new EnumeratedDistribution(weightList);
	}

	private void generateNoCommons() {
		List<Pair<Rarity, Double>> weightList = new LinkedList<>();
		weightList.add(new Pair(RARE, NC_RARE_PERCENTAGE));
		weightList.add(new Pair(EPIC, NC_EPIC_PERCENTAGE));
		weightList.add(new Pair(LEGENDARY, NC_LEGENDARY_PERCENTAGE));
		noCommons = new EnumeratedDistribution(weightList);
	}

	private Card getCard() {
		return getCard((Rarity) withCommons.sample());
	}

	private Card getCard(Rarity r) {
		List<Card> cardList = cards.get(r);
		return cardList.get(rng.nextInt(cardList.size()));
	}

	private Card getRareOrBetter() {
		return getCard((Rarity) noCommons.sample());
	}

	//TODO: test the probability distribution to confirm it conforms to that of Hearthstone.
	public Deque<Card> openPack() {
		Deque<Card> out = new ArrayDeque<>();

		for (int i = 0; i < 4; i++) {
			out.offer(getCard());
		}

		out.offer(getRareOrBetter());

		return out;
	}

}
