package model;

import java.util.Arrays;

import model.crossover.Crossover;
import model.crossover.TwoPointCrossover;
import model.mutation.FlipBitMutation;
import model.mutation.Mutation;
import model.selection.RankSelection;
import model.selection.Selection;

public class Gameboard {

	private Population oldGeneration;
	private Population newGeneration;

	private Crossover crossover;
	private Mutation mutation;
	private Selection selection;

	private boolean found;

	public Gameboard() {
		restart();
	}

	public void restart() {
		found = false;

		oldGeneration = new Population(1602);
		oldGeneration.initPopulation();

		newGeneration = new Population();

		crossover = new TwoPointCrossover();
		mutation = new FlipBitMutation();
		selection = new RankSelection();
	}

	// Lai tạo quần thể tạo ra n cá thể con
	public Individual[] crossover(Population pop) {

		int individualNum = pop.getIndividuals().length;
		Individual[] popCross = new Individual[individualNum];

		Individual offspring;

		for (int i = 0; i < individualNum; i++) {
			offspring = crossover.cross(pop);
			popCross[i] = offspring;
		}

		return popCross;
	}

	// đột biến 0.5 số lượng cá thể truyền vào tạo ra 0.5 cá thể con
	public Individual[] mutation(Individual[] individuals) {

		int sizeOfPop = individuals.length;

		Individual[] popMu = new Individual[sizeOfPop / 2];

		// System.out.println("Small size: " + sizeOfPop / 2);

		// Đột biến nữa sau các cá thể vừa lại tạo (các cá thể không tốt)
		Individual offspring;
		for (int i = sizeOfPop - 1; i >= sizeOfPop / 2; i--) {
			offspring = mutation.mutation(individuals[i]);
			popMu[sizeOfPop - i - 1] = offspring;
			// System.out.println("Big index:" + i);
			// System.out.println("Small index:" + (sizeOfPop - i - 1));
		}

		return popMu;
	}

	public void initNewGenetation() {
		// Lai tạo được một số cá thể
		Individual[] popCross = crossover(oldGeneration);
		// Sắp xếp các cá thể theo thứ tự xấu dần.
		Arrays.sort(popCross);
		// Lấy 1 nữa cá thể xấu nhất vừa lại tạo đi đột biến.
		Individual[] popMu = mutation(popCross);

		// Chọn số lượng cá thể tốt vừa đột biến và lai tạo thành thế hệ mới
		Individual[] newIndividuals = selection.rankSelection(oldGeneration.getIndividuals().length, popCross, popMu);
		newGeneration.setIndividuals(newIndividuals);
	}

	public void genetic() {
		while (!found) {
			// Khởi tạo thế hệ mới.
			System.out.println("Old:  " + oldGeneration.tostring());
			initNewGenetation();

			// Kiểm tra thế hệ mới có cá thể cần tìm không?
			System.out.println("New:  " + newGeneration.tostring());
			found = check(newGeneration.getIndividuals());
			if (found)
				break;

			// Nếu không tìm được, lấy thế hệ mới làm quần thể ban đầu và tiếp tục di
			// truyền.
			Individual[] nextIn = selection.rankSelection(oldGeneration.getIndividuals().length,
					newGeneration.getIndividuals(), oldGeneration.getIndividuals());
			oldGeneration.setIndividuals(nextIn);
			if (!found)
				System.out.println("Next: " + oldGeneration.tostring());
		}
	}

	public boolean check(Individual[] individuals) {
		for (Individual in : individuals) {
			if (in.fitness() == 0) {
				System.out.println("Destination individual is:" + in.toString());
				System.out.println("------------END----------------");
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Gameboard gb = new Gameboard();
		long st = System.currentTimeMillis();
		gb.genetic();
		long et = System.currentTimeMillis();
		System.out.println("Time sovle: " + (et - st) / 1000 + "s");
		/*
		 * Code bug nếu quần thể ban đầu là lẻ
		 * Lâu lâu nó bị ngu 1 lần nên giải không ra.
		 * Chương trình chạy quá lâu.
		 */
	}
}
