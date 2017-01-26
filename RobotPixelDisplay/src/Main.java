import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Main {
	public static Image getImageFromArray(int[] pixels, int width, int height)
	  {
	    BufferedImage image =
	        new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    WritableRaster raster = (WritableRaster) image.getData();
	    raster.setPixels(0, 0, width, height, pixels);
	    image.setData(raster);
	    return image;
	  }
	public static Image getImageFromArray(Pixel[][] pixels, int width, int height) {
		BufferedImage image =
		        new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		WritableRaster raster = (WritableRaster) image.getData();
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				raster.setPixel(j, i, pixels[i][j].getColorArray());
			}
		}
		image.setData(raster);
	    return image;
	}
	public static Image getPixelatedImageFromArray(Pixel[][] pixels, int width, int height) {
		BufferedImage image =
		        new BufferedImage(width * 16, height * 16, BufferedImage.TYPE_INT_RGB);
		WritableRaster raster = (WritableRaster) image.getData();
		for(int i = 0; i < height * 16; i = i + 16) {
			for(int j = 0; j < width * 16; j = j + 16) {
				for(int modI = 0; modI < 16; modI++) {
					for(int modJ = 0; modJ < 16; modJ++) {
						raster.setPixel(j + modJ, i + modI, pixels[i/16][j/16].getColorArray());
					}
				}
			}
		}
		image.setData(raster);
	    return image;
	}

	  public static void main(String[] args) throws IOException
	  {
	    JFrame jf = new JFrame();
	    JLabel jl = new JLabel();
	    int tick = 0;
	    int WIDTH = 18;
	    int HEIGHT = 18;
	    
	    Pixel[][] arrayimage = new Pixel[HEIGHT][WIDTH];
	    for(int i = 0; i < HEIGHT; i++) {
	    	for(int j = 0; j < WIDTH; j++) {
	    		arrayimage[i][j] = new Pixel(0,0,0);
	    	}
	    }
	    Pixel pix = new Pixel(0,0,0);
	    while(true) {
	    	tick = (++tick) % 648;
	    	
	    	for (int i = 0; i < HEIGHT; i++)
		    {
	    		for (int j = 0; j < WIDTH; j++) {
		    	  	arrayimage[i][j].setColor(tick + HEIGHT * i + HEIGHT * j);
		    	  	//System.out.println(pix);
	    		}
		    }
		    ImageIcon ii = new ImageIcon(getPixelatedImageFromArray(arrayimage, WIDTH, HEIGHT));
		    jl.setIcon(ii);
		    jf.add(jl);
		    jf.pack();
		    jf.setVisible(true);
		    try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	  }
}
