package model.comunity;

import java.util.Arrays;

public class Population {

	private Individual[] individuals;

	public Population() {
	}

	public Population(int num) {
		individuals = new Individual[num];
	}

	public Population(Individual[] individuals) {
		this.individuals = individuals;
	}

	public void initPopulation(Individual defaultIn) {
		for (int i = 0; i < individuals.length; i++) {
			Individual individual = new Individual(defaultIn);
			individual.fillRamdom();
			individuals[i] = individual;
		}
	}

	public void initPopulation() {
		int[][] chromosomes = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		Individual defaultIn = new Individual(chromosomes);
		for (int i = 0; i < individuals.length; i++) {
			Individual individual = new Individual(defaultIn);
			individual.fillRamdom();
			individuals[i] = individual;
		}
	}

	public Individual getBest() {

		Arrays.sort(individuals);

		return individuals[0];
	}

	public String tostring() {
		String st = "";
		for (int i = 0; i < individuals.length; i++) {
			st += individuals[i].fitness() + "|";
		}
		return st;
	}

	public Individual[] getIndividuals() {
		return individuals;
	}

	public void setIndividuals(Individual[] individuals) {
		this.individuals = individuals;
	}

	public static void main(String[] args) {
		Population population = new Population(1);
		int[][] chromosomes = new int[][] { { 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 3, 0, 6, 0, 0, 0, 0, 0, 0 }, { 4, 0, 0, 0, 0, 0, 0, 0, 0 }, { 5, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 6, 0, 0, 0, 0, 0, 0, 0, 0 }, { 7, 0, 0, 0, 0, 0, 0, 0, 0 }, { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 9, 0, 0, 0, 0, 0, 0, 0, 0 } };
		Individual defaultIn = new Individual(chromosomes);
		population.initPopulation(defaultIn);

		System.out.println(population.tostring());
		System.out.println(population.getIndividuals()[0].toString());

		System.out.println(defaultIn.getGenes()[2].getDefaultIndexs());
	}
}
