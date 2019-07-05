package main.com.show;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import AcidRain.DataBase;
import AcidRain.StartFrame;

public class ResultView implements ActionListener {

	JFrame frame;
	JPanel panel, panel_1;
	JButton endButton, reStartButton, btnNewButton, button;
	JLabel lblNewLabel, scoreLabel, nameLabel;
	int score, wordCount;
	private JTable table;
	Vector<Vector<String>> vc = new Vector<Vector<String>>();

	Vector<String> userColumn = new Vector<String>();
	Vector<String> userRow = new Vector<String>();
	DefaultTableModel model = new DefaultTableModel();
	private JTable table_1;

	DataBase database = new DataBase();
	
	public ResultView(int score, int wordCount) {
		this.score = score;
		this.wordCount = wordCount;
		
		frame = new JFrame();
		panel = new JPanel();
		panel_1 = new JPanel();
		lblNewLabel = new JLabel();
		endButton = new JButton("End");
		reStartButton = new JButton("Re");
		lblNewLabel = new JLabel("\uACB0\uACFC");
		scoreLabel = new JLabel();
		nameLabel = new JLabel(StartFrame.user.getUSER_ID());

		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(127, 10, 230, 242);
		panel.add(panel_1);
		panel_1.setLayout(null);

		endButton.setBounds(12, 209, 76, 23);
		endButton.addActionListener(this);
		panel_1.add(endButton);

		reStartButton.setBounds(129, 209, 76, 23);
		reStartButton.addActionListener(this);
		panel_1.add(reStartButton);

		lblNewLabel.setBounds(98, 10, 44, 15);
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(27, 26, 178, 173);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel namein = new JLabel("\uC774\uB984");
		namein.setBounds(23, 35, 57, 15);
		panel_2.add(namein);

		JLabel scorein = new JLabel("\uC810\uC218");
		scorein.setBounds(23, 66, 57, 15);
		panel_2.add(scorein);

		nameLabel.setBounds(78, 35, 57, 15);
		panel_2.add(nameLabel);

		//score = checkTheFinishedGame(score);

		scoreLabel.setText(Integer.toString(score));
		scoreLabel.setBounds(78, 67, 57, 15);
		panel_2.add(scoreLabel);
		
		database.InsertScore(StartFrame.user.getUSER_ID(), Integer.toString(GameChooseUI.GAMESCORE), StartFrame.user.getWhichGame());

		String[][] ColumnName = { { "ID", "NAME", "RAINSCR", "MINESCR", "TotalSCR" }, };
		String[] Name = { "ID", "NAME", "RAINSCR", "MINESCR", "TotalSCR" };

		model = new DefaultTableModel(ColumnName, Name);
		
		table = new JTable();
		
		vc = database.View();
		for(int i=0;i<vc.size();i++)
			model.addRow(vc.get(i));
		
		table.setModel(model);
		panel.add(table);
		table.setBounds(12, 278, 460, 174);
		table.setVisible(true);
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		GameChooseUI.mainFrame.setVisible(false);
	}

	public int checkTheFinishedGame(int score) {
		return wordCount > 0 ? 0 : score;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == endButton) {
			System.exit(0);
		}

		if (e.getSource() == reStartButton) {
			GameChooseUI.mainFrame.dispose();
			GameChooseUI.GAMESCORE = 0;
			new GameChooseUI().showUI();

		}
	}

}
