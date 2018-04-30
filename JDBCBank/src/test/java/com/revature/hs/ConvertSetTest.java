package com.revature.hs;

import com.revature.hs.card.dao.CardService;
import org.junit.Test;

import static com.revature.hs.ui.SetOptions.*;
import static org.junit.Assert.*;

public class ConvertSetTest {

	@Test
	public void convertSetName() {
		assertEquals("GILNEAS", CardService.convertSetName(Witchwood));
	}

	@Test
	public void convertSetName2() {
		assertEquals("LOOTAPALOOZA", CardService.convertSetName(KobaldsAndCatacombs));
	}

	@Test
	public void convertSetName3() {
		assertEquals("ICECROWN", CardService.convertSetName(KnightsOfTheFrozenThrone));
	}

	@Test
	public void convertSetName4() {
		assertEquals("UNGORO", CardService.convertSetName(UnGoro));
	}

	@Test
	public void convertSetName5() {
		assertEquals("GANGS", CardService.convertSetName(MeanStreetsOfGadgetzan));
	}

	@Test
	public void convertSetName6() {
		assertEquals("OG", CardService.convertSetName(WhispersOfTheOldGods));
	}

	@Test
	public void convertSetName7() {
		assertEquals("TGT", CardService.convertSetName(TheGrandTournament));
	}

	@Test
	public void convertSetName8() {
		assertEquals("GVG", CardService.convertSetName(GoblinsVsGnomes));
	}

}