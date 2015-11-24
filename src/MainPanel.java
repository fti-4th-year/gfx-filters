import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import filter.Filter;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ImagePanel src, dst;
	
	public MainPanel(BufferedImage img) {
		super();
		setPreferredSize(new Dimension(800, 600));
		setLayout(new GridLayout(0, 2));
		
		src = new ImagePanel(img);
		dst = new ImagePanel(img);
		add(src);
		add(dst);
	}
	
	public void copySrcToDst() {
		dst.copy(src);
		repaint();
	}
	
	public void copyDstToSrc() {
		src.copy(dst);
		repaint();
	}
	
	public void apply(Filter filter) {
		dst.apply(filter, src.getImage());
		repaint();
	}
}
