package model.comunity;

import java.util.Arrays;

public class Population {

	private Individual[] individuals;

	public Population() {
	}

	public Population(int individualNum) {
		individuals = new Individual[individualNum];
	}

	public Population(Individual[] individuals) {
		this.individuals = individuals;
	}

	public void initPopulation() {
		for (int i = 0; i < individuals.length; i++) {
			Individual individual = new Individual();
			individual.initIndividual();
			individuals[i] = individual;
		}
	}

	public void sortIndividuals() {
		Arrays.sort(individuals);
	}

	public Individual getBest() {
		// sort
		sortIndividuals();
		// get first
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

	public Individual getIndividual(int index) {
		return individuals[index];
	}

	public void setIndividual(Individual individual, int index) {
		individuals[index] = individual;
	}

	public static void main(String[] args) {
		Population population = new Population();
		System.out.println(population.tostring());
	}
}
