import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class Main {
	public static Image getImageFromArray(int[] pixels, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		WritableRaster raster = (WritableRaster) image.getData();
		raster.setPixels(0, 0, width, height, pixels);
		image.setData(raster);
		return image;
	}

	public static Image getImageFromArray(Pixel[][] pixels, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		WritableRaster raster = (WritableRaster) image.getData();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				raster.setPixel(j, i, pixels[i][j].getColorArray());
			}
		}
		image.setData(raster);
		return image;
	}

	public static Image getPixelatedImageFromArray(Pixel[][] pixels, int width, int height) {
		BufferedImage image = new BufferedImage(width * 16, height * 16, BufferedImage.TYPE_INT_RGB);
		WritableRaster raster = (WritableRaster) image.getData();
		for (int i = 0; i < height * 16; i = i + 16) {
			for (int j = 0; j < width * 16; j = j + 16) {
				for (int modI = 0; modI < 16; modI++) {
					for (int modJ = 0; modJ < 16; modJ++) {
						raster.setPixel(j + modJ, i + modI, pixels[i / 16][j / 16].getColorArray());
					}
				}
			}
		}
		image.setData(raster);
		return image;
	}
	public static void moveBounce(int bounceDir, int bounceX, int bounceY) {
		//0 - top left, 1 - top right, 2 - bottom right, 3 - bottom left
		if(bounceX == 0) {
			bounceDir = 3 - bounceDir;
		}
		if(bounceX == 17) {
			bounceDir = 3 - bounceDir;
		}
		if(bounceY == 0) {
			bounceDir = 1 - bounceDir;
		}
		if(bounceY == 17) {
			bounceDir = 5 - bounceDir;
		}
		switch(bounceDir) {
		case 0:
			bounceX--;
			bounceY--;
			break;
		case 1:
			bounceX++;
			bounceY--;
			break;
		case 2:
			bounceX++;
			bounceY++;
			break;
		case 3:
			bounceX--;
			bounceY++;
			break;
		}
	}
	public static void main(String[] args) throws IOException {
		JFrame jf = new JFrame();
		JLabel jl = new JLabel();
		int tick = 0;
		int WIDTH = 18;
		int HEIGHT = 18;
		int bounceDir = 0;
		int bounceX = 5;
		int bounceY = 10;

		Pixel[][] arrayimage = new Pixel[HEIGHT][WIDTH];
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				arrayimage[i][j] = new Pixel(0, 0, 0);
			}
		}
		Pixel pix = new Pixel(0, 0, 0);
		while (true) {
			tick = (++tick) % 648;
			moveBounce(bounceDir,bounceX,bounceY);
			for (int i = 0; i < HEIGHT; i++) {
				for (int j = 0; j < WIDTH; j++) {
					// arrayimage[i][j].pulsateCircular(tick,i,j);
					// arrayimage[i][j].pulsateDiagonal(tick,i,j);
					// arrayimage[i][j].randomNoise();
					// arrayimage[i][j].movingSpiral(tick,i,j,20);
					// arrayimage[i][j].sparkle(0.00002);
					arrayimage[i][j].drawColor(i,j,bounceX,bounceY);
				}
			}
			ImageIcon ii = new ImageIcon(getPixelatedImageFromArray(arrayimage, WIDTH, HEIGHT));
			jl.setIcon(ii);
			jf.add(jl);
			jf.pack();
			jf.setVisible(true);
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
