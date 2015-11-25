import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import filter.Filter;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	
	public ImagePanel() {
		super();
		setBackground(new Color(0x0000FF));
		setBorder(BorderFactory.createLineBorder(getBackground(), 5));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, (getWidth() - image.getWidth())/2, (getHeight() - image.getHeight())/2, null);
	}
	
	public void loadImage(BufferedImage src) {
		image = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int iy = 0; iy < image.getHeight(); ++iy) {
			for(int ix = 0; ix < image.getWidth(); ++ix) {
				image.setRGB(ix, iy, src.getRGB(ix, iy));
			}
		}
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	void copy(ImagePanel src) {
		for(int iy = 0; iy < Math.min(image.getHeight(), src.image.getHeight()); ++iy) {
			for(int ix = 0; ix < Math.min(image.getWidth(), src.image.getWidth()); ++ix) {
				image.setRGB(ix, iy, src.image.getRGB(ix, iy));
			}
		}
	}
	
	void apply(Filter filter, BufferedImage src) {
		for(int iy = 0; iy < image.getHeight(); ++iy) {
			for(int ix = 0; ix < image.getWidth(); ++ix) {
				image.setRGB(ix, iy, filter.sample(ix, iy, src));
			}
		}
	}
}
