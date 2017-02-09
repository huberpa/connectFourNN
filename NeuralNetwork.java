package connectFour;

import java.util.ArrayList;

public class NeuralNetwork {
	
	ArrayList<NeuralLayer> layers = new ArrayList<>();
	ArrayList<Double> probabilities;
	
	public NeuralNetwork(int hiddenLayerCount, int hiddenLayerNeurons, int inputlayerNeurons, int outputLayerNeurons, double weightInitSpace) {
		
		layers.add(new NeuralLayer(inputlayerNeurons));
		
		for(int i = 0; i < hiddenLayerCount; i++){
			layers.add(new NeuralLayer(hiddenLayerNeurons, layers.get(layers.size()-1), weightInitSpace));
		}
		
		layers.add(new NeuralLayer(outputLayerNeurons, layers.get(layers.size()-1), weightInitSpace));
	}

	public void computeAction(ArrayList<Integer> ownBoard, ArrayList<Integer> oponentBoard) {
		
		layers.get(0).setNetworkInput(ownBoard, oponentBoard);
		
//		System.out.println("");
//		
//		System.out.println("Layer: 0");
//		for (Neuron n : layers.get(0).getNeurons()) {
//			System.out.print(n.getState()+" , ");
//		}
		
		for (int i = 1; i < layers.size(); i++) {
			layers.get(i).computeState(layers.get(i-1));
			
//			System.out.println("");

//			System.out.println("Layer: "+i);
//			for (Neuron n : layers.get(i).getNeurons()) {
//				System.out.print(n.getState()+" , ");
//			}
		}
		
		probabilities = layers.get(layers.size()-1).getNetworkOutput();

//		System.out.println(probabilities);
			}

	public int getAction(int i) {
		return getMaxValue(probabilities,i);
	}
	
	public int getMaxValue(ArrayList<Double> probabilities, int number){
		ArrayList<Double> tmp_probabilities = probabilities;
		int max = 0;
		for (int j = 0; j <= number; j++) {
			max = 0;
			if(tmp_probabilities.size()>0){
				for (int i = 1; i < tmp_probabilities.size(); i++) {
				    if (tmp_probabilities.get(i) > tmp_probabilities.get(max)) {
				      max = i;
				    }
				}
				tmp_probabilities.remove(max);
			}else{
				return -1;
			}
		}
		return max+1;
	}
}
