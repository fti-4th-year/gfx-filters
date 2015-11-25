package filter;

import color.Color;

public class GrayScaleFilter extends PixelFilter {
	private Color color;
	private double r, g, b;
	
	public GrayScaleFilter() {
		this(0.299, 0.587, 0.114);
	}
	
	public GrayScaleFilter(double r, double g, double b) {
		this.r = r;
		this.g = g;
		this.b = b;
		color = new Color();
	}
	
	@Override
	public int sample(int c) {
		color.setInt(c);
		double avg = (r*color.r + g*color.g + b*color.b);
		color.set(avg, avg, avg);
		return color.getInt();
	}

}
