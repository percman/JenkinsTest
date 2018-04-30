package com.revature.hs.card;

import com.revature.hs.card.dao.CardService;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;
import org.apache.log4j.Logger;

import java.util.*;

import static com.revature.hs.card.Rarity.*;
import static java.lang.Math.min;

public class PackSim {
	private static final Logger logger = Logger.getLogger(PackSim.class);

	private EnumeratedDistribution withCommons;
	private EnumeratedDistribution noCommons;
	private Random rng;
	private String name;
	private static PackSim instance;

	public static final double COMMON_PERCENTAGE = 88.75;
	public static final double RARE_PERCENTAGE = 8.922;
	public static final double EPIC_PERCENTAGE = 1.939;
	public static final double LEGENDARY_PERCENTAGE = .388;

	public static final double NC_RARE_PERCENTAGE = 23.0;
	public static final double NC_EPIC_PERCENTAGE = 5.0;
	public static final double NC_LEGENDARY_PERCENTAGE = 1.0;

	private PackSim() {
		logger.info("Preparing PackSim ");
		logger.debug("Generating Rarity Distributions");
		generateRarityDistributions();
		logger.debug("Generating RNG");
		rng = new Random();
	}

	public static PackSim getInstance() {
		if (instance == null) {
			instance = new PackSim();
		}
		return instance;
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

	private Card getCard(String setName) {
		return CardService.getCard((Rarity) withCommons.sample(), setName);
	}


	private Card getRareOrBetter(String setName) {
		return CardService.getCard((Rarity) noCommons.sample(), setName);
	}

	//TODO: test the probability distribution to confirm it conforms to that of Hearthstone.
	public Deque<Card> openPack(String setName) {
		Deque<Card> out = new ArrayDeque<>();

		for (int i = 0; i < 4; i++) {
			out.offer(getCard(setName));
		}

		out.offer(getRareOrBetter(setName));

		return out;
	}

}
