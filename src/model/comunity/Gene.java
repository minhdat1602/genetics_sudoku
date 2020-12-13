package model.comunity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Gene {
	private int[] chromosomes;

	public Gene(int[] chromosomes) {
		super();
		this.chromosomes = chromosomes;
	}

	public Gene() {
		chromosomes = new int[9];
		fillRandom();
	}

	public boolean equalChro(Gene order) {
		for (int i = 0; i < order.getChromosomes().length; i++) {
			if (chromosomes[i] != order.getChromosomes()[i])
				return false;
		}
		return true;
	}

	public void copy(Gene order) {
		chromosomes = Arrays.copyOf(order.getChromosomes(), chromosomes.length);
	}

	public String toString() {
		String st = "Gene:";
		for (int i = 0; i < 9; i++) {
			st += " " + chromosomes[i];
		}
		return st;
	}

	public void fillRandom() {
		Random random = new Random();
		ArrayList<Integer> notvalid = new ArrayList<Integer>();

		for (int i = 0; i < chromosomes.length; i++) {
			int value = random.nextInt(chromosomes.length) + 1;
			while (notvalid.contains(value)) {
				value = random.nextInt(chromosomes.length) + 1;
			}
			chromosomes[i] = value;
			notvalid.add(value);

		}
	}

	public int[] getChromosomes() {
		return chromosomes;
	}

	public void setChromosomes(int[] tiles) {
		this.chromosomes = tiles;
	}

	public void setChromosome(int index, int value) {
		chromosomes[index] = value;
	}

	public int getChromosome(int index) {
		return chromosomes[index];
	}

	public static void main(String[] args) {
		int[] i1 = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] i2 = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Gene gene = new Gene(i1);
		Gene x = new Gene(i2);
		System.out.println(gene.equalChro(x));
		System.out.println(gene.equalChro(gene));
		System.out.println(gene.toString());
	}
}
