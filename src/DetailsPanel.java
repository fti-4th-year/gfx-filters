import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import color.Color;

import filter.GammaFilter;
import filter.GaussianFilter;
import filter.MatrixFilter;
import filter.SortFilter;


public class DetailsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public static final int
	  MODE_SORT      = 0x01,
	  MODE_MATRIX    = 0x02,
	  MODE_GAUSSIAN  = 0x03,
	  MODE_GAMMA     = 0x04;
	
	private int current_mode;
	
	private JPanel sort_panel;
	private JSlider ePos;
	
	private JPanel matrix_panel;
	private JSpinner[] eMatrix;
	private JSpinner eFactor;
	private JCheckBox eNormalize;
	private JSpinner[] eOffset;
	
	private JPanel gaussian_panel;
	private JSlider eSigma;
	
	private JPanel gamma_panel;
	private JSlider ePower;
	
	private JPanel current_panel = null;
	
	private MainPanel main_panel;
	
	public DetailsPanel(MainPanel mp) {
		super();
		
		setLayout(new BorderLayout());
		main_panel = mp;
		
		JPanel cells, line;
		JSpinner spinner;
		JCheckBox checkbox;
		JSlider slider;
		
		ActionListener action_listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applyCurrentSettings();
			}
		};
		ChangeListener change_listener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				applyCurrentSettings();
			}
		};
		
		sort_panel = new JPanel();
		{
			sort_panel.add(new JLabel("Position: 0"));
			ePos = new JSlider(0, 24, 13);
			ePos.addChangeListener(change_listener);
			sort_panel.add(ePos);
			sort_panel.add(new JLabel("24"));
		}
		
		matrix_panel = new JPanel();
		{
			eMatrix = new JSpinner[9];
			eOffset = new JSpinner[3];
			cells = new JPanel(new GridLayout(3,3,6,6));
			for(int i = 0; i < 9; ++i) {
				double val = i == 4 ? 9.0 : -1.0;
				spinner = new JSpinner(new SpinnerNumberModel(val, -99.0, 100.0, 1.0));
				spinner.addChangeListener(change_listener);
				cells.add(spinner);
				eMatrix[i] = spinner;
			}
			matrix_panel.add(cells);
			
			cells = new JPanel(new GridLayout(3, 0));
			{
				line = new JPanel(new FlowLayout(FlowLayout.LEFT));
				{
					line.add(new JLabel("Offset:"));
					for(int i = 0; i < 3; ++i) {
						spinner = new JSpinner(new SpinnerNumberModel(0, -255, 255, 1));
						spinner.addChangeListener(change_listener);
						line.add(spinner);
						eOffset[i] = spinner;
					}
				}
				cells.add(line);
				line = new JPanel(new FlowLayout(FlowLayout.LEFT));
				{
					line.add(new JLabel("Factor:"));
					spinner = new JSpinner(new SpinnerNumberModel(1, -99, 100, 0.1));
					spinner.addChangeListener(change_listener);
					line.add(spinner);
					eFactor = spinner;
				}
				cells.add(line);
				checkbox = new JCheckBox("Normalize");
				checkbox.addActionListener(action_listener);
				cells.add(checkbox);
				eNormalize = checkbox;
			}
			matrix_panel.add(cells);
		}
		
		
		gaussian_panel = new JPanel();
		gaussian_panel.add(new Label("Sigma: 0.5"));
		slider = new JSlider(1, 8, 2);
		slider.addChangeListener(change_listener);
		gaussian_panel.add(slider);
		eSigma = slider;
		gaussian_panel.add(new Label("4"));
		
		gamma_panel = new JPanel();
		gamma_panel.add(new JLabel("Power: 0.1"));
		slider = new JSlider(-10,10,0);
		slider.addChangeListener(change_listener);
		gamma_panel.add(slider);
		ePower = slider;
		gamma_panel.add(new JLabel("10"));
	}
	
	public void applyCurrentSettings() {
		switch (current_mode) {
		case MODE_SORT:
			main_panel.apply(new SortFilter(2, ePos.getValue()));
			break;
		case MODE_MATRIX:
			{
				double[] m = new double[9];
				for(int i = 0; i < 9; ++i) {
					m[i] = ((Double) eMatrix[i].getValue()).doubleValue();
				}
				
				double f = 1.0;
				Color o = new Color(
						((Integer) eOffset[0].getValue()).intValue()/255.0,
						((Integer) eOffset[1].getValue()).intValue()/255.0,
						((Integer) eOffset[2].getValue()).intValue()/255.0
						);
				
				f = ((Double) eFactor.getValue()).doubleValue();
				if(eNormalize.isSelected()) {
					double sum = 0.0;
					for(int i = 0; i < 9; ++i) {
						sum += m[i];
					}
					if(Math.abs(sum) > 1e-6)
						f = 1.0/sum;
				}
				
				for(int i = 0; i < 9; ++i) {
					m[i] = f*m[i];
				}
				
				main_panel.apply(new MatrixFilter(m, o));
			}
			break;
		case MODE_GAUSSIAN:
			main_panel.apply(new GaussianFilter(0.5*eSigma.getValue()));
		case MODE_GAMMA:
			{
				double pow = -0.1*ePower.getValue();
				main_panel.apply(new GammaFilter(Math.pow(10, pow)));
			}
			break;
		default:
			break;
		}
	}
	
	public void setMode(int mode) {
		if(current_panel != null) {
			remove(current_panel);
			current_panel = null;
		}
		switch (mode) {
		case MODE_SORT:
			current_panel = sort_panel;
			break;
		case MODE_MATRIX:
			current_panel = matrix_panel;
			break;
		case MODE_GAUSSIAN:
			current_panel = gaussian_panel;
			break;
		case MODE_GAMMA:
			current_panel = gamma_panel;
			break;
		default:
			System.err.println("unknown mode");
			break;
		}
		current_mode = mode;
		add(current_panel, BorderLayout.CENTER);
		updateUI();
	}
}
