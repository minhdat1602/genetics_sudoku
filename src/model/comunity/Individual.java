package model.comunity;

import java.util.ArrayList;

public class Individual implements Comparable<Individual> {
	private Gene[] genes;

	public Individual(Individual order) {

		genes = new Gene[order.getGenes().length];
		for (int i = 0; i < genes.length; i++) {
			genes[i] = new Gene();
			genes[i].copy(order.getGenes()[i]);
			genes[i].setDefaultValues(order.getGenes()[i].getDefaultValues());
		}
	}

	public Individual() {
		genes = new Gene[9];
		for (int i = 0; i < genes.length; i++) {
			genes[i] = new Gene();
		}
	}

	public Individual(int[][] chromosomes) {

		this.genes = new Gene[chromosomes.length];
		for (int i = 0; i < chromosomes.length; i++) {
			Gene gene = new Gene();
			ArrayList<Integer> defaultIndexs = new ArrayList<Integer>();
			ArrayList<Integer> defaultValues = new ArrayList<Integer>();
			for (int j = 0; j < chromosomes[i].length; j++) {
				gene.getChromosomes()[j] = chromosomes[i][j];
				if (chromosomes[i][j] != 0) {
					defaultIndexs.add(j);
					defaultValues.add(chromosomes[i][j]);
				}
			}
			gene.setDefaultIndexs(defaultIndexs);
			gene.setDefaultValues(defaultValues);
			genes[i] = gene;
		}
	}

	public void fillRamdom() {
		for (int i = 0; i < genes.length; i++) {
			genes[i].fillRandom();
		}
	}

	public boolean equals(Individual order) {
		for (int i = 0; i < order.getGenes().length; i++) {
			if (!genes[i].equalChro(order.getGenes()[i]))
				return false;
		}
		return true;
	}

	public String toString() {
		String st = "";
		for (int i = 0; i < 9; i++) {
			st += "\n\t" + genes[i].toString();
		}
		// "\n" + st += "Heuristic: " + fitness();
		return st;
	}

	public int rowHeuristic() {
		int result = 0;
		for (int k = 0; k < 9; k++) {
			for (int i = 0; i < 9; i++) {
				for (int j = i + 1; j < 9; j++) {
					if (genes[k].getChromosomes()[i] == genes[k].getChromosomes()[j])
						result++;
				}
			}
		}
		return result;
	}

	public int colHeuristic() {
		int result = 0;
		for (int k = 0; k < 9; k++) {
			for (int i = 0; i < 9; i++) {
				for (int j = i + 1; j < 9; j++) {
					if (genes[i].getChromosomes()[k] == genes[j].getChromosomes()[k]) {
						result++;
					}
				}
			}
		}
		return result;
	}

	// total Box heuristic
	public int boxHeuristic() {
		int result = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 1; k <= 9; k++) {
					result += checkBox(i, j, k);
				}
			}
		}
		return result;
	}

	// box heristic at num index
	private int checkBox(int offSetX, int offSetY, int num) {
		int result = 0;

		int beginX = offSetX * 3;
		int beginY = offSetY * 3;

		for (int i = beginX; i < beginX + 3; i++) {
			for (int j = beginY; j < beginY + 3; j++) {
				if (genes[i].getChromosomes()[j] == num)
					result++;
			}
		}

		return (result > 0 ? --result : 0);
	}

	// fitness is total conflict of state
	public int fitness() {
		return rowHeuristic() + colHeuristic() + boxHeuristic();
	}

	public Gene[] getGenes() {
		return genes;
	}

	public void setGenes(Gene[] genes) {
		this.genes = genes;
	}

	public static void main(String[] args) {
		Individual individual = new Individual();
		individual.fillRamdom();
		Individual x = new Individual();
		x.fillRamdom();
		System.out.println(individual.equals(individual));
		System.out.println(individual.equals(x));
		System.out.println("Individual:" + individual.toString());
		System.out.println("Box heuristic: " + individual.boxHeuristic());
	}

	@Override
	public int compareTo(Individual o) {
		if (o == null)
			return Integer.MAX_VALUE;
		return this.fitness() - o.fitness();
	}
}
