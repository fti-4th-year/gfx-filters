import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import filter.GrayScaleFilter;
import filter.InvertFilter;
import filter.MatrixFilter;
import filter.UniformFilter;


public class TabPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private MainPanel main_panel;
	private DetailsPanel details_panel;
	
	private JToggleButton[] tbArray;
	
	public TabPanel(MainPanel mp, DetailsPanel dp) {
		super();
		main_panel = mp;
		details_panel = dp;
		setLayout(new GridLayout(1, 0));
		
		tbArray = new JToggleButton[3];
		
		JButton button;
		JToggleButton tab;
		
		button = new JButton("Uniform");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < tbArray.length; ++i)
					tbArray[i].setSelected(false);
				details_panel.setVisible(false);
				main_panel.apply(new UniformFilter());
			}
		});
		add(button);
		
		button = new JButton("Invert");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < tbArray.length; ++i)
					tbArray[i].setSelected(false);
				details_panel.setVisible(false);
				main_panel.apply(new InvertFilter());
			}
		});
		add(button);
		
		button = new JButton("GrayScale");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < tbArray.length; ++i)
					tbArray[i].setSelected(false);
				details_panel.setVisible(false);
				main_panel.apply(new GrayScaleFilter());
			}
		});
		add(button);
		
		tab = new JToggleButton("Sort");
		tab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JToggleButton)e.getSource()).isSelected()) {
					for(int i = 0; i < tbArray.length; ++i)
						if(i != 0) tbArray[i].setSelected(false);
					details_panel.setMode(DetailsPanel.MODE_SORT);
					details_panel.applyCurrentSettings();
					details_panel.setVisible(true);
				} else {
					details_panel.setVisible(false);
					main_panel.apply(new UniformFilter());
				}
			}
		});
		add(tab);
		tbArray[0] = tab;
		
		tab = new JToggleButton("Matrix");
		tab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JToggleButton)e.getSource()).isSelected()) {
					for(int i = 0; i < tbArray.length; ++i)
						if(i != 1) tbArray[i].setSelected(false);
					details_panel.setMode(DetailsPanel.MODE_MATRIX);
					details_panel.applyCurrentSettings();
					details_panel.setVisible(true);
				} else {
					details_panel.setVisible(false);
					main_panel.apply(new UniformFilter());
				}
			}
		});
		add(tab);
		tbArray[1] = tab;
		
		tab = new JToggleButton("Gaussian");
		tab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JToggleButton)e.getSource()).isSelected()) {
					for(int i = 0; i < tbArray.length; ++i)
						if(i != 2) tbArray[i].setSelected(false);
					details_panel.setMode(DetailsPanel.MODE_GAUSSIAN);
					details_panel.applyCurrentSettings();
					details_panel.setVisible(true);
				} else {
					details_panel.setVisible(false);
					main_panel.apply(new UniformFilter());
				}
			}
		});
		add(tab);
		tbArray[2] = tab;
		
		details_panel.setVisible(false);
	}
}
