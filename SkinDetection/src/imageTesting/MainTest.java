package imageTesting;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainTest {
	public static void main(String[] args) throws IOException {
		//System.out.println("hello");
		
		BufferedImage bi=ImageIO.read(new File("image/image (1).jpg"));
		BufferedImage BI = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		int countloop=0;  

		for (int x = 0; x <bi.getWidth(); x++) {
		    for (int y = 0; y < bi.getHeight(); y++) {
		        Color c = new Color(bi.getRGB(x, y));
		        //int c2 = new Color(c.getRed(), c.getGreen(), c.getBlue()).getRGB();
		        //BI.setRGB(x, y, c2);
		       // c2.setRed(c.getRed());
		        System.out.println("red=="+c.getRed()+" green=="+c.getGreen()+"    blue=="+c.getBlue()+"  countloop="+countloop++);                                                                                                                                                  
		    }
		}
		
		
		Color myWhite = new Color(255, 255, 255); // Color white
		int rgb = myWhite.getRGB();

		try {
		    BufferedImage img = null;
		    try {
		        img = ImageIO.read(new File("bubbles.bmp"));
		    }
		    catch (IOException e) {
		    }

		    for (int i = 0; i < 100; i++) {
		        for (int j = 0; j < 100; j++) {
		            img.setRGB(i, j, rgb);
		        }
		    }

		    // retrieve image
		    File outputfile = new File("saved.png");
		    ImageIO.write(img, "png", outputfile);
		}
		catch (IOException e) {
		}
		
		
		
		
		
		
		
		//BufferedImage BI = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		//File outputfile = new File("image/out.jpg");
	    //ImageIO.write(BI, "png", outputfile);
		
	}

}
