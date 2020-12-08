package model;

public class Individual implements Comparable<Individual> {
	private Gene[] genes;

	public Individual(Gene[] order) {
		genes = new Gene[order.length];

		for (int i = 0; i < order.length; i++) {
			genes[i] = new Gene();
			genes[i].copy(order[i]);
		}
	}

	public Individual() {
		genes = new Gene[9];
	}

	public void initIndividual() {
		for (int i = 0; i < 9; i++) {
			genes[i] = new Gene();
		}
	}

	public String toString() {
		String st = "";
		for (int i = 0; i < 9; i++) {
			st += "\n\t" + genes[i].toString();
		}
		return st;
	}

	public int rowHeuristic() {
		int result = 0;
		for (int k = 0; k < 9; k++) {
			for (int i = 0; i < 9; i++) {
				for (int j = i + 1; j < 9; j++) {
					if (genes[k].getTiles()[i] == genes[k].getTiles()[j])
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
					if (genes[i].getTiles()[k] == genes[j].getTiles()[k]) {
						result++;
					}
				}
			}
		}
		return result;
	}

	//
	public int boxHeuristic() {
		int result = 0;

		return result;
	}

	public int fitness() {
		return rowHeuristic() + colHeuristic() + boxHeuristic();
	}

	public Gene[] getGenes() {
		return genes;
	}

	public void setGenes(Gene[] genes) {
		this.genes = genes;
	}

	public void setGene(Gene gene, int number) {
		genes[number] = gene;
	}

	public Gene getGene(int number) {
		return genes[number];
	}

	public static void main(String[] args) {
		Individual individual = new Individual();
		individual.initIndividual();

		System.out.println(individual.toString());

		System.out.println("Column heuristic: " + individual.colHeuristic());
	}

	@Override
	public int compareTo(Individual o) {
		return this.fitness() - o.fitness();
	}
}
