package model.crossover;

import java.util.Random;

import model.comunity.Gene;
import model.comunity.Individual;
import model.comunity.Population;

public class SinglePointCrossover implements Crossover {

	public SinglePointCrossover() {
		super();
	}

	public Individual cross(Individual dad, Individual mom) {

//		System.out.println("Dad before: " + dad.toString());
//		System.out.println("Mom before: " + mom.toString());

		// System.out.println(population.tostring());
		Random rd = new Random();

		// Số lượng gene mỗi cá thể.
		int sizeOfGene = dad.getGenes().length;
//		for(int i = 0; i < 9; i++) {
//			System.out.println("Index " + i + " " + mom.getGenes()[i].getDefaultIndexs());
//			System.out.println("Value " + i + " " + mom.getGenes()[i].getDefaultValues());
//		}

		/*
		 * Số lượng gene của cá thể bố và mẹ di truyền cho cá thể con. geneDadNumber: số
		 * lượng gene của bố trong cá thể con còn lại số lượng gene của cá thể mẹ.
		 */
		int indexDivide = rd.nextInt(8) + 1;

		// Khởi tạo cá thể con.
		Individual son1 = new Individual();
		Individual son2 = new Individual();

		// Di truyền gene của bố cho gene con
		for (int i = 0; i < indexDivide; i++) {
			Gene x = new Gene();
			// x.setDefaultIndexs(dad.getGenes()[i].getDefaultIndexs());
			// x.setDefaultValues(dad.getGenes()[i].getDefaultValues());
			x.copy(dad.getGenes()[i]);
			son1.getGenes()[i] = x;

			Gene y = new Gene();
			y.copy(mom.getGenes()[i]);
			son2.getGenes()[i] = y;
		}
		// Di truyền gene mẹ cho gene con
		for (int i = indexDivide; i < sizeOfGene; i++) {
			Gene x = new Gene();
			x.copy(mom.getGenes()[i]);
			son1.getGenes()[i] = x;

			Gene y = new Gene();
			y.copy(dad.getGenes()[i]);
			son2.getGenes()[i] = y;
		}

//		for (int i = 0; i < 9; i++) {
//			 System.out.println("Index " + i + " " +
//			 son2.getGenes()[i].getDefaultIndexs());
//			 System.out.println("Value " + i + " " +
//			 son2.getGenes()[i].getDefaultValues());
//		}

//		System.out.println("Dad after: " + dad.toString());
//		System.out.println("Mom after: " + mom.toString());
//		System.out.println("Cross at: " + indexDivide);
//		System.out.println("Son 1: " + son1.toString());
//		System.out.println("Fitness son 1: " + son1.fitness());
//		System.out.println("Son 2: " + son2.toString());
//		System.out.println("Fitness son 2: " + son2.fitness());

		return (son1.fitness() > son2.fitness() ? son2 : son1);
	}

	public static void main(String[] args) {
		SinglePointCrossover tpc = new SinglePointCrossover();
		Population population = new Population(20);
		int[][] genes = new int[][] { { 1, 2, 3, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 6, 0, 0, 0, 0, 0 },
				{ 5, 0, 0, 0, 0, 0, 2, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 3, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 8, 0, 0, 5, 0, 0 }, { 0, 0, 6, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 4, 0, 0 } };
		Individual defaultIn = new Individual(genes);
		population.initPopulation(defaultIn);
		Individual in = tpc.cross(population.getIndividuals()[2], population.getIndividuals()[3]);
		System.out.println("Son:" + in.toString());

	}

}
