import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private MainPanel panel;
	
	public MainFrame() throws Exception {
		super();
		
		panel = new MainPanel();
		panel.load(getClass().getResource("resources/lenna.png"));
		
		add(panel, BorderLayout.CENTER);
		add(new TopPanel(panel), BorderLayout.PAGE_START);
		add(new BottomPanel(panel), BorderLayout.PAGE_END);
		pack();
		
		setTitle("Function Drawer");
		setMinimumSize(new Dimension(480, 320));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame frame = null;
				try {
					frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
