package com.revature.hs;

import com.revature.hs.card.dao.CardService;
import org.junit.Test;

import static com.revature.hs.ui.SetOptions.*;
import static org.junit.Assert.*;

public class ConvertSetTest {

	@Test
	public void convertSetName() {
		assertEquals("The Witchwood", CardService.convertSetName(Witchwood));
	}

	@Test
	public void convertSetName2() {
		assertEquals("Kobalds and Catacombs", CardService.convertSetName(KobaldsAndCatacombs));
	}

	@Test
	public void convertSetName3() {
		assertEquals("Knights of the Frozen Throne", CardService.convertSetName(KnightsOfTheFrozenThrone));
	}

	@Test
	public void convertSetName4() {
		assertEquals("Journey to Un'Goro", CardService.convertSetName(UnGoro));
	}

	@Test
	public void convertSetName5() {
		assertEquals("Mean Streets of Gadgetzan", CardService.convertSetName(MeanStreetsOfGadgetzan));
	}

	@Test
	public void convertSetName6() {
		assertEquals("Whispers of the Old Gods", CardService.convertSetName(WhispersOfTheOldGods));
	}

	@Test
	public void convertSetName7() {
		assertEquals("The Grand Tournament", CardService.convertSetName(TheGrandTournament));
	}

	@Test
	public void convertSetName8() {
		assertEquals("Goblins vs. Gnomes", CardService.convertSetName(GoblinsVsGnomes));
	}

}