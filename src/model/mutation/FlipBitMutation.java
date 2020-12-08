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
	public Individual oneGeneMutation(Individual individual) {
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
		int valueAtX = newGene.getTile(indexX);
		int valueAtY = newGene.getTile(indexY);

		// Đột biến bằng cách đổi vị trí của 2 nhiễm sắc thể trong gene
		newGene.setTile(indexX, valueAtY);
		// System.out.println("Nhiễm sắc thể 1: " + indexX);
		newGene.setTile(indexY, valueAtX);
		// System.out.println("Nhiễm sắc thể 2: " + indexY);

		return newInd;
	}

	@Override
	public Individual twoGeneMutation(Individual individual) {
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
			//System.out.println("Vị trí gene: " + indexGene[i]);

			// Lấy ngẫy nhiên 2 vị trí NST trong gene.
			int indexX = rd.nextInt(n);
			int indexY;
			do {
				indexY = rd.nextInt(n);
			} while (indexX == indexY);

			// Lấy 2 giá trị của 2 NST
			int valueAtX = newGene.getTile(indexX);
			int valueAtY = newGene.getTile(indexY);

			// Đột biến bằng cách đổi vị trí của 2 nhiễm sắc thể trong gene
			newGene.setTile(indexX, valueAtY);
			//System.out.println("Nhiễm sắc thể 1: " + indexX);
			newGene.setTile(indexY, valueAtX);
			//System.out.println("Nhiễm sắc thể 2: " + indexY);
		}

		return newInd;
	}

	public static void main(String[] args) {
		FlipBitMutation mutation = new FlipBitMutation();
		Individual oldIn = new Individual();
		oldIn.initIndividual();
		System.out.println("Old before: " + oldIn.toString());
		Individual newIn = mutation.twoGeneMutation(oldIn);
		System.out.println("Old after" + oldIn.toString());
		System.out.println("New: " + newIn.toString());
	}
}
