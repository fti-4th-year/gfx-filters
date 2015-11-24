package filter;

import java.awt.image.BufferedImage;

public interface Filter {
	public int sample(int x, int y, BufferedImage img);
}
