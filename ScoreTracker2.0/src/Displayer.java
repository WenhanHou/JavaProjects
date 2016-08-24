import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

class Displayer implements Observer{
	private Frame f; // frame
	private Button RdBth;
	private Button HoleBth;
	private Button StrikeBth;
	private Button EndRdBth;
	private Button EndHoleBth;
	private Button EndStrikeBth;
	private Controler controler;
	private JComboBox<String> comboBox;

	Displayer() {
		displayPanel();
		controler = new Controler();
		controler.addObserver(this);
	}

	public void update(Observable obj, Object arg){
		JOptionPane.showMessageDialog(null, arg.toString());
	}
	public void displayPanel() {
		f = new Frame("Golf Score Tracker");
		f.setBounds(300, 100, 600, 500);
		f.setLayout(new FlowLayout()); // default layout mgr

		List<String> mapValuesList = new ArrayList<String>(Club.map.values());
		String[] arr = new String[mapValuesList.size()];
		mapValuesList.toArray(arr);

		comboBox = new JComboBox<String>(arr);
		comboBox.setEditable(false);

		RdBth = new Button("Start Round");
		HoleBth = new Button("Start a hole");
		StrikeBth = new Button("Start a strike");
		EndRdBth = new Button("End round");
		EndHoleBth = new Button("End a hole");
		EndStrikeBth = new Button("End a strike");

		f.add(RdBth);
		f.add(HoleBth);
		f.add(comboBox);
		f.add(StrikeBth);
		f.add(EndStrikeBth);
		f.add(EndHoleBth);
		f.add(EndRdBth);

		initEvent();
		f.setVisible(true);
	}

	public void initEvent() {
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("closing");
				System.exit(0);
			}
		});

		RdBth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.start_Round();
			}
		});

		HoleBth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.start_Hole();
			}
		});

		StrikeBth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.start_Strike(comboBox.getSelectedItem().toString());
			}
		});

		EndRdBth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.end_Round();
			}
		});

		EndHoleBth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.end_Hole();
			}
		});

		EndStrikeBth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.end_Strike();
			}
		});

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Displayer();
	}
}
