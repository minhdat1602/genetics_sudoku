package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Gene {
	private int[] tiles;

	public Gene(int[] tiles) {
		super();
		this.tiles = tiles;
	}

	public Gene() {
		tiles = new int[9];
		fillRandom();
	}

	public void copy(Gene order) {
			tiles = Arrays.copyOf(order.getTiles(), tiles.length);
	}

	public String toString() {
		String st = "Gene:";
		for (int i = 0; i < 9; i++) {
			st += " " + tiles[i];
		}
		return st;
	}

	public void fillRandom() {
		Random random = new Random();
		ArrayList<Integer> notvalid = new ArrayList<Integer>();

		for (int i = 0; i < tiles.length; i++) {
			int value = random.nextInt(tiles.length) + 1;
			while (notvalid.contains(value)) {
				value = random.nextInt(tiles.length) + 1;
			}
			tiles[i] = value;
			notvalid.add(value);

		}
	}

	public int[] getTiles() {
		return tiles;
	}

	public void setTiles(int[] tiles) {
		this.tiles = tiles;
	}

	public void setTile(int index, int value) {
		tiles[index] = value;
	}

	public int getTile(int index) {
		return tiles[index];
	}

	public static void main(String[] args) {
		Gene gene = new Gene();
		System.out.println(gene.toString());
	}
}
