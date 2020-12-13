package model.crossover;

import java.util.Random;

import model.comunity.Individual;
import model.comunity.Population;

public class SinglePointCrossover implements Crossover {

	public SinglePointCrossover() {
		super();
	}

	public Individual cross(Individual dad, Individual mom) {

		// System.out.println(population.tostring());
		Random rd = new Random();

		// Số lượng gene mỗi cá thể.
		int sizeOfGene = dad.getGenes().length;

		/*
		 * Số lượng gene của cá thể bố và mẹ di truyền cho cá thể con. geneDadNumber: số
		 * lượng gene của bố trong cá thể con còn lại số lượng gene của cá thể mẹ.
		 */
		int geneDadNumber = rd.nextInt(3) + 4;

		// Khởi tạo cá thể con.
		Individual individualSon1 = new Individual();
		Individual individualSon2 = new Individual();

		// Di truyền gene của bố cho gene con
		for (int i = 0; i < geneDadNumber; i++) {
			individualSon1.setGene(dad.getGene(i), i);
			individualSon2.setGene(mom.getGene(i), i);
		}
		// Di truyền gene mẹ cho gene con
		for (int i = geneDadNumber; i < sizeOfGene; i++) {
			individualSon1.setGene(mom.getGene(i), i);
			individualSon2.setGene(dad.getGene(i), i);
		}
		// System.out.println("Dad after: " + individual_dad.toString());
		// System.out.println("Mom after: " + individual_mom.toString());
		// System.out.println("Cross at: " + geneDadNumber);
		// System.out.println("Son 1: " + individualSon1.toString());
		// System.out.println("Fitness son 1: " + individualSon1.fitness());
		// System.out.println("Son 2: " + individualSon2.toString());
		// System.out.println("Fitness son 2: " + individualSon2.fitness());

		return (individualSon1.fitness() > individualSon2.fitness() ? individualSon2 : individualSon1);
	}

	public static void main(String[] args) {
		SinglePointCrossover tpc = new SinglePointCrossover();
		Population pop = new Population(20);
		pop.initPopulation();
		Individual in = tpc.cross(pop.getIndividual(2), pop.getIndividual(3));
		System.out.println("Son:" + in.toString());
	}

}
