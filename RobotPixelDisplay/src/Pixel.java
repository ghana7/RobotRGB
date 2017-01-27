public class Pixel {
	private int red;
	private int green;
	private int blue;

	public Pixel(int _red, int _green, int _blue) {
		red = _red;
		green = _green;
		blue = _blue;
	}

	public Pixel(Pixel p) {
		red = p.red;
		green = p.green;
		blue = p.blue;
	}

	public int[] getColorArray() {
		int[] out = new int[3];
		out[0] = red;
		out[1] = green;
		out[2] = blue;
		return out;
	}

	public String toString() {
		String output = "";
		output += "R:" + red;
		output += " G:" + green;
		output += " B:" + blue;
		return output;
	}

	public void setColor(int input) {
		double frequency = Math.PI / 324;
		red = 128 + (int) (127 * Math.sin(frequency * input));
		blue = 128 + (int) (127 * Math.sin(frequency * input + frequency * 216));
		green = 128 + (int) (127 * Math.sin(frequency * input + frequency * 432));
	}

	public void setColor(int _red, int _green, int _blue) {
		red = _red;
		green = _green;
		blue = _blue;
	}
	public void toGrayScale() {
		int sum = red + green + blue;
		red = sum / 3;
		green = sum / 3;
		blue = sum / 3;
	}

	public void pulsateCircular(int tick, int x, int y) {
		setColor(648 - tick + 16 * (int) (1 + Math.abs(x - 8.5) + Math.abs(y - 8.5)));
	}

	public void pulsateDiagonal(int tick, int x, int y) {
		setColor(tick + 16 * x + 16 * y);
	}
	public void spiral(int tick, int x, int y, double cenX, double cenY) {
		double dX = x - cenX;
		double dY = y - cenY;
		double angle = Math.atan2(dY, dX);
		angle += Math.PI;
		angle *= (648 / (Math.PI * 2));
		setColor(tick + (int)angle);
	}
	public void movingSpiral(int tick, int x, int y, int radius) {
		double cenAngle = tick;
		cenAngle *= (Math.PI / 324);
		cenAngle -= Math.PI;
		double cenX = Math.cos(cenAngle) * radius + 8.5;
		double cenY = Math.sin(cenAngle) * radius + 8.5;
		
		double dX = x - cenX;
		double dY = y - cenY;
		double angle = Math.atan2(dY, dX);
		angle += Math.PI;
		angle *= (648 / (Math.PI * 2));
		setColor(tick + (int)angle);
	}
	public void sparkle(double frequency) {
		if(getColorArray()[0] == 0 && getColorArray()[1] == 0 && getColorArray()[2] == 0) {
			if(Math.random() < frequency) {
				setColor(255,255,255);
			}
		} else {
			setColor((int)(getColorArray()[0] * 0.99), (int)(getColorArray()[1] * 0.99), (int)(getColorArray()[2] * 0.99));
		}
	}
	public void drawColor(int x, int y, int ballx, int bally) {
		if(x == ballx && y == bally) {
			setColor(255,255,255);
		} else {
			setColor(0,0,0);
		}
	}
	
	public void randomNoise() {
		setColor((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
	}
}
