package filter;

import color.Color;

public class BorderFilter extends MatrixFilter {
	private static final double f = 2.0;
	private static final double o = 0.0;
	private static final double m[] = {0,-f,0,-f,4*f,-f,0,-f,0};
	public BorderFilter() {
		super(m, new Color(o,o,o));
	}
}
