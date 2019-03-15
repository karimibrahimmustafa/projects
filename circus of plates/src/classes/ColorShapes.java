package classes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class ColorShapes {
	private BufferedImage image;
public BufferedImage color(String path,Color color){
	try {
		 image =ImageIO.read(new File(path));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	int width = image.getWidth();
    int height = image.getHeight();
    WritableRaster raster = image.getRaster();

    for (int xx = 0; xx < width; xx++) {
      for (int yy = 0; yy < height; yy++) {
        int[] pixels = raster.getPixel(xx, yy, (int[]) null);
        if(color.getBlue()==0){
        	pixels[2] = pixels[2]+0;
        }
        else 
        	pixels[2]=color.getBlue();
        if(color.getGreen()==0){
        	pixels[1] = pixels[1]+0;
        }
        else 
        	pixels[1]=color.getGreen();
        if(color.getRed()==0){
        	pixels[0] = pixels[0]+0;
        }
        else 
        	pixels[0]=color.getRed();
       
        raster.setPixel(xx, yy, pixels);
      }
    }
    return image;
	
}
}
