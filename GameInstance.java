package connectFour;

public class GameInstance {

	final static int OUT_OF_BOUNDS = -1;
	boolean playerOne = true;
	Board connectFour;
	
	public GameInstance() {
		connectFour = new Board();
		connectFour.initializeBoard();
//		connectFour.showGrid();
		}
	
	public NeuralNetwork play(NeuralNetwork network1, NeuralNetwork network2){
		while(true){
			if(playerOne){
				playerOne = !playerOne;
				
				network1.computeAction(connectFour.boardOne, connectFour.boardTwo);
				int out = network1.getAction(0);
				
				int i = 1;
				int chipPosition = connectFour.slideDown(out);
				while(chipPosition == OUT_OF_BOUNDS){
					out = network1.getAction(i);
					if(out == -1){
						return network2;
					}
					chipPosition = connectFour.slideDown(out);
					i++;
				}
				
				connectFour.addPlayerOneAt(chipPosition);
				
//				connectFour.showGrid();
				
				if(connectFour.checkPlayerOneForWin()){
					return network1;
				}
			}else{
				playerOne = !playerOne;

				network2.computeAction(connectFour.boardOne, connectFour.boardTwo);
				int out = network2.getAction(0);
				
				int i = 1;
				int chipPosition = connectFour.slideDown(out);
				while(chipPosition == OUT_OF_BOUNDS){
					out = network2.getAction(i);
					if(out == -1){
						return network1;
					}
					chipPosition = connectFour.slideDown(out);
					i++;
				}				
				
				connectFour.addPlayerTwoAt(chipPosition);
				
//				connectFour.showGrid();
				
				if(connectFour.checkPlayerTwoForWin()){
					return network2;
				}
			}
		}
	}
	
}
