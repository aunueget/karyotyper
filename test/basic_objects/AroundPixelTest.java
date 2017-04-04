package basic_objects;

import java.util.LinkedList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AroundPixelTest {

	@Test
	public void testGetPositionsBetweenNeg() {
		LinkedList<Integer> posList = AroundPixel.getPositionsBetweenNeg(0, 4);
		assertEquals(posList.size(), 3);
		assertTrue(posList.contains(7));
		assertTrue(posList.contains(6));
		assertTrue(posList.contains(5));
		assertFalse(posList.contains(4));
		assertFalse(posList.contains(3));
		assertFalse(posList.contains(2));
		assertFalse(posList.contains(1));
		assertFalse(posList.contains(0));

	}
	@Test
	public void testGetPositionsBetweenPlus() {
		LinkedList<Integer> posList = AroundPixel.getPositionsBetweenPlus(0, 4);
		assertEquals(posList.size(), 3);
		assertTrue(posList.contains(1));
		assertTrue(posList.contains(2));
		assertTrue(posList.contains(3));
		assertFalse(posList.contains(4));
		assertFalse(posList.contains(5));
		assertFalse(posList.contains(6));
		assertFalse(posList.contains(7));
		assertFalse(posList.contains(0));

	}
	@Test
	public void testGetOppisitPos() {
		assertEquals(4, AroundPixel.getOppisitePos(0));
		assertEquals(5, AroundPixel.getOppisitePos(1));
		assertEquals(6, AroundPixel.getOppisitePos(2));
		assertEquals(7, AroundPixel.getOppisitePos(3));
		assertEquals(0, AroundPixel.getOppisitePos(4));
		assertEquals(1, AroundPixel.getOppisitePos(5));
		assertEquals(2, AroundPixel.getOppisitePos(6));
		assertEquals(3, AroundPixel.getOppisitePos(7));

	}
	@Test
	public void testHandleLoop() {
		assertEquals(7, AroundPixel.handleLoop(-1));
		assertEquals(6, AroundPixel.handleLoop(-2));
		assertEquals(5, AroundPixel.handleLoop(-3));
		assertEquals(0, AroundPixel.handleLoop(8));
		assertEquals(1, AroundPixel.handleLoop(9));
		assertEquals(2, AroundPixel.handleLoop(10));
		assertEquals(3, AroundPixel.handleLoop(11));

	}
}
