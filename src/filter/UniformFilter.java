package filter;

import java.awt.image.BufferedImage;

public class UniformFilter implements Filter {
	@Override
	public int sample(int x, int y, BufferedImage img) {
		return img.getRGB(x, y);
	}
}
