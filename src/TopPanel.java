import java.awt.BorderLayout;

import javax.swing.JPanel;


public class TopPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private MainPanel main_panel;
	private TabPanel tab_panel;
	private DetailsPanel details_panel;
	
	public TopPanel(MainPanel mp) {
		super();
		
		main_panel = mp;
		details_panel = new DetailsPanel(main_panel);
		tab_panel = new TabPanel(main_panel, details_panel);
		
		setLayout(new BorderLayout());
		add(tab_panel, BorderLayout.PAGE_START);
		add(details_panel, BorderLayout.CENTER);
	}
}
