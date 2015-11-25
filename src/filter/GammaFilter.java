package filter;

import color.Color;

public class GammaFilter extends PixelFilter {
	private Color color;
	private double power;
	public GammaFilter(double pow) {
		power = pow;
		color = new Color();
	}
	@Override
	public int sample(int c) {
		color.setInt(c);
		color.r = Math.pow(color.r, power);
		color.g = Math.pow(color.g, power);
		color.b = Math.pow(color.b, power);
		return color.getInt();
	}
}
