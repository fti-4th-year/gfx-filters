import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;


public class BottomPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private MainPanel main_panel;
	JFileChooser chooser;
	public BottomPanel(MainPanel mp) {
		super();
		main_panel = mp;
		chooser = new JFileChooser();
		
		JButton button;
		
		button = new JButton("Load");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = chooser.showOpenDialog(main_panel);
				if (option == JFileChooser.APPROVE_OPTION) {
					try {
						main_panel.load(chooser.getSelectedFile().getPath());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		add(button);
		button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = chooser.showSaveDialog(main_panel);
				if (option == JFileChooser.APPROVE_OPTION) {
					try {
						main_panel.save(chooser.getSelectedFile().getPath());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		add(button);
		
		button = new JButton("SRC to DST");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main_panel.copySrcToDst();
			}
		});
		add(button);
		button = new JButton("DST to SRC");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main_panel.copyDstToSrc();
			}
		});
		add(button);
	}
}
