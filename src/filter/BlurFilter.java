package filter;

public class BlurFilter extends MatrixFilter {
	private static final double m[] = {1./9,1./9,1./9,1./9,1./9,1./9,1./9,1./9,1./9};
	public BlurFilter() {
		super(m);
	}
}
