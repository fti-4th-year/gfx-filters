package filter;

public class UniformFilter extends PixelFilter {
	@Override
	public int sample(int c) {
		return c;
	}
}
