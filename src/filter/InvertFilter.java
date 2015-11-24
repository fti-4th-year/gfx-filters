package filter;

import java.awt.image.BufferedImage;

import color.Color;

public class InvertFilter implements Filter {
	private Color tmp;
	public InvertFilter() {
		tmp = new Color();
	}
	@Override
	public int sample(int x, int y, BufferedImage img) {
		tmp.setInt(img.getRGB(x, y));
		tmp.multiply(-1.0);
		tmp.add(Color.WHITE);
		return tmp.getInt();
	}
}
