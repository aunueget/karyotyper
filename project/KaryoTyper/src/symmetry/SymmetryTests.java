package symmetry;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import targetGetter.TestShape;

import Target.TargetShape;
import basicObjects.AxisVertex;
import basicObjects.AxisVertex.DistanceException;
import basicObjects.MedialAxisGraph;
import basicObjects.Shape;


public class SymmetryTests {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SymmetryTests test = new SymmetryTests();
		test.rectangleColorTest();
//		test.crestColorTest();
//		test.realColorTest();

	}
	
	public void rectangleColorTest() {
		short [][] imgValues = new short[10][10];
		ArrayList<Point> axisPoints = new ArrayList<Point>();
		
		for (int i = 0; i < imgValues.length; i++) {
			for ( int j = 0; j < imgValues[0].length; j++) {
				imgValues[i][j] = 0;
			}
		}
		
		for (int i = 2; i < 8; i++) {
//			imgValues[i][4] = 1;
			imgValues[i][5] = 1;
			imgValues[i][6] = 1;
			imgValues[i][7] = 1;
//			imgValues[i][8] = 1;
			axisPoints.add(new Point(i, 6));
		}
		
//		imgValues[2][5] = 0;
		
		Image img = new Image(imgValues);
		Axis axis = new Axis(axisPoints, img, 3, 5);
		
		System.out.println(img.toString());
		boolean isSymmetric = DetectSymmetry.detectColorSymmetry(img, axis, 3, .99);
		System.out.println("Is the shape symmetric? " + isSymmetric);
		
		DetectSymmetry.detectWidthSymmetry(img, axis, .99);
	}
	
	public void crestColorTest() {
		short [][] imgValues = new short[10][10];
		ArrayList<Point> axisPoints = new ArrayList<Point>();
		
		for (int i = 0; i < imgValues.length; i++) {
			for ( int j = 0; j < imgValues[0].length; j++) {
				imgValues[i][j] = 0;
			}
		}
		
		//Create axis points
		imgValues[7][0] = 1;
		imgValues[7][1] = 1;
		axisPoints.add(new Point(7, 1));
		imgValues[7][2] = 1;
		imgValues[6][0] = 1;
		imgValues[6][1] = 1;
		axisPoints.add(new Point(6, 1));
		imgValues[6][2] = 1;
		imgValues[5][0] = 1;
		imgValues[5][1] = 1;
		axisPoints.add(new Point(5, 1));
		imgValues[5][2] = 1;
		imgValues[4][1] = 1;
		imgValues[3][2] = 1;
		imgValues[4][2] = 1;
		axisPoints.add(new Point(4, 2));
		imgValues[3][3] = 1;
		imgValues[4][3] = 1;
		axisPoints.add(new Point(4, 3));
		imgValues[5][3] = 1;
		imgValues[3][4] = 1;
		imgValues[4][4] = 1;
		axisPoints.add(new Point(4, 4));
		imgValues[5][4] = 1;
		imgValues[4][5] = 1;
		imgValues[5][5] = 1;
		axisPoints.add(new Point(5, 5));
		imgValues[5][6] = 1;
		imgValues[6][4] = 1;
		imgValues[6][5] = 1;
		axisPoints.add(new Point(6, 5));
		imgValues[6][6] = 1;
		imgValues[7][4] = 1;
		imgValues[7][5] = 1;
		axisPoints.add(new Point(7, 5));
		imgValues[7][6] = 1;
		
		Image img = new Image(imgValues);
		Axis axis = new Axis(axisPoints, img, 3, 5);
		
		System.out.println(img.toString());
		boolean isSymmetric = DetectSymmetry.detectColorSymmetry(img, axis, 3, .99);
		System.out.println("Is the shape symmetric? " + isSymmetric);
		
		DetectSymmetry.detectWidthSymmetry(img, axis, .99);
	}
	
	public void realColorTest() {
		TargetShape image = TestShape.getImageObject("C:\\Users\\Robert\\Desktop\\SchoolWork\\" +
				"CSC492\\repo\\project\\Karyotyper\\testImage.jpg");
//		boolean[][] img = image.getShape();
//		Image imageObject = new Image(img);
		MedialAxisGraph axis = new MedialAxisGraph(image.getSkeltonPoints());
		for (AxisVertex v : axis.getAxisGraph()) {
			try {
				v.calculateOrthogonalLine(5, 7);
			} catch (DistanceException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
//		DetectSymmetry.detectColorSymmetry(imageObject, axis, 20, 0.99);
	}

}
