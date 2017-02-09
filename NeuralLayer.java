package connectFour;

import java.util.ArrayList;

public class NeuralLayer {

	ArrayList<Neuron> neurons = new ArrayList<>();
	
	public NeuralLayer(int inputlayerNeurons) {
		for(int i = 0; i < inputlayerNeurons; i++){
			neurons.add(new Neuron());
		}
	}

	public NeuralLayer(int numberOfNeurons, NeuralLayer previousNeuralLayer, double weightInitSpace) {
		for(int i = 0; i < numberOfNeurons; i++){
			neurons.add(new Neuron(previousNeuralLayer, weightInitSpace));
		}
	}

	public ArrayList<Neuron> getNeurons() {
		return neurons;
	}

	public void setNetworkInput(ArrayList<Integer> ownBoard, ArrayList<Integer> oponentBoard) {
//		System.out.println(ownBoard);
//		System.out.println(oponentBoard);

		for(int i = 0; i < ownBoard.size(); i++){
			neurons.get(ownBoard.get(i)-1).setState(1.0);
		}
		for(int i = 0; i < oponentBoard.size(); i++){
			neurons.get(oponentBoard.get(i)-1).setState(-1.0);
		}
	}

	public void computeState(NeuralLayer previousNeuralLayer) {
		for (Neuron neuron : neurons) {
			neuron.computeState(previousNeuralLayer.getNeurons());
		}
	}

	public ArrayList<Double> getNetworkOutput() {
		ArrayList<Double> probabilities = new ArrayList<>();
		
		for (Neuron neuron : neurons) {
			probabilities.add(neuron.getState());
		}
		
		return probabilities;
	}
	
}
