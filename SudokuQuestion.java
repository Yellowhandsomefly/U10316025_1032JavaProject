import java.util.Random;
public class SudokuQuestion {

	Random random = new Random();

	//an array of final stage 
	int[][] question ={{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 
			{ 4, 5, 6, 7, 8, 9, 1, 2, 3 },  
            { 7, 8, 9, 1, 2, 3, 4, 5, 6 },  
            { 2, 1, 4, 3, 6, 5, 8, 9, 7 },  
            { 3, 6, 5, 8, 9, 7, 2, 1, 4 },  
            { 8, 9, 7, 2, 1, 4, 3, 6, 5 },  
            { 5, 3, 1, 6, 4, 2, 9, 7, 8 },  
            { 6, 4, 2, 9, 7, 8, 5, 3, 1 },  
            { 9, 7, 8, 5, 3, 1, 6, 4, 2 }};
	int[][][] isEdit = new int[9][3][3];
	int[][] twoIsEdit = new int[9][9];
	
	/*exchange element of final stage
	/*ex. 1 -> 9 and 9 -> 1*/
	public void exchangeElement(){
	
		for(int time = 0; time < 4; time++){
			
			int first = random.nextInt(8) + 1;
			int second = random.nextInt(8) + 1;
		
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					if(question[i][j] == first){
						question[i][j] = second;
					}else if(question[i][j] == second){
						question[i][j] = first;
					}
				}
			}
		}
	}
	
	//exchange line of final stage
	public void exchangeLine(){
		int temp;
		int[] tempLine;
		for(int time = 0; time < 4; time++){
			
			//exchange column of final stage
			int changeLine = random.nextInt(8) + 1;
			
			for(int j = 0; j < 9; j++){
				if(changeLine % 3 == 0){
					temp = question[j][changeLine];
					question[j][changeLine] = question[j][changeLine + 1];
					question[j][changeLine + 1] = temp;
				}else{
					temp = question[j][changeLine];                       				
					question[j][changeLine] = question[j][changeLine - 1];
					question[j][changeLine - 1] = temp;
				}
			}
			
			//exchange row of final stage
			int changeRow = random.nextInt(8) + 1;
			
			if(changeRow % 3 == 0){
				tempLine = question[changeRow];
				question[changeRow] = question[changeRow + 1];
				question[changeRow + 1] = tempLine;
			}else{
				tempLine = question[changeRow];
				question[changeRow] = question[changeRow - 1];
				question[changeRow - 1] = tempLine;
			}
		}
	}
	
	//symmetry matrix
	public void exchangeSymmetry(){
		int temp = 0;  
		for(int i = 0; i < 9; i++){       
	    	for(int j = 0 + i; j < 9; j++){ 
	    		temp = question[i][j];
	    		question[i][j] =  question[j][i];
	    		question[j][i] = temp;
	    	}
		}	
	}
	
	//generate question
	public void generateQuestion(String difficulty){
		int deleteNumber = 5;
		for(int number = 0; number < 9; number++){
			switch(difficulty){
			
				case "easy":
					deleteNumber = random.nextInt(2) + 1;
					howTrouble(number,deleteNumber);	
					break;
				case "mid":
					deleteNumber = random.nextInt(2) + 2;
					howTrouble(number,deleteNumber);	
					break;
				case "difficulty":
					deleteNumber = random.nextInt(4) + 3;
					howTrouble(number,deleteNumber);		
					break;
				case "evil":
					deleteNumber = random.nextInt(4) + 4;
					howTrouble(number,deleteNumber);	
					break;
				default :
					break;
			}
		}
		
		for(int y = 0; y < 9; y++){
			for(int j = 0; j  < 9; j++){
				twoIsEdit[y][j] = isEdit[j / 3 + 3 * (y / 3)][y % 3][j % 3]; 
			}
		}
	}
	
	//Return IsEdit array to ensure which block can be entered by user
	public int[][] getIsEdit(){
		return twoIsEdit;
	}
	
	//ensure which block can be entered by user
	public void howTrouble(int i, int deleteNumber){
		int[] position = new int[2];
		
		for(int j = 0; j < 3; j++){
			for(int k = 0; k < 3; k++){
				isEdit[i][j][k] = 0;
			}
		}
		for(int o = 0; o < deleteNumber; o++){
			boolean isDelete = false;
			while(!(isDelete)){
				position[0] = random.nextInt(3);
				position[1] = random.nextInt(3);
				if(isEdit[i][position[0]][position[1]] != 1){
					isEdit[i][position[0]][position[1]] = 1;
					isDelete = true;
				}
			}
		}
	}
	
	//wash the element of the final stage
	public void wash(){
		exchangeElement();
		exchangeSymmetry();
		exchangeLine();
	}
	
	//return the question
	public int[][] getQuestion(){
		return question;
	}
	
	//decide is the answer user enter right
	public boolean isRight(int[][] answer){
		int[] checkRow = new int[9];
		int[] checkColumn = new int[9];
		int[][][] blockMode = new int[9][3][3];
		int[] checkBlock = new int[9];
		boolean isRight = true;
		
		//check row
		if(isRight){
			for(int row = 0; row < 9; row++){
				if(isRight){
					for(int n = 0; n < 9; n++){
						checkRow[answer[row][n] - 1]++;
					}
					for(int g = 0; g < 9; g++){
						if(checkRow[g] > 1 + row){
							isRight = false;
						}
					}
				}
			}
		}
		
		//check column
		if(isRight){
			for(int column = 0; column < 9; column++){
				if(isRight){
					for(int a = 0; a < 9; a++){
						checkColumn[answer[a][column] - 1]++;
					}
					for(int g = 0; g < 9; g++){
						if(checkColumn[g] > 1 + column){
							isRight = false;
						}
					}
				}
			}
		}
		
		//check nine blocks
		if(isRight){
			for(int y = 0; y < 9; y++){
				for(int j = 0; j  < 9; j++){
					blockMode[j / 3 + 3 * (y / 3)][y % 3][j % 3] = answer[y][j]; 
				}
			}
			for(int block = 0; block < 9; block++){
				if(isRight){
					for(int g = 0; g < 3; g++){
						for(int s = 0; s < 3; s++){
							checkBlock[blockMode[block][g][s] - 1]++;
						}
					}
					for(int g = 0; g < 9; g++){
						if(checkBlock[g] > 1 + block){
							isRight = false;
						}
					}
				}
			}
		}
		
		return isRight;
	}
}

