package imageTesting;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Final {

	public static void main(String[] args) throws IOException {
		int imageFileNumber = 1;
		int[][][] skin = new int[256][256][256];
		int[][][] nonSkin = new int[256][256][256];
		double probabilityOfSkinGivenRGB = 0;
		double probabilityOfNonSkinGivenRGB = 0;
		int totalSkin = 0;
		int totalNonSkin = 0;
		
		int totalPixel = 0;

		
		
		/*
		
		while (true) {

			File image = new File("data/image/image (" + imageFileNumber + ").jpg");
			File mask = new File("data/mask/mask (" + imageFileNumber + ").png");
			BufferedImage img = null;
			BufferedImage msk = null;

			try {
				img = ImageIO.read(image);
				msk = ImageIO.read(mask);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				break;
			}
			int width = img.getWidth();
			int height = img.getHeight();
			int[][] pixel = new int[width][height];
			Raster raster = img.getData();
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					totalPixel++;

					pixel[i][j] = raster.getSample(i, j, 0);

					Color c1 = new Color(img.getRGB(i, j));
					Color c2 = new Color(msk.getRGB(i, j));
					
					
					if (c2.getRed() == 0 && c2.getGreen() == 0 && c2.getBlue() == 0){
						nonSkin[c1.getRed()][c1.getGreen()][c1.getBlue()]++;
						totalNonSkin++;
					}
						
					else{
						skin[c1.getRed()][c1.getGreen()][c1.getBlue()]++;
						totalSkin++;
					}
						// System.out.println(c2.getRed()+" "+c2.getGreen()+"
					// "+c2.getBlue());
				}
			}

			imageFileNumber++;

		}
		
		
		
		File in  = new File("data.txt");
		FileWriter fileWriter = new FileWriter(in);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		
		for(int i = 0; i < 256; i++)
		{
			for(int j = 0; j < 256; j++)
			{
				for(int k = 0; k < 256; k++)
					printWriter.println(skin[i][j][k]);
			}
		}
		
		for(int i = 0; i < 256; i++)
		{
			for(int j = 0; j < 256; j++)
			{
				for(int k = 0; k < 256; k++)
					printWriter.println(nonSkin[i][j][k]);
			}
		}
		
		printWriter.println(totalPixel + " " + totalSkin + " " + totalNonSkin);
		
		System.out.println("trained with " +imageFileNumber+" image");
		printWriter.close();
		fileWriter.close();
		
		
		
		*/
		
		
		
		

		File f2 = new File("data.txt");
		Scanner sc = new Scanner(f2);
		
		for(int i = 0; i < 256; i++)
		{
			for(int j = 0; j < 256; j++)
			{
				for(int k = 0; k < 256; k++){
					skin[i][j][k] = sc.nextInt();
					
				}
			}
		}
		for(int i = 0; i < 256; i++)
		{
			for(int j = 0; j < 256; j++)
			{
				for(int k = 0; k < 256; k++)
					nonSkin[i][j][k] = sc.nextInt();
			}
		}
		
		totalPixel = sc.nextInt();
		totalSkin = sc.nextInt();
		totalNonSkin = sc.nextInt();
		
		sc.close();
		
		//System.out.println(total);
		//System.out.println(totalPixel);
		
		File testImage = new File("input/test.jpg");
		BufferedImage testImg = null;

		try {
			testImg = ImageIO.read(testImage);
			
		} catch (IOException e) {
			System.out.println("no input file");
			
		}

		int widthTest = testImg.getWidth();
		int heightTest = testImg.getHeight();

		Color myWhite = new Color(255, 255, 255); // Color white
		int rgb = myWhite.getRGB();

		BufferedImage theImage = new BufferedImage(widthTest, heightTest, BufferedImage.TYPE_INT_RGB);

		int[][] pixelTest = new int[widthTest][heightTest];
		Raster raster = testImg.getData();
		for (int i = 0; i < widthTest; i++) {
			for (int j = 0; j < heightTest; j++) {
				pixelTest[i][j] = raster.getSample(i, j, 0);

				Color c1 = new Color(testImg.getRGB(i, j));
				double probabilityOfSkin = (double)totalSkin/(double)totalPixel;
				double probabilityOfNonSkin = (double) totalNonSkin/(double)totalPixel;
			
				 probabilityOfSkinGivenRGB = (double) skin[c1.getRed()][c1.getGreen()][c1.getBlue()]
						/ (double) totalPixel;
				

				 probabilityOfNonSkinGivenRGB = (double) nonSkin[c1.getRed()][c1.getGreen()][c1.getBlue()]/(double) totalPixel;
						  
						  
						  
			
				if (probabilityOfNonSkinGivenRGB > 0.0) {
					
					if (probabilityOfSkinGivenRGB / probabilityOfNonSkinGivenRGB > 0.27986443)
						theImage.setRGB(i, j, rgb);
					else
						theImage.setRGB(i, j, 0);

				} else
					theImage.setRGB(i, j, 0);

				
			}
		}
		
		InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(testImage);
	        os = new FileOutputStream(new File("data/image/image (" + imageFileNumber + ").jpg"));
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
		
		

		File outputfile = new File("input/abc.png");
		try {
			ImageIO.write(theImage, "png", outputfile);
		} catch (IOException e1) {

		}
		
		System.out.println("image written");
		
		
		
		
		
		
	}

	
}
