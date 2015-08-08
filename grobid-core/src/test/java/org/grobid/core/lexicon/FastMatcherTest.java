package org.grobid.core.lexicon;

import java.io.File;
import java.util.List;

import org.grobid.core.mock.MockContext;
import org.grobid.core.utilities.GrobidProperties;
import org.grobid.core.utilities.OffsetPosition;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class FastMatcherTest {
	
	private static LexiconImpl lexicon;
	
	@BeforeClass
	public static void setInitialContext() throws Exception{
		MockContext.setInitialContext();
		GrobidProperties.getInstance();
		lexicon = LexiconImpl.getInstance();
	}
	
	@AfterClass
	public static void destroyInitialContext() throws Exception {
		MockContext.destroyInitialContext();
	}

	@Test
	public void testFastMatcher() {
		new FastMatcher(new File(
				GrobidProperties.get_GROBID_HOME_PATH()
						+ "/lexicon/journals/abbrev_journals.txt"));
	}
	
	@Test
	public void testinJournalNames(){
		List<OffsetPosition> inJournalNames = lexicon.inJournalNames("abc <p> Economics </p>");
		System.out.println(inJournalNames);
	}
	

}
