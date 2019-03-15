package classes;


	import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
	
import java.io.IOException;

// w w w  . jav  a 2s  . com
import javax.imageio.ImageIO;

	public class Main {
		  public static void main(String args[]) throws IOException{
			  File file= new File("test.png");
			  BufferedImage image = ImageIO.read(file);
			  // Getting pixel color by position x and y 
			  int clr=  image.getRGB(image.getMinX(),image.getMinY()); 
			  int  red   = (clr & 0x00ff0000) >> 16;
			  int  green = (clr & 0x0000ff00) >> 8;
			  int  blue  =  clr & 0x000000ff;
			  
			  }
	}

