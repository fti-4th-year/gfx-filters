package filter;

import java.awt.image.BufferedImage;

import color.Color;

public class MatrixFilter implements Filter {
	private Color sum, tmp;
	private double[] matrix;
	private int sx, sy;
	private Color offset;
	
	public MatrixFilter(double[] m) {
		this(m, Color.BLACK);
	}
	
	public MatrixFilter(double[] m, int x, int y) {
		this(m, x, y, Color.BLACK);
	}
	
	public MatrixFilter(double[] m, Color c) {
		this(m, 1, 1, c);
	}
	
	public MatrixFilter(double[] m, int x, int y, Color c) {
		set(m, x, y);
		sum = new Color();
		tmp = new Color();
		offset = c;
	}
	
	public void set(double[] m, int x, int y) {
		matrix = new double[(2*x + 1)*(2*y + 1)];
		sx = x;
		sy = y;
		for(int i = 0; i < Math.min(m.length, matrix.length); ++i) {
			matrix[i] = m[i];
		}
	}

	@Override
	public int sample(int x, int y, BufferedImage img) {
		sum.clear();
		for(int iy = -sy; iy <= sy; ++iy) {
			if(y + iy >= 0 && y + iy < img.getHeight()) {
				for(int ix = -sx; ix <= sx; ++ix) {
					if(x + ix >= 0 && x + ix < img.getWidth()) {
						tmp.setInt(img.getRGB(x + ix, y + iy));
						tmp.multiply(matrix[(2*sx + 1)*(iy + sy) + (ix + sx)]);
						sum.add(tmp);
						//System.out.println(ix + " " + iy);
					}
				}
			}
		}
		sum.add(offset);
		return sum.getInt();
	}
}
