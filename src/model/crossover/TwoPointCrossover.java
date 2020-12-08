package model.crossover;

import java.util.Random;

import model.Individual;
import model.Population;

public class TwoPointCrossover implements Crossover {

	public TwoPointCrossover() {
		super();
	}

	public Individual cross(Population population) {
		//System.out.println(population.tostring());
		Random rd = new Random();

		// Số lượng individual trong population.
		int sizeOfPopulation = population.getIndividuals().length;

		// Lấy ngẫu nhiên cá thể bố và mẹ trong một Population.
		int individualDadRandom = rd.nextInt(sizeOfPopulation);
		int individualMomRandom;
		// Đảm bảo rằng individual bố khác mẹ.
		do {
			individualMomRandom = rd.nextInt(sizeOfPopulation);
		} while (individualMomRandom == individualDadRandom);

		Individual individual_dad = population.getIndividual(individualDadRandom);
		//System.out.println(individual_dad.toString());
		Individual individual_mom = population.getIndividual(individualMomRandom);

		// Số lượng gene mỗi cá thể.
		int sizeOfGene = individual_dad.getGenes().length;

		/*
		 * Số lượng gene của cá thể bố và mẹ di truyền cho cá thể con. geneDadNumber: số
		 * lượng gene của bố trong cá thể con còn lại số lượng gene của cá thể mẹ.
		 */
		int geneDadNumber = rd.nextInt(sizeOfGene + 1);

		// Khởi tạo cá thể con.
		Individual individualSon = new Individual();

		// Di truyền gene của bố cho gene con
		for (int i = 0; i < geneDadNumber; i++) {
			individualSon.setGene(individual_dad.getGene(i), i);
		}
		// Di truyền gene mẹ cho gene con
		for (int i = geneDadNumber; i < sizeOfGene; i++) {
			individualSon.setGene(individual_mom.getGene(i), i);
		}
		//System.out.println(individual_dad.toString());
		//System.out.println(population.tostring());
		return individualSon;
	}
	public static void main(String[] args) {
		TwoPointCrossover tpc = new TwoPointCrossover();
		Population pop = new Population(20);
		pop.initPopulation();
		Individual in = tpc.cross(pop);
		System.out.println("After cross:" + in.toString());
	}

}
