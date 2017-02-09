package connectFour;

import java.util.ArrayList;
import java.util.Collections;

public class Board {
	
	ArrayList<Integer> boardOne;
	ArrayList<Integer> boardTwo;
	
	public void initializeBoard() {
		boardOne = new ArrayList<>();
		boardTwo = new ArrayList<>();
	}
	
	public ArrayList<Integer> getBoardOne() {
		return boardOne;
	}

	public ArrayList<Integer> getBoardTwo() {
		return boardTwo;
	}

	public void addPlayerOneAt(int input) {
		boardOne.add(input);
	}

	public boolean checkPlayerOneForWin() {
		
		for (Integer value : boardOne) {
				if(Collections.frequency(boardOne, value+1)>0 && Collections.frequency(boardOne, value+2)>0 && Collections.frequency(boardOne, value+3)>0){
					return true;
				}
				if(Collections.frequency(boardOne, value+5)>0 && Collections.frequency(boardOne, value+10)>0 && Collections.frequency(boardOne, value+15)>0){
					return true;
				}
				if(Collections.frequency(boardOne, value+6)>0 && Collections.frequency(boardOne, value+12)>0 && Collections.frequency(boardOne, value+18)>0){
					return true;
				}
				if(Collections.frequency(boardOne, value+7)>0 && Collections.frequency(boardOne, value+14)>0 && Collections.frequency(boardOne, value+21)>0){
					return true;
				}
		}	
		return false;
	}


	public void addPlayerTwoAt(int input) {
		boardTwo.add(input);
	}


	public boolean checkPlayerTwoForWin() {

		for (Integer value : boardTwo) {
			if(Collections.frequency(boardTwo, value+1)>0 && Collections.frequency(boardTwo, value+2)>0 && Collections.frequency(boardTwo, value+3)>0){
				return true;
			}
			if(Collections.frequency(boardTwo, value+5)>0 && Collections.frequency(boardTwo, value+10)>0 && Collections.frequency(boardTwo, value+15)>0){
				return true;
			}
			if(Collections.frequency(boardTwo, value+6)>0 && Collections.frequency(boardTwo, value+12)>0 && Collections.frequency(boardTwo, value+18)>0){
				return true;
			}
			if(Collections.frequency(boardTwo, value+7)>0 && Collections.frequency(boardTwo, value+14)>0 && Collections.frequency(boardTwo, value+21)>0){
				return true;
			}
		}	
		return false;
	}

	public int slideDown(int column) {
		
		for(int i = 0; i < 6; i++){
			int cell = 1+((column-1)*6)+i;
			if(Collections.frequency(boardOne, cell)==0 && Collections.frequency(boardTwo, cell)==0){
				return cell;
			}
		}
		return -1;
	}

	public void showGrid() {

		String line;
		System.out.println("");
		System.out.println("");
		System.out.println(" 1 2 3 4 5 6 7");

		for(int i = 6; i > 0; i--){
			line = "";
			for(int j = 1; j < 8; j++){
				if(Collections.frequency(boardOne, i+((j-1)*6))>0){
					line += " X";
				}
				else if(Collections.frequency(boardTwo, i+((j-1)*6))>0){
					line += " O";
				}else{
					line += " _";
				}
			}
			System.out.println(line);
		}
		
		System.out.println("");
		System.out.println("");
		
	}


}
