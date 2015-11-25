package filter;

import color.Color;

public class InvertFilter extends PixelFilter {
	private Color tmp;
	public InvertFilter() {
		tmp = new Color();
	}
	@Override
	public int sample(int c) {
		tmp.setInt(c);
		tmp.multiply(-1.0);
		tmp.add(Color.WHITE);
		return tmp.getInt();
	}
}
