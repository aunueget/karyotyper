package medial_axis;

import java.awt.Point;
import java.util.LinkedList;

public class DistanceMap {
	private int[][] distanceFromEdgeMatrix;

	public DistanceMap() {
		this.distanceFromEdgeMatrix = null;
	}

	public DistanceMap(DistanceMap newMap) {
		this.copyDistanceMap(newMap);
	}

	public DistanceMap(int x, int y) {
		distanceFromEdgeMatrix = new int[x][y];
		initDistanceMap();
	}

	private void initDistanceMap() {
		for (int i = 0; i < this.distanceFromEdgeMatrix.length; i++) {
			for (int j = 0; j < this.distanceFromEdgeMatrix[0].length; j++) {
				this.distanceFromEdgeMatrix[i][j] = -5;
			}
		}

	}

	public Point getSizeOfDistanceMap() {
		return new Point(this.distanceFromEdgeMatrix.length, this.distanceFromEdgeMatrix[0].length);
	}

	/**
	 * this returns a points distance from the edge of the cluster
	 * 
	 * @param tempPoint
	 *            the point to get the distance to the edge of
	 * @return the number representing how many pixels from the edge of the cluster this point is
	 */
	public int getDistanceFromEdge(Point tempPoint) {
		return this.distanceFromEdgeMatrix[tempPoint.x][tempPoint.y];
	}

	/**
	 * this sets the points distance to the edge of the cluster
	 * 
	 * @param tempPoint
	 *            the point to set the distance to the edge of
	 * @param distance
	 *            from the edge of the cluster to set the point too
	 */
	public void setDistanceFormEdge(Point tempPoint, int distance) {
		this.distanceFromEdgeMatrix[tempPoint.x][tempPoint.y] = distance;

	}

	/**
	 * this returns a linkedlist of points that are the distance from the edge specified by distance
	 * 
	 * @param distance
	 *            is the number of pixels from the edge to get a list of
	 * @return linkedlist of points at that are the distance away from the edge
	 */
	public LinkedList<Point> getTheEdge(int distance) {
		LinkedList<Point> edge = new LinkedList<Point>();
		for (int i = 0; i < this.distanceFromEdgeMatrix[0].length; i++) {
			for (int j = 0; j < this.distanceFromEdgeMatrix.length; j++) {
				if (this.distanceFromEdgeMatrix[j][i] == distance) {
					edge.add(new Point(j, i));
				}
			}
		}
		return edge;

	}

	public int getWidth() {
		return this.distanceFromEdgeMatrix.length;
	}

	public int getHeight() {
		return this.distanceFromEdgeMatrix[0].length;
	}

	private void copyDistanceMap(DistanceMap copyMap) {
		if (copyMap.distanceFromEdgeMatrix != null) {
			this.distanceFromEdgeMatrix = new int[copyMap.distanceFromEdgeMatrix.length][copyMap.distanceFromEdgeMatrix[0].length];
			for (int j = 0; j < this.distanceFromEdgeMatrix.length; j++) {
				for (int i = 0; i < this.distanceFromEdgeMatrix[0].length; i++) {
					this.distanceFromEdgeMatrix[j][i] = copyMap.distanceFromEdgeMatrix[j][i];
				}
			}

		}
	}

	/**
	 * prints to the console a text based representation of the distance map for testing
	 */
	public void mapOut() {
		for (int i = 0; i < distanceFromEdgeMatrix[0].length; i++) {
			for (int j = 0; j < distanceFromEdgeMatrix.length; j++) {
				if (distanceFromEdgeMatrix[j][i] == -5) {
					System.out.print('_');
				} else {
					System.out.print(distanceFromEdgeMatrix[j][i]);
				}
			}
			System.out.println("");
		}
	}
}
