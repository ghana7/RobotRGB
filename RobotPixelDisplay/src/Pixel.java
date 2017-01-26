
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
		red = 128 + (int)(127 * Math.sin(frequency * input));
		blue = 128 + (int)(127 * Math.sin(frequency * input + frequency * 216));
		green = 128 + (int)(127 * Math.sin(frequency * input + frequency * 432));
	}
}
