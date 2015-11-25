package filter;

import java.awt.image.BufferedImage;

import color.Color;

public class EmbossFilter implements Filter {
	private GrayScaleFilter grayscale;
	private MatrixFilter matrix;
	private Color offset;
	public EmbossFilter() {
		double[] m = {-1,-0.5,0,-0.5,0,0.5,0,0.5,1};
		offset = new Color(0.5,0.5,0.5);
		matrix = new MatrixFilter(m,offset);
		grayscale = new GrayScaleFilter();
	}
	@Override
	public int sample(int x, int y, BufferedImage img) {
		return grayscale.sample(matrix.sample(x, y, img));
	}
	
}
