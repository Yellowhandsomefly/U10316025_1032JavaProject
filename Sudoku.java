import java.awt.event.*;
import java.awt.*;

import javax.swing.*;	
import javax.swing.border.LineBorder;


public class Sudoku extends JFrame{
	
	JTextField[][] block = new JTextField[9][9];
	private JLabel text = new JLabel("start");
	private JButton replayButton = new JButton("replay");
	private JButton clearButton = new JButton("clear");
	private JButton answerButton = new JButton("answer");
	Sudoku(){
		JPanel bPanel = new JPanel();
		replayButton.setBounds(130, 130, 500, 300);
		bPanel.add(replayButton);
		clearButton.setBounds(130, 730, 500, 300);
		bPanel.add(clearButton);
		answerButton.setBounds(130, 1330, 500, 300);
		bPanel.add(answerButton);
		
		JPanel blockPanel = new JPanel(new GridLayout(9, 9, 0, 0));
		blockPanel.setBorder(new LineBorder(Color.PINK, 5));
		for(int i = 1; i <= 9; i++){
			for(int j = 1; j <= 9; j++){
				block[i - 1][j - 1] = new JTextField("");
				block[i - 1][j - 1].setBorder(new LineBorder(Color.PINK, 2));
				blockPanel.add(block[i - 1][j - 1]);
			}
		}
		
		JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		textPanel.add(text);
		textPanel.setBorder(new LineBorder(Color.ORANGE, 5));
		
		JPanel questuonPanel = new JPanel(new BorderLayout());
		questuonPanel.add(textPanel, BorderLayout.NORTH);
		questuonPanel.add(blockPanel, BorderLayout.CENTER);
		
		setLayout(new GridLayout(1, 2, 0, 0));
		add(bPanel);
		add(questuonPanel);
	}
		
	
	public static void main(String[] args){
		JFrame frame = new Sudoku();
		frame.setTitle("U10316025_Sudoku");
		frame.setSize(1000, 800);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
