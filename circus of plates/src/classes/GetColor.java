package classes;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class GetColor {
public String getColor(BufferedImage image){
	
	
	 
	  int clr=  image.getRGB(image.getMinX(),image.getMinY()); 
	  int  red   = (clr & 0x00ff0000) >> 16;
	  int  green = (clr & 0x0000ff00) >> 8;
	  int  blue  =  clr & 0x000000ff;
	return ""+red+green+blue;
}
}
