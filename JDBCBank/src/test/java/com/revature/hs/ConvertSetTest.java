package com.revature.hs;

import com.revature.hs.card.CardCollector;
import org.junit.Test;

import static com.revature.hs.ui.SetOptions.*;
import static org.junit.Assert.*;

public class ConvertSetTest {

	@Test
	public void convertSetName() {
		assertEquals("The Witchwood", CardCollector.convertSetName(Witchwood));
	}

	@Test
	public void convertSetName2() {
		assertEquals("Kobalds and Catacombs", CardCollector.convertSetName(KobaldsAndCatacombs));
	}

	@Test
	public void convertSetName3() {
		assertEquals("Knights of the Frozen Throne", CardCollector.convertSetName(KnightsOfTheFrozenThrone));
	}

	@Test
	public void convertSetName4() {
		assertEquals("Journey to Un'Goro", CardCollector.convertSetName(UnGoro));
	}

	@Test
	public void convertSetName5() {
		assertEquals("Mean Streets of Gadgetzan", CardCollector.convertSetName(MeanStreetsOfGadgetzan));
	}

	@Test
	public void convertSetName6() {
		assertEquals("Whispers of the Old Gods", CardCollector.convertSetName(WhispersOfTheOldGods));
	}

	@Test
	public void convertSetName7() {
		assertEquals("The Grand Tournament", CardCollector.convertSetName(TheGrandTournament));
	}

	@Test
	public void convertSetName8() {
		assertEquals("Goblins vs. Gnomes", CardCollector.convertSetName(GoblinsVsGnomes));
	}

}