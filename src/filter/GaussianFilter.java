package filter;

import java.awt.image.BufferedImage;

import color.Color;

public class GaussianFilter implements Filter {
	
	private double[] array;
	private int size;
	private Color sum, tmp;
	
	public GaussianFilter(double sigma) {
		sum = new Color();
		tmp = new Color();
		size = 4;
		array = new double[(2*size + 1)*(2*size + 1)];
		double sigma2 = sigma*sigma;
		double sum = 0.0;
		for(int iy = -size; iy <= size; ++iy) {
			for(int ix = -size; ix <= size; ++ix) {
				sum += array[(iy + size)*(2*size + 1) + (ix + size)] = 1.0/(2.0*Math.PI*(sigma2))*Math.exp(-0.5*((ix*ix + iy*iy)/sigma2));
			}
		}
		for(int i = 0; i < array.length; ++i) {
				array[i] /= sum;
		}
	}
	
	@Override
	public int sample(int x, int y, BufferedImage img) {
		sum.clear();
		for(int iy = -size; iy <= size; ++iy) {
			if(y + iy >= 0 && y + iy < img.getHeight()) {
				for(int ix = -size; ix <= size; ++ix) {
					if(x + ix >= 0 && x + ix < img.getWidth()) {
						tmp.setInt(img.getRGB(x + ix, y + iy));
						tmp.multiply(array[(iy + size)*(2*size + 1) + (ix + size)]);
						sum.add(tmp);
					}
				}
			}
		}
		return sum.getInt();
	}
}
