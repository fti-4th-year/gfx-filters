package filter;

public class SharpenFilter extends MatrixFilter {
	private static final double m[] = {-1,-1,-1,-1,9,-1,-1,-1,-1};
	public SharpenFilter() {
		super(m);
	}
}
