package com.revature.hs.card;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class CardSorter implements Comparator<Card> {

	private List<String> order;

	public CardSorter() {
		super();
		order = new LinkedList<>();
		order.add("COMMON");
		order.add("RARE");
		order.add("EPIC");
		order.add("LEGENDARY");
	}

	public int compare(Card c1, Card c2) {
		String c1Name = c1.getRarity().name();
		String c2Name = c2.getRarity().name();
		return order.indexOf(c1Name) - order.indexOf(c2Name);

	}
}
