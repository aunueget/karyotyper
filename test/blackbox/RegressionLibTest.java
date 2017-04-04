package blackbox;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import medial_axis.RegressionLib;
import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegressionLibTest {
	@Test
	public void testApproxByPolynomial() {
		RegressionLib testObj = new RegressionLib();
		double testVal = 0;

		ArrayList<Point> linearInput = new ArrayList<Point>(Arrays.asList(new Point(0, 0),
				new Point(1, 1)));
		DifferentiableUnivariateFunction linearFunc = testObj.approxByPolynomial(1, linearInput);
		assertEquals(2.0, linearFunc.value(2),.001);
		assertEquals(0.0, testObj.getFitError(),.001);

		ArrayList<Point> cubicInput = new ArrayList<Point>(Arrays.asList(new Point(-2, -8),
				new Point(-1, -1), new Point(0, 0), new Point(2, 8)));
		DifferentiableUnivariateFunction cubicFunc = testObj.approxByPolynomial(3, cubicInput);
		assertEquals(-27.0, cubicFunc.value(-3),.001);
		assertEquals(0.0, testObj.getFitError(),.001);

		ArrayList<Point> dirtyInput = new ArrayList<Point>(Arrays.asList(new Point(-200, -10),
				new Point(-1, -100), new Point(0, 30), new Point(10, 9)));
		DifferentiableUnivariateFunction approxFunc = testObj.approxByPolynomial(3, dirtyInput);
		testVal = approxFunc.value(-3);
		assertTrue(String.format("Actual value: %f", testVal), Math.abs(-427.351 - testVal) < 0.001);
		assertTrue(String.format("Actual value: %f", testObj.getFitError()), testObj.getFitError() < 0.001);
	}
}
