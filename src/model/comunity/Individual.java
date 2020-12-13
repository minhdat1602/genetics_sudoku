package model.comunity;

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

	public boolean equalGene(Individual order) {
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
				if (genes[i].getChromosome(j) == num)
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

	public void setGene(Gene gene, int number) {
		genes[number] = new Gene();
		genes[number].copy(gene);
	}

	public Gene getGene(int number) {
		return genes[number];
	}

	public static void main(String[] args) {
		Individual individual = new Individual();
		individual.initIndividual();
		Individual x = new Individual();
		x.initIndividual();
		System.out.println(individual.equalGene(individual));
		System.out.println(individual.equalGene(x));
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
