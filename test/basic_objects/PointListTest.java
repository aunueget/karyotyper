package basic_objects;

import java.awt.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PointListTest{
	@Test
	public void testSetList() {
		PointList tempList = new PointList(new Point(10, 2), 2);
		tempList.addPoint(new Point(3, 2), 2);
		tempList.addPoint(new Point(5, 1), 2);
		tempList.addPoint(new Point(6, 10), 5);

		PointList pointList = new PointList();
		pointList.setList(tempList);

		assertEquals(pointList.getDistanceFromEdge(), 2);
		assertEquals(pointList.getList().size(), 3);
		assertTrue(pointList.getList().contains(new Point(5, 1)));
		assertFalse(pointList.getList().contains(new Point(6, 10)));

	}
	@Test
	public void testAddPoint() {
		PointList tempList = new PointList(new Point(10, 2), 2);
		assertTrue(tempList.addPoint(new Point(3, 2), 2));
		assertTrue(tempList.addPoint(new Point(5, 1), 2));
		assertFalse(tempList.addPoint(new Point(6, 10), 5));
		assertFalse(tempList.addPoint(new Point(6, 11), 22));

	}

}
