package model.comunity;

import java.util.ArrayList;
import java.util.Random;

public class Gene {
	private int[] chromosomes;
	private ArrayList<Integer> defaultValues;
	private ArrayList<Integer> defaultIndexs;

	public Gene(int[] chromosomes) {
		super();

		this.chromosomes = chromosomes;

		defaultValues = new ArrayList<Integer>();
		defaultIndexs = new ArrayList<Integer>();

		for (int i = 0; i < chromosomes.length; i++) {
			if (chromosomes[i] != 0) {
				defaultValues.add(chromosomes[i]);
				defaultIndexs.add(i);
			}
		}
	}

	public Gene() {
		chromosomes = new int[9];
		defaultValues = new ArrayList<>();
		defaultIndexs = new ArrayList<>();
	}

	public boolean equalChro(Gene order) {
		for (int i = 0; i < order.getChromosomes().length; i++) {
			if (chromosomes[i] != order.getChromosomes()[i])
				return false;
		}
		return true;
	}

	public void copy(Gene order) {

		for (int i = 0; i < order.getChromosomes().length; i++) {
			chromosomes[i] = order.getChromosomes()[i];
		}
		
		
		this.defaultValues = order.defaultValues;
		this.defaultIndexs = order.defaultIndexs;
//		
//		for (int i = 0; i < order.defaultValues.size(); i++) {
//			this.defaultValues.add(order.defaultValues.get(i));
//			this.defaultIndexs.add(order.defaultIndexs.get(i));
//		}
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

		ArrayList<Integer> valids = new ArrayList<Integer>();

		for (int i = 0; i < chromosomes.length; i++) {
			if (chromosomes[i] == 0) {
				int value;
				do {
					value = random.nextInt(chromosomes.length) + 1;
				} while (valids.contains(value) || defaultValues.contains(value));

				chromosomes[i] = value;
				valids.add(value);

			}

		}
	}

	public int[] getChromosomes() {
		return chromosomes;
	}

	public void setChromosomes(int[] tiles) {
		this.chromosomes = tiles;
	}

	public ArrayList<Integer> getDefaultValues() {
		return defaultValues;
	}

	public void setDefaultValues(ArrayList<Integer> values) {
		this.defaultValues = values;
	}

	public ArrayList<Integer> getDefaultIndexs() {
		return defaultIndexs;
	}

	public void setDefaultIndexs(ArrayList<Integer> defaultIndexs) {
		this.defaultIndexs = defaultIndexs;
	}

	public static void main(String[] args) {
		int[] i1 = new int[] { 0, 0, 0, 0, 5, 6, 7, 8, 9 };
		Gene x1 = new Gene(i1);
		System.out.println(x1.getDefaultValues().toString());
		x1.fillRandom();

		Gene x2 = new Gene();
		x2.fillRandom();

		System.out.println(x1.toString());
		System.out.println(x2.toString());
	}
}
