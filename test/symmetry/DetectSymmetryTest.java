/**
 * 
 */
package symmetry;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import testing.TestShape;
import medial_axis.MedialAxisGraph;
import chromosome.ChromosomeCluster;
import chromosome.GeneticSlideImage;

import static org.junit.Assert.fail;

/**
 * @author Robert
 * 
 */
public class DetectSymmetryTest{

	private GeneticSlideImage image;
	private ChromosomeCluster cluster;
	private MedialAxisGraph axisGraph;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	public void setUp()throws Exception {
		String imagePath = ".\\testImages\\testImage1.png";
		cluster = TestShape.getCluster(imagePath);
		image = TestShape.getGeneticSlideImage();
		cluster.createMedialAxisGraph(image);
		axisGraph = cluster.getMedialAxisGraph();
		axisGraph.generateOrthogonals(3, 5);
	}

	/**
	 * Test method for
	 * {@link symmetry.DetectSymmetryTest(medial_axis.MedialAxisGraph, chromosome.GeneticSlideImage)}
	 * .
	 */
	@Test
	public void testDetectWidthSymmetry() {
		DetectSymmetry.detectWidthSymmetry(axisGraph, image);
		fail("Not yet implemented.  DetectSymmetry may be obsolete (11/11/2012)");
	}

}
