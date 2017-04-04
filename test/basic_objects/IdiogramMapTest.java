/**
 * 
 */
package basic_objects;

import java.io.File;
import idiogram.IdiogramMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * @author Robert
 *
 */
public class IdiogramMapTest {

	private IdiogramMap map;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	public void setUp() throws Exception {
		map = new IdiogramMap(new File(".\\ChromosomeIdiogramSheet.csv"));

	}
	
	/**
	 * Test method for {@link idiogram.IdiogramMap#get(Idiogram)}.
	 */
	@Test
	public void testGet() {
//		Chromosome chromosome = new Chromosome(10);
//		chromosome.add(new ChromosomeBand(ChromosomeBand.Type.WHITE, 21));
//		chromosome.add(new ChromosomeBand(ChromosomeBand.Type.BLACK, 10));
//		
//		int number = map.get(chromosome);
//		
//		assertEquals(number, 11);
		fail("Waiting for decision on implementation.");
	}

}
