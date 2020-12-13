package model.mutation;

import java.util.Random;

import model.comunity.Gene;
import model.comunity.Individual;

public class FlipBitMutation implements Mutation {

	public FlipBitMutation() {

	}

	// FlipBitMutation: Đột biến ngẫu nhiên 2 nhiễm sắc thể trong một gene
	// Gene được chọn ngẫu nhiên
	// Sau khi đột biến tạo ra một cá thể mới, cá thể ban đầu vẫn giữ nguyên.

	@Override
	public Individual cellLevel(Individual individual) {
		Random rd = new Random();
		int type = rd.nextInt(5);
		// 80% đột biến 1 cặp NST (trên 1 Gene)
		// 20% đột biến 2 cặp NST (trên 2 Gene)
		return (type < 4 ? apairChromosome(individual) : twoPairChromosome(individual));
	}

	public Individual apairChromosome(Individual individual) {
		Random rd = new Random();

		// Khởi tạo cá thể đột biến
		// gene mới copy từ gene cũ, không phải dùng chung vùng nhớ.
		Individual newInd = new Individual(individual.getGenes());

		int n = individual.getGenes().length;

		// lấy ngẫu nhiên một gene trong cá thể.
		int indexGene = rd.nextInt(n);
		Gene newGene = newInd.getGene(indexGene);
		// System.out.println("Vị trí gene: " + indexGene);

		// Lấy ngẫy nhiên 2 vị trí NST trong gene.
		int indexX = rd.nextInt(n);
		int indexY;
		do {
			indexY = rd.nextInt(n);
		} while (indexX == indexY);

		// Lấy 2 giá trị của 2 NST
		int valueAtX = newGene.getChromosome(indexX);
		int valueAtY = newGene.getChromosome(indexY);

		// Đột biến bằng cách đổi vị trí của 2 nhiễm sắc thể trong gene
		newGene.setChromosome(indexX, valueAtY);
		// System.out.println("Nhiễm sắc thể 1: " + indexX);
		newGene.setChromosome(indexY, valueAtX);
		// System.out.println("Nhiễm sắc thể 2: " + indexY);

		return newInd;
	}

	public Individual twoPairChromosome(Individual individual) {
		Random rd = new Random();

		Individual newInd = new Individual(individual.getGenes());

		int n = individual.getGenes().length;

		// lấy ngẫu nhiên một gene trong cá thể.
		int[] indexGene = new int[2];

		int validIndex = -1;

		for (int i = 0; i < 2; i++) {

			indexGene[i] = rd.nextInt(n);
			while (indexGene[i] == validIndex) {
				indexGene[i] = rd.nextInt(n);
			}
			validIndex = indexGene[i];

			Gene newGene = newInd.getGene(indexGene[i]);
			// System.out.println("Vị trí gene: " + indexGene[i]);

			// Lấy ngẫy nhiên 2 vị trí NST trong gene.
			int indexX = rd.nextInt(n);
			int indexY;
			do {
				indexY = rd.nextInt(n);
			} while (indexX == indexY);

			// Lấy 2 giá trị của 2 NST
			int valueAtX = newGene.getChromosome(indexX);
			int valueAtY = newGene.getChromosome(indexY);

			// Đột biến bằng cách đổi vị trí của 2 nhiễm sắc thể trong gene
			newGene.setChromosome(indexX, valueAtY);
			// System.out.println("Nhiễm sắc thể 1: " + indexX);
			newGene.setChromosome(indexY, valueAtX);
			// System.out.println("Nhiễm sắc thể 2: " + indexY);
		}

		return newInd;
	}
	/*
	 * public Individual geneMutation(Individual individual) { Random rd = new
	 * Random();
	 * 
	 * // Lấy 2 gene X và Y int indexX = rd.nextInt(9); int indexY; do { indexY =
	 * rd.nextInt(9); } while (indexX == indexY); // Lấy 1 vị trí ngẫu nhiên trong
	 * gene X và Y. int index1 = rd.nextInt(9); int index2; do { index2 =
	 * rd.nextInt(9); } while (index2 == index1); // Khởi tạo gene đột biến.
	 * Individual inMu = new Individual(individual.getGenes()); // Đột biến 1 vị trí
	 * của gene X bằng 1 giá trị tại vị trí của gene Y. Gene geneX =
	 * inMu.getGene(indexX); Gene geneY = inMu.getGene(indexY);
	 * 
	 * int value1 = geneX.getChromosome(index1); int value2 =
	 * geneY.getChromosome(index2); // System.out.println("Gene: " + indexX +
	 * " Index: " + index1); // System.out.println("Gene: " + indexY + " Index: " +
	 * index2); geneX.setChromosome(index1, value2); geneY.setChromosome(index2,
	 * value1);
	 * 
	 * return inMu; }
	 */

	public static void main(String[] args) {
		FlipBitMutation mutation = new FlipBitMutation();
		Individual oldIn = new Individual();
		oldIn.initIndividual();
		System.out.println("Old before: " + oldIn.toString());
		Individual newIn = mutation.cellLevel(oldIn);
		System.out.println("Old after" + oldIn.toString());
		System.out.println("New: " + newIn.toString());
		// System.out.println(newIn.rowHeuristic());
	}

}
