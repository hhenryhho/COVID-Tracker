package observer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import country_service.CountryList;

/*
 * This class exists as a singleton. This is to ensure that there is only one instance of the map image that
 * that is shared across the program.
 */
public class ImageRenderer implements CalculatorListener {

	private static ImageRenderer INSTANCE;
	private final File PATH_ORIGINAL = new File("./src/assets/map.jpg");
	private final File PATH_EDITED = new File("./src/assets/mapedited.jpg");
	BufferedImage img;
	private ImageIcon imgIcon; 
	private int width;
	private int height;

	private ImageRenderer(int width, int height) {
		this.width = width;
		this.height = height;
		try {
			img = ImageIO.read(PATH_ORIGINAL);
			imgIcon = new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ImageRenderer getInstance(int width, int height) {
		if (INSTANCE == null) {
			INSTANCE = new ImageRenderer(width, height);
		}
		return INSTANCE;
	}

	public static ImageRenderer getInstance() {

		return INSTANCE;
	}

	@Override
	public void update(CountryList countryList) {
		
		int maxOvalDimension;
		int minOvalDimension = 15;
		double maxValue = 0;
		
		// Reset the image to a blank slate
		try {
			img = ImageIO.read(PATH_ORIGINAL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Find the max value of the analysis results
		for (int i = 0; i < countryList.size(); i++) {
			if (countryList.getCountryByIndex(i).getAnalysisValue() > maxValue) {
				maxValue = countryList.getCountryByIndex(i).getAnalysisValue();
			}
		}
		
		// Iterate through each of the countries in the country list and add render a circle on the map
		for (int i = 0; i < countryList.size(); i++) {
			if (maxValue < 10000) {
				maxOvalDimension = 20;
			} else if (maxValue < 50000) {
				maxOvalDimension = 30;
			} else if (maxValue < 100000) {
				maxOvalDimension = 50;
			} else {
				maxOvalDimension = 70;
			}

			int ovalDimension = (int)Math.round(((maxOvalDimension - minOvalDimension) * countryList.getCountryByIndex(i).getAnalysisValue()/maxValue) + minOvalDimension);
			double[] coordinates = readCoordinates(countryList.getCountryByIndex(i).getCountryName());
			Point newPoint = getXY(coordinates[0], coordinates[1]);

			// Add the circle to the image
			Graphics2D editableImage = (Graphics2D) img.getGraphics();
			editableImage.setColor(Color.RED);
			editableImage.setStroke(new BasicStroke(3));
			editableImage.fillOval(newPoint.x - (ovalDimension / 2), newPoint.y - (ovalDimension / 2), ovalDimension, ovalDimension);
			
			editableImage.drawImage(img, null, 0, 0);
			try {
				ImageIO.write(img, "JPEG", PATH_EDITED);
				BufferedImage img2 = ImageIO.read(PATH_EDITED);
				imgIcon = new ImageIcon(img2.getScaledInstance(width, height, Image.SCALE_SMOOTH));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	public ImageIcon render() {
		return imgIcon;
	}

	/* Getting the right coordinates in the jpeg given the latitude, 
	 * the longitude of the country and the size of the jpeg map.
	 */
	private Point getXY(double lat, double lng) {
		int screenX = (int) Math.round((((lng + 180) / 360) * img.getWidth()));
		int screenY = (int) Math.round(((((lat * -1) + 90) / 180) * img.getHeight()));

		return new Point(screenX, screenY);
	}

	// Gets the coordinates of the country by reading the coordinates csv file
	private double[] readCoordinates(String countryName) {
		double x = 0;
		double y = 0;
		try {
			Scanner reader = new Scanner(new File("./src/assets/coordinates.csv"));  
			while (reader.hasNextLine())  
			{  
				String currentLine = reader.nextLine();
				String[] splitter = currentLine.split(",");
				if (splitter[3].equals(countryName)) {
					x = Double.parseDouble(splitter[1]);
					y = Double.parseDouble(splitter[2]);
				} 
			}
			reader.close(); 
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return new double[] {x, y};
	}  
}
