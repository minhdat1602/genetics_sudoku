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

//		for (int i = 0; i < 9; i++) {
//			System.out.println("Index " + i + " " + individual.getGenes()[i].getDefaultIndexs());
//			System.out.println("value " + i + " " + individual.getGenes()[i].getDefaultValues());
//		}

		// Random rd = new Random();
		// int type = rd.nextInt(5);
		// 80% đột biến 1 cặp NST (trên 1 Gene)
		// 20% đột biến 2 cặp NST (trên 2 Gene)
//		return (type < 4 ? apairChromosome(individual) : twoPairChromosome(individual));
		return apairChromosome(individual);
	}

	public Individual apairChromosome(Individual individual) {
		Random rd = new Random();

		// Khởi tạo cá thể đột biến
		// gene mới copy từ gene cũ, không phải dùng chung vùng nhớ.
		Individual newInd = new Individual(individual);

		int n = individual.getGenes().length;

		// lấy ngẫu nhiên một gene trong cá thể.

		int indexGene;
		do {
			indexGene = rd.nextInt(n);
			//System.out.println("a");
		} while (individual.getGenes()[indexGene].getDefaultIndexs().size() > 7);

		// System.out.println("Vị trí gene: " + indexGene);
		Gene newGene = new Gene();
		newGene.copy(newInd.getGenes()[indexGene]);

		// Lấy ngẫy nhiên 2 vị trí NST trong gene.
		int indexX;
		int indexY;
		do {
			// vi tri ???
			indexX = rd.nextInt(n);
			indexY = rd.nextInt(n);
			// System.out.println("X" + indexX);
			// System.out.println("Y" + indexY);
		//	System.out.println(indexGene);
		//	System.out.println("IndexList: " + newGene.getDefaultIndexs());
			// System.out.println("IndexX: " + indexX);
			// System.out.println("IndexY: " + indexY);

		} while (indexX == indexY || newGene.getDefaultIndexs().contains(indexX)
				|| newGene.getDefaultIndexs().contains(indexY));

		// Lấy 2 giá trị của 2 NST
		int valueAtX = newGene.getChromosomes()[indexX];
		int valueAtY = newGene.getChromosomes()[indexY];

		// Đột biến bằng cách đổi vị trí của 2 nhiễm sắc thể trong gene
		newGene.getChromosomes()[indexX] = valueAtY;
		newGene.getChromosomes()[indexY] = valueAtX;

		// System.out.println("Index " + newGene.getDefaultIndexs());
		// System.out.println("Value " + newGene.getDefaultValues());

		newInd.getGenes()[indexGene] = newGene;
		// System.out.println("Nhiễm sắc thể 1: " + indexX);
		// System.out.println("Nhiễm sắc thể 2: " + indexY);

		// for(int i = 0; i < 9; i++) {
		// System.out.println("Index " + i + " " +
		// newInd.getGenes()[i].getDefaultIndexs());
		// System.out.println("value " + i + " " +
		// newInd.getGenes()[i].getDefaultValues());
		// }

		return newInd;
	}

//	public Individual twoPairChromosome(Individual individual) {
//		Random rd = new Random();
//
//		Individual newInd = new Individual(individual);
//
//		int n = individual.getGenes().length;
//
//		// lấy ngẫu nhiên một gene trong cá thể.
//		int[] indexGene = new int[2];
//
//		int validIndex = -1;
//
//		for (int i = 0; i < 2; i++) {
//
//			indexGene[i] = rd.nextInt(n);
//			while (indexGene[i] == validIndex) {
//				indexGene[i] = rd.nextInt(n);
//			}
//			validIndex = indexGene[i];
//
//			Gene newGene = newInd.getGene(indexGene[i]);
//			// System.out.println("Vị trí gene: " + indexGene[i]);
//
//			// Lấy ngẫy nhiên 2 vị trí NST trong gene.
//			int indexX = rd.nextInt(n);
//			int indexY;
//			do {
//				indexY = rd.nextInt(n);
//			} while (indexX == indexY);
//
//			// Lấy 2 giá trị của 2 NST
//			int valueAtX = newGene.getChromosome(indexX);
//			int valueAtY = newGene.getChromosome(indexY);
//
//			// Đột biến bằng cách đổi vị trí của 2 nhiễm sắc thể trong gene
//			newGene.setChromosome(indexX, valueAtY);
//			// System.out.println("Nhiễm sắc thể 1: " + indexX);
//			newGene.setChromosome(indexY, valueAtX);
//			// System.out.println("Nhiễm sắc thể 2: " + indexY);
//		}
//
//		return newInd;
//	}

	public static void main(String[] args) {
		FlipBitMutation mutation = new FlipBitMutation();
		int[][] chromosomes = new int[][] { { 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0, 0, 2, 0, 0 },
				{ 3, 0, 0, 0, 0, 0, 0, 0, 0 }, { 4, 0, 5, 0, 0, 0, 0, 0, 0 }, { 5, 0, 0, 0, 7, 0, 0, 0, 0 },
				{ 6, 0, 0, 0, 0, 0, 0, 0, 0 }, { 7, 0, 0, 3, 0, 0, 0, 0, 0 }, { 8, 0, 0, 0, 0, 3, 0, 0, 0 },
				{ 9, 0, 0, 0, 0, 0, 0, 0, 0 } };
		Individual oldIn = new Individual(chromosomes);
		oldIn.fillRamdom();
		System.out.println("Old before: " + oldIn.toString());
		Individual newIn;
		for (int i = 0; i < 15; i++) {
			newIn = mutation.cellLevel(oldIn);
			System.out.println("Old after" + oldIn.toString());
			System.out.println("New: " + newIn.toString());
		}
		// System.out.println(newIn.rowHeuristic());
	}

}
