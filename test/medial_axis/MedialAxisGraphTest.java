package medial_axis;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import basic_objects.Vertex;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MedialAxisGraphTest{
	private LinkedList<Point> straightLine;
	private LinkedList<Point> diagonalLine;
	private LinkedList<Point> curvedLine;
	private LinkedList<Point> oneIntersection4branches;
	private LinkedList<Point> threeSegmentGraph;
	private LinkedList<Point> oneIntersectionWithTinyLoop;
	private LinkedList<Point> chunk4TrimWith3brances;
	private DistanceMap distanceMap;

	@Before
	public void setUp()throws Exception {
		distanceMap = new DistanceMap(15, 15);
		for (int i = 0; i < distanceMap.getWidth(); i++) {
			for (int j = 0; j < distanceMap.getHeight(); j++) {
				if (j == distanceMap.getHeight() - 1 || i == distanceMap.getWidth() - 1) {
					distanceMap.setDistanceFormEdge(new Point(i, j), 0);
				} else {
					distanceMap.setDistanceFormEdge(new Point(i, j), 10);
				}
			}
		}
		straightLine = new LinkedList<Point>();
		straightLine.add(new Point(0, 0));
		straightLine.add(new Point(0, 1));
		straightLine.add(new Point(0, 2));
		straightLine.add(new Point(0, 3));
		straightLine.add(new Point(0, 4));
		straightLine.add(new Point(0, 5));
		straightLine.add(new Point(0, 6));
		straightLine.add(new Point(0, 7));
		straightLine.add(new Point(0, 8));
		straightLine.add(new Point(0, 9));
		straightLine.add(new Point(0, 10));
		straightLine.add(new Point(0, 11));

		curvedLine = new LinkedList<Point>();
		curvedLine.add(new Point(0, 0));
		curvedLine.add(new Point(0, 1));
		curvedLine.add(new Point(0, 2));
		curvedLine.add(new Point(0, 3));
		curvedLine.add(new Point(1, 3));
		curvedLine.add(new Point(1, 4));
		curvedLine.add(new Point(2, 4));
		curvedLine.add(new Point(2, 5));
		curvedLine.add(new Point(3, 5));
		curvedLine.add(new Point(3, 6));
		curvedLine.add(new Point(3, 7));
		curvedLine.add(new Point(2, 8));
		curvedLine.add(new Point(2, 9));
		curvedLine.add(new Point(1, 9));
		curvedLine.add(new Point(1, 10));
		curvedLine.add(new Point(0, 10));

		diagonalLine = new LinkedList<Point>();
		diagonalLine.add(new Point(0, 0));
		diagonalLine.add(new Point(9, 9));
		diagonalLine.add(new Point(1, 1));
		diagonalLine.add(new Point(8, 8));
		diagonalLine.add(new Point(2, 2));
		diagonalLine.add(new Point(6, 6));
		diagonalLine.add(new Point(3, 3));
		diagonalLine.add(new Point(4, 4));
		diagonalLine.add(new Point(5, 5));
		diagonalLine.add(new Point(7, 7));
		diagonalLine.add(new Point(10, 10));
		oneIntersection4branches = new LinkedList<Point>();
		// intersection
		oneIntersection4branches.add(new Point(10, 10));
		// branch1
		oneIntersection4branches.add(new Point(9, 9));
		oneIntersection4branches.add(new Point(8, 8));
		oneIntersection4branches.add(new Point(7, 7));
		oneIntersection4branches.add(new Point(6, 6));
		oneIntersection4branches.add(new Point(5, 5));
		oneIntersection4branches.add(new Point(4, 4));
		oneIntersection4branches.add(new Point(3, 3));
		oneIntersection4branches.add(new Point(2, 2));
		// branch2
		oneIntersection4branches.add(new Point(9, 11));
		oneIntersection4branches.add(new Point(8, 11));
		oneIntersection4branches.add(new Point(7, 11));
		oneIntersection4branches.add(new Point(6, 11));
		oneIntersection4branches.add(new Point(5, 11));
		oneIntersection4branches.add(new Point(4, 11));
		oneIntersection4branches.add(new Point(3, 11));
		// branch3 touches distance map 0
		oneIntersection4branches.add(new Point(11, 11));
		oneIntersection4branches.add(new Point(12, 12));
		oneIntersection4branches.add(new Point(13, 13));
		oneIntersection4branches.add(new Point(14, 14));
		// branch4 touches distance map 0
		oneIntersection4branches.add(new Point(11, 9));
		oneIntersection4branches.add(new Point(11, 8));
		oneIntersection4branches.add(new Point(11, 7));
		oneIntersection4branches.add(new Point(11, 6));
		oneIntersection4branches.add(new Point(11, 5));
		oneIntersection4branches.add(new Point(12, 4));
		oneIntersection4branches.add(new Point(13, 5));
		oneIntersection4branches.add(new Point(14, 6));

		threeSegmentGraph = new LinkedList<Point>();
		// segment1 touches distance map 0
		threeSegmentGraph.add(new Point(11, 11));
		threeSegmentGraph.add(new Point(12, 12));
		threeSegmentGraph.add(new Point(13, 13));
		threeSegmentGraph.add(new Point(14, 14));
		// segment2 touches distance map 0
		threeSegmentGraph.add(new Point(11, 9));
		threeSegmentGraph.add(new Point(11, 8));
		threeSegmentGraph.add(new Point(11, 7));
		threeSegmentGraph.add(new Point(11, 6));
		// segment3
		threeSegmentGraph.add(new Point(9, 11));
		threeSegmentGraph.add(new Point(8, 11));
		threeSegmentGraph.add(new Point(7, 11));
		threeSegmentGraph.add(new Point(6, 11));
		threeSegmentGraph.add(new Point(5, 11));
		threeSegmentGraph.add(new Point(4, 11));
		threeSegmentGraph.add(new Point(3, 11));

		this.oneIntersectionWithTinyLoop = new LinkedList<Point>();
		this.oneIntersectionWithTinyLoop.add(new Point(4, 4));
		this.oneIntersectionWithTinyLoop.add(new Point(5, 3));
		this.oneIntersectionWithTinyLoop.add(new Point(5, 5));
		this.oneIntersectionWithTinyLoop.add(new Point(6, 4));
		this.oneIntersectionWithTinyLoop.add(new Point(7, 4));
		this.oneIntersectionWithTinyLoop.add(new Point(5, 6));

		chunk4TrimWith3brances = new LinkedList<Point>();
		// segment1
		this.chunk4TrimWith3brances.add(new Point(11, 11));
		this.chunk4TrimWith3brances.add(new Point(12, 12));
		this.chunk4TrimWith3brances.add(new Point(13, 13));
		this.chunk4TrimWith3brances.add(new Point(14, 14));
		// segment2 touches distance map 0
		this.chunk4TrimWith3brances.add(new Point(11, 9));
		this.chunk4TrimWith3brances.add(new Point(11, 8));
		this.chunk4TrimWith3brances.add(new Point(11, 7));
		this.chunk4TrimWith3brances.add(new Point(11, 6));
		// segment3
		this.chunk4TrimWith3brances.add(new Point(9, 11));
		this.chunk4TrimWith3brances.add(new Point(8, 11));
		this.chunk4TrimWith3brances.add(new Point(7, 11));
		this.chunk4TrimWith3brances.add(new Point(6, 11));
		this.chunk4TrimWith3brances.add(new Point(5, 11));
		this.chunk4TrimWith3brances.add(new Point(4, 11));
		this.chunk4TrimWith3brances.add(new Point(3, 11));
		// chunk connecting segments
		this.chunk4TrimWith3brances.add(new Point(10, 11));
		this.chunk4TrimWith3brances.add(new Point(10, 10));
		this.chunk4TrimWith3brances.add(new Point(11, 10));
		this.chunk4TrimWith3brances.add(new Point(12, 10));
		this.chunk4TrimWith3brances.add(new Point(12, 11));
		this.chunk4TrimWith3brances.add(new Point(10, 12));
		this.chunk4TrimWith3brances.add(new Point(11, 12));

	}
	@Test
	public void testBuildGraph() {

		MedialAxisGraph testGraph = new MedialAxisGraph();
		testGraph.buildGraph(this.oneIntersection4branches, distanceMap);
		assertEquals(testGraph.getAxisGraph().size(), 28);
		assertEquals(testGraph.getIntersectionCount(testGraph.getAxisGraph()), 1);
		assertEquals(testGraph.getSegmentCount(), 1);

		testGraph = new MedialAxisGraph();
		testGraph.buildGraph(this.straightLine, distanceMap);
		assertEquals(testGraph.getAxisGraph().size(), 12);
		assertEquals(testGraph.getIntersectionCount(testGraph.getAxisGraph()), 0);
		assertEquals(testGraph.getSegmentCount(), 1);

		testGraph = new MedialAxisGraph();
		testGraph.buildGraph(this.threeSegmentGraph, distanceMap);
		assertEquals(testGraph.getAxisGraph().size(), 15);
		assertEquals(testGraph.getIntersectionCount(testGraph.getAxisGraph()), 0);
		assertEquals(testGraph.getSegmentCount(), 3);

	}
	@Test
	public void testRemoveSegments() {

		LinkedList<Point> pointList;
		MedialAxisGraph testGraph = new MedialAxisGraph();
		testGraph.buildGraph(this.oneIntersection4branches, distanceMap);
		testGraph.setChromoWidth(13.5);
		testGraph.removeSegments(5, -1);
		pointList = testGraph.getMedialAxisFromGraph();
		assertTrue(pointList.contains(new Point(5, 11)));
		assertTrue(pointList.contains(new Point(5, 5)));
		assertTrue(pointList.contains(new Point(11, 6)));
		assertFalse(pointList.contains(new Point(12, 12)));
		assertEquals(this.oneIntersection4branches.size() - 4, pointList.size());

	}
	@Test
	public void testRemoveUnconnectedSegments() {

		LinkedList<Point> pointList;
		MedialAxisGraph testGraph = new MedialAxisGraph();
		testGraph.buildGraph(this.threeSegmentGraph, distanceMap);
		assertEquals(
				testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(9, 11)))
						.getMySegement(), 2);
		testGraph.removeUnconnectedSegments(4);
		pointList = testGraph.getMedialAxisFromGraph();
		assertFalse(pointList.contains(new Point(11, 11)));
		assertFalse(pointList.contains(new Point(11, 7)));
		assertTrue(pointList.contains(new Point(8, 11)));
		assertEquals(this.threeSegmentGraph.size() - pointList.size(), 8);
		assertEquals(testGraph.getSegmentCount(), 1);
		assertEquals(
				testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(9, 11)))
						.getMySegement(), 0);
		assertEquals(
				testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(8, 11)))
						.getMySegement(), 0);
		assertEquals(
				testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(7, 11)))
						.getMySegement(), 0);
		assertEquals(
				testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(6, 11)))
						.getMySegement(), 0);
		assertEquals(
				testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(5, 11)))
						.getMySegement(), 0);
		assertEquals(
				testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(4, 11)))
						.getMySegement(), 0);
		assertEquals(
				testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(3, 11)))
						.getMySegement(), 0);

	}
	@Test
	public void testAddVertex() {
		LinkedList<Point> pointList;
		MedialAxisGraph testGraph = new MedialAxisGraph();
		testGraph.buildGraph(this.threeSegmentGraph, distanceMap);
		assertEquals(testGraph.getSegmentCount(), 3);
		assertEquals(testGraph.getIntersectionCount(testGraph.getAxisGraph()), 0);
		testGraph.addVertex(new Vertex(new Point(10, 10), 10));
		pointList = testGraph.getMedialAxisFromGraph();
		assertTrue(pointList.contains(new Point(10, 10)));
		assertEquals(testGraph.getSegmentCount(), 1);
		assertEquals(testGraph.getIntersectionCount(testGraph.getAxisGraph()), 1);

	}	@Test
	public void testRemoveVertex(){
		LinkedList<Point> pointList;
		MedialAxisGraph testGraph = new MedialAxisGraph();
		testGraph.buildGraph(this.chunk4TrimWith3brances, distanceMap);
		assertTrue(testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(9,11))).
				isChild(testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(10,11)))));
		assertTrue(testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(10,11))).
				isChild(testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(9,11)))));
		testGraph.removeVertex(testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(10,11))));
		assertFalse(testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(9,11))).
				isChild(new Point(10,11)));
		assertEquals(testGraph.indexOfVertexWithPoint(new Point(10,11)),-1);
		

	}
	@Test
	public void testGetSegment() {

		LinkedList<Point> pointList;

		MedialAxisGraph testGraph = new MedialAxisGraph();
		LinkedList<Vertex> testSegment = new LinkedList<Vertex>();
		testGraph.buildGraph(straightLine, distanceMap);
		testSegment.add(testGraph.getAxisGraph().get(0));
		testGraph.getSegment(testSegment, 0,true);
		pointList = testGraph.getMedialAxisFromGraph();
		assertTrue(pointList.contains(new Point(0, 11)));
		assertEquals(pointList.size(), 12);

		testGraph = new MedialAxisGraph();
		testSegment = new LinkedList<Vertex>();
		testGraph.buildGraph(diagonalLine, distanceMap);
		testSegment.add(testGraph.getAxisGraph().get(0));
		testGraph.getSegment(testSegment, 0,true);
		pointList = testGraph.getMedialAxisFromGraph();
		assertTrue(pointList.contains(new Point(10, 10)));
		assertEquals(pointList.size(), 11);

		testGraph = new MedialAxisGraph();
		testSegment = new LinkedList<Vertex>();
		testGraph.buildGraph(curvedLine, distanceMap);
		testSegment.add(testGraph.getAxisGraph().get(0));
		testGraph.getSegment(testSegment, 0,true);
		pointList = testGraph.getMedialAxisFromGraph();
		assertTrue(pointList.contains(new Point(0, 10)));
		assertEquals(pointList.size(), 16);
		
		testGraph = new MedialAxisGraph();
		testGraph.buildGraph(this.diagonalLine, distanceMap);
		LinkedList<Vertex> segment=new LinkedList<Vertex>();
		segment.add(testGraph.getAxisGraph().get(3));
		LinkedList<Vertex> orderedList=testGraph.getSegment(segment, 0,true);
		assertEquals(testGraph.getAxisGraph().size(),orderedList.size());
		assertEquals(orderedList.get(0).getPoint(), new Point(0,0));
		assertEquals(orderedList.get(1).getPoint(), new Point(1,1));
		assertEquals(orderedList.get(2).getPoint(), new Point(2,2));
		assertEquals(orderedList.get(3).getPoint(), new Point(3,3));
		assertEquals(orderedList.get(4).getPoint(), new Point(4,4));
		assertEquals(orderedList.get(5).getPoint(), new Point(5,5));
		assertEquals(orderedList.get(6).getPoint(), new Point(6,6));
		assertEquals(orderedList.get(7).getPoint(), new Point(7,7));
		assertEquals(orderedList.get(8).getPoint(), new Point(8,8));
		assertEquals(orderedList.get(9).getPoint(), new Point(9,9));
		assertEquals(orderedList.get(10).getPoint(), new Point(10,10));
	}
	@Test
	public void testCheckTinyLoop() {
		MedialAxisGraph testGraph = new MedialAxisGraph();
		testGraph.buildGraph(this.oneIntersection4branches, distanceMap);
		Vertex tempVertex = testGraph.checkTinyLoop(testGraph.getIntersections(
				testGraph.getAxisGraph()).get(0));
		assertEquals(null, tempVertex);

		MedialAxisGraph testGraph2 = new MedialAxisGraph();
		testGraph2.buildGraph(this.oneIntersectionWithTinyLoop, distanceMap);
		tempVertex = testGraph2.checkTinyLoop(testGraph2
				.getIntersections(testGraph2.getAxisGraph()).get(0));
		assertEquals(new Point(4, 4), tempVertex.getPoint());

	}
	@Test
	public void testFillInSkeleton() {
		LinkedList<Point> pointList;
		MedialAxisGraph testGraph = new MedialAxisGraph();
		testGraph.buildGraph(this.threeSegmentGraph, distanceMap);
		testGraph.setDistanceMap(distanceMap);
		testGraph.setMedialAxis(testGraph.getAxisGraph());
		assertEquals(testGraph.getSegmentCount(), 3);
		assertEquals(testGraph.getIntersectionCount(testGraph.getAxisGraph()), 0);
		testGraph.fillInSkeleton(14);
		pointList = testGraph.getMedialAxisFromGraph();
		assertTrue(pointList.contains(new Point(10, 10)));
		assertEquals(testGraph.getSegmentCount(), 1);
		// assertEquals(testGraph.getIntersectionCount(testGraph.getAxisGraph()), 1);
		for (int i = 0; i < testGraph.getAxisGraph().size(); i++) {
			System.out.print(testGraph.getAxisGraph().get(i).getPoint().toString() + "--");
		}
	}
	@Test
	public void testGetBridgePoint() {
		MedialAxisGraph testGraph = new MedialAxisGraph();
		testGraph.buildGraph(this.threeSegmentGraph, distanceMap);
		testGraph.setMedialAxis(testGraph.getAxisGraph());
		assertEquals(testGraph.getSegmentCount(), 3);
		assertEquals(testGraph.getIntersectionCount(testGraph.getAxisGraph()), 0);
		assertEquals(new Point(10, 10), testGraph.getBridgePoint(7, new Point(9, 11)));
		assertEquals(new Point(10, 11), testGraph.getBridgePoint(6, new Point(9, 11)));
		assertEquals(new Point(10, 11), testGraph.getBridgePoint(6, new Point(9, 11)));
	}
	@Test
	public void testCheckForMostNewConnection() {
		MedialAxisGraph testGraph = new MedialAxisGraph();
		testGraph.buildGraph(this.threeSegmentGraph, distanceMap);
		testGraph.setMedialAxis(testGraph.getAxisGraph());
		assertEquals(testGraph.getSegmentCount(), 3);
		assertEquals(testGraph.getIntersectionCount(testGraph.getAxisGraph()), 0);
		assertEquals(2, testGraph.checkForMostNewConnection(7, new Point(9, 11)));
		assertEquals(1, testGraph.checkForMostNewConnection(6, new Point(9, 11)));
		assertEquals(1, testGraph.checkForMostNewConnection(6, new Point(9, 11)));
	}
	@Test
	public void testSameSegment() {
		LinkedList<Point> pointList;
		MedialAxisGraph testGraph = new MedialAxisGraph();
		testGraph.buildGraph(this.threeSegmentGraph, distanceMap);
		assertEquals(testGraph.getSegmentCount(), 3);
		assertEquals(testGraph.getIntersectionCount(testGraph.getAxisGraph()), 0);
		pointList = testGraph.getMedialAxisFromGraph();
		assertTrue(testGraph.sameSegment(new Point(9, 11), new Point(8, 11)));
		assertFalse(testGraph.sameSegment(new Point(9, 11), new Point(11, 9)));
		assertFalse(testGraph.sameSegment(new Point(9, 11), new Point(11, 11)));

	}
	@Test
	public void testTrimGraph(){
		LinkedList<Point> pointList;
		MedialAxisGraph testGraph = new MedialAxisGraph();
		testGraph.buildGraph(this.chunk4TrimWith3brances, distanceMap);
		assertTrue(testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(9,11))).
				isChild(testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(10,11)))));
		assertTrue(testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(10,11))).
				isChild(testGraph.getAxisGraph().get(testGraph.indexOfVertexWithPoint(new Point(9,11)))));

		testGraph.trimGraph();

		pointList = testGraph.getMedialAxisFromGraph();
		assertFalse(pointList.contains(new Point(10, 11)));
		assertFalse(pointList.contains(new Point(12, 10)));
		assertFalse(pointList.contains(new Point(12, 11)));
		assertTrue(pointList.contains(new Point(10, 10)));

	}	@Test
	public void testGetOrderedMedialAxis(){

		MedialAxisGraph testGraph = new MedialAxisGraph();
		testGraph = new MedialAxisGraph();
		testGraph.buildGraph(this.diagonalLine, distanceMap);
		LinkedList<Vertex> segment=new LinkedList<Vertex>();
		segment.add(testGraph.getAxisGraph().get(3));
		ArrayList<Point> orderedList=testGraph.getOrderedMedialAxis();
		assertEquals(testGraph.getAxisGraph().size(),orderedList.size());
		assertEquals(orderedList.get(0), new Point(0,0));
		assertEquals(orderedList.get(1), new Point(1,1));
		assertEquals(orderedList.get(2), new Point(2,2));
		assertEquals(orderedList.get(3), new Point(3,3));
		assertEquals(orderedList.get(4), new Point(4,4));
		assertEquals(orderedList.get(5), new Point(5,5));
		assertEquals(orderedList.get(6), new Point(6,6));
		assertEquals(orderedList.get(7), new Point(7,7));
		assertEquals(orderedList.get(8), new Point(8,8));
		assertEquals(orderedList.get(9), new Point(9,9));
		assertEquals(orderedList.get(10), new Point(10,10));

	}
	
}
