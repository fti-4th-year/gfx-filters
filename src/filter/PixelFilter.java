package filter;

import java.awt.image.BufferedImage;

public abstract class PixelFilter implements Filter {
	@Override
	public int sample(int x, int y, BufferedImage img) {
		return sample(img.getRGB(x, y));
	}
	public abstract int sample(int color);
}
