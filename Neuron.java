package connectFour;

import java.util.ArrayList;

public class Neuron {

	double state = 0;
	ArrayList<Double> incomingWeights = new ArrayList<>();
	
	public Neuron() {	
	}
	
	public Neuron(NeuralLayer previousNeuralLayer, double weightInitSpace) {
		for (Neuron neuron : previousNeuralLayer.getNeurons()) {
			incomingWeights.add(Math.random()*2-1);
		}
	}
	
	public ArrayList<Double> getWeights(){
		return incomingWeights;
	}
	
	public void setState(double state){
		this.state = state;
	}

	public double getState() {
		return state;
	}
	
	public void computeState(ArrayList<Neuron> previousNeurons){
		double inputSum = 0;
		for(int i = 0; i < incomingWeights.size(); i++){
			inputSum += incomingWeights.get(i)*previousNeurons.get(i).getState();
		}
		this.state = 1/(1+Math.exp(-inputSum));
	}
}
