import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class BottomPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private MainPanel main_panel;
	public BottomPanel(MainPanel mp) {
		super();
		main_panel = mp;
		
		JButton button;
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
