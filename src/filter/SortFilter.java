package filter;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import color.Color;



public class SortFilter implements Filter {
	
	private Color tmp;
	private int size;
	private int pos;
	private double[][] array;
	
	public SortFilter(int size, int pos) {
		this.size = size;
		this.pos = pos;
		array = new double[3][3*(2*size + 1)*(2*size + 1)];
		tmp = new Color();
	}
	
	private int getIndex(int ix, int iy) {
		return (iy + size)*(2*size + 1) + (ix + size);
	}
	
	@Override
	public int sample(int x, int y, BufferedImage img) {
		for(int iy = -size; iy <= size; ++iy) {
			if(y + iy >= 0 && y + iy < img.getHeight()) {
				for(int ix = -size; ix <= size; ++ix) {
					if(x + ix >= 0 && x + ix < img.getWidth()) {
						int index = getIndex(ix, iy);
						tmp.setInt(img.getRGB(x + ix, y + iy));
						array[0][index] = tmp.r;
						array[1][index] = tmp.g;
						array[2][index] = tmp.b;
					} else {
						for(int i = 0; i < 3; ++i) {
							array[i][getIndex(ix, iy)] = 0.0;
						}
					}
				}
			} else {
				for(int ix = -size; ix <= size; ++ix) {
					for(int i = 0; i < 3; ++i) {
						array[i][getIndex(ix, iy)] = 0.0;
					}
				}
			}
		}
		for(int i = 0; i < 3; ++i) {
			Arrays.sort(array[i]);
		}
		tmp.set(array[0][pos], array[1][pos], array[2][pos]);
		return tmp.getInt();
	}

}
