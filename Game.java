package connectFour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
	
	final static int OUT_OF_BOUNDS = -1;
	final static int NUMBER_OF_HIDDEN_LAYERS = 2;
	final static int NEURONS_PER_HIDDEN_LAYER = 40;
	final static int NEURONS_IN_INPUT = 42;
	final static int NEURONS_IN_OUTPUT = 7;
	
	final static double WEIGHT_INIT_SPACE = Math.random()*2+1;
	
	public static void main(String[] args) {

		ArrayList<NeuralNetwork> networks = new ArrayList<>();
		for(int i = 0; i < 4096; i++){
			networks.add(new NeuralNetwork(NUMBER_OF_HIDDEN_LAYERS, NEURONS_PER_HIDDEN_LAYER, NEURONS_IN_INPUT, NEURONS_IN_OUTPUT, WEIGHT_INIT_SPACE));
		}
		
		NeuralNetwork winner = getBestNetwork(networks);
		HashMap<Integer, Integer> mp = new HashMap<>();
		mp.
		
		Board connectFour = new Board();
		connectFour.initializeBoard();
		connectFour.showGrid();

		boolean playerOne = true;
		Scanner input = new Scanner(System.in);
				
		while(true){
			if(playerOne){
				playerOne = !playerOne;
				System.out.print("Player 1 make a move:");
				
				winner.computeAction(connectFour.boardOne, connectFour.boardTwo);
				int out = winner.getAction(0);
				
				int i = 1;
				int chipPosition = connectFour.slideDown(out);
				while(chipPosition == OUT_OF_BOUNDS){
					out = winner.getAction(i);
					chipPosition = connectFour.slideDown(out);
					i++;
				}
				
				connectFour.addPlayerOneAt(chipPosition);
				
				connectFour.showGrid();
				
				if(connectFour.checkPlayerOneForWin()){
					System.out.println("Player 1 wins!");
					input.close();
					break;
				}
			}else{
				playerOne = !playerOne;
				System.out.print("Player 2 make a move:");

				String inputString = input.nextLine();

				try { 
					Integer.parseInt(inputString); 
			    } catch(Exception e) { 
			    	System.out.println("Please enter a valid number!");
					playerOne = !playerOne;
					continue;
			    }
				
				int chipPosition = connectFour.slideDown(Integer.parseInt(inputString));
				
				if(chipPosition==OUT_OF_BOUNDS){
					System.out.println("Column already full!");
					playerOne = !playerOne;
					continue;
				}
				
				connectFour.addPlayerTwoAt(chipPosition);
				
				connectFour.showGrid();
				
				if(connectFour.checkPlayerTwoForWin()){
					System.out.print("Player 2 wins!");
					input.close();
					break;
				}
			}
		}
	}

	private static NeuralNetwork getBestNetwork(ArrayList<NeuralNetwork> networks) {

		ArrayList<NeuralNetwork> winningNetworks = new ArrayList<>();

		System.out.println(networks.size());
		
		for(int i = 0; i < networks.size(); i += 2){
			GameInstance game = new GameInstance();
			winningNetworks.add(game.play(networks.get(i), networks.get(i+1)));
		}
		
		if(winningNetworks.size() == 1){
			return winningNetworks.get(0);
		}else{
			return getBestNetwork(winningNetworks);
		}
	}
}
