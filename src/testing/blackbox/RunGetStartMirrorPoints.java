package testing.blackbox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import runner.ImageQueue;
import basic_objects.PointList;
import chromosome.ChromosomeList;
import chromosome.GeneticSlideImage;
import extraction.ClusterSplitter;
import extraction.Extractor;

public class RunGetStartMirrorPoints extends JFrame {
	/**
	 * 
	 */
	private static boolean closing;// =false;
	private static final long serialVersionUID = 1L;
	public int imgCounter;
	public int targetsFound;
	public static JLabel currentStatus;
	private long start;

	public RunGetStartMirrorPoints(String string) {
		super(string);
		RunGetStartMirrorPoints.closing = false;
		start = System.currentTimeMillis();
		imgCounter = 0;
		targetsFound = 0;

	}

	// Create and set up the window.
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("add the commandline arugments <imagesPath>");
			System.out.println("Ex.  java -jar *.jar /home/arc/images/");
		} else {
			System.out.println(args[0]);

			// int imgCounter=0;
			RunGetStartMirrorPoints frame = new RunGetStartMirrorPoints("chromosome Getter GUI");
			frame.setLayout(new FlowLayout());
			JPanel upper = new JPanel();
			JPanel lower = new JPanel();
			frame.setLocation(200, 200);
			Dimension minSize = new Dimension(400, 200);
			frame.setMinimumSize(minSize);
			JLabel imgCount = new JLabel("Currently No Images In Directory");
			RunGetStartMirrorPoints.currentStatus = new JLabel("Waiting for images");
			RunGetStartMirrorPoints.currentStatus.setForeground(Color.RED);
			frame.add(upper);
			frame.add(lower);
			upper.add(RunGetStartMirrorPoints.currentStatus);
			lower.add(imgCount);
			frame.setVisible(true);
			// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

			// create custom close operation
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					exitProcedure();
				}

			});
			String filename;
			// initialize the que
			ImageQueue images = new ImageQueue();
			// initialize the extractor
			while (!RunGetStartMirrorPoints.closing) {
				// System.out.println(args[i]+"---------nextFilestarts Here---------------");
				// put images in the que and return next file in the path from string args
				filename = images.getNextFile(args[0]);
				if (filename != null) {
					if (!RunGetStartMirrorPoints.currentStatus.getText().contains("Finishing")) {
						RunGetStartMirrorPoints.currentStatus
								.setText("Finding Chromosomes in slide image: " + filename);
					}
					Extractor extractor = new Extractor();
					// use the current image in the que and create a slideImage
					GeneticSlideImage image = new GeneticSlideImage(filename);
					// extract the background from the image
					extractor.removeBackground(image);
					// get clusters from the image and keep a count of how many
					frame.targetsFound += extractor.findClusters(image);
					// pass the list of clusters on to slidelist
					ChromosomeList slideList1 = new ChromosomeList(extractor.getClusterList(), image);
					for(int i=0;i<slideList1.getChromosomeList().size();i++){
						LinkedList<PointList> cutList=ClusterSplitter.getSplitPoints(slideList1.getChromosomeList().get(i), (int) Math.round(image.getChromoWidth()/3));
						if(!cutList.isEmpty()){
							int newChromosomes=extractor.splitClusters(slideList1.getChromosomeList().get(i), cutList, image);
							if(newChromosomes>1){
								slideList1.getChromosomeList().remove(i);
								i--;
							}
						}
					}
					ChromosomeList splitList=new ChromosomeList(extractor.getSplitList(),image);
					while(!slideList1.getChromosomeList().isEmpty()){
						splitList.getChromosomeList().add(slideList1.getChromosomeList().pop());
					}
					splitList.calcMedialAxis(image);
					splitList.markStartMirrorPoints(image);
					
					
					
					
					
					imgCount.setText("Writing Splits" + splitList.size() + " images. ");


					
					
					
					
					imgCount.setText(frame.targetsFound + " Chromosomes found in "
							+ (++frame.imgCounter) + " slides read so far.");
					if (!RunGetStartMirrorPoints.currentStatus.getText().contains("Finishing")) {
						RunGetStartMirrorPoints.currentStatus.setText("Waiting for images");
					} else {
						RunGetStartMirrorPoints.currentStatus.setText("Finished looking at img"
								+ filename + " shutting down.");
					}
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();

				}
			}

			frame.exit();
			frame.dispose();
		}
	}

	protected static void exitProcedure() {
		RunGetStartMirrorPoints.closing = true;
		RunGetStartMirrorPoints.currentStatus
				.setText("Finishing current image search and shutting down.");
	}

	public void exit() {

		// use the current time to mark files as unique file name
		// Date today=new Date();
		System.out.println("Minutes:Secs " + (((System.currentTimeMillis() - start) / 60000) / 60)
				+ ":" + ((System.currentTimeMillis() - start) / 1000));

		try {
			File dir1 = new File(".");
			System.out.println("Working directory is: " + dir1.getAbsolutePath());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
