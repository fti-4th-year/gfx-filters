import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import filter.Filter;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ImagePanel src, dst;
	
	public MainPanel() {
		super();
		setPreferredSize(new Dimension(800, 600));
		setLayout(new GridLayout(0, 2));
		
		src = new ImagePanel();
		dst = new ImagePanel();
		add(src);
		add(dst);
	}
	
	public void load(URL url) throws IOException {
		BufferedImage img = ImageIO.read(url);
		src.loadImage(img);
		dst.loadImage(img);
	}
	
	public void load(String filename) throws IOException {
		BufferedImage img = ImageIO.read(new File(filename));
		src.loadImage(img);
		dst.loadImage(img);
		repaint();
	}
	
	public void save(String filename) throws IOException {
		ImageIO.write(dst.getImage(), "png", new File(filename));
	}
	
	public ImagePanel getSrc() {
		return src;
	}
	
	public ImagePanel getDst() {
		return dst;
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
