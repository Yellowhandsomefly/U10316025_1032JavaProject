import java.awt.*;
import java.awt.event.*;

import javax.swing.*;	
import javax.swing.border.LineBorder;


public class Sudoku extends JFrame{
	
	SudokuQuestion question = new SudokuQuestion();
	JTextField[][] block = new JTextField[9][9];
	JTextField[][] answerText = new JTextField[9][9];
	private JLabel text = new JLabel("start");
	private JButton easyButton = new JButton("easy");
	private JButton midButton = new JButton("mid");
	private JButton difficultButton = new JButton("difficult");
	private JButton evilButton = new JButton("evil");
	private JButton clearButton = new JButton("clear");
	private JButton answerButton = new JButton("answer");
	private JButton checkButton = new JButton("check");
	private JButton clearAnswerButton = new JButton("clear answer");
	
	Sudoku(){
		//Design the right  panel of the frame
		JPanel bPanel = new JPanel();
		bPanel.setLayout(null);
		
		//Button to choose a easy or mid or difficult question 
		easyButton.setBounds(10, 151, 150, 40);
		bPanel.add(easyButton);
		midButton.setBounds(170, 151, 150, 40);
		bPanel.add(midButton);
		difficultButton.setBounds(330, 151, 150, 40);
		bPanel.add(difficultButton);
		evilButton.setBounds(490, 151, 150, 40);
		bPanel.add(evilButton);
		
		//Button to clear the things that user enters 
		clearButton.setBounds(20, 204, 150, 40);
		bPanel.add(clearButton);
		
		//Button to show the answer
		answerButton.setBounds(20, 257, 150, 40);
		bPanel.add(answerButton);
		
		//Button to clear the answer
		clearAnswerButton.setBounds(200, 257, 150, 40);
		bPanel.add(clearAnswerButton);
		
		//Button to check the answer that user enter
		checkButton.setBounds(20, 320, 150, 40);
		bPanel.add(checkButton);
		
		/*design the block of answer*/
		//Declare the array of JPanel type to represent nine big blocks
		JPanel[] allAnswerBlockPanel = new JPanel[9];
		for(int o = 0; o < 9; o++){
			allAnswerBlockPanel[o] = new JPanel(new GridLayout(3, 3, 0, 0));
			allAnswerBlockPanel[o].setBorder(new LineBorder(Color.blue, 2));
		}
		
		//Declare a panel to include nine big blocks
		JPanel answerPanel = new JPanel(new GridLayout(3, 3, 0, 0));
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				answerText[i][j] = new JTextField();
				answerText[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				answerText[i][j].setFont(new Font("Courie", Font.BOLD, 30));
				answerText[i][j].setBorder(new LineBorder(new Color(153, 204, 255), 1));
				answerText[i][j].setEditable(false);
				
				//add answer text field to every blocks in every big block
				allAnswerBlockPanel[j / 3 + 3 * (i / 3)].add(answerText[i][j]);
				
			}
		}
		
		//Add nine big blocks to a panel
		for(int h = 0; h < 9; h++){
			answerPanel.add(allAnswerBlockPanel[h]);
		}
		answerPanel.setBounds(20, 400, 400, 400);
		bPanel.add(answerPanel);
		
		/*design the block of question*/
		//Declare the array of JPanel type to represent nine big blocks
		JPanel[] allBlockPanel = new JPanel[9];
		for(int o = 0; o < 9; o++){
			allBlockPanel[o] = new JPanel(new GridLayout(3, 3, 0, 0));
			allBlockPanel[o].setBorder(new LineBorder(new Color(255, 153, 255), 3));
		}
		
		//Declare a panel to include nine big blocks
		JPanel blockPanel = new JPanel(new GridLayout(3, 3, 0, 0));
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				block[i][j] = new JTextField();
				block[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				block[i][j].setFont(new Font("Courie", Font.BOLD, 50));
				block[i][j].setBorder(new LineBorder(Color.PINK, 2));
				
				//add text field to every blocks in every big block
				allBlockPanel[j / 3 + 3 * (i / 3)].add(block[i][j]);
				
			}
		}
	
		//Add nine big blocks to a panel
		for(int h = 0; h < 9; h++){
			blockPanel.add(allBlockPanel[h]);
		}
		
		//a panel to show the difficulty
		JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		text.setFont(new Font("Courie", Font.BOLD, 40));
		textPanel.add(text);
		textPanel.setBorder(new LineBorder(Color.ORANGE, 5));
		
		JPanel questuonPanel = new JPanel(new BorderLayout());
		questuonPanel.add(textPanel, BorderLayout.NORTH);
		questuonPanel.add(blockPanel, BorderLayout.CENTER);
		
		setLayout(new GridLayout(1, 2, 0, 0));
		add(bPanel);
		add(questuonPanel);
		
		//invoke listener
		easyButton.addActionListener(new easyButtonListener());
		midButton.addActionListener(new midButtonListener());
		difficultButton.addActionListener(new difficultButtonListener());
		evilButton.addActionListener(new evilButtonListener());
		easyButton.addActionListener(new clearAnswerButtonListener());
		midButton.addActionListener(new clearAnswerButtonListener());
		difficultButton.addActionListener(new clearAnswerButtonListener());
		evilButton.addActionListener(new clearAnswerButtonListener());
		clearButton.addActionListener(new clearButtonListener());
		answerButton.addActionListener(new answerButtonListener());
		checkButton.addActionListener(new checkButtonListener());
		clearAnswerButton.addActionListener(new clearAnswerButtonListener());
	}
	
	//Generate the easy question
	private class easyButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			text.setText("EASY");
			question.wash();
			question.generateQuestion("easy");
			int[][] isEdit = question.getIsEdit();
			int[][] content = question.getQuestion();
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					if(isEdit[i][j] == 1){
						block[i][j].setForeground(new Color(153, 204, 255));
						block[i][j].setEditable(true);
						block[i][j].setText("");
					}
					else{
						block[i][j].setForeground(new Color(0, 102, 153));
						block[i][j].setText(String.valueOf(content[i][j]));
						block[i][j].setEditable(false);
					}
				}
			}
		}
	}
	
	//Generate the mid question
	private class midButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			text.setText("COMMON");
			question.wash();
			question.generateQuestion("mid");
			int[][] isEdit = question.getIsEdit();
			int[][] content = question.getQuestion();
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					if(isEdit[i][j] == 1){
						block[i][j].setForeground(new Color(153, 204, 255));
						block[i][j].setEditable(true);
						block[i][j].setText("");
					}
					else{
						block[i][j].setForeground(new Color(0, 102, 153));
						block[i][j].setText(String.valueOf(content[i][j]));
						block[i][j].setEditable(false);
					}
				}
			}
		}
	}
	
	//Generate the difficult question
	private class difficultButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			text.setText("DIFFICULTY");
			question.wash();
			question.generateQuestion("difficulty");
			int[][] isEdit = question.getIsEdit();
			int[][] content = question.getQuestion();
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					if(isEdit[i][j] == 1){
						block[i][j].setForeground(new Color(153, 204, 255));
						block[i][j].setEditable(true);
						block[i][j].setText("");
					}
					else{
						block[i][j].setForeground(new Color(0, 102, 153));
						block[i][j].setText(String.valueOf(content[i][j]));
						block[i][j].setEditable(false);
					}
				}
			}
		}
	}
	
	//Generate the evil question
	private class evilButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			text.setText("EVIL");
			question.wash();
			question.generateQuestion("evil");
			int[][] isEdit = question.getIsEdit(); 
			int[][] content = question.getQuestion();
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					if(isEdit[i][j] == 1){
						block[i][j].setForeground(new Color(153, 204, 255));
						block[i][j].setEditable(true);
						block[i][j].setText("");
					}
					else{
						block[i][j].setForeground(new Color(0, 102, 153));
						block[i][j].setText(String.valueOf(content[i][j]));
						block[i][j].setEditable(false);
					}
				}
			}
		}
	}
	//Clear the thing that user enter
	private class clearButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			int[][] isEdit = question.getIsEdit(); 
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					if(isEdit[i][j] == 1){
						block[i][j].setText("");
					}
				}
			}
		}
	}
	
	//clear the answer
	private class clearAnswerButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					answerText[i][j].setText("");
				}
			}
		}
	}
	
	//check the answer that user enter
	private class checkButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			int[][] respond = new int[9][9];
			String status = "nothing";
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					try{
						respond[i][j] = Integer.parseInt(block[i][j].getText());
						if(respond[i][j] < 1 || respond[i][j] > 9){
							status = "error";
						}
					}
					catch(Exception ex){
						status = "error";
					}
				}
			}
			
			JFrame resultFrame = new JFrame();
			resultFrame.setTitle("the result");
			
			JLabel result = new JLabel();
			result.setHorizontalAlignment(SwingConstants.CENTER);
			result.setFont(new Font("Courie", Font.BOLD, 50));
			result.setForeground(new Color(0, 102, 153));
			
			if(status == "nothing"){
				boolean isRight = question.isRight(respond);
				
				if(isRight){
					result.setText("WIN");
			
				}else{
					result.setText("LOSE");
				}
			}else{
				result.setText("input error");
			}
			
			resultFrame.add(result);
			resultFrame.pack();
			resultFrame.setLocationRelativeTo(null); 
			resultFrame.setVisible(true);
		}
	}
	
	//show the answer
	private class answerButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e){
			int[][] answer = question.getQuestion();
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					
					answerText[i][j].setText(String.valueOf(answer[i][j]));
				}
			}
		}
	}
	
	//Frame
	public static void main(String[] args){
		JFrame frame = new Sudoku();
		frame.setTitle("U10316025_Sudoku");
		frame.setSize(1500, 900);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
