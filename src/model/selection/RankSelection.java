package model.selection;

import java.util.Arrays;

import model.comunity.Individual;
import model.comunity.Population;

public class RankSelection implements Selection {

	public RankSelection() {
	}

	@Override

	public Individual[] rankSelection(int rank, Individual[]... in) {
		Individual[] inSum = new Individual[getSumLength(in)];

		int begin = 0;
		for (int i = 0; i < in.length; i++) {
			Individual[] inElement = in[i];
			for (int j = 0; j < inElement.length; j++)
				inSum[begin++] = inElement[j];
		}

		Arrays.sort(inSum);

		Individual[] inRank = new Individual[rank];

		for (int i = 0; i < rank; i++) {
			inRank[i] = inSum[i];
		}

		return inRank;
	}

	private int getSumLength(Individual[]... in) {
		int sum = 0;
		for (int i = 0; i < in.length; i++) {
			sum += in[i].length;
		}
		return sum;
	}

	public static void main(String[] args) {
		RankSelection selection = new RankSelection();

		Population oldp = new Population(20);
		oldp.initPopulation();
		oldp.sortIndividuals();
		System.out.println("Old:  " + oldp.tostring());

		Population newp = new Population(20);
		newp.initPopulation();
		newp.sortIndividuals();
		System.out.println("New:  " + newp.tostring());
		
		Population nextp = new Population();
		Individual[] nextIn = selection.rankSelection(oldp.getIndividuals().length, newp.getIndividuals(),
				oldp.getIndividuals());
		nextp.setIndividuals(nextIn);
		System.out.println("Next: " + nextp.tostring());
	}
}
