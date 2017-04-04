/**
 * 
 */
package basic_objects;

import org.junit.Before;
import org.junit.Test;
import java.awt.Point;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * @author Robert
 * 
 */
public class VectorTest{

	private Vector vector;
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	public void setUp()throws Exception {
		vector = new Vector(1, 0);
	}

	/**
	 * Test method for {@link basic_objects.Vector#Vector(double, double)}.
	 */
	@Test
	public void testVector() {
		double xComp = vector.x;
		double yComp = vector.y;

		assertEquals(1.0, xComp,.001);
		assertEquals(0.0, yComp,.001);
	}

	/**
	 * Test method for {@link basic_objects.Vector#rotateVector(double)}.
	 */
	@Test
	public void testRotateVectorDouble() {
		vector.rotateVector(Math.PI / 4);
		double testX = Math.sqrt(2) / 2;
		double testY = Math.sqrt(2) / 2;

		int roundedTestX = (int) Math.round(testX * 100000);
		int roundedTestY = (int) Math.round(testY * 100000);
		int roundedActualX = (int) Math.round(vector.x * 100000);
		int roundedActualY = (int) Math.round(vector.y * 100000);

		assertEquals(roundedTestX, roundedActualX);
		assertEquals(roundedTestY, roundedActualY);
	}

	/**
	 * Test method for {@link basic_objects.Vector#rotateVector(basic_objects.Vector, double)}.
	 */
	@Test
	public void testRotateVectorVectorDouble() {
		Vector v1 = Vector.rotateVector(vector, Math.PI / 4);
		long actualX = Math.round(v1.x * 100000);
		long actualY = Math.round(v1.y * 100000);
		long testX = Math.round(Math.sqrt(2) / 2 * 100000);
		long testY = Math.round(Math.sqrt(2) / 2 * 100000);

		assertEquals(testX, actualX);
		assertEquals(testY, actualY);
	}

	/**
	 * Test method for {@link basic_objects.Vector#normalize(basic_objects.Vector)}.
	 */
	@Test
	public void testNormalize() {
		Vector testVector = new Vector(3, 3);
		testVector = Vector.normalize(testVector);
		double newX = Math.sqrt(2) / 2;
		double newY = Math.sqrt(2) / 2;

		assertEquals(testVector.x, newX,.001);
		assertEquals(testVector.y, newY,.001);
	}

	/**
	 * Test method for {@link basic_objects.Vector#add(basic_objects.Vector, basic_objects.Vector)}.
	 */
	@Test
	public void testAdd() {
		Vector v1 = new Vector(2, 5);
		Vector v2 = new Vector(-4, 9);
		v1 = Vector.add(v1, v2);
		double newX = 2 - 4;
		double newY = 5 + 9;

		assertEquals(v1.x, newX,.001);
		assertEquals(v1.y, newY,.001);
	}
	@Test
	public void testAngleBetween(){
		Vector v1 = new Vector(3, 0);
		Vector v2 = new Vector(0, 4);
		
		double angle=Vector.angleBetween(v1, v2);
		
		assertTrue(angle>1.57&&angle<1.58);
		
		v1 = new Vector(3, 3);
		v2 = new Vector(0, 4);
		
		angle=Vector.angleBetween(v1, v2);
		
		assertTrue(angle>.784&&angle<.786);
		
		v1 = new Vector(3, 0);
		v2 = new Vector(-5, .2);
		
		angle=Vector.angleBetween(v1, v2);
		
		assertTrue(angle>3.1&&angle<3.11);

		v1 = new Vector(3, 0);
		v2 = new Vector(-5, -.2);
		
		angle=Vector.angleBetween(v1, v2);
		
		assertTrue(angle>3.1&&angle<3.11);

	}

	/**
	 * Test method for {@link basic_objects.Vector#getVectorBetweenTwoPoints(Point, Point)}
	 */
	@Test
	public void testMagnitude() {
		Vector v1 = new Vector(1,0);
		assertEquals(1.0, Vector.magnitude(v1),.001);
	}
	
	/**
	 * Test method for {@link basic_objects.Vector#dotProduct(Vector, Vector)
	 */
	@Test
	public void testDotProduct() {
		Vector v1 = new Vector(1,2);
		Vector v2 = new Vector(3,2);
		double testProduct = 7;
		double actualProduct = Vector.dotProduct(v1, v2);
		assertEquals(testProduct, actualProduct,.001);
	}

	/**
	 * Test method for
	 * {@link basic_objects.Vector#getVectorBetweenTwoPoints(java.awt.Point, java.awt.Point)}.
	 */
	@Test
	public void testGetVectorBetweenTwoPoints() {
		Point p1 = new Point(1, 2);
		Point p2 = new Point(3, 4);

		Vector testVector = new Vector(p2.x - p1.x, p2.y - p1.y);
		Vector actualVector = Vector.getVectorBetweenTwoPoints(p1, p2);
		assertEquals(testVector, actualVector);
	}

	/**
	 * Test method for {@link basic_objects.Vector#getDirectionalAngle(Vector, Vector)}.
	 */
	@Test
	public void testGetAngle() {
		Vector v1 = new Vector(2, 1);
		double testAngle = Math.PI / 4;
		Vector v2 = Vector.rotateVector(v1, testAngle);

		double actualAngle = Vector.getDirectionalAngle(v1, v2);

		int roundedTestAngle = (int) Math.round(testAngle * 100000);
		int roundedActualAngle = (int) Math.round(actualAngle * 100000);

		assertEquals(roundedTestAngle, roundedActualAngle);

		testAngle = 5 * Math.PI / 4;
		Vector v3 = Vector.rotateVector(v1, testAngle);
		actualAngle = Vector.getDirectionalAngle(v1, v3);
		assertEquals(testAngle, actualAngle,.001);

	}

}
